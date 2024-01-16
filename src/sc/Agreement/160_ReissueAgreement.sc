theme: /ReissueAgreement
  
    state: ReissueAgreementOKC
        q!: $reissueAgreement
        q!: { $agentRequest * переоформ* }
        intent!: /160_ReissueAgreement
        script:
            startIntent('/160_ReissueAgreement');
            announceAudio(audioDict.perevod_na_okc);
            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
            $.session.intent.resultCode = 6;
            stopIntent();
        go!: /Transfer/Transfer
