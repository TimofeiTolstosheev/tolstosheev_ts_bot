theme: /WhatElse

    state: WhatElse
        q!: whatElse
        script:
            announceAudio(audioDict.WhatElseIntent_1);
        
        state: HaveQuestions
            q: $yesWhatElse *
            q: * $yesQuestions *
            q: * $anotherQuestion *
            script:
                announceAudio(audioDict.GetFirstIntent_3);
                
            state: CatchAll || noContext = true
                event: noMatch
                intentGroup: /NoMatch
                q: * (не @Successful) *
                q: [о/ну] да
                go!: /NoMatch/GetIntent
        
        state: NoQuestions
            q: $noWhatElse *
            q: (($yes ($no/не)) / ($no $yes))
            q: * $noQuestions *
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
                if($.session.userType == 'user' && $.injector.techProblemsIntents.indexOf($.session.lastIntent) > 0){
                    $reactions.transition("/Transfer/CheckOCTP");
                }else{
                    $.session.intent.resultCode = 6;
                    $reactions.transition("/Transfer/Transfer");
                }
                
        state: CatchAll || noContext = true
            event: noMatch
            q: {($no/не) [@SynonymsForAgreement] $yes}
            q: не (@Modal/@Give) $yes
            q: с вами не договоришься
            intentGroup: /NoMatch
            script:
                if(countRepeatsInRow() < $injector.noMatchLimit) {
                    $reactions.transition("/NoMatch/GetIntent");
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
            q: $noWhatElse *
            q: $noQuestions *
            q: $yesAccess
            go!: /WhatElse/ToExit
            
        state: HaveQuestions
            q: $yesWhatElse *
            q: * $yesQuestions *
            q: * $anotherQuestion *
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
            intentGroup: /NoMatch
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
        q!: [$no/все] { ($no/все) ($regexp<с?пасиб[оа]?>/благодар*) } $bye 
        q!: {большое $regexp<с?пасиб[оа]?>} $bye 
        q!: * { [$regexp<с?пасиб[оа]?>] * {[я/мы] [все] $regexp<выяснил[аи]?>} * $bye } *
        script:
            stopIntent();
            announceAudio(audioDict.toExit);
            $.session.callerInput = 'toExit';
            $reactions.transition("/Transfer/Transfer");
            
    state: NoQuestionsFirstStep
        intent!: /Thanks
        go!: /WhatElse/WhatElse
        
    state: HaveQuestionsFirstStep
        q!: $yesQuestions
        q!: { [это] [я/мы] куда (позвонил*/звоню/звоним/дозвонил*) }
        q!: { [это] [точно/вы [из]] @DomRu } [да]
        q!: (мне (хотелось [бы]/хочется)) (узнать/задать вопрос)
        go!: /WhatElse/WhatElse/HaveQuestions
        
    state: HaveNoQuestionsFirstStep
        q!: $noQuestions
        q!: { (ничем / никак / никого) $bye }
        q!: (я/мы/мне/нам) [уже] (решил/решили) проблему
        script:
            announceAudio(audioDict.WhatElseIntent_2);

        state: HaveQuestions
            q: $yesWhatElse *
            q: * $yesQuestions *
            q: * $anotherQuestion *
            go!: /WhatElse/WhatElse/HaveQuestions
        
        state: NoQuestions
            q: $noWhatElse *
            q: * $noQuestions *
            go!: /WhatElse/ToExit
            
        state: ToOperator
            q: $agentRequest
            intent: /405_AgentRequest
            script:
                if(countAgentRequests($parseTree) < $injector.agentRequestLimit && !$.session.agentRequested) {
                    $.session.agentRequested = true;
                    announceAudio(audioDict.IUnderstoodUMust);
                    $reactions.transition("/WhatElse/HaveNoQuestionsFirstStep");
                }else{
                    announceAudio(audioDict.perevod_na_okc);
                    $.session.callerInput = 'agent';
                    $.session.intent.resultCode = 6;
                    stopIntent(); // завершаем основной интент
                    startIntent('/405_AgentRequest');
                    $.session.intent.resultCode = 6;
                    $reactions.transition('/Transfer/Transfer');
                }
    
    state: BotPraising
        q!: $praising
        q!: * $thanksForOtherIntent *
        script:
            announceAudio(audioDict.DRT_0004);