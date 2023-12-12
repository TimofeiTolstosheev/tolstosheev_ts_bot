theme: /SMSInformIntent
    state: Выдача данных по СМС
        #нет q
        intent!: /210_SMSInformIntent
        script:
            startIntent('210_SMSInformIntent');
        go!: /Transfer/Перевод чата
