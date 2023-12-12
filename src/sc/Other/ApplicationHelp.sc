theme: /ApplicationHelp
    state: Консультация по работе приложений
        q!: $200_ApplicationHelp
        intent!: /200_ApplicationHelp
        script:
            startIntent('200_ApplicationHelp');
        go!: /Transfer/Перевод чата
