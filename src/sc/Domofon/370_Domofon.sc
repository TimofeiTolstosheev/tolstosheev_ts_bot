theme: /Domofon
  
    state: DomofonOKC
        q!: $commonDomofon
        intent!: /370_Domofon
        script:
            startIntent('/370_Domofon');
        if: $.session.cifral
            go!: /Domofon/DomofonOKC/Cifral
        else:
            if: $.session.clarifyDomofon
                go!: /Domofon/DomofonOKC/NotCifral/ToOperator
            else:
                go!: /Domofon/DomofonOKC/NotCifral
        
        state: Cifral
            script:
                announceAudio(audioDict.CifralGo);
                $.session.intent.resultCode = 32;
                $.session.callerInput = 'cifral';
            go!: /Transfer/Transfer
            
        state: NotCifral
            script:
                announceAudio(audioDict.Domofon);
                $.session.clarifyDomofon = true;
        
            state: СatchAll || noContext = true
                event: noMatch
                event: speechNotRecognized
                script:
                    $.session.intent.stepsCnt++;
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        $reactions.transition("/Domofon/DomofonOKC/NotCifral");
                    }else{
                        $reactions.transition("/Domofon/DomofonOKC/NotCifral/ToOperator");
                    }
            
            state: ToOperator
                script:
                    $.session.intent.resultCode = 6;
                    $.session.callerInput = 'DMF';
                    announceAudio(audioDict.DomofonCons);
                go!: /Transfer/Transfer

