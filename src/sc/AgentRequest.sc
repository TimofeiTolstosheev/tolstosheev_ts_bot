theme: /AgentRequest
    
    state: CheckAuth
        script:
            startIntent('405_AgentRequest');
            $.session.agentRequested = true;
        if: $.session.agentRequestCount < 3
            if: $.session.userType == 'user'
                go!: /AgentRequest/CheckAuth/Auth
            else:
                go!: /AgentRequest/CheckAuth/NotAuth
        else:
            go!: /AgentRequest/CheckAuth/NotAuth/AgentRequest/CheckProactive/ToOperator
        
        state: Auth
            script:
                $.session.reboot = 0;
                // параметры для await-экшна
                $.session.awaitAction = $.session.awaitAction || {};
                $.session.awaitAction.returnState = $context.currentState;
                $.session.awaitAction.action = "checkServices";
                $.session.awaitAction.readTimeout = 1000;
                $.session.awaitAction.audio = audioDict.initialWait;
                if($.session.awaitAction.status || $.session.proactiveProblems){
                    delete $.session.awaitAction;
                    if($.session.proactiveProblems){
                        announceAudio(audioDict.IUnderstoodUMust);
                        $reactions.transition("/Proactive/RouteProactive");
                    }else{
                        $reactions.transition("/AgentRequest/CheckAuth/Auth/GetIntent");
                    }
                }else{
                    $reactions.transition("/AwaitAction/RunAction");
                }
        
            state: GetIntent
                script:
                    announceAudio(audioDict.GetFirstIntent_onAgentRequest_01);
                
                state: AgentRequest
                    q: $agentRequest
                    intent: /405_AgentRequest
                    if: countAgentRequests($parseTree) < 3
                        script:
                            $.session.intent.stepsCnt++;
                            announceAudio(audioDict.AgentHelpU2);
                    else:
                        go!: /AgentRequest/CheckAuth/NotAuth/AgentRequest/CheckProactive/ToOperator
                    
                    state: Consulting
                        q: $financeQuestion
                        intent: /5_FinanceQuestion
                        go!: /FinanceQuestion/FinanceQuestion
                        
                    state: Tech
                        q: $commonTechnicalProblems
                        intent: /250_CommonTechnicalProblems
                        script:
                            $.session.intent.stepsCnt++;
                        go!: /CommonTechnicalProblems/StartIntent
                    
                    state: ToOperator
                        q: $agentRequest
                        intent: /405_AgentRequest
                        script:
                            $.session.intent.stepsCnt++;
                            $.session.intent.resultCode = 6;
                            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            announceAudio(audioDict.toAgentRequest);
                            // для технических сценариев проверяем ОЦТП метки
                            if($.injector.techProblemsIntents.indexOf($.session.intent.currentIntent) > 0){
                                $reactions.transition("/Transfer/CheckOCTP");
                            }else{
                                $reactions.transition("/Transfer/Transfer");
                            }
            
                    state: CatchAll || noContext = true
                        event: noMatch
                        intent: /NoMatch/Trash
                        if: !$.session.repeatsInRow
                            script:
                                $.session.repeatsInRow = 1;
                                $reactions.transition("/NoMatch/GetIntent");
                        else:
                            script:
                                $reactions.transition("/NoMatch/GetIntent/GetIntent2");
                
        state: NotAuth
            script:
                announceAudio(audioDict.GetFirstIntent_onAgentRequest_01);
            
            state: AgentRequest
                q: $agentRequest
                intent: /405_AgentRequest
                script:
                    $.session.agentRequestCount = $.session.agentRequestCount || 0;
                    $.session.agentRequestCount += 1;
                if: $.session.agentRequestCount < 3
                    script:
                        $.session.intent.stepsCnt++;
                        announceAudio(audioDict.AgentHelpU);
                else:
                    go!: /AgentRequest/CheckAuth/NotAuth/AgentRequest/CheckProactive
                    
                state: Consulting
                    q: $financeQuestion
                    intent: /5_FinanceQuestion
                    script:
                        $.session.intent.stepsCnt++;
                    go!: /FinanceQuestion/FinanceQuestion/RouteFinanceQuestion
                    
                state: Tech
                    q: $commonTechnicalProblems
                    intent: /250_CommonTechnicalProblems
                    script:
                        $.session.intent.stepsCnt++;
                    go!: /CommonTechnicalProblems/StartIntent
                
                state: CheckProactive
                    q: $agentRequest
                    intent: /405_AgentRequest
                    script:
                        $.session.intent.stepsCnt++;
                        // параметры для await-экшна
                        $.session.awaitAction = $.session.awaitAction || {};
                        $.session.awaitAction.returnState = $context.currentState;
                        $.session.awaitAction.action = "checkServices";
                        $.session.awaitAction.readTimeout = 1000;
                        $.session.awaitAction.audio = audioDict.initialWait;
                        if($.session.awaitAction.status || $.session.proactiveProblems){
                            delete $.session.awaitAction;
                            if($.session.proactiveProblems){
                                announceAudio(audioDict.IUnderstoodUMust);
                                $reactions.transition("/Proactive/RouteProactive");
                            }else{
                                $reactions.transition("/AgentRequest/CheckAuth/NotAuth/AgentRequest/CheckProactive/ToOperator");
                            }
                        }else{
                            $reactions.transition("/AwaitAction/RunAction");
                        }
                    
                    state: ToOperator
                        script:
                            $.session.intent.resultCode = 6;
                            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            announceAudio(audioDict.toAgentRequest);
                            // для технических сценариев проверяем ОЦТП метки
                            if($.injector.techProblemsIntents.indexOf($.session.intent.currentIntent) > 0){
                                $reactions.transition("/Transfer/CheckOCTP");
                            }else{
                                $reactions.transition("/Transfer/Transfer");
                            }
            
            state: CatchAll || noContext = true
                event: noMatch
                intentGroup: /NoMatch
                script:
                    $.session.intent.stepsCnt++;
                go!: /NoMatch/GetIntent
