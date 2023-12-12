theme: /ContractDetails
    state: NewAddress
        q!: $160_ContractDetailAddressIntent
        script:
            startIntent('160_ContractDetails');
        go!: /ContractDetails/Консультация по переоформлению договора/Другой адрес
        
    state: NewOwner
        q!: $160_ContractDetailPersonIntent
        script:
            startIntent('160_ContractDetails');
        go!: /ContractDetails/Консультация по переоформлению договора/Другое лицо
            
    state: Консультация по переоформлению договора
        q!: $160_ContractDetails
        intent!: /160_ContractDetails
        script:
            startIntent('160_ContractDetails');
        a: Хотите переоформить договор на другой адрес или на другого человека?
        buttons:
            "Другой адрес" -> /ContractDetails/Консультация по переоформлению договора/Другой адрес
            "Другое лицо" -> /ContractDetails/Консультация по переоформлению договора/Другое лицо
        intent: /160_ContractDetails/ContractDetailAddressIntent || toState = "/ContractDetails/Консультация по переоформлению договора/Другой адрес"
        intent: /160_ContractDetails/ContractDetailPersonIntent || toState = "/ContractDetails/Консультация по переоформлению договора/Другое лицо"
        event: noMatch || toState = "/Transfer/Перевод чата"
        
        state: не готов на переоформление
            a: В таком случае напишите нам за несколько дней перед датой, когда потребуется переоформить договор.
            go!: /Еще вопросы?

        state: заявка на переоформление
            a: Вы хотели бы сейчас оформить заявку на переоформление договора?
            buttons:
                "Да" -> /Transfer/Перевод чата
                "Нет" -> /ContractDetails/Консультация по переоформлению договора/не готов на переоформление
            q: * $commonYes * || toState = "/Transfer/Перевод чата"
            intent: /Yes || toState = "/Transfer/Перевод чата"
            q: * $commonNo * || toState = "/ContractDetails/Консультация по переоформлению договора/не готов на переоформление"
            intent: /No || toState = "/ContractDetails/Консультация по переоформлению договора/не готов на переоформление"
            event: noMatch || toState = "/Transfer/Перевод чата"

        state: Другое лицо
            q: другое лицо
            a: Ваш номер договора будет изменен при переоформлении договора на другого человека.
                       Ваш текущий баланс будет перенесен на новый договор после закрытия заявки на подключение на новом адресе.
            go!: /ContractDetails/Консультация по переоформлению договора/заявка на переоформление

        state: Другой адрес
            q: другой адрес
            a: Ваш номер договора будет изменен при переоформлении договора на другой адрес.
                       Ваш текущий баланс будет перенесен на новый договор после закрытия заявки на подключение на новом адресе.
                       При подключении услуг укладка кабеля открытым способом свыше 10 метров оплачивается отдельно.
            go!: /ContractDetails/Консультация по переоформлению договора/заявка на переоформление

