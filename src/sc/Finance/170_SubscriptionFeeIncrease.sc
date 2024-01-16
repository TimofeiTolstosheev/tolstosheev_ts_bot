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
            q: $yesForSms
            script:
                $.session.intent.stepsCnt++;
                sendSMSbyTemplate('838_SubscriptionFeeIncreaseSMS');
                if($.session.SMSstatus){
                    announceAudio(audioDict.SMSPovAP);
                }
            go!: /WhatElse/WhatElse
            
        state: DontSendSMS
            q: $commonNo
            q: $noForSms
            q: $no * $agentRequest
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
