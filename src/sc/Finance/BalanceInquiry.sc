theme: /BalanceInquiry
    state: CheckAuth
        q!: $90_Balance
        intent!: /90_Balance
        script:
            startIntent('90_Balance');
        if: $.session.userAuth
            go!: /BalanceInquiry/CheckAuth/GetBalance
        else:
            go!: /BalanceInquiry/CheckAuth/balance unauthorized
        
        state: GetBalance
            script:
                getBalance();
            # если нет суммы баланса, то переводим на оператора
            if: $.session.curBalance
                if: $.session.lastPayment == 1 && $.session.lastPaymentSum
                    a: Вижу, что недавно вы пополнили счет на {{$.session.lastPaymentSum}} ₽.
                    
                if: $.session.textParam == 14
                    a: Ваш баланс {{$.session.curBalance}} ₽.
                    a: Стоимость услуг за {{$.session.periodDates}} {{$.session.chargesSum}} ₽. Баланс может измениться после выставления счёта за {{$.session.periodDates}}.
                    a: Оплатите {{$.session.recommendSum}} ₽ за {{$.session.periodDates}}.
                if: $.session.textParam == 13
                    a: Ваш баланс {{$.session.curBalance}} ₽.
                    a: {{$.session.nextPeriodDate}} со счёта будет списана оплата за {{$.session.periodDates}} – {{$.session.chargesSum}} ₽.
                if: $.session.textParam == 12
                    a: Ваш баланс {{$.session.curBalance}} ₽.
                    a: Стоимость услуг за {{$.session.periodDates}} {{$.session.chargesSum}} ₽.
                    a: Баланс может измениться после выставления счёта за {{$.session.periodDates}}.
                    a: Оплатите {{$.session.recommendSum}} ₽ за {{$.session.periodDates}}, чтобы сохранить доступ к услугам.
                if: $.session.textParam == 11
                    a: Ваш баланс {{$.session.curBalance}} ₽.
                    a: Договор будет закрыт {{$.session.closeDate}}.
                if: $.session.textParam == 10
                    a: Ваш баланс {{$.session.curBalance}} ₽.
                    a: Договор будет закрыт {{$.session.closeDate}}.
                    a: До закрытия оплатите {{$.session.recommendSum}} ₽.
                if: $.session.textParam == 7
                    a: Ваш баланс {{$.session.curBalance}} ₽.
                    a: Стоимость услуг за {{$.session.periodDates}} {{$.session.chargesSum}} ₽.
                    a: Оплатите за {{$.session.periodDates}} {{$.session.recommendSum}} ₽.
                if: $.session.textParam == 6
                    a: Ваш баланс {{$.session.curBalance}} ₽.
                    a: Чтобы сохранить доступ к услугам, внесите {{$.session.recommendSum}} ₽.
                if: $.session.textParam == 5
                    a: Ваш баланс {{$.session.curBalance}} ₽.
                    a: Стоимость услуг за {{$.session.periodDates}} {{$.session.chargesSum}} ₽.
                    a: Чтобы сохранить доступ к услугам оплатите до {{$.session.opEndDate}} {{$.session.recommendSum}} ₽.
                if: $.session.textParam == 4
                    a: Ваш баланс {{$.session.curBalance}} ₽.
                    a: Чтобы восстановить доступ к услугам, внесите {{$.session.recommendSum}} ₽.
                if: $.session.textParam == 3
                    a: Ваш баланс {{$.session.curBalance}} ₽.
                    a: {{$.session.nextPeriodDate}} со счёта будет списана оплата за {{$.session.periodDates}} {{$.session.chargesSum}} ₽.
                if: $.session.textParam == 2
                    a: Ваш баланс {{$.session.curBalance}} ₽.
                    a: Стоимость услуг за {{$.session.periodDates}} {{$.session.chargesSum}} ₽.
                    a: Чтобы сохранить доступ к услугам, оплатите до {{$.session.nextPeriodDate}} {{$.session.recommendSum}} ₽.
                if: $.session.textParam == 1
                    a: Ваш баланс {{$.session.curBalance}} ₽. Стоимость услуг за {{$.session.periodDates}} {{$.session.chargesSum}} ₽.
                    a: Чтобы восстановить доступ к услугам оплатите за {{$.session.periodDates}} {{$.session.recommendSum}} ₽.
                if: $.session.textParam == 0
                    a: Ваш баланс {{$.session.curBalance}} ₽. Стоимость услуг за {{$.session.periodDates}} {{$.session.chargesSum}} ₽.
                    a: Чтобы сохранить доступ к  услугам оплатите за {{$.session.periodDates}} {{$.session.recommendSum}} ₽.
                if: $.session.textParam < 0 || $.session.textParam > 14
                    go!: /Transfer/Перевод чата
                if: $.session.retStr === '$5'
                    a: Обратите, пожалуйста, внимание, что баланс может измениться при выставлении счета за звонки на мобильные телефоны в другие города и страны, если вы их совершите.
                go!: /Еще вопросы?
            else:
                go!: /Transfer/Перевод чата
            
        state: balance unauthorized
            a: Узнать баланс и историю начислений можно в <a href="https://perm.dom.ru/balance_history">личном кабинете</a>, а оплатить <a href="https://perm.dom.ru/payments">здесь</a>.
            a: Если у вас нет доступа для входа в личный кабинет, то напишите мне об этом, и я подскажу, как восстановить ваш пароль.
            go!: /Еще вопросы?