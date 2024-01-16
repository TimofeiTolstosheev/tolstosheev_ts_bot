theme: /WholeCheaper
    state: CheckAuth
        q!: $wholeCheaper
        intent!: /710_WholeCheaper
        script:
            startIntent('710_WholeCheaper');
        if: $.session.userType == 'user'
            go!: /WholeCheaper/CheckProducts
        else:
            go!: /WholeCheaper/NotAuth
    
    state: CheckProducts
        if: !$.session.int
            script:
                announceAudio(audioDict.KTVcheaper);
            go!: /WhatElse/WhatElse
        else:
            go!: ./CheckOptom
        
        state: CheckOptom
            script:
                checkOptom();
            go!: ./AnnounceAvailablePromo
                
            # делаем отдельный стейт, чтобы можно было повторить доступные акции без запроса
            state: AnnounceAvailablePromo || modal = true
                script:
                    if($.session.optomDateTo){
                        announceAudio(audioDict.ActivCheaper);
                        announceDateGen(new Date($.session.optomDateTo));
                        $reactions.transition("/WholeCheaper/SMS");
                    }else{
                        if(!$.session.optom3available && !$.session.optom6available && !$.session.optom12available){
                            $reactions.transition('./ToOperator');
                            $.session.intent.resultCode = 3;
                        }else{
                            // если доступна только одна акция
                            if(($.session.optom3available && !$.session.optom6available && !$.session.optom12available) ||
                               (!$.session.optom3available && $.session.optom6available && !$.session.optom12available) ||
                               (!$.session.optom3available && !$.session.optom6available && $.session.optom12available)){
                                    announceAudio(audioDict["1cheaper"]);
                                    announceNumber($.session.optom3available ? 3 : ($.session.optom6available ? 6 : 12));
                                    announceAudio($.session.optom3available ? audioDict.montha : audioDict.months);
                                    $.session.optomSelected = $.session.optom3available ? 3 : ($.session.optom6available ? 6 : 12);
                                    if($.session.optom3available){
                                        $reactions.transition($.session.optom3enoughBalance ? "./Confirm" : "./NotEnoughBalance");
                                    }else{
                                        if($.session.optom6available){
                                            $reactions.transition($.session.optom6enoughBalance ? "./Confirm" : "./NotEnoughBalance");
                                        }else{
                                            if($.session.optom12available){
                                                $reactions.transition($.session.optom12enoughBalance ? "./Confirm" : "./NotEnoughBalance");
                                            }
                                        }
                                    }
                            }else{
                                // если несколько акций
                                announceAudio(audioDict['23cheaper']);
                                if($.session.optom3available){announceAudio(audioDict.ru_digit_3_nom);}
                                if($.session.optom6available){
                                    if($.session.optom3available && !$.session.optom12available) announceAudio(audioDict.sound_i);
                                    announceAudio(audioDict.ru_digit_6_nom);
                                }
                                if($.session.optom12available){
                                    if($.session.optom6available || ($.session.optom3available && !$.session.optom6available)) announceAudio(audioDict.sound_i);
                                    announceAudio(audioDict.ru_digit_12_nom);
                                }
                                announceAudio(audioDict.months);
                                announceAudio(audioDict['23cheaper2']);
                            }
                        }
                    }
            
                state: 3months
                    q: * @WholeCheaper3 *
                    script:
                        $.session.intent.stepsCnt++;
                        $.session.optomSelected = 3;
                        if($.session.optom3enoughBalance){
                            $reactions.transition("../Confirm");
                        }else{
                            $reactions.transition("../NotEnoughBalance");
                        }
                    
                state: 6months
                    q: * @WholeCheaper6 *
                    script:
                        $.session.intent.stepsCnt++;
                        $.session.optomSelected = 6;
                        if($.session.optom6enoughBalance){
                            $reactions.transition("../Confirm");
                        }else{
                            $reactions.transition("../NotEnoughBalance");
                        }
                    
                state: 12months
                    q: * @WholeCheaper12 *
                    script:
                        $.session.intent.stepsCnt++;
                        $.session.optomSelected = 12;
                        if($.session.optom12enoughBalance){
                            $reactions.transition("../Confirm");
                        }else{
                            $reactions.transition("../NotEnoughBalance");
                        }

                state: Confirm
                    script:
                        announceAudio(audioDict.EnoughMoney);

                    state: GetOptom
                        q: $commonYes
                        q: $yesForPromo
                        script:
                            $.session.intent.stepsCnt++;
                            setOptom($.session.optomSelected);
                            if(!$.session.setOptomStatus){
                                announceAudio(audioDict.DiscountError);
                                $.session.intent.resultCode = 3;
                                $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                                stopIntent();
                                $reactions.transition('/Transfer/Transfer');
                            }else{
                                announceAudio(audioDict.ConnectedDiscount);
                                $reactions.transition("/WhatElse/WhatElse");
                            }
                            
                    state: Deny
                        q: $commonNo
                        q: $noForPromo
                        script:
                            $.session.intent.stepsCnt++;
                        go!: /WholeCheaper/SMS

                    state: catchAll || noContext = true
                        event: noMatch
                        script:
                            $.session.intent.stepsCnt++;
                            if(countRepeatsInRow() < $injector.noMatchLimit) {
                                $reactions.transition("/WholeCheaper/CheckProducts/CheckOptom/AnnounceAvailablePromo/Confirm");
                            }else{
                                $reactions.transition("/WholeCheaper/SMS");
                            }

                state: NotEnoughBalance
                    script:
                        announceAudio(audioDict.NotEnoughMoney);
                        var paySum = '0';
                        switch($.session.optomSelected){
                            case 3:
                                paySum = $.session.optom3paySum;
                                break;
                            case 6:
                                paySum = $.session.optom6paySum;
                                break;
                            case 12:
                                paySum = $.session.optom12paySum;
                                break;
                            default:
                                break;
                            }
                        announceSum(paySum);
                        announceAudio(audioDict.NotEnoughMoney2);
                        $reactions.transition("/WholeCheaper/SMS");

                state: catchAll || noContext = true
                    event: noMatch
                    script:
                        $.session.intent.stepsCnt++;
                        if(countRepeatsInRow() < $injector.noMatchLimit) {
                            $reactions.transition("/WholeCheaper/CheckProducts/CheckOptom/AnnounceAvailablePromo");
                        }else{
                            $reactions.transition("../ToOperator");
                        }
                
                state: ToOperator
                    script:
                        announceAudio(audioDict.bonus_perevod_na_okc);
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = $.session.intent.resultCode || 1;
                        stopIntent();
                    go!: /Transfer/Transfer
    
    state: NotAuth
        script:
            announceAudio(audioDict.NoAvtorCheaper);
        go!: /WholeCheaper/SMS
        
    state: SMS
        if: $.session.cellPhone
            go!: /WholeCheaper/SMS/Send
        else:
            go!: /WhatElse/WhatElse
            
        state: Send
            script:
                sendSMSbyTemplate('817_WholeCheaperSMS');
                if($.session.SMSstatus){
                    announceAudio(audioDict.DiscountMessage);
                }
                $reactions.transition("/WhatElse/WhatElse");
