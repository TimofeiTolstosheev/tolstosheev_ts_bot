theme: /OneChannelProblem
    state: OneChannelProblem
        q!: $tvSeveralChannelsMalfunction
        intent!: /TV/275_TVSeveralChannelsMalfunction
        script:
            startIntent('/275_TVSeveralChannelsMalfunction');
            $.session.malfunctionChannels = getTvChannelNamesFromEntities();
            customLog('malfunctionChannels: ' + toPrettyString($.session.malfunctionChannels));
            $.session.productId = 53; // 5 - интернет, 53 - ТВ
        go!: /OneChannelProblem/CheckServices
        
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
                        if($.session.malfunctionChannels.length > 0){
                            $reactions.transition("/OneChannelProblem/CheckServices/GetChannels/CheckTVpackage");
                        }else{
                            $reactions.transition("/OneChannelProblem/CheckServices/GetChannels");
                        }
                    }else{
                        $reactions.transition("/AreYouHome/AreYouHome");
                    }
                }
            }else{
                $reactions.transition("/AwaitAction/RunAction");
            }
            
        state: GetChannels || modal = true
            script:
                announceAudio(audioDict.indicate_channelnameOneChannel);
            
            state: ParseChannels
                q: * @TVChannelName *
                q: * @TVChannelPackageName *
                q: (перв*/1) [канал]
                script:
                    $.session.intent.stepsCnt++;
                    $.session.malfunctionChannels = getTvChannelNamesFromEntities();
                    customLog('malfunctionChannels: ' + toPrettyString($.session.malfunctionChannels));
                go!: /OneChannelProblem/CheckServices/GetChannels/CheckTVpackage
                    
            state: CheckTVpackage
                script:
                    checkTVpackageByChannels();
                if: $.session.hasActivePacket
                    go!: /OneChannelProblem/CheckServices/AreYouHome
                else:
                    if: $.session.monthAgoInactivePacketId > 0
                        go!: /OneChannelProblem/CheckServices/GetChannels/CheckTVpackage/InactivePackage
                    else:
                        go!: /OneChannelProblem/CheckServices/GetChannels/CheckTVpackage/NoPackage
                
                state: InactivePackage
                    script:
                        announceAudio(audioDict.validity_OneChannel);
                        // если знаем пакет, то говорим название
                        if(tvPacketsDict[$.session.monthAgoInactivePacketId]){
                            if(audioDict[tvPacketsDict[$.session.monthAgoInactivePacketId]]){
                                announceAudio(audioDict[tvPacketsDict[$.session.monthAgoInactivePacketId]]);
                            }
                        }
                    go!: /OneChannelProblem/CheckServices/GetChannels/CheckTVpackage/NoPackage/PackagesInfo
                
                state: NoPackage
                    script:
                        announceAudio(audioDict.notIncluded_OneChannel);
                    go!: /OneChannelProblem/CheckServices/GetChannels/CheckTVpackage/NoPackage/PackagesInfo
                    
                    state: PackagesInfo
                        script:
                            announceAudio(audioDict.packageExpired_OneChannel);
                    
                        state: ToSetupTVchannels
                            q: $commonYes
                            q: $yesForConsultationAboutPackages
                            script:
                                $.session.intent.stepsCnt++;
                            go!: /ChannelPackage/ChannelPackageSMS
                       
                        state: WhatElse
                            q: $commonNo
                            q: $noForConsultationAboutPackages
                            script:
                                $.session.intent.stepsCnt++;
                            go!: /WhatElse/WhatElse
                            
                        state: catchAll || noContext = true
                            event: noMatch
                            intent: /NoMatch/Trash
                            script:
                                if(countRepeatsInRow() < $injector.noMatchLimit) {
                                    announceAudio(audioDict.packageExpired_OneChannel);
                                }else{
                                    $reactions.transition("/WhatElse/WhatElse");
                                }
                   
            state: AgentRequest || noContext = true
                q: $agentRequest
                intent: /405_AgentRequest
                script:
                    if(countRepeatsInRow() < $injector.agentRequestLimit) {
                        $.session.agentRequested = true;
                        announceAudio(audioDict.channel_nameOneChannel);
                    }else{
                        $reactions.transition("/OneChannelProblem/CheckServices/AreYouHome");
                    }
                    
            
            state: catchAll || noContext = true
                q: (@duckling.number/@duckling.ordinal)
                event: noMatch
                script:
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        announceAudio(audioDict.channel_nameOneChannel);
                    }else{
                        $reactions.transition("/OneChannelProblem/CheckServices/AreYouHome");
                    }
                    
        state: AreYouHome || modal = true
            script:
                if (typeof $.session.atHome !== 'undefined') {
                    if ($.session.atHome){
                        $reactions.transition("/OneChannelProblem/CheckServices/AreYouHome/CheckSpas");
                    }else{
                        $reactions.transition("/OneChannelProblem/CheckServices/AreYouHome/NotHome");
                    }
                }else{
                    announceAudio(audioDict.URatHomeTV);
                }
            
            state: CheckSpas
                q: $commonYes
                q: * $yesAtHome *
                script:
                    $.session.atHome = true;
                    // параметры для await-экшна
                    $.session.awaitAction = $.session.awaitAction || {};
                    $.session.awaitAction.returnState = $context.currentState;
                    $.session.awaitAction.action = "getSpasData";
                    $.session.awaitAction.readTimeout = 4000;
                    $.session.awaitAction.audio = audioDict.spasTimeout;
                    if($.session.awaitAction.status){
                        delete $.session.awaitAction;
                        $.session.returnState = '/OneChannelProblem/CheckServices/RefreshCktv'; // стейт, чтобы вернуться, из создания сз
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
                    
                go!: /OneChannelProblem/CheckServices/RefreshCktv
                        
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
                intentGroup: /NoMatch
                script:
                    # если больше двух раз, считаем, что клиент дома
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        announceAudio(audioDict.URatHomeTV);
                    }else{
                        $reactions.transition('/OneChannelProblem/CheckServices/AreYouHome/CheckSpas');
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
                    if($.session.noInputCount < $injector.noInputLimit && $.session.repeatsInRow < 2) {
                        announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_1);
                        $reactions.transition("/OneChannelProblem/CheckServices/AreYouHome");
                    }else{
                        $reactions.transition("/OneChannelProblem/CheckServices/AreYouHome/CheckSpas");
                    }
                
        state: RefreshCktv
            script:
                announceAudio(audioDict.refreshTVpacks);
                refreshCktv();
                announceAudio(audioDict.lalaWait);
            go!: ./Ask
            
            state: Ask
                script:
                    announceAudio(audioDict.after_refreshTVpacks);
            
                state: Fixed
                    q: $commonYes *
                    q: $yesAccess *
                    q: * { (всё/вещан*/изображен*/@TVChannel) (есть/появил*/показ*) } *
                    q: * { (всё/вещан*/изображен*/@TVChannel) *работ* } *
                    script:
                        $.session.intent.stepsCnt++;
                    go!: /WhatElse/WhatElse
                    
                state: NotFixed
                    q: $commonNo *
                    q: $noAccess *
                    q: * { (вещан*/изображен*/@TVChannel) (нет/отсутств*) } *
                    q: * { (вещан*/изображен*/@TVChannel) (не *работ*/не показ*) } *
                    q: * { (вещан*/изображен*/@TVChannel) (не появил*/не появляет*) } *
                    q: $tvChannelProblem
                    q: $tvQualityProblem
                    q: $tvSeveralChannelsMalfunction
                    q: $tvServiceProblem
                    q: $tvEquipmentProblem
                    q: $commonIssueTV
                    q: $setupTVChannels
                    intentGroup:: /TV
                    script:
                        $.session.intent.stepsCnt++;
                        if($.session.malfunctionChannels.length > 0){
                            announceAudio(audioDict.checked_OneChannel);
                            $reactions.transition("/SetupTVchannels/SetupTVchannels");
                        }else{
                            if($.session.cellPhone){
                                announceAudio(audioDict.TV_dostupen_kanal_paket1);
                                sendSMSbyTemplate('833_TVpackagesSMS');
                                if($.session.SMSstatus){
                                    announceAudio(audioDict.SMS_dostupen_kanal_paket);
                                }
                            }else{
                                announceAudio(audioDict.TV_dostupen_kanal_paket);
                            }
                        }
                    go!: /WhatElse/WhatElse

                state: СatchAll || noContext = true
                    event: noMatch
                    intentGroup: /NoMatch
                    script:
                        if(countRepeatsInRow() < $injector.noMatchLimit) {     
                            announceAudio(audioDict.after_refreshTVpacks);
                        }else{
                            announceAudio(audioDict.perevod_na_okc_from_TVChannelProblemIntent_noMatchAnswer);
                            $.session.intent.resultCode = $.session.intent.resultCode || 1;
                            $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            $reactions.transition('/Transfer/CheckOCTP');
                        }
