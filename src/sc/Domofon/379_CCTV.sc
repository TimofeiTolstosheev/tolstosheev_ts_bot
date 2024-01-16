theme: /CCTV
    
    state: CCTVOKC
        q!: $CCTV
        intent!: /379_CCTV
        script:
            startIntent('/379_CCTV');
        if: $.session.cifral
            go!: /CCTV/CCTVOKC/Cifral
        else:
            go!: /CCTV/CCTVOKC/NotCifral
        
        state: Cifral
            script:
                announceAudio(audioDict.CifralGo);
                $.session.intent.resultCode = 32;
                $.session.callerInput = 'cifral';
            go!: /Transfer/Transfer
       
        state: NotCifral || modal = true
            script:
                announceAudio(audioDict.Videonabludenie);
        
            state: СatchAll || noContext = true
                event: noMatch
                event: speechNotRecognized
                script:
                    $.session.callerInput = 'dmftech';
                    $.session.intent.resultCode = 6;
                    announceAudio(audioDict.DomofonTech);
                go!: /Transfer/Transfer
