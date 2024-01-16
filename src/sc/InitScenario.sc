init:
    $global.TRANSFER_STATE = "/Transfer/Transfer";
    $global.OPERATOR_STATE = "/Init/ToOperator"
    $global.COMMON_QUESTION_STATE = "/CommonQuestion/CommonQuestion";
    $global.HOW_CAN_HELP_STATE = "/Init/HowCanIHelpYou";

    function interceptGlobalMatch(targetState, onState) {
        // в стейте onState отлавливаем match в глобальную тематику
        // и форсированно переходим в стейт targetState
        return bind("selectNLUResult", function() {
            if ($.request.query !== "/start"
                && $.currentState === onState
                && !$.nluResults.selected.clazz.startsWith(onState)) {
                $.temp.matchTargetState = $.nluResults.selected.clazz;
                // если указан относительный путь, склеиваем onState и targetState
                $.temp.targetState = targetState.startsWith("/")
                    ? targetState
                    : onState + "/" + targetState;
            }
        }, onState);
    }

    _.each({
        "/Init/Initialization": "CatchAll",
        "/Init/Initialization/ConfirmCity": "ThemeMatch",
        "/Init/Initialization/ConfirmCity/PleaseSayYourCity": "ThemeMatch",
        "/Init/Initialization/ConfirmCity/RequestStreetAndHouse": "ThemeMatch",
        "/Init/Initialization/RequestFIO": "ThemeMatch",
        "/Init/InitMSHi/RequestFIO": "ThemeMatch",
        "/Init/InitMSHi/RequestStreetAndHouse": "ThemeMatch",
    }, interceptGlobalMatch);

