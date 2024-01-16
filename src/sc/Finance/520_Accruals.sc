theme: /Accruals
    state: GoToBalance
        intent!: /520_Accruals
        script:
            startIntent('/520_Accruals');
        go!: /Balance/Balance/CheckAuth
