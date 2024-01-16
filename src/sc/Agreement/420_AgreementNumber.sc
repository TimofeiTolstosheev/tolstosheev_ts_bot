theme: /AgreementNumber
    state: CheckAuth
        q!: $agreementNumber
        intent!: /420_AgreementNumber
        script:
            startIntent('/420_AgreementNumber');
        
        if: $.session.userType == 'user'
            go!: /AgreementNumber/CheckAuth/AnnounceAgreementNumber
        else:
            go!: /AgreementNumber/CheckAuth/NotAuth
    
        state: AnnounceAgreementNumber
            script:
                splitAgreementNumber($.session.agreementNumber);
                announceAudio(audioDict.agreementNumber);
                announceTwoDigitNumber($.session.agreementNumber0);
                announceTwoDigitNumber($.session.agreementNumber1);
                announceTwoDigitNumber($.session.agreementNumber2);
                announceTwoDigitNumber($.session.agreementNumber3);
                announceTwoDigitNumber($.session.agreementNumber4);
                announceTwoDigitNumber($.session.agreementNumber5);
            if: $.session.cellPhone
                go!: /AgreementNumber/CheckAuth/AnnounceAgreementNumber/AskSms
            else:
                go!: /WhatElse/WhatElse
                
            state: AskSms  || modal = true
                script:
                    announceAudio(audioDict.SMSBalance);
                
                state: SendSms
                    q: $commonYes
                    q: $yesForSms
                    script:
                        $.session.intent.stepsCnt++;
                        sendSMS('801_AgreementNumber');
                        if($.session.SMSstatus){
                            $.session.intent.resultCode = 0;
                            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            announceAudio(audioDict.SMSGo);
                            $reactions.transition("/WhatElse/WhatElse");
                        }
                        else{
                            $.session.intent.resultCode = 6;
                            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            stopIntent();
                            announceAudio(audioDict.transferToAgentForFurther);
                            $reactions.transition('/Transfer/Transfer');
                        }
                    
                state: DontSendSms
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
                            $reactions.transition('/AgreementNumber/CheckAuth/AnnounceAgreementNumber/AskSms');
                        }else{
                            $.session.intent.resultCode = 2;
                            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            stopIntent();
                            announceAudio(audioDict.toAgentRequest);
                            $reactions.transition('/Transfer/Transfer');
                        }
            
        state: NotAuth
            script:
                $.session.intent.resultCode = 1;
                $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                announceAudio(audioDict.perevod_na_okc);
            go!: /Transfer/Transfer
