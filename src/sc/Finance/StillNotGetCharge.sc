theme: /StillNotGetCharge
    state: Потерянный платеж
        q!: $70_StillNotGetCharge
        intent!: /70_StillNotGetCharge
        script:
            startIntent('70_StillNotGetCharge');
        a: Если прошло меньше суток, то рекомендую подождать. В редких случаях срок перевода может быть до 24 часов. Если прошло больше - напишите мне об этом, и я позову своего коллегу для поиска платежа
        a: Следить за своим балансом и поступлением денежных средств вы можете в Личном кабинете или в Мобильном приложении «Мой Дом.ру». Войти в Личный кабинет вы можете по <a href="https://dom.ru/lk">ссылке</a>, а скачать приложение «Мой Дом.ру» можете по <a href="https://dom.ru/service/agent">ссылке</a>.
               Если у вас нет доступа для входа в Личный кабинет, или вы оплатили другим способом, то напишите об этом, и я переведу вас на специалиста для дальнейшей консультации.
        go!: /Еще вопросы?
