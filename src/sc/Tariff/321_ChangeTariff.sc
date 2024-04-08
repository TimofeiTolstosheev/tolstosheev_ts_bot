theme: /ChangeTariff
    
    state: ChangeTariffSMS || modal = true
        q!: $changeTariff
        intent!: /321_ChangeTariff
        script:
            startIntent('/321_ChangeTariff');
            if($.session.cellPhone){
                announceAudio(audioDict.SmenaTarif);
            }else{
                announceAudio(audioDict.tarif_perevod_na_okc);
                $reactions.transition("/WhatElse/WhatElse");
            }
        
        state: CheckPhone 
            # не можем использовать * так как иначе можем сматчится с любым другим интентом
            q: * @Change *
            q: $changeTariff
            intent: /321_ChangeTariff
            q: $commonInfoTariff
            intent: /320_CommonInfoTariff
            q: $tariffInfo
            intent: /322_TariffInfo
            event: noMatch
            script:
                $.session.intent.stepsCnt++;
                if($.session.phoneStatus == 1){
                    announceAudio(audioDict.SMATarifAkt);
                }else{
                    announceAudio(audioDict.SMATarifNOAkt);
                }
                sendSMS('811_ChangeTariffSMS');
                if($.session.SMSstatus){
                    $reactions.transition("SMSsuccess");
                }else{
                    $reactions.transition("SMSerror");
                }
                
            state: SMSsuccess
                script:
                    announceAudio(audioDict.sms_Otpravlena);
                go!: /WhatElse/WhatElse 
                
            state: SMSerror
                script:
                    announceAudio(audioDict.sms_neOtpravlena_transfer);
                    $.session.intent.resultCode = 6;
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                go!: /Transfer/Transfer
                
        state: AgentRequest || noContext = true
                q: $agentRequest
                intent: /405_AgentRequest
                go!: /ChangeTariff/ChangeTariffSMS/CheckPhone