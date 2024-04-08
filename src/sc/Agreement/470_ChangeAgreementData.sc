theme: /ChangeAgreementData
  
    state: ChangeAgreementDataOKC
        q!: $changeAgreementData
        intent!: /470_ChangeAgreementData
        script:
            startIntent('/470_ChangeAgreementData');
            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
            $.session.intent.resultCode = 6;
            stopIntent();
            announceAudio(audioDict.perevod_na_okc_ChangeAgreementData);
        go!: /Transfer/Transfer
