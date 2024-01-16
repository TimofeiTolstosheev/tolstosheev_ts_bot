theme: /CallsOnHold
  
    state: CallsOnHold
        q!: $callsOnHold
        intent!: /610_CallsOnHold
        script:
            startIntent('/610_CallsOnHold');
        go!: ../CallsOnHold1
        
    state: CallsOnHold1
        script:
            announceAudio(audioDict.Answering1);
        
        state: CallsOnHold2
            q: $callsOnHold
            intent: /610_CallsOnHold
            script:
                $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                announceAudio(audioDict.Answering4);
                stopIntent();
            go!: /Transfer/Transfer
       
        state: CallsOnHoldPickedUp
            script:
                $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                announceAudio(audioDict.CallsOnHoldPickedUp);
                
        state: AgentRequest || noContext = true
            q: $agentRequest
            intent: /405_AgentRequest
            script:
                startIntent('/405_AgentRequest');
                if(countRepeatsInRow() < $injector.noMatchLimit) {
                    announceAudio(audioDict.IUnderstoodUMust);
                    announceAudio(audioDict.No_answer4);
                }else{
                    announceAudio(audioDict.perevod_na_okc);
                    $.session.intent.resultCode = 6;
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $reactions.transition('/Transfer/Transfer');
                }
        
        state: СatchAll || noContext = true
            event: noMatch
            script:
                $.session.intent.stepsCnt++;
                if(countRepeatsInRow() < $injector.noMatchLimit) {
                    $reactions.transition('/CallsOnHold/CallsOnHold1/CallsOnHoldPickedUp');
                }else{
                    announceAudio(audioDict.perevod_na_okc);
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $reactions.transition('/Transfer/Transfer');
                }
