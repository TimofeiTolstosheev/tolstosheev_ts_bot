# $.session.transferAnswer может быть задан в сценарии. Если не задан, то определяем по линии перевода

theme: /Transfer
    state: Перевод чата || sessionResult = "Перевод", sessionResultColor = "#7524AA"
        script:
            if($.session.intent){
                $.session.intent.resultCode = $.session.intent.resultCode || 6;
            }
            $.session.transferAnswer = $.session.transferAnswer || "Я позову на помощь коллегу, он сейчас ответит.";
        go!: /Transfer/Transfer

    state: Перевод чата NUA || sessionResult = "Перевод NUA", sessionResultColor = "#7524AA"
        script:
            if($.session.intent){
                $.session.intent.resultCode = $.session.intent.resultCode || 8;
            }
            $.session.transferAnswer = $.session.transferAnswer || "Я позову на помощь старшего специалиста, сейчас он подключится.";
            $.session.transferLine = "NUA";
        go!: /Transfer/Transfer

    state: Перевод чата OCP || sessionResult = "Перевод OCP", sessionResultColor = "#7524AA"
        script:
            if($.session.intent){
                $.session.intent.resultCode = $.session.intent.resultCode || 12;
            }
            $.session.transferAnswer = $.session.transferAnswer || "Я позову на помощь специалиста отдела продаж. Один момент, он сейчас ответит.";
            $.session.transferLine = "OCP";
        go!: /Transfer/Transfer

    state: Перевод чата ОЦТП || sessionResult = "Перевод ОЦТП", sessionResultColor = "#7524AA"
        script:
            if($.session.intent){
                $.session.intent.resultCode = $.session.intent.resultCode || 7;
            }
            $.session.transferAnswer = $.session.transferAnswer || "Я позову на помощь коллегу, он сейчас ответит.";
            $.session.transferLine = "OCTP";
        go!: /Transfer/Transfer
        
    state: IntentLimitTransfer || sessionResult = "Перевод. Первышено ограничение на повторный интент"
        script:
            if($.session.intent){
                $.session.intent.resultCode = $.session.intent.resultCode || 28;
            }
            $.session.transferAnswer = $.session.transferAnswer || "Я позову на помощь коллегу, он сейчас ответит.";
        go!: /Transfer/Transfer

    state: TransferOnError || sessionResult = "Перевод. Возникла ошибка"
        if: $context.testContext    
            a: Перевод по ошибке
        script:
            if($.session.intent){
                $.session.intent.resultCode = $.session.intent.resultCode || 41;
            }
            $.session.transferAnswer = $.session.transferAnswer || "Я позову на помощь коллегу, он сейчас ответит.";
        go!: /Transfer/Transfer
        
    state: Transfer
        script:
            stopIntent(); // завершаем инетнет, если вдруг не завершили в сценарии
            checkTransferTime();
            if($.session.transferTime){
                $.session.chatEndType = 'transfer';
                sendDialogLog();
                $.session.transferAnswer = $.session.transferAnswer || $.session.transferAnswer || "Я позову на помощь коллегу, он сейчас ответит.";
                $reactions.answer($.session.transferAnswer);
                $.session.transferLine = $.session.transferLine || $.injector.defaultTransferLine;
                customLog("Transfer to: " + $.session.transferLine);
                $response.replies = $response.replies || [];
                if(!$context.textContext){
                    $response.replies.push({
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
                $.session.timeout = '10 seconds';
            }
