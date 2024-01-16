theme: /CableReplacement
  
    state: CableReplacementOKC
        q!: $cableReplacement
        intent!: /750_CableReplacement
        script:
            startIntent('/750_CableReplacement');
            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
            $.session.intent.resultCode = 6;
            stopIntent();
            announceAudio(audioDict.perevod_na_okc_from_cable_replacement);
        go!: /Transfer/Transfer
