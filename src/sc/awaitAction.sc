# выполнение await-запросов
# получаем по запросу id в очереди. Далее проверяем результат по id
# параметры для await-экшна $.session.awaitAction
# $.session.awaitAction.returnState - стейт для возврата после await экшна
# $.session.awaitAction.action - экшн
# [$.session.awaitAction.readTimeout] - таймаут получения результат по id в очереди (мс). То есть через такое количество времени будем пытаться получить данные
# [$.session.awaitAction.audio] - аудио для ожидания ответа из справочника audioDict (в виде audioDict.NoConnection);

theme: /AwaitAction
    
    state: RunAction || modal = true
        script:
            if($.session.awaitAction){
                $.session.awaitAction.status = true; // сразу проставляем статус, чтобы случайно не зациклиться и отработать awaitAction только один раз
                $.session.awaitAction.attempts = $.session.awaitAction.attempts || 0;
                $.session.awaitAction.attempts++;
                switch($.session.awaitAction.action){
                    case "checkSession":
                        checkSession();
                        break;
                    case "getAvaialbeServiceRequestTimeSlots":
                        getAvaialbeServiceRequestTimeSlots();
                        break;
                    case "createServiceRequest":
                        createServiceRequest();
                        break;
                    case "getSpasData":
                        getSpasData();
                        break;
                    case "getBalance":
                        getBalance();
                        break;
                    case "checkPromisedPayment":
                        checkPromisedPayment();
                        break;
                    case "checkRouter":
                        checkRouter();
                        break;
                    case "checkSwitchAccident":
                        checkSwitchAccident();
                        break;
                    case "checkServices":
                        checkServices();
                        break;
                    case "getAvaialbeServiceRequestDays":
                        getAvaialbeServiceRequestDays();
                        break;
                    case "checkOptom":
                        checkOptom();
                        break;
                    default:
                        throw new Error("Unknown await action.");
                }
            }else{
                throw new Error("Await action parameters are not defined.");
            }
            if($.session.waitResponse){
                if($.testContext){
                    $reactions.transition("./CheckStatus");
                }else{
                    if($.session.awaitAction.attempts > 4){
                        customLog("Await request attempts limit reached.");
                        delete $.session.awaitRequestId;
                        $.session.waitResponse = false;
                        $reactions.transition($.session.awaitAction.returnState);
                    }else{
                        var interval = '1s';
                        if($context.testContext || $.request.channelType == 'chatwidget'){
                            interval = '15s'; // для виджета выставляем таймаут подольше, так как аудио не воспроизводятся, а сразу выводятся текстом
                        }
                        announceAudio($.session.awaitAction.audio || audioDict.initialWait);
                        $reactions.timeout({interval: interval, targetState: './CheckStatus'});
                    }
                }
            }else{
                delete $.session.awaitRequestId;
                $reactions.transition($.session.awaitAction.returnState);
            }
        
        state: CheckStatus
            event: speechNotRecognized
            event: noMatch
            go!: ..
