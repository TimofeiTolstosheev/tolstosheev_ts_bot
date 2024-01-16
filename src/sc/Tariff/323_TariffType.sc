theme: /TariffType
    
    state: TariffTypeSMS
        q!: $tariffType
        intent!: /323_TariffType
        script:
            startIntent('/323_TariffType');
            announceAudio(audioDict.WhichTarif);
            if($.session.cellPhone){
                if($.session.phoneStatus == 1){
                    announceAudio(audioDict.SMATarifAkt);
                }else{
                    announceAudio(audioDict.SMATarifNOAkt);
                }
                sendSMS('813_TariffTypeSMS');
                if($.session.SMSstatus){
                    $reactions.transition("SMSsuccess");
                }else{
                    $reactions.transition("SMSerror");
                }
            }else{
                announceAudio(audioDict.tarif_perevod_na_okc);
                $reactions.transition("/WhatElse/WhatElse");
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
