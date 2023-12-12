theme: /Consultation
    state: Консультация
        #нет q
        intent: /5_Consultation
        script:
            startIntent('5_Consultation');
        go!: /Transfer/Перевод чата
