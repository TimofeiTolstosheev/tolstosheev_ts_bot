theme: /SmartDom
    
    state: SmartDomOKC
        q!: $smartDom
        intent!: /377_SmartDom
        script:
            startIntent('/377_SmartDom');
        if: $.session.cifral
            go!: /SmartDom/SmartDomOKC/Cifral
        else:
            go!: /SmartDom/SmartDomOKC/NotCifral
        
        state: Cifral
            script:
                announceAudio(audioDict.CifralGo);
                $.session.intent.resultCode = 32;
                $.session.callerInput = 'cifral';
            go!: /Transfer/Transfer
            
        state: NotCifral || modal = true
            script:
                announceAudio(audioDict.SmartDom);
            
            state: СatchAll || noContext = true
                event: noMatch
                event: speechNotRecognized
                script:
                    $.session.intent.resultCode = 6;
                    $.session.callerInput = 'dmftech';
                    announceAudio(audioDict.DomofonTech);
                go!: /Transfer/Transfer