theme: /Init

    state: Initialization
        script:
            $.session.intent = $.session.intent || {};
            if($.session.intent.currentIntent !== "570_Initialization") {
                startIntent("570_Initialization");
            }
            announceAudio(audioDict.Initialization);

        state: CallsOnHold
            q: $callsOnHold
            intent: /610_CallsOnHold
            script:
                $.session.intent.resultCode = 1;
                stopIntent();
            go!: /CallsOnHold/CallsOnHold

        state: InitPodklOborudovanie
            q: [$no] $commonNo || fromState = "/Init/Initialization", onlyThisState = true
            q: ($notClient/$newClient) || fromState = "/Init/Initialization", onlyThisState = true
            script:
                announceAudio(audioDict.InitPodklOborudovanie);
            
            state: ActivateService
                # заменила $activateService на $potentialClientService, фраза из $activateService уже внутри $potentialClientService
                q: $potentialClientService
                intent: /340_PotentialClient/340_PotentialClientService
                script:
                    $.session.intent.resultCode = 15;
                go!: /PotentialClient/Service
            
            state: Device
                # заменила $getRouter на $potentialClientDevice, фразы из $getRouter полностью добавила в $potentialClientDevice
                q: $potentialClientDevice
                intent: /340_PotentialClient/340_PotentialClientDevice
                script:
                    $.session.intent.resultCode = 15;
                go!: /PotentialClient/Device
            
            state: AnotherQuestion
                q: $anotherQuestion || fromState = "/Init/Initialization/InitPodklOborudovanie", onlyThisState = true
                script:
                    $.session.intent.resultCode = 22;
                go!: {{ HOW_CAN_HELP_STATE }}

            state: CatchAll || noContext = true
                q: [$yes] $commonYes || fromState = "/Init/Initialization/InitPodklOborudovanie", onlyThisState = true
                q: [$no] $commonNo || fromState = "/Init/Initialization/InitPodklOborudovanie", onlyThisState = true
                event: noMatch
                script:
                    countRepeatsInRow();
                if: $.session.repeatsInRow < 2
                    script:
                        announceAudio(audioDict.InitPodklOborudovanie_CatchAll);
                elseif: $.session.repeatsInRow < 3
                    script:
                        announceAudio(audioDict.InitPodklOborudovanie_CatchAll_2);
                else:
                    script:
                        announceAudio(audioDict.perevod_na_okc);
                        $.session.intent.resultCode = 1;
                    go!: {{ TRANSFER_STATE }}
            
            state: CallSupport || noContext = true
                q: $agentRequest
                intent: /405_AgentRequest
                if: countAgentRequests($parseTree) < $injector.agentRequestLimit    
                    script:
                        announceAudio(audioDict.InitPodklOborudovanie_operator);
                else:
                    script:
                        announceAudio(audioDict.perevod_na_okc);
                        $.session.intent.resultCode = 1;
                    go!: {{ TRANSFER_STATE }}
        
        state: CallOperator || noContext = true, fromState = "/Init/Initialization", onlyThisState = true
            q: $agentRequest
            intent: /405_AgentRequest
            script:
                if(countAgentRequests($parseTree) < $injector.agentRequestLimit) {
                    announceAudio(audioDict.Initialization_Operator);
                }else{
                    $reactions.transition("/Init/Initialization/ConfirmCity");
                    $.session.agentRequested = true;
                }
        
        state: CatchAll  || noContext = true
            event: noMatch
            script:
                countRepeatsInRow();
            if: $.session.repeatsInRow < 2
                script:
                    announceAudio(audioDict.Initialization_CatchAll);
            else:
                go!: /Init/Initialization/ConfirmCity  
            
            state: AgentFirstStep
                q: $agentRequest
                intent: /405_AgentRequest 
                go!: /Init/Initialization/ConfirmCity
        
        state: InitializationNonOwner
            q: $isNotMyAgreement
            script:
                announceAudio(audioDict.Initialization_nonowner);
                $.session.intent.resultCode = 1;
            go!: /Init/Initialization/ConfirmCity

        state: ConfirmCity
            q: [$yes] $commonYes || fromState = "/Init/Initialization", onlyThisState = true
            q: $yesClient || fromState = "/Init/Initialization", onlyThisState = true
            if: $.session.cityData && $.session.cityData.code && $.session.cityData.name
                script:
                    $temp.cityAudio = citiesAudioMapping[$.session.city]; // определяем аудио для города
                    announceAudio(audioDict[$temp.cityAudio]);
            else:
                script:
                    delete $.session.cityData;
                go!: ./PleaseSayYourCity
            # q: ($yes / lf ) * || toState = "/Init/Initialization/RequestStreetAndHouse"
            # q: ($no / нт / ytn / нетт) * || toState = "./PleaseSayYourCity"
            # q: * $City * || toState = "./PleaseSayYourCity/GetCity"
            
            state: CallsOnHold
                q: $callsOnHold
                intent: /610_CallsOnHold
                script:
                    $.session.intent.resultCode = 1;
                    stopIntent();
                go!: /CallsOnHold/CallsOnHold
            
            state: ThemeMatch || noContext = true
                # перехватываем match в глобальную тематику при помощи interceptGlobalMatch в init-блоке
                script:
                    countRepeatsInRow();
                if: $.session.repeatsInRow < 2
                    script:
                        announceAudio(audioDict.ConfirmCity_subject);
                        $reactions.transition("/Init/Initialization/ConfirmCity");
                        $.session.themeMatched = true;
                else:
                    script:
                        $.session.intent.resultCode = 1;
                    go!: {{ $temp.matchTargetState }}
            
            state: AgentRequest || noContext = true
                q: $agentRequest
                intent: /405_AgentRequest
                if: countAgentRequests($parseTree) < 2
                    script:
                        announceAudio(audioDict.ConfirmCity_subject);
                        $reactions.transition("/Init/Initialization/ConfirmCity");
                        $.session.agentRequested = true;
                else:
                    go!: {{ OPERATOR_STATE }}
                    
            state: LocalNoInput || noContext = true
                event: speechNotRecognized
                # для тестов
                q: NoInput
                intent: /NoInput
                go!: /LocalNoInput/NoInput
            
            state: CatchAll
                event: noMatch
                
                script:
                    countRepeatsInRow();
                if: $.session.repeatsInRow < 2
                    script:
                        announceAudio(audioDict.verno_CatchAll);
                    go!: ..
                else:
                    script:
                        $.session.intent.resultCode = 1;
                    go!: {{ COMMON_QUESTION_STATE }}

            state: PleaseSayYourCity
                q: ($no / [$no] $commonNo / $notClient ) * || fromState = "/Init/Initialization/ConfirmCity", onlyThisState = true
                script:
                    announceAudio(audioDict.pleaseSayYoutCity);

                state: ThemeMatch || noContext = true
                    # перехватываем match в глобальную тематику при помощи interceptGlobalMatch в init-блоке
                    script:
                        countRepeatsInRow();
                    if: $.session.repeatsInRow < 2
                        script:
                            announceAudio(audioDict.pleaseSayYoutCity_ThemeMatch);
                            $.session.themeMatched = true;
                    else:
                        script:
                            $.session.intent.resultCode = 1;
                        go!: {{ $temp.matchTargetState }}
                
                state: AgentRequest || noContext = true
                    q: $agentRequest
                    intent: /405_AgentRequest
                    if: countAgentRequests($parseTree) < $injector.agentRequestLimit
                        script:
                            announceAudio(audioDict.pleaseSayYoutCity_ThemeMatch);
                            $.session.agentRequested = true;
                    else:
                        go!: {{ OPERATOR_STATE }}
                
                state: LocalNoInput || noContext = true
                    event: speechNotRecognized
                    # для тестов
                    q: NoInput
                    intent: /NoInput
                    go!: /LocalNoInput/NoInput
                
                state: CatchAll || noContext = true
                    event: noMatch
                    script:
                        countRepeatsInRow();
                    if: $.session.repeatsInRow < 2
                        script:
                            announceAudio(audioDict.pleaseSayYoutCity_CatchAll);
                    else:
                        script:
                            announceAudio(audioDict.perevod_na_okc);
                            $.session.intent.resultCode = 1;
                        go!: {{ TRANSFER_STATE }}

                state: GetCity
                    q: * $City *
                    q: * $City * || fromState = "/Init/Initialization/ConfirmCity", onlyThisState = true
                    script:
                        $.session.cityData = getCityDataByName($parseTree._City.name, citiesData);
                        if($.session.cityData){
                            $.session.region = $.session.cityData.region || $.session.region;
                        }
                    if: $.session.cityData && $.session.cityData.code && $.session.cityData.name
                        go!: /Init/Initialization/ConfirmCity/RequestStreetAndHouse
                    else:
                        script:
                            $.session.intent.resultCode = 1;
                        go!: {{ HOW_CAN_HELP_STATE }}
                    
            state: RequestStreetAndHouse
                q: ($yes / [$yes] $commonYes / $yesClient) * || fromState = "/Init/Initialization/ConfirmCity", onlyThisState = true
                script:
                    announceAudio(audioDict.requestStreetAndHouse);
        
                state: ThemeMatch || noContext = true
                    # перехватываем match в глобальную тематику при помощи interceptGlobalMatch в init-блоке
                    script:
                        countRepeatsInRow();
                    if: $.session.repeatsInRow < 2
                        script:
                            announceAudio(audioDict.requestStreetAndHouse_ThemeMatch);
                            $.session.themeMatched = true;
                    else:
                        script:
                            $.session.intent.resultCode = 1;
                        go!: {{ $temp.matchTargetState }}
                    
                state: AgentRequest || noContext = true
                    q: $agentRequest
                    intent: /405_AgentRequest
                    if: countAgentRequests($parseTree) < $injector.agentRequestLimit
                        script:
                            announceAudio(audioDict.requestStreetAndHouse_ThemeMatch);
                            $.session.agentRequested = true;
                    else:
                        go!: {{ OPERATOR_STATE }}
                
                state: LocalNoInput || noContext = true
                    event: speechNotRecognized
                    # для тестов
                    q: NoInput
                    intent: /NoInput
                    go!: /LocalNoInput/NoInput
                
                state: CatchAll || noContext = true
                    event: noMatch
                    # исключаем возможность сматчится с этой темой и попадание в ThemeMatch
                    
                    q: $isNotMyAgreement
                    script:
                        countRepeatsInRow();
                    if: $.session.repeatsInRow < 2
                        script:
                            announceAudio(audioDict.requestStreetAndHouse_CatchAll);
                    else:
                        script:
                            $.session.intent.resultCode = 1;
                        go!: {{ COMMON_QUESTION_STATE }}
        
                state: GetAddress
                    q: * [$excludeAddressWord] ($Address/$IncompleteAddress/$NonExplicitStreet) *
                    script:
                        $.session.address = $parseTree._Address || $parseTree._IncompleteAddress || $parseTree._NonExplicitStreet;
                        if (!$parseTree._Address) $.session.isIncompleteAddress = true;
                        authByAddress();
                    if: $.session.userType !== "guest-with-house"
                        if: $.session.isIncompleteAddress && countRepeatsInRow() < 2 && !$.session.localRepeat
                            script:
                                announceAudio(audioDict.requestStreetAndHouse_fullAddress);
                                $.session.localRepeat = true;
                            go: ..
                        else:
                            script:
                                $.session.intent.resultCode = 1;
                            go!: {{ HOW_CAN_HELP_STATE }}
                    else:
                        script:
                            $.session.houseIdAuthToken = $.session.token;
                        go!: /Init/Initialization/RequestFIO
        
        state: RequestFIO
            script:
                announceAudio(audioDict.zapros_FIO);
    
            state: ThemeMatch || noContext = true
                # перехватываем match в глобальную тематику при помощи interceptGlobalMatch в init-блоке
                script:
                    countRepeatsInRow();
                if: $.session.repeatsInRow < 2
                    script:
                        announceAudio(audioDict.RequestFIO_ThemeMatch);
                        $.session.themeMatched = true;
                else:
                    script:
                        $.session.intent.resultCode = 1;
                    go!: {{ $temp.matchTargetState }}
            
            state: AgentRequest || noContext = true
                q: $agentRequest
                intent: /405_AgentRequest
                if: countAgentRequests($parseTree) < $injector.agentRequestLimit
                    script:
                        announceAudio(audioDict.requestStreetAndHouse_ThemeMatch);
                        $.session.agentRequested = true;
                else:
                    go!: {{ OPERATOR_STATE }}
            
            state: LocalNoInput || noContext = true
                event: speechNotRecognized
                # для тестов
                q: NoInput
                intent: /NoInput
                go!: /LocalNoInput/NoInput
            
            state: CatchAll || noContext = true
                event: noMatch
                intentGroup: /NoMatch
                script:
                    countRepeatsInRow();
                if: $.session.repeatsInRow < 2
                    script:
                        announceAudio(audioDict.zapros_FIO_CatchAll);
                else:
                    script:
                        $.session.intent.resultCode = 1;
                    go!: {{ COMMON_QUESTION_STATE }}
                
            state: DontRemember || noContext = true
                q: $dontRemember
                q: $dontRememberWhoseAgreement
                script:
                    countRepeatsInRow();
                if: $.session.repeatsInRow < 2 && !$.session.localDontRememberRepeat
                    script:
                        announceAudio(audioDict.zapros_FIO_dontRemember);
                        $.session.localDontRememberRepeat = true;
                else:
                    script:
                        $.session.intent.resultCode = 1;
                    go!: {{ HOW_CAN_HELP_STATE }}
            
            state: NonOwner || noContext = true
                q: $isNotMyAgreement
                script:
                    announceAudio(audioDict.zapros_FIO_nonowner);
                    $.session.intent.resultCode = 1;
                
            state: NoMiddleName || noContext = true
                q: $haveNoPatronymic $weight<+0.5>
                script:
                    announceAudio(audioDict.zapros_FIO_noMiddleName);
                    $.session.hasNoPatronymic = true;
    
            state: GetFIO
                q: * ($FIO/{$IncompleteFIO * [$haveNoPatronymic]}) *
                script:
                    $.session.fio = $parseTree._FIO;
                    if (!$parseTree._FIO  && $parseTree._IncompleteFIO && !($parseTree._haveNoPatronymic || $.session.hasNoPatronymic)) {
                        $.session.isIncompleteFIO = true;
                    }
                    authByFIOAndHouseId();
                if: $.session.userType === "user"
                    go!: /Init/GetBaseInfo
                else:
                    if: $.session.isIncompleteFIO && countRepeatsInRow() < 2  && !$.session.localRepeat
                        script:
                            announceAudio(audioDict.zapros_FIO_FullName);
                            $.session.localRepeat = true;
                        go: ..
                    else:
                        script:
                            $.session.intent.resultCode = 1;
                        go!: {{ HOW_CAN_HELP_STATE }}

    state: InitMSHi
        script:
            $.session.intent = $.session.intent || {};
            if($.session.intent.currentIntent !== "570_Initialization") {
                startIntent("570_Initialization");
            }
            announceAudio(audioDict.InitMSHi);
        go!: ./RequestFIO

        state: RequestFIO
            script:
                announceAudio(audioDict.zapros_FIO);

            state: ThemeMatch || noContext = true
                # перехватываем match в глобальную тематику при помощи interceptGlobalMatch в init-блоке
                q: $agentRequest
                script:
                    countRepeatsInRow();
                if: $.session.repeatsInRow < 2
                    script:
                        if($parseTree._agentRequest){
                        # приравниваем к 3, чтобы при повторном запросе оператора сделать перевод в стейте OPERATOR_STATE 
                            $.session.agentRequestCount = 3;
                            $.session.agentRequested = true;
                        }
                        announceAudio(audioDict.RequestFIO_ThemeMatch);
                        $.session.themeMatched = true;
                else:
                    script:
                        $.session.intent.resultCode = 1;
                    go!: {{ $parseTree._agentRequest ? OPERATOR_STATE : $temp.matchTargetState }}
            
            state: LocalNoInput || noContext = true
                event: speechNotRecognized
                # для тестов
                q: NoInput
                intent: /NoInput
                go!: /LocalNoInput/NoInput
            
            state: CatchAll || noContext = true
                event: noMatch
                script:
                    countRepeatsInRow();
                if: $.session.repeatsInRow < 2
                    script:
                        announceAudio(audioDict.zapros_FIO_CatchAll);
                else:
                    script:
                        $.session.intent.resultCode = 1;
                    go!: {{ COMMON_QUESTION_STATE }}
            
            state: DontRemember || noContext = true
                q: $dontRemember
                q: $dontRememberWhoseAgreement
                script:
                    countRepeatsInRow();
                if: $.session.repeatsInRow < 2
                    script:
                        announceAudio(audioDict.zapros_FIO_dontRemember);
                else:
                    script:
                        $.session.intent.resultCode = 1;
                    go!: {{ HOW_CAN_HELP_STATE }}
            
            state: NonOwner || noContext = true
                q: $isNotMyAgreement
                script:
                    announceAudio(audioDict.zapros_FIO_nonowner);
                    $.session.intent.resultCode = 1;
            
            state: NoMiddleName || noContext = true
                q: $haveNoPatronymic
                script:
                    announceAudio(audioDict.zapros_FIO_noMiddleName);
                    $.session.hasNoPatronymic = true;

            state: GetFIO
                q: * ($FIO/{$IncompleteFIO * [$haveNoPatronymic]}) *
                script:
                    $.session.fio = $parseTree._FIO;
                    if (!$parseTree._FIO && $parseTree._IncompleteFIO && !($parseTree._haveNoPatronymic || $.session.hasNoPatronymic)) {
                        $.session.isIncompleteFIO = true;
                    }
                    authByFIOAndHouse();
                if: $.session.userType === "user"
                    go!: /Init/GetBaseInfo
                else:
                    if: $.session.isIncompleteFIO && countRepeatsInRow() < 2 && !$.session.localRepeat
                        script:
                            announceAudio(audioDict.zapros_FIO_FullName);
                            $.session.localRepeat = true;
                        go: ..
                    else:
                        if: $.session.authErrorCode === 9
                            go!: /Init/InitMSHi/RequestStreetAndHouse
                        else:
                            script:
                                if($.session.intent){
                                    $.session.intent.resultCode = 1;
                                }
                            go!: {{ HOW_CAN_HELP_STATE }}

        state: RequestStreetAndHouse
            script:
                announceAudio(audioDict.severalAgreements_requestStreetAndHouse);
                
            state: ThemeMatch || noContext = true
                # перехватываем match в глобальную тематику при помощи interceptGlobalMatch в init-блоке
                q: $agentRequest
                script:
                    countRepeatsInRow();
                if: $.session.repeatsInRow < 2
                    script:
                        if($parseTree._agentRequest){
                        # приравниваем к 3, чтобы при повторном запросе оператора сделать перевод в стейте OPERATOR_STATE 
                            $.session.agentRequestCount = 3;
                            $.session.agentRequested = true;
                        }
                        announceAudio(audioDict.severalAgreements_requestStreetAndHouse_ThemeMatch);
                        $.session.themeMatched = true;
                else:
                    script:
                        $.session.intent.resultCode = 1;
                    go!: {{ $parseTree._agentRequest ? OPERATOR_STATE : $temp.matchTargetState }}
            
            state: LocalNoInput || noContext = true
                event: speechNotRecognized
                # для тестов
                q: NoInput
                intent: /NoInput
                go!: /LocalNoInput/NoInput
            
            state: CatchAll || noContext = true
                event: noMatch
                script:
                    countRepeatsInRow();
                if: $.session.repeatsInRow < 2
                    script:
                        announceAudio(audioDict.requestStreetAndHouse_CatchAll);
                else:
                    script:
                        $.session.intent.resultCode = 1;
                    go!: {{ COMMON_QUESTION_STATE }}

            state: GetAddress
                q: * [$excludeAddressWord] ($Address/$IncompleteAddress/$NonExplicitStreet) *
                script:
                    $.session.address = $parseTree._Address || $parseTree._IncompleteAddress || $parseTree._NonExplicitStreet;
                    if (!$parseTree._Address) $.session.isIncompleteAddress = true;
                    authByFIOAndHouse();
                if: $.session.userType == 'user'
                    go!: /Init/GetBaseInfo
                else:
                    if: $.session.isIncompleteAddress && countRepeatsInRow() < 2 && !$.session.localRepeat
                        script:
                            announceAudio(audioDict.requestStreetAndHouse_fullAddress);
                            $.session.localRepeat = true;
                        go: ..
                    else:
                        go!: {{ HOW_CAN_HELP_STATE }}
                        
                        
    state: DIALOG_WELCOME_EVENT_1
        script:
            announceAudio(audioDict.DIALOG_WELCOME_EVENT_1);
        go!: /Init/GetBaseInfo

    state: GetBaseInfo
        script:
            getBaseInfo();
        go!: {{ HOW_CAN_HELP_STATE }}

    state: HowCanIHelpYou
        script:
            if($.session.userType == 'guest'){
                getBaseInfo(); // если у клиент домофонный договор, то будет userType guest, но мы по нему должны получить данные из getBaseInfo
                if($.session.agreementId) $.session.intent.resultCode = 0; // если получили договор, то считаем инит успешным
            }
            announceAudio(audioDict.GetFirstIntent_1);
        
        state: Operator
            q: $agentRequest
            intent: /405_AgentRequest
            script:
                if($.session.agentRequestCount){
                    $.session.agentRequestCount = $.session.agentRequestCount || 0;
                    $.session.agentRequestCount += 1;
                }else{
                    $.session.agentRequestCount = 1;
                }
            go!: /AgentRequest/CheckAuth
        
        state: NoMatch
            event: noMatch
            intent: /NoMatch/Trash
            go!: /NoMatch/GetIntent
            
        state: NoInput || noContext = true
            event: speechNotRecognized
            # для тестов
            q: NoInput
            intent: /NoInput
            go!: /NoInput/NoInput1
            
    state: ToOperator || noContext = true
        if: $.session.agentRequestCount < 3 && !$.session.themeMatched
            script:
                announceAudio(audioDict.Operator);
                $reactions.transition($.session.lastState);
        else:
            script:
                announceAudio(audioDict.perevod_na_okc);
                $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                $.session.intent.resultCode = 6;
                stopIntent(); // завершаем основной интент
                startIntent('/405_AgentRequest');
                $.session.intent.resultCode = 6;
                $reactions.transition('/Transfer/Transfer');