theme: /Accident
    state: AccidentOrPPR
        intent: /500_accident
        script:
            startIntent('500_accident');
        if: $.session.accident
            go!: /Accident/AccidentOrPPR/Accident
        else:
            if: $.session.ppr > 0
                go!: /Accident/AccidentOrPPR/PPR
            else:
                go!: /Transfer/Перевод чата

        state: Accident
            script:
                $.temp.accidentEndDate = dateToDefaultString(moment($.session.accidentEndDate, "DD.MM.YYYY HH:mm:ss Z").toDate());
            a: По вашему адресу наблюдаются технические сложности, сейчас услуги связи могут не работать. Нам очень жаль, что так вышло, мы уже чиним. Постараемся восстановить услуги к {{$.temp.accidentEndDate}}.
            if: $.session.ppr == 2
                script:
                    $.temp.futurePprStartDate = dateToDefaultString(moment($.session.futurePprStartDate, "DD.MM.YYYY HH:mm:ss Z").toDate());
                    $.temp.futurePprEndDate = dateToDefaultString(moment($.session.futurePprEndDate, "DD.MM.YYYY HH:mm:ss Z").toDate());
                a: Обратите внимание, что с {{$.temp.futurePprStartDate}} до {{$.temp.futurePprEndDate}} будут проводиться плановые профилактические работы. В это время интернет, телевещание и телефония могут временно отсутствовать. Это необходимо, чтобы в дальнейшем услуги продолжали радовать вас. Надеемся на ваше понимание.
            go!: /Еще вопросы?

        state: PPR
            script:
                $.temp.currentPprEndDate = dateToDefaultString(moment($.session.currentPprEndDate, "DD.MM.YYYY HH:mm:ss Z").toDate());
            a: Сейчас по вашему дому идут профилактические работы. Это необходимо, чтобы в дальнейшем услуги продолжали радовать вас. Ожидаем, что к {{$.temp.currentPprEndDate}} профилактика завершится. Надеемся на ваше понимание.
            if: $.session.ppr == 2
                script:
                    $.temp.futurePprStartDate = dateToDefaultString(moment($.session.futurePprStartDate, "DD.MM.YYYY HH:mm:ss Z").toDate());
                    $.temp.futurePprEndDate = dateToDefaultString(moment($.session.futurePprEndDate, "DD.MM.YYYY HH:mm:ss Z").toDate());
                a: Обратите внимание, что с {{$.temp.futurePprStartDate}} до {{$.temp.futurePprEndDate}} будут проводиться плановые профилактические работы. В это время интернет, телевещание и телефония могут временно отсутствовать. Это необходимо, чтобы в дальнейшем услуги продолжали радовать вас. Надеемся на ваше понимание.
            go!: /Еще вопросы?
