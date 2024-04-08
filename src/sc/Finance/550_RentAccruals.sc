theme: /RentAccruals
  
    state: RentAccrualsOKC
        q!: $rentAccruals
        intent!: /550_RentAccruals
        script:
            startIntent('/550_RentAccruals');
            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
            $.session.intent.resultCode = 6;
            stopIntent();
            announceAudio(audioDict.perevod_na_okc_RentAccruals);
        go!: /Transfer/Transfer
