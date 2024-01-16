theme: /RestoreAgreement
  
    state: RestoreAgreementOKC
        q!: $restoreAgreement
        intent!: /480_RestoreAgreement
        script:
            startIntent('/480_RestoreAgreement');
            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
            $.session.intent.resultCode = 6;
            stopIntent();
            announceAudio(audioDict.perevod_na_okc);
        go!: /Transfer/Transfer
