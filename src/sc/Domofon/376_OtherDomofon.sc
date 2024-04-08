theme: /OtherDomofon
    
    state: OtherDomofonOKC
        q!: $otherDomofon
        intent!: /376_OtherDomofon
        script:
            startIntent('/376_OtherDomofon');
        if: $.session.cifral
            go!: /OtherDomofon/OtherDomofonOKC/Cifral
        else:
            go!: /OtherDomofon/OtherDomofonOKC/NotCifral
        
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
