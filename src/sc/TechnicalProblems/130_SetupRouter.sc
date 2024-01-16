theme: /SetupRouter
    
    state: CheckProactive
        q!: $setupRouter
        intent!: /130_SetupRouter
        script:
            startIntent('130_SetupRouter');
        go!: ../CheckServices
    
    state: CheckServices
        script:
            $.session.productId = 5; // 5 - интернет, 53 - ТВ
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
                        $reactions.transition("/SetupRouter/CheckSpas");
                    }else{
                        if($.session.cellPhone){
                            $reactions.transition("/SetupRouter/AskSMS");
                        }else{
                            $reactions.transition("/SetupRouter/ToOperator");
                        }
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
                $.session.returnState = $.session.cellPhone ? '/SetupRouter/AskSMS' : '/SetupRouter/ToOperator'; // стейт, чтобы вернуться, из создания сз
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
        
        if: $.session.cellPhone
            go!: /SetupRouter/AskSMS
        else:
            go!: /SetupRouter/ToOperator
            
    state: AskSMS || modal = true
        script:
            announceAudio(audioDict.sms_NastroykaRoutera);
            announceAudio(audioDict.doYouWant_sms);
        
        state: SendSMS
            q: $commonYes
            q: $yesForSms
            script:
                $.session.intent.stepsCnt++;
                sendSMS('803_SetupRouterSMS');
                if($.session.SMSstatus){
                    announceAudio(audioDict.sms_Otpravlena);
                    if($.session.phoneStatus == 1){
                        $reactions.transition("/SetupRouter/AskLoginSMS");
                    }else{
                        $reactions.transition("/WhatElse/WhatElse");
                    }
                }else{
                    announceAudio(audioDict.sms_neOtpravlena_transfer);
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $.session.intent.resultCode = 1;
                    $reactions.transition("/Transfer/Transfer");
                }
        
        state: NoSMS
            q: $commonNo
            q: $noForSms
            script:
                $.session.intent.stepsCnt++;
            if: $.session.phoneStatus == 1
                go!: /SetupRouter/AskLoginSMS
            else:
                script:
                    announceAudio(audioDict.Configuring_router_transfer);
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $.session.intent.resultCode = 6;
                    $reactions.transition("/Transfer/Transfer");
        
        state: AgentRequest || noContext = true
            q: $agentRequest
            intent: /405_AgentRequest
            script:
                if(countRepeatsInRow() < $injector.noMatchLimit) {
                    $reactions.transition("/SetupRouter/AskSMS");
                }else{
                    announceAudio(audioDict.perevod_na_okc);
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $.session.intent.resultCode = 6;
                    stopIntent(); // завершаем основной интент
                    startIntent('/405_AgentRequest');
                    $.session.intent.resultCode = 6;
                    $reactions.transition('/Transfer/Transfer');
                }    
        
        state: СatchAll || noContext = true
            event: noMatch
            script:
                $.session.intent.stepsCnt++;
                if(countRepeatsInRow() < $injector.noMatchLimit) {
                    $reactions.transition('/SetupRouter/AskSMS');
                }else{
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $.session.intent.resultCode = 1;
                    stopIntent();
                    announceAudio(audioDict.configuring_router_noMatchAnswer_transfer);
                    $reactions.transition("/Transfer/Transfer");
                }
    
    state: AskLoginSMS || modal = true
        script:
            announceAudio(audioDict.sms_AutorisationData);
            announceAudio(audioDict.doYouWant_sms);
        
        state: SendSMS
            q: $commonYes
            q: $yesForSms
            script:
                $.session.intent.stepsCnt++;
                sendSMS('804_CredentialsSMS');
                if($.session.SMSstatus){
                    announceAudio(audioDict.sms_Otpravlena);
                    $reactions.transition("/WhatElse/WhatElse");
                }else{
                    announceAudio(audioDict.sms_neOtpravlena_transfer);
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $.session.intent.resultCode = 1;
                    $reactions.transition("/Transfer/Transfer");
                }
        
        state: NoSMS
            q: $commonNo
            q: $noForSms
            script:
                $.session.intent.stepsCnt++;
            go!: /WhatElse/WhatElse
        
        state: AgentRequest || noContext = true
            q: $agentRequest
            intent: /405_AgentRequest
            script:
                if(countRepeatsInRow() < $injector.noMatchLimit) {
                    announceAudio(audioDict.doYouWant_sms);
                }else{
                    announceAudio(audioDict.perevod_na_okc);
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $.session.intent.resultCode = 6;
                    stopIntent(); // завершаем основной интент
                    startIntent('/405_AgentRequest');
                    $.session.intent.resultCode = 6;
                    $reactions.transition('/Transfer/Transfer');
                }
        
        state: СatchAll || noContext = true
            event: noMatch
            script:
                $.session.intent.stepsCnt++;
                if(countRepeatsInRow() < $injector.noMatchLimit) {
                    announceAudio(audioDict.doYouWant_sms);
                }else{
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $.session.intent.resultCode = 1;
                    stopIntent();
                    announceAudio(audioDict.configuring_router_noMatchAnswer_transfer);
                    $reactions.transition("/Transfer/Transfer");
                }
        
    state: ToOperator
        script:
            $.session.intent.stepsCnt++;
            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
            $.session.intent.resultCode = 6;
            announceAudio(audioDict.Configuring_router_transfer);
            $reactions.transition("/Transfer/Transfer");
