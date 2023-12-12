theme: /OffersHelpIntent
    state: Консультация по тарифным планам
        q!: $180_OffersHelpIntent
        intent!: /180_OffersHelpIntent
        script:
            startIntent('180_OffersHelpIntent');
        a: Ознакомиться с информацией по вашему тарифу, а также сменить его, вы можете в <a href="https://dom.ru/lk/change-tariff">личном кабинете</a> или мобильном приложении «Мой Дом.ру». Если нет доступа в личный кабинет, напишите мне об этом, и я позову коллегу на помощь.
        go!: /Еще вопросы?