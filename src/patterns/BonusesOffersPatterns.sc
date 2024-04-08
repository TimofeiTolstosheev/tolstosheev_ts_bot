patterns:

    $manageSubscription = ( * @CatchUp *
        | * @Subscription *
        | * { (вопрос*/информац*/консультац*) * @CatchUp } *
        | * { (вопрос*/информац*/консультац*) * @Subscription } *
        | * { (платн*/бесплатн*/дополнительн*) * @Subscription } *
        | * { (удали*/удале*) * @CatchUp } *
        | * { (удали*/удале*) * @Subscription } *
        | * { *блокиров* * @CatchUp } *
        | * { *блокиров* * @Subscription } *
        | * { @Know * @CatchUp } *
        | * { @Know * @Subscription } *
        | * { @CatchUp * (@TVSet/@Television) } *
        | * { @Subscription * (@TVSet/@Television) } *
        | * { активац* * @CatchUp } *
        | * { активац* * @Subscription } *
        | * { активир* * @CatchUp } *
        | * { активир* * @Subscription } *
        | * { контрол* * @CatchUp } *
        | * { контрол* * @Subscription } *
        | * { не работ* * @Subscription } *
        | * { не (удали*/удале*) * @CatchUp } *
        | * { не (удали*/удале*) * @Subscription } *
        | * { не *блокиров* * @CatchUp } *
        | * { не *блокиров* * @Subscription } *
        | * { не активир* * @CatchUp } *
        | * { не активир* * @Subscription } *
        | * { не использ* * @CatchUp } *
        | * { не использ* * @Subscription } *
        | * { не отключ* * @CatchUp } *
        | * { не отключ* * @Subscription } *
        | * { не подключ* * @CatchUp } *
        | * { не подключ* * @Subscription } *
        | * { не работ* * @CatchUp } *
        | * { опци* * @CatchUp } *
        | * { опци* * @Subscription } *
        | * { отключ* * @TVServices } *
        | * { поддержк* * @CatchUp } *
        | * { поддержк* * @Subscription } *
        | * { подключ* * @CatchUp } *
        | * { подключ* * @Subscription } *
        | * { прекратит* * @CatchUp } *
        | * { прекратит* * @Subscription } *
        | * { работ* * @CatchUp } *
        | * { работ* * @Subscription } *
        | * { управл* * @Subscription } *
        | * { услуг* * @CatchUp } *
        | * { услуг* * @Subscription } *
        | * { уточн* * @CatchUp } *
        | * { уточн* * @Subscription } *
        | * { приостанов* * @Subscription } *
        | * { (убери*/убрать/убирай*) * @Subscription } *
        | * { пролонг* * @Subscription } *
        | * { *обнов* * @Subscription } *
        | * { приостанов* * @CatchUp } *
        | * { (убери*/убрать/убирай*) * @CatchUp } *
        | * { (продли*/продле*) * @CatchUp } *
        | * { пролонг* * @CatchUp } *
        | * { *обнов* * @CatchUp } *
        | * { выключ* * @CatchUp } *
        | * { выключ* * @Subscription } *
        | * { не выключ* * @CatchUp } *
        | * { не выключ* * @Subscription } *
        | * { подключ* * платн* @Services } * 
        | * { без [@My] ведома * @CatchUp } *
        | * { без [@My] ведома * @Subscription } *
        | * { без [@My] ведома * платн* @Services } * )

    $discount = ( * { @Discount * ежемесячн* * @Payment } *
        | * { @Discount * месячн* * @Payment } *
        | * { льгот* * ежемесячн* * @Payment } *
        | * { льгот* * месячн* * @Payment } *
        | * { @Discount * меньше * *платить } *
        | * { @Discount * мало * *платить } *
        | * { @Discount * меньше * оплачива* } *
        | * { @Discount * мало * оплачива* } *
        | * { льгот* * меньше * *платить } *
        | * { льгот* * мало * *платить } *
        | * { льгот* * меньше * оплачива* } *
        | * { льгот* * мало * оплачива* } *
        | * { @Discount * @Tariff } *
        | * { @Discount * счет* } *
        | * { льгот* * @Tariff } *
        | * { льгот* * счет* } *
        | * { @Discount * уменьшени* * стоимост* } *
        | * { @Discount * уменьшени* * @Payment } *
        | * { @Discount * уменьшени* * сумм* } *
        | * { @Discount * уменьшени* * начислен* } *
        | * { @Discount * *нижени* * стоимост* } *
        | * { @Discount * *нижени* * @Payment } *
        | * { @Discount * *нижени* * сумм* } *
        | * { @Discount * *нижени* * начислен* } *
        | * { льгот* * уменьшени* * стоимост* } *
        | * { льгот* * уменьшени* * @Payment } *
        | * { льгот* * уменьшени* * сумм* } *
        | * { льгот* * уменьшени* * начислен* } *
        | * { льгот* * *нижени* * стоимост* } *
        | * { льгот* * *нижени* * @Payment } *
        | * { льгот* * *нижени* * сумм* } *
        | * { льгот* * *нижени* * начислен* } *
        | * { @Discount * @duckling.number процент* } *
        | * { льгот* * @duckling.number процент* } *
        | * { @Discount * @Services  } *
        | * { льгот* * @Services  } *
        | * { @Discount * @Beneficiary } *
        | * { льгот* * @Beneficiary } *
        | * { @Discount * @Services } *
        | * { льгот* * @Services } *
        | * { @Know * @Discount } *
        | * { @Know * льгот* } *
        | * { будет * @Discount } *
        | * { будут * @Discount } *
        | * { был* * @Discount } *
        | * { был* * льгот* } *
        | * { будет * льгот* } *
        | * { будут * льгот* } *
        | * { какие * @Discount } *
        | * { какая * @Discount } *
        | * { какую * @Discount } *
        | * { какой * @Discount } *
        | * { какие * льгот* } *
        | * { какая * льгот* } *
        | * { какую * льгот* } *
        | * { какой * льгот* } *
        | * { @Tariff * @Beneficiary } *
        | * { вариант* @Beneficiary } *
        | * { @Modal * (@Discount/льгот*) } *
        | * { *делайте * (@Discount/льгот*) } *
        | * { *делать * (@Discount/льгот*) } *
        | * { активир* * (@Discount/льгот*) } *
        | * { есть * (@Discount/льгот*) } *
        | * { подключ* * (@Discount/льгот*) } *
        | * { продле* * (@Discount/льгот*) } *
        | * { продли* * (@Discount/льгот*) } *
        | * { пролонг* * (@Discount/льгот*) } *
        | * { существу* * (@Discount/льгот*) } *
        | * { без [@My] ведома * (@Discount/льгот*) } *
        | * как * сэкономить * 
        | * { (не устраив*) * @Payment * @Beneficiary } * )

    $channelPackage = ( * { @Know * @TVChannelPackageName } *
        | * { (доп/дополнительн*) * @TVChannelName } *
        | * { (доп/дополнительн*) * @TVChannelName } *
        | * { (доп/дополнительн*) * пакет* @TVChannel } *
        | * { платн* * @TVChannel } *
        | * { (продли*/продле*) * @TVChannelPackageName } *
        | * { (спорт*/платн*) * @TVChannelName } *
        | * { (спорт*/платн*) * @TVChannelPackageName } *
        | * { убери* * @TVChannelPackageName } *
        | * { убрать * @TVChannelPackageName } *
        | * { убирай* * @TVChannelPackageName } *
        | * { *обнов* * @TVChannelPackageName } *
        | * { пакет* @TVChannel * @Television } *
        | * { пакет* @TVChannel * @TVChannelName } *
        | * { подключ* * @TVChannelPackageName } *
        | * { подключ* * некотор* * @TVChannel } *
        | * { подключ* * нескольк* * @TVChannel } *
        | * { подключ* * пакет* * @TVChannel } *
        | * { приостанов* @TVChannelPackageName } *
        | * { пролонг* * @TVChannelPackageName } *
        | * { вопрос* * @TVChannelPackageName } *
        | * { информац* * @TVChannelPackageName } *
        | * { консультац* * @TVChannelPackageName } *
        | * { удали* * @TVChannelPackageName } *
        | * { удале* * @TVChannelPackageName } *
        | * { *блокиров* * @TVChannelPackageName } *
        | * { @TVChannelPackageName * (@TVSet/@Television) } *
        | * { активац* * @TVChannelPackageName } *
        | * { активир* * @TVChannelPackageName } *
        | * { контрол* * @TVChannelPackageName } *
        | * { не (удали*/удале*) * @TVChannelPackageName } *
        | * { не *блокиров* * @TVChannelPackageName } *
        | * { не активир* * @TVChannelPackageName } *
        | * { не использ* * @TVChannelPackageName } *
        | * { не отключ* * @TVChannelPackageName } *
        | * { не подключ* * @TVChannelPackageName } *
        | * { не работ* * @TVChannelPackageName } *
        | * { опци* * @TVChannelPackageName } *
        | * { отключ* * @TVChannelPackageName } *
        | * { поддержк* * @TVChannelPackageName } *
        | * { прекратит* * @TVChannelPackageName } *
        | * { работ* * @TVChannelPackageName } *
        | * { управл* * @TVChannelPackageName } *
        | * { услуг* * @TVChannelPackageName } *
        | * { уточн* * @TVChannelPackageName } *
        | * { @Know * пакет* @TVChannel } *
        | * { платн* * @TVChannel } *
        | * { (продли*/продле*) * пакет* @TVChannel } *
        | * { (спорт*/платн*) * пакет* @TVChannel } *
        | * { убери* * пакет* @TVChannel } *
        | * { убрать * пакет* @TVChannel } *
        | * { убирай* * пакет* @TVChannel } *
        | * { *обнов* * пакет* @TVChannel } *
        | * { подключ* * пакет* @TVChannel } *
        | * { приостанов* пакет* @TVChannel } *
        | * { пролонг* * пакет* @TVChannel } *
        | * { вопрос* * пакет* @TVChannel } *
        | * { информац* * пакет* @TVChannel } *
        | * { консультац* * пакет* @TVChannel } *
        | * { удали* * пакет* @TVChannel } *
        | * { удале* * пакет* @TVChannel } *
        | * { *блокиров* * пакет* @TVChannel } *
        | * { пакет* @TVChannel * (@TVSet/@Television) } *
        | * { активац* * пакет* @TVChannel } *
        | * { активир* * пакет* @TVChannel } *
        | * { контрол* * пакет* @TVChannel } *
        | * { не (удали*/удале*) * пакет* @TVChannel } *
        | * { не *блокиров* * пакет* @TVChannel } *
        | * { не активир* * пакет* @TVChannel } *
        | * { не использ* * пакет* @TVChannel } *
        | * { не отключ* * пакет* @TVChannel } *
        | * { не подключ* * пакет* @TVChannel } *
        | * { не работ* * пакет* @TVChannel } *
        | * { опци* * пакет* @TVChannel } *
        | * { отключ* * пакет* @TVChannel } *
        | * { поддержк* * пакет* @TVChannel } *
        | * { прекратит* * пакет* @TVChannel } *
        | * { работ* * пакет* @TVChannel } *
        | * { управл* * пакет* @TVChannel } *
        | * { услуг* * пакет* @TVChannel } *
        | * { уточн* * пакет* @TVChannel } * 
        | * { без [@My] ведома * @TVChannelPackageName } * 
        | * { отключ* * @TVChannel * (не @Need) } * 
        | * { выключ* * @TVChannel * (не @Need) } *
        | * { удал* * @TVChannel * (не @Need) } *  )

    $newBonuses = ( * { (бонус*/балл*) * @AllServices } *
        | * { (бонус*/балл*) * @SynonymsForAgreement } *
        | * { *блокиров* * балл* } *
        | * { *блокиров* * бонус* } *
        | * { @Know * балл* } *
        | * { @Know * бонус* } *
        | * { *обнов* * балл* } *
        | * { *обнов* * бонус* } *
        | * { *регистр* * программ* лояльност*} *
        | * { актив* * балл* } *
        | * { актив* * бонус* } *
        | * { балл* * @Beneficiary } *
        | * { балл* * @DomRu } *
        | * { балл* * @PersonalAccount } *
        | * { балл* * @Services } *
        | * { балл* * @SubscriptionFee} *
        | * { балл* * @SynonymsForAgreement } *
        | * { балл* * @Telephone } *
        | * { балл* * @Television } *
        | * { балл* * кешбэк } *
        | * { бонус* * @Beneficiary } *
        | * { бонус* * @DomRu } *
        | * { бонус* * @PersonalAccount } *
        | * { бонус* * @Services } *
        | * { бонус* * @SubscriptionFee} *
        | * { бонус* * @SynonymsForAgreement } *
        | * { бонус* * @Telephone } *
        | * { бонус* * @Television } *
        | * { бонус* * кешбэк } *
        | * { бонус* благодарност* } *
        | * { бонус* лояльност*} *
        | * { бонус* льгот*} *
        | * { бонус* программ*} *
        | * { бонусн* баланс* } *
        | * { будет * компенсац* } *
        | * { будет * перерасчет* } *
        | * { вариант* * акци* } *
        | * { выключ* * акци* } *
        | * [куда] { исчез* * балл* } *
        | * [куда] { исчез* * бонус* } *
        | * { подключ* * @Subscription * балл* } *
        | * { подключ* * @Subscription * бонус* } *
        | * { не *блокиров* * балл* } *
        | * { не *блокиров* * бонус* } *
        | * { не актив* * балл* } *
        | * { не актив* * бонус* } *
        | * { не выключ* * балл* } *
        | * { не выключ* * бонус* } *
        | * { не отключ* * балл* } *
        | * { не отключ* * бонус* } *
        | * { не подключ*  * программ* бонус*} *
        | * { не подключ*  * программ* лояльност* } *
        | * { не подключ*  * программ* привилег*} *
        | * { не подключ* * балл* } *
        | * { не подключ* * бонус* } *
        | * { не работ* * балл* } *
        | * { не работ* * бонус* } *
        | * { не удале* * балл* } *
        | * { не удале* * бонус* } *
        | * { не удали* * балл* } *
        | * { не удали* * бонус* } *
        | * { отключ* * балл* } *
        | * { отключ* * бонус* } *
        | * { подключ* * балл* } *
        | * { подключ* * бонус* } *
        | * { подключ* * программ* бонус*} *
        | * { подключ* * программ* лояльност* } *
        | * { подключ* * программ* привилег*} *
        | * { получ* * балл* } *
        | * { получ* * бонус* } *
        | * { потратить * балл* } *
        | * { потратить * бонус* } *
        | * [куда] { пропал* * балл* } *
        | * [куда] { пропал* * бонус* } *
        | * { приветственн* бонус* } *
        | * { программ* лояльност*} *
        | * { программ* привилег*} *
        | * { продле* * балл* } *
        | * { продле* * бонус* } *
        | * { продли* * балл* } *
        | * { продли* * бонус* } *
        | * { пролонг* * балл* } *
        | * { пролонг* * бонус* } *
        | * { рекламн* * балл* } *
        | * { рекламн* * бонус* } *
        | * { сколько * (бонус*/балл*) * @AllServices } *
        | * { сколько * балл* * @AllServices } *
        | * { сколько * бонус* * @AllServices } *
        | * { убери* * балл* } *
        | * { убери* * бонус* } *
        | * { убра* * акци* } *
        | * { убра* * балл* } *
        | * { убра* * бонус* } *
        | * { удале* * балл* } *
        | * { удале* * бонус* } *
        | * { удали* * балл* } *
        | * { удали* * бонус* } *
        | * { управл* * балл* } *
        | * { управл* * бонус* } *
        | * { уточн* * балл* } *
        | * { уточн* * бонус* } *
        | * сорри бонус* * 
        | * { без [@My] ведома * (бонус*/балл*) } * 
        | * { скорост* бонус* } * )

    $otherPromo = ( * { акци* * @PersonalAccount} *
        | * @LookEverywhere *
        | * { *блокиров* * акци* } *
        | * { @Know * акци* } *
        | * { *обнов* * акци* } *
        | * { актив* * акци* } *
        | * { акци* * @Beneficiary } *
        | * { акци* * @DomRu } *
        | * { акци* * @Services } *
        | * { акци* * @SubscriptionFee} *
        | * { акци* * @SynonymsForAgreement } *
        | * { акци* * @Telephone } *
        | * { акци* * @Television } *
        | * { акци* * кешбэк } *
        | * { не *блокиров* * акци* } *
        | * { не актив* * акци* } *
        | * { не выключ* * акци* } *
        | * { не отключ* * акци* } *
        | * { не подключ* * акци* } *
        | * { не работ* * акци* } *
        | * { не удале* * акци* } *
        | * { не удали* * акци* } *
        | * { отключ* * акци* } *
        | * { отключ* * доп услуг* } *
        | * { отключ* * дополнительн* услуг* } *
        | * { подключ* * акци* } *
        | * { получ* * акци* } *
        | * { прекратит* * акци* } *
        | * { приостан* * акци* } *
        | * { [проблем*] * продле* * акци* } *
        | * { [ @Operator $weight<+0.3>] * продле* * акци* } *
        | * { [ @Operator $weight<+0.3>] * продли* * акци* } *
        | * { пролонг* * акци* } *
        | * { рекламн* * акци* } *
        | * { убери* * акци* } *
        | * { удале* * акци* } *
        | * { удали* * акци* } *
        | * { управл* * акци* } *
        | * { уточн* * акци* } * 
        | * { без [@My] ведома * акци* } * 
        | * { платн* * акци* } *
        | * { платн* * опци* } * 
        | * { @Operator * акци* } * 
        | * { (*дела*/*ключ*) * @StaticIP } * )
        
    $antivirus = ( * @Antivirus *
        | * { актив* * @Antivirus } *
        | * { консульт* * @Antivirus } *
        | * { вопрос* * @Antivirus } *
        | * { помощ* * @Antivirus } *
        | * { назови* * @Antivirus } *
        | * { перечисл* * @Antivirus } *
        | * { добав* * @Antivirus } *
        | * { @Know * @Antivirus } *
        | * { (найти/находить) * @Antivirus } *
        | * { настро* * @Antivirus }*
        | * { настра* * @Antivirus }*
        | * { не работа* * @Antivirus } *
        | * { не заработа* * @Antivirus } *
        | * { не срабат* * @Antivirus } *
        | * { неполадк* * @Antivirus } *
        | * { сложност* * @Antivirus } *
        | * { отключ* * @Antivirus } *
        | * { (перебой/перебои) * @Antivirus } *
        | * { *плат* * @Antivirus } *
        | * { @Payment * @Antivirus } *
        | * { приостановит* * @Antivirus } *
        | * { проблем* * @Antivirus } *
        | * { жалоб* * @Antivirus } *
        | * { ошибк* * @Antivirus } *
        | * { продлит* * @Antivirus } *
        | * { продлев* * @Antivirus } *
        | * { продолж* * @Antivirus } *
        | * { продлен* * @Antivirus } *
        | * { расскаж* * @Antivirus } *
        | * { рассказ* * @Antivirus } *
        | * { провер* * @Antivirus } *
        | * { (убрать/убери*) * @Antivirus } *
        | * { (удали*/удале*) * @Antivirus } *
        | * { установ* * @Antivirus } *
        | * { устанав* * @Antivirus } *
        | * { (что/что то/что-то) * случил* @Antivirus } *
        | * { *блокиров* * @Antivirus } *
        | * { *информ* * @Antivirus } *
        | * { $techSupport * @Antivirus } *
        | * { включ* * @Antivirus } *
        | * { выкидыв* * @Antivirus } *
        | * { вылета* * @Antivirus } *
        | * { доступ* * @Antivirus } *
        | * { есть * @Antivirus } *
        | * { запуск* * @Antivirus } *
        | * { исчез* * @Antivirus } *
        | * { не [@Successful] войти * @Antivirus } *
        | * { не [@Successful] зайти * @Antivirus } *
        | * { не [@Successful] подключить * @Antivirus } *
        | * { не получ* * войти * @Antivirus } *
        | * { не получ* * зайти * @Antivirus } *
        | * { не получ* * подключить * @Antivirus } *
        | * { не подключ* * @Antivirus } *
        | * { обруб* * @Antivirus } *
        | * { *ключи* * @Antivirus } *
        | * { отмен* * @Antivirus } *
        | * { отруб* * @Antivirus } *
        | * { перестал* * вход* * @Antivirus } *
        | * { перестал* * заход* * @Antivirus } *
        | * { перестал* * ключ* * @Antivirus } *
        | * { перестал* * работа* * @Antivirus } *
        | * { перестал* * срабат* * @Antivirus } *
        | * { подключ* * @Antivirus } *
        | * { *проб* * @Antivirus } *
        | * { *тестить * @Antivirus } *
        | * { пропал* * @Antivirus } *
        | * { *тестировать * @Antivirus } *
        | * { существ* * @Antivirus } *
        | * { управл* * @Antivirus } *
        | * { интерес* * @Antivirus } * 
        | * { без [@My] ведома * @Antivirus } * )