theme: /TVQualityProblem
    state: TVQualityProblem
        q!: $tvQualityProblem
        intent!: /TV/270_TVQualityProblem
        script:
            startIntent('/270_TVQualityProblem');
            $.session.productId = 53; // 5 - интернет, 53 - ТВ
        go!: /TVQualityProblem/CheckServices
        
    state: CheckServices
        script:
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
                        $reactions.transition("/TVQualityProblem/CheckServices/CheckSpas");
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
                    $.session.returnState = '/TVQualityProblem/CheckServices/AreYouHome'; // стейт, чтобы вернуться, из создания сз
                    // проверяем необходимость сервисной заявки
                    if($.session.spas.tvProblem.technicalServiceRequest){
                        // заявка на техников сервиса
                        $reactions.transition("/CreateServiceRequest/TSrequest");
                    }else{
                        if($.session.spas.tvProblem.networkAdministrationRequest){
                            // заявка на техников сервиса
                            $reactions.transition("/CreateServiceRequest/NetworkAdminRequest");
                        }
                    }
                
                    if($.session.spas.tvProblem.technicalSupport){
                        $.session.callerInput = $.session.spas.tvProblem.flowLoss ? 'tech_octp' : 'tech_spas3';
                        announceAudio(audioDict.perevod_na_okc_from_TVChannelProblemIntent1);
                        $reactions.transition("/Transfer/CheckOCTP");
                    }else{
                        if($.session.spas.tvProblem.actionCategories){
                            // определяем коллер и код, чтобы потом по ним смогли перевести
                            $.session.callerInput = $.session.spas.tvProblem.drtvTicket ? 'tech_spas4' :
                                                    ($.session.spas.tvProblem.diagnostics ? 'tech_spas2' : 'tech_spas');
                        }
                    }
                }else{
                    $reactions.transition("/AwaitAction/RunAction");
                }
                
            go!: /TVQualityProblem/CheckServices/AreYouHome
            
        state: AreYouHome || modal = true
            script:
                if (typeof $.session.atHome !== 'undefined') {
                    if ($.session.atHome){
                        $reactions.transition("/TVQualityProblem/CheckServices/AreYouHome/CheckProducts");
                    }else{
                        $reactions.transition("/TVQualityProblem/CheckServices/AreYouHome/NotHome");
                    }
                }else{
                    announceAudio(audioDict.URatHomeTV);
                }
            
            state: CheckProducts
                q: $commonYes
                q: * $yesAtHome *
                script:
                    $.session.intent.stepsCnt++;
                    $.session.atHome = true;
                # проверяем продукты и оборудование
                if: $.session.hasIptv 
                    # есть иптв
                    script:
                        announceAudio(audioDict.InetIPTV);
                    go!: /PageSpeedConnection/LowSpeed
                elseif: $.session.domrutv || $.session.dctv
                    # цифровое тв
                    go!: /TVQualityProblem/CheckServices/AreYouHome/CheckProducts/RefreshCktv
                else:
                    go!: /TVQualityProblem/CheckServices/AreYouHome/CheckProducts/ToOperator
        
                state: ToOperator
                    script:
                        announceAudio(audioDict.perevod_na_okc);
                        $.session.intent.resultCode = $.session.intent.resultCode || 6;
                        $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    go!: /Transfer/CheckOCTP
                    
                state: RefreshCktv
                    script:
                        refreshCktv();
                        announceAudio(audioDict.RebutKabelTV);
                        announceAudio(audioDict.MusicTV);
                    go!: /TVQualityProblem/CheckServices/AreYouHome/CheckProducts/RefreshCktv/ConfimrFix
                    
                    state: ConfimrFix || modal = true
                        script:
                            announceAudio(audioDict.RebootRouter3);
                    
                        state: Fixed
                            q: $commonYes
                            q: * решил* *
                            q: * { проблем* (нет/не сохран*/ушл*) } *
                            script:
                                $.session.intent.stepsCnt++;
                                announceAudio(audioDict.HappySuccess);
                            go!: /WhatElse/WhatElse
                            
                        state: NotFixed
                            q: $commonNo
                            q: * не решил* *
                            q: * { проблем* (есть/сохран*/остал*) } *
                            script:
                                $.session.intent.stepsCnt++;
                                announceAudio(audioDict.perevod_na_okc_from_TVChannelProblemIntent_undoneAnswer);
                                $.session.intent.resultCode = $.session.intent.resultCode || 6;
                                $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            go!: /Transfer/CheckOCTP
    
                        state: СatchAll || noContext = true
                            event: noMatch
                            script:
                                $.session.intent.stepsCnt++;
                                if(countRepeatsInRow() < $injector.noMatchLimit) {
                                    $reactions.transition('/TVQualityProblem/CheckServices/AreYouHome/CheckProducts/RefreshCktv/ConfimrFix');
                                }else{
                                    announceAudio(audioDict.perevod_na_okc_from_TVChannelProblemIntent_undoneAnswer);
                                    $.session.intent.resultCode = $.session.intent.resultCode || 6;
                                    $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                                    $reactions.transition("/Transfer/CheckOCTP");
                                }
                            
            state: ToOperator
                script:
                    announceAudio(audioDict.perevod_na_okc);
                    $.session.intent.resultCode = $.session.intent.resultCode || 6;
                    $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                go!: /Transfer/CheckOCTP
            
            state: NotHome
                q: $commonNo
                q: * $noAtHome *
                script:
                    $.session.intent.stepsCnt++;
                    $.session.atHome = false;
                    announceAudio(audioDict.RUNotAtHomeTV);
                go!: /WhatElse/WhatElse
    
            state: AgentRequest || noContext = true
                q: $agentRequest
                intent: /405_AgentRequest
                go!: /LocalAgentRequest/AgentRequest
            
            state: СatchAll || noContext = true
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    # если больше двух раз, считаем, что клиент дома
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        announceAudio(audioDict.URatHomeTV);
                    }else{
                        $reactions.transition('/TVQualityProblem/CheckServices/AreYouHome/ToOperator');
                    }
