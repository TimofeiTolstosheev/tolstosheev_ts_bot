theme: /WhichSubscriptionFee
    state: GoToBalance
        q!: $whichSubscriptionFee
        intent!: /94_WhichSubscriptionFee
        script:
            startIntent('/94_WhichSubscriptionFee');
        go!: /Balance/Balance/CheckAuth
