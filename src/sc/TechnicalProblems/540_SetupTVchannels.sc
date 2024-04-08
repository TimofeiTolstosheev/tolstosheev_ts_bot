theme: /SetupTVchannels
    state: SetupTVchannels
        q!: $setupTVChannels
        intent!: /TV/540_SetupTVChannels
        script:
            startIntent('/540_SetupTVChannels');
            $.session.productId = 53; // 5 - интернет, 53 - ТВ
        go!: /SetupTVchannels/CheckServices
        
    state: CheckServices
        script:
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
                        $reactions.transition("/SetupTVchannels/CheckServices/CheckSpas");
                    }else{
                        $reactions.transition('/SetupTVchannels/CheckServices/CheckCellPhone');
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
                    $.session.returnState = '/SetupTVchannels/CheckServices/CheckCellPhone'; // стейт, чтобы вернуться, из создания сз
                    // проверяем необходимость сервисной заявки
                    if($.session.spas.tvProblem.technicalServiceRequest){
                        // заявка на техников сервиса
                        $reactions.transition("/CreateServiceRequest/TSrequest");
                    }else{
                        if($.session.spas.tvProblem.networkAdministrationRequest){
                            // заявка на техников сервиса
                            $reactions.transition("/CreateServiceRequest/NetworkAdminRequest");
                        }
                    }
                    
                    if($.session.spas.tvProblem.technicalSupport){
                        $.session.callerInput = $.session.spas.tvProblem.flowLoss ? 'tech_octp' : 'tech_spas3';
                        announceAudio(audioDict.perevod_na_okc_from_TVChannelProblemIntent1);
                        $reactions.transition("/Transfer/CheckOCTP");
                    }else{
                        if($.session.spas.tvProblem.actionCategories){
                            // определяем коллер и код, чтобы потом по ним смогли перевести
                            $.session.callerInput = $.session.spas.tvProblem.drtvTicket ? 'tech_spas4' :
                                                    ($.session.spas.tvProblem.diagnostics ? 'tech_spas2' : 'tech_spas');
                        }
                    }
                }else{
                    $reactions.transition("/AwaitAction/RunAction");
                }
                
            go!: /SetupTVchannels/CheckServices/CheckCellPhone
            
        state: CheckCellPhone
            if: $.session.cellPhone
                go!: /SetupTVchannels/CheckServices/AskSMS
            else:
                go!: /SetupTVchannels/CheckServices/SetupInfo
        
        state: AskSMS || modal = true
            script:
                announceAudio(audioDict.SetupTVChannels_SMS);
            
            state: SendSMS
                q: $commonYes
                q: * $yesForSms *
                script:
                    $.session.intent.stepsCnt++;
                    sendSMSbyTemplate('805_SetupTVchannelsSMS');
                if: $.session.SMSstatus
                    go!: SMSsuccess
                else:
                    go!: SMSerror

                state: SMSsuccess
                    script:
                        announceAudio(audioDict.sms_Otpravlena);
                    go!: /WhatElse/WhatElse
                    
                state: SMSerror
                    script:
                        announceAudio(audioDict.transferToAgentForFurtherSMS);
                        $.session.intent.resultCode = $.session.intent.resultCode || 6;
                        $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        stopIntent();
                    go!: /Transfer/Transfer
                    
            state: DontSendSMS
                q: $commonNo
                q: * $noForSms *
                script:
                    $.session.intent.stepsCnt++;
                go!: /SetupTVchannels/CheckServices/SetupInfo
    
            state: AgentRequest || noContext = true
                q: $agentRequest
                intent: /405_AgentRequest
                script:
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        $reactions.transition("/SetupTVchannels/CheckServices/AskSMS");
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
                        $reactions.transition('/SetupTVchannels/CheckServices/AskSMS');
                    }else{
                        announceAudio(audioDict.toAgentRequest);
                        $.session.intent.resultCode = $.session.intent.resultCode || 1;
                        $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        stopIntent();
                        $reactions.transition('/Transfer/Transfer');
                    }

        state: SetupInfo
            script:
                announceAudio(audioDict.SetupTVChannels);
            go!: /WhatElse/WhatElse
