require: namesRu/names-ru.csv
    module = sys.zb-common
    name = Names
    var = $Names

require: city/cities-ru.csv
    module = sys.zb-common
    name = Cities
    var = $Cities

require: slotfilling/slotFilling.sc
  module = sys.zb-common
require: common.js
  module = sys.zb-common
require: dateTime/dateTime.sc
  module = sys.zb-common

# справочники
require: dicts/cities/citiesByRegion.yaml
    var = citiesByRegion
require: dicts/cities/citiesData.yaml
    var = citiesData
require: dicts/cities/billingAlias.yaml
    var = billingAlias
require: dicts/cities/citiesAudioMapping.yaml
    var = citiesAudioMapping
require: dicts/intents.yaml
    var = intentDict
require: dicts/tvPackets.yaml
    var = tvPacketsDict
require: dicts/audio.yaml
    var = audioDict
require: dicts/dateTimeAudio.yaml
    var = dateTimeAudioDict

# списки улиц
require: libraries/streetsDict1.csv
    name = streetsDict1
    var = $streetsDict1
require: libraries/streetsDict2.csv
    name = streetsDict2
    var = $streetsDict2
require: libraries/streetsDict3.csv
    name = streetsDict3
    var = $streetsDict3
require: libraries/streetsDict4.csv
    name = streetsDict4
    var = $streetsDict4
    # strict = true
require: libraries/userName.csv
    name = userName
    var = $userName

require: require.sc

