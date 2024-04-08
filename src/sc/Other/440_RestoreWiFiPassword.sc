theme: /RestoreWiFiPassword
    state: RestoreWiFiPassword
        q!: $restoreWiFipassword
        intent!: /440_RestoreWiFipassword
        script:
            startIntent('440_RestoreWiFipassword');
        go!: ../CheckRouter
        
    state: CheckRouter
        script:
            if($.session.userType == 'user' && $.session.cellPhone && $.session.phoneStatus == 1){
                // параметры для await-экшна
                $.session.awaitAction = $.session.awaitAction || {};
                $.session.awaitAction.returnState = $context.currentState;
                $.session.awaitAction.action = "checkRouter";
                $.session.awaitAction.readTimeout = 4000;
                $.session.awaitAction.audio = audioDict.initialWait;
                if($.session.awaitAction.status){
                    delete $.session.awaitAction;
                    if($.session.haveRouter && $.session.activeSession){
                        $reactions.transition('/RestoreWiFiPassword/AskSMS');
                    }else{
                        $reactions.transition("/RestoreWiFiPassword/FindPasswordByYourself");
                    }
                }else{
                    $reactions.transition("/AwaitAction/RunAction");
                }
            }else{
                $reactions.transition("/RestoreWiFiPassword/FindPasswordByYourself");
            }
        
    state: AskSMS || modal = true
        script:
            announceAudio(audioDict.sms_RestoreWiFipassword);
            announceAudio(audioDict.doYouWant_sms);
        
        state: SendSMS
            q: $commonYes
            q: * $yesForSms *
            script:
                $.session.intent.stepsCnt++;
                sendSMS('806_RestoreWiFiPasswordSMS');
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
            q: * $noForSms *
            script:
                $.session.intent.stepsCnt++;
                announceAudio(audioDict.RestoreWiFipassword_noActual);
            go!: /WhatElse/WhatElse
        
        state: СatchAll || noContext = true
            event: noMatch
            script:
                $.session.intent.stepsCnt++;
                if(countRepeatsInRow() < $injector.noMatchLimit) {
                    $reactions.transition('/RestoreWiFiPassword/AskSMS');
                }else{
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $.session.intent.resultCode = 1;
                    announceAudio(audioDict.OKC_RestoreWiFipassword_NOMATCH);
                    $reactions.transition("/Transfer/Transfer");
                }
    
    state: FindPasswordByYourself
        script:
            announceAudio(audioDict.RestoreWiFipassword_noActual);
        go!: /WhatElse/WhatElse
