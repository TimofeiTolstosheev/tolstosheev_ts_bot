theme: /NoMatch
    
    state: GetIntent
        script:
            startIntent('0_NoMatch');
            announceAudio(audioDict.DnotUnderstandU1);
        q: $noAnswer || toState = "/NoAnswer/NoAnswer/NoAnswer2/NoAnswer3", onlyThisState = true
        intent: /390_NoAnswer || toState = "/NoAnswer/NoAnswer/NoAnswer2/NoAnswer3", onlyThisState = true
        
        state: GetIntent2
            event: noMatch
            q: ($commonYes/$commonNo/звон*) 
            intentGroup: /NoMatch
            script:
                announceAudio(audioDict.DnotUnderstandU2);
            
            state: Consulting
                q: $financeQuestion
                intent: /5_FinanceQuestion
                go!: /FinanceQuestion/FinanceQuestion
                
            state: Tech
                q: $commonTechnicalProblems
                intent: /250_CommonTechnicalProblems
                go!: /CommonTechnicalProblems/StartIntent
            
            state: CheckProactive
                event: noMatch
                intentGroup: /NoMatch
                script:
                    $.session.reboot = 0;
                    // параметры для await-экшна
                    $.session.awaitAction = $.session.awaitAction || {};
                    $.session.awaitAction.returnState = $context.currentState;
                    $.session.awaitAction.action = "checkServices";
                    $.session.awaitAction.readTimeout = 3000;
                    $.session.awaitAction.audio = audioDict.initialWait;
                    if($.session.awaitAction.status){
                        delete $.session.awaitAction;
                        if($.session.proactiveProblems){
                            announceAudio(audioDict.NotUnderstandableRequest);
                            $reactions.transition("/Proactive/RouteProactive");
                        }else{
                            $reactions.transition("/NoMatch/GetIntent/GetIntent2/CheckProactive/ToOperator");
                        }
                    }else{
                        $reactions.transition("/AwaitAction/RunAction");
                    }
                    
                state: ToOperator
                    script:
                        announceAudio(audioDict.perevod_na_okc);
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = 1;
                    go!: /Transfer/Transfer

        state: Operator
            q: $agentRequest
            intent: /405_AgentRequest
            script:
                if(!$.session.agentRequestCount){
                    $.session.agentRequestCount = $.session.agentRequestCount || 0;
                    $.session.agentRequestCount += 1;
                    $reactions.transition("/AgentRequest/CheckAuth");
                }else{
                    startIntent('405_AgentRequest');
                    if ($.session.userType == 'user'){
                        $reactions.transition("/AgentRequest/CheckAuth/Auth/GetIntent/AgentRequest");
                    }else{
                        $reactions.transition("/AgentRequest/CheckAuth/NotAuth/AgentRequest");
                    }
                }
