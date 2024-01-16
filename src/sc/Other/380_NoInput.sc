 theme: /NoInput
    state: NoInput1
        script:
            startIntent('/380_NoInput');
            announceAudio(audioDict.No_Input1);
            
        state: NoInput2
            event: speechNotRecognized
            # для тестов
            q: NoInput
            intent: /NoInput
            script:
                announceAudio(audioDict.No_Input2);
            
            state: NoInput3
                event: speechNotRecognized
                # для тестов
                q: NoInput
                intent: /NoInput
                script:
                    announceAudio(audioDict.No_Input3);
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    $.session.intent.resultCode = 10;
                    stopIntent();
                go!: /Transfer/Transfer
            
            state: NoMatch
                event: noMatch
                go!: /NoMatch/GetIntent
        
        state: AgentRequest
            q: $agentRequest
            intent: /405_AgentRequest
            go!: /AgentRequest/CheckAuth
        
        state: NoMatch
            event: noMatch
            go!: /NoMatch/GetIntent
