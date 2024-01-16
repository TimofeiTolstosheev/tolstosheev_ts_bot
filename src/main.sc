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

require: require.sc

init:
    bind("preProcess", function($context){
        $.session.entryState = $context.currentState;
    });

    bind("postProcess", function($context){
        $.session.lastState = $context.currentState;
        $.session.lastEntryState = $.temp.entryState || $.session.entryState;
        $.session.stateLog = $.session.stateLog || [];
        $.session.stateLog.push($.session.lastEntryState);
        $dialer.setNoInputTimeout(5000);
    });
    
    bind("onScriptError", function($context) {
        if($context.testContext || $.request.channelType == 'chatwidget'){
            $reactions.answer($context.exception.message);
        }
        customLog('ScriptError: ' + $context.exception.message);
        $analytics.setSessionData('Script error', $context.exception.message);
        $reactions.transition('/Transfer/TransferOnError');
    }, "/", "Handle script errors");
    
    bind("onDialogError", function($context) {
        if($context.testContext || $.request.channelType == 'chatwidget'){
            $reactions.answer($context.exception.message);
        }
        customLog('DialogError: ' + $context.exception.message);
        $analytics.setSessionData('Dialog error', $context.exception.message);
        $reactions.transition('/Transfer/TransferOnError');
    }, "/", "Handle dialog errors");
    
    bind("onAnyError", function($context) {
        if($context.testContext || $.request.channelType == 'chatwidget'){
            $reactions.answer($context.exception.message);
        }
        customLog('UnhandledError: '+ $context.exception.message);
        $analytics.setSessionData('Unhandled error', $context.exception.message);
        
        // если до этого уже пытались перевести по ошибке, но опять возникла ошибка, то сразу переводим по дефолтному коллеру
        if($.session.transferErrorFlag){
            transferByCallerInput($.injector.defaultCallerInput);
        }else{
            $.session.transferErrorFlag = true;
            announceAudio(audioDict.perevod_na_okc);
            if($.session.intent){
                $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                $.session.intent.resultCode = 41;
            }else{
                startIntent('0_NoMatch');
                $.session.intent.resultCode = 41;
                $.session.callerInput = $.injector.defaultCallerInput;
                stopIntent();
            }
            stopIntent();
            $.session.callerInput = $.session.callerInput || $.injector.defaultCallerInput;
            $.session.callEndType = 'transfer';
            if($.testContext || $.request.channelType == 'chatwidget'){
                $reactions.answer("Перевод по коллеру {{$.session.callerInput}}");
            }
            if($.session.dialogLog){
                try{
                    sendDialogLog();
                }catch(e){
                    customLog("Failed to send dialog to BI. Error: " + e.message);
                }
            }
            $analytics.setSessionData("Call end type", $.session.callEndType);
            transferByCallerInput($.session.callerInput);
        }
    }, "/", "Handle any errors ");
    
    bind("selectNLUResult", function($context) {
        customLog("NLU results: " + toPrettyString($context.nluResults));
        // получим все результаты от всех классификаторов
        var allResults = _.chain($context.nluResults)
            .omit("selected")
            .values()
            .flatten()
            .value();
        
        //очищаем все результаты от оператора
        var noAgentRequestResults = allResults.filter(function(result){
                if(result.ruleType == "pattern"){
                    return !result.pattern.match(/agentRequest/gi) && result.hasOwnProperty("clazz");
                }
                if(result.ruleType == "intent" || result.ruleType == "intentGroup"){
                    return !result.debugInfo.intent.path.match(/.405_AgentRequest/gi) && result.hasOwnProperty("clazz");
                }
            });
        customLog("Filtered NLU results: " + toPrettyString(noAgentRequestResults));
        
        // ищем результат с максимальным score среди отфильтрованных результатов
        var selected;
        var maxScore = _.chain(noAgentRequestResults)
            .pluck("score")
            .max()
            .value();
        
        selected = _.findWhere(noAgentRequestResults, {
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
            go!: {{ HOW_CAN_HELP_STATE }}
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
            if($.session.dialogLog){
                try{
                    sendDialogLog();
                }catch(e){
                    customLog("Failed to send dialog to BI. Error: " + e.message);
                }
            }
    
    state: BotHangUp
        event: botHangup
        script:
            $analytics.setSessionData("Hang up", "bot");
            // переведем как по ошибке
            $reactions.transition('/Transfer/TransferOnError');
            
    state: SessionLimit
        event: sessionDataSoftLimitExceeded
        script:
            $.session.intent.resultCode = 42;
            stopIntent();
            logDialogError($.session.lastEntryState, "sessionDataSoftLimitExceeded");
        go!: /Transfer/TransferOnError
    
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
                    $reactions.answer("Неверный номер телефона");
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
    