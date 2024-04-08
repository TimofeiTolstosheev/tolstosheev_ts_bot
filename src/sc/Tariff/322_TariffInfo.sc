theme: /TariffInfo
    
    state: TariffInfoSMS
        q!: $tariffInfo
        intent!: /322_TariffInfo
        script:
            startIntent('/322_TariffInfo');
            logState("Информация о тарифах");
            if($.session.cellPhone){
                announceAudio(audioDict.SmenaTarifInfo);
                $reactions.transition("/TariffInfo/TariffInfoSMS/CheckPhone");
            }else{
                announceAudio(audioDict.tarif_perevod_na_okc);
                $reactions.transition("/WhatElse/WhatElse");
            }
        
        state: CheckPhone 
            q: *
            q: * @Change *
            q: $changeTariff
            intent: /321_ChangeTariff
            q: $commonInfoTariff
            intent: /320_CommonInfoTariff
            q: $tariffInfo
            intent: /322_TariffInfo
            script:
                $.session.intent.stepsCnt++;
                logState("Информация о тарифах. Отправка СМС.");
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
                    logState("Информация о тарифах. Успешная отправка СМС.");
                    announceAudio(audioDict.sms_Otpravlena);
                go!: /WhatElse/WhatElse 
                
            state: SMSerror
                script:
                    logState("Информация о тарифах. Ошибка отправки СМС.");
                    announceAudio(audioDict.sms_neOtpravlena_transfer);
                    $.session.intent.resultCode = 6;
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                go!: /Transfer/Transfer