init:
    // константы
    $global.AGENT_REQUEST_INTENT_CODE = 405;
    $global.BOT_ERROR_EXIT_CODE = 41;
    
    bind("preMatch", function($context){
        if($context.session.lastState == "/Init/HowCanIHelpYou" && $context.request.query){
            $context.request.query = checkMiscWordsInString($context.request.query);
            var isDigitsOnly = /^\d+/.test($context.request.query);
            if(isDigitsOnly){
                if($context.request.query.replace(/\s/g, "").replace(/\d+/g, "").length == 0){
                    $reactions.transition("/NoMatch/GetIntent");
                };
            };
            customLog("Filtered Phrase Result: " + toPrettyString($context.request.query));
            return $context.request.query;
        };
    });
    
    bind("preProcess", function($context){
        $.session.entryState = $context.currentState;
        $.session.queryCnt = $.session.queryCnt || 0;
        if($.request.query) $.session.queryCnt++;
        if($.session.queryCnt > 10){
            $analytics.setScenarioAction("Больше 10 фраз");
        };
        
        if($.session.lastState == "/WhatElse/WhatElse"){
            var interrupted = false;
            try{
                interrupted = $dialer.isBargeInInterrupted();
            }catch(e){
                customLog("Failed to check barge-in interruption. Error: " + e);
            }
            customLog("Interrupted: " + interrupted);
            if(interrupted){
                if($.session.entryState == "/WhatElse/WhatElse/ToOperator"){
                    $analytics.setScenarioAction("Перебивание WhatElse. Запрос оператора.");
                    $analytics.setSessionData("Перебивание WhatElse", "Запрос оператора");
                }else{
                    $analytics.setScenarioAction("Перебивание WhatElse. Другое.");
                    $analytics.setSessionData("Перебивание WhatElse", "Другое");
                }
            }else{
                $analytics.setScenarioAction("Нет перебивания WhatElse");
            }
        }
    });

    bind("postProcess", function($context){
        $.session.lastState = $context.currentState;
        $.session.lastEntryState = $.temp.entryState || $.session.entryState;
        $.session.intent = $.session.intent || {};
        // для этих сценариев в ветке А не логируем обычные стейты, а логируем в самих сценариях через logState
        if(!($.session.clientPathExperiment == "A" && ($.session.intent.currentIntent == "570_Initialization" || $.session.intent.currentIntent == "322_TariffInfo"))){
            $.session.stateLog = $.session.stateLog || [];
            $.session.stateLog.push($.session.lastEntryState);
        }
        $dialer.setNoInputTimeout(5000);
    });
    
    bind("onScriptError", function($context) {
        if($context.testContext || $.request.channelType == 'chatwidget'){
            $reactions.answer($context.exception.message);
        }
        customLog('ScriptError: ' + $context.exception.message);
        $analytics.setSessionData('Script error', $context.exception.message);
        $analytics.setScenarioAction('Script error');
        $reactions.transition('/Transfer/TransferOnError');
    }, "/", "Handle script errors");
    
    bind("onDialogError", function($context) {
        if($context.testContext || $.request.channelType == 'chatwidget'){
            $reactions.answer($context.exception.message);
        }
        customLog('DialogError: ' + $context.exception.message);
        $analytics.setSessionData('Dialog error', $context.exception.message);
        $analytics.setScenarioAction('Dialog error');
        $reactions.transition('/Transfer/TransferOnError');
    }, "/", "Handle dialog errors");
    
    bind("onAnyError", function($context) {
        if($context.testContext || $.request.channelType == 'chatwidget'){
            $reactions.answer($context.exception.message);
        }
        customLog('UnhandledError: '+ $context.exception.message);
        $analytics.setSessionData('Unhandled error', $context.exception.message);
        $analytics.setScenarioAction('Unhandled error');
        $analytics.setScenarioAction("Перевод по ошибке");
        // записываем экспешн как метрику
        $analytics.setScenarioAction($context.exception.message);
        if($.session.queryCnt == 1) $analytics.setScenarioAction("Ошибка на стейте Start");
        
        // если до этого уже пытались перевести по ошибке, но опять возникла ошибка, то сразу переводим по дефолтному коллеру
        if($.session.transferErrorFlag){
            transferByCallerInput($.injector.defaultCallerInput);
        }else{
            $.session.transferErrorFlag = true;
            announceAudio(audioDict.perevod_na_okc);
            if($.session.intent){
                $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                $.session.intent.resultCode = $global.BOT_ERROR_EXIT_CODE;
            }else{
                startIntent("6_NoIntent");
                $.session.intent.resultCode = $global.BOT_ERROR_EXIT_CODE;
                $.session.callerInput = $.injector.defaultCallerInput;
            }
            stopIntent();
            $.session.callerInput = $.session.callerInput || $.injector.defaultCallerInput;
            $.session.callEndType = 'transfer';
            if($.testContext || $.request.channelType == 'chatwidget'){
                $reactions.answer("Перевод по коллеру {{$.session.callerInput}}");
            }
            
            try{
                sendDialogLog();
            }catch(e){
                customLog("Failed to send dialog to BI. Error: " + e.message);
            }
            
            $analytics.setSessionData("Call end type", $.session.callEndType);
            transferByCallerInput($.session.callerInput);
        }
    }, "/", "Handle any errors ");
    
    bind("selectNLUResult", function($context) {
        customLog("NLU results: " + toPrettyString($context.nluResults));
        /*
        смотрим на фразу, и:
        1. если нет+интент = уходим в интент
        2. если нет+оператор = уходим в оператора
        3. если нет+ноу матч = уходим в ноу матч Пжлст задайте вопрос другими словами
        4. если нет без ничего = прощаемся /ToExit
        
        1. если да+интент = уходим в интент
        2. если да+оператор = уходим в оператора
        3. если да+ноу матч = уходим в ноу матч Пжлст задайте вопрос другими словами
        4. если да без ничего = какой вопрос?
        
        если что-то попадает в паттерны, то это да
        
        q: ($yes / ад / lf / даа / $yes или $no $yes) *
        q: * $yesQuestions *
        q: * $anotherQuestion *
        
        если что-то попадает в паттерны, то это нет
        
        q: ($no / нт / ytn / нетт / нету / $yes или $no $no / нет все) *
        q: * $noQuestions *
        */
        // получим все результаты от всех классификаторов
        var allResults = _.chain($context.nluResults)
            .omit("selected")
            .values()
            .flatten()
            .value();
        var filteredResults = {};
        if($context.currentState == "/WhatElse/WhatElse"){
            //очищаем все результаты от "Да" и "Нет"
            var resultsWithoutYesNo = allResults.filter(function(result){
                    if(result.ruleType == "pattern"){
                        return !result.pattern.match(/(yesQuestions|yes|noQuestions|no)/gi) && result.hasOwnProperty("clazz");
                    }
                });
            customLog("resultsWithoutYesNo: " + toPrettyString(resultsWithoutYesNo));
            if(resultsWithoutYesNo){
                //очищаем все результаты от оператора
                var resultsWithoutAgent = resultsWithoutYesNo.filter(function(result){
                        if(result.ruleType == "pattern"){
                            return !(result.pattern.match(/agentRequest/gi)) && result.hasOwnProperty("clazz");
                        }
                        if(result.ruleType == "intent" || result.ruleType == "intentGroup"){
                            return !result.debugInfo.intent.path.match(/.405_AgentRequest/gi) && result.hasOwnProperty("clazz");
                        }
                    });
                customLog("resultsWithoutAgent: " + toPrettyString(resultsWithoutAgent));
                if(resultsWithoutAgent){
                    //очищаем все результаты от явных НоуМатчей (intentGroup =/NoMatch)
                    var resultsWithoutNoMatch = resultsWithoutAgent.filter(function(result){
                            if(result.ruleType == "intentGroup"){
                                return !result.debugInfo.intent.path.match(/.NoMatch/gi) && result.hasOwnProperty("clazz");
                            }
                        });
                    customLog("resultsWithoutNoMatch: " + toPrettyString(resultsWithoutNoMatch));
                    filteredResults = resultsWithoutNoMatch || resultsWithoutAgent;
                }else{
                    filteredResults = resultsWithoutYesNo;
                }
            }
            
        }else{
            //очищаем все результаты от явных НоуМатчей (intentGroup =/NoMatch)
            var resultsWithoutNoMatch = resultsWithoutAgent.filter(function(result){
                    if(result.ruleType == "intentGroup"){
                        return !result.debugInfo.intent.path.match(/.NoMatch/gi) && result.hasOwnProperty("clazz");
                    }
                });
            customLog("resultsWithoutNoMatch: " + toPrettyString(resultsWithoutNoMatch));
            
            if(resultsWithoutNoMatch){
                //очищаем все результаты от оператора и приветствия
                var noAgentRequestResults = allResults.filter(function(result){
                        if(result.ruleType == "pattern"){
                            return !(result.pattern.match(/(agentRequest|hello)/gi)) && result.hasOwnProperty("clazz");
                        }
                        if(result.ruleType == "intent" || result.ruleType == "intentGroup"){
                            return !result.debugInfo.intent.path.match(/.405_AgentRequest/gi) && result.hasOwnProperty("clazz");
                        }
                    });
                filteredResults = noAgentRequestResults || resultsWithoutNoMatch;
            }
        }
        customLog("Filtered NLU results: " + toPrettyString(filteredResults));
        
        // ищем результат с максимальным score среди отфильтрованных результатов
        var selected;
        var maxScore = _.chain(filteredResults)
            .pluck("score")
            .max()
            .value();
        
        selected = _.findWhere(filteredResults, {
            score: maxScore
        });
        
        customLog("New NLU result selection: " + toPrettyString(selected));
        // если получили новый результат, перезаписываем прошлый
        if(selected) $context.nluResults.selected = selected;
    });
    
