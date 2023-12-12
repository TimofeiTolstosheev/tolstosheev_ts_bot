theme: /PhoneProblem
    state: Phone
        intent!: /350_PhoneProblem
        q!: $350_PhoneProblem
        script:
            startIntent('350_PhoneProblem');
            $.session.intent.resultCode = 6;
        go!: /Transfer/Перевод чата
