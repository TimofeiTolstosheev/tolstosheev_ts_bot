theme: /SPAS
    state: GetSpasData
        script:
            $.session.productId = $.session.productId || 5; // по-умолчанию продукт 5 - интернет
            $.session.returnState = $.session.returnState || '/Transfer/Перевод чата'; // если не знаем, куда возвращаться, переведем на оператора
            getSpasData();
        if: $.session.productId == 53
            go!: /SPAS/GetSpasData/TV
        if: $.session.productId == 5
            go!: /SPAS/GetSpasData/Internet
        else:
            script:
                $reactions.transition($.session.returnState);
    
        state: Internet
            if: $.session.spas.internetProblem.technicalSupport || $.session.spas.internetProblem.diagnostics
                go!: /Transfer/Перевод чата
            else:
                script:
                    $reactions.transition($.session.returnState);
        
        state: TV
            if: $.session.spas.tvProblem.flowLoss
                go!: /Transfer/Перевод чата ОЦТП
            else:
                if: $.session.spas.tvProblem.technicalSupport || $.session.spas.tvProblem.drtvTicket
                    go!: /Transfer/Перевод чата
                else:
                    script:
                        $reactions.transition($.session.returnState);
