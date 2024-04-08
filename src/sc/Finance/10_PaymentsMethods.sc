theme: /PaymentsMethods
    
    state: PaymentsMethods
        q!: $paymentsMethods
        intent!: /10_PaymentsMethods
        script:
            startIntent('10_PaymentsMethods');
            $.session.repeatsInRow = $.session.repeatsInRow || 0;
            $.session.repeatsInRow += 1;
            
            if($.session.repeatsInRow == 3) {
                $reactions.transition('/Transfer/IntentLimiTransfer');
            }else{
                announceAudio(audioDict.paymentsMethods_ALL);
                if($.session.cellPhone) {
                    $reactions.transition("/PaymentsMethods/PaymentsMethods/AskSMS");
                }else{
                    $reactions.transition("/WhatElse/WhatElse");
                }
            }
            
        state: AskSMS || modal = true
            script:
                announceAudio(audioDict.SMSpayment);

            state: Yes 
                q: $commonYes 
                q: * $yesForSms *
                script:
                    $.session.intent.stepsCnt++;
                go!: /PaymentsMethods/PaymentsMethods/AskSMS/SmsGo
            
            state: No
                q: $commonNo 
                q: * $noForSms *
                script:
                    $.session.intent.stepsCnt++;
                go!: /WhatElse/WhatElse
                
            state: СatchAll || noContext = true
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_3);
                        $reactions.transition('/PaymentsMethods/PaymentsMethods/AskSMS');
                    }else{
                        announceAudio(audioDict.toAgentRequest);
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = 6;
                        stopIntent();
                        $reactions.transition('/Transfer/Transfer');
                    }
                    
            state: LocalAgentRequest
                q: $agentRequest
                intent: /405_AgentRequest
                script:
                    if(countAgentRequests($parseTree) < $injector.agentRequestLimit){
                        $.session.agentRequestCount = $.session.agentRequestCount || 0;
                        $.session.agentRequestCount += 1;
                        announceAudio(audioDict.Operator);
                        $reactions.transition('/PaymentsMethods/PaymentsMethods/AskSMS');
                    }else{
                        announceAudio(audioDict.toAgentRequest);
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = 6;
                        stopIntent(); // завершаем основной интент
                        startIntent('/405_AgentRequest');
                        $.session.intent.resultCode = 6;
                        $reactions.transition('/Transfer/Transfer');
                    }
                
            state: LocalNoInput || noContext = true
                event: speechNotRecognized
                # для тестов
                q: NoInput
                intent: /NoInput
                script:
                    countRepeatsInRow();
                    $.session.noInputCount = $.session.noInputCount || 0;
                    $.session.noInputCount++;
                    if($.session.noInputCount < $injector.noInputLimit) {
                        announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_1);
                        $reactions.transition('/PaymentsMethods/PaymentsMethods/AskSMS');
                    }else{
                        announceAudio(audioDict.No_Input3);
                        stopIntent(); // завершаем текущий интент
                        startIntent("380_NoInput"); // дополнительно записываем NoInput
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || "silence";
                        $.session.intent.resultCode = 10;
                        stopIntent();
                        $reactions.transition('/Transfer/Transfer');
                    }
            
            state: SmsGo
                script:
                    announceAudio(audioDict.i_will_send_sms);
                    sendSMSbyTemplate('802_PaymentsMethodsSMS');
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
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = 6;
                        stopIntent();
                    go!: /Transfer/Transfer
                    