theme: /ManageServicePacket
    state: Управление контентом
        q!: $30_ManageServicePacket
        intent!: /30_ManageService
        script:
            startIntent('30_ManageService');
            // для этого интента максимальное количество попаданий в интент 1
            if($.session.intentCount['30_ManageService'] > 1) {
                $reactions.transition("/Transfer/IntentLimitTransfer");
            }
        go!: /ManageServicePacket/Управление контентом/Проверка авторизации
        
        state: Управляй просмотром
            q!: $catchUp
            intent!: /30_ManageService/ViewControl
            script:
                startIntent('ViewControl');
                // для этого интента максимальное количество попаданий в интент 1
                if($.session.intentCount['ViewControl'] > 1) {
                    $reactions.transition("/Transfer/IntentLimitTransfer");
                }
            go!: /ManageServicePacket/Управление контентом/Проверка авторизации/Управляй просмотром

        state: Подписки
            q!: $manageSubscription
            intent!: /30_ManageService/Subscriptions
            script:
                startIntent('Subscriptions');
                // для этого интента максимальное количество попаданий в интент 1
                if($.session.intentCount['Subscriptions'] > 1) {
                    $reactions.transition("/Transfer/IntentLimitTransfer");
                }
            go!: /ManageServicePacket/Управление контентом/Проверка авторизации/Подписки

        state: Антивирус
            q!: $antivirus
            intent!: /30_ManageService/Antivirus
            script:
                startIntent('Antivirus');
                // для этого интента максимальное количество попаданий в интент 1
                if($.session.intentCount['Antivirus'] > 1) {
                    $reactions.transition("/Transfer/IntentLimitTransfer");
                }
            go!: /ManageServicePacket/Управление контентом/Проверка авторизации/Антивирус

        state: Бонус скорости
            q!: $speedBonus
            intent!: /30_ManageService/SpeedBonus
            script:
                startIntent('SpeedBonus');
                // для этого интента максимальное количество попаданий в интент 1
                if($.session.intentCount['SpeedBonus'] > 1) {
                    $reactions.transition("/Transfer/IntentLimitTransfer");
                }
            go!: /ManageServicePacket/Управление контентом/Проверка авторизации/Бонус скорости

        state: Пакеты каналов
            q!: $channelPackage
            intent!: /30_ManageService/ChannelPackages
            script:
                startIntent('ChannelPackages');
                // для этого интента максимальное количество попаданий в интент 1
                if($.session.intentCount['ChannelPackages'] > 1) {
                    $reactions.transition("/Transfer/IntentLimitTransfer");
                }
            go!: /ManageServicePacket/Управление контентом/Проверка авторизации/Пакеты каналов
        
        state: Проверка авторизации
            if: $.session.userAuth
                a: Напишите, пожалуйста, с каким контентом необходима помощь? Пакеты каналов, Бонус скорости, антивирус, управляй просмотром или подписки?
                buttons:
                    "Пакеты каналов" -> /ManageServicePacket/Управление контентом/Проверка авторизации/Пакеты каналов
                    "Бонус скорости" -> /ManageServicePacket/Управление контентом/Проверка авторизации/Бонус скорости
                    "Антивирус" -> /ManageServicePacket/Управление контентом/Проверка авторизации/Антивирус
                    "Подписки" -> /ManageServicePacket/Управление контентом/Проверка авторизации/Подписки
                    "Управляй просмотром" -> /ManageServicePacket/Управление контентом/Проверка авторизации/Управляй просмотром
            else:
                go!: /Transfer/Перевод чата
             
            state: Управляй просмотром
                script:
                    $.session.intent.stepCount++;
                a: Подключить или отключить услугу «Управляй просмотром» вы можете в <a href="https://dom.ru/catchup">личном кабинете</a>.
                go!: /Еще вопросы?
    
            state: Подписки
                script:
                    $.session.intent.stepCount++;
                a: Управлять подписками вы можете в <a href="https://movix.ru/videoteka">личном кабинете</a>.
                go!: /Еще вопросы?
    
            state: Антивирус
                script:
                    $.session.intent.stepCount++;
                a: Управлять подпиской на антивирус вы можете в <a href="https://dom.ru/lk/internet/antivirus">личном кабинете</a>.
                go!: /Еще вопросы?
    
            state: Бонус скорости
                script:
                    $.session.intent.stepCount++;
                a: Подключить или отключить скоростной бонус вы можете в <a href="https://dom.ru/lk/internet/speedup">личном кабинете</a>.
                go!: /Еще вопросы?
    
            state: Пакеты каналов
                script:
                    $.session.intent.stepCount++;
                a: Управлять пакетами каналов вы можете в <a href="https://dom.ru/domru-tv/packages">личном кабинете</a>.
                go!: /Еще вопросы?
                
            state: Другое
                q!: $otherServices
                intent!: /30_ManageService/OtherServices
                script:
                    startIntent('OtherServices')
                    $.session.intent.stepCount++;
                go!: /Transfer/Перевод чата
            
            state: CatchAll || noContext = true
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        $reactions.transition('/ManageServicePacket/Управление контентом/Проверка авторизации');
                    }else{
                        $.session.intent.resultCode = 6;
                        $reactions.transition('/Transfer/Перевод чата');
                    }
