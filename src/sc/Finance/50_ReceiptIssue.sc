theme: /ReceiptIssue
  
    state: ReceiptIssueOKC
        q!:  $receiptIssue
        intent!: /50_ReceiptIssue
        script:
            startIntent('/50_ReceiptIssue');
            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
            $.session.intent.resultCode = 6;
            stopIntent();
            announceAudio(audioDict.perevod_na_okc_Invoice);
        go!: /Transfer/Transfer
