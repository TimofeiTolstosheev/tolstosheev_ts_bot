theme: /PromisedPayment
    state: PromisedPayment
        q!: $80_PromisedPayment
        intent!: /80_PromisedPayment
        script:
            startIntent('80_PromisedPayment');
        if: $.session.userAuth
            go!: /PromisedPayment/PromisedPayment/CheckPromisedPayment
        else:
            go!: /PromisedPayment/Transfer
        
        state: CheckPromisedPayment
            script:
                checkPromisedPayment();
            if: $.session.promisedPaymentError
                if: $.session.promisedPaymentError == 13 || $.session.promisedPaymentError == 15 || $.session.promisedPaymentError == 18
                    go!: /PromisedPayment/PromisedPayment/Error13_15_18
                if: $.session.promisedPaymentError == 17
                    go!: /PromisedPayment/PromisedPayment/Error17
                if: $.session.promisedPaymentError == 16
                    go!: /PromisedPayment/PromisedPayment/Error16
                if: $.session.promisedPaymentError == 11
                    go!: /PromisedPayment/PromisedPayment/Error11
                if: $.session.promisedPaymentError == 14
                    go!: /PromisedPayment/PromisedPayment/Error14
                if: $.session.promisedPaymentError == 12
                    go!: /PromisedPayment/PromisedPayment/Error12
                else:
                    go!: /Transfer/Перевод чата
            else:
                if: $.session.canGetpromisedPayment
                    if: $.session.mono
                        go!: /PromisedPayment/PromisedPayment/CheckPromisedPayment/GetDays
                    else:
                        go!: /PromisedPayment/PromisedPayment/CheckPromisedPayment/Confirm
                else:
                    # если ошибки нет, но ОП недоступен
                    go!: /Transfer/Перевод чата
                    
            state: Confirm
                a: «Обещанный платёж» работает до предпоследнего дня месяца или до внесения оплаты в этом месяце. Стоимость дня — {{$.session.promisedPaymentCost}} рублей. Подключить?
                buttons:
                    "Да" -> /PromisedPayment/PromisedPayment/CheckPromisedPayment/GetDays/GetPromisedPayment
                    "Нет" -> /Еще вопросы?
                
                q: $commonYes || toState = "/PromisedPayment/PromisedPayment/CheckPromisedPayment/GetDays/GetPromisedPayment"
                q: $yesForPromisedPayment || toState = "/PromisedPayment/PromisedPayment/CheckPromisedPayment/GetDays/GetPromisedPayment"
                intent: /Yes || toState = "/PromisedPayment/PromisedPayment/CheckPromisedPayment/GetDays/GetPromisedPayment"
                q: (не/ни) (хочу/надо/подключай*) || toState = "/Еще вопросы?"
                q: $commonNo || toState = "/Еще вопросы?"
                q: $noForPromisedPayment || toState = "/Еще вопросы?"
                intent: /No || toState = "/Еще вопросы?"
            
            state: GetDays
                a: «Обещанный платёж» доступен на срок до {{$.session.promisedPaymentMaxDay}} дней и будет активен до момента оплаты. Стоимость дня — {{$.session.promisedPaymentCost}} рублей. Напишите цифрами от 1 до {{$.session.promisedPaymentMaxDay}} на сколько дней вы хотите его подключить?
                q: @duckling.number || toState = "/PromisedPayment/PromisedPayment/CheckPromisedPayment/GetDays/ValidateDays"
                q: $commonNo || toState = "/Еще вопросы?"
                q: $noForPromisedPayment || toState = "/Еще вопросы?"
                intent: /No || toState = "/Еще вопросы?"
                
                state: ValidateDays
                    script:
                        
                        $.session.intent.stepsCnt++;
                        $.session.promisedPaymentDays = $parseTree['duckling.number'][0].value;
                    if: $.session.promisedPaymentDays < 1 || $.session.promisedPaymentDays > $.session.promisedPaymentMaxDay
                        script:
                            if(countRepeatsInRow() < $injector.noMatchLimit) {
                                $reactions.answer('Извините, но вы называете неподходящее число дней.');
                                $reactions.transition('/PromisedPayment/PromisedPayment/CheckPromisedPayment/GetDays');
                            }else{
                                $.session.intent.resultCode = 6;
                                $reactions.transition('/Transfer/Перевод чата');
                            }
                    else:
                        go!: /PromisedPayment/PromisedPayment/CheckPromisedPayment/GetDays/GetPromisedPayment
                
                state: GetPromisedPayment
                    script:
                        $.session.promisedPaymentDays = $.session.promisedPaymentDays || $.session.promisedPaymentMaxDay;
                        getPromisedPayment($.session.promisedPaymentDays);
                    if: $.session.promisedPaymentError
                        go!: /Transfer/Перевод чата
                    else:
                        if: $.session.mono
                            if: $.session.after15
                                a: «Обещанный платёж» подключил. Стоимость услуги за {{$.session.promisedPaymentDays}} {{ $nlp.conform("день", $.session.promisedPaymentDays) }} — {{$.session.promisedPaymentTotalCost}} рублей. Вам потребуется активировать тариф, сделать это можно в <a href="https://dom.ru/lk">личном кабинете</a>. Доступ появится через 5 минут, не забудьте перезагрузить роутер. Если оплатите раньше окончания «Обещанного платежа», то списание за него будет только за дни пользования.
                            else:
                                a: «Обещанный платёж» подключил. Стоимость услуги за {{$.session.promisedPaymentDays}} {{ $nlp.conform("день", $.session.promisedPaymentDays) }} — {{$.session.promisedPaymentTotalCost}} рублей. Доступ появится через 5 минут, не забудьте перезагрузить роутер. Если оплатите раньше окончания «Обещанного платежа», то списание за него будет только за дни пользования.
                        else:
                            a: «Обещанный платёж» подключил. Стоимость услуги за 1 день — {{$.session.promisedPaymentCost}} {{ $nlp.conform("рубль", $.session.promisedPaymentCost) }}. Доступ появится через 5 минут. Для того, чтобы услуги заработали, не забудьте перезагрузить оборудование. Если оплатите раньше окончания «Обещанного платежа», то списание за него будет только за дни пользования.
                    go!: /Еще вопросы?

        state: Error12
            a: На вашем договоре уже подключен «Обещанный платеж». Он будет автоматически отключен на следующий день после поступления оплаты.
            go!: /Еще вопросы?

        state: Error14
            if: $.session.mono
                a: Сейчас не получится активировать «Обещанный платеж». Использовать «Обещанный платеж» возможно только с 1 по 15 число текущего месяца.
            else:
                a: Сейчас не получится активировать «Обещанный платеж». Использовать «Обещанный платеж» возможно только с 21 числа текущего месяца.
                
            a: Рассказать вам о способах оплаты?
            buttons:
                "Да" -> /PaymentsMetods/Способы оплаты
                "Нет" -> /Еще вопросы?
            q: $commonYes || toState = "/PaymentsMetods/Способы оплаты"
            q: $yesForPaymentMethods || toState = "/PaymentsMetods/Способы оплаты"
            intent: /Yes || toState = "/PaymentsMetods/Способы оплаты"
            q: $commonNo || toState = "/Еще вопросы?"
            q: $noForPaymentMethods || toState = "/Еще вопросы?"
            intent: /No || toState = "/Еще вопросы?"
            
            state: CatchAll || noContext = true
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        $reactions.transition('../');
                    }else{
                        $.session.intent.resultCode = 6;
                        $reactions.transition('/Transfer/Перевод чата');
                    }

        state: Error11
            a: «Обещанный платеж» доступен, если у вас есть услуга интернет. На вашем типе договора «Обещанный платеж» подключить не получится.
                Рассказать вам о способах оплаты?
            buttons:
                "Да" -> /PaymentsMetods/Способы оплаты
                "Нет" -> /Еще вопросы?
            q: $commonYes || toState = "/PaymentsMetods/Способы оплаты"
            q: $yesForPaymentMethods || toState = "/PaymentsMetods/Способы оплаты"
            intent: /Yes || toState = "/PaymentsMetods/Способы оплаты"
            q: $commonNo || toState = "/Еще вопросы?"
            q: $noForPaymentMethods || toState = "/Еще вопросы?"
            intent: /No || toState = "/Еще вопросы?"
            event: noMatch || toState = "/Transfer/Перевод чата"

        state: Error16
            a: Сейчас подключить «Обещанный платеж» не получится, так как в прошлом месяце на счете была задолженность.
                Рассказать вам о способах оплаты?
            buttons:
                "Да" -> /PaymentsMetods/Способы оплаты
                "Нет" -> /Еще вопросы?
            q: $commonYes || toState = "/PaymentsMetods/Способы оплаты"
            q: $yesForPaymentMethods || toState = "/PaymentsMetods/Способы оплаты"
            intent: /Yes || toState = "/PaymentsMetods/Способы оплаты"
            q: $commonNo || toState = "/Еще вопросы?"
            q: $noForPaymentMethods || toState = "/Еще вопросы?"
            event: noMatch || toState = "/Transfer/Перевод чата"

        state: Error17
            a: На вашем договоре интернет сейчас активен и «Обещанный платеж» не требуется. Если у вас есть сложности с доступом в Интернет, то напишите мне об этом. Я помогу вам с решением вопроса.
            go!: /Еще вопросы?

        state: Error13_15_18
            a: На данный момент «Обещанный платеж» подключить не получится.
                Я позову на помощь человека, один момент, он сейчас ответит.
            go!: /Transfer/Перевод чата
            
    state: Transfer || modal = true
        a: Я позову на помощь коллегу, он сейчас ответит.
        a: А пока напишите номер договора или ФИО и адрес (например, Москва, 3-я улица Строителей, дом 25, кв 12), после вашего сообщения оператор подключится к чату.
        
        state: ToOperator
            q: *
            go!: /Transfer/Перевод чата
