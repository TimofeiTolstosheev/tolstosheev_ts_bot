theme: /CancelSuspension
    state: CheckSuspension
        q!: $cancelSuspend
        intent!: /155_CancelSuspend
        script:
            startIntent('155_CancelSuspend');
        if: $.session.userType == 'user' && $.session.suspension
            if: $.session.phoneStatus == 1
                go!: /CancelSuspension/AskToCancel
            else:
                go!: /CancelSuspension/ToWebCab
        else:
            go!: /CancelSuspension/ToOperator
            
    state: AskToCancel || modal = true
        script:
            announceAudio(audioDict.CancelSuspension);
            announceAudio(audioDict.CancelSuspension1);

        state: CancelSuspension
            q: $commonYes 
            q: * (снять/сними*/отмен*) *
            script:
                $.session.intent.stepsCnt++;
                cancelSuspension();
                if($.session.cancelSuspensionStatus){
                    announceAudio(audioDict.DiscontinuedSuspension);
                    $reactions.transition("/WhatElse/WhatElse");
                }else{
                    announceAudio(audioDict.toAgentRequest);
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $.session.intent.resultCode = 3;
                    $reactions.transition("/Transfer/Transfer");
                }

        state: NoCancel
            q: $anotherQuestion || toState = "/WhatElse/WhatElse/HaveQuestions"
            q: $commonNo
            q: * одн* услуг* *
            go!: /CancelSuspension/ToWebCab
        
        state: СatchAll || noContext = true
            event: noMatch
            script:
                $.session.intent.stepsCnt++;
                if(countRepeatsInRow() < $injector.noMatchLimit) {
                    announceAudio(audioDict.CancelSuspension1);
                }else{
                    $reactions.transition("/CancelSuspension/ToWebCab");
                }
    
    state: ToWebCab
        script:
            announceAudio(audioDict.CancelSuspensionLK);
        if: $.session.cellPhone
            go!: /CancelSuspension/SendMS
        else:
            go!: /WhatElse/WhatElse

    state: SendMS
        script:
            sendSMSbyTemplate('816_CancelSuspensionSMS');
            if($.session.SMSstatus){
                announceAudio(audioDict.sms_Otpravlena);
            }
            $reactions.transition("/WhatElse/WhatElse");

    state: ToOperator
        script:
            announceAudio(audioDict.AgentGoP);
            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
            $.session.intent.resultCode = 1;
            $reactions.transition("/Transfer/Transfer");
