theme: /CancelSuspend
    state: CheckOS
        intent!: /155_CancelSuspend
        q!: $155_CancelSuspend
        script:
            startIntent('155_CancelSuspend');
        if: $.session.os == 'Android'
            go!: /CancelSuspend/CheckOS/suspense android
        else:
            if: $.session.os == 'iOS'
                go!: /CancelSuspend/CheckOS/suspense ios
            else:
                go!: /CancelSuspend/CheckOS/suspense web
                    
        state: suspense ios
            a: Управлять услугами легко! В приложении Мой Дом.ru (вкладка ▶️ Услуги – Приостановить обслуживание) вы сможете:
                   • узнать условия приостановления;
                   • временно приостановить доступ сроком от 7 до 124 дней (услуга платная);
                   • продлить или изменить сроки приостановления;
                   • отменить приостановление услуг; 
                   • проверить активность услуг.
                   Инструкции и ответы на популярные вопросы можно найти во вкладке ❤️ Помощь - 💬 Вопросы и ответы
            go!: /Еще вопросы?
    
        state: suspense android
            a: Управлять услугами легко! В приложении Мой Дом.ru (вкладка ▶️ Услуги – Приостановить услуги) вы сможете:
                   • узнать условия приостановления;
                   • временно приостановить доступ сроком от 7 до 124 дней (услуга платная);
                   • продлить или изменить сроки приостановления;
                   • отменить приостановление услуг;
                   • проверить активность услуг.
                   Инструкции и ответы на популярные вопросы можно найти во вкладке ❤️ Помощь - 📖 Частые вопросы и ответы
            go!: /Еще вопросы?
    
        state: suspense web
            a: Управлять услугами легко! В личном кабинете по <a href="https://dom.ru/management">ссылке</a> вы сможете:
                   • узнать условия приостановления;
                   • временно приостановить доступ сроком от 7 до 124 дней (услуга платная);
                   • продлить или изменить сроки приостановления;
                   • отменить приостановление услуг;
                   • проверить активность услуг.
                   Инструкции и ответы на популярные вопросы размещены на сайте в разделе Помощь
            go!: /Еще вопросы?