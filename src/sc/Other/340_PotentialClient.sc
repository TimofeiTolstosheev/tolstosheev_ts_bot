theme: /PotentialClient
    state: Service
        q!: $potentialClientService
        intent!: /340_PotentialClient/340_PotentialClientService
        script:
            startIntent('/340_PotentialClientService');
        go!: ./CheckAuth
        
        state: CheckAuth
            if: $.session.userType != 'user' && $.session.authErrorCode != 9
                # Не найдено ни одного договора
                go!: /PotentialClient/PhysicalOrLegal
            else:
                script:
                    announceAudio(audioDict.UtochneniePK);
                
            state: AuthServiceToOperator
                q: $commonYes
                q: $yesForNewService
                script:
                    $.session.intent.stepsCnt++;
                    announceAudio(audioDict.OCPOmilia);
                    $.session.intent.resultCode = 12;
                    $.session.callerInput = 'p_client';
                go!: /Transfer/Transfer
                
            state: ToOperator
                q: $commonNo
                q: $noForNewService
                script:
                    $.session.intent.stepsCnt++;
                    announceAudio(audioDict.OborudovaniePK);
                    $.session.intent.resultCode = 6;
                    $.session.callerInput = 'operator';
                    stopIntent();
                go!: /Transfer/Transfer
            
            state: CatchAll || noContext = true
                intent: /NoMatch/ObsceneLanguage
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        $reactions.transition('/PotentialClient/Service/CheckAuth');
                    }else{
                        $reactions.transition('/PotentialClient/Service/CheckAuth/ToOperator');
                    }
    
    state: Device
        q!: $potentialClientDevice
        intent!: /340_PotentialClient/340_PotentialClientDevice
        script:
            startIntent('/340_PotentialClientDevice');
        if: $.session.userType != 'user' && $.session.authErrorCode != 9
            # Не найдено ни одного договора
            go!: /PotentialClient/PhysicalOrLegal
        else:
            script:
                announceAudio(audioDict.OborudovaniePK);
                $.session.intent.resultCode = 6;
                $.session.callerInput = 'operator';
                stopIntent();
            go!: /Transfer/Transfer
        
    state: PhysicalOrLegal
        script:
            announceAudio(audioDict.fizik_yurik);
        
        state: PhysicalToOperator
            q: * @PhysicalEntity *
            q: [$oneWord] физическ* [$oneWord]
            script:
                $.session.intent.stepsCnt++;
                announceAudio(audioDict.OCPOmilia);
                $.session.intent.resultCode = 12;
                $.session.callerInput = 'p_client';
            go!: /Transfer/Transfer
        
        state: LegalToOperator
            q: * @LegalEntity * 
            q: (юридическ*/как юридическ*/как юридическ* лицо/как юридическ* $oneWord)
            script:
                $.session.intent.stepsCnt++;
                announceAudio(audioDict.yurikForFurther);
                $.session.intent.resultCode = 11;
                $.session.callerInput = 'B2BFL';
            go!: /Transfer/Transfer
        
        state: ToOperator
            q: $agentRequest
            intent: /405_AgentRequest
            script:
                $.session.intent.stepsCnt++;
                if(countRepeatsInRow() < $injector.noMatchLimit) {
                    $.session.agentRequested = true;
                    $.session.intent.resultCode = 12;
                    // запишем интент "Запрос оператора" с кодом 12
                    try { 
                        var intentId = getIntentParam("405_AgentRequest", "intentCode");
                    } catch (e) { 
                        var intenId = -1;
                    }
                    logIntent(intentId, getNow(), getNow(), 1, 12, '', $.session.stateLog || $.session.prevStateLog);
                    $reactions.transition('/PotentialClient/PhysicalOrLegal');
                }else{
                    $.session.callerInput = 'p_client';
                    stopIntent();
                    announceAudio(audioDict.OCPOmilia);
                    $reactions.transition("/Transfer/Transfer");
                }
                    
                    
        state: CatchAll || noContext = true
            event: noMatch
            script:
                $.session.intent.stepsCnt++;
                if(countRepeatsInRow() < $injector.noMatchLimit) {
                    $reactions.transition('/PotentialClient/PhysicalOrLegal');
                }else{
                    announceAudio(audioDict.OCPOmilia);
                    $.session.intent.resultCode = 12;
                    $.session.callerInput = 'p_client';
                    $reactions.transition('/Transfer/Transfer');
                }
