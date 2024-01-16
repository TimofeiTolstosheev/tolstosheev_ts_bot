theme: /TvChannelProblem
    
    state: CheckProactive
        q!: $tvChannelProblem
        intent!: /TV/290_TVChannelProblem
        script:
            startIntent('290_TVChannelProblem');
            refreshCktv();
        go!: ../CheckServices
    
    state: CheckServices
        script:
            $.session.productId = 53; // 5 - интернет, 53 - ТВ
            // параметры для await-экшна
            $.session.awaitAction = $.session.awaitAction || {};
            $.session.awaitAction.returnState = $context.currentState;
            $.session.awaitAction.action = "checkServices";
            $.session.awaitAction.readTimeout = 2000;
            $.session.awaitAction.audio = audioDict.initialWait;
            if($.session.awaitAction.status || $.session.proactiveResult){
                delete $.session.awaitAction;
                if($.session.proactiveProblems){
                    $reactions.transition("/Proactive/RouteProactive");
                }else{
                    if($.session.userType == 'user'){
                        $reactions.transition("/TvChannelProblem/CheckSpas");
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
            
                $.session.returnState = '/TvChannelProblem/AreYouAtHome'; // стейт, чтобы вернуться, из создания сз
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
                    $.session.callerInput = $.session.spas.tvProblem.flowLoss ? 'tech_octp' :
                                            ($.session.spas.tvProblem.technicalSupport ? 'tech_spas3' : 'tech_spas');
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
        go!: /TvChannelProblem/AreYouAtHome
        
    state: AreYouAtHome || modal = true
        script:
            if (typeof $.session.atHome !== 'undefined') {
                if ($.session.atHome){
                    $reactions.transition("/TvChannelProblem/AreYouAtHome/HowMuchChannels");
                }else{
                    $reactions.transition("/TvChannelProblem/AreYouAtHome/NotAtHome");
                }
            }else{
                announceAudio(audioDict.URatHomeTV);
            }
    
        state: HowMuchChannels || modal = true
            q: $commonYes
            q: * $yesAtHome *
            script:
                $.session.intent.stepsCnt++;
                $.session.atHome = true;
                announceAudio(audioDict.howmuch_channels);
        
            state: AllChannels
                q: * ($allChannels / $noChannels) *
                script:
                    $.session.intent.stepsCnt++;
                    $.session.allChannelsProblem = true;
                go!: /TvChannelProblem/AreYouAtHome/HowMuchChannels/CheckProducts
                    
                state: HowMuchTV || modal = true
                    script:
                        announceAudio(audioDict['1or2TV']);
                    
                    state: HasOneProblemTV
                        q: * $oneTVSet *
                        script:
                            $.session.intent.stepsCnt++;
                            $.session.allTVproblem = false; // считаем false, чтобы не уводить в ветку создания СЗ
                            announceAudio(audioDict['1DecoderKTV']);
                        go!: /TvChannelProblem/AreYouAtHome/HowMuchChannels/CheckSwitchAccident
                    
                    state: HowMuchProblemTV
                        q: * $moreThanOneTVSet *
                        script:
                            $.session.intent.stepsCnt++;
                            announceAudio(audioDict.Problem1or2);

                        state: OneTVproblem
                            q: * $oneTVSet *
                            script:
                                $.session.intent.stepsCnt++;
                                $.session.allTVproblem = false;
                                announceAudio(audioDict.TVsetupGo);
                            go!: /SetupTVchannels/SetupTVchannels
                            
                        state: MultipleTVproblem
                            q: * $moreThanOneTVSet *
                            script:
                                $.session.intent.stepsCnt++;
                                $.session.allTVproblem = true;
                                announceAudio(audioDict['1DecoderKTV']);
                            go!: /TvChannelProblem/AreYouAtHome/HowMuchChannels/CheckSwitchAccident
            
                        state: catchAll || noContext = true
                            event: noMatch
                            script:
                                # если два раза не поняли, переводим на оператора
                                if(countRepeatsInRow() < $injector.noMatchLimit) {
                                    announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_3);
                                    announceAudio(audioDict.Problem1or2);
                                }else{
                                    announceAudio(audioDict.perevod_na_okc_from_TVChannelProblemIntent);
                                    $.session.intent.resultCode = $.session.intent.resultCode || 6;
                                    $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                                    $reactions.transition("/Transfer/CheckOCTP");
                                }
                                
                        state: LocalTVNoInput || noContext = true
                            event: speechNotRecognized
                            # для тестов
                            q: NoInput
                            intent: /NoInput
                            script:
                                countRepeatsInRow();
                                $.session.noInputCount = $.session.noInputCount || 0;
                                $.session.noInputCount++;
                                if($.session.noInputCount < $injector.noInputLimit && $.session.repeatsInRow < 3) {
                                    announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_1);
                                }else{
                                    announceAudio(audioDict.No_Input3);
                                    stopIntent(); // завершаем текущий интент
                                    startIntent("380_NoInput"); // дополнительно записываем NoInput
                                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || "silence";
                                    $.session.intent.resultCode = 10;
                                    stopIntent();
                                    $reactions.transition('/Transfer/Transfer');
                                }
            
                    state: catchAll || noContext = true
                        event: noMatch
                        script:
                            # если два раза не поняли, считаем, что у клиента несколько телевизоров
                            if(countRepeatsInRow() < $injector.noMatchLimit && !$.session.localRepeats) {
                                announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_3);
                                announceAudio(audioDict['1or2TV']);
                                $.session.localRepeats = true;
                            }else{
                                $reactions.transition("/TvChannelProblem/AreYouAtHome/HowMuchChannels/AllChannels/HowMuchTV/HasOneProblemTV");
                            }
                            
                state: LocalTVNoInput || noContext = true
                    event: speechNotRecognized
                    # для тестов
                    q: NoInput
                    intent: /NoInput
                    script:
                        countRepeatsInRow();
                        $.session.noInputCount = $.session.noInputCount || 0;
                        $.session.noInputCount++;
                        if($.session.noInputCount < $injector.noInputLimit && $.session.repeatsInRow < 3) {
                            announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_1);
                            $reactions.transition("/TvChannelProblem/AreYouAtHome/HowMuchChannels/AllChannels/HowMuchTV");
                        }else{
                            $reactions.transition("/TvChannelProblem/AreYouAtHome/HowMuchChannels/AllChannels/HowMuchTV/HowMuchProblemTV");
                        }
                
            state: SomeChannels
                q: * $partOfTheChannels *
                script:
                    $.session.intent.stepsCnt++;
                    $.session.allChannelsProblem = false;
                go!: /TvChannelProblem/AreYouAtHome/HowMuchChannels/CheckProducts
            
            state: InternetOnly
                script:
                    announceAudio(audioDict.InetOnly);
                    $.session.intent.resultCode = $.session.intent.resultCode || 6;
                    $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                go!: /Transfer/CheckOCTP

            state: CheckProducts
                # проверяем продукты
                if: $.session.int && !$.session.ktv && !$.session.domrutv && !$.session.ktv_social && !$.session.dctv
                    # только интернет
                    go!: /TvChannelProblem/AreYouAtHome/HowMuchChannels/InternetOnly
                elseif: !$.session.int && $.session.ktv && !$.session.domrutv && !$.session.ktv_social && !$.session.dctv
                    # только ктв
                    if: $.session.allChannelsProblem
                        go!: /TvChannelProblem/AreYouAtHome/HowMuchChannels/AllChannels/HowMuchTV
                    else:
                        script:
                            announceAudio(audioDict.perevod_na_okc_monoKTV_TV);
                            $.session.intent.resultCode = $.session.intent.resultCode || 6;
                            $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        go!: /Transfer/CheckOCTP
                else:
                    if: $.session.allChannelsProblem
                        # проверяем оборудование
                        if: $.session.tvSetup == 'ktv-only' || $.session.tvSetup == 'ktv-plus-box'
                            go!: /TvChannelProblem/AreYouAtHome/HowMuchChannels/AllChannels/HowMuchTV
                        elseif: $.session.tvSetup == 'multiple-box-no-iptv'
                            go!: /TvChannelProblem/AreYouAtHome/HowMuchChannels/AllChannels/HowMuchTV/HowMuchProblemTV
                        elseif: $.session.tvSetup == 'one-box-no-ktv'
                            go!: /TvChannelProblem/AreYouAtHome/HowMuchChannels/CheckSwitchAccident
                        elseif: $.session.hasIptv == true
                            script:
                                announceAudio(audioDict.InetIPTV);
                            go!: /NoLink/CheckProactive
                        else:
                            script:
                                announceAudio(audioDict.perevod_na_okc_from_TVChannelProblemIntent);
                                $.session.intent.resultCode = $.session.intent.resultCode || 6;
                                $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            go!: /Transfer/CheckOCTP
                    else:
                        go!: /OneChannelProblem/OneChannelProblem
                
            state: CheckSwitchAccident
                script:
                    $.session.intent.stepsCnt++;
                    // параметры для await-экшна
                    $.session.awaitAction = $.session.awaitAction || {};
                    $.session.awaitAction.returnState = $context.currentState;
                    $.session.awaitAction.action = "checkSwitchAccident";
                    $.session.awaitAction.readTimeout = 4000;
                    $.session.awaitAction.audio = audioDict.lalaWait10;
                    if($.session.awaitAction.status){
                        delete $.session.awaitAction;
                        if($.session.switchAccident){
                            announceAudio(audioDict.AvariaIPTV);
                            $reactions.transition("/WhatElse/WhatElse");
                        }else{
                            $reactions.transition("/TvChannelProblem/AreYouAtHome/HowMuchChannels/CheckSwitchAccident/NoAccident");
                        }
                    }else{
                        $reactions.transition("/AwaitAction/RunAction");
                    }
            
                state: NoAccident
                    script:
                        if($.session.allTVproblem){
                            $.session.serviceRequestComment = 'У клиента: отсутствует вещание на всех каналах, плотность подключения кабеля проверили, сложность сохраняется на всех ТВ.'
                            $reactions.transition("/CreateServiceRequest/TSrequest");
                        }else{
                            // если только кам-модуль
                            if($.session.iktvDevices["cam-module"] > 0 && !$.session.iktvDevices["dvbc"] &&
                               !$.session.iktvDevices["movix"] && !$.session.iktvDevices["iptv"] &&
                               !$.session.iktvDevices["virtualDevice"] && !$.session.iktvDevices["movixPro"] &&
                               !$.session.iktvDevices["assortmentBox"]){
                                announceAudio(audioDict.IPTVOctp);
                                $reactions.transition("/Transfer/CheckOCTP");
                            }else{
                                announceAudio(audioDict.IPTVOkc);
                                $.session.intent.resultCode = 6;
                                $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                                $reactions.transition("/Transfer/CheckOCTP");
                            }
                        }
            
            state: СatchAll || noContext = true
                event: noMatch
                # event: speechNotRecognized
                script:
                    $.session.intent.stepsCnt++;
                    # если больше двух раз, считаем, что проблема со всеми каналами
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_3);
                        announceAudio(audioDict.howmuch_channels);
                    }else{
                        $reactions.transition("/TvChannelProblem/AreYouAtHome/HowMuchChannels/AllChannels");
                    }
                    
            state: LocalTVNoInput || noContext = true
                event: speechNotRecognized
                # для тестов
                q: NoInput
                intent: /NoInput
                script:
                    countRepeatsInRow();
                    $.session.noInputCount = $.session.noInputCount || 0;
                    $.session.noInputCount++;
                    if($.session.noInputCount < $injector.noInputLimit && $.session.repeatsInRow < 3) {
                        announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_1);
                        $reactions.transition("/TvChannelProblem/AreYouAtHome/HowMuchChannels");
                    }else{
                        $reactions.transition("/TvChannelProblem/AreYouAtHome/HowMuchChannels/AllChannels");
                    }
                    
        state: NotAtHome
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
            script:
                if(countRepeatsInRow() < $injector.agentRequestLimit && !$.session.localNoMatch) {
                    $reactions.transition("/TvChannelProblem/AreYouAtHome");
                }else{
                    $.session.intent.resultCode = 6;
                    announceAudio(audioDict.perevod_na_okc);
                    startIntent('/405_AgentRequest');
                    $.session.intent.resultCode = 6;
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $reactions.transition('/Transfer/Transfer');
                }
    
        state: СatchAll || noContext = true
            event: noMatch
            script:
                $.session.intent.stepsCnt++;
                # если больше двух раз, считаем, что клиент дома
                if(countRepeatsInRow() < $injector.noMatchLimit) {
                    $.session.localNoMatch = true;
                    announceAudio(audioDict.URatHomeTV);
                }else{
                    $reactions.transition("/TvChannelProblem/AreYouAtHome/HowMuchChannels");
                }
