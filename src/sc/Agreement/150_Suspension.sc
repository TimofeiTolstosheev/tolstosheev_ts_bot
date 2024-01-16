theme: /Suspension
    state: /GetReason
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
            
            state: SuspensionOther
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
