theme: /UserMoving
    state: UserMoving
        q!: $userMoving 
        intent!: /430_UserMoving
        script:
            startIntent('430_UserMoving');
        go!: /UserMoving/UserMoving/AskNewAddress
        
        state: AskNewAddress || modal = true
            script:
                announceAudio(audioDict.PereezdAdres);
        
            state: CheckCity
                q: * $City *
                script:
                    if($parseTree._City){
                        $.session.cityData = getCityDataByName($parseTree._City.name, citiesData);
                        if($.session.cityData){
                            $.session.regionFromParseTree = $.session.cityData.region;
                        }
                        if($.session.regionFromParseTree != $.session.region){
                            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            $.session.intent.resultCode = 6;
                            stopIntent();
                            announceAudio(audioDict.transferToAgentForFurther);
                            $reactions.transition('/Transfer/Transfer');
                        }else{
                            $reactions.transition("/UserMoving/UserMoving/AskNewAddress/CheckAddress");
                        }
                    }
                        
            state: CheckAddress
                q: * [$excludeAddressWord] ($Address/$IncompleteAddress/$NonExplicitStreet) *
                script:
                    $.session.address = $parseTree._Address || $parseTree._IncompleteAddress || $parseTree._NonExplicitStreet;
                    if (!$parseTree._Address) $.session.isIncompleteAddress = true;
                    
                    if($.session.address){
                        checkAddress();
                    }
                    if(!$.session.checkAddressError && $.session.canConnect){
                        $reactions.transition("/UserMoving/UserMoving/AskNewAddress/CheckAddress/CanConnect"); 
                    }else{
                        $reactions.transition("/UserMoving/UserMoving/AskNewAddress/CheckAddress/CannotConnect");
                    }
                    
                state: CanConnect
                    script:
                        announceAudio(audioDict.HouseConnected);
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = 6;
                        stopIntent();
                    go!: /Transfer/Transfer
                    
                state: CannotConnect
                    script:
                        announceAudio(audioDict.HouseNotConnected);
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = 6;
                        stopIntent();
                    go!: /Transfer/Transfer
                    
            state: AgentRequest || noContext = true
                q: $agentRequest
                intent: /405_AgentRequest
                script:
                    if($.session.repeatsInRow < $injector.agentRequestLimit && !$.session.agentRequested) {
                        $.session.agentRequested = true;
                        $reactions.transition("/UserMoving/UserMoving/AskNewAddress");
                    }else{
                        announceAudio(audioDict.perevod_na_okc);
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = 6;
                        stopIntent(); // завершаем основной интент
                        startIntent('/405_AgentRequest');
                        $.session.intent.resultCode = 6;
                        $reactions.transition('/Transfer/Transfer');
                    }
            
            state: СatchAll || noContext = true
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        announceAudio(audioDict.PereezdAdres);
                    }else{
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = 6;
                        stopIntent();
                        announceAudio(audioDict.transferToAgentForFurther);
                        $reactions.transition('/Transfer/Transfer');
                    }
