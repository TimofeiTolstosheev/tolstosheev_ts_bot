theme: /PromisedPayment
    
    state: CheckAuth
        q!: $promisedPayment
        intent!: /80_PromisedPayment
        script:
            startIntent('80_PromisedPayment');
        if: $.session.userType == 'user'
            go!: /PromisedPayment/CheckPromisedPayment
        else:
            go!: /PromisedPayment/NotAuth
    
    state: CheckPromisedPayment
        script:
            // параметры для await-экшна
            $.session.awaitAction = $.session.awaitAction || {};
            $.session.awaitAction.returnState = $context.currentState;
            $.session.awaitAction.action = "checkPromisedPayment";
            $.session.awaitAction.readTimeout = 4000;
            $.session.awaitAction.audio = audioDict.initialWait;
            if($.session.awaitAction.status){
                delete $.session.awaitAction;
                if($.session.promisedPaymentError){
                    switch($.session.promisedPaymentError) {
                        case 11:
                            announceAudio(audioDict.promisedPayError11);
                            $reactions.transition("/PaymentsMethods/PaymentsMethods");
                            break;
                        case 12:
                            announceAudio(audioDict.promisedPayError12_1);
                            $reactions.transition("/WhatElse/PromisedPayWhatElse");
                            break;
                        case 13:
                            announceAudio(audioDict.promisedPayError13_1);
                            $reactions.transition("/PromisedPayment/CheckPromisedPayment/CheckDebt");
                            break;
                        case 14:
                            announceAudio(audioDict.promisedPayError14KP);
                            $reactions.transition("/PromisedPayment/CheckPromisedPayment/CheckDebt");
                            break;
                        case 16:
                            announceAudio(audioDict.promisedPayError16_1);
                            announceSum($.session.balanceActiveFrom);
                            announceAudio(audioDict.promisedPayError16_2);
                            $reactions.transition("/PromisedPayment/CheckPromisedPayment/PaymentMethods");
                            break;
                        case 17:
                            announceAudio(audioDict.promisedPayError17_1);
                            $reactions.transition("/WhatElse/PromisedPayWhatElse");
                            break;
                        default:
                            $.session.intent.resultCode = 6;
                            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            announceAudio(audioDict.transferToAgentForFurther);
                            stopIntent();
                            $reactions.transition('/Transfer/Transfer');
                    }
                }else{
                    if($.session.canGetpromisedPayment){
                        $reactions.transition("/PromisedPayment/CheckPromisedPayment/CheckDays");
                    }else{
                        $.session.intent.resultCode = 6;
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        announceAudio(audioDict.transferToAgentForFurther);
                        stopIntent();
                        $reactions.transition('/Transfer/Transfer');
                    }
                }
            }else{
                $reactions.transition("/AwaitAction/RunAction");
            }
            
        state: CheckDays
            if: $.session.promisedPaymentMaxDay > 0
                script:
                    if($.session.promisedPaymentCost > 0){
                        announceAudio(audioDict.daysCostPreview);
                        announceSum($.session.promisedPaymentCost);
                    }else{
                        announceAudio(audioDict.daysCostPreview_besplatno);
                        announceAudio(audioDict.besplatno);
                    }

                if: $.session.mono
                    go!: GetDays
                else:
                    go!: Confirm
            else:
                script:
                    $.session.intent.resultCode = 6;
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    announceAudio(audioDict.transferToAgentForFurther);
                    stopIntent();
                    $reactions.transition('/Transfer/Transfer');
            
            state: GetDays || modal = true
                script:
                    announceAudio(audioDict.maxDayOpPrewview);
                    announceNumber($.session.promisedPaymentMaxDay);
                    announceAudio(audioDict.request_days);
                    
                state: ValidateDays
                    q: * @duckling.number *
                    script:
                        $.session.intent.stepsCnt++;
                        $.session.promisedPaymentDays = $parseTree['duckling.number'][0].value;
                    if: $.session.promisedPaymentDays > $.session.promisedPaymentMaxDay
                        go!: /PromisedPayment/CheckPromisedPayment/IncorrectDays
                    else:
                        go!: /PromisedPayment/CheckPromisedPayment/GetPP
                    
                state: СatchAll || noContext = true
                    event: noMatch
                    script:
                        $.session.intent.stepsCnt++;
                        if(countRepeatsInRow() < $injector.noMatchLimit) {
                            $reactions.transition('/PromisedPayment/CheckPromisedPayment/CheckDays/GetDays');
                        }else{
                            $reactions.transition('/PromisedPayment/CheckPromisedPayment/IncorrectDays');
                        }
            
            state: Confirm
                script:
                    announceAudio(audioDict.ConfirmIntent);
                
                state: GetPP
                    q: $commonYes
                    q: $yesForPromisedPayment
                    script:
                        $.session.intent.stepsCnt++;
                    go!: /PromisedPayment/CheckPromisedPayment/GetPP
                        
                state: NotConfirmed
                    q: $commonNo
                    q: $noForPromisedPayment
                    script:
                        $.session.intent.stepsCnt++;
                    go!: /WhatElse/WhatElse
                    
                state: СatchAll || noContext = true
                    event: noMatch
                    script:
                        $.session.intent.stepsCnt++;
                        if(countRepeatsInRow() < $injector.noMatchLimit) {
                            $reactions.transition('/PromisedPayment/CheckPromisedPayment/CheckDays');
                        }else{
                            announceAudio(audioDict.transferToAgentForFurther);
                            $.session.intent.resultCode = 6;
                            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            stopIntent();
                            $reactions.transition("/Transfer/Transfer");
                        }
        
        state: GetPP
            script:
                $.session.promisedPaymentDays = $.session.promisedPaymentDays || $.session.promisedPaymentMaxDay;
                getPromisedPayment($.session.promisedPaymentDays);
                if($.session.promisedPaymentError){
                    announceAudio(audioDict.transferToAgentForFurther);
                    $.session.intent.resultCode = 6;
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    stopIntent();
                    $reactions.transition("/Transfer/Transfer");
                }else{
                    if($.session.promisedPaymentTotalCost > 0){
                        announceAudio(audioDict.promisedPaySetInfo_OK);
                        announceAudio(audioDict.DRT_0003);
                        announceSum($.session.promisedPaymentTotalCost);
                    }else{
                        announceAudio(audioDict.promisedPaySetInfo_OK_besplatno);
                    }  
                    // у моно при подключении ОП после 15 числа надо активировать ТП
                    if($.session.mono && $.session.after15){
                        activateTP();
                        if(!$.session.tpActivationStatus){
                            $reactions.transition("/PromisedPayment/CheckPromisedPayment/GetPP/TPactivationFailed");
                        }else{
                            $reactions.transition("/WhatElse/WhatElse");
                        }
                    }else{
                        $reactions.transition("/WhatElse/WhatElse");
                    }
                }
                
            state: TPactivationFailed
                script:
                    announceAudio(audioDict.PerevodnaOKC_activatetarif);
                    $.session.intent.resultCode = 6;
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    stopIntent();
                go!: /Transfer/Transfer
        
        state: IncorrectDays
            script:
                announceAudio(audioDict.invalidDaysOp);
                announceAudio(audioDict.perevod_na_okc);
                $.session.intent.resultCode = 6;
                $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                stopIntent();
            go!: /Transfer/Transfer
            
        state: CheckDebt
            script:
                // $.session.balanceActiveFrom приходит строкой. Чтобы сравнить с нулем преобразуем в число
                var balanceActiveFrom = Number($.session.balanceActiveFrom.replace(',', '.'));
                if(balanceActiveFrom < 0){
                    announceAudio(audioDict.UBalans);
                    announceSum($.session.balanceActiveFrom);
                    $reactions.transition("/PromisedPayment/CheckPromisedPayment/PaymentMethods");
                }else{
                    $reactions.transition("/WhatElse/PromisedPayWhatElse");
                }
                
        state: PaymentMethods
            script:
                announceAudio(audioDict.aftererrorop);
        
            state: ToPaymentMehtods
                q: $commonYes
                q: $yesForPaymentMethods
                script:
                    $.session.intent.stepsCnt++;
                go!: /PaymentsMethods/PaymentsMethods
            
            state: ToWhatElse
                q: $commonNo
                q: $noForPaymentMethods
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
                        announceAudio(audioDict.aftererrorop_CatchAll);
                        $reactions.transition('/PromisedPayment/CheckPromisedPayment/PaymentMethods');
                    }else{
                        $reactions.transition('/WhatElse/WhatElse');
                    }
    
    state: NotAuth
        script:
            if($.session.authErrorCode == 9){
                announceAudio(audioDict.error_perevod_na_okc);
            }else{
                announceAudio(audioDict.promisedPaySetInfo);
            }
            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
            stopIntent();
        go!: /Transfer/Transfer