theme: /

    state: Start
        q!: $regex</start>
        script:
            $analytics.setSessionData("version", $injector.version || "");
            //TODO для тестов
            if($.request.channelType == 'chatwidget' && !$.session.phoneNumber){
                $reactions.transition("/GetPhoneNumber");
            }else{
                if($.request.channelType == 'chatwidget'){
                    $reactions.answer("ID диалога: " + $.sessionId);
                }
                $context.testContext = $context.testContext || false;
                parseResteriskInputParams();
                init();
                $.session.cityData = getCityByRegion($.session.region, citiesByRegion, citiesData);
                authByPhoneNumber();
            }
        if: $.session.userType === "undefined-guest"
            go!: /Init/HowCanIHelpYou
        elseif: $.session.userType === "user"
            go!: /Init/DIALOG_WELCOME_EVENT_1
        else:
            if: $.session.authErrorCode === 9
                script:
                    $.session.houseAuthToken = $session.token;
                go!: /Init/InitMSHi
            else:
                script:
                    $.session.addressAuthToken = $session.token;
                go!: /Init/Initialization
            
    state: HangUp
        event!: hangup
        # для теста
        q!: hangup
        script:
            $.session.callEndType = 'far-hup';
            stopIntent();
            $analytics.setSessionData("Call end type", $.session.callEndType);
            $analytics.setSessionData("Hang up", "client");
            try{
                sendDialogLog();
            }catch(e){
                customLog("Failed to send dialog to BI. Error: " + e.message);
            }
            $.session.dialogLogIsSent = $.session.dialogLogIsSent || false;
            if(!$.session.dialogLogIsSent) $analytics.setScenarioAction("Не отправлено в BI");
            if($.session.queryCnt == 1) $analytics.setScenarioAction("В диалоге только start");
    
    state: BotHangUp
        event!: botHangup
        script:
            $analytics.setSessionData("Hang up", "bot");
            // переведем как по ошибке
            $reactions.transition('/Transfer/TransferOnError');
            
    state: SessionLimit
        event!: sessionDataSoftLimitExceeded
        script:
            $.session.intent.resultCode = 42;
            stopIntent();
            $analytics.setScenarioAction('Session soft limit');
            logDialogError($.session.lastEntryState, "sessionDataSoftLimitExceeded");
        go!: /Transfer/TransferOnError
    
    state: TimeLimit
        event!: timeLimit
        script:
            $analytics.setScenarioAction("Time limit");
            // переведем как по ошибке
            $reactions.transition('/Transfer/TransferOnError');
    
    state: GetPhoneNumber
        a: Введите номер телефона в формате 7**********.
        
        state: CheckNumber
            q: @duckling.phone-number
            script:
                var p = $parseTree.text;
                if(p.match(/7[0-9]{10}/)){
                    $.session.phoneNumber = p;
                    $reactions.transition("/Start");
                }else{
                    $reactions.answer("Неверный номер телефона!");
                    $reactions.transition("/GetPhoneNumber");
                }
                
    # стейт для теста NLU
    state: DummyState
        a: Пустота
    
    # стейт для прогона тестов с поиском адреса
    #state: AddressTest
    #    q!: addressTest
    #    script:
    #        $.session.region = "dev-dcc-spb";
    #        $.session.phoneNumber = "79000000000";
    #        $.session.cityData = getCityByRegion($.session.region, citiesByRegion, citiesData);
    #        $.session.msg = "";
    #    a: адрес
    #    
    #    state: AuthByAddress
    #        q: * ($Address/$IncompleteAddress/$NonExplicitStreet) *
    #        script:
    #            $.session.msg = $request.query + ";";
    #            $.session.address = $parseTree._Address || $parseTree._IncompleteAddress || $parseTree._NonExplicitStreet;
    #            if (!$parseTree._Address) $.session.isIncompleteAddress = true;
    #            $.session.msg += $.session.address.street + " дом " + $.session.address.house + " корпус " + $.session.address.housing + ";";
    #            authByAddress();
    #        if: $.session.userType !== "guest-with-house"
    #            script:
    #                $.session.msg += "дом не найден";
    #        else:
    #            scriptEs6:
    #                var j;
    #                try {
    #                    j = JSON.parse(atob($context.session.token.split('.')[1]));
    #                    $context.session.msg += j.user.houseId;
    #                }catch(e){
    #                    $context.session.msg += "failed to parse token";
    #                }
    #        a: {{$.session.msg}}
    #    
    #    state: NoMatch
    #        event: noMatch
    #        script:
    #            $.session.msg += $request.query;
    #        a: адрес не распознан