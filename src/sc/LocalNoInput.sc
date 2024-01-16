theme: /LocalNoInput
            
    state: NoInput || noContext = true
        event!: speechNotRecognized
        # для тестов
        q!: NoInput
        intent!: /NoInput
        
        script:
            countRepeatsInRow();
            $.session.noInputCount = $.session.noInputCount || 0;
            $.session.noInputCount++;
            if($.session.noInputCount < $injector.noInputLimit) {
                announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_1);
                $reactions.transition($.session.lastState);
            }else{
                announceAudio(audioDict.No_Input3);
                stopIntent(); // завершаем текущий интент
                startIntent("380_NoInput"); // дополнительно записываем NoInput
                $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || "silence";
                $.session.intent.resultCode = 10;
                stopIntent();
                $reactions.transition('/Transfer/Transfer');
            }
