theme: /Recalculation
  
    state: RecalculationOKC
        q!: $recalculation
        intent!: /560_Recalculation
        script:
            startIntent('/560_Recalculation');
            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
            $.session.intent.resultCode = 6;
            stopIntent();
            announceAudio(audioDict.perevod_na_okc);
        go!: /Transfer/Transfer