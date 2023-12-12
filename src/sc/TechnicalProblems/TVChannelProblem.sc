theme: /TVChannelTVqualityProblem
    state: TVqualityProblem
        q!: $270_TVQualityProblem
        intent!: /270_TVQualityProblem
        script:
            startIntent('270_TVQualityProblem');
        go!: /TVChannelTVqualityProblem/CheckAuth
       
    state: TVchannelProblem
        q!: $290_TVChannelProblem
        intent!: /290_TVChannelProblem
        script:
            startIntent('290_TVChannelProblem');
        go!: /TVChannelTVqualityProblem/CheckAuth
    
    state: CheckAuth
        if: $.session.userAuth
            go!: /TVChannelTVqualityProblem/Auth
        else:
            go!: /TVChannelTVqualityProblem/NotAuth
        a: Проверка авторизации
        
    state: Auth
        script:
            $.session.productId = 53; // 5 - интернет, 53 - телевидение
            checkAccess();
        if: $.session.proactiveProblem
            go!: /Proactive/RouteProactive
        else:
            go!: /TVChannelTVqualityProblem/Auth/SPAS
        
        state: SPAS
            script:
                // если в СПАСе не обнаружится проблем, то вернемся в этот стейт
                $.session.returnState = '/TVChannelTVqualityProblem/Auth/RefreshCKTV';
            go!: /SPAS/GetSpasData
    
        state: RefreshCKTV
            a: Сейчас я обновлю информацию о доступных каналах на договоре, но мне также потребуется ваша помощь: пожалуйста, отключите телевизор и приставку из розетки на 15 секунд, затем подключите обратно. Обязательно проверьте плотность подключения телевизионных кабелей. После этого подождите пару минут и проверьте работу телевидения.
            script:
                refreshCktv();
            a: Проверьте, пожалуйста, после перезагрузки услуги заработали?
            buttons:
                "Работает" -> /TVChannelTVqualityProblem/Auth/RefreshCKTV/Fixed
                "Не работает" -> /TVChannelTVqualityProblem/Auth/RefreshCKTV/AreYouHome
            q: $yes || onlyThisState = true, toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/Fixed"
            q: $yesAccess || onlyThisState = true, toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/Fixed"
            intent: /300_NoLink6xxIntent/Work || onlyThisState = true, toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/Fixed"
            q: $no || toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/AreYouHome"
            q: $noAccess || toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/AreYouHome"
            intent: /300_NoLink6xxIntent/Not_work || toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/AreYouHome"
            event: noMatch || toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/AreYouHome"
    
            state: Fixed
                q: $yes
                q: $yesAccess
                a: Рад, что у нас все получилось.
                go!: /Еще вопросы?
        
            state: AreYouHome
                q: $no
                q: $noAccess
                a: Жаль, что не получилось вам помочь.
                a: Подскажите, вы сейчас находитесь дома?
                buttons:
                    "Да" -> /TVChannelTVqualityProblem/Auth/RefreshCKTV/AreYouHome/Home
                    "Нет" -> /TVChannelTVqualityProblem/Auth/RefreshCKTV/AreYouHome/NotHome
                q: $yes || toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/AreYouHome/Home"
                q: $yesAtHome || toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/AreYouHome/Home"
                intent: /300_NoLink6xxIntent/Home || toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/AreYouHome/Home"
                q: $no || toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/AreYouHome/NotHome"
                q: $noAtHome || toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/AreYouHome/NotHome"
                intent: /300_NoLink6xxIntent/Not_home || toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/AreYouHome/NotHome"
                event: noMatch || toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/AreYouHome/Home"
            
                state: Home
                    go!: /Transfer/Перевод чата
            
                state: NotHome
                    a: В этом случае не получится провести диагностику с оператором. Когда будете рядом с оборудованием, напишите нам ещё раз.
                    go!: /Еще вопросы?
                    
    state: NotAuth
        a: В 90% случаев в подобных ситуациях помогает перезагрузка
                  Пожалуйста, отключите ваши телевизор и приставку (если используете её) из розетки на 15 секунд, затем подключите обратно. Проверьте, плотно ли подключены телевизионные кабели, нет ли на них повреждений.
        a: Проверьте, пожалуйста, после перезагрузки услуги заработали?
        buttons:
            "Работает" -> /TVChannelTVqualityProblem/Auth/RefreshCKTV/Fixed
            "Не работает" -> /TVChannelTVqualityProblem/Auth/RefreshCKTV/AreYouHome
        q: $yes || onlyThisState = true, toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/Fixed"
        q: $yesAccess || onlyThisState = true, toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/Fixed" 
        intent: /300_NoLink6xxIntent/Work || onlyThisState = true, toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/Fixed"
        q: $no || toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/AreYouHome"
        q: $noAccess || toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/AreYouHome"
        intent: /300_NoLink6xxIntent/Not_work || toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/AreYouHome"
        event: noMatch || toState = "/TVChannelTVqualityProblem/Auth/RefreshCKTV/AreYouHome"
        