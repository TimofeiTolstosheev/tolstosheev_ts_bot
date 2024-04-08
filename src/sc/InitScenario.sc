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
                $.session.intent.resultCode = 1;
            }
            logState("Инициализация. Договор не найден.");
            announceAudio(audioDict.Initialization);

        state: CallsOnHold
            q: $callsOnHold
            intent: /610_CallsOnHold
            script:
                $.session.intent.resultCode = 1;
                logState("Звонок на удержании");
                stopIntent();
            go!: /CallsOnHold/CallsOnHold
        
        state: ActivateService
            q: $potentialClientService
            intent: /340_PotentialClient/340_PotentialClientService
            script:
                logState("Потенциальный клиент. Подключить услугу.");
                $.session.intent.resultCode = 15;
            go!: /PotentialClient/Service
            
        state: Device
            q: $potentialClientDevice
            intent: /340_PotentialClient/340_PotentialClientDevice
            script:
                logState("Потенциальный клиент. Подключить оборудование.");
                $.session.intent.resultCode = 15;
            go!: /PotentialClient/Device
        
        state: InitPodklOborudovanie
            q: [$no] $commonNo || fromState = "/Init/Initialization", onlyThisState = true
            q: ($notClient/$newClient) * || fromState = "/Init/Initialization", onlyThisState = true
            script:
                logState("Не наш клиент. Уточнение.");
                announceAudio(audioDict.InitPodklOborudovanie);
            
            state: AnotherQuestion
                q: $anotherQuestion * || fromState = "/Init/Initialization/InitPodklOborudovanie", onlyThisState = true
                script:
                    $.session.intent.resultCode = 22;
                    logState("Не наш клиент. Другой вопрос.");
                go!: {{ HOW_CAN_HELP_STATE }}

            state: CatchAll || noContext = true
                q: [$yes] $commonYes || fromState = "/Init/Initialization/InitPodklOborudovanie", onlyThisState = true
                q: [$no] $commonNo || fromState = "/Init/Initialization/InitPodklOborudovanie", onlyThisState = true
                event: noMatch
                intentGroup: /NoMatch
                script:
                    countRepeatsInRow();
                    logState("Не наш клиент. CatchAll.");
                if: $.session.repeatsInRow < 2
                    script:
                        announceAudio(audioDict.InitPodklOborudovanie_CatchAll);
                elseif: $.session.repeatsInRow < 3
                    script:
                        announceAudio(audioDict.InitPodklOborudovanie_CatchAll_2);
                else:
                    script:
                        announceAudio(audioDict.perevod_na_okc);
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = 1;
                    go!: {{ TRANSFER_STATE }}
                    
            state: LocalNoInput || noContext = true
                event: speechNotRecognized
                # для тестов
                q: NoInput
                intent: /NoInput
                script:
                    countRepeatsInRow();
                    $.session.noInputCount = $.session.noInputCount || 0;
                    $.session.noInputCount++;
                    logState("Не наш клиент. NoInput.");
                    if($.session.noInputCount < $injector.noInputLimit) {
                        announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_1);
                        $reactions.transition('/Init/Initialization/InitPodklOborudovanie');
                    }else{
                        announceAudio(audioDict.No_Input3);
                        $.session.intent.resultCode = 1;
                        stopIntent(); // завершаем текущий интент
                        startIntent("380_NoInput"); // дополнительно записываем NoInput
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || "silence";
                        $.session.intent.resultCode = 10;
                        stopIntent();
                        $reactions.transition('/Transfer/Transfer');
                    }
            
            state: CallSupport || noContext = true
                q: $agentRequest
                intent: /405_AgentRequest
                script:
                    logState("Не наш клиент. NoInput.");
                    if(countAgentRequests($parseTree) < $injector.agentRequestLimit){
                        announceAudio(audioDict.InitPodklOborudovanie_operator);
                    }else{
                        announceAudio(audioDict.perevod_na_okc);
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = 1;
                        $reactions.transition(TRANSFER_STATE);
                    }
        
        state: CallOperator || noContext = true, fromState = "/Init/Initialization", onlyThisState = true
            q: $agentRequest
            intent: /405_AgentRequest
            script:
                logState("Инициализация. Запрос оператора.");
                if(countAgentRequests($parseTree) < $injector.agentRequestLimit) {
                    announceAudio(audioDict.Initialization_Operator);
                }else{
                    $reactions.transition("/Init/Initialization/ConfirmCity");
                    $.session.agentRequested = true;
                }
        
        state: CatchAll  || noContext = true
            event: noMatch
            intentGroup: /NoMatch
            script:
                logState("Инициализация. CatchAll.");
                countRepeatsInRow();
            if: $.session.repeatsInRow < 2
                script:
                    announceAudio(audioDict.Initialization_CatchAll);
            else:
                go!: /Init/Initialization/ConfirmCity
        
        state: InitializationNonOwner
            q: $isNotMyAgreement
            script:
                announceAudio(audioDict.Initialization_nonowner);
                logState("Инициализация. Не владелец.");
                $.session.intent.resultCode = 1;
            go!: /Init/Initialization/ConfirmCity

        state: ConfirmCity
            q: ([$yes] $commonYes/[$yes] ваш)  || fromState = "/Init/Initialization", onlyThisState = true
            q: $yesClient * || fromState = "/Init/Initialization", onlyThisState = true
            script:
                logState("Инициализация. Подтверждение города.");
                if($.session.cityData && $.session.cityData.code && $.session.cityData.name){
                    $temp.cityAudio = citiesAudioMapping[$.session.city]; // определяем аудио для города
                    announceAudio(audioDict[$temp.cityAudio]);
                }else{
                    delete $.session.cityData;
                    $reactions.transition("./PleaseSayYourCity");
                }
            
            state: CallsOnHold
                q: $callsOnHold
                intent: /610_CallsOnHold
                script:
                    logState("Инициализация. Звонок на удержании.");
                    $.session.intent.resultCode = 1;
                    stopIntent();
                go!: /CallsOnHold/CallsOnHold
            
            state: ThemeMatch || noContext = true
                # перехватываем match в глобальную тематику при помощи interceptGlobalMatch в init-блоке
                script:
                    countRepeatsInRow();
                    logState("Инициализация. ThemeMatch.");
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
                script:
                    logState("Инициализация. Запрос оператора.");
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
                script:
                    countRepeatsInRow();
                    $.session.noInputCount = $.session.noInputCount || 0;
                    $.session.noInputCount++;
                    logState("Инициализация. NoInput.");
                    if($.session.noInputCount < $injector.noInputLimit) {
                        announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_1);
                        $reactions.transition('/Init/Initialization/ConfirmCity');
                    }else{
                        announceAudio(audioDict.No_Input3);
                        $.session.intent.resultCode = 1;
                        stopIntent(); // завершаем текущий интент
                        startIntent("380_NoInput"); // дополнительно записываем NoInput
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || "silence";
                        $.session.intent.resultCode = 10;
                        stopIntent();
                        $reactions.transition('/Transfer/Transfer');
                    }
            
            state: CatchAll
                event: noMatch
                intentGroup: /NoMatch
                script:
                    countRepeatsInRow();
                    logState("Инициализация. CatchAll.");
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
                    logState("Инициализация. Уточнение города.");
                    announceAudio(audioDict.pleaseSayYoutCity);

                state: ThemeMatch || noContext = true
                    # перехватываем match в глобальную тематику при помощи interceptGlobalMatch в init-блоке
                    script:
                        countRepeatsInRow();
                        logState("Инициализация. ThemeMatch.");
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
                    script:
                        logState("Инициализация. Запрос оператора.");
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
                    script:
                        countRepeatsInRow();
                        $.session.noInputCount = $.session.noInputCount || 0;
                        $.session.noInputCount++;
                        logState("Инициализация. NoInput.");
                        if($.session.noInputCount < $injector.noInputLimit) {
                            announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_1);
                            $reactions.transition('/Init/Initialization/ConfirmCity/PleaseSayYourCity');
                        }else{
                            announceAudio(audioDict.No_Input3);
                            stopIntent(); // завершаем текущий интент
                            startIntent("380_NoInput"); // дополнительно записываем NoInput
                            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || "silence";
                            $.session.intent.resultCode = 10;
                            stopIntent();
                            $reactions.transition('/Transfer/Transfer');
                        }
                
                state: CatchAll || noContext = true
                    event: noMatch
                    intentGroup: /NoMatch
                    script:
                        countRepeatsInRow();
                        logState("Инициализация. CatchAll.");
                    if: $.session.repeatsInRow < 2
                        script:
                            announceAudio(audioDict.pleaseSayYoutCity_CatchAll);
                    else:
                        script:
                            announceAudio(audioDict.perevod_na_okc);
                            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                            $.session.intent.resultCode = 1;
                        go!: {{ TRANSFER_STATE }}

                state: GetCity
                    q: * $City *
                    q: * $City * || fromState = "/Init/Initialization/ConfirmCity", onlyThisState = true
                    script:
                        logState("Инициализация. Проверка города.");
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
                    logState("Инициализация. Уточнение адреса.");
                    announceAudio(audioDict.requestStreetAndHouse);
        
                state: ThemeMatch || noContext = true
                    # перехватываем match в глобальную тематику при помощи interceptGlobalMatch в init-блоке
                    script:
                        countRepeatsInRow();
                        logState("Инициализация. ThemeMatch.");
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
                    script:
                        logState("Инициализация. Запрос оператора.");
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
                    script:
                        countRepeatsInRow();
                        $.session.noInputCount = $.session.noInputCount || 0;
                        $.session.noInputCount++;
                        logState("Инициализация. NoInput.");
                        if($.session.noInputCount < $injector.noInputLimit) {
                            announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_1);
                            $reactions.transition('/Init/Initialization/ConfirmCity/RequestStreetAndHouse');
                        }else{
                            announceAudio(audioDict.No_Input3);
                            stopIntent(); // завершаем текущий интент
                            startIntent("380_NoInput"); // дополнительно записываем NoInput
                            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || "silence";
                            $.session.intent.resultCode = 10;
                            stopIntent();
                            $reactions.transition('/Transfer/Transfer');
                        }
                
                state: CatchAll || noContext = true
                    event: noMatch
                    intentGroup: /NoMatch
                    q: * подожди* *
                    # исключаем возможность сматчится с этой темой и попадание в ThemeMatch
                    
                    q: $isNotMyAgreement
                    script:
                        countRepeatsInRow();
                        logState("Инициализация. CatchAll.");
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
                        logState("Инициализация. Авторизация по адресу.");
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
                logState("Инициализация. Уточнение ФИО.");
                announceAudio(audioDict.zapros_FIO);
    
            state: ThemeMatch || noContext = true
                # перехватываем match в глобальную тематику при помощи interceptGlobalMatch в init-блоке
                script:
                    countRepeatsInRow();
                    logState("Инициализация. ThemeMatch.");
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
                script:
                    logState("Инициализация. Запрос оператора.");
                    if(countAgentRequests($parseTree) < $injector.agentRequestLimit){
                        announceAudio(audioDict.RequestFIO_ThemeMatch);
                        $.session.agentRequested = true;
                    }else{
                        $.session.intent.resultCode = 1;
                        $reactions.transition(OPERATOR_STATE);
                    }
            
            state: LocalNoInput || noContext = true
                event: speechNotRecognized
                # для тестов
                q: NoInput
                intent: /NoInput
                script:
                    countRepeatsInRow();
                    $.session.noInputCount = $.session.noInputCount || 0;
                    $.session.noInputCount++;
                    logState("Инициализация. NoInput.");
                    if($.session.noInputCount < $injector.noInputLimit) {
                        announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_1);
                        $reactions.transition('/Init/Initialization/RequestFIO');
                    }else{
                        announceAudio(audioDict.No_Input3);
                        $.session.intent.resultCode = 1;
                        stopIntent(); // завершаем текущий интент
                        startIntent("380_NoInput"); // дополнительно записываем NoInput
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || "silence";
                        $.session.intent.resultCode = 10;
                        stopIntent();
                        $reactions.transition('/Transfer/Transfer');
                    }
            
            state: CatchAll || noContext = true
                event: noMatch
                intentGroup: /NoMatch
                q: * подожди* *
                script:
                    countRepeatsInRow();
                    logState("Инициализация. CatchAll.");
                if: $.session.repeatsInRow < 2
                    script:
                        announceAudio(audioDict.zapros_FIO_CatchAll);
                else:
                    script:
                        $.session.intent.resultCode = 1;
                    go!: {{ COMMON_QUESTION_STATE }}
                
            state: ActivateService
                q: $potentialClientService
                intent: /340_PotentialClient/340_PotentialClientService
                q: * договор [еще/[еще] ни на кого/пока] (не (оформлен/оформили/заключен/заключили/заключали)) *
                q: * (не (оформлен/оформили/заключен/заключили/заключали)) [еще/[еще] ни на кого/пока] договор *
                q: * купили новую квартиру
                script:
                    logState("Потенциальный клиент. Подключить услугу.");
                    $.session.intent.resultCode = 15;
                go!: /PotentialClient/Service
            
            state: DontRemember || noContext = true
                q: $dontRemember
                q: $dontRememberWhoseAgreement
                script:
                    countRepeatsInRow();
                    logState("Инициализация. Не помнит ФИО.");
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
                    logState("Инициализация. Не владелец.");
                    announceAudio(audioDict.zapros_FIO_nonowner);
                    $.session.intent.resultCode = 1;
                
            state: NoMiddleName || noContext = true
                q: $haveNoPatronymic $weight<+0.5>
                script:
                    logState("Инициализация. Уточнение отчества.");
                    announceAudio(audioDict.zapros_FIO_noMiddleName);
                    $.session.hasNoPatronymic = true;
                    
            state: LegalEntity
                q: $legalEntity
                q: * (ооо/зао/оао/ао/@Company/предприятие/ип/и пэ) $regexp_i<([А-Яа-я]+)> [$regexp_i<([А-Яа-я]+)>] [+] *
                q: [это/(я/мы) [из]] организаци*
                q: { договор оформлен (на организацию) }
                intent: /580_LegalEntity
                script:
                    logState("Инициализация. Юридическое лицо.");
                    $.session.intent.resultCode = 1;
                go!: /LegalEntity/LegalEntity
    
            state: GetFIO
                q: * ($FIO/{$IncompleteFIO * [$haveNoPatronymic]}) *
                script:
                    logState("Инициализация. Авторизация по ФИО.");
                    $.session.fio = $parseTree._FIO || $parseTree._IncompleteFIO;
                    if (!$parseTree._FIO  && $parseTree._IncompleteFIO && !($parseTree._haveNoPatronymic || $.session.hasNoPatronymic)) {
                        $.session.isIncompleteFIO = true;
                    }
                    if($parseTree._IncompleteFIO){
                        $.session.fio = capitalizeFirstLetters($parseTree.text);
                    }
                    authByFIOAndHouseId();
                if: $.session.userType === "user"
                    go!: /Init/GetBaseInfo
                else:
                    if: $.session.isIncompleteFIO && countRepeatsInRow() < 2  && !$.session.localRepeat
                        script:
                            announceAudio(audioDict.zapros_FIO_FullName);
                            logState("Инициализация. Уточнение полных ФИО.");
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
            logState("Инициализация. Множественная связка.");
            announceAudio(audioDict.InitMSHi);
        go!: ./RequestFIO

        state: RequestFIO
            script:
                logState("Инициализация. Уточнение ФИО.");
                announceAudio(audioDict.zapros_FIO);

            state: ThemeMatch || noContext = true
                # перехватываем match в глобальную тематику при помощи interceptGlobalMatch в init-блоке
                script:
                    countRepeatsInRow();
                    logState("Инициализация. ThemeMatch.");
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
                script:
                    logState("Инициализация. ThemeMatch.");
                    if(countAgentRequests($parseTree) < $injector.agentRequestLimit){
                        announceAudio(audioDict.RequestFIO_ThemeMatch);
                        // приравниваем к 3, чтобы при повторном запросе оператора сделать перевод в стейт OPERATOR_STATE
                        $.session.agentRequestCount = 3;
                        $.session.agentRequested = true;
                    }else{
                        $.session.intent.resultCode = 1;
                        $reactions.transition(OPERATOR_STATE);
                    }
            
            state: LocalNoInput || noContext = true
                event: speechNotRecognized
                # для тестов
                q: NoInput
                intent: /NoInput
                script:
                    countRepeatsInRow();
                    $.session.noInputCount = $.session.noInputCount || 0;
                    $.session.noInputCount++;
                    logState("Инициализация. NoInput.");
                    if($.session.noInputCount < $injector.noInputLimit) {
                        announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_1);
                        $reactions.transition('/Init/InitMSHi/RequestFIO');
                    }else{
                        announceAudio(audioDict.No_Input3);
                        $.session.intent.resultCode = 1;
                        stopIntent(); // завершаем текущий интент
                        startIntent("380_NoInput"); // дополнительно записываем NoInput
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || "silence";
                        $.session.intent.resultCode = 10;
                        stopIntent();
                        $reactions.transition('/Transfer/Transfer');
                    }
            
            state: CatchAll || noContext = true
                event: noMatch
                intentGroup: /NoMatch
                q: * подожди* *
                script:
                    countRepeatsInRow();
                    logState("Инициализация. CatchAll.");
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
                    logState("Инициализация. Не помнит ФИО.");
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
                    logState("Инициализация. Не владелец.");
                    announceAudio(audioDict.zapros_FIO_nonowner);
                    $.session.intent.resultCode = 1;
            
            state: NoMiddleName || noContext = true
                q: $haveNoPatronymic
                script:
                    logState("Инициализация. Уточнение отчества.");
                    announceAudio(audioDict.zapros_FIO_noMiddleName);
                    $.session.hasNoPatronymic = true;

            state: LegalEntity
                q: $legalEntity
                q: * (ооо/зао/оао/ао/@Company/предприятие/ип/и пэ) $regexp_i<([А-Яа-я]+)> [$regexp_i<([А-Яа-я]+)>] [+] *
                q: [это/(я/мы) [из]] организаци*
                q: { договор оформлен (на организацию) }
                intent: /580_LegalEntity
                script:
                    logState("Инициализация. Юридическое лицо.");
                    $.session.intent.resultCode = 1;
                go!: /LegalEntity/LegalEntity
            
            state: GetFIO
                q: * ($FIO/{$IncompleteFIO * [$haveNoPatronymic]}) *
                script:
                    logState("Инициализация. Авторизация по ФИО.");
                    $.session.fio = $parseTree._FIO || $parseTree._IncompleteFIO;
                    if (!$parseTree._FIO && $parseTree._IncompleteFIO && !($parseTree._haveNoPatronymic || $.session.hasNoPatronymic)) {
                        $.session.isIncompleteFIO = true;
                    }
                    if(!$parseTree._FIO){
                        $.session.fio = capitalizeFirstLetters($parseTree.text);
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
                logState("Инициализация. Уточнение адреса.");
                announceAudio(audioDict.severalAgreements_requestStreetAndHouse);
                
            state: ThemeMatch || noContext = true
                # перехватываем match в глобальную тематику при помощи interceptGlobalMatch в init-блоке
                script:
                    countRepeatsInRow();
                    logState("Инициализация. ThemeMatch.");
                if: $.session.repeatsInRow < 2
                    script:
                        announceAudio(audioDict.severalAgreements_requestStreetAndHouse_ThemeMatch);
                        $.session.themeMatched = true;
                else:
                    script:
                        $.session.intent.resultCode = 1;
                    go!: {{ $temp.matchTargetState }}
            
            state: AgentRequest || noContext = true
                q: $agentRequest
                intent: /405_AgentRequest
                script:
                    logState("Инициализация. Запрос оператора.");
                    if(countAgentRequests($parseTree) < $injector.agentRequestLimit){
                        announceAudio(audioDict.severalAgreements_requestStreetAndHouse_ThemeMatch);
                        // приравниваем к 3, чтобы при повторном запросе оператора сделать перевод в стейте OPERATOR_STATE
                        $.session.agentRequestCount = 3;
                        $.session.agentRequested = true;
                    }else{
                        $.session.intent.resultCode = 1;
                        $reactions.transition(OPERATOR_STATE);
                    }
            
            state: LocalNoInput || noContext = true
                event: speechNotRecognized
                # для тестов
                q: NoInput
                intent: /NoInput
                script:
                    countRepeatsInRow();
                    $.session.noInputCount = $.session.noInputCount || 0;
                    $.session.noInputCount++;
                    logState("Инициализация. NoInput.");
                    if($.session.noInputCount < $injector.noInputLimit) {
                        announceAudio(audioDict.DIALOG_NO_MATCH_EVENT_1);
                        $reactions.transition('/Init/InitMSHi/RequestStreetAndHouse');
                    }else{
                        announceAudio(audioDict.No_Input3);
                        stopIntent(); // завершаем текущий интент
                        startIntent("380_NoInput"); // дополнительно записываем NoInput
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || "silence";
                        $.session.intent.resultCode = 10;
                        stopIntent();
                        $reactions.transition('/Transfer/Transfer');
                    }
            
            state: CatchAll || noContext = true
                event: noMatch
                intentGroup: /NoMatch
                q: * подожди* *
                script:
                    countRepeatsInRow();
                    logState("Инициализация. CatchAll.");
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
                    logState("Инициализация. Авторизация по адресу.");
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
                        script:
                            $.session.intent.resultCode = 1;
                        go!: {{ HOW_CAN_HELP_STATE }}
                        
                        
    state: DIALOG_WELCOME_EVENT_1
        script:
            logState("Начало");
            announceAudio(audioDict.DIALOG_WELCOME_EVENT_1);
        go!: /Init/GetBaseInfo

    state: GetBaseInfo
        script:
            getBaseInfo();
        go!: {{ HOW_CAN_HELP_STATE }}

    state: HowCanIHelpYou
        script:
            logState($.session.userType == 'user' ? "Чем помочь. Авторизован." : "Чем помочь. Неавторизован.");
            if($.session.userType == 'guest'){
                getBaseInfo(); // если у клиент домофонный договор, то будет userType guest, но мы по нему должны получить данные из getBaseInfo
                if($.session.agreementId) $.session.intent.resultCode = 0; // если получили договор, то считаем инит успешным
            }
            if($.session.agreementId && $.session.intent) {
                $.session.intent.resultCode = 0; // если получили договор, то считаем инит успешным
            }
            # TODO ИЩИ ТУТ
            announceAudio(audioDict.GetFirstIntent_1);
        
        state: Operator
            q: $agentRequest
            intent: /405_AgentRequest
            script:
                logState("Запрос оператора");
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
            script:
                countRepeatsInRow();
                logState("Намерение не определено");
            if: $.session.repeatsInRow < 2
                go!: /NoMatch/GetIntent
            else:
                go!: /NoMatch/GetIntent/GetIntent2
            
        state: NoInput || noContext = true
            event: speechNotRecognized
            # для тестов
            q: NoInput
            intent: /NoInput
            script:
                logState("NoInput");
            go!: /NoInput/NoInput1
            
    state: ToOperator || noContext = true
        script:
            logState("Запрос оператора");
            if($.session.agentRequestCount < 3 && !$.session.themeMatched && !$.session.agentRequested){
                announceAudio(audioDict.Operator);
                $reactions.transition($.session.lastState);
            }else{
                announceAudio(audioDict.perevod_na_okc);
                $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                $.session.intent.resultCode = 6;
                stopIntent(); // завершаем основной интент
                startIntent('/405_AgentRequest');
                $.session.intent.resultCode = 6;
                $reactions.transition('/Transfer/Transfer');
            }
