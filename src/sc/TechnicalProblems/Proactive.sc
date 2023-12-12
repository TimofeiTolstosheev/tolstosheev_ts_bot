theme: /Proactive
    state: RouteProactive
        if: $.session.accident || $.session.ppr
            go!: /Accident/AccidentOrPPR
        if: $.session.proactiveResult == 'suspended'
            go!: /Proactive/Suspension
        if: $.session.proactiveResult == 'noInternet'
            go!: /Proactive/NoInternet
        if: $.session.proactiveResult == 'dz'
            go!: /Proactive/Debit
        if: $.session.proactiveResult == 'negativeBalance'
            go!: /Proactive/NegativeBalance
        if: $.session.proactiveResult == 'noActivation'
            go!: /Proactive/NoActivation
        if: $.session.serviceRequestStatus == 'request-pending'
            go!: /Proactive/ServiceRequest/Pending
        if: $.session.serviceRequestStatus == 'request-undefined'
            go!: /Proactive/ServiceRequest/NoPlanTime
            
    state: Suspension
        a: Сейчас услуги по вашему адресу приостановлены.
            Возобновить можно в личном кабинете или мобильном приложении "Мой Дом.ру".
            После возобновления услуги заработают.
        go!: /Еще вопросы?
    
    state: NoInternet
        a: У вас не подключена услуга интернет. Подключить можно в <a href="https://dom.ru/lk/change-tariff">личном кабинете</a> или в мобильном приложении Мой Дом.ру.
        go!: /Еще вопросы?
    
    state: Debit
        a: Работа услуг приостановлена из-за отрицательного баланса. Для восстановления доступа к услугам, вам необходимо внести платеж в размере {{$.session.recommendSum}} руб.
        a: Телевидение заработает в течение 24 часов с момента поступления оплаты. Если вы уже внесли оплату, то ожидайте, в скором времени услуги заработают.
        go!: /Еще вопросы?
       
    state: NegativeBalance
        a: Работа услуг приостановлена из-за отрицательного баланса. Для восстановления доступа к услугам, вам необходимо внести платеж в размере {{$.session.recommendSum}} руб.
        a: После поступления оплаты, доступ к услугам будет возобновлен в течение 10 минут. Не забудьте перезагрузить роутер.
        go!: /Еще вопросы?
    
    state: NoActivation
        a: Услуга безлимитного интернета сейчас не активирована. Рассказать вам как активировать тариф?
        buttons:
            "Да" -> /TarifActivate/Активация тарифного плана
            "Нет" -> /Еще вопросы?
        q: * $commonYes * || onlyThisState = true, toState = "/TarifActivate/Активация тарифного плана"
        q: * активир* * || onlyThisState = true, toState = "/TarifActivate/Активация тарифного плана"
        q: * @Give * || onlyThisState = true, toState = "/TarifActivate/Активация тарифного плана"
        intent: /Yes || onlyThisState = true, toState = "/TarifActivate/Активация тарифного плана"
        q: * $commonNo * || toState = "/Еще вопросы?"
        q: * не активир* * || toState = "/Еще вопросы?"
        intent: /No || toState = "/Еще вопросы?"
        event: noMatch || toState = "/Еще вопросы?"
    
    state: ServiceRequest

        state: Pending
            script:
                $.temp.srvPlanDateFrom = dateToDefaultString($.session.srvPlanDateFrom);
                $.temp.srvPlanDateTo = dateToDefaultString($.session.srvPlanDateTo);
            a: На ваш адрес назначена заявка на выезд специалиста. Инженер приедет с {{$.temp.srvPlanDateFrom}} до {{$.temp.srvPlanDateTo}}. Если вы хотите отменить или перенести заявку, сделать это можно в <a href="https://dom.ru/lk/orders">личном кабинете</a>.
            go!: /Proactive/ServiceRequest/AskOperator
            
        state: NoPlanTime
            a: На ваш адрес назначена заявка на выезд специалиста. Инженер выполнит работы в ближайшее время.
            go!: /Proactive/ServiceRequest/AskOperator

        state: AskOperator
            a: Соединить вас с оператором для дальнейшей консультации?
            buttons:
                "Да" -> /Transfer/Перевод чата
                "Нет" -> /Еще вопросы?
            q: $agentRequest || toState = "/Transfer/Перевод чата"
            q: $commonYes || toState = "/Transfer/Перевод чата"
            q: $yesOperator || toState = "/Transfer/Перевод чата"
            intent: /Yes || toState = "/Transfer/Перевод чата"
            q: $commonNo || toState = "/Еще вопросы?"
            q: $noOperator || toState = "/Еще вопросы?"
            intent: /No || toState = "/Еще вопросы?"
            event: noMatch || toState = "/Еще вопросы?"
