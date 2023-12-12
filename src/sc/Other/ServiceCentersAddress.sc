theme: /ServiceCentersAddress
    state: Адреса СЦ
        q!: $20_ServiceCentersAddressIntent
        intent!: /20_ServiceCentersAddress
        script:
            startIntent('20_ServiceCentersAddress');
        a: Адреса и время работы офисов вы можете посмотреть <a href="https://perm.dom.ru/service/contact">здесь</a>.
        go!: /Еще вопросы?
