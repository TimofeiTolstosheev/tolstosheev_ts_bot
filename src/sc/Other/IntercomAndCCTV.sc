theme: /IntercomAndCCTV
    state: Домофония
        q!: $370_IntercomAndCCTV
        q!: $keyIntercom 
        q!: $keyBuy 
        q!: $keyActivate 
        q!: $keyNotWork 
        q!: $domofonActivate 
        q!: $smartDom
        q!: $domofonNotWork
        q!: $CCTV
        intent!: /370_IntercomAndCCTV
        script:
            startIntent('370_IntercomAndCCTV');
        go!: /Transfer/Перевод чата