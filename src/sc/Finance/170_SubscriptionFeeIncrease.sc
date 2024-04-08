theme: /SubscriptionFeeIncrease
    state: CheckAuth
        q!: $subscriptionFeeIncrease
        intent!: /170_SubscriptionFeeIncrease
        script:
            startIntent('170_SubscriptionFeeIncrease');
        if: $.session.subscriptionFeeIncrease
            go!: /SubscriptionFeeIncrease/Increase
        else:
            go!: /SubscriptionFeeIncrease/NoIncrease
    
    state: Increase
        script:
            announceAudio(audioDict.PovAP);
        if: $.session.cellPhone
            go!: /SubscriptionFeeIncrease/AskSMS
        else:
            go!: /WhatElse/WhatElse
                    
    state: NoIncrease
        script:
            announceAudio(audioDict.NoPovAP);
        if: $.session.cellPhone
            go!: /SubscriptionFeeIncrease/AskSMS
        else:
            go!: /WhatElse/WhatElse
        
    state: AskSMS  || modal = true
        script:
            announceAudio(audioDict.NeedSMSPovAP);
        
        state: SendSMS
            q: $commonYes 
            q: * $yesForSms *
            script:
                $.session.intent.stepsCnt++;
                sendSMSbyTemplate('838_SubscriptionFeeIncreaseSMS');
                if($.session.SMSstatus){
                    announceAudio(audioDict.SMSPovAP);
                }
            go!: /WhatElse/WhatElse
            
        state: DontSendSMS
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
                    $reactions.transition('/SubscriptionFeeIncrease/AskSMS');
                }else{
                    $reactions.transition('/WhatElse/WhatElse');
                }
        
        state: AgentRequest || noContext = true
            q: $agentRequest
            q: $no * $agentRequest
            intent: /405_AgentRequest
            script:
                if(countRepeatsInRow() < $injector.agentRequestLimit && !$.session.agentRequested) {
                    $.session.agentRequested = true;
                    announceAudio(audioDict.IUnderstoodUMust);
                    $reactions.transition("/SubscriptionFeeIncrease/AskSMS");
                }else{
                    announceAudio(audioDict.perevod_na_okc);
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $.session.intent.resultCode = 6;
                    stopIntent(); // завершаем основной интент
                    startIntent('/405_AgentRequest');
                    $.session.intent.resultCode = 6;
                    $reactions.transition('/Transfer/Transfer');
                }
