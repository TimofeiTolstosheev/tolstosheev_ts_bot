theme: /StopAgreementFinal
    
    state: Намерение расторгнуть договор (НРД) || modal = true
        q!: $360_StopAgreementFinal
        intent!: /360_StopAgreementFinal
        script:
            startIntent('360_StopAgreementFinal');
        if: $.session.userAuth
            script:
                agreementTerminationRequest();
            go!: /StopAgreementFinal/Намерение расторгнуть договор (НРД)/CheckingTime
        else:
            a: Я позову на помощь коллегу, он сейчас ответит.
            a: А пока напишите номер договора или ФИО и адрес (например, Москва, 3-я улица Строителей, дом 25, кв 12), после вашего сообщения оператор подключится к чату.
            
        state: CheckingTime
            q: *
            script:
                checkGSKtime($.session.city);
                if($.session.gskWorkTime){
                    $reactions.transition("/Transfer/Перевод чата NUA");
                }else{
                    $reactions.transition("/Transfer/Перевод чата");
                }
