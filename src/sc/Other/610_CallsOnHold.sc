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
                if(countRepeatsInRow() < $injector.noMatchLimit && !$.session.agentRequested) {
                    $.session.agentRequested = true;
                    // запишем интент "Запрос оператора" с кодом 0
                    logIntent(AGENT_REQUEST_INTENT_CODE, getNow(), getNow(), 1, 0, '', $.session.stateLog || $.session.prevStateLog); // дергаем logIntent, чтобы не перетереть основной интент
                    announceAudio(audioDict.IUnderstoodUMust);
                    announceAudio(audioDict.No_answer4);
                }else{
                    $.session.intent.resultCode = 6;
                    // проверим, был ли уже запрос оператора
                    // получим индекс интента оператора
                    var agentRequestIndex = _.findLastIndex($.session.dialogLog, {
                          intentCode: AGENT_REQUEST_INTENT_CODE});
                    if(agentRequestIndex >= 0){
                        $.session.dialogLog[agentRequestIndex].exitCode = 6;
                    }else{
                        // запишем интент "Запрос оператора" с кодом 6
                        logIntent($global.AGENT_REQUEST_INTENT_CODE, getNow(), getNow(), 1, 6, '', $.session.stateLog || $.session.prevStateLog);
                    }
                    announceAudio(audioDict.toAgentRequest);
                    $.session.callerInput = 'selfInformation';
                    $reactions.transition('/Transfer/Transfer');
                }
        
        state: СatchAll
            event: noMatch
            script:
                $.session.intent.stepsCnt++;
                countRepeatsInRow();
                if($.session.repeatsInRow < $injector.noMatchLimit) {
                    $reactions.transition('/CallsOnHold/CallsOnHold1/CallsOnHoldPickedUp');
                }else{
                    announceAudio(audioDict.perevod_na_okc);
                    $.session.intent.resultCode = 6;
                    $.session.callerInput = getIntentParam('0_NoMatch', 'callerInput') || $.injector.defaultCallerInput;
                    $reactions.transition('/Transfer/Transfer');
                }
