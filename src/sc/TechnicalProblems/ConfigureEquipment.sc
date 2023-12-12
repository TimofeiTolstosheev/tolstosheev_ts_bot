theme: /ConfigureEquipment
    state: Настройка дополнительного оборудования
        #нет q
        intent!: /140_ConfigureEquipment
        script:
            startIntent('140_ConfigureEquipment');
        a: Какое устройство вы хотите настроить?
        buttons:
            "Декодер" -> /SetupDecoder/Настройка декодера/Около ТВ?
            "CAM-модуль" -> /SetupDecoder/Настройка декодера/Около ТВ?
            "Роутер" -> /SetupRouter/Настройка роутера
            "Другое" -> /ConfigureEquipment/Настройка дополнительного оборудования/Находится дома?
        q: (@TVSet/ТВ/тв/TV/tv) || toState = "/SetupDecoder/Настройка декодера/Около ТВ?"
        intent: /120_SetupDecoder/CAM || toState = "/SetupDecoder/Настройка декодера/Около ТВ?"
        intent: /120_SetupDecoder/Decoder || onlyThisState = true, toState = "/SetupDecoder/Настройка декодера/Около ТВ?"
        intent: /130_SetupRouter || toState = "/SetupRouter/Настройка роутера"
        event: noMatch || toState = "/Transfer/Перевод чата"

        state: Не дома
            a: Напишите, когда будете рядом с оборудованием, поможем настроить ;)
            go!: /Еще вопросы?

        state: Находится дома?
            a: Спасибо. Оборудование находится рядом с вами?
            buttons:
                "Да" -> /Transfer/Перевод чата
                "Нет" -> /ConfigureEquipment/Настройка дополнительного оборудования/Не дома
            q: $no || toState = "/ConfigureEquipment/Настройка дополнительного оборудования/Не дома"
            q: $noAtHome || toState = "/ConfigureEquipment/Настройка дополнительного оборудования/Не дома"
            q: * не рядом *
            intent: /No || toState = "/ConfigureEquipment/Настройка дополнительного оборудования/Не дома"
            q: $yes || toState = "/Еще вопросы?"
            q: $yesAtHome || toState = "/Еще вопросы?"
            q: * рядом * || toState = "/Еще вопросы?"
            intent: /Yes || toState = "/Еще вопросы?"
            event: noMatch || toState = "./"
