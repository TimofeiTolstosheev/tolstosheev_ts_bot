init:
    function interceptGlobalMatch(targetState, onState) {
        // в стейте onState отлавливаем match в глобальную тематику
        // и форсированно переходим в стейт targetState
        return bind("selectNLUResult", function() {
            if ($.request.query !== "/start"
                && !$.nluResults.selected.clazz.startsWith(onState)) {
                $.temp.matchTargetState = $.nluResults.selected.clazz;
                // если указан относительный путь, склеиваем onState и targetState
                $.temp.targetState = targetState.startsWith("/")
                    ? targetState
                    : onState + "/" + targetState;
            }
        }, onState);
    }

    _.each({
        "/CreateServiceRequest/TSrequest/Ask": "ThemeMatch",
        "/CreateServiceRequest/NetworkAdminRequest/Ask": "ThemeMatch"
    }, interceptGlobalMatch);
    
theme: /CreateServiceRequest
    state: checkSpasData
        # TODO возможно стейт не понадобится
        script:
            $.session.productId = $.session.productId || 5; // если не знаем продукт, то по-умолчанию используем 5 - Интернет
            if($.session.productId == 5){
                $.session.serviceRequestComment = $.session.spas.internetProblem.requestComment;
                if($.session.spas.internetProblem.technicalServiceRequest){
                    // заявка на техников сервиса
                    $reactions.transition("/CreateServiceRequest/TSrequest");
                }else{
                    if($.session.spas.internetProblem.networkAdministrationRequest){
                        // заявка на техников сервиса
                        $reactions.transition("/CreateServiceRequest/NetworkAdminRequest");
                    }
                }
            }
            if($.session.productId == 53){
                $.session.serviceRequestComment = $.session.spas.tvProblem.requestComment;
                if($.session.spas.tvProblem.technicalServiceRequest){
                    // заявка на техников сервиса
                    $reactions.transition("/CreateServiceRequest/TSrequest");
                }else{
                    if($.session.spas.tvProblem.networkAdministrationRequest){
                        // заявка на техников сервиса
                        $reactions.transition("/CreateServiceRequest/NetworkAdminRequest");
                    }
                }
            }else{
                customLog('Unknown product');
                $.session.transition($.session.returnState);// возвращаемся в сценарий, откуда пришли
            }
    
    state: TSrequest
        script:
            announceAudio(audioDict.SPAS_TS_request);
            $reactions.transition("/CreateServiceRequest/TSrequest/Ask");
            
        state: Ask || modal = true
            script:
                announceAudio(audioDict.doYouWant_TS_request);

            state: ThemeMatch || noContext = true
                # перехватываем match в глобальную тематику при помощи interceptGlobalMatch в init-блоке
                script:
                    countRepeatsInRow();
                if: $session.repeatsInRow < 2
                    script:
                        announceAudio(audioDict.doYouWant_TS_request_ThemeMatch);
                        $.session.themeMatched = true;
                else:
                    go!: {{ $temp.matchTargetState }}
        
            state: GetAvailableDays
                q: * $commonYes *
                q: * какой день *
                q: * готов* *
                q: * (создай*/создавай*) *
                script:
                    // параметры для await-экшна
                    $.session.awaitAction = $.session.awaitAction || {};
                    $.session.awaitAction.returnState = $context.currentState;
                    $.session.awaitAction.action = "getAvaialbeServiceRequestDays";
                    $.session.awaitAction.readTimeout = 2000;
                    if($.session.awaitAction.status){
                        delete $.session.awaitAction;
                        $.session.intent.stepsCnt++;
                        $reactions.transition("/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/AskDay");
                    }else{
                        $reactions.transition("/AwaitAction/RunAction");
                    }
                    
                state: AskDay || modal = true
                    script:
                        if(!$.session.today && !$.session.tomorrow && !$.session.afterTomorrow){
                            $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            $.session.intent.resultCode = 1;
                            announceAudio(audioDict.SZNoTime);
                            $reactions.transition("/Transfer/CheckOCTP");
                        }else{
                            announceAudio(audioDict.SZProverilaDey);
                            announceAudio(audioDict.SZDostypno);
                            if($.session.today){
                                announceAudio(audioDict.today);
                            }
                            if($.session.tomorrow){
                                announceAudio(audioDict.tomorrow);
                            }
                            if($.session.afterTomorrow){
                                announceAudio(audioDict.after_tomorrow);
                            }
                            announceAudio(audioDict.SZDostypno2);
                        }
                    
                    state: Today || noContext = true
                        q: * сегодня *
                        script:
                            if($.session.today){
                                $.session.serviceRequestDay = 'today';
                                $.session.serviceRequestDate = new Date($.session.billingCurrentDate);
                                $reactions.transition("/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/GetAvailableTimeSlots");
                            }else{
                                if(countRepeatsInRow() < $injector.noMatchLimit){
                                    announceAudio(audioDict.SZDostypno_outOfRangeDate);
                                    $reactions.transition("/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/AskDay");
                                }else{
                                    announceAudio(audioDict.callbackInThreeDays);
                                    $reactions.transition("/WhatElse/WhatElse");
                                }
                            }
                        
                    state: Tomorrow || noContext = true
                        q: * завтра *
                        script:
                            if($.session.tomorrow){
                                $.session.serviceRequestDay = 'tomorrow';
                                // добавим один день
                                $.session.serviceRequestDate = new Date($.session.billingCurrentDate);
                                $.session.serviceRequestDate.setDate($.session.serviceRequestDate.getDate() + 1);
                                $reactions.transition("/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/GetAvailableTimeSlots");
                            }else{
                                if(countRepeatsInRow() < $injector.noMatchLimit){
                                    announceAudio(audioDict.SZDostypno_outOfRangeDate);
                                    $reactions.transition("/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/AskDay");
                                }else{
                                    announceAudio(audioDict.callbackInThreeDays);
                                    $reactions.transition("/WhatElse/WhatElse");
                                }
                            }
                        
                    state: AfterTomorrow || noContext = true
                        q: * послезавтра *
                        script:
                            $.session.intent.stepsCnt++;
                            if($.session.afterTomorrow){
                                $.session.serviceRequestDay = 'day-after-tomorrow';
                                // добавим два дня
                                $.session.serviceRequestDate = new Date($.session.billingCurrentDate);
                                $.session.serviceRequestDate.setDate($.session.serviceRequestDate.getDate() + 2);
                                $reactions.transition("/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/GetAvailableTimeSlots");
                            }else{
                                if(countRepeatsInRow() < $injector.noMatchLimit){
                                    announceAudio(audioDict.SZDostypno_outOfRangeDate);
                                    $reactions.transition("/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/AskDay");
                                }else{
                                    announceAudio(audioDict.callbackInThreeDays);
                                    $reactions.transition("/WhatElse/WhatElse");
                                }
                            }
                            
                    state: IncorrectDay || noContext = true
                        q: * @duckling.date *
                        q: * @duckling.time *
                        q: * @duckling.time-of-day *
                        event: noMatch
                        script:
                            #TODO сравнивать день недели с актуальным чтобы определить четверг=сегодня
                            # $.session.dayOfWeek = (new Date()).getDay() - 1; //здесь ср=3
                            # $.session.billingCurrentDayOfWeek = (new Date($.session.billingCurrentDate)).getDay(); //здесь чт=3
                            var flag = 0;
                            if($parseTree.text.match(/сегодня/i)){flag = 1;}
                            else if($parseTree.text.match(/завтра/i)){flag = 2;}
                            else if($parseTree.text.match(/послезавтра/i)){flag = 3;}
                            if(flag == 0){
                                $.session.intent.stepsCnt++;
                                if(countRepeatsInRow() < $injector.noMatchLimit) {
                                    announceAudio(audioDict.SZDostypno_timeIssue);
                                    $reactions.transition("/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/AskDay");
                                }else{
                                    $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                                    $.session.intent.resultCode = 6;
                                    announceAudio(audioDict.perezagruzhayVsyo_transferToOKC);
                                    $reactions.transition("/Transfer/CheckOCTP");
                                }
                            }
                            else if(flag == 1){$reactions.transition('/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/AskDay/Today');}
                            else if(flag == 2){$reactions.transition('/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/AskDay/Tomorrow');}
                            else if(flag == 3){$reactions.transition('/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/AskDay/AfterTomorrow');}
                    
                state: GetAvailableTimeSlots
                    script:
                        // параметры для await-экшна
                        $.session.awaitAction = $.session.awaitAction || {};
                        $.session.awaitAction.returnState = $context.currentState;
                        $.session.awaitAction.action = "getAvaialbeServiceRequestTimeSlots";
                        $.session.awaitAction.readTimeout = 4000;
                        $.session.awaitAction.audio = audioDict.initialWait;
                        if($.session.awaitAction.status){
                            delete $.session.awaitAction;
                            if(!$.session.firstHalf && !$.session.secondHalf){
                                $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                                $.session.intent.resultCode = 1;
                                announceAudio(audioDict.NoTime);
                                $reactions.transition("/Transfer/CheckOCTP");
                            }
                            if($.session.firstHalf && $.session.secondHalf){
                                if($.session.firstHalf){
                                    $.session.serviceRequestDayHalf = 1;
                                }
                                if($.session.secondHalf){
                                    $.session.serviceRequestDayHalf = 2;
                                }
                                $reactions.transition("/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/GetAvailableTimeSlots/FirstAndSecond");
                            }else{
                                $reactions.transition("/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/GetAvailableTimeSlots/AskTime");
                            }
                        }else{
                            $reactions.transition("/AwaitAction/RunAction");
                        }
                    
                    state: FirstAndSecond || modal = true
                        script:
                            announceAudio(audioDict.mnogoPolovin);
                        
                        state: First
                            q: * { (перв*/1) [половин*] } *
                            q: * { люб* [половин*/врем*] } *
                            script:
                                $.session.intent.stepsCnt++;
                                $.session.serviceRequestDayHalf = 1;
                                $reactions.transition("/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/GetAvailableTimeSlots/AskTime");
                        
                        state: Second
                            q: * { (втор*/2) [половин*] } * 
                            script:
                                $.session.intent.stepsCnt++;
                                $.session.serviceRequestDayHalf = 2;
                                $reactions.transition("/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/GetAvailableTimeSlots/AskTime");
                            
                        state: CheckTime || noContext = true
                            q: * @duckling.time *
                            q: * @duckling.time-of-day *
                            script:
                                $.session.intent.stepsCnt++;
                                if(countRepeatsInRow() < $injector.noMatchLimit) {
                                    // проверим, есть ли озвученное время в доступных слотах
                                    $temp.requiredTime = $parseTree["_duckling.time"] || $parseTree["_duckling.time-of-day"];
                                    var hour = $temp.requiredTime.hour < 10 ? "0" + $temp.requiredTime.hour : $temp.requiredTime.hour;
                                # ищем 1е совпадение с числом в парстри 
                                    var searchingDigit = /.*?(\d+)/;
                                # преобразуем 23 в 11, если диалог во 2 половине дня
                                    var parseTreeHour = $parseTree.text.match(searchingDigit)[1];
                                    if(parseTreeHour){
                                        if(parseTreeHour.length < 2){
                                            parseTreeHour = "0" + parseTreeHour
                                        };
                                        if(parseTreeHour != hour && $.session.serviceRequestDayHalf == 1){
                                            hour = parseTreeHour
                                        }
                                    }
                                    hour = hour + ":00";
                                    if($.session.timeSlots.indexOf(hour) < 0){
                                        announceAudio(audioDict.mnogoPolovin_outOfRangeHalfDay);
                                        $reactions.transition("/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/GetAvailableTimeSlots/FirstAndSecond");
                                    }else{
                                        $.session.serviceRequestDate = new Date($.session.serviceRequestDate);
                                        hour = hour.replace(":00", "");
                                        $.session.serviceRequestDate.setHours(parseInt(hour));
                                        $.session.serviceRequestDate.setMinutes(0);
                                        $reactions.transition("/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/GetAvailableTimeSlots/ConfirmServiceRequest");
                                    }
                                }else{
                                    announceAudio(audioDict.callbackInThreeDays);
                                    $reactions.transition("/WhatElse/WhatElse");
                                }
                            
                        state: CatchAll || noContext = true
                            event: noMatch
                            script:
                                $.session.intent.stepsCnt++;
                                if(countRepeatsInRow() < $injector.noMatchLimit) {
                                    announceAudio(audioDict.mnogoPolovin_CatchAll);
                                    $reactions.transition("/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/AskDay");
                                }else{
                                    announceAudio(audioDict.callbackInThreeDays);
                                    $reactions.transition("/WhatElse/WhatElse");
                                }
                        
                    state: AskTime || modal = true
                        script:
                            if($.session.timeSlots && $.session.serviceRequestDayHalf){
                                if($.session.timeSlots.length > 0){
                                    announceAudio(audioDict.I_sprosil_u_technica);
                                    for(var i = 0; i < $.session.timeSlots.length; i++){
                                        var t = $.session.timeSlots[i].split(':');
                                        var hour = parseInt(t[0]); // получаем час
                                        if($.session.serviceRequestDayHalf == 1 && hour < 15){
                                            // первая половина
                                            announceTimeFromTimeTo(hour, 0, 0, 0);
                                        }
                                        if($.session.serviceRequestDayHalf == 2 && hour >= 15){
                                            // вторая половина
                                            announceTimeFromTimeTo(hour, 0, 0, 0);
                                        }
                                    }
                                    announceAudio(audioDict.I_sprosil_u_technica1);
                                }
                            }else{
                                announceAudio(audioDict.NoTime);
                                $reactions.transition("/Transfer/CheckOCTP");
                            }
                            
                        state: CheckTime || noContext = true
                            q: * @duckling.time *
                            q: * @duckling.time-of-day *
                            q: в час[ дня]
                            script:
                                $.session.intent.stepsCnt++;
                                // если попали не по ducklink, то считаем, что по фразе "в час" = 13:00
                                // TODO если логика будет усложняться, надо будет переделать
                                $temp.requiredTime = $parseTree["_duckling.time"] || $parseTree["_duckling.time-of-day"] || "13:00";
                                var hour = $temp.requiredTime.hour < 10 ? "0" + $temp.requiredTime.hour : $temp.requiredTime.hour;
                                # ищем 1е совпадение с числом в парстри 
                                var searchingDigit = /.*?(\d+)/;
                                # преобразуем 23 в 11, если диалог во 2 половине дня
                                var parseTreeHour = $parseTree.text.match(searchingDigit)[1];
                                if(parseTreeHour){
                                    if(parseTreeHour.length < 2){
                                        parseTreeHour = "0" + parseTreeHour;
                                    };
                                    if(parseTreeHour != hour && $.session.serviceRequestDayHalf == 1){
                                        hour = parseTreeHour;
                                    }
                                }
                                hour = hour + ":00";
                                // проверим, есть ли озвученное время в доступных слотах
                                if($.session.timeSlots.indexOf(hour) < 0){
                                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                                        announceAudio(audioDict.I_sprosil_u_technica_unavailableTime);
                                        $reactions.transition("/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/GetAvailableTimeSlots/AskTime");
                                    }else{
                                        $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                                        $.session.intent.resultCode = 1;
                                        announceAudio(audioDict.I_sprosil_u_technica_unavailableTime);
                                        $reactions.transition("/Transfer/CheckOCTP");
                                    }
                                }else{
                                # в $.session.serviceRequestDate лежит дата без времени 00:00.000Z, запишем полученные из диалога
                                    $.session.serviceRequestDate = new Date($.session.serviceRequestDate);
                                    hour = hour.replace(":00", "");
                                    $.session.serviceRequestDate.setHours(parseInt(hour));
                                    $.session.serviceRequestDate.setMinutes(0);
                                    $reactions.transition("/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/GetAvailableTimeSlots/ConfirmServiceRequest");
                                }
                            
                        state: CatchAll || noContext = true
                            event: noMatch
                            script:
                                $.session.intent.stepsCnt++;
                                if(countRepeatsInRow() < $injector.noMatchLimit) {
                                    announceAudio(audioDict.I_sprosil_u_technica_CatchAll);
                                    $reactions.transition("/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/GetAvailableTimeSlots/AskTime");
                                }else{
                                    $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                                    $.session.intent.resultCode = 1;
                                    announceAudio(audioDict.YaNeSozdal_noMatchAnswer);
                                    $reactions.transition("/Transfer/CheckOCTP");
                                }
                    
                    state: ConfirmServiceRequest || modal = true
                        script:
                            announceAudio(audioDict.goZayavku1);
                            if($.session.serviceRequestDay == 'today'){
                                announceAudio(audioDict.today);
                            }
                            if($.session.serviceRequestDay == 'tomorrow'){
                                announceAudio(audioDict.tomorrow);
                            }
                            if($.session.serviceRequestDay == 'after-tomorrow'){
                                announceAudio(audioDict.after_tomorrow);
                            }
                            announceDate($.session.serviceRequestDate);
                            announceHoursFromDate($.session.serviceRequestDate);
                            announceAudio(audioDict.goZayavku2);
                        
                        state: CreateServiceRequest
                            q: $commonYes
                            script:
                                $.session.aao = false;
                                
                                // параметры для await-экшна
                                $.session.awaitAction = $.session.awaitAction || {};
                                $.session.awaitAction.returnState = $context.currentState;
                                $.session.awaitAction.action = "createServiceRequest";
                                $.session.awaitAction.readTimeout = 4000;
                                $.session.awaitAction.audio = audioDict.initialWait;
                                if($.session.awaitAction.status){
                                    delete $.session.awaitAction;
                                    if($.session.serviceRequestStatus == 'OK'){
                                        $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                                        $.session.intent.resultCode = 40;
                                        announceAudio(audioDict.SZYaSozdal_TS_request);
                                        // заново преобразуем в дату для аннонсов даты и времени
                                        $.session.serviceRequestDate = new Date($.session.serviceRequestDate);
                                        announceDateGen($.session.serviceRequestDate);
                                        announceAudio(audioDict.at);
                                        announceHoursFromDate($.session.serviceRequestDate);
                                        $reactions.transition("/WhatElse/WhatElse");
                                    }else{
                                        // TODO после доработки будем проверять три статуса
                                        /*if($.session.serviceRequestStatus == '2'){
                                            // создали заявку, но не смогли запланировать
                                            $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                                            $.session.intent.resultCode = 6;
                                            announceAudio(audioDict.YaNeSplaniroval_TS_request);
                                            $reactions.transition("/Transfer/CheckOCTP");
                                        }else{
                                        */
                                        $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                                        $.session.intent.resultCode = 1;
                                        announceAudio(audioDict.YaNeSozdal_billingError);
                                        $reactions.transition("/Transfer/CheckOCTP");
                                        //}
                                    }
                                }else{
                                    $.session.intent.stepsCnt++;
                                    if($.session.serviceRequestComment == ''){
                                        if($.session.spas){
                                            if($.session.productId == 5){
                                                $.session.serviceRequestComment = $.session.spas.internetProblem.requestComment;
                                            }
                                            if($.session.productId == 53){
                                                $.session.serviceRequestComment = $.session.spas.tvProblem.requestComment;
                                            }
                                        }
                                    }else{
                                        $.session.serviceRequestComment;
                                    }
                                    $reactions.transition("/AwaitAction/RunAction");
                                }
                                
                        state: ToOperator
                            q: $commonNo
                            script:
                                $.session.intent.stepsCnt++;
                                $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                                $.session.intent.resultCode = 1;
                                announceAudio(audioDict.YaNeSozdal_clientDisagree);
                                $reactions.transition("/Transfer/CheckOCTP");
                            
                        state: CatchAll || noContext = true
                            event: noMatch
                            script:
                                $.session.intent.stepsCnt++;
                                if(countRepeatsInRow() < $injector.noMatchLimit) {
                                    announceAudio(audioDict.goZayavku_CatchAll);
                                    $reactions.transition("/CreateServiceRequest/TSrequest/Ask/GetAvailableDays/GetAvailableTimeSlots/ConfirmServiceRequest");
                                }else{
                                    $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                                    $.session.intent.resultCode = 1;
                                    announceAudio(audioDict.YaNeSozdal_noMatchAnswer);
                                    $reactions.transition("/Transfer/CheckOCTP");
                                }
                
            state: ToOperator
                q: $commonNo
                q: * не готов* *
                q: * не создавай* *
                q: * $noAtHome *
                script:
                    $.session.intent.stepsCnt++;
                    $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $.session.intent.resultCode = 6;
                    announceAudio(audioDict.perezagruzhayVsyo_transferToOKC);
                    $reactions.transition("/Transfer/CheckOCTP");
            
            state: AgentRequest || noContext = true
                q: $agentRequest
                intent: /405_AgentRequest
                go!: /LocalAgentRequest/AgentRequest
                        
            state: AvailableTime || noContext = true
                q: * @duckling.time *
                q: * @duckling.time-of-day *
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        $reactions.transition("/CreateServiceRequest/TSrequest/Ask");
                    }else{
                        $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = 6;
                        announceAudio(audioDict.perezagruzhayVsyo_transferToOKC);
                        $reactions.transition("/Transfer/CheckOCTP");
                    }
                    
    state: NetworkAdminRequest
        script:
            announceAudio(audioDict.SPAS_admin_request);
        go!: ./Ask
        
        state: Ask
            script:
                announceAudio(audioDict.doYouWant_admin_request);

            state: ThemeMatch || noContext = true
                # перехватываем match в глобальную тематику при помощи interceptGlobalMatch в init-блоке
                script:
                    countRepeatsInRow();
                if: $session.repeatsInRow < 2
                    script:
                        announceAudio(audioDict.doYouWant_admin_request_ThemeMatch);
                        $.session.themeMatched = true;
                else:
                    go!: {{ $temp.matchTargetState }}
        
            state: CreateNetworkAdminRequest
                q: * $commonYes *
                q: * какой день *
                q: * готов* *
                q: * (создай*/создавай*) *
                script:
                    $.session.aao = true;
                    // параметры для await-экшна
                    $.session.awaitAction = $.session.awaitAction || {};
                    $.session.awaitAction.returnState = $context.currentState;
                    if($.session.serviceRequestComment == ''){
                        if($.session.productId == 5){
                            $.session.serviceRequestComment = $.session.spas.internetProblem.requestComment;
                        }
                        if($.session.productId == 53){
                            $.session.serviceRequestComment = $.session.spas.tvProblem.requestComment;
                        }
                    }else{
                        $.session.serviceRequestComment;
                    }
                    $.session.awaitAction.action = "createServiceRequest";
                    $.session.awaitAction.readTimeout = 4000;
                    $.session.awaitAction.audio = audioDict.initialWait;
                    if($.session.awaitAction.status){
                        delete $.session.awaitAction;
                        if($.session.serviceRequestStatus == 'OK'){
                            announceAudio(audioDict.YaSozdal_admin_request);
                            $.session.intent.resultCode = 40;
                            $reactions.transition("/WhatElse/WhatElse");
                        }else{
                            $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            $.session.intent.resultCode = 3;
                            announceAudio(audioDict.YaNeSozdal_billingError);
                            $reactions.transition("/Transfer/CheckOCTP");
                        }
                    }else{
                        $.session.intent.stepsCnt++;
                        $reactions.transition("/AwaitAction/RunAction");
                    }
                    
            state: NoNetworkRequest
                q: $commonNo
                q: * не готов* *
                q: * не создавай* *
                q: * $noAtHome *
                script:
                    $.session.intent.stepsCnt++;
                    $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $.session.intent.resultCode = 6;
                    announceAudio(audioDict.perezagruzhayVsyo_transferToOKC);
                    $reactions.transition("/Transfer/CheckOCTP");
            
            state: AgentRequest || noContext = true
                q: $agentRequest
                intent: /405_AgentRequest
                go!: /LocalAgentRequest/AgentRequest
            
            state: catchAll || noContext = true
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        announceAudio(audioDict.doYouWant_admin_request_CatchAll);
                        $reactions.transition("/CreateServiceRequest/NetworkAdminRequest/Ask");
                    }else{
                        $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = 1;
                        announceAudio(audioDict.YaNeSozdal_noMatchAnswer);
                        $reactions.transition("/Transfer/CheckOCTP");
                    }
