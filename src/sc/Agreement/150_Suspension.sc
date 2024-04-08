theme: /Suspension
    
    state: GetReason
        q!: $agreementSuspend
        intent!: /150_AgreementSuspend
        script:
            startIntent('150_AgreementSuspend');
        go!: ./Reason
        
        state: Reason || modal = true
            script:
                announceAudio(audioDict.ReasonBlocking);
        
            state: ToAgreementStop
                q: $agreementSuspendReason
                intent: /150_AgreementSuspend/AgreementSuspendReason
                q: $stopAgreement
                intent: /360_StopAgreement
                script:
                    $.session.intent.stepsCnt++;
                go!: /StopAgreement/CheckGSKworkTime
                
            state: ToUserMoving
                q: $userMoving 
                intent: /430_UserMoving
                script:
                    $.session.intent.stepsCnt++;
                go!: /UserMoving/UserMoving
                
            state: SuspensionOther
                q: уезж*
                q: $agreementSuspend
                intent: /150_AgreementSuspend
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    announceAudio(audioDict.suspensionLK);
                if: $.session.cellPhone
                    go!: SendSMS
                go!: /WhatElse/WhatElse
                
                state: SendSMS
                    script:
                        sendSMSbyTemplate('814_SuspensionSMS');
                        if($.session.SMSstatus){
                            announceAudio(audioDict.sms_Otpravlena_suspension);
                        }
                    go!: /WhatElse/WhatElse
                    
            state: AgentRequest || noContext = true
                q: $agentRequest
                intent: /405_AgentRequest
                script:
                    if(countAgentRequests($parseTree) < $injector.agentRequestLimit && !$.session.agentRequested) {
                        $.session.agentRequested = true;
                        announceAudio(audioDict.IUnderstoodUMust);
                        $reactions.transition("/Suspension/GetReason/Reason");
                    }else{
                        announceAudio(audioDict.perevod_na_okc);
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = 6;
                        stopIntent(); // завершаем основной интент
                        startIntent('/405_AgentRequest');
                        $.session.intent.resultCode = 6;
                        $reactions.transition('/Transfer/Transfer');
                    }
