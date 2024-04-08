init:
    function interceptGlobalMatch(targetState, onState) {
        // в стейте onState отлавливаем match в глобальную тематику
        // и форсированно переходим в стейт targetState
        return bind("selectNLUResult", function() {
            if ($.request.query !== "/start"
                && !$.nluResults.selected.clazz.startsWith(onState)) {
                $.temp.matchTargetState = $.nluResults.selected.clazz;
                // если указан относительный путь, склеиваем onState и targetState
                $.temp.targetState = targetState.startsWith("/")
                    ? targetState
                    : onState + "/" + targetState;
            }
        }, onState);
    }

    _.each({
        "/CommonInfoTariff/CommonInfoTariffSMS/Announce": "ThemeMatch"
    }, interceptGlobalMatch);

theme: /CommonInfoTariff
    
    state: CommonInfoTariffSMS || modal = true
        q!: $commonInfoTariff
        intent!: /320_CommonInfoTariff
        script:
            startIntent('320_CommonInfoTariff');
        go!: ./Announce
        
        state: Announce
            script:
                if($.session.cellPhone){
                    announceAudio(audioDict.CommonTarif);
                }else{
                    announceAudio(audioDict.tarif_perevod_na_okc);
                    $reactions.transition("/WhatElse/WhatElse");
                }
            
            state: CheckPhone
                event: noMatch
                q: $commonInfoTariff
                intent: /320_CommonInfoTariff
                q: $tariffInfo
                intent: /322_TariffInfo
                q: $tariffType
                intent: /323_TariffType
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
                    
            state: AgentRequest 
                q: $agentRequest
                intent: /405_AgentRequest
                go!: /CommonInfoTariff/CommonInfoTariffSMS/Announce/CheckPhone
                
            state: ThemeMatch || noContext = true
                # перехватываем match в глобальную тематику при помощи interceptGlobalMatch в init-блоке
                go!: /CommonInfoTariff/CommonInfoTariffSMS/Announce/CheckPhone