theme: /RestoreLogoPass
    state: Восстановление и смена учетных данных
        q!: $230_RestoreLogoPass
        intent!: /230_RestoreLogoPass
        script:
            startIntent('230_RestoreLogoPass');
        a: Если вы помните номер договора, привязанный телефон или email - вы можете самостоятельно воспользоваться <a href="https://dom.ru/user/recovery/pass">формой</a> восстановления пароля.
            Если у вас не получится, напишите мне об этом, и я позову коллегу на помощь.
        go!: /Еще вопросы?
