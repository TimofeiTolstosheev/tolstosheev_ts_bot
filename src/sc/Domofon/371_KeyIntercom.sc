theme: /KeyIntercom
  
    state: KeyIntercomOKC
        q!: $keyIntercom
        intent!: /371_KeyIntercom
        script:
            startIntent('/371_KeyIntercom');
        if: $.session.cifral
            go!: /KeyIntercom/KeyIntercomOKC/Cifral
        else:
            if: $.session.clarifyKey
                go!: /KeyIntercom/KeyIntercomOKC/NotCifral/ToOperator
            else:
                go!: /KeyIntercom/KeyIntercomOKC/NotCifral
        
        state: Cifral
            script:
                announceAudio(audioDict.CifralGo);
                $.session.intent.resultCode = 32;
                $.session.callerInput = 'cifral';
            go!: /Transfer/Transfer
            
        state: NotCifral || modal = true
            script:
                announceAudio(audioDict.KeyClarify);
                $.session.clarifyKey = true;
            q: * (покупк*/купить) * || toState = "/KeyBuy/KeyBuyOKC"
            q: * (привяз*/активир*) * || toState = "/KeyActivate/KeyActivateOKC"
            q: * (не работа*/не срабат*/неисправен/полом*/сломал*) * || toState = "/KeyNotWork/KeyNotWorkOKC"
            
            state: СatchAll || noContext = true
                event: noMatch
                event: speechNotRecognized
                script:
                    if(countRepeatsInRow() < $injector.noMatchLimit){
                        $reactions.transition("/KeyIntercom/KeyIntercomOKC/NotCifral");
                    }else{
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = 6;
                        stopIntent();
                        announceAudio(audioDict.DomofonCons);
                        $reactions.transition("/Transfer/Transfer");
                    }
            
            state: ToOperator
                script:
                    $.session.callerInput = 'DMF';
                    announceAudio(audioDict.DomofonCons);
                go!: /Transfer/Transfer
