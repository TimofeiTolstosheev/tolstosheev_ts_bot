theme: /CommonQuestion
  
    state: CommonQuestion
        q!: $commonQuestion
        intent!: /650_CommonQuestion
        script:
            startIntent('/650_CommonQuestion');
        go!: ./WhatCanIdo
        
        state: WhatCanIdo
            script:
                $.session.intent.stepsCnt++;
                announceAudio(audioDict.whatcanido);
                announceAudio(audioDict.whatcanido2);
                
            state: Fin
                q: $financeQuestion
                intent: /5_FinanceQuestion
                go!: /FinanceQuestion/FinanceQuestion
            
            state: Tech
                q: $commonTechnicalProblems
                intent: /250_CommonTechnicalProblems
                go!: /CommonTechnicalProblems/StartIntent
                
            state: Both
                q: и то и [то/другое]
                q: и та и та
                q: $regexp<об[ае]>
                q: {(и финансовая) (и техническая)}
                q: * (@DontWork $regexp<услуг[аи]>) [и] (@Need консультац*) *
                q: * (@Need консультац*) [и] (@DontWork $regexp<услуг[аи]>) *
                go!: /CommonTechnicalProblems/StartIntent
            
            state: СatchAll || noContext = true
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        $reactions.transition('/CommonQuestion/CommonQuestion/WhatCanIdo');
                    }else{
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = 6;
                        stopIntent();
                        announceAudio(audioDict.toAgentRequest);
                        $reactions.transition('/Transfer/Transfer');
                    }
