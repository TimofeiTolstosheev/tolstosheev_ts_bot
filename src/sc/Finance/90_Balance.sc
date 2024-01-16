theme: /Balance
    state: Balance
        q!: $balance
        intent!: /90_Balance
        script:
            startIntent('/90_Balance');
        go!: CheckAuth
    
        state: CheckAuth
            if: $.session.userType == 'user'
                go!: /Balance/Balance/CheckAuth/GetBalance
            else:
                go!: /Balance/Balance/CheckAuth/NotAuth
            
            state: GetBalance
                script:
                    // параметры для await-экшна
                    $.session.awaitAction = $.session.awaitAction || {};
                    $.session.awaitAction.returnState = $context.currentState;
                    $.session.awaitAction.action = "getBalance";
                    $.session.awaitAction.readTimeout = 4000;
                    $.session.awaitAction.audio = audioDict.initialWait;
                    if($.session.awaitAction.status){
                        delete $.session.awaitAction;
                        if($.session.lastPayment == 1 && $.session.lastPaymentSum){
                            announceAudio(audioDict.PaymentCame);
                            announceSum($.session.lastPaymentSum);
                        }
                        // определяем шаблон РМ
                        $analytics.setSessionData("Balance text param", $.session.textParam || "undefined");
                        switch($.session.textParam) {
                            case 0:
                                announceAudio(audioDict.UBalans);
                                announceSum($.session.curBalance);
                                if($.session.periodDates.indexOf("-") > 0){
                                    announceAudio(audioDict.balance_period_2);
                                }else{
                                    announceAudio(audioDict.StoimistZa);
                                }
                                announcePeriod($.session.periodDates);
                                announceSum($.session.chargesSum);
                                announceAudio(audioDict.OplataZa);
                                announcePeriod($.session.periodDates);
                                announceSum($.session.recommendSum);
                                break;
                            case 1:
                                announceAudio(audioDict.UBalans);
                                announceSum($.session.curBalance);
                                if($.session.periodDates.indexOf("-") > 0){
                                    announceAudio(audioDict.balance_period_2);
                                }else{
                                    announceAudio(audioDict.StoimistZa);
                                }
                                announcePeriod($.session.periodDates);
                                announceSum($.session.chargesSum);
                                announceAudio(audioDict.VostanovitDostup);
                                announcePeriod($.session.periodDates);
                                announceSum($.session.recommendSum);
                                break;
                            case 2:
                                announceAudio(audioDict.UBalans);
                                announceSum($.session.curBalance);
                                if($.session.periodDates.indexOf("-") > 0){
                                    announceAudio(audioDict.balance_period_2);
                                }else{
                                    announceAudio(audioDict.StoimistZa);
                                }
                                announcePeriod($.session.periodDates);
                                announceSum($.session.chargesSum);
                                announceAudio(audioDict.OplataDo);
                                var d = moment($.session.nextPeriodDate, "DD.MM.YYYY").toDate();
                                announceDateGen(d);
                                announceSum($.session.recommendSum);
                                break;
                            case 3:
                                announceAudio(audioDict.UBalans);
                                announceSum($.session.curBalance);
                                var d = moment($.session.nextPeriodDate, "DD.MM.YYYY").toDate();
                                announceDateGen(d);
                                if($.session.periodDates.indexOf("-") > 0){
                                    announceAudio(audioDict.balance_period3);
                                }else{
                                    announceAudio(audioDict.SOplataZa);
                                }
                                announcePeriod($.session.periodDates);
                                announceSum($.session.chargesSum);
                                break;
                            case 4:
                                announceAudio(audioDict.UBalans);
                                announceSum($.session.curBalance);
                                announceAudio(audioDict.Vnesite);
                                announceSum($.session.chargesSum);
                                break;
                            case 5:
                                announceAudio(audioDict.UBalans);
                                announceSum($.session.curBalance);
                                if($.session.periodDates.indexOf("-") > 0){
                                    announceAudio(audioDict.balance_period_2);
                                }else{
                                    announceAudio(audioDict.StoimistZa);
                                }
                                announcePeriod($.session.periodDates);
                                announceSum($.session.chargesSum);
                                announceAudio(audioDict.OplataDo);
                                var d = moment($.session.opEndDate, "DD.MM.YYYY").toDate();
                                announceDateGen(d);
                                announceSum($.session.recommendSum);
                                break;
                            case 6:
                                announceAudio(audioDict.UBalans);
                                announceSum($.session.curBalance);
                                announceAudio(audioDict.saveServices_2);
                                announceSum($.session.recommendSum);
                                break;
                            case 7:
                                announceAudio(audioDict.UBalans);
                                announceSum($.session.curBalance);
                                if($.session.periodDates.indexOf("-") > 0){
                                    announceAudio(audioDict.balance_period_2);
                                }else{
                                    announceAudio(audioDict.StoimistZa);
                                }
                                announcePeriod($.session.periodDates);
                                announceSum($.session.chargesSum);
                                announceAudio(audioDict.OplataZa);
                                announcePeriod($.session.periodDates);
                                announceSum($.session.recommendSum);
                                break;
                            case 10:
                                announceAudio(audioDict.UBalans);
                                announceSum($.session.curBalance);
                                announceAudio(audioDict.agreementClos);
                                var d = moment($.session.closeDate, "DD.MM.YYYY").toDate();
                                announceDateGen(d);
                                announceAudio(audioDict.OplataClos);
                                announceSum($.session.recommendSum);
                                break;
                            case 11:
                                announceAudio(audioDict.UBalans);
                                announceSum($.session.curBalance);
                                announceAudio(audioDict.agreementClos);
                                var d = moment($.session.closeDate, "DD.MM.YYYY").toDate();
                                announceDateGen(d);
                                break;
                            case 12:
                                announceAudio(audioDict.UBalans);
                                announceSum($.session.curBalance);
                                if($.session.periodDates.indexOf("-") > 0){
                                    announceAudio(audioDict.balance_period_2);
                                }else{
                                    announceAudio(audioDict.StoimistZa);
                                }
                                announcePeriod($.session.periodDates);
                                announceSum($.session.chargesSum);
                                announceAudio(audioDict.BalansChangeZa);
                                announcePeriod($.session.periodDates);
                                announceAudio(audioDict.Oplatite);
                                announceSum($.session.recommendSum);
                                announceAudio(audioDict.Za);
                                announcePeriod($.session.periodDates);
                                announceAudio(audioDict.saveServices);
                                break;
                            case 13:
                                announceAudio(audioDict.UBalans);
                                announceSum($.session.curBalance);
                                var d = moment($.session.nextPeriodDate, "DD.MM.YYYY").toDate();
                                announceDateGen(d);
                                if($.session.periodDates.indexOf("-") > 0){
                                    announceAudio(audioDict.balance_period3);
                                }else{
                                    announceAudio(audioDict.SOplataZa);
                                }
                                announcePeriod($.session.periodDates);
                                announceSum($.session.chargesSum);
                                break;
                            case 14:
                                announceAudio(audioDict.UBalans);
                                announceSum($.session.curBalance);
                                if($.session.periodDates.indexOf("-") > 0){
                                    announceAudio(audioDict.balance_period_2);
                                }else{
                                    announceAudio(audioDict.StoimistZa);
                                }
                                announcePeriod($.session.periodDates);
                                announceSum($.session.chargesSum);
                                announceAudio(audioDict.BalansChangeZa);
                                announcePeriod($.session.periodDates);
                                announceAudio(audioDict.Oplatite);
                                announceSum($.session.recommendSum);
                                announceAudio(audioDict.Za);
                                announcePeriod($.session.periodDates);
                                break;
                            default:
                                $.session.intent.resultCode = 6;
                                $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                                announceAudio(audioDict.transferToAgentForFurther);
                                stopIntent();
                                $reactions.transition('/Transfer/Transfer');
                                break;
                        }
                        
                        if($.session.retStr === '$5'){
                            announceAudio(audioDict.BalansChange);
                        }
                    }else{
                        $reactions.transition("/AwaitAction/RunAction");
                    }
                
                if: $.session.cellPhone
                    go!: /Balance/Balance/CheckAuth/GetBalance/AskBalanceSms
                else:
                    go!: /Balance/Balance/CheckAuth/GetBalance/GoToWebCab
                    
                state: AskBalanceSms
                    script:
                        announceAudio(audioDict.SMSBalance);
                    
                    state: SendBalanceSms
                        q: $commonYes
                        q: $yesForSms
                        script:
                            $.session.intent.stepsCnt++;
                            sendSMS('800_AccountBalanceSMS');
                            if($.session.SMSstatus){
                                announceAudio(audioDict.SMSGo);
                            }
                        go!: /Balance/Balance/CheckAuth/GetBalance/AskBalanceSms/SendWebCabSms
                        
                    state: SendWebCabSms
                        q: $commonNo
                        q: $noForSms
                        script:
                            $.session.intent.stepsCnt++;
                            announceAudio(audioDict.DetelBalance);
                            $.session.intent.stepsCnt++;
                            sendSMSbyTemplate('834_SMSDetelBalance');
                            if($.session.SMSstatus){
                                announceAudio(audioDict.SMSGo);
                            }
                        go!: /WhatElse/WhatElse
                    
                    state: СatchAll || noContext = true
                        event: noMatch
                        script:
                            $.session.intent.stepsCnt++;
                            if(countRepeatsInRow() < $injector.noMatchLimit) {
                                announceAudio(audioDict.SMSBalance);
                            }else{
                                $reactions.transition('/Balance/Balance/CheckAuth/GetBalance/AskBalanceSms/SendWebCabSms');
                            }
                            
                state: GoToWebCab
                    script:
                        announceAudio(audioDict.DetelBalanceAvtorizGorod);
                    go!: /WhatElse/WhatElse
                    
            state: NotAuth
                if: $.session.cellPhone
                    go!: /Balance/Balance/CheckAuth/NotAuth/SendWebCabSms
                else:
                    go!: /Balance/Balance/CheckAuth/NotAuth/GoToWebCab
                
                state: SendWebCabSms
                    script:
                        announceAudio(audioDict.DetelBalanceNoAvtoriz);
                        sendSMSbyTemplate('834_SMSDetelBalance');
                        if($.session.SMSstatus){
                            announceAudio(audioDict.SMSGo);
                        }
                    go!: /WhatElse/WhatElse
                
                state: GoToWebCab
                    script:
                        announceAudio(audioDict.DetelBalanceNoAvtorizGorod);
                    go!: /WhatElse/WhatElse
