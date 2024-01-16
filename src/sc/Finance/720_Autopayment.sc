theme: /Autopayment
  
    state: AutopaymentOKC
        q!: $autopay
        intent!: /720_Autopay
        script:
            startIntent('/720_Autopay');
            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
            $.session.intent.resultCode = 6;
            stopIntent();
            announceAudio(audioDict.perevod_na_okc_from_autopayment);
        go!: /Transfer/Transfer
