theme: /PaymentsMethods
    
    state: PaymentsMethods
        q!: $paymentsMethods
        intent!: /10_PaymentsMethods
        script:
            startIntent('/10_PaymentsMethods');
            announceAudio(audioDict.paymentsMethods_ALL);
          
        if: $.session.cellPhone
            go!: AskSMS
        else: 
            go!: /WhatElse/WhatElse
            
        state: AskSMS || modal = true
            script:
                announceAudio(audioDict.SMSpayment);

            state: Yes 
                q: $commonYes
                q: $yesForSms
                script:
                    $.session.intent.stepsCnt++;
                go!: /PaymentsMethods/PaymentsMethods/AskSMS/SmsGo
            
            state: No
                q: $commonNo
                q: $noForSms
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
                    