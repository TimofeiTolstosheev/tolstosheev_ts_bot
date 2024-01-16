theme: /TVEquipmentProblem
  
    state: TVEquipmentProblem
        q!: $tvEquipmentProblem
        intent!: /TV/485_TVEquipmentProblem
        script:
            startIntent('/485_TVEquipmentProblem');
        go!: ../CheckServices
    
    state: CheckServices
        script:
            $.session.productId = 53; // 5 - интернет, 53 - ТВ
            $.session.reboot = 0;
            // параметры для await-экшна
            $.session.awaitAction = $.session.awaitAction || {};
            $.session.awaitAction.returnState = $context.currentState;
            $.session.awaitAction.action = "checkServices";
            $.session.awaitAction.readTimeout = 4000;
            $.session.awaitAction.audio = audioDict.initialWait;
            if($.session.awaitAction.status || $.session.proactiveResult){
                delete $.session.awaitAction;
                if($.session.proactiveProblems){
                    $reactions.transition("/Proactive/RouteProactive");
                }else{
                    $.session.intent.resultCode = 6;
                    $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                    stopIntent();
                    announceAudio(audioDict.transferToAgentForFurther);
                    $reactions.transition("/Transfer/Transfer");
                }

            }else{
                $reactions.transition("/AwaitAction/RunAction");
            }