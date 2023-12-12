theme: /Greeting
    state: FirstIntent
        a: Напишите свой вопрос, и я постараюсь вам помочь.
        buttons:
            "Не работает интернет" -> /PageNotOpenLowSpeedNoLinkPPoE/NoLink
            "Баланс и детализация" -> /BalanceInquiry/CheckAuth
            "Низкая скорость" -> /PageNotOpenLowSpeedNoLinkPPoE/NoLink
            "Проблемы с ТВ" -> /TVChannelTVqualityProblem/TVchannelProblem
            "Консультация по тарифу" -> /OffersHelpIntent/Консультация по тарифным планам
            "Подключить услугу" -> /ManageServicePacket/Управление контентом
        event: noMatch || toState = "/NoMatch"
    
    state: HelloAgain
        q!: $helloAdvanced
        a: И еще раз привет! Рад, что я вам понравился, с удовольствием продолжу общение. Чем помочь сейчас?
        event: noMatch || toState = "/NoMatch"
