theme: /DomofonNotWork
    
    state: DomofonNotWorkOKC
        q!: $domofonNotWork
        intent!: /378_DomofonNotWork
        script:
            startIntent('/378_DomofonNotWork');
        if: $.session.cifral
            go!: /DomofonNotWork/DomofonNotWorkOKC/Cifral
        else:
            go!: /DomofonNotWork/DomofonNotWorkOKC/NotCifral
        
        state: Cifral
            script:
                announceAudio(audioDict.CifralGo);
                $.session.intent.resultCode = 32;
                $.session.callerInput = 'cifral';
            go!: /Transfer/Transfer
            
        state: NotCifral || modal = true
            script:
                announceAudio(audioDict.DomofonNotWork);
            
            state: СatchAll || noContext = true
                event: noMatch
                event: speechNotRecognized
                script:
                    $.session.callerInput = 'dmftech';
                    $.session.intent.resultCode = 6;
                    announceAudio(audioDict.DomofonTech);
                go!: /Transfer/Transfer