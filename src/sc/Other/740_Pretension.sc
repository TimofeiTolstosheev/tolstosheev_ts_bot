theme: /Pretension
  
    state: PretensionOKC
        q!: $complaintsAndPretension
        intent!: /740_ComplaintsAndPretension
        script:
            startIntent('/740_ComplaintsAndPretension');
            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
            $.session.intent.resultCode = 6;
            stopIntent();
            announceAudio(audioDict.perevod_na_okc_from_pretension);
        go!: /Transfer/Transfer
