theme: /ConfigureInternetConnection
    state: Настройка подключения к сети Интернет
        q!: $220_ConfigureInternetConnection 
        intent!: /220_ConfigureInternetConnection
        script:
            startIntent('220_ConfigureInternetConnection');
        go!: /Transfer/Перевод чата