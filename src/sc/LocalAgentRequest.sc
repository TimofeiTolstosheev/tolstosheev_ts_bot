theme: /LocalAgentRequest
    
    state: AgentRequest || noContext = true
        q!: $agentRequest
        intent!: /405_AgentRequest
        
        script:
            $.session.agentRequestCount = $.session.agentRequestCount || 0;
            $.session.agentRequestCount++;
            $.session.dialogLog = $.session.dialogLog || [];
            if(countRepeatsInRow() < $injector.agentRequestLimit && !$.session.agentRequested && !$.session.themeMatched) {
                $.session.agentRequested = true;
                // запишем интент "Запрос оператора" с кодом 0
                try{
                    var intentId = getIntentParam("405_AgentRequest", "intentCode");
                    if (typeof intentId == 'undefined') intentId = -1;
                } catch (e) { 
                    var intentId = -1;
                }
                logIntent(intentId, getNow(), getNow(), 1, 0, '', $.session.stateLog || $.session.prevStateLog); // дергаем logIntent, чтобы не перетереть основной интент
                $reactions.transition($.session.lastState);
            }else{
                // если до этого уже записали в dialogLog первый запрос оператора, то обновим код завершения на 6
                try{
                    var intentId = getIntentParam("405_AgentRequest", "intentCode");
                    if (typeof intentId == 'undefined') intentId = -1;
                } catch (e) { 
                    var intentId = -1;
                }
                var lastIntent = $.session.dialogLog.length > 0 ? $.session.dialogLog[$.session.dialogLog.length - 1].intentCode : null;
                if(lastIntent == intentId){
                    $.session.dialogLog[$.session.dialogLog.length - 1].exitCode = 6;
                }else{
                    // запишем интент "Запрос оператора" с кодом 6
                    logIntent(intentId, getNow(), getNow(), 1, 6, '', $.session.stateLog || $.session.prevStateLog);
                }
                if($.session.intent){
                    $.session.intent.resultCode = 6;
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput');
                    $.session.lastIntent = $.session.intent.currentIntent;
                }
                // если уже был коллер по какому-то интенту, то используем его. Если нет - оператора.
                $.session.callerInput = $.session.callerInput || $.injector.defaultCallerInput;
                if($.session.callerInput == 'silence'){ $.session.callerInput = 'agent'};
                announceAudio(audioDict.toAgentRequest);
                // для технических сценариев проверяем ОЦТП метки
                if($.injector.techProblemsIntents.indexOf($.session.lastIntent) > 0){
                    stopIntent();
                    $reactions.transition("/Transfer/CheckOCTP");
                }else{
                    $.session.intent.resultCode = 6;
                    stopIntent();
                    $reactions.transition("/Transfer/Transfer");
                }
            }
