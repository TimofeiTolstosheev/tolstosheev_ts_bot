theme: /TarifActivate
    state: Активация тарифного плана
        q!: $110_TarifActivate
        intent!: /110_TarifActivate
        script:
            startIntent('110_TarifActivate');
        if: $.session.userAuth
            go!: /TarifActivate/CheckAccess
        else:
            go!: /TarifActivate/ActivationInfo
            
    state: CheckAccess
        script:
            checkAccess();
        if: $.session.proactiveResult == 'spas'
            # если нет проблем в проактиве, считаем, что тариф активирован
            a: На вашем договоре услуги уже активны. Если у вас есть сложности с доступом в интернет, то напишите мне об этом.
            go!: /Еще вопросы?
        else:
            go!: /TarifActivate/ActivationInfo

    state: ActivationInfo
        a: Активация тарифа доступна в <a href="https://dom.ru/lk/internet/activate">личном кабинете</a>. Через 10 минут после активации перезагрузите роутер. Важно: при активации тарифа до 15 числа, списывается полная месячная абонентская плата, интернет будет работать до конца месяца. При активации тарифа после 15 числа, абонентская плата списывается пропорционально оставшимся дням до конца месяца.
        go!: /Еще вопросы?
