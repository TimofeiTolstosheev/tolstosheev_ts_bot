theme: /LegalEntity
  
    state: LegalEntity
        q!: $legalEntity
        intent!: /580_LegalEntity
        script:
            startIntent('580_LegalEntity');
        go!: /LegalEntity/LegalEntity/AreYouLegalEntity
            
        state: AreYouLegalEntity
            script:
                announceAudio(audioDict.UEntity);
            
            state: LegalYes
                q: * $commonYes *
                q: * @LegalEntity *
                script:
                    $.session.intent.stepsCnt++;
                go!: /LegalEntity/LegalEntity/AreYouLegalEntity/LegalYes/YesLegalEntity
                
                state: YesLegalEntity
                    script:
                        $.session.intent.stepsCnt++;
                        announceAudio(audioDict.GoEntity);
                        $.session.callerInput = 'b2bivr';
                        $.session.intent.resultCode = 11;
                    go!: /Transfer/Transfer
                
            state: LegalNo || noContext = true
                q: * $commonNo *
                q: * @PhysicalEntity *
                script:
                    $.session.intent.stepsCnt++;
                go!: /LegalEntity/LegalEntity/AreYouLegalEntity/LegalNo/NoLegalEntity
                
                state: NoLegalEntity || noContext = true
                    script:
                        $.session.repeatsInRow = $.session.repeatsInRow || 0;
                        $.session.repeatsInRow += 1;
                        if($.session.repeatsInRow < 3){
                            announceAudio(audioDict.ForgiveMe);
                        }else{
                            announceAudio(audioDict.perevod_na_okc);
                            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            $.session.intent.resultCode = 4;
                            stopIntent();
                            $reactions.transition('/Transfer/Transfer');
                        }
            
                    state: СatchAll
                        event: noMatch
                        script:
                            $.session.intent.stepsCnt++;
                            $.session.repeatsInRow = $.session.repeatsInRow || 0;
                            $.session.repeatsInRow += 1;
                        go!: /NoMatch/GetIntent
                        
            state: СatchAll || noContext = true
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        $reactions.transition('/LegalEntity/LegalEntity/AreYouLegalEntity');
                    }else{
                        announceAudio(audioDict.perevod_na_okc);
                        $.session.intent.resultCode = 6;
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        stopIntent();
                        $reactions.transition('/Transfer/Transfer');
                    }
