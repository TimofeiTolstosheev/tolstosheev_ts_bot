theme: /DebtQuestionDetails
    state: GoToBalance
        q!: $debtQuestionDetails
        intent!: /95_DebtQuestionDetails
        script:
            startIntent('/95_DebtQuestionDetails');
        go!: /Balance/Balance/CheckAuth
