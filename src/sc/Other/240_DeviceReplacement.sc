theme: /DeviceReplacement
  
    state: DeviceReplacementOKC
        q!: $deviceReplacement
        intent!: /240_DeviceReplacement
        script:
            startIntent('/240_DeviceReplacement');
            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
            $.session.intent.resultCode = 6;
            stopIntent();
            announceAudio(audioDict.perevod_na_okc);
        go!: /Transfer/Transfer
