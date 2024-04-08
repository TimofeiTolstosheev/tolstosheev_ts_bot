theme: /Balance
    state: Balance
        q!: $balance
        intent!: /90_Balance
        script:
            startIntent('/90_Balance');
        go!: CheckAuth
    
        state: CheckAuth
            if: $.session.userType == 'user' && !$.session.cifral && !$.session.oplataUk && !$.session.metacom
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
                        var textParam = $.session.textParam ? $.session.textParam.toString() : "undefined";
                        $analytics.setSessionData("Balance text param", textParam);
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
                                if($.session.periodDates.indexOf("-") > 0){
                                    announceAudio(audioDict.VostanovitDostup);
                                }else{
                                    announceAudio(audioDict.Payforaccess);
                                }
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
                        q: * $yesForSms *
                        script:
                            $.session.intent.stepsCnt++;
                            //var smsTemplate = $.session.intent.currentIntent == "530_PaymentIssue" ? "839_PaymentIssueSMS" : "800_AccountBalanceSMS";
                            switch($.session.intent.currentIntent) {
                                case "530_PaymentIssue":
                                    var smsTemplate = "839_PaymentIssueSMS";
                                    break;
                                case "91_PaymentQuestion":
                                    var smsTemplate = "841_PaymentQuestionSMS";
                                    break;
                                case "92_DebtQuestion":
                                    var smsTemplate = "843_DebtQuestionSMS";
                                    break;
                                case "93_DebtReason":
                                    var smsTemplate = "845_DebtReasonSMS";
                                    break;
                                case "94_WhichSubscriptionFee":
                                    var smsTemplate = "847_WhichSubscriptionFeeSMS";
                                    break;
                                case "95_DebtQuestionDetails":
                                    var smsTemplate = "849_DebtQuestionDetailsSMS";
                                    break;
                                case "96_ChangingSubscriptionFee":
                                    var smsTemplate = "851_ChangingSubscriptionFeeSMS";
                                    break;
                                default:
                                    var smsTemplate = "800_AccountBalanceSMS";
                                    break;
                            }
                            sendSMS(smsTemplate);
                            if($.session.SMSstatus){
                                announceAudio(audioDict.SMSGo);
                            }
                        go!: /Balance/Balance/CheckAuth/GetBalance/AskBalanceSms/SendWebCabSms
                        
                    state: SendWebCabSms
                        q: $commonNo
                        q: * $noForSms *
                        script:
                            $.session.intent.stepsCnt++;
                            announceAudio(audioDict.DetelBalance);
                            $.session.intent.stepsCnt++;
                            //var smsTemplate = $.session.intent.currentIntent == "530_PaymentIssue" ? "840_SMSDetelPaymentIssue" : "834_SMSDetelBalance";
                            switch($.session.intent.currentIntent) {
                                case "530_PaymentIssue":
                                    var smsTemplate = "840_SMSDetelPaymentIssue";
                                    break;
                                case "91_PaymentQuestion":
                                    var smsTemplate = "842_PaymentQuestionSMSDetel";
                                    break;
                                case "92_DebtQuestion":
                                    var smsTemplate = "844_DebtQuestionSMSDetel";
                                    break;
                                case "93_DebtReason":
                                    var smsTemplate = "846_DebtReasonSMSDetel";
                                    break;
                                case "94_WhichSubscriptionFee":
                                    var smsTemplate = "848_WhichSubscriptionFeeSMSDetel";
                                    break;
                                case "95_DebtQuestionDetails":
                                    var smsTemplate = "850_DebtQuestionDetailsSMSDetel";
                                    break;
                                case "96_ChangingSubscriptionFee":
                                    var smsTemplate = "852_ChangingSubscriptionFeeSMSDetel";
                                    break;
                                default:
                                    var smsTemplate = "834_SMSDetelBalance";
                                    break;
                            }
                            sendSMSbyTemplate(smsTemplate);
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
                if: $.session.cifral
                    go!: ./Cifral
                else:
                    if: $.session.oplataUk
                        go!: ./OplataUK
                    else:
                        if: $.session.metacom
                            go!: ./Metacom
                        else:
                            if: $.session.cellPhone
                                go!: /Balance/Balance/CheckAuth/NotAuth/SendWebCabSms
                            else:
                                go!: /Balance/Balance/CheckAuth/NotAuth/GoToWebCab
                                
                state: Cifral
                    script:
                        announceAudio(audioDict.DomofonBalanceCifral);
                        $.session.intent.resultCode = 32;
                        $.session.callerInput = 'dmf_cifral';
                    go!: /Transfer/Transfer
                    
                state: OplataUK
                    script:
                        announceAudio(audioDict.DomofonUK);
                    go!: /WhatElse/WhatElse
                    
                state: Metacom
                    script:
                        announceAudio(audioDict.DomofonMetakom);
                    go!: /WhatElse/WhatElse
                
                state: SendWebCabSms
                    script:
                        announceAudio(audioDict.DetelBalanceNoAvtoriz);
                        //var smsTemplate = $.session.intent.currentIntent == "530_PaymentIssue" ? "840_SMSDetelPaymentIssue" : "834_SMSDetelBalance";
                        switch($.session.intent.currentIntent) {
                            case "530_PaymentIssue":
                                var smsTemplate = "840_SMSDetelPaymentIssue";
                                break;
                            case "91_PaymentQuestion":
                                var smsTemplate = "842_PaymentQuestionSMSDetel";
                                break;
                            case "92_DebtQuestion":
                                var smsTemplate = "844_DebtQuestionSMSDetel";
                                break;
                            case "93_DebtReason":
                                var smsTemplate = "846_DebtReasonSMSDetel";
                                break;
                            case "94_WhichSubscriptionFee":
                                var smsTemplate = "848_WhichSubscriptionFeeSMSDetel";
                                break;
                            case "95_DebtQuestionDetails":
                                var smsTemplate = "850_DebtQuestionDetailsSMSDetel";
                                break;
                            case "96_ChangingSubscriptionFee":
                                var smsTemplate = "852_ChangingSubscriptionFeeSMSDetel";
                                break;
                            default:
                                var smsTemplate = "834_SMSDetelBalance";
                                break;
                        }
                        sendSMSbyTemplate(smsTemplate);
                        if($.session.SMSstatus){
                            announceAudio(audioDict.SMSGo);
                        }
                    go!: /WhatElse/WhatElse
                
                state: GoToWebCab
                    script:
                        announceAudio(audioDict.DetelBalanceNoAvtorizGorod);
                    go!: /WhatElse/WhatElse
