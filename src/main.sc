require: slotfilling/slotFilling.sc
  module = sys.zb-common
require: common.js
    module = sys.zb-common
require: dateTime/dateTime.sc
  module = sys.zb-common
require: Scripts/globals.js
    type = scriptEs6
    name = globals

# словари
require: Dicts/intents.yaml
    var = intentDict
require: Dicts/Cities/citiesByRegion.yaml
    var = citiesByRegion
require: Dicts/Cities/citiesData.yaml
    var = citiesData
require: Dicts/Cities/billingAlias.yaml
    var = billingAlias

require: require.sc

init:
    bind("preProcess", function($context){
        $.session.entryState = $context.currentState;
        $.session.stateLog = $.session.stateLog || [];
        $.session.stateLog.push($.session.entryState);
    });
    
    bind("postProcess", function($context){
        $.session.lastState = $context.currentState;
        $.session.lastEntryState = $.session.entryState;
        if($context.currentState != '/Timeout' && $context.currentState != '/StopSession' &&
           $context.currentState != "/Transfer/Transfer" && $context.currentState != "/Прощание" &&
           !$context.testContext){
            // отправляем в таймаут-стейт только один раз, чтобы не попасть в бесконечный цикл
            var timeout = $.session.timeout || $.injector.reactionTimeout;
            delete $.session.timeout;
            $reactions.timeout({interval: timeout, targetState: '/Timeout'});
        }
        $analytics.setSessionData('Last reply', $.response.answer || "");
    });
    
    bind("onScriptError", function($context) {
        if($context.testContext || $.request.channelType == 'chatwidget'){
            $reactions.answer($context.exception.message);
        }
        customLog('Script error: ' + $context.exception.message);
        $analytics.setSessionData('ScriptError', $context.exception.message);
        $reactions.transition('/Transfer/TransferOnError');
    }, "/", "Handle script errors");
    
    bind("onDialogError", function($context) {
        if($context.testContext || $.request.channelType == 'chatwidget'){
            $reactions.answer($context.exception.message);
        }
        customLog('Dialog error: ' + $context.exception.message);
        $analytics.setSessionData('DialogError', $context.exception.message);
        $reactions.transition('/Transfer/TransferOnError');
    }, "/", "Handle dialog errors");
    
    bind("onAnyError", function($context) {
        if($context.testContext || $.request.channelType == 'chatwidget'){
            $reactions.answer($context.exception.message);
        }
        customLog('Unhandled error: '+ $context.exception.message);
        $analytics.setSessionData('UnhandledError', $context.exception.message);
        if($.session.intent){
            $.session.intent.resultCode = $.session.intent.resultCode || 41;
        }
        // перевод сразу отсюда, так как transition не работает из onAnyError
        $.session.transferAnswer = $.session.transferAnswer || "Я позову на помощь коллегу, он сейчас ответит.";
        stopIntent(); // завершаем инетнет, если вдруг не завершили в сценарии
        checkTransferTime();
        if($.session.transferTime){
            $.session.chatEndType = 'transfer';
            sendDialogLog();
            $.session.transferAnswer = $.session.transferAnswer || $.session.transferAnswer || "Я позову на помощь коллегу, он сейчас ответит.";
            $reactions.answer($.session.transferAnswer);
            $.session.transferLine = $.session.transferLine || $.injector.defaultTransferLine;
            customLog("Transfer to: " + $.session.transferLine);
            $.response.replies = $.response.replies || [];
            if(!$context.textContext){
                $.response.replies.push({
                    type: "switch",
                    firstMessage: $jsapi.chatHistory(), // Оператор получит историю переписки пользователя с ботом.
                    lastMessage: "Диалог с оператором завершен",
                    closeChatPhrases: ["/closeChat", "Закрыть диалог"],
                    appendCloseChatButton: false,
                    theme: "/",
                    ignoreOffline: true,
                    sendMessagesToOperator: false,
                    sendMessageHistoryAmount: 5,
                    hiddenAttributes: {},
                    oneTimeMessage: false,
                    destination: $.session.transferLine, // Группа операторов, на которую будет переведен диалог.
                    attributes: { "targetLine": $.session.transferLine },
                    targetLine: $.session.transferLine
                });
            }
            $jsapi.stopSession();
        }else{
            $.session.chatEndType = 'near-hup';
            sendDialogLog();
            $reactions.answer("Похоже, что для решения вопроса будут нужны живые люди. Ночью с 01:00 до 5:00 (МСК) все операторы спят. Напишите нам после 5 утра, мы обязательно поможем )");
            $jsapi.stopSession();
        }
    }, "/", "Handle any errors");
    
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
            if($.request.channelType == 'chatwidget'){
                $jsapi.startSession();
            }
            $context.testContext = $context.testContext || false;
            $.session.transferLine = $injector.defaultTransferLine;
            $.session.chatEndType = 'near-hup'; // по-умолчанию считаем, что диалог успешно завершится в боте
            getGenesysData();
            authByAgreementId();
            if($.session.userType == 'user'){
                getBaseInfo();
            }
        a: Привет! Я Робот-помощник Дом.ру.
    
        state: Agent
            q: $agentRequest
            intent: /405AgentRequest/Новый интент
            go!: /AgentRequest/AgentRequestFromStart
            
        state: Greeting
            q: $helloAdvanced
            go!: /Greeting/FirstIntent

    state: NoMatch
        q: $noMatch
        intentGroup: /NoMatch
        event: noMatch
        event: lengthLimit
        script:
            // пробуем поменять раскладку
            var text = $parseTree.text;
            $temp.fixedText = $nlp.fixKeyboardLayout(text);
            if($temp.fixedText){
                customLog("Initital nomatched text '" + text + "' has been fixed to '" + $temp.fixedText + "'.");
                var matchResults = $nlp.match($temp.fixedText, "/") || {};
                customLog("NLP match results: " + toPrettyString(matchResults));
                if(matchResults.targetState && matchResults.targetState != "/NoMatch"){
                    $parseTree = matchResults.parseTree;
                    $temp.nextState = matchResults.targetState;
                    $reactions.transition($temp.nextState);
                }else{
                    startIntent('NoMatch');
                }
            }else{
                startIntent('NoMatch');
            }
        a: Я вас не понял :( Дайте мне шанс помочь вам, переформулируйте вопрос, пожалуйста.
        buttons:
            "Не работает интернет" -> /PageNotOpenLowSpeedNoLinkPPoE/NoLink
            "Баланс и детализация" -> /BalanceInquiry/CheckAuth
            "Низкая скорость" -> /PageNotOpenLowSpeedNoLinkPPoE/NoLink
            "Проблемы с ТВ" -> /TVChannelTVqualityProblem/TVchannelProblem
            "Консультация по тарифу" -> /OffersHelpIntent/Консультация по тарифным планам
            "Подключить услугу" -> /ManageServicePacket/Управление контентом
        event: noMatch || toState = "/NoMatch/Transfer"
        
        state: Transfer
            a: Диалог принял неожиданный для меня поворот.
            go!: /Transfer/Перевод чата
            
    state: Еще вопросы?
        q!: ( $thanks / {помог* $thanks} / {работ* $thanks} ) 
        script:
            stopIntent(); // завершаем интент, если вдруг не завершили в сценарии
        a: Могу ещё чем-нибудь вам помочь?
        buttons:
            "Да" -> /Какие вопросы?
            "Нет" -> /Прощание
        q: $yesQuestions * || toState = "/Какие вопросы?"
        intent: /Yes || toState = "/Какие вопросы?"
        q: $noQuestions * || toState = "/Прощание"
        intent: /No || toState = "/Прощание"
        q: $noMatch || toState = "/NoMatch"
        
    state: Какие вопросы?
        a: Какой вопрос вы хотите решить?
        buttons:
            "Не работает интернет" -> /PageNotOpenLowSpeedNoLinkPPoE/NoLink
            "Баланс и детализация" -> /BalanceInquiry/CheckAuth
            "Низкая скорость" -> /PageNotOpenLowSpeedNoLinkPPoE/NoLink
            "Проблемы с ТВ" -> /TVChannelTVqualityProblem/TVchannelProblem
            "Консультация по тарифу" -> /OffersHelpIntent/Консультация по тарифным планам
            "Подключить услугу" -> /ManageServicePacket/Управление контентом

    state: Прощание || sessionResult = "Прощание", sessionResultColor = "#7524AA"
        q!: $byeAdvanced
        a: Рад вам помочь! Если у вас появятся вопросы, пишите в любое удобное время. Рад помочь. Пишите в любое время. Персонально от себя рекомендую мобильное приложение «Мой Дом.ру»:
                в нём можно управлять услугами, получать информацию о начислениях и спецпредложениях, а также писать мне в онлайн-чат. 
                На сайте доступны <a href="https://dom.ru/service/agent">ссылки</a> для скачивания версии для iOS и Android. Удачного дня!
        script:
            stopIntent();
            if($.session.dialogLog){
                sendDialogLog();
            }
            if(!$context.testContext){
                $reactions.timeout({interval: '5 seconds', targetState: '/StopSession'});
            }
            
    state: FileEvent
        event: fileEvent
        event: fileTooBigEvent
        a: Напишите свой вопрос, и я постараюсь вам помочь.
        buttons:
            "Не работает интернет" -> /PageNotOpenLowSpeedNoLinkPPoE/NoLink
            "Баланс и детализация" -> /BalanceInquiry/CheckAuth
            "Низкая скорость" -> /PageNotOpenLowSpeedNoLinkPPoE/NoLink
            "Проблемы с ТВ" -> /TVChannelTVqualityProblem/TVchannelProblem
            "Консультация по тарифу" -> /OffersHelpIntent/Консультация по тарифным планам
            "Подключить услугу" -> /ManageServicePacket/Управление контентом
        event: noMatch || toState = "/NoMatch"
    
    state: SessionLimit
        event: sessionDataSoftLimitExceeded
        script:
            $.session.intent.resultCode = 42;
            stopIntent();
            logDialogError($.session.lastEntryState, "sessionDataSoftLimitExceeded");
        go!: /Transfer/TransferOnError

    state: Timeout || sessionResult = "Завершение сессии по таймауту"
        script:
            $.session.chatEndType = 'timeout';
            customLog("Chat terminated by timeout");
            if($.request.channelType == 'chatwidget'){
                $reactions.answer("Чат завершен по таймауту.");
            }
        go!: /StopSession
        
    state: ClientLeft || sessionResult = "Клиент закрыл чат"
        # завершение чата со стороны клиента
        event!: ClientLeftEvent
        script:
            $.session.chatEndType = 'far-hup';
        go!: /StopSession
    
    state: LengthLimit || noContext = true
        event!: lengthLimit
        script:
            $.session.intent.stepsCnt++;
            if(countRepeatsInRow() < $injector.noMatchLimit) {
                $reactions.transition($.session.lastState);
            }else{
                $.session.intent.resultCode = 6;
                $reactions.transition('/Transfer/Перевод чата');
            }
        
    state: StopSession
        a:
        script:
            stopIntent();
            if($.session.dialogLog){
                sendDialogLog();
            }
            $jsapi.stopSession();

    state: Test
        q!: test
        a: test
        scriptEs6:
            auth.authByAgreementId();
        a: {{$.session.token}}