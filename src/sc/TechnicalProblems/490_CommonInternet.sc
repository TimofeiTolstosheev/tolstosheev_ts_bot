theme: /490_CommonInternet
        
    state: CommonInternet
        q!: $commonInternetUnd
        intent!: /Internet/490_CommonInternetUnd
        script:
            startIntent('/490_CommonInternetUnd');
        go!: ../CheckServices
    
    state: CheckServices
        script:
            $.session.productId = 5; // 5 - интернет, 53 - ТВ
            $.session.reboot = 0;
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
                        reboot(); //здесь запускаем, а результат смотрим дальше, если потребуется
                        $reactions.transition("/490_CommonInternet/CheckServices/CheckSpas");
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
                $.session.awaitAction.readTimeout = 4000;;
                $.session.awaitAction.audio = audioDict.spasTimeout;
                if($.session.awaitAction.status){
                    delete $.session.awaitAction;
                    $.session.returnState = '/490_CommonInternet/CheckServices/GetIntent'; // стейт, чтобы вернуться, из создания сз
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
                    // определяем коллер
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
                
            go!: /490_CommonInternet/CheckServices/GetIntent
        
        state: GetIntent
            script:
                announceAudio(audioDict.CommonInternetQuestion);
            
            state: СatchAll || noContext = true
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        $reactions.transition('/490_CommonInternet/CheckServices/GetIntent');
                    }else{
                        $.session.intent.resultCode = $.session.intent.resultCode || 6;
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        stopIntent();
                        announceAudio(audioDict.perevod_na_okc_from_CommonIssueInternet);
                        $reactions.transition("/Transfer/Transfer");
                    }
    
    state: CannotHelp
        q!: $commonInternetNoAct
        intent!: /Internet/490_CommonInternetNoAct
        script:
            startIntent('/490_CommonInternetNoAct');
        go!: ./CheckServices
        
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
                            $reactions.transition("/490_CommonInternet/CannotHelp/CheckSpas");
                        }else{
                            $reactions.transition('/490_CommonInternet/CheckServices/GetIntent');
                        }
                    }
                }else{
                    $reactions.transition("/AwaitAction/RunAction");
                }

        state: CheckSpas
            script:
                getSpasData();
                $.session.returnState = '/490_CommonInternet/CannotHelp/ToOperator'; // стейт, чтобы вернуться, из создания сз
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
                // определяем коллер
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
            go!: /490_CommonInternet/CannotHelp/ToOperator
        
        state: ToOperator
            script:
                announceAudio(audioDict.perevod_na_okc_from_CommonIssueInternet);
                $.session.intent.resultCode = $.session.intent.resultCode || 6;
                $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                stopIntent();
            go!: /Transfer/Transfer
