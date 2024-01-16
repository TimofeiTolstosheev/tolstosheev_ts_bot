theme: /FinanceQuestion
        
    state: FinanceQuestion
        q!: $financeQuestion
        intent!: /5_FinanceQuestion
        script:
            startIntent('/5_FinanceQuestion');
            $.session.intent.stepsCnt++;
            if(countRepeatsInRow() < $injector.noMatchLimit) {
                $reactions.transition('/FinanceQuestion/FinanceQuestion/RouteFinanceQuestion');
            }else{
                $reactions.transition('/FinanceQuestion/FinanceQuestion/RouteFinanceQuestion/ToOperator');
            }
        
        state: RouteFinanceQuestion
            script:
                announceAudio(audioDict.fin3);
        
            state: ToOperator
                script:
                    $.session.intent.resultCode = 1;
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    stopIntent();
                    announceAudio(audioDict.transferToAgentForFurther);
                go!: /Transfer/Transfer
                
            state: CatchAll || noContext = true
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        $reactions.transition('/FinanceQuestion/FinanceQuestion/RouteFinanceQuestion');
                    }else{
                        $reactions.transition('/FinanceQuestion/FinanceQuestion/RouteFinanceQuestion/ToOperator');
                    }
