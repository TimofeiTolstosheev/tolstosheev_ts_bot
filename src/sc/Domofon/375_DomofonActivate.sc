theme: /DomofonActivate
    
    state: DomofonActivateOKC
        q!: $domofonActivate
        intent!: /375_DomofonActivate
        script:
            startIntent('/375_DomofonActivate');
        if: $.session.cifral
            go!: /DomofonActivate/DomofonActivateOKC/Cifral
        else:
            go!: /DomofonActivate/DomofonActivateOKC/NotCifral
        
        state: Cifral
            script:
                announceAudio(audioDict.CifralGo);
                $.session.intent.resultCode = 32;
                $.session.callerInput = 'dmf_cifral';
            go!: /Transfer/Transfer
            
        state: NotCifral
            script:
                $.session.callerInput = 'DMF';
                $.session.intent.resultCode = 6;
                announceAudio(audioDict.DomofonCons);
            go!: /Transfer/Transfer
