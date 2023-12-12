theme: /PaymentsMetods
    
    state: Способы оплаты
        q!: $10_PaymentsMethods
        intent!: /10_PaymentsMethods
        script:
            startIntent('10_PaymentsMethods');
        a: Способы оплаты указаны по <a href="https://perm.dom.ru/payments">ссылке</a>. Вам потребуется номер договора или номер телефона, привязанный к договору.
        go!: /Еще вопросы?
