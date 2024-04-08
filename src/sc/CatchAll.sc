theme: /CatchAll
    state: NoMatch || noContext = true
        event!: noMatch
        script:
            if($.session.intent) $.session.intent.stepsCnt++;
            if(countRepeatsInRow() < $injector.noMatchLimit) {
                announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_3);
                if($.session.themeMatched){
                    $.session.themeMatched = false;
                    $reactions.transition($.session.prevContext);
                }else{
                    $reactions.transition($.session.lastState || $.session.lastEntryState);
                }
            }else{
                announceAudio(audioDict.perevod_na_okc);
                $.session.intent = $.session.intent || {};
                $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || "no_match";
                stopIntent(); // завершаем текущий интент
                startIntent("0_NoMatch");
                $.session.intent.resultCode = 1;
                stopIntent();
                $reactions.transition('/Transfer/Transfer');
            }
