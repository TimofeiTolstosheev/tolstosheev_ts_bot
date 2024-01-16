# три намерения в одном сценарии: "Не открываются страницы", "Разрывы связи", "Низкая скорость"
theme: /PageSpeedConnection
    state: PageNotOpen
        q!: $pageNotOpen
        intent!: /Internet/260_PageNotOpen
        script:
            startIntent('/260_PageNotOpen');
            $.session.productId = 5; // 5 - интернет, 53 - ТВ
        go!: /PageSpeedConnection/CheckServices
        
    state: ConnectProblem
        q!: $connectProblem
        intent!: /Internet/310_ConnectProblem
        script:
            startIntent('/310_ConnectProblem');
            $.session.productId = 5; // 5 - интернет, 53 - ТВ
        go!: /PageSpeedConnection/CheckServices
        
    state: LowSpeed
        q!: $lowSpeed
        intent!: /Internet/280_LowSpeed
        script:
            startIntent('/280_LowSpeed');
            $.session.productId = 5; // 5 - интернет, 53 - ТВ
        go!: /PageSpeedConnection/CheckServices
        
    state: CheckServices
        script:
            $.session.reboot = 0;
            // параметры для await-экшна
            $.session.awaitAction = $.session.awaitAction || {};
            $.session.awaitAction.returnState = $context.currentState;
            $.session.awaitAction.action = "checkServices";
            $.session.awaitAction.readTimeout = 4000;
            $.session.awaitAction.audio = audioDict.initialWait;
            if($.session.awaitAction.status || $.session.proactiveResult){
                delete $.session.awaitAction;
                if($.session.proactiveProblems){
                    $reactions.transition("/Proactive/RouteProactive");
                }else{
                    if($.session.userType == 'user'){
                        reboot(); //здесь запускаем, а результат смотрим дальше, если потребуется
                        $reactions.transition("/PageSpeedConnection/CheckServices/CheckSpas");
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
                $.session.awaitAction.readTimeout = 4000;
                $.session.awaitAction.audio = audioDict.spasTimeout;
                if($.session.awaitAction.status){
                    delete $.session.awaitAction;
                    $.session.returnState = '/PageSpeedConnection/CheckServices/AreYouHome'; // стейт, чтобы вернуться, из создания сз
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
                
            go!: /PageSpeedConnection/CheckServices/AreYouHome
            
        state: AreYouHome || modal = true
            script:
                if (typeof $.session.atHome !== 'undefined') {
                    if ($.session.atHome){
                        $reactions.transition("/PageSpeedConnection/CheckServices/AreYouHome/CheckReboot");
                    }else{
                        $reactions.transition("/PageSpeedConnection/CheckServices/AreYouHome/NotHome");
                    }
                }else{
                    announceAudio(audioDict.UHomeInternet);
                }
        
            state: CheckReboot
                q: $commonYes
                q: * $yesAtHome *
                script:
                    $.session.intent.stepsCnt++;
                    $.session.atHome = true;
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
                            delete $.session.rebootAwaitRequestId;
                        }
                    }
                if: $.session.rebooted
                    script:
                        announceAudio(audioDict.RebootRouter);
                        announceAudio(audioDict.lalaWait);
                        announceAudio(audioDict.RebootRouter2);
                    go!: /PageSpeedConnection/CheckServices/AreYouHome/CheckReboot/Rebooted
                else:
                    go!: /PageSpeedConnection/CheckServices/AreYouHome/CheckReboot/NotRebooted

                state: Rebooted
                    script:
                        announceAudio(audioDict.RebootRouter3);
                    
                    state: Fixed
                        q: $commonYes
                        q: * решил* *
                        q: * { проблем* (нет/не сохран*/ушл*) } *
                        script:
                            $.session.intent.stepsCnt++;
                            announceAudio(audioDict.HappySuccess);
                        go!: /WhatElse/WhatElse
                    
                    state: NotFixed
                        q: $commonNo
                        q: * не решил* *
                        q: * { проблем* (есть/сохран*/остал*) } *
                        script:
                            $.session.intent.stepsCnt++;
                            announceAudio(audioDict.SorryNotWork);
                        go!: /Transfer/CheckOCTP

                    state: СatchAll || noContext = true
                        event: noMatch
                        script:
                            $.session.intent.stepsCnt++;
                            if(countRepeatsInRow() < $injector.noMatchLimit) {
                                announceAudio(audioDict.RebootRouter3);
                            }else{
                                announceAudio(audioDict.perevod_na_okc);
                                $reactions.transition("/Transfer/CheckOCTP");
                            }
                
                state: NotRebooted
                    script:
                        announceAudio(audioDict.perevod_na_okc);
                        $.session.intent.resultCode = 6;
                    go!: /Transfer/CheckOCTP
                    
            state: NotHome
                q: $commonNo
                q: * $noAtHome *
                script:
                    $.session.intent.stepsCnt++;
                    $.session.atHome = false;
                    announceAudio(audioDict.UHomeInternet2);
                go!: /WhatElse/WhatElse
                
            state: AgentRequest || noContext = true
                q: $agentRequest
                intent: /405_AgentRequest
                go!: /LocalAgentRequest/AgentRequest
    
            state: СatchAll || noContext = true
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    // если больше двух раз, считаем, что клиент дома
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        announceAudio(audioDict.UHomeInternet);
                    }else{
                        $reactions.transition("/PageSpeedConnection/CheckServices/AreYouHome/CheckReboot");
                    }
    
