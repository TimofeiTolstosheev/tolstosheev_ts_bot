theme: /HotSpotIssue
  
    state: HotSpotIssueOKC
        q!: $hotSpotIssue
        intent!: /510_HotSpotIssue
        script:
            startIntent('/510_HotSpotIssue');
            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
            announceAudio(audioDict.transferToAgentForFurther);
            $.session.intent.resultCode = 6;
            stopIntent();
        go!: /Transfer/Transfer
