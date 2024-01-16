patterns:

    $commonInfoTariff = ( * { (@TariffSingular/пакет) * @Internet } *
        | * { (@TariffSingular/пакет) * @Television} *
        | * { (@TariffSingular/пакет) * @Telephone} *
        | * { (@TariffSingular/пакет) * @DomRu} *
        | * { безлимитн* * @Internet } *
        | * { безлимитн* * @Telephone } *
        | * { есть * канал* * @TariffSingular } *
        | * { есть * канал* * договор* } *
        | * { имеет* * канал* * @TariffSingular } *
        | * { имеет* * канал* * договор* } *
        | * { узна* * канал* * @TariffSingular } *
        | * { узна* * канал* * договор* } *
        | * { перечисл* * канал* * @TariffSingular } *
        | * { перечисл* * канал* * договор* } *
        | * { @TariffSingular * @SynonymsForAgreement } *
        | * { @Modal * узна* * @TariffSingular } *
        | * { @Modal * перечисл* * @TariffSingular } *
        | * { @Modal * назов* * @TariffSingular } *
        | * { @Modal * названи* * @TariffSingular } *
        | * { @Modal * @TariffSingular * @Beneficiary } *
        | * { @Modal * договор* * @Beneficiary } *
        | * { @TariffSingular * остается * @duckling.number * ~рубль } *
        | * { @TariffSingular * остался * @duckling.number * ~рубль } *
        | * { @TariffSingular * будет * @duckling.number * ~рубль } *
        | * { есть * @TariffSingular } *
        | * по поводу @TariffSingular
        | * { вопрос* * @TariffSingular } *
        | * { [ @Operator $weight<+0.3>] @TariffSingular } *
        | * { [ @Operator $weight<+0.3>] * вопрос* * @TariffSingular } *
        | * { [ @Operator $weight<+0.3>] * @Talk * @TariffSingular } *
        | * { консульт* * @TariffSingular } *
        | * { @Know * скорост* @TariffSingular } *
        | * { @Know * скорост* @AllServices } * )

    $changeTariff = (  * { @Modal * больше * канал* } *
        | * { @Modal * меньше * канал* } *
        | * { @Modal * выбра* * @TariffSingular } *
        | * { @Modal * выбра* * скорост* } *
        | * { @Modal * скорост* * быстрее} *
        | * { @Modal * скорост* * выше} *
        | * { @Modal * скорост* * лучше} *
        | * { снижени* * ежемесяч* * начислени* } *
        | * { снижени* * ежемесяч* * сумм* } *
        | * { снижени* * ежемесяч* * @Payment } *
        | * { снижени* * ежемесяч* * @SubscriptionFee } *
        | * { снизить * ежемесяч* * начислени* } *
        | * { снизить * ежемесяч* * сумм* } *
        | * { снизить * ежемесяч* * @Payment } *
        | * { снизить * ежемесяч* * @SubscriptionFee } *
        | * { снижать * ежемесяч* * начислени* } *
        | * { cнижать * ежемесяч* * сумм* } *
        | * { снижать * ежемесяч* * @Payment } *
        | * { снижать * ежемесяч* * @SubscriptionFee } *
        | * { @Change * @TariffSingular } *
        | * { [ @Operator $weight<+0.3>] * @Change * @TariffSingular } *
        | * { @Modal @Change * @TariffSingular } *
        | * { [@Modal] @Change * @TariffSingular * @AllServices } *
        | * { возможност* * @Change * @TariffSingular } *
        | * { (не @Successful) * @Change * @TariffSingular } * 
        | * { пытаюсь * @Change * @TariffSingular } * 
        | * { попытк* * @Change * @TariffSingular } *
        | * { увелич* скорост* @AllServices } * 
        | * { увелич* скорост* @TariffSingular } * 
        | * { перейти * друг* @TariffSingular } * 
        | * { переход* * друг* @TariffSingular } * 
        | * { перейд* * друг* @TariffSingular } * 
        | * { @TariffSingular * *дешев*} * 
        | * { перейти * @TariffSingular * *дешев*} *
        | * { перейд* * @TariffSingular * *дешев*} *
        | * { @Know * @TariffSingular * *дешев*} *
        | * { [очень] больш* * @Payment } * { *брать * @Tariff * *дешев* } *
        | * { [очень] больш* * @Payment } * { *берите * @Tariff * *дешев* } *)

    $tariffInfo = ( * @TariffPlural *
        | * { ~база * @TariffPlural } *
        | * { линейк* * @TariffPlural } *
        | * { компан* * @TariffPlural } *
        | * { [ @Operator $weight<+0.3>] * вопрос* * @TariffPlural } *
        | * { [ @Operator $weight<+0.3>] * @Talk * @TariffPlural } *
        | * { консультац* * @TariffPlural } *
        | * { информац* * @TariffPlural } *
        | * { други* * @TariffPlural } *
        | * { актуальны* * @TariffPlural } *
        | * { [ @Operator $weight<+0.3>] * нов* * @TariffPlural } *
        | * { [ @Operator $weight<+0.3>] * нов* * @TariffSingular } *
        | * { назови* * @TariffPlural } *
        | * { перечисл* * @TariffPlural } *
        | * { @Know * @TariffPlural } *
        | * { почему * @TariffPlural } *
        | * { каки* * @TariffPlural } *
        | @TariffPlural
        | * { @TariffPlural * @Internet } *
        | * { @TariffPlural * @Television } *
        | * { @TariffPlural * @Telephone } *
        | * { @TariffPlural * договор* } *
        | * { @TariffPlural * комплексн* предложен* } *
        | * { @TariffPlural * комплекс* } *
        | * { @TariffPlural * скидк* } *
        | * { @TariffPlural * акци* } *
        | * { @TariffPlural * льгот* } *
        | * { @TariffPlural * @DomRu } *
        | * { @TariffPlural * @PersonalAccount } *  
        | * { какой * самый * дешев* * @TariffSingular } * 
        | * какой * { @TariffSingular * *дешев* * @My } * 
        | * { (не устраив*) * [@My] * @TariffSingular } *  )

    $tariffType = ( * { @My * @TariffSingular} *
        | * { какой * нас * @TariffSingular} *
        | * { какой * наш * @TariffSingular} *
        | * { какой * нашему * @TariffSingular} *
        | * { какой * свой * @TariffSingular} *
        | * { какой * своего * @TariffSingular} *
        | * { какой * своему * @TariffSingular} *
        | * { какой * своим * @TariffSingular} *
        | * { какой * своём * @TariffSingular} *
        | * { какой * своем * @TariffSingular} *
        | * { какой * @My * @TariffSingular } *
        | * { вопрос* * @My * @TariffSingular } *
        | * { консульт* * @My * @TariffSingular } *
        | * { информац* * @My * @TariffSingular } *
        | * { узна* * @My * @TariffSingular } *
        | * { разъясни* * @My * @TariffSingular } *
        | * { вход* * @My * @TariffSingular } * 
        | * { что * @TariffSingular * @My * установ* } * )