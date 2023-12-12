theme: /Invoice
    state: Квитанция
        q!: $50_InvoiceIntent
        intent!: /50_Invoice
        script:
            startIntent('50_Invoice');
        go!: /Invoice/Квитанция/Clarify
        
        state: Clarify
            a: Вам не пришла квитанция или не совпадает сумма в квитанции?
            buttons:
                "Не пришла квитанция" -> /Invoice/Квитанция/Clarify/Не пришла квитанция
                "Не совпадает сумма" -> /Invoice/Квитанция/Clarify/Не совпадает сумма
            intent: /LostInvoice || toState = "/Invoice/Квитанция/Clarify/Не пришла квитанция"
            intent: /SumProbInvoice || toState = "/Invoice/Квитанция/Clarify/Не совпадает сумма"

            state: Не совпадает сумма
                q!: $50_SumProbInvoiceIntent
                intent!: /50_Invoice/50_SumProbInvoice
                script:
                    startIntent('50_SumProbInvoice');
                a: В квитанции абонентская плата указана без учета скидок. Если вы оплатите указанную в квитанции сумму, то остаток перейдет на следующий месяц. Посмотреть начисления можно в <a href="https://dom.ru/balance_history">личном кабинете</a> или мобильном приложении «Мой Дом.ру».
                go!: /Еще вопросы?
        
            state: Не пришла квитанция
                q!: $50_LostInvoiceIntent
                intent!: /50_Invoice/50_LostInvoice
                script:
                    startIntent('50_LostInvoice');
                a: Мы переходим на электронный формат квитанций, вы можете ознакомиться с ними в <a href="https://dom.ru/lk/receipt">личном кабинете</a>.
                a: Если нет доступа в личный кабинет или вы хотите получать бумажный вариант квитанции, то напишите мне об этом, и я позову коллегу на помощь.
                go!: /Еще вопросы?
                    
            state: CatchAll || noContext = true
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        $reactions.transition('/Invoice/Квитанция/Clarify');
                    }else{
                        $.session.intent.resultCode = 6;
                        $reactions.transition('/Transfer/Перевод чата');
                    }
