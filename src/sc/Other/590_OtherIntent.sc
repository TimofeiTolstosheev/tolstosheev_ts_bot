theme: /OtherIntent
  
    state: OtherIntentOKC
        q!: $otherIntent
        q!: * $thanksForOtherIntent *
        intent!: /590_OtherIntent
        script:
            startIntent('/590_OtherIntent');
            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
            $.session.intent.resultCode = 6;
            stopIntent();
            announceAudio(audioDict.UnderstoodU);
        go!: /Transfer/Transfer
