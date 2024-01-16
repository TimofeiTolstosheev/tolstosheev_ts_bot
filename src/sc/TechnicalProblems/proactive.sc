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
        "/Proactive/Accident/AskSMS": "ThemeMatch",
        "/Proactive/PaymentMethods": "ThemeMatch",
        "/Proactive/AskPromisedPayment": "ThemeMatch",
    }, interceptGlobalMatch);
    
theme: /Proactive
    state: RouteProactive || noContext = true
        script:
            // определим на всякий случай коллер по основному интенту
            $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
        
        # аварии/ППР
        if: $.session.accident 
            go!: /Proactive/Accident
        if: $.session.ppr
            go!: /Proactive/PPR
        if: $.session.potentialAccident
            go!: /Proactive/PotentialAccident
        
        script:
            // у всего проактива один код 29
            // определяем тут, потому что у аварии и ППР свой интент, поэтому тот, из которого пришли должен остаться с 0
            $.session.intent.resultCode = 29;
        
        # состояние услуг
        if: $.session.proactiveResult == 'monoKTV_DZ_NotActive'
            go!: /Proactive/monoKTV_DZ_NotActive
        if: $.session.proactiveResult == 'monoKTV_NoDZ_NotActive'
            go!: /Proactive/monoKTV_NoDZ_NotActive
        if: $.session.proactiveResult == 'KP_DZ_NotActive'
            go!: /Proactive/KP_DZ_NotActive
        if: $.session.proactiveResult == 'KP_NoDZ_NotActive'
            go!: /Proactive/KP_NoDZ_NotActive
        if: $.session.proactiveResult == 'mono_NotEnoughBalance_NotActive'
            go!: /Proactive/mono_NotEnoughBalance_NotActive
        if: $.session.proactiveResult == 'mono_EnoughBalance_NotActive'
            go!: /Proactive/mono_EnoughBalance_NotActive
        if: $.session.serviceRequest && $.session.serviceRequestStatus != 'request-overdue'
            go!: /ServiceRequestInfo/CheckSRV
        script:
            // попали в проактив по ошибке
            $.session.proactiveProblem = false;
            $reactions.transition($.session.lastState);
            
    state: Accident
        script:
            if($.session.intent.currentIntent == '300_NoLink'){
                var applComment = 'По адресу Клиента зафиксирована авария/ППР, проинформирован.';
                var clientUtterance = $parseTree.text;
                var problem = 'no-link-6xx-success';
                createAppeal(applComment, clientUtterance, $.session.productId, problem);
            }
            stopIntent();
            startIntent('500_Accident');
            $.session.intent.resultCode = 29; // у всего проактива один код 29
            $.session.intent.info = $.session.accidentId;
            announceAudio(audioDict.techProblems_accident_header);
            announceAudio(audioDict.avaria_time);
            
            $temp.now = new Date();
            $temp.accEndDate = moment($.session.accidentEndDate, "DD.MM.YYYY HH:mm:ss Z").toDate();
            $temp.accidentHours = Math.round(($temp.accEndDate - $temp.now) / 1000 / 60 / 60);
            $temp.accidentHours = $temp.accidentHours < 1 ? 1 : $temp.accidentHours; // если время уже прошло, считаем, что закончится через час
            announceTime($temp.accidentHours, 0);
            
            announceAudio(audioDict.techProblems_accident_ending);
            if($.session.cellPhone && $.session.askSms){
                $reactions.transition("/Proactive/Accident/AskSMS");
            }else{
                $reactions.transition('/Proactive/CallAfterAccident');
            }
        
        state: AskSMS
            script:
                announceAudio(audioDict.zakaz_sms_info1);
                
            state: SendSMS
                q: $commonYes
                q: $yesForSms
                script:
                    $.session.intent.stepsCnt++;
                    setTechServiceSmsNotification();
                    announceAudio(audioDict.zakaz_sms_info2);
                    $reactions.transition('/Proactive/CallAfterAccident');
                    
            state: NoSMS
                q: $commonNo
                q: $noForSms
                script:
                    $.session.intent.stepsCnt++;
                go!: /Proactive/CallAfterAccident

            state: ThemeMatch || noContext = true
                # перехватываем match в глобальную тематику при помощи interceptGlobalMatch в init-блоке
                script:
                    countRepeatsInRow();
                if: $session.repeatsInRow < 2
                    script:
                        announceAudio(audioDict.zakaz_sms_info1_ThemeMatch);
                        $.session.themeMatched = true;
                else:
                    go!: {{ $temp.matchTargetState }}
        
            state: CatchAll || noContext = true
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        announceAudio(audioDict.zakaz_sms_proactive_CatchAll);
                    }else{
                        if($.session.applId){
                            closeAppeal($.session.applId);
                        }
                        $reactions.transition('/WhatElse/WhatElse');
                    }
        
    state: PPR
        script:
            if($.session.intent.currentIntent == '300_NoLink'){
                var applComment = 'По адресу Клиента зафиксирована авария/ППР, проинформирован.';
                var clientUtterance = $parseTree.text;
                var problem = 'no-link-6xx-success';
                createAppeal(applComment, clientUtterance, $.session.productId, problem);
            }
            stopIntent();
            startIntent('500_Accident');
            $.session.intent.resultCode = 29; // у всего проактива один код 29
            announceAudio(audioDict.techProblems_ppr_header_1);
            $temp.pprEndDate = moment($.session.currentPprEndDate, "DD.MM.YYYY HH:mm:ss Z").toDate();
            announceTimeFromTimeTo(-1, -1, $temp.pprEndDate.getHours(), $temp.pprEndDate.getMinutes());
            $reactions.transition('/Proactive/CallAfterAccident');
        
    state: PotentialAccident
        script:
            if($.session.intent.currentIntent == '300_NoLink'){
                var applComment = 'По адресу Клиента зафиксирована авария/ППР, проинформирован.';
                var clientUtterance = $parseTree.text;
                var problem = 'no-link-6xx-success';
                createAppeal(applComment, clientUtterance, $.session.productId, problem);
            }
            stopIntent();
            startIntent('500_Accident');
            $.session.intent.resultCode = 29; // у всего проактива один код 29
            announceAudio(audioDict.potential_accident);
            $reactions.transition('/Proactive/CallAfterAccident');

    state: CallAfterAccident
        script:
            announceAudio(audioDict.Avaria_Wait);
            if($.session.applId){
                closeAppeal($.session.applId);
            }
            $reactions.transition('/WhatElse/WhatElse');
        
    # Для No Link создаем обращение
    state: monoKTV_DZ_NotActive
        script:
            if($.session.intent.currentIntent == '300_NoLink'){
                var applComment = 'По адресу Клиента зафикисрована ДЗ, проинформирован.';
                var clientUtterance = $parseTree.text;
                var problem = 'no-link-6xx-success';
                createAppeal(applComment, clientUtterance, $.session.productId, problem);
            }
            announceAudio(audioDict.Oplati_DZ_ktv);
            announceAudio(audioDict.Oplati_recplatezh);
            announceSum($.session.recommendSum);
        go!: /Proactive/PaymentMethods
        
    state: monoKTV_NoDZ_NotActive
        script:
            if($.session.intent.currentIntent == '300_NoLink'){
                var applComment = 'У Клиента не активирован тариф.';
                var clientUtterance = $parseTree.text;
                var problem = 'no-link-6xx-success';
                createAppeal(applComment, clientUtterance, $.session.productId, problem);
            }
            announceAudio(audioDict.transferToAgentForFurther);
            $.session.callerInput = 'tech';
            $.session.intent.resultCode = 6;
            $reactions.transition("/Transfer/Transfer");
        
    state: KP_DZ_NotActive
        script:
            if($.session.intent.currentIntent == '300_NoLink'){
                var applComment = 'По адресу Клиента зафикисрована ДЗ, проинформирован.';
                var clientUtterance = $parseTree.text;
                var problem = 'no-link-6xx-success';
                createAppeal(applComment, clientUtterance, $.session.productId, problem);
            }
            announceAudio(audioDict.Oplati_DZ);
            announceAudio(audioDict.Oplati_recplatezh);
            announceSum($.session.recommendSum);
        go!: /Proactive/PaymentMethods
        
    state: KP_NoDZ_NotActive
        script:
            if($.session.intent.currentIntent == '300_NoLink'){
                var applComment = 'У Клиента недостаточно средств на договоре, проинформирован.';
                var clientUtterance = $parseTree.text;
                var problem = 'no-link-6xx-success';
                createAppeal(applComment, clientUtterance, $.session.productId, problem);
            }
            announceAudio(audioDict.Oplati_recplatezh);
            announceSum($.session.recommendSum);
            if($.session.promisedPaymentAvailable){
                $reactions.transition("/Proactive/AskPromisedPayment");
            }else{
                $reactions.transition('/Proactive/PaymentMethods');
            }
        
    state: mono_NotEnoughBalance_NotActive
        script:
            if($.session.intent.currentIntent == '300_NoLink'){
                var applComment = 'У Клиента недостаточно средств на договоре, проинформирован.';
                var clientUtterance = $parseTree.text;
                var problem = 'no-link-6xx-success';
                createAppeal(applComment, clientUtterance, $.session.productId, problem);
            }
            announceAudio(audioDict.Oplati_recplatezh);
            announceSum($.session.recommendSum);
            if($.session.promisedPaymentAvailable){
                $reactions.transition("/Proactive/AskPromisedPayment");
            }else{
                $reactions.transition('/Proactive/PaymentMethods');
            }
        
    state: mono_EnoughBalance_NotActive
        script:
            if($.session.intent.currentIntent == '300_NoLink'){
                var applComment = 'У Клиента не активирован тариф, проинформирован об активации.';
                var clientUtterance = $parseTree.text;
                var problem = 'no-link-6xx-success';
                createAppeal(applComment, clientUtterance, $.session.productId, problem);
            }
            announceAudio(audioDict.activeTP);
            announceSum($.session.subscriptionFee);
            announceAudio(audioDict.activeTPnow);
            $reactions.transition("/Proactive/AskToActivateTP");

    state: AskToActivateTP
            
        state: ActivateTP
            q: $commonYes
            q: активир* *
            q: @Give
            script:
                $.session.intent.stepsCnt++;
                activateTP();
                if(!$.session.tpActivationStatus){
                    announceAudio(audioDict.active_notOK);
                    announceAudio(audioDict.perevod_na_okc);
                    $.session.intent.resultCode = 1;
                    $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $reactions.transition("/Transfer/Transfer");
                }else{
                    announceAudio(audioDict.active_OK);
                    if($.session.applId){
                        closeAppeal($.session.applId);
                    }
                    $reactions.transition('/WhatElse/WhatElse');
                }
                
        state: ToWhatElse
            q: $commonNo
            q: не активир* *
            script:
                $.session.intent.stepsCnt++;
                if($.session.applId){
                    closeAppeal($.session.applId);
                }
            go!: /WhatElse/WhatElse
                
        state: CatchAll || noContext = true
            event: noMatch
            script:
                $.session.intent.stepsCnt++;
                if(countRepeatsInRow() < $injector.noMatchLimit) {
                    announceAudio(audioDict.activeTPnow_CatchAll);
                    $reactions.transition('/Proactive/AskToActivateTP');
                }else{
                    announceAudio(audioDict.activeTPnow_CatchAll_2);
                    if($.session.applId){
                        closeAppeal($.session.applId);
                    }
                    $reactions.transition('/WhatElse/WhatElse');
                }
        
    state: PaymentMethods
        script:
            announceAudio(audioDict.aftererrorop);
        
        state: ToPaymentMehtods
            q: $commonYes
            q: * $yesForPaymentMethods *
            script:
                $.session.intent.stepsCnt++;
            go!: /PaymentsMethods/PaymentsMethods
            
        state: ToWhatElse
            q: $commonNo
            q: * $noForPaymentMethods *
            script:
                $.session.intent.stepsCnt++;
                if($.session.applId){
                    closeAppeal($.session.applId);
                }
            go!: /WhatElse/WhatElse

        state: ThemeMatch || noContext = true
            # перехватываем match в глобальную тематику при помощи interceptGlobalMatch в init-блоке
            script:
                countRepeatsInRow();
            if: $session.repeatsInRow < 2
                script:
                    announceAudio(audioDict.aftererrorop_ThemeMatch);
                    $.session.themeMatched = true;
            else:
                go!: {{ $temp.matchTargetState }}
            
        state: ToOperator
            q: $agentRequest
            intent: /405_AgentRequest
            script:
                $.session.intent.stepsCnt++;
                $.session.agentRequested = true;
                announceAudio(audioDict.perevod_na_okc);
                $.session.intent.resultCode = 6;
                $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                stopIntent();
                startIntent('405_AgentRequest');
                $.session.intent.resultCode = $.session.intent.resultCode || 6;
                stopIntent();
                $reactions.transition("/Transfer/Transfer");
        
        state: CatchAll || noContext = true
            event: noMatch
            script:
                $.session.intent.stepsCnt++;
                if(countRepeatsInRow() < $injector.noMatchLimit) {
                    announceAudio(audioDict.aftererrorop_CatchAll);
                    $reactions.transition('/Proactive/PaymentMethods');
                }else{
                    if($.session.applId){
                        closeAppeal($.session.applId);
                    }
                    $reactions.transition('/WhatElse/WhatElse');
                }
    
    state: AskPromisedPayment
        script:
            announceAudio(audioDict.wait_for_the_payment);
            announceAudio(audioDict.ask_promisedPay);
        
        state: ToPromisedPayment
            q: $commonYes
            q: * $yesForPromisedPayment *
            script:
                $.session.intent.stepsCnt++;
            go!: /PromisedPayment/CheckAuth
            
        state: ToPaymentMethods
            q: $commonNo
            q: * $noForPromisedPayment *
            script:
                $.session.intent.stepsCnt++;
            go!: /Proactive/PaymentMethods

        state: ThemeMatch || noContext = true
            # перехватываем match в глобальную тематику при помощи interceptGlobalMatch в init-блоке
            script:
                countRepeatsInRow();
            if: $session.repeatsInRow < 2
                script:
                    announceAudio(audioDict.ask_promisedPay_ThemeMatch);
                    $.session.themeMatched = true;
            else:
                go!: {{ $temp.matchTargetState }}
            
        state: ToOperator
            q: $agentRequest
            intent: /405_AgentRequest
            script:
                $.session.intent.stepsCnt++;
                $.session.agentRequested = true;
                announceAudio(audioDict.perevod_na_okc);
                $.session.intent.resultCode = 6;
                $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                stopIntent();
                startIntent('405_AgentRequest');
                $.session.intent.resultCode = $.session.intent.resultCode || 6;
                stopIntent();
                $reactions.transition("/Transfer/Transfer");
        
        state: CatchAll || noContext = true
            event: noMatch
            script:
                $.session.intent.stepsCnt++;
                if(countRepeatsInRow() < $injector.noMatchLimit) {
                    announceAudio(audioDict.ask_promisedPay_CatchAll);
                    $reactions.transition('/Proactive/AskPromisedPayment');
                }else{
                    if($.session.applId){
                        closeAppeal($.session.applId);
                    }
                    $reactions.transition('/WhatElse/WhatElse');
                }
