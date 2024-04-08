theme: /CommonTechnicalProblems
    state: CommonTechnicalProblems
        q!: $noAccess
        q!: $commonTechnicalProblems
        intent!: /250_CommonTechnicalProblems
        # script:
        #     startIntent('/250_CommonTechnicalProblems');
        go!: ../StartIntent
    
    state: StartIntent
        script:
            startIntent('/250_CommonTechnicalProblems');
        go!: /CommonTechnicalProblems/StartIntent/CheckServices
    
        state: CheckServices
            script:
                $.session.productId = 5; // 5 - интернет, 53 - ТВ
                // параметры для await-экшна
                $.session.awaitAction = $.session.awaitAction || {};
                $.session.awaitAction.returnState = $context.currentState;
                $.session.awaitAction.action = "checkServices";
                $.session.awaitAction.readTimeout = 4000;
                $.session.awaitAction.audio = audioDict.initialWait;
                if($.session.awaitAction.status || $.session.proactiveResult){
                    delete $.session.awaitAction;
                    if($.session.proactiveProblems){
                        $reactions.transition("/Proactive/RouteProactive");
                    }else{
                        if($.session.userType == 'user'){
                            $reactions.transition("/CommonTechnicalProblems/StartIntent/CheckSpas");
                        }else{
                            $reactions.transition("/AreYouHome/AreYouHome");
                        }
                    }
                }else{
                    $reactions.transition("/AwaitAction/RunAction");
                }
            
        state: CheckSpas
            script:
                // параметры для await-экшна
                $.session.awaitAction = $.session.awaitAction || {};
                $.session.awaitAction.returnState = $context.currentState;
                $.session.awaitAction.action = "getSpasData";
                $.session.awaitAction.readTimeout = 4000;
                $.session.awaitAction.audio = audioDict.spasTimeout;
                if($.session.awaitAction.status){
                    delete $.session.awaitAction;
                    $.session.returnState = '/CommonTechnicalProblems/StartIntent/GetIntent'; // стейт, чтобы вернуться, из создания сз
                    // проверяем необходимость сервисной заявки
                    if($.session.spas.internetProblem.technicalServiceRequest){
                        // заявка на техников сервиса
                        $reactions.transition("/CreateServiceRequest/TSrequest");
                    }else{
                        if($.session.spas.internetProblem.networkAdministrationRequest){
                            // заявка на техников сервиса
                            $reactions.transition("/CreateServiceRequest/NetworkAdminRequest");
                        }
                    }
                    // определяем коллер и код, чтобы потом по ним перевести
                    if($.session.spas.internetProblem.actionCategories){
                        if($.session.spas.internetProblem.technicalSupport){
                            $.session.callerInput = 'tech_spas3';
                        }else{
                            if($.session.spas.internetProblem.diagnostics){
                                $.session.callerInput = 'tech_spas2';
                            }else{
                                $.session.callerInput = 'tech_spas';
                            }
                        }
                    }
                }else{
                    $reactions.transition("/AwaitAction/RunAction");
                }
            
            go!: /CommonTechnicalProblems/StartIntent/GetIntent
        
        state: GetIntent
            # уточняем интент в зависимости от продуктов на договоре
            script:
                # моно интернет
                if($.session.int && !$.session.ktv && !$.session.domrutv && !$.session.ktv_social && !$.session.dctv){
                    $reactions.transition("/CommonTechnicalProblems/StartIntent/GetIntent/MonoInternet");
                }
                # моно ТВ
                if(!$.session.int && ($.session.ktv || $.session.domrutv || $.session.ktv_social || $.session.dctv)){
                    $reactions.transition("/CommonTechnicalProblems/StartIntent/GetIntent/MonoTv");
                }
                # КП
                if($.session.int && ($.session.ktv || $.session.domrutv || $.session.ktv_social || $.session.dctv)){
                    $reactions.transition("/CommonTechnicalProblems/StartIntent/GetIntent/KP");
                }
                # нет ни интернета ни ТВ
                if(!$.session.int && !$.session.ktv && !$.session.domrutv && !$.session.ktv_social && !$.session.dctv){
                    $reactions.transition("/CommonTechnicalProblems/StartIntent/GetIntent/NoProducts");
                }
            
            state: MonoInternet
                script:
                    announceAudio(audioDict.SpecifyQuestion);
                
                state: Operator
                    q: $agentRequest
                    intent: /405_AgentRequest
                    script:
                        $.session.intent.stepsCnt++;
                        $.session.agentRequested = true;
                        $.session.intent.resultCode = $.session.intent.resultCode || 6;
                        $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        stopIntent();
                        startIntent('405_AgentRequest');
                        $.session.intent.resultCode = $.session.intent.resultCode || 6;
                        stopIntent();
                        announceAudio(audioDict.transferToAgentForFurther);
                        $reactions.transition("/Transfer/Transfer");
                    
                state: СatchAll || noContext = true
                    event: noMatch
                    script:
                        $.session.intent.stepsCnt++;
                        if(countRepeatsInRow() < $injector.noMatchLimit) {
                            $reactions.transition('/CommonTechnicalProblems/StartIntent/GetIntent/MonoInternet');
                        }else{
                            $.session.intent.resultCode = $.session.intent.resultCode || 6;
                            $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            stopIntent();
                            announceAudio(audioDict.transferToAgentForFurther);
                            $reactions.transition("/Transfer/Transfer");
                        }
                        
            state: MonoTv
                script:
                    announceAudio(audioDict.SpecifyBroadcasting);
                
                state: NoBroadcasting
                    q: * отсутствует *
                    q: $tvChannelProblem
                    intent: /TV/290_TVChannelProblem
                    script:
                        $.session.intent.stepsCnt++;
                    go!: /TvChannelProblem/CheckProactive
                        
                state: Interruption
                    q: * (рассыпа*/помех*) *
                    q: $tvQualityProblem
                    intent: /TV/270_TVQualityProblem
                    script:
                        $.session.intent.stepsCnt++;
                    go!: /TVQualityProblem/TVQualityProblem
            
                state: Operator
                    q: $agentRequest
                    intent: /405_AgentRequest
                    script:
                        $.session.intent.stepsCnt++;
                        $.session.agentRequested = true;
                        $.session.intent.resultCode = $.session.intent.resultCode || 6;
                        $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        stopIntent();
                        startIntent('405_AgentRequest');
                        $.session.intent.resultCode = $.session.intent.resultCode || 6;
                        stopIntent();
                        announceAudio(audioDict.transferToAgentForFurther);
                        $reactions.transition("/Transfer/Transfer");
            
                state: СatchAll || noContext = true
                    event: noMatch
                    script:
                        $.session.intent.stepsCnt++;
                        if(countRepeatsInRow() < $injector.noMatchLimit) {
                            $reactions.transition('/CommonTechnicalProblems/StartIntent/GetIntent/MonoTv');
                        }else{
                            $.session.intent.resultCode = $.session.intent.resultCode || 1;
                            $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            stopIntent();
                            announceAudio(audioDict.transferToAgentForFurther);
                            $reactions.transition("/Transfer/Transfer");
                        }
                
            state: KP
                script:
                    announceAudio(audioDict.SpecifyIntTV);
                
                state: Internet
                    q: * @Internet *
                    q: * { @Internet и  @Television } *
                    script:
                        $.session.intent.stepsCnt++;
                    go!: /NoLink/CheckProactive
                    
                state: TV
                    q: * @Television *
                    script:
                        $.session.intent.stepsCnt++;
                    go!: /TvChannelProblem/CheckProactive
                    
                state: Domofon
                    q: * (домофон*/@VideoThings) *
                    script:
                        $.session.intent.stepsCnt++;
                    go!: /Domofon/DomofonOKC  
            
                state: СatchAll || noContext = true
                    event: noMatch
                    script:
                        $.session.intent.stepsCnt++;
                        if(countRepeatsInRow() < $injector.noMatchLimit) {
                            $reactions.transition('/CommonTechnicalProblems/StartIntent/GetIntent/KP');
                        }else{
                            $.session.intent.resultCode = $.session.intent.resultCode || 1;
                            $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            stopIntent();
                            announceAudio(audioDict.transferToAgentForFurther);
                            $reactions.transition("/Transfer/Transfer");
                        }
                
            state: NoProducts
                script:
                    announceAudio(audioDict.NoIntTV);
                    $.session.intent.resultCode = $.session.intent.resultCode || 6;
                    $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    stopIntent();
                go!: /Transfer/Transfer
