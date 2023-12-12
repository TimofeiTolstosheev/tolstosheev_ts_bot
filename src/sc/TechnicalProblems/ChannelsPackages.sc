theme: /ChannelsPackages
    state: Вопрос по составу пакетов каналов
        q!: $40_ChannelsPackagesIntent
        intent!: /40_ChannelsPackages
        script:
            startIntent('40_ChannelsPackages');
        a: Все доступные каналы и дополнительные пакеты можно посмотреть в <a href="https://dom.ru/domru-tv/packages">личном кабинете</a>. Если у вас нет доступа в личный кабинет, то напишите мне об этом, и я подскажу, как восстановить ваш пароль.
        go!: /Еще вопросы?