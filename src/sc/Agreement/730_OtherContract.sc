theme: /OtherContract
  
    state: OtherContractOKC
        q!: $otherContract
        intent!: /730_OtherContract
        script:
            startIntent('/730_OtherContract');
            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
            $.session.intent.resultCode = 6;
            stopIntent();
            announceAudio(audioDict.perevod_na_okc_from_othercontract);
        go!: /Transfer/Transfer
