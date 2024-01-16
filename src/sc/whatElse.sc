theme: /WhatElse

    state: WhatElse
        script:
            announceAudio(audioDict.WhatElseIntent_1);
        
        state: HaveQuestions
            q: ($yes / ад / lf / даа / $yes или $no $yes)
            q: * $yesQuestions *
            q: $anotherQuestion
            script:
                announceAudio(audioDict.GetFirstIntent_3);
                
            state: CatchAll || noContext = true
                event: noMatch
                go!: /NoMatch/GetIntent
        
        state: NoQuestions
            q: ($no / нт / ytn / нетт / $yes или $no $no) *
            q: $noQuestions *
            go!: /WhatElse/ToExit
            
        state: ToOperator
            q: $agentRequest
            intent: /405_AgentRequest
            script:
                $.session.agentRequested = true;
                announceAudio(audioDict.toAgentRequest);
                // оставляем коллер инпут интента
                $.session.callerInput = $.session.callerInput || ($.session.intent ? getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput : $.injector.defaultCallerInput);
                $.session.lastIntent = $.session.intent ? $.session.intent.currentIntent : "";
                stopIntent(); // завершаем основной интент
                startIntent('405_AgentRequest'); // дополнительно кладем интент "запрос оператор"
                // для технических сценариев проверяем ОЦТП метки
                if($.injector.techProblemsIntents.indexOf($.session.lastIntent) > 0){
                    $reactions.transition("/Transfer/CheckOCTP");
                }else{
                    $.session.intent.resultCode = 6;
                    $reactions.transition("/Transfer/Transfer");
                }
                
        state: CatchAll || noContext = true
            event: noMatch
            q: {$no @SynonymsForAgreement $yes}
            script:
                if(countRepeatsInRow() < $injector.noMatchLimit) {
                    $reactions.transition("/WhatElse/WhatElse");
                }else{
                    startIntent('0_NoMatch');
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    announceAudio(audioDict.perevod_na_okc);
                    $reactions.transition("/WhatElse/WhatElse/CatchAll/ToOperator");
                }
                
            state: ToOperator
                script:
                    $.session.intent.resultCode = 1;
                    $reactions.transition("/Transfer/Transfer");
                
    
    state: PromisedPayWhatElse
        script:
            announceAudio(audioDict.promisedPayWhatElse);
            
        state: NoQuestions
            q: ($no / нт / ytn / нетт) *
            q: $noQuestions *
            q: $yesAccess
            go!: /WhatElse/ToExit
            
        state: HaveQuestions
            q: ($yes / lf )
            q: * $yesQuestions *
            q: $anotherQuestion
            go!: /WhatElse/WhatElse/HaveQuestions
            
        state: ToOperator
            q: $agentRequest
            intent: /405_AgentRequest
            script:
                announceAudio(audioDict.toAgentRequest);
                $.session.agentRequested = true;
                $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                stopIntent();
                startIntent('405_AgentRequest');
                $.session.intent.resultCode = 6;
            go!: /Transfer/Transfer
                
        state: CatchAll || noContext = true
            event: noMatch
            q: {$no @SynonymsForAgreement $yes}
            script:
                $.session.intent.stepsCnt++;
                if(countRepeatsInRow() < $injector.noMatchLimit) {
                    $reactions.transition("/WhatElse/PromisedPayWhatElse");
                }else{
                    announceAudio(audioDict.perevod_na_okc);
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $.session.intent.resultCode = 6;
                    stopIntent();
                    startIntent('0_NoMatch');
                    $.session.intent.resultCode = 1;
                    stopIntent();
                    $reactions.transition("/Transfer/Transfer");
                }

    state: ToExit
        q!: $bye
        script:
            stopIntent();
            announceAudio(audioDict.toExit);
            $.session.callerInput = 'toExit';
            $reactions.transition("/Transfer/Transfer");
