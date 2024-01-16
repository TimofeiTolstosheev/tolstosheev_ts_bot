theme: /CommonInfoTariff
    
    state: CommonInfoTariffSMS || modal = true
        q!: $commonInfoTariff
        intent!: /320_CommonInfoTariff
        script:
            startIntent('/320_CommonInfoTariff');
            if($.session.cellPhone){
                announceAudio(audioDict.CommonTarif);
            }else{
                announceAudio(audioDict.tarif_perevod_na_okc);
                $reactions.transition("/WhatElse/WhatElse");
            }
        
        state: CheckPhone 
            q: *
            script:
                $.session.intent.stepsCnt++;
                if($.session.phoneStatus == 1){
                    announceAudio(audioDict.SMATarifAkt);
                }else{
                    announceAudio(audioDict.SMATarifNOAkt);
                }
                sendSMS('812_TariffInfoSMS');
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