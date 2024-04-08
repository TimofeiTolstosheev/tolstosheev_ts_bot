theme: /NoLink
    state: CheckProactive
        q!: $noLink
        intent!: /Internet/300_NoLink
        script:
            startIntent('/300_NoLink');
            announceAudio(audioDict.chckSrvWithFioTimeout);
        go!: ../CheckServices
    
    state: CheckServices
        script:
            $.session.productId = 5; // 5 - интернет, 53 - ТВ
            $.session.reboot = 0;
            // параметры для await-экшна
            $.session.awaitAction = $.session.awaitAction || {};
            $.session.awaitAction.returnState = $context.currentState;
            $.session.awaitAction.action = "checkServices";
            $.session.awaitAction.readTimeout = 3000;
            $.session.awaitAction.audio = audioDict.lalaWait10;
            if($.session.awaitAction.status){
                delete $.session.awaitAction;
                if($.session.proactiveProblems){
                    $reactions.transition("/Proactive/RouteProactive");
                }else{
                    if($.session.userType == 'user'){
                        reboot(); //здесь запускаем, а результат смотрим дальше
                        $reactions.transition("/NoLink/CheckSpas");
                    }else{
                        $reactions.transition("/AreYouHome/AreYouHome");
                    }
                }
            }else{
                $reactions.transition("/AwaitAction/RunAction");
            }
        
    state: CheckSpas
        script:
            // параметры для await-экшна
            $.session.awaitAction = $.session.awaitAction || {};
            $.session.awaitAction.returnState = $context.currentState;
            $.session.awaitAction.action = "getSpasData";
            $.session.awaitAction.readTimeout = 1000;
            $.session.awaitAction.audio = audioDict.spasTimeout;
            if($.session.awaitAction.status){
                delete $.session.awaitAction;
                // создаем обращение
                var applComment;
                var clientUtterance = $parseTree.text;
                var problem = 'no-link-6xx-success';
                if ($.session.spas.internetProblem.actionCategories){
                    applComment = 'Ограничений на договоре не выявлено, запущена перезагрузка порта и роутера, проинформирован о необходимости проверки подключений и перезагрузки оборудования. В СПАС имеются метрики по продукту интернет.';
                }else{
                    applComment = 'Ограничений на договоре не выявлено, запущена перезагрузка порта и роутера, проинформирован о необходимости проверки подключений и перезагрузки оборудования.';
                }
                createAppeal(applComment, clientUtterance, $.session.productId, problem);
                
                $.session.returnState = '/NoLink/CheckSwitchAccident'; // стейт, чтобы вернуться, из создания сз
                // проверяем необходимость сервисной заявки
                if($.session.spas.internetProblem.technicalServiceRequest){
                    // заявка на техников сервиса
                    $reactions.transition("/CreateServiceRequest/TSrequest");
                }else{
                    if($.session.spas.internetProblem.networkAdministrationRequest){
                        // заявка на техников сервиса
                        $reactions.transition("/CreateServiceRequest/NetworkAdminRequest");
                    }
                }
                // определяем коллер и код, чтобы потом по ним перевести
                if($.session.spas.internetProblem.actionCategories){
                    if($.session.spas.internetProblem.technicalSupport){
                        $.session.callerInput = 'tech_spas3';
                    }else{
                        if($.session.spas.internetProblem.diagnostics){
                            $.session.callerInput = 'tech_spas2';
                        }else{
                            $.session.callerInput = 'tech_spas';
                        }
                    }
                }
            }else{
                $reactions.transition("/AwaitAction/RunAction");
            }
            
        go!: /NoLink/CheckSwitchAccident
    
    state: CheckSwitchAccident
        script:
            // параметры для await-экшна
            $.session.awaitAction = $.session.awaitAction || {};
            $.session.awaitAction.returnState = $context.currentState;
            $.session.awaitAction.action = "checkSwitchAccident";
            $.session.awaitAction.readTimeout = 1000;
            $.session.awaitAction.audio = audioDict.lalaWait10;
            if($.session.awaitAction.status){
                delete $.session.awaitAction;
            }else{
                announceAudio(audioDict.wait_timeout);
                $reactions.transition("/AwaitAction/RunAction");
            }
        if: $.session.switchAccident
            go!: /NoLink/CheckSwitchAccident/SwitchAccident
        else:
            go!: /NoLink/AreYouAtHome
        
        state: SwitchAccident
            script:
                announceAudio(audioDict.CommutatorAvaria);
                if($.session.applId){
                    closeAppeal($.session.applId);
                    delete $.session.applId;
                }
            go!: /WhatElse/WhatElse
            
    state: AreYouAtHome || modal = true
        script:
            if (typeof $.session.atHome !== 'undefined') {
                if ($.session.atHome){
                    $reactions.transition('/NoLink/CheckSession');
                }else{
                    $reactions.transition('/NoLink/AreYouAtHome/NotAtHome');
                }
            }else{
                announceAudio(audioDict.UHomeInternet);
            }
            
        state: Home
            q: $commonYes
            q: * $yesAtHome *
            go!: /NoLink/CheckSession
            
                        
        state: NotAtHome
            q: $commonNo
            q: * $noAtHome *
            script:
                $.session.intent.stepsCnt++;
                $.session.atHome = false;
                announceAudio(audioDict.UHomeInternet2);
                if($.session.applId){
                    closeAppeal($.session.applId);
                    delete $.session.applId;
                }
            go!: /WhatElse/WhatElse
            
        state: LocalNoInput || noContext = true
            event: speechNotRecognized
            # для тестов
            q: NoInput
            intent: /NoInput
            script:
                $.session.intent.stepsCnt++;
                countRepeatsInRow();
                $.session.noInputCount = $.session.noInputCount || 0;
                $.session.noInputCount++;
                if($.session.noInputCount < $injector.noInputLimit) {
                    announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_1);
                    $reactions.transition("/NoLink/AreYouAtHome");
                }else{
                    announceAudio(audioDict.No_Input3);
                    stopIntent(); // завершаем текущий интент
                    startIntent("380_NoInput"); // дополнительно записываем NoInput
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || "silence";
                    $.session.intent.resultCode = 10;
                    stopIntent();
                    $reactions.transition('/Transfer/Transfer');
                }
        
        state: AgentRequest || noContext = true
            q: $agentRequest
            intent: /405_AgentRequest
            script:
                countAgentRequests($parseTree);
            if: $.session.agentRequestCount < 2 && !$.session.noInputCount && !$.session.repeatsInRow
                script:
                    announceAudio(audioDict.Operator);
                go!: /NoLink/AreYouAtHome
            elseif: $.session.agentRequestCount < 3
                go!: /NoLink/CheckSession
            else:
                script:
                    announceAudio(audioDict.perevod_na_okc);
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $.session.intent.resultCode = 6;
                    stopIntent(); // завершаем основной интент
                    startIntent('/405_AgentRequest');
                    $.session.intent.resultCode = 6;
                    $reactions.transition('/Transfer/Transfer');
        
        state: СatchAll || noContext = true
            event: noMatch
            script:
                $.session.intent.stepsCnt++;
                # если больше двух раз, считаем, что клиент дома
                if(countRepeatsInRow() < $injector.noMatchLimit) {
                    announceAudio(audioDict.UHomeInternet);
                }else{
                    $reactions.transition('/NoLink/CheckSession');
                }
        
    state: CheckSession
        script:
            $.session.intent.stepsCnt++;
            $.session.atHome = true;
            if($.session.int){
                // проверим результат перезагрузки и статус сессии
                if($.session.rebootAwaitRequestId){
                    var response = getResponseByRequestId($.session.rebootAwaitRequestId);
                    if(response.data){
                        $.session.rebooted = response.data.rebooted;
                        $.session.rebootResult = response.data.rebootResult;
                        $.session.rebootPortResult = response.data.rebootPortResult;
                        $.session.dropSessionResult = response.data.dropSessionResult;
                        $.session.rebootRouterResult = response.data.rebootRouterResult;
                        $.session.checkSessionResult = response.data.checkSessionResult;
                        $.session.sessionStatus = response.data.sessionStatus;
                        $.session.authorizationType = response.data.authorizationType;
                        delete $.session.rebootAwaitRequestId;
                    }
                }
                
                if($.session.rebootResult == "No active internet"){
                    announceAudio(audioDict.noInternetProduct);
                    $.session.intent.resultCode = $.session.intent.resultCode || 6;
                    $reactions.transition("/Transfer/CheckOCTP");
                }else{
                    if($.session.rebootResult == "No parameters for diagnostics"){
                        announceAudio(audioDict.perevod_na_okc);
                        $.session.intent.resultCode = $.session.intent.resultCode || 6;
                        $reactions.transition("/Transfer/CheckOCTP");
                    }else{
                        if ($.session.sessionStatus == 'new') {
                            // сессия менее 5 минут
                            $reactions.transition('/NoLink/CheckSession/NewSessionLessThanFiveMin');
                        }else{
                            if ($.session.sessionStatus == 'old') {
                                // сессия более 5 минут
                                $reactions.transition('/NoLink/CheckSession/NewSessionMoreThanFiveMin');
                            }else{
                                // DHCP-82 сразу переводим
                                if($.session.authorizationType == 4){
                                    announceAudio(audioDict.perevod_na_okc);
                                    $.session.intent.resultCode = $.session.intent.resultCode || 6;
                                    $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                                    $reactions.transition("/Transfer/CheckOCTP");
                                }else{
                                    announceAudio(audioDict.NoConnection);
                                    $reactions.transition('/NoLink/CheckSession/NoSession');
                                }
                            }
                        }
                    }
                }
            }else{
                announceAudio(audioDict.noInternetProduct);
                $.session.intent.resultCode = $.session.intent.resultCode || 6;
                $reactions.transition("/Transfer/CheckOCTP");
            }
            
        state: NewSessionLessThanFiveMin
            # сессия менее 5 минут
            script:
                announceAudio(audioDict.NewSession);
                announceAudio(audioDict.NewSessionReboot);
                $reactions.transition("/NoLink/CheckSession/NewSessionLessThanFiveMin/Fix");
            
            state: Fix
                # отдельный стейт, чтобы при переспросе воспроизвели только этот стейт
                script:
                    announceAudio(audioDict.NewSessionWorking);
            
                state: Success
                    q: $yes
                    q: * $yesAccess *
                    script:
                        $.session.intent.stepsCnt++;
                        announceAudio(audioDict.HappySuccess);
                        if($.session.applId){
                            closeAppeal($.session.applId);
                            delete $.session.applId;
                        }
                    go!: /WhatElse/WhatElse
                
                state: Fail
                    q: $no
                    q: * $noAccess *
                    script:
                        $.session.intent.stepsCnt++;
                        announceAudio(audioDict.ThisIsStrange);
                        announceAudio(audioDict.Need_operator);
                    
                    state: ToOperator
                        q: $commonYes
                        q: $agentRequest
                        intent: /405_AgentRequest
                        q: * $yesOperator *
                        script:
                            $.session.intent.stepsCnt++;
                            announceAudio(audioDict.perevod_na_okc);
                            $.session.intent.resultCode = $.session.intent.resultCode || 6;
                        go!: /Transfer/CheckOCTP
                        
                    state: NoOperator
                        q: $commonNo
                        q: * $noOperator *
                        script:
                            $.session.intent.stepsCnt++;
                            if($.session.applId){
                                closeAppeal($.session.applId);
                                delete $.session.applId;
                            }
                        go!: /WhatElse/WhatElse
                    
                    state: СatchAll || noContext = true
                        event: noMatch
                        script:
                            $.session.intent.stepsCnt++;
                            if(countRepeatsInRow() < $injector.noMatchLimit) {
                                announceAudio(audioDict.Need_operator);
                            }else{
                                $reactions.transition('/NoLink/CheckSession/NewSessionLessThanFiveMin/Fix/Fail/ToOperator');
                            }
                    
                state: AgentRequest || noContext = true
                    q: $agentRequest
                    intent: /405_AgentRequest
                    script:
                        if(countRepeatsInRow() < $injector.noMatchLimit && !$.session.agentRequested) {
                            $reactions.transition("/NoLink/CheckSession/NewSessionLessThanFiveMin/Fix");
                        }else{
                            announceAudio(audioDict.perevod_na_okc);
                            $.session.intent.resultCode = 6;
                            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            stopIntent(); // завершаем основной интент
                            startIntent('/405_AgentRequest');
                            $.session.intent.resultCode = 6;
                            $reactions.transition('/Transfer/CheckOCTP');
                        }
                
                state: СatchAll || noContext = true
                    event: noMatch
                    script:
                        $.session.intent.stepsCnt++;
                        if(countRepeatsInRow() < $injector.noMatchLimit) {
                            announceAudio(audioDict.NewSessionWorking);
                        }else{
                            announceAudio(audioDict.perevod_na_okc_from_NoLink6xxIntent1);
                            $.session.intent.resultCode = $.session.intent.resultCode || 1;
                            $reactions.transition('/Transfer/CheckOCTP');
                        }
                
                state: LocalNoInput || noContext = true
                    event: speechNotRecognized
                    # для тестов
                    q: NoInput
                    intent: /NoInput
                    script:
                        $.session.intent.stepsCnt++;
                        countRepeatsInRow();
                        $.session.noInputCount = $.session.noInputCount || 0;
                        $.session.noInputCount++;
                        if($.session.noInputCount < $injector.noInputLimit) {
                            announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_1);
                            $reactions.transition("/NoLink/CheckSession/NewSessionLessThanFiveMin/Fix");
                        }else{
                            announceAudio(audioDict.No_Input3);
                            stopIntent(); // завершаем текущий интент
                            startIntent("380_NoInput"); // дополнительно записываем NoInput
                            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || "silence";
                            $.session.intent.resultCode = 10;
                            stopIntent();
                            $reactions.transition('/Transfer/Transfer');
                        }        
                
        state: NewSessionMoreThanFiveMin
            # сессия более 5 минут
            script:
                announceAudio(audioDict.RebootRouter);
                announceAudio(audioDict.lalaWait);
                announceAudio(audioDict.RebootRouter2);
                $reactions.transition('/NoLink/CheckSession/NewSessionMoreThanFiveMin/Fix');
            
            state: Fix
                # отдельный стейт, чтобы при переспросе воспроизвели только этот стейт
                script:
                    announceAudio(audioDict.RebootRouter3);
            
                state: Success
                    q: $commonYes
                    q: решил*
                    q: { проблем* (нет/не сохран*/ушл*) }
                    script:
                        $.session.intent.stepsCnt++;
                        announceAudio(audioDict.HappySuccess);
                        if($.session.applId){
                            closeAppeal($.session.applId);
                            delete $.session.applId;
                        }
                    go!: /WhatElse/WhatElse
                
                state: Fail
                    q: $commonNo
                    q: не решил*
                    q: { проблем* (есть/сохран*/остал*) }
                    script:
                        $.session.intent.stepsCnt++;
                    go!: /NoLink/CheckSession/NewSessionMoreThanFiveMin/Fix/Fail/ToOperator
                    
                    state: ToOperator
                        script:
                            announceAudio(audioDict.SorryNotWork);
                            $.session.intent.resultCode = $.session.intent.resultCode || 6;
                        go!: /Transfer/CheckOCTP
                    
                state: СatchAll || noContext = true
                    event: noMatch
                    script:
                        $.session.intent.stepsCnt++;
                        if(countRepeatsInRow() < $injector.noMatchLimit) {
                            announceAudio(audioDict.RebootRouter3);
                        }else{
                            $reactions.transition('/NoLink/CheckSession/NewSessionMoreThanFiveMin/Fix/Fail/ToOperator');
                        }
                        
                state: LocalNoInput || noContext = true
                    event: speechNotRecognized
                    # для тестов
                    q: NoInput
                    intent: /NoInput
                    script:
                        $.session.intent.stepsCnt++;
                        countRepeatsInRow();
                        $.session.noInputCount = $.session.noInputCount || 0;
                        $.session.noInputCount++;
                        if($.session.noInputCount < $injector.noInputLimit) {
                            announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_1);
                            $reactions.transition("/NoLink/CheckSession/NewSessionMoreThanFiveMin/Fix");
                        }else{
                            announceAudio(audioDict.No_Input3);
                            stopIntent(); // завершаем текущий интент
                            startIntent("380_NoInput"); // дополнительно записываем NoInput
                            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || "silence";
                            $.session.intent.resultCode = 10;
                            stopIntent();
                            $reactions.transition('/Transfer/Transfer');
                        }
            
        state: NoSession
            # сессия более 5 минут
            script:
                // параметры для await-экшна
                $.session.awaitAction = $.session.awaitAction || {};
                $.session.awaitAction.returnState = $context.currentState;
                $.session.awaitAction.action = "checkSession";
                $.session.awaitAction.readTimeout = 1000;
                $.session.awaitAction.audio = audioDict.NewSessionReboot_short;
                if($.session.awaitAction.status){
                    delete $.session.awaitAction;
                    if($.session.activeSessionAvailable){
                        $reactions.transition('/NoLink/CheckSession/NoSession/NewSession');
                    }else{
                        $reactions.transition('/NoLink/CheckSession/NoSession/NoNewSession');
                    }
                }else{
                    $reactions.transition("/AwaitAction/RunAction");
                }
            
            state: NewSession
                script:
                    announceAudio(audioDict.NoConnectionSuccess);
                
                state: Success
                    q: $yes
                    q: $yesForOpeningPages
                    script:
                        $.session.intent.stepsCnt++;
                        if($.session.applId){
                            closeAppeal($.session.applId);
                            delete $.session.applId;
                        }
                    go!: /WhatElse/WhatElse
                    
                state: Fail
                    q: $no
                    q: $noForOpeningPages
                    script:
                        $.session.intent.stepsCnt++;
                    go!: /NoLink/CheckSession/NoSession/NewSession/Fail/ToOperator
                    
                    state: ToOperator
                        script:
                            announceAudio(audioDict.SorryNotWork);
                            $.session.intent.resultCode = $.session.intent.resultCode || 6;
                        go!: /Transfer/CheckOCTP
                    
                state: СatchAll || noContext = true
                    event: noMatch
                    script:
                        $.session.intent.stepsCnt++;
                        if(countRepeatsInRow() < $injector.noMatchLimit) {
                            announceAudio(audioDict.NoConnectionSuccess);
                        }else{
                            $reactions.transition('/NoLink/CheckSession/NoSession/NewSession/Fail/ToOperator');
                        }
                        
                state: LocalNoInput || noContext = true
                    event: speechNotRecognized
                    # для тестов
                    q: NoInput
                    intent: /NoInput
                    script:
                        $.session.intent.stepsCnt++;
                        countRepeatsInRow();
                        $.session.noInputCount = $.session.noInputCount || 0;
                        $.session.noInputCount++;
                        if($.session.noInputCount < $injector.noInputLimit) {
                            announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_1);
                            $reactions.transition("/NoLink/CheckSession/NoSession/NewSession");
                        }else{
                            announceAudio(audioDict.No_Input3);
                            stopIntent(); // завершаем текущий интент
                            startIntent("380_NoInput"); // дополнительно записываем NoInput
                            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || "silence";
                            $.session.intent.resultCode = 10;
                            stopIntent();
                            $reactions.transition('/Transfer/Transfer');
                        }
                        
            state: NoNewSession
                script:
                    $.session.intent.stepsCnt++;
                    announceAudio(audioDict.NoConnectionDefeat);
                    $.session.returnState = '/WhatElse/WhatElse'; // стейт для возврата из СЗ
                    // проверим, надо ли создать СЗ - получаем параметр из checkSession
                    if($.session.serviceRequestRequired){
                        if($.session.aao){
                            $reactions.transition('/CreateServiceRequest/NetworkAdminRequest');
                        }else{
                            $reactions.transition('/CreateServiceRequest/TSrequest');
                        }
                    }else{
                        announceAudio(audioDict.SorryNotWork);
                        $.session.intent.resultCode = $.session.intent.resultCode || 6;
                        $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $reactions.transition("/Transfer/CheckOCTP");
                    }
