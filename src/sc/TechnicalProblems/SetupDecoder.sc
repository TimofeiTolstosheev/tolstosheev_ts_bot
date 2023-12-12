theme: /SetupDecoder
    state: Настройка декодера
        intent!: /120_SetupDecoder/120_DisambiguationSetupTV
        script:
            startIntent('120_DisambiguationSetupTV');
        a: Вы хотите настроить приставку (декодер) или телевизор с CAM-модулем внутри?
        buttons:
            "Декодер (приставка)" -> /SetupDecoder/Настройка декодера/Настройка декодера1
            "CAM-модуль" -> /SetupDecoder/Настройка декодера/Около ТВ?
        q: * @Decoder * || toState = "/SetupDecoder/Настройка декодера/Настройка декодера1"
        intent: /120_SetupDecoder/Decoder || toState = "/SetupDecoder/Настройка декодера/Настройка декодера1"
        q: * @CAMmodule * || toState = "/SetupDecoder/Настройка декодера/Около ТВ?"
        intent: /120_SetupDecoder/CAM || toState = "/SetupDecoder/Настройка декодера/Около ТВ?"
        event: noMatch || toState = "./"

        state: Настройка декодера1
            q!: $120_SetupDecoder
            intent!: /120_SetupDecoder
            script:
                startIntent('120_SetupDecoder');
            a: Я нашел для вас <a href="https://chel.dom.ru/service/knowledgebase/article/3141">инструкцию</a> по настройке оборудования. Попробуйте настроить по ней, и если что-то не получится, обязательно напишите мне.
                      Могу вам еще чем-либо помочь?
            intent: /120_SetupDecoder/Decoder || toState = "/SetupDecoder/Настройка декодера/Около ТВ?"
            intent: /120_SetupDecoder/CAM || toState = "/SetupDecoder/Настройка декодера/Около ТВ?"
            intent: /120_SetupDecoder || toState = "/SetupDecoder/Настройка декодера/Около ТВ?"
            q: $120_SetupDecoder || toState = "/SetupDecoder/Настройка декодера/Около ТВ?"
            event: noMatch || onlyThisState = true, toState = "/Transfer/Перевод чата"

        state: Не дома
            a: Напишите, как будете рядом с оборудованием, поможем настроить ;)
            go!: /Еще вопросы?

        state: Около ТВ?
            intent: /120_SetupDecoder/CAM || fromState = "/SetupDecoder/Настройка декодера"
            a: Вы сейчас находитесь около телевизора?
            buttons:
                "Да" -> /Transfer/Перевод чата
                "Нет" -> /SetupDecoder/Настройка декодера/Не дома
            q: $yes || toState = "/Transfer/Перевод чата"
            q: $yesAtHome || toState = "/Transfer/Перевод чата"
            q: * (рядом/около) * || toState = "/Transfer/Перевод чата"
            intent: /Yes || toState = "/Transfer/Перевод чата"
            q: $no || toState = "/SetupDecoder/Настройка декодера/Не дома"
            q: $noAtHome || toState = "/SetupDecoder/Настройка декодера/Не дома"
            q: * [$no] не (рядом/около) * || toState = "/SetupDecoder/Настройка декодера/Не дома"
            intent: /No || toState = "/SetupDecoder/Настройка декодера/Не дома"
            event: noMatch || toState = "/Transfer/Перевод чата"
