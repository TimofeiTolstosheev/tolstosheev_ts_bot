theme: /SetupRouter
    state: Настройка роутера
        q!: $130_SetupRouter
        intent!: /130_SetupRouter
        script:
            startIntent('130_SetupRouter');
        a: Делюсь <a href="https://dom.ru/service/knowledgebase/internet/kak-nastroit-router?utm_referrer=https%3a%2f%2fmiro.com%2f">инструкцией</a> для настройки роутеров. 
            Там всё просто :)
        a: Для настройки роутера может потребоваться логин и пароль от договора. Если у вас их нет, то вот <a href="https://dom.ru/user/recovery/pass">ссылка</a> для восстановления.
        go!: /Еще вопросы?
