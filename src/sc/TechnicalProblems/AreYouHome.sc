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
        "/AreYouHome/AreYouHome": "ThemeMatch"
    }, interceptGlobalMatch);
    
theme: /AreYouHome
            
    state: AreYouHome
        script:
            if (typeof $.session.atHome !== 'undefined') {
                if ($.session.atHome){
                    $reactions.transition('/AreYouHome/AreYouHome/Transfer');
                }else{
                    $reactions.transition('/AreYouHome/AreYouHome/NotAtHome');
                }
            }else{
                announceAudio(audioDict.UHomeInternetNoAvtor);
            }
            
        state: Transfer
            q: $commonYes
            q: * $yesAtHome *
            script:
                if($.session.productId == 5){
                    announceAudio(audioDict.perevod_na_okc_from_NoLink6xxIntent1);
                }else{
                    announceAudio(audioDict.perevod_na_okc_from_TVChannelProblemIntent1);
                }
                $.session.intent.resultCode = 6;
                $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                $reactions.transition('/Transfer/Transfer');
                
        state: NotAtHome
            q: $commonNo
            q: * $noAtHome *
            script:
                $.session.intent.stepsCnt++;
                announceAudio(audioDict.UHomeInternet3);
            go!: /WhatElse/WhatElse
            
        state: AgentRequest || noContext = true
            q: $agentRequest
            intent: /405_AgentRequest
            script:
                countAgentRequests($parseTree);
            if: $.session.agentRequestCount < 2 && !$.session.noInputCount && !$.session.repeatsInRow
                script:
                    announceAudio(audioDict.ConfirmCity_subject);
                go!: /AreYouHome/AreYouHome
            else:
                script:
                    announceAudio(audioDict.perevod_na_okc);
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $.session.intent.resultCode = 6;
                    stopIntent(); // завершаем основной интент
                    startIntent('/405_AgentRequest');
                    $.session.intent.resultCode = 6;
                    $reactions.transition('/Transfer/Transfer');

        state: ThemeMatch || noContext = true
            # перехватываем match в глобальную тематику при помощи interceptGlobalMatch в init-блоке
            script:
                countRepeatsInRow();
            if: $.session.repeatsInRow < 2
                script:
                    announceAudio(audioDict.ConfirmCity_subject);
                    $.session.themeMatched = true;
                    $reactions.transition('/AreYouHome/AreYouHome');
            else:
                go!: {{ $temp.matchTargetState }}
            
        state: NoInput || noContext = true
            event: speechNotRecognized
            # для тестов
            q: NoInput
            intent: /NoInput
            script:
                $.session.intent.stepsCnt++;
                countRepeatsInRow();
                $.session.noInputCount = $.session.noInputCount || 0;
                $.session.noInputCount++;
                if($.session.noInputCount < $injector.noInputLimit) {
                    announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_1);
                    $reactions.transition('/AreYouHome/AreYouHome');
                }else{
                    announceAudio(audioDict.No_Input3);
                    stopIntent(); // завершаем текущий интент
                    startIntent("380_NoInput"); // дополнительно записываем NoInput
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || "silence";
                    $.session.intent.resultCode = 10;
                    stopIntent();
                    $reactions.transition('/Transfer/Transfer');
                }
                    
        state: СatchAll || noContext = true
            event: noMatch
            script:
                $.session.intent.stepsCnt++;
                if(countRepeatsInRow() < $injector.noMatchLimit) {
                    $reactions.transition('/AreYouHome/AreYouHome');
                }else{
                    announceAudio(audioDict.perevod_na_okc);
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $.session.intent.resultCode = 6;
                    $reactions.transition('/Transfer/Transfer');
                }