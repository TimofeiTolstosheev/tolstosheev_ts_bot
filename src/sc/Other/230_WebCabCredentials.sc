theme: /WebCabCredentials
    
    state: WebCabCredentials
        q!: $webCabCredentials
        intent!: /230_WebCabCredentials
        script:
            startIntent('/230_WebCabCredentials');
        if: $.session.cellPhone && $.session.userType == 'user' && $.session.phoneStatus == 1
            go!: /WebCabCredentials/WebCabCredentials/SMSLogPass
        else: 
            go!: /WebCabCredentials/WebCabCredentials/ToOperator
            
        state: ToOperator
            script:
                announceAudio(audioDict.OKC_WebCabCredentials);
                $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                $.session.intent.resultCode = 6;
                stopIntent();
            go!: /Transfer/Transfer
        
        state: SMSLogPass || modal = true
            script:
                announceAudio(audioDict.SMS_authorization);
            
            state: Yes
                q: $commonYes
                q: $yesForSms
                script:
                    $.session.intent.stepsCnt++;
                    announceAudio(audioDict.i_will_send_sms);                    
                    sendSMS('807_SMSCredentials');

                if: $.session.SMSstatus
                    go!: SMSsuccess
                else: 
                    go!: SMSerror
                       
                state: SMSsuccess
                    script:
                        announceAudio(audioDict.sms_Otpravlena); 
                    go!: /WebCabCredentials/WebCabCredentials/SMSLogPass/SMSRouter
                
                state: SMSerror
                    script:
                        announceAudio(audioDict.sms_neOtpravlena_transfer);
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = 6;
                        stopIntent();
                    go!: /Transfer/Transfer
                    
            state: No
                q: $commonNo
                q: $noForSms
                script:
                    $.session.intent.stepsCnt++;
                go!: /WebCabCredentials/WebCabCredentials/SMSLogPass/SMSRouter
            
            state: AgentRequest || noContext = true
                    q: $agentRequest
                    intent: /405_AgentRequest
                    script:
                        if(countRepeatsInRow() < $injector.noMatchLimit) {
                            $reactions.transition("/WebCabCredentials/WebCabCredentials/SMSLogPass");
                        }else{
                            announceAudio(audioDict.perevod_na_okc);
                            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            $.session.intent.resultCode = 6;
                            stopIntent(); // завершаем основной интент
                            startIntent('/405_AgentRequest');
                            $.session.intent.resultCode = 6;
                            $reactions.transition('/Transfer/Transfer');
                        }
            
            state: SMSRouter || modal = true
                script:
                    announceAudio(audioDict.Router_instructions);
                
                state: Yes 
                    q: $commonYes
                    q: $yesForSms
                    script:
                        $.session.intent.stepsCnt++;
                        sendSMS('808_SMSSetupRouter');

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
                            announceAudio(audioDict.sms_neOtpravlena_transfer);
                            $.session.callerInput = getIntentParam('/230_WebCabCredentials', 'callerInput') || $.injector.defaultCallerInput;
                            $.session.intent.resultCode = 6;
                            stopIntent();
                        go!: /Transfer/Transfer
                    
                state: No
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
                            $reactions.transition("/WebCabCredentials/WebCabCredentials/SMSLogPass/SMSRouter");
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
                    if: countRepeatsInRow() < $injector.noMatchLimit
                        go!: /WebCabCredentials/WebCabCredentials/SMSLogPass/SMSRouter
                    else:
                        go!: /WebCabCredentials/WebCabCredentials/SMSLogPass/SMSRouter/СatchAll/DoUNeedOperator
                        
                    state: DoUNeedOperator
                        script:
                            announceAudio(audioDict.Need_operator);
                        # подумать, возможно, $yesQuestions взять частично
                        q: [$yes] ($commonYes/$ok/$yesQuestions) || toState = "/WebCabCredentials/WebCabCredentials/SMSLogPass/SMSRouter/СatchAll/DoUNeedOperator/AgreeForOperator"
                        event: noMatch || toState = "/WebCabCredentials/WebCabCredentials/SMSLogPass/SMSRouter/СatchAll/DoUNeedOperator/AgreeForOperator"
                        q: [$no] ($commonNo/$never/$noQuestions) * ||toState = "/WebCabCredentials/WebCabCredentials/SMSLogPass/SMSRouter/СatchAll/DoUNeedOperator/DisagreeForOperator"
                    
                        state: AgreeForOperator
                            script:
                                announceAudio(audioDict.toAgentRequest);
                                $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                                $.session.intent.resultCode = 6;
                                stopIntent();
                                $reactions.transition('/Transfer/Transfer');
                                
                        state: DisagreeForOperator
                            go!: /WhatElse/WhatElse
                
            state: СatchAll || noContext = true
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        $reactions.transition('/WebCabCredentials/WebCabCredentials/SMSLogPass');
                    }else{
                        announceAudio(audioDict.toAgentRequest);
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = 6;
                        stopIntent();
                        $reactions.transition('/Transfer/Transfer');
                    }
