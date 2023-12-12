theme: /AgentRequest
    state: AgentRequestFromStart
        # сюда переводим, если запрос оператора был первым интентом
        script:
            startIntent('405_AgentRequest');
            $.session.agentRequestCount = $.session.agentRequestCount || 0;
            $.session.agentRequestCount++;
        a: Начинаю решать ваш вопрос сам, если не смогу, то сразу позову на помощь своих коллег.
        buttons:
            "Не работает интернет" -> /PageNotOpenLowSpeedNoLinkPPoE/NoLink
            "Баланс и детализация" -> /BalanceInquiry/CheckAuth
            "Низкая скорость" -> /PageNotOpenLowSpeedNoLinkPPoE/NoLink
            "Проблемы с ТВ" -> /TVChannelTVqualityProblem/TVchannelProblem
            "Консультация по тарифам" -> /OffersHelpIntent/Консультация по тарифным планам
            "Подключить услугу" -> /ManageServicePacket/Управление контентом
        q: $agentRequest || toState = "/AgentRequest/SelectTheme"
        intent: /405_AgentRequest || toState = "/AgentRequest/SelectTheme"
        intent: OtherIntent || toState = "/AgentRequest/SelectTheme"
        q: $otherIntent || toState = "/AgentRequest/SelectTheme"
        event: noMatch || toState = "/AgentRequest/SelectTheme"
        
    state: AgentRequestFromIntent
        # сюда переводим, если запрос оператора был из другого сценария
        q!: $agentRequest
        intent!: /AgentRequest
        script:
            startIntent('405_AgentRequest');
        go!: /AgentRequest/SelectTheme
            
    state: SelectTheme
        intent!: OtherIntent
        q!: $otherIntent
        script:
            $.session.agentRequestCount = $.session.agentRequestCount || 0;
            $.session.agentRequestCount++;
            if($.session.agentRequestCount > $.injector.agentRequestLimit){
                $reactions.transition("/Transfer/Перевод чата");
            }
        a: Хочу позвать человека, но нужно выбрать подходящего. Если сложность с интернетом, ТВ либо нужна настройка оборудования, то выберите "Технический вопрос". А если вопрос про баланс, изменение тарифа или операции с договором, то "Консультация".
        buttons:
            "Консультация" -> /Consultation/Консультация
            "Технический вопрос" -> /TechQuestion/Общие тех вопросы
        intent: /AgentRequest || toState = "/AgentRequest/SelectTheme/SelectTheme2"
        intent: /250_TechQuestion || toState = "/TechQuestion/Общие тех вопросы"
        intent: /5_Consultation || toState = "/Consultation/Консультация"
        event: noMatch || toState = "/AgentRequest/SelectTheme/SelectTheme2"

        state: SelectTheme2
            q: $agentRequest
            intent: /AgentRequest
            script:
                startIntent('405_AgentRequest');
                $.session.agentRequestCount++;
            a: Пожалуйста, выберите тематику вашего вопроса, это поможет подобрать для вас лучшего сотрудника.
            buttons:
                "Технический вопрос" -> /TechQuestion/Общие тех вопросы
                "Консультация" -> /Consultation/Консультация
            q: $agentRequest  || toState = "/AgentRequest/SelectTheme/SelectTheme2/ToOperator"  
            intent: /AgentRequest || toState = "/AgentRequest/SelectTheme/SelectTheme2/ToOperator"
            intent: /5_Consultation || toState = "/Consultation/Консультация"
            intent: /250_TechQuestion || toState = "/TechQuestion/Общие тех вопросы"
            event: noMatch || toState = "/AgentRequest/SelectTheme/SelectTheme2/NoMatch"

            state: ToOperator
                q: $agentRequest
                intent: /AgentRequest
                script:
                    startIntent('405_AgentRequest');
                    $.session.agentRequestCount++;
                go!: /AgentRequest/Transfer
                
            state: NoMatch
                script:
                    $.session.intent.resultCode = 1;
                go!: /AgentRequest/Transfer
    
    state: Transfer
        if: $.session.userAuth
            a: Диалог принял неожиданный для меня поворот.
            go!: /Transfer/Перевод чата
        else:
            a: Диалог принял неожиданный для меня поворот. Я позову на помощь коллегу, сейчас он подключится. А пока подготовьте номер договора или ФИО и адрес (например, Москва, 3-я улица Строителей, дом 25, кв 12).
        q:* || toState = "/Transfer/Перевод чата"
