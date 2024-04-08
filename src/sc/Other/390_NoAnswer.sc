theme: /NoAnswer
   
    state: NoAnswer || noContext = true
        q!: $noAnswer
        intent!: /390_NoAnswer
        script:
            announceAudio(audioDict.GetFirstIntent_greet);
        go: /NoAnswer/NoAnswer
        
        state: NoAnswer2
            q: $noAnswer
            intent: /390_NoAnswer
            script:
                startIntent('/390_NoAnswer');
                announceAudio(audioDict.DIALOG_No_Input_Event_2);
            
            state: NoAnswer3
                q: $noAnswer
                intent: /390_NoAnswer
                script:
                    announceAudio(audioDict.No_answer4);
                
                state: NoAnswer4
                    q: $noAnswer
                    intent: /390_NoAnswer
                    script:
                        announceAudio(audioDict.No_answer3);
                        
                    state: NoAnswer5
                        q: $noAnswer
                        intent: /390_NoAnswer
                        script:
                            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            $.session.intent.resultCode = 6;
                            stopIntent();
                            announceAudio(audioDict.transferToAgentForFurther);
                        go!: /Transfer/Transfer
       
        state: СatchAll || noContext = true
            event: noMatch
            script:
                if(countRepeatsInRow() < $injector.noMatchLimit) {
                    $reactions.transition('/NoMatch/GetIntent');
                }else{
                    announceAudio(audioDict.transferToAgentForFurther);
                    if(!$.session.intent){
                        startIntent('/390_NoAnswer');
                    }
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $.session.intent.resultCode = 6;
                    stopIntent();
                    $reactions.transition('/Transfer/Transfer');
                }