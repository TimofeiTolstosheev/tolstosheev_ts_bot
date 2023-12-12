theme: /PageNotOpenLowSpeedNoLinkPPoE
    
    state: PageNotOpen
        q!: $260_PageNotOpen
        intent!: /260_PageNotOpen
        script:
            startIntent('260_PageNotOpen');
        go!: /PageNotOpenLowSpeedNoLinkPPoE/CheckAuth
    
    state: LowInetSpeed
        q!: $280_LowInetSpeed
        intent!: /280_LowInetSpeed
        script:
            startIntent('280_LowInetSpeed');
        go!: /PageNotOpenLowSpeedNoLinkPPoE/CheckAuth
    
    state: NoLink
        q!: $300_NoLink6xxIntent
        intent!: /300_NoLink6xxIntent
        script:
            startIntent('300_NoLink6xxIntent');
        go!: /PageNotOpenLowSpeedNoLinkPPoE/CheckAuth
    
    state: ConnectProblemPPoE
        q!: $310_ConnectProblem
        intent!: /310_ConnectProblemPPoEIntent
        script:
            startIntent('310_ConnectProblemPPoEIntent');
        go!: /PageNotOpenLowSpeedNoLinkPPoE/CheckAuth
    
    state: CheckAuth
        if: $.session.userAuth
            go!: /PageNotOpenLowSpeedNoLinkPPoE/Auth
        else:
            go!: /PageNotOpenLowSpeedNoLinkPPoE/NotAuth

    state: Auth
        script:
            $.session.productId = 5; // 5 - интернет, 53 - телевидение
            checkAccess();
        if: $.session.proactiveProblem
            go!: /Proactive/RouteProactive
        else:
            go!: /PageNotOpenLowSpeedNoLinkPPoE/Auth/SPAS
        
        state: SPAS
            script:
                // если в СПАСе не обнаружится проблем, то вернемся в этот стейт
                $.session.returnState = '/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot';
            go!: /SPAS/GetSpasData

        state: Reboot
            a: Сейчас я проверю и перезагружу подъездное оборудование, но мне также потребуется ваша помощь: пожалуйста, отключите роутер из розетки на 15 секунд, затем подключите обратно и перезагрузите ваши устройства: ноутбук, ПК, планшет или другие девайсы. После этого подождите пару минут и проверьте работу интернета.
            script:
                reboot();
            go!: /PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot

            state: AfterReboot
                if: $.session.newVer
                    a: Проверьте, пожалуйста, сейчас услуги работают хорошо?
                    buttons:
                        "Да" -> /PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/Fixed
                        "Нет" -> /PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/NotFixed
                else:
                    a: Проверьте, пожалуйста, сейчас услуги работают хорошо?
                # intent: /300_PageNotOpenLowSpeedNoLinkPPoE/Not_work || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/NotFixed", onlyThisState = true
                q: $no || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/NotFixed", onlyThisState = true
                q: * $noAccess * || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/NotFixed", onlyThisState = true
                q: {* и @Television завис* *}  || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/NotFixed", onlyThisState = true
                q: * { выполнени* диагностик* неполад* * мобильн* приложен* * обнаружен* сбо* } * || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/NotFixed", onlyThisState = true
                intent: /300_NoLink6xxIntent/Not_work || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/NotFixed", onlyThisState = true
                # intent: /300_PageNotOpenLowSpeedNoLinkPPoE/Work || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/Fixed", onlyThisState = true
                q: $yes || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/Fixed", onlyThisState = true
                q: $yesAccess || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/Fixed", onlyThisState = true
                intent: /300_NoLink6xxIntent/Work || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/Fixed", onlyThisState = true
                # intent: /300_PageNotOpenLowSpeedNoLinkPPoE/already_rebooted || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/AlreadyRebooted"
                q: * перезагружал* * || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/AlreadyRebooted"
                intent: /300_NoLink6xxIntent/already_rebooted || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/AlreadyRebooted"
                # intent: /300_PageNotOpenLowSpeedNoLinkPPoE/expect || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/Expect"
                intent: /300_NoLink6xxIntent/expect || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/Expect"
                # intent: /300_PageNotOpenLowSpeedNoLinkPPoE/how_to_check || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/HowToCheckOld"
                intent: /300_NoLink6xxIntent/how_to_check || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/HowToCheck"
                # intent: /300_PageNotOpenLowSpeedNoLinkPPoE/check_for_yourself || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/CheckByYourself"
                intent: /300_NoLink6xxIntent/check_for_yourself || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/CheckByYourself"
                event: noMatch || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/NotFixed"
    
                state: Fixed
                    a: Рад, что у нас все получилось.
                    go!: /Еще вопросы?
                
                state: NotFixed
                    a: Жаль, что не получилось вам помочь.  Подскажите, вы сейчас находитесь дома?
                    if: $.session.newVer
                        buttons:
                            "Да" -> /Transfer/Перевод чата
                            "Нет" -> /PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/NotFixed/NotHome
                    # intent: /300_PageNotOpenLowSpeedNoLinkPPoE/Home || toState = "/Transfer/Перевод чата"
                    q: $yes || toState = "/Transfer/Перевод чата"
                    q: $yesAtHome || toState = "/Transfer/Перевод чата"
                    intent: /300_NoLink6xxIntent/Work || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/Fixed", onlyThisState = true
                    intent: /300_NoLink6xxIntent/Home || toState = "/Transfer/Перевод чата"
                    # intent: /300_PageNotOpenLowSpeedNoLinkPPoE/Not_home || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/NotFixed/NotHome"
                    q: $no || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/NotFixed/NotHome"
                    q: $noAtHome || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/NotFixed/NotHome"
                    intent: /300_NoLink6xxIntent/Not_home || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/NotFixed/NotHome"
                    event: noMatch || toState = "/Transfer/Перевод чата"
                    
                    state: NotHome
                        a: В этом случае не получится провести диагностику с оператором. Когда будете рядом с оборудованием, напишите нам ещё раз.
                        go!: /Еще вопросы?
    
                state: AlreadyRebooted
                    a: Понимаю вас, но я только что перезагрузил оборудование с нашей стороны, именно поэтому потребуется от вас еще раз перезагрузить роутер.
                    go!: /PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/CheckAgain
                
                state: Expect
                    a: Если ситуация не изменится, обязательно напишите мне.
                    go!: /Еще вопросы?
    
                state: HowToCheck
                    a: Попробуйте открыть любой сайт.
                    a: Проверьте, пожалуйста, сейчас услуги работают корректно?
                    go!: /PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/CheckAgain
    
                state: CheckByYourself
                    a: Я проверил оборудование с нашей стороны, а также произвел его перезагрузку. Каких-либо ограничений с нашей стороны нет.
                    go!: /PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/CheckAgain
                    
                state: CheckAgain
                    a: Проверьте, пожалуйста, сейчас услуги работают корректно?
                    if: $.session.newVer
                        buttons:
                            "Не работает " -> /PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/NotFixed
                            "Работает" -> /PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/Fixed
                    
                    # intent: /300_PageNotOpenLowSpeedNoLinkPPoE/Work || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/Fixed"
                    q: $yes || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/Fixed"
                    q: $yesAccess || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/Fixed"
                    intent: /300_NoLink6xxIntent/Work || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/Fixed"
                    # intent: /300_PageNotOpenLowSpeedNoLinkPPoE/Not_work || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/NotFixed"
                    q: $no || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/NotFixed"
                    q: $noAccess || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/NotFixed"
                    intent: /300_NoLink6xxIntent/Not_work || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/NotFixed"
                    event: noMatch || toState = "/PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot/NotFixed"
                
    state: NotAuth
        a: В 90% случаев в подобных ситуациях помогает перезагрузка.
            Пожалуйста, отключите ваш роутер из розетки на 15 секунд, затем подключите обратно и перезагрузите ваши устройства: ноутбук, ПК, планшет или другие девайсы.
        go!: /PageNotOpenLowSpeedNoLinkPPoE/Auth/Reboot/AfterReboot
