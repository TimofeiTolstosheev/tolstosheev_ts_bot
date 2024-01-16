theme: /495_CommonTV
        
    state: CommonTV
        q!: $commonIssueTV
        intent!: /TV/495_CommonIssueTV
        script:
            startIntent('/495_CommonIssueTV');
        go!: ../CheckServices
    
    state: CheckServices
        script:
            $.session.productId = 53; // 5 - интернет, 53 - ТВ
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
                        $reactions.transition("/495_CommonTV/CheckServices/CheckSpas");
                    }else{
                        $reactions.transition('/495_CommonTV/CheckServices/GetIntent');
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
                $.session.awaitAction.readTimeout = 4000;;
                $.session.awaitAction.audio = audioDict.spasTimeout;
                if($.session.awaitAction.status){
                    delete $.session.awaitAction;
                    $.session.returnState = '/495_CommonTV/CheckServices/GetIntent'; // стейт, чтобы вернуться, из создания сз
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
                
            go!: /495_CommonTV/CheckServices/GetIntent
        
        state: GetIntent
            script:
                announceAudio(audioDict.CommonTVQuestion);
                
            state: ToTvChannelProblem
                # Пожалуйста, уточните конкретнее. у вас не работает телевидение? помехи на каналах? либо не работает ТВ приставка?
                q: * @Television *
                q: * не работает *
                q: $tvChannelProblem
                intent: /TV/290_TVChannelProblem
                script:
                    $.session.intent.stepsCnt++;
                go!: /TvChannelProblem/CheckProactive
                
            state: ToTVQualityProblem
                # Пожалуйста, уточните конкретнее. у вас не работает телевидение? помехи на каналах? либо не работает ТВ приставка?
                q: * помех* *
                q: $tvQualityProblem
                intent: /TV/270_TVQualityProblem
                script:
                    $.session.intent.stepsCnt++;
                go!: /TVQualityProblem/TVQualityProblem
                
            state: ToTVEquipmentProblem
                # Пожалуйста, уточните конкретнее. у вас не работает телевидение? помехи на каналах? либо не работает ТВ приставка?
                q: * (@TVEquipment/приставка) *
                q: $tvEquipmentProblem
                intent: /485_TVEquipmentProblem
                script:
                    $.session.intent.stepsCnt++;
                go!: /TVEquipmentProblem/TVEquipmentProblem
                
            state: СatchAll || noContext = true
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        $reactions.transition('/495_CommonTV/CheckServices/GetIntent');
                    }else{
                        $.session.intent.resultCode = $.session.intent.resultCode || 6;
                        $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        stopIntent();
                        announceAudio(audioDict.perevod_na_okc_from_CommonIssueTV);
                        $reactions.transition("/Transfer/Transfer");
                    }
