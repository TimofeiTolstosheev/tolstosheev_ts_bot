theme: /Transfer
    state: CheckOCTP
        # TODO перенести проверку на технические сценарии из других тем в тему Transfer, чтобы определять только тут - проверять ОЦТП метки или нет
        # стейт, если не знаем коллер и надо проверить метки
        script:
            checkRedirectOctp();
            if($.session.sessionOctp && ($.session.intent.currentIntent == '300_NoLink' || $.session.lastIntent == '300_NoLink')){
                // для noLink проверяем sessionOctp
                $.session.callerInput = "inetoctp";
                $.session.intent.resultCode = 7;
            }else{
                if ($.session.flagOctp){
                    $.session.intent.resultCode = 7;
                    $.session.callerInput = "OCTPMetka";
                }else{
                    if (!$.session.octpAppTypeTransfer && ($.session.intent.currentIntent == '280_LowSpeed' || $.session.lastIntent == '280_LowSpeed')){
                        $.session.intent.resultCode = 7;
                        $.session.callerInput = "octp_speed";
                    }else{
                        if(($.session.intent.currentIntent == '290_TVChannelProblem' || $.session.lastIntent == '290_TVChannelProblem') &&
                           $.session.iktvDevices["cam-module"] > 0 && !$.session.iktvDevices["dvbc"] &&
                           !$.session.iktvDevices["movix"] && !$.session.iktvDevices["iptv"] &&
                           !$.session.iktvDevices["virtualDevice"] && !$.session.iktvDevices["movixPro"] &&
                           !$.session.iktvDevices["assortmentBox"]){
                            // если интент 290 и только кам-модуль, то коллер cam_octp
                            $.session.intent.resultCode = 7;
                            $.session.callerInput = "cam_octp";
                        }else{
                            // проверяем, получили ли до этого коллер СПАСа
                            switch($.session.callerInput){
                                case "tech_spas":
                                    $.session.intent.resultCode = 6;
                                    break;
                                case "tech_spas2":
                                    $.session.intent.resultCode = 6;
                                    break;
                                case "tech_spas3":
                                    $.session.intent.resultCode = 7;
                                    break;
                                case "tech_octp":
                                    $.session.intent.resultCode = 7;
                                default:
                                    $.session.callerInput = $.session.callerInput || getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                                    $.session.intent.resultCode = $.session.intent.resultCode || 6;
                                    break;
                                }
                        }
                    }
                }
            }
        go!: /Transfer/Transfer
    
    state: IntentLimiTransfer
        script:
            announceAudio(audioDict.perevod_na_okc_from_Repeat);
            $.session.intent.resultCode = 28;
            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
        go!: /Transfer/Transfer
    
    state: TransferOnError
        #стейт для перевод по непредвиденным ошибкам
        script:
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
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    stopIntent();
                }
                $reactions.transition("/Transfer/Transfer");
            }
    
    state: Transfer
        # стейт, если уже знаем коллер
        script:
            // завершаем интент, если вдруг не завершили в сценарии
            stopIntent();
            $.session.callerInput = $.session.callerInput || $.injector.defaultCallerInput;
            if($.session.callerInput == 'toExit' || $.session.callerInput == 'silence'){
                // считаем, что диалог завершился в боте
                $.session.callEndType = 'near-hup';
            }else{
                // считаем, что диалог завершился переводом
                $.session.callEndType = 'transfer';
            }
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
