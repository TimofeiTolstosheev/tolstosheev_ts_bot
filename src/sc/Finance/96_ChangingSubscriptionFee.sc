theme: /ChangingSubscriptionFee
    state: GoToBalance
        q!: $changingSubscriptionFee
        intent!: /96_ChangingSubscriptionFee
        script:
            startIntent('/96_ChangingSubscriptionFee');
        go!: /Balance/Balance/CheckAuth
