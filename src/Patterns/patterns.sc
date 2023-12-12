patterns:
    
    $10_PaymentsMethods = ( * { @Payment * через * @Internet } *
        | * { (как/где/каким образом) * *гасить * $money } *
        | * { (как/где/каким образом) * *кидать * $money } *
        | * { (как/где/каким образом) * *кидать * @SubscriptionFee } *
        | * { (как/где/каким образом) * *кидывать * $money } *
        | * { (как/где/каким образом) * *кидывать * @SubscriptionFee } *
        | * { (как/где/каким образом) * *кинуть * $money } *
        | * { (как/где/каким образом) * *платить * $money } *
        | * { (как/где/каким образом) * *платить * @SubscriptionFee } *
        | * { (как/где/каким образом) * *плачивать * $money } *
        | * { (как/где/каким образом) * *плачивать * @SubscriptionFee } *
        | * { (как/где/каким образом) * внесен* * $money } *
        | * { (как/где/каким образом) * внести * $money } *
        | * { (как/где/каким образом) * вносить * $money } *
        | * { (как/где/каким образом) * перевести * $money } *
        | * { (как/где/каким образом) * перевести * @SubscriptionFee } *
        | * { (как/где/каким образом) * переводить * $money } *
        | * { (как/где/каким образом) * переводить * @SubscriptionFee } *
        | * { (как/где/каким образом) * пополнени* * $money } *
        | * { (как/где/каким образом) * пополнить * $money } *
        | * { (как/где/каким образом) * пополнить * @SubscriptionFee } *
        | * { (как/где/каким образом) * пополнять * $money } *
        | * { (как/где/каким образом) * пополнять * @SubscriptionFee } *
        | * { @Payment * в приложении } *
        | * { @Payment * на сайте } *
        | * { @Payment * онлайн } *
        | * { @Payment * по карте } *
        | * { @Payment * через * приложен* } *
        | * { @Payment * через * сайт } *
        | * { вариант* * @Payment } *
        | * { где * *гасить * @AllServices } *
        | * { где * *гасить * @Payment } *
        | * { где * *гасить * долг* } *
        | * { где * *гасить * задолженност* } *
        | * { где * *кидать * @AllServices } *
        | * { где * *кидать * @Payment } *
        | * { где * *кидать * долг* } *
        | * { где * *кидать * задолженност* } *
        | * { где * *кидывать * @AllServices } *
        | * { где * *кидывать * @Payment } *
        | * { где * *кидывать * долг* } *
        | * { где * *кидывать * задолженност* } *
        | * { где * *кинуть * @AllServices } *
        | * { где * *кинуть * @Payment } *
        | * { где * *кинуть * долг* } *
        | * { где * *кинуть * задолженност* } *
        | * { где * *платить * @AllServices } *
        | * { где * *платить * @Payment } *
        | * { где * *платить * долг* } *
        | * { где * *платить * задолженност* } *
        | * { где * *платить * наличн* } *
        | * { где * *плачивать * @AllServices } *
        | * { где * *плачивать * @Payment } *
        | * { где * *плачивать * долг* } *
        | * { где * *плачивать * задолженност* } *
        | * { где * @Payment * наличн* } *
        | * { где * внести * @Payment } *
        | * { где * вносить * @Payment } *
        | * { где * перевести * $money } *
        | * { где * перевести * @AllServices } *
        | * { где * перевести * @Payment } *
        | * { где * перевести * долг* } *
        | * { где * перевести * задолженност* } *
        | * { где * переводить * @AllServices } *
        | * { где * переводить * @Payment } *
        | * { где * переводить * долг* } *
        | * { где * переводить * задолженност* } *
        | * { где * пополнить * @AllServices } *
        | * { где * пополнить * @Payment } *
        | * { где * пополнить * долг* } *
        | * { где * пополнить * задолженност* } *
        | * { где * пополнять * @AllServices } *
        | * { где * пополнять * @Payment } *
        | * { где * пополнять * долг* } *
        | * { где * пополнять * задолженност* } *
        | * { где * произвести * @Payment } *
        | * { где * производить * @Payment } *
        | * { где * сделать * @Payment } *
        | * { как * *гасить * @AllServices } *
        | * { как * *гасить * @Payment } *
        | * { как * *гасить * долг* } *
        | * { как * *гасить * задолженност* } *
        | * { как * *кидать * @AllServices } *
        | * { как * *кидать * @Payment } *
        | * { как * *кидать * долг* } *
        | * { как * *кидать * задолженност* } *
        | * { как * *кидывать * @AllServices } *
        | * { как * *кидывать * @Payment } *
        | * { как * *кидывать * долг* } *
        | * { как * *кидывать * задолженност* } *
        | * { как * *кинуть * @AllServices } *
        | * { как * *кинуть * @Payment } *
        | * { как * *кинуть * долг* } *
        | * { как * *кинуть * задолженност* } *
        | * { как * *платить * @AllServices } *
        | * { как * *платить * @Payment } *
        | * { как * *платить * долг* } *
        | * { как * *платить * задолженност* } *
        | * { как * *платить * наличн* } *
        | * { как * *плачивать * @AllServices } *
        | * { как * *плачивать * @Payment } *
        | * { как * *плачивать * долг* } *
        | * { как * *плачивать * задолженност* } *
        | * { как * @Payment * наличн* } *
        | * { как * внести * @Payment } *
        | * { как * вносить * @Payment } *
        | * { как * перевести * $money } *
        | * { как * перевести * @AllServices } *
        | * { как * перевести * @Payment } *
        | * { как * перевести * долг* } *
        | * { как * перевести * задолженност* } *
        | * { как * переводить * @AllServices } *
        | * { как * переводить * @Payment } *
        | * { как * переводить * долг* } *
        | * { как * переводить * задолженност* } *
        | * { как * пополнить * @AllServices } *
        | * { как * пополнить * @Payment } *
        | * { как * пополнить * долг* } *
        | * { как * пополнить * задолженност* } *
        | * { как * пополнять * @AllServices } *
        | * { как * пополнять * @Payment } *
        | * { как * пополнять * долг* } *
        | * { как * пополнять * задолженност* } *
        | * { как * произвести * @Payment } *
        | * { как * производить * @Payment } *
        | * { каким образом * *гасить * @AllServices } *
        | * { каким образом * *гасить * @Payment } *
        | * { каким образом * *гасить * долг* } *
        | * { каким образом * *гасить * задолженност* } *
        | * { каким образом * *кидать * @AllServices } *
        | * { каким образом * *кидать * @Payment } *
        | * { каким образом * *кидать * долг* } *
        | * { каким образом * *кидать * задолженност* } *
        | * { каким образом * *кидывать * @AllServices } *
        | * { каким образом * *кидывать * @Payment } *
        | * { каким образом * *кидывать * долг* } *
        | * { каким образом * *кидывать * задолженност* } *
        | * { каким образом * *кинуть * @AllServices } *
        | * { каким образом * *кинуть * @Payment } *
        | * { каким образом * *кинуть * долг* } *
        | * { каким образом * *кинуть * задолженност* } *
        | * { каким образом * *платить * @AllServices } *
        | * { каким образом * *платить * @Payment } *
        | * { каким образом * *платить * долг* } *
        | * { каким образом * *платить * задолженност* } *
        | * { каким образом * *платить * наличн* } *
        | * { каким образом * *плачивать * @AllServices } *
        | * { каким образом * *плачивать * @Payment } *
        | * { каким образом * *плачивать * долг* } *
        | * { каким образом * *плачивать * задолженност* } *
        | * { каким образом * @Payment * наличн* } *
        | * { каким образом * внести * @Payment } *
        | * { каким образом * вносить * @Payment } *
        | * { каким образом * перевести * $money } *
        | * { каким образом * перевести * @AllServices } *
        | * { каким образом * перевести * @Payment } *
        | * { каким образом * перевести * долг* } *
        | * { каким образом * перевести * задолженност* } *
        | * { каким образом * переводить * @AllServices } *
        | * { каким образом * переводить * @Payment } *
        | * { каким образом * переводить * долг* } *
        | * { каким образом * переводить * задолженност* } *
        | * { каким образом * пополнить * @AllServices } *
        | * { каким образом * пополнить * @Payment } *
        | * { каким образом * пополнить * долг* } *
        | * { каким образом * пополнить * задолженност* } *
        | * { каким образом * пополнять * @AllServices } *
        | * { каким образом * пополнять * @Payment } *
        | * { каким образом * пополнять * долг* } *
        | * { каким образом * пополнять * задолженност* } *
        | * { каким образом * произвести * @Payment } *
        | * { каким образом * производить * @Payment } *
        | * { не @Successful * *гасить * $money } *
        | * { не @Successful * *гашен* * банк* } *
        | * { не @Successful * *гашен* * вэтэбэ } *
        | * { не @Successful * *гашен* * касс* } *
        | * { не @Successful * *гашен* * киоск* } *
        | * { не @Successful * *гашен* * мегафон* } *
        | * { не @Successful * *гашен* * почт* } *
        | * { не @Successful * *гашен* * сбербанк* } *
        | * { не @Successful * *гашен* * связно* } *
        | * { не @Successful * *кидать * $money } *
        | * { не @Successful * *кидывать * $money } *
        | * { не @Successful * *кинуть * $money } *
        | * { не @Successful * *кинуть * банк* } *
        | * { не @Successful * *кинуть * вэтэбэ } *
        | * { не @Successful * *кинуть * касс* } *
        | * { не @Successful * *кинуть * киоск* } *
        | * { не @Successful * *кинуть * мегафон* } *
        | * { не @Successful * *кинуть * почт* } *
        | * { не @Successful * *кинуть * сбербанк* } *
        | * { не @Successful * *кинуть * связно* } *
        | * { не @Successful * *платить * $money } *
        | * { не @Successful * *платить * банк* } *
        | * { не @Successful * *платить * вэтэбэ } *
        | * { не @Successful * *платить * касс* } *
        | * { не @Successful * *платить * киоск* } *
        | * { не @Successful * *платить * мегафон* } *
        | * { не @Successful * *платить * почт* } *
        | * { не @Successful * *платить * сбербанк* } *
        | * { не @Successful * *платить * связно* } *
        | * { не @Successful * *плачивать * $money } *
        | * { не @Successful * *плачивать * банк* } *
        | * { не @Successful * *плачивать * вэтэбэ } *
        | * { не @Successful * *плачивать * касс* } *
        | * { не @Successful * *плачивать * киоск* } *
        | * { не @Successful * *плачивать * мегафон* } *
        | * { не @Successful * *плачивать * почт* } *
        | * { не @Successful * *плачивать * сбербанк* } *
        | * { не @Successful * *плачивать * связно* } *
        | * { не @Successful * внесен* * $money } *
        | * { не @Successful * внесен* * банк* } *
        | * { не @Successful * внесен* * вэтэбэ } *
        | * { не @Successful * внесен* * касс* } *
        | * { не @Successful * внесен* * киоск* } *
        | * { не @Successful * внесен* * мегафон* } *
        | * { не @Successful * внесен* * почт* } *
        | * { не @Successful * внесен* * сбербанк* } *
        | * { не @Successful * внесен* * связно* } *
        | * { не @Successful * внести * $money } *
        | * { не @Successful * внести * банк* } *
        | * { не @Successful * внести * вэтэбэ } *
        | * { не @Successful * внести * касс* } *
        | * { не @Successful * внести * киоск* } *
        | * { не @Successful * внести * мегафон* } *
        | * { не @Successful * внести * почт* } *
        | * { не @Successful * внести * сбербанк* } *
        | * { не @Successful * внести * связно* } *
        | * { не @Successful * вносить * $money } *
        | * { не @Successful * вносить * банк* } *
        | * { не @Successful * вносить * вэтэбэ } *
        | * { не @Successful * вносить * касс* } *
        | * { не @Successful * вносить * киоск* } *
        | * { не @Successful * вносить * мегафон* } *
        | * { не @Successful * вносить * почт* } *
        | * { не @Successful * вносить * сбербанк* } *
        | * { не @Successful * вносить * связно* } *
        | * { не @Successful * перевести * $money } *
        | * { не @Successful * переводить * $money } *
        | * { не @Successful * пополнени* * $money } *
        | * { не @Successful * пополнени* * банк* } *
        | * { не @Successful * пополнени* * вэтэбэ } *
        | * { не @Successful * пополнени* * касс* } *
        | * { не @Successful * пополнени* * киоск* } *
        | * { не @Successful * пополнени* * мегафон* } *
        | * { не @Successful * пополнени* * почт* } *
        | * { не @Successful * пополнени* * сбербанк* } *
        | * { не @Successful * пополнени* * связно* } *
        | * { не @Successful * пополнить * $money } *
        | * { не @Successful * пополнять * $money } *
        | * { не @Successful * систем* город } *
        | * { не берет * систем* город } *
        | * { не брать * систем* город } *
        | * { не приним* * систем* город } *
        | * { не принять * систем* город } *
        | * { не проход* * @Payment } *
        | * { способ* * @Payment } * )

    $20_ServiceCentersAddressIntent = (* @ServiceCenters * @DayOfWeek *
        |* {@ServiceCenters * @DomRu} *
        |* {адрес* * @ServiceCenters} *
        |* {врем* работ* * @ServiceCenters} *
        |* {адрес* * @DomRu [умн* @DomRu $weight<-1>]} *
        |* {адрес* * @DomRu [домофон* $weight<-1>]} *
        |* {адрес* * @DomRu [@VideoThings $weight<-1>]} *
        |* {адрес* * @DomRu [@Website $weight<-1>]} *
        |* {адрес* * @DomRu [приложен* $weight<-1>]} *
        |* {врем* работ* * @DomRu} *
        |  * { (*знать/*знавать) * @ServiceCenters } *
        | * { (назови*/назвать/называть) * @ServiceCenters } *
        | * { (находит*/найти) * @ServiceCenters } *
        | * { (обед*/перерыв*) * @ServiceCenters } *
        | * { (подходит*/подойти) * @ServiceCenters } *
        | * { (работающ*/открыт*) * @ServiceCenters } *
        | * { режим* работ* * @ServiceCenters } *
        | * { место * @ServiceCenters } *
        | * { расположен* * @ServiceCenters } *
        | * { @ServiceCenters * @mystem.geo } *
        | * { ближайш* * @ServiceCenters } *
        | * { где * @ServiceCenters } *
        | * { график* работ* * @ServiceCenters } *
        | * { куда * @ServiceCenters } *
        | * { работ* * @ServiceCenters } *
        | * { расписан* * @ServiceCenters } * )
        
    $30_ManageServicePacket = ( * { подключить [вашу] @Services } *
        | * { подключение [вашей] @Services } * 
        | * { *ключ* * платн* @Services } * 
        | * { *ключ* * (доп/дополнит*) @Services } * 
        | * { *блок* * платн* @Services } * 
        | * { *блок* * (доп/дополнит*) @Services } * 
        | * { подключ* * @Services * за 1 (р/р./руб/руб./рубль) } * )
        
    $antivirus = ( * @Antivirus *
        | * { актив* * @Antivirus } *
        | * { консультац* * @Antivirus } *
        | * { вопрос* * @Antivirus } *
        | * { помощ* * @Antivirus } *
        | * { назови* * @Antivirus } *
        | * { перечисл* * @Antivirus } *
        | * { *знать * @Antivirus } *
        | * { *знай * @Antivirus } *
        | * { *знавать * @Antivirus } *
        | * { (найти/находить) * @Antivirus } *
        | * { настро* * @Antivirus }*
        | * { настраива* * @Antivirus }* 
        | * { не работа* * @Antivirus } *
        | * { не заработа* * @Antivirus } *
        | * { не срабат* * @Antivirus } *
        | * { неполадк* * @Antivirus } *
        | * { сложност* * @Antivirus } *
        | * { отключите * @Antivirus } *
        | * { отключиться * @Antivirus } *
        | * { отключить * @Antivirus } *
        | * { отключусь * @Antivirus } *
        | * { отключаться * @Antivirus } *
        | * { отключайте * @Antivirus } *
        | * { (перебой/перебои) * @Antivirus } *
        | * { *платит* * @Antivirus } *
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
        | * { рассказать * @Antivirus } *
        | * { провер* * @Antivirus } *
        | * { (убрать/убери*) * @Antivirus } *
        | * { (удали*/удале*) * @Antivirus } *
        | * { установ* * @Antivirus } *
        | * { устанав* * @Antivirus } *
        | * { (что/что то/что-то) * случил* @Antivirus } *
        | * { *блокиров* * @Antivirus } *
        | * { *информ* * @Antivirus } *
        | * { [тех/техническ*] поддержк* * @Antivirus } *
        | * { включ* * @Antivirus } *
        | * { выкидыв* * @Antivirus } *
        | * { вылета* * @Antivirus } *
        | * { доступ* * @Antivirus } *
        | * { есть * @Antivirus } *
        | * { запуск* * @Antivirus } *
        | * { исчез* * @Antivirus } *
        | * { не [@Successful] войти * @Antivirus } *
        | * { не [@Successful] зайти * @Antivirus } *
        | * { не [@Successful] подключ* * @Antivirus } *
        | * { обруб* * @Antivirus } *
        | * { *ключил* * @Antivirus } *
        | * { отмен* * @Antivirus } *
        | * { отруб* * @Antivirus } *
        | * { перестал* * вход* * @Antivirus } *
        | * { перестал* * заход* * @Antivirus } *
        | * { перестал* * подключ* * @Antivirus } *
        | * { перестал* * работа* * @Antivirus } *
        | * { перестал* * срабат* * @Antivirus } *
        | * { подключ* * @Antivirus } *
        | * { попробовать * @Antivirus } *
        | * { потестить * @Antivirus } *
        | * { пропал* * @Antivirus } *
        | * { протестировать * @Antivirus } *
        | * { существ* * @Antivirus } *
        | * { управл* * @Antivirus } *) 
        
    $channelPackage = ( * { *знать * @TVChannelPackageName } *
        | * { *знай* * @TVChannelPackageName } *
        | * { *знавать * @TVChannelPackageName } *
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
        | * { подключ* * сериал* } *
        | * { приостанов* @TVChannelPackageName } *
        | * { пролонг* * @TVChannelPackageName } *
        | * { вопрос* * @TVChannelPackageName } *
        | * { информац* * @TVChannelPackageName } *
        | * { консультац* * @TVChannelPackageName } *
        | * { удали* * @TVChannelPackageName } *
        | * { удале* * @TVChannelPackageName } *
        | * { *блокиров* * @TVChannelPackageName } *
        | * { @TVChannelPackageName * (@TVSet/@Television) } *
        | * { актив* * @TVChannelPackageName } *
        | * { контрол* * @TVChannelPackageName } *
        | * { не (удали*/удале*) * @TVChannelPackageName } *
        | * { не *блокиров* * @TVChannelPackageName } *
        | * { не актив* * @TVChannelPackageName } *
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
        | * { *знать * пакет* @TVChannel } *
        | * { *знай* * пакет* @TVChannel } *
        | * { *знавать * пакет* @TVChannel } *
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
        | * { актив* * пакет* @TVChannel } *
        | * { контрол* * пакет* @TVChannel } *
        | * { не (удали*/удале*) * пакет* @TVChannel } *
        | * { не *блокиров* * пакет* @TVChannel } *
        | * { не актив* * пакет* @TVChannel } *
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
        | * { уточн* * пакет* @TVChannel } * ) 
        
    $catchUp = ( * @CatchUp *
        | * { вопрос* * @CatchUp } *
        | * { информац* * @CatchUp } *
        | * { консультац* * @CatchUp } *
        | * { удали* * @CatchUp } *
        | * { удале* * @CatchUp } *
        | * { *блокиров* * @CatchUp } *
        | * { *знать * пакет* @CatchUp } *
        | * { *знай* * пакет* @CatchUp } *
        | * { *знавать * пакет* @CatchUp } *
        | * { @CatchUp * (@TVSet/@Television) } *
        | * { актив* * @CatchUp } *
        | * { контрол* * @CatchUp } *
        | * { не (удали*/удале*) * @CatchUp } *
        | * { не *блокиров* * @CatchUp } *
        | * { не актив* * @CatchUp } *
        | * { не использ* * @CatchUp } *
        | * { не отключ* * @CatchUp } *
        | * { не подключ* * @CatchUp } *
        | * { не работ* * @CatchUp } *
        | * { опци* * @CatchUp } *
        | * { отключ* * @CatchUp } *
        | * { поддержк* * @CatchUp } *
        | * { подключ* * @CatchUp } *
        | * { прекратит* * @CatchUp } *
        | * { работ* * @CatchUp } *
        | * { услуг* * @CatchUp } *
        | * { уточн* * @CatchUp } *
        | * { приостанов* * @CatchUp } *
        | * { убери* * @CatchUp } *
        | * { убрать * @CatchUp } *
        | * { убирай* * @CatchUp } *
        | * { продли* * @CatchUp } *
        | * { продле* * @CatchUp } *
        | * { пролонг* * @CatchUp } *
        | * { *обнов* * @CatchUp } *) 
        
    $manageSubscription = ( * @Subscription *
        | * { вопрос* * @Subscription } *
        | * { информац* * @Subscription } *
        | * { консультац* * @Subscription } *
        | * { платн* * @Subscription } *
        | * { бесплатн* * @Subscription } *
        | * { дополнительн* * @Subscription } *
        | * { удали* * @Subscription } *
        | * { удале* * @Subscription } *
        | * { *блокиров* * @Subscription } *
        | * { *знать * пакет* @Subscription } *
        | * { *знай* * пакет* @Subscription } *
        | * { *знавать * пакет* @Subscription } *
        | * { @Subscription * (@TVSet/@Television) } *
        | * { актив* * @Subscription } *
        | * { контрол* * @Subscription } *
        | * { не работ* * @Subscription } *
        | * { не удали* * @Subscription } *
        | * { не удале* * @Subscription } *
        | * { не *блокиров* * @Subscription } *
        | * { не актив* * @Subscription } *
        | * { не использ* * @Subscription } *
        | * { не отключ* * @Subscription } *
        | * { не подключ* * @Subscription } *
        | * { опци* * @Subscription } *
        | * { отключ* * @Subscription } *
        | * { поддержк* * @Subscription } *
        | * { подключ* * @Subscription } *
        | * { прекратит* * @Subscription } *
        | * { работ* * @Subscription } *
        | * { управл* * @Subscription } *
        | * { услуг* * @Subscription } *
        | * { уточн* * @Subscription } *
        | * { приостанов* * @Subscription } *
        | * { убрать * @Subscription } *
        | * { убери* * @Subscription } *
        | * { убирай* * @Subscription } *
        | * { пролонг* * @Subscription } *
        | * { *обнов* * @Subscription } *
        | * { выключ* * @CatchUp } *
        | * { выключ* * @Subscription } *
        | * { не выключ* * @CatchUp } *
        | * { не выключ* * @Subscription } *)
        
    $speedBonus = ( * { бонус* скорост* } *
        | * { стоимост* * бонус* скорост* } *
        | * { *блокиров* * бонус* скорост* } *
        | * { *знать * бонус* скорост* } *
        | * { *обнов* * бонус* скорост* } *
        | * { актив* * бонус* скорост* } *
        | * { бонус* скорост* * @Beneficiary } *
        | * { бонус* скорост* * @DomRu } *
        | * { бонус* скорост* * @Services } *
        | * { опци* * бонус* скорост* } *
        | * { бонус* скорост* * @SynonymsForAgreement } *
        | * { бонус* скорост* * @Internet } *
        | * { не *блокиров* * бонус* скорост* } *
        | * { не актив* * бонус* скорост* } *
        | * { не выключ* * бонус* скорост* } *
        | * { не отключ* * бонус* скорост* } *
        | * { не подключ* * бонус* скорост* } *
        | * { не работ* * бонус* скорост* } *
        | * { не удале* * бонус* скорост* } *
        | * { не удали* * бонус* скорост* } *
        | * { отключ* * бонус* скорост* } *
        | * { подключ* * бонус* скорост* } *
        | * { @Successful * бонус* скорост* } *
        | * { потратить * бонус* скорост* } *
        | * { прекратит* * бонус* скорост* } *
        | * { приветственн* бонус* скорост* } *
        | * { приостан* * бонус* скорост* } *
        | * { продле* * бонус* скорост* } *
        | * { продли* * бонус* скорост* } *
        | * { пролонг* * бонус* скорост* } *
        | * { рекламн* * бонус* скорост* } *
        | * { сколько * бонус* скорост* * @AllServices } *
        | * { убери* * бонус* скорост* } *
        | * { убра* * бонус* скорост* } *
        | * { удале* * бонус* скорост* } *
        | * { удали* * бонус* скорост* } 
        | * { управл* * бонус* скорост* } *
        | * { уточн* * бонус* скорост* } * )
        
    $otherServices = ( * @StaticIP * 
        | * @ParentalControl * 
        | * { не *менять * @IPaddress } *
        | * { не *менить * @IPaddress } *
        | * { не *менял* * @IPaddress } *
        | * { не *менил* * @IPaddress } *
        | * { не *меняйте * @IPaddress } *
        | * { не *меняй  * @IPaddress } *
        | * { не *мените * @IPaddress } *)
    
    $40_ChannelsPackagesIntent = (* { вернут* * [@TVChannel] * @TVChannelName } *
        |* { возвращат* * [@TVChannel] * @TVChannelName } *
        |* { пропал* * [@TVChannel] * @TVChannelName } *
        |* { исчез* * [@TVChannel] * @TVChannelName } *
        |* { ушел * [@TVChannel] * @TVChannelName } *
        |* { ушли * [@TVChannel] * @TVChannelName } *
        |* { делся * [@TVChannel] * @TVChannelName } *
        |* { делись * [@TVChannel] * @TVChannelName } *
        |* { нет * [@TVChannel] * @TVChannelName } *
        |* { нету * [@TVChannel] * @TVChannelName } *
        |* { убрал* * [@TVChannel] * @TVChannelName } *
        |* { какие * @TVChannel * @TVChannelPackageName } *
        |* { какой * @TVChannel * @TVChannelPackageName } *
        |* { сколько * @TVChannel * @TVChannelPackageName } *
        |* { что * @TVChannel * @TVChannelPackageName } *
        |* { @TVChannel * @TVChannelPackageName } *
        |* { @TVChannel * @TVChannelName } *
        | * {[куда/куда то/куда-то] * делась * @TVChannel} *
        | * {[куда/куда то/куда-то] * делись * @TVChannel} *
        | * {[куда/куда то/куда-то] * делось * @TVChannel} *
        | * {[куда/куда то/куда-то] * делся * @TVChannel} *
        | * {@TVChannel * исчез*} *
        | * {@TVChannel * убрал*} *
        | * {остал* * [только] * обычн* * @TVChannel} *
        | * {остал* * [только] * стандартн* * @TVChannel} *
        | * {остал* * [только] * федеральн* * @TVChannel} *
        | * {остал* * [только] * центральн* * @TVChannel} *
        | * {отключен* * @TVChannel} *
        | * {отключил* показ* * @TVChannel } *
        | * {показ* * [только] * обычн* * @TVChannel} *
        | * {показ* * [только] * стандартн* * @TVChannel} *
        | * {показ* * [только] * федеральн* * @TVChannel} *
        | * {показ* * [только] * центральн* * @TVChannel} *
        | * {работа* * [только] * обычн* * @TVChannel} *
        | * {работа* * [только] * стандартн* * @TVChannel} *
        | * {работа* * [только] * федеральн* * @TVChannel} *
        | * {работа* * [только] * центральн* * @TVChannel} * )
        
    $50_InvoiceIntent = (* @Invoice *
        | * реквизит* )
        
    $50_SumProbInvoiceIntent = (* { вопрос* * [сумм*] * @Invoice } *
        |* { вопрос* * [начислен*] * @Invoice } *
        |* { вопрос* * [счет*] * @Invoice } *
        |* { выставлен* * [сумм*] * @Invoice } *
        |* { выставлен* * [начислен*] * @Invoice } *
        |* { выставлен* * [счет*] * @Invoice } *
        |* { информац* * @Invoice } *
        |* { неверн* * @Invoice } *
        |* { некорректн* * @Invoice } *
        |* { неправильн* * @Invoice } *
        |* { не выстав* * @Invoice } *
        |* { не отображ* * @Invoice } *
        |* { не посчит* * @Invoice } *
        |* { ошиб* * @Invoice } *
        |* { не совпадает * @Invoice } *)
        
    $50_LostInvoiceIntent = ( * {не приш* * @Invoice} *
        |* {не был* * @Invoice} *
        |* {не @Successful * @Invoice} *
        |* {не доставл* * @Invoice} *
        |* {достав* * @Invoice} *
        |* {направл* * @Invoice} *
        |* {отправ* * @Invoice} *
        |* {предостав* * @Invoice} *
        |* {присыла* * @Invoice} *
        |* {приход* * @Invoice} *
        |* {нужн* * @Invoice} *
        |* {не приш* * @Invoice} *
        |* {бумаж* * @Invoice} *
        |* {где * @Invoice} *)
        
    $70_StillNotGetCharge = ( * { было * *плачен* * $money } *
        | * { $regexp<верн(уть|ите)> * $money } *
        | * { не видим * @Payment } * 
        | * { не видим * @Payment * @SynonymsForAgreement } * 
        | * { не видим * $money } *
        | * { не видим * $money * @SynonymsForAgreement } *
        | * { не видн* * @Payment } *
        | * { не видн* * @Payment * @SynonymsForAgreement } *
        | * { не видн* * $money } *
        | * { не видн* * $money * @SynonymsForAgreement } *
        | * { не вижу * @Payment } *
        | * { не вижу * @Payment * @SynonymsForAgreement } *
        | * { не вижу * $money } *
        | * { не вижу * $money * @SynonymsForAgreement } *
        | * { не дошл* * @Payment } *
        | * { не дошл* * @Payment * @SynonymsForAgreement } *
        | * { не дошл* * $money } *
        | * { не дошл* * $money * @SynonymsForAgreement } *
        | * { не зачисл* * @Payment } *
        | * { не зачисл* * @Payment * @SynonymsForAgreement } *
        | * { не зачисл* * $money } *
        | * { не зачисл* * $money * @SynonymsForAgreement } *
        | * { не оплат* * @Payment } *
        | * { не оплат* * @Payment * @SynonymsForAgreement } *
        | * { не оплат* * $money * @SynonymsForAgreement } *
        | * { не оплач* * @Payment } *
        | * { не оплач* * @Payment * @SynonymsForAgreement } *
        | * { не отобра* * @Payment } *
        | * { не отобра* * @Payment * @SynonymsForAgreement } *
        | * { не отобра* * $money } *
        | * { не отобра* * $money * @SynonymsForAgreement } *
        | * { не переве* * @Payment } *
        | * { не переве* * @Payment * @SynonymsForAgreement } *
        | * { не переве* * $money } *
        | * { не переве* * $money * @SynonymsForAgreement } *
        | * { не пополн* * @Payment } *
        | * { не пополн* * @Payment * @SynonymsForAgreement } *
        | * { не пополн* * $money } *
        | * { не пополн* * $money * @SynonymsForAgreement } *
        | * { не поступ* * @Payment } *
        | * { не поступ* * @Payment * @SynonymsForAgreement } *
        | * { не поступ* * $money } *
        | * { не поступ* * $money * @SynonymsForAgreement } *
        | * { не приход* * @Payment } *
        | * { не приход* * @Payment * @SynonymsForAgreement } *
        | * { не приход* * $money } *
        | * { не приход* * $money * @SynonymsForAgreement } *
        | * { не пришл* * @Payment } *
        | * { не пришл* * @Payment * @SynonymsForAgreement } *
        | * { не пришл* * $money } *
        | * { не пришл* * $money * @SynonymsForAgreement } *)
        
    $80_PromisedPayment =  ( * @PromisedPayment *
        | * { *знать * пакет* @PromisedPayment } *
        | * { *знай* * пакет* @PromisedPayment } *
        | * { *знавать * пакет* @PromisedPayment } *
        | * { актив* * @PromisedPayment } *
        | * { брать * @PromisedPayment } *
        | * { беру * @PromisedPayment } *
        | * { берем * @PromisedPayment } *
        | * { брал* * @PromisedPayment } *
        | * { взять * @PromisedPayment } *
        | * { возьм* * @PromisedPayment } *
        | * { взял* * @PromisedPayment } *
        | * { выдай * @PromisedPayment } *
        | * { выдайте * @PromisedPayment } *
        | * { выдать * @PromisedPayment } *
        | * { дай * @PromisedPayment } *
        | * { дайте * @PromisedPayment } *
        | * { дать * @PromisedPayment } *
        | * { отсроч* * (@Payment/платеж*) } *
        | * { *скажи* * @PromisedPayment } *
        | * { *говори* * @PromisedPayment } *
        | * { сумм* * @PromisedPayment } *
        | * { стоимост* * @PromisedPayment } *
        | * { стоит* * @PromisedPayment } *
        | * { *платн* @PromisedPayment } *
        | * { *пользова* * @PromisedPayment } *
        | * { @PromisedPayment * @AllServices } *
        | * { @PromisedPayment * @Payment } *
        | * { @PromisedPayment * @SynonymsForAgreement } *
        | * { взят* * [в] * (долг*/кредит*/аванс*)} *
        | * { включ* * @PromisedPayment } *
        | * { выключ* * @PromisedPayment } *
        | * { отключ* * @PromisedPayment } *
        | * { оформ* * @PromisedPayment } *
        | * { подключ* * @PromisedPayment } *
        | * { постав* * @PromisedPayment } *
        | * { продл* * @PromisedPayment } *
        | * { сколько * стоит * @PromisedPayment } *
        | * { сколько * стоят * @PromisedPayment } *
        | * { требует* * @PromisedPayment } *
        | * { установ* * @PromisedPayment } *
        | * { @PromisedPayment * @duckling.interval * числ* } *
        | * { @PromisedPayment * @duckling.interval * месяц* } *
        | * { @PromisedPayment * @duckling.interval * @Month } *
        | * продлит* @Internet * (с/до/по/на) @duckling.number * числ* *
        | * продлит* @Internet * (с/до/по/на) @duckling.number * ~день *
        | * продлит* @Internet * (с/до/по/на) @duckling.number * ~дата *
        | * продлит* @Internet * (с/до/по/на) @duckling.number * месяц* *
        | * продлит* @Internet * (с/до/по/на) @duckling.number * @Month *
        | * @Internet продлит* * (с/до/по/на) @duckling.number * числ* *
        | * @Internet продлит* * (с/до/по/на) @duckling.number * ~день *
        | * @Internet продлит* * (с/до/по/на) @duckling.number * ~дата *
        | * @Internet продлит* * (с/до/по/на) @duckling.number * месяц* *
        | * @Internet продлит* * (с/до/по/на) @duckling.number * @Month * 
        | * {@Internet включ*} * минуту * 
        | * {@Internet включ*} * минуты * 
        | * {@Internet включ*} * минутку * 
        | * {@Internet включ*} * минутки * 
        | * {@Internet включ*} * минуточку * 
        | * {@Internet включ*} * минуточки * )
        
    $90_Balance = (* {баланс* * [за/от/до/на] @Months } *
        | * {начислен* * [за/от/до/на] @Months } *
        | * {сумм* * [за/от/до/на] @Months } *
        | * {*плат* * [за/от/до/на] @Months } *
        | * {@Payment * [за/от/до/на] @Months } *
        | * {долг* * [за/от/до/на] @Months } *
        | * {задолж* * [за/от/до/на] @Months } *
        | * {*гасить * долг* * за @AllServices } *
        | * {*гасить * задолж* * за @AllServices } *
        | * {*гасить * минус* * за @AllServices } *
        | * {почему * за @Months * @Need * *платить } *
        | * {почему * за @Months * @Must *платить } *
        | * {почему * за @Months * сумм* } *
        | * {почему * за @Months * начислен* } *
        | * {почему * [так] много начислен* * за @Months } *
        | * {почему * *платит* * (два/три/2/3) раза } *
        | * {почему * баланс* * за @Months * состав* } *
        | * {почему * начислен* * за @Months * состав* } *
        | * {почему * сумм* * за @Months } *
        | * {почему * @Payment * за @Months } *
        | * {почему * баланс* * за @Months } *
        | * {почему * начислен* * за @Months } *
        | * {сколько * *платит* * @Need * @Months } *
        | * {сколько * *платит* * @Must * @Months } *
        | * {сколько * *платит* * по баланс* } *
        | * {сколько * *платит* * по @SynonymsForAgreement } *
        | * {сколько * *платит* * по счет* } *
        | * {сколько * *платит* * по @AllServices } *
        | * {сколько * *платит* * за баланс* } *
        | * {сколько * *платит* * за @SynonymsForAgreement} *
        | * {сколько * *платит* * за счет* } *
        | * {сколько * *платит* * за @AllServices } *
        | * { @duckling.amount-of-money * за какое время } *
        | * { @Month * минус* * есть } *
        | * { @Month * минус* * нет } *
        | * { @Month * минус* * нету } *
        | * { @Month * долг* * есть } *
        | * { @Month * долг* * нет } *
        | * { @Month * долг* * нету } *
        | * { @Month * задолженност* * есть } *
        | * { @Month * задолженност* * нет } *
        | * { @Month * задолженност* * нету } *
        | * {минус* * @AllServices } *
        | * {минус* * @SynonymsForAgreement } *
        | * {минус* * баланс* } *
        | * {минус* * счет* } *
        | * {долг* * @AllServices } *
        | * {долг* * @SynonymsForAgreement } *
        | * {долг* * баланс* } *
        | * {долг* * счет* } *
        | * {задолженност* * @AllServices } *
        | * {задолженност* * @SynonymsForAgreement } *
        | * {задолженност* * баланс* } *
        | * {задолженност* * счет* } *
        | * {баланс* * @AllServices } *
        | * {баланс* * @SynonymsForAgreement } *
        | * {баланс* * счет* } *
        | * {счет* * @AllServices } *
        | * {счет* * @SynonymsForAgreement } *
        | * {начислен* * @AllServices } *
        | * {начислен* * @SynonymsForAgreement } *
        | * {начислен* * баланс* } *
        | * {начислен* * счет* } *
        | * {сумм* * @AllServices } *
        | * {сумм* * @SynonymsForAgreement } *
        | * {сумм* * баланс* } *
        | * {сумм* * счет* } *
        | * {@Payment * @AllServices } *
        | * {@Payment * @SynonymsForAgreement } *
        | * {@Payment * баланс* } *
        | * {@Payment * счет* } *
        | * { баланс * за * @Month * }
        | * { счет * за * @Month * }
        | * { минус * за * @Month * }
        | * { долг* * за * @Month * }
        | * { задолженност* * за * @Month * }
        | * стоимость * (тариф* [план*]/@AllServices) *
        | * (тариф* [план*]/@AllServices) * стоимость *
        | * { сейчас * баланс* * @duckling.amount-of-money } *
        | * { сейчас * счет* * @duckling.amount-of-money } *
        | * { сегодня * баланс* * @duckling.amount-of-money } *
        | * { сегодня * счет* * @duckling.amount-of-money } *
        | * { баланс* * @DomRu } *
        | * { начислени* * @DomRu } *
        | * { сумм* * @DomRu } *
        | * { @Payment * @DomRu } *
        | * { счет * @DomRu } *
        | * сколько * { $money * [на/по] * баланс* } *
        | * сколько * { $money * [на/по] * @SynonymsForAgreement } *
        | * сколько * { $money * [на/по] * номер* @SynonymsForAgreement } *
        | * сколько * { $money * [на/по] * счет* } *
        | * { $money * закончились * (по/на) * баланс* } *
        | * { $money * закончились * (по/на) * @SynonymsForAgreement } *
        | * { $money * закончились * (по/на) * номер* @SynonymsForAgreement } *
        | * { $money * закончились * (по/на) * счет* } *
        | * { хватает * $money * на баланс* } *
        | * { хватает * $money * на @SynonymsForAgreement } *
        | * { хватает * $money * на счет* } *
        | * { хватает * @Payment * на баланс* } *
        | * { хватает * @Payment * на @SynonymsForAgreement } *
        | * { хватает * @Payment * на счет* } *
        | * { хватает * сумм* * на баланс* } *
        | * { хватает * сумм* * на @SynonymsForAgreement } *
        | * { хватает * сумм* * на счет* } *
        | * сколько * { (абон/абонентск*/ежемесячн*/месячн*) * (@Payment/обслуживание/стоимость) } *
        | * сколько * { (@Payment/обслуживание/стоимость) * месяц* } *
        | * (какой/какие/какая) * { (абон/абонентск*/ежемесячн*/месячн*) * (@Payment/обслуживание/стоимость) } *
        | * (какой/какие/какая) * { (@Payment/обслуживание/стоимость) * месяц* } * 
        | * (какой/какие/какая) * { (баланс*/начислени*/сумм*/@Payment) * *платить } *
        | * { рекомендован* * баланс* } *
        | * { рекомендован* * начислени* } *
        | * { рекомендован* * сумм* } *
        | * { рекомендован* * @Payment } *
        | * { рекомендуем* * баланс* } *
        | * { рекомендуем* * начислени* } *
        | * { рекомендуем* * сумм* } *
        | * { рекомендуем* * @Payment } *
        | * { какой * баланс* } *
        | * { (какое/какие) * начислени* } *
        | * { (какие/какая) * сумм* } *
        | * { (какой/какие/какая) * @Payment } *
        | * { (какие/какая) * задолженност* } *
        | * { (какой/какие) * долг* } * 
        | * { долг* * @Payment * произв*} *
        | * { задолженност* * @Payment * произв*} *
        | * { долг* * @Payment * перев*} *
        | * { задолженност* * @Payment * перев*} *
        | * { @Payment * произв* * [@duckling.date]} *
        | * { @Payment * перев* * [@duckling.date]} *
        | * { за что * @Must * *гасить} *
        | * { за что * @Must * *платит*} *
        | * { за что * @Must * @Payment} *
        | * { за что * @Need * *гасить} *
        | * { за что * @Need * *платит*} *
        | * { за что * @Need * @Payment} * 
        | * {@Payment * прош* * или нет} *
        | * {@Payment * (*шел/*шла) * или нет} * 
        | * { как * влияет * ([на] @SubscriptionFee) } * 
        | * { сколько * @Equipment * (плотить/платить) } * )
    
    $100_ChangeTariff = ( * { @Modal * больше * канал* } *
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
        | * { *меняй* * @TariffSingular } *
        | * { *менят* * @TariffSingular } *
        | * { *менит* * @TariffSingular } * 
        | * { возможност* * *менит* * @TariffSingular } * 
        | * { (смена/смену) * @TariffSingular } *
        | * { изменени* * @TariffSingular } *
        | * { не @Successful * *меняй* * @TariffSingular } *
        | * { не @Successful * *менят* * @TariffSingular } *
        | * { не @Successful * *менит* * @TariffSingular } *
        | * { не @Successful * (смена/смену) * @TariffSingular } *
        | * { не @Successful * изменени* * @TariffSingular } * )
    
    $110_TarifActivate = ( * { актив* * @SynonymsForAgreement } *
        | * { актив* * @Tariff } *
        | * { актив* * @Services } *
        | * { актив* * доступ* } *
        | * { не актив* * @Tariff } *
        | * { неактив* * @Tariff } *
        | * { ниактив* * @Tariff } *
        | * { ни актив* * @Tariff } *
        | * { нет актив* * @Tariff } *
        | * { не * актив* * @Tariff } *
        | * { не актив* * @SynonymsForAgreement } *
        | * { неактив* * @SynonymsForAgreement } *
        | * { ниактив* * @SynonymsForAgreement } *
        | * { ни актив* * @SynonymsForAgreement } *
        | * { нет актив* * @SynonymsForAgreement } *
        | * { не * актив* * @SynonymsForAgreement } *
        | * { не актив* * @Services } *
        | * { неактив* * @Services} *
        | * { ниактив* * @Services } *
        | * { ни актив* * @Services } *
        | * { нет актив* * @Services } *
        | * { @Services * не * актив* } *
        | * { не актив* * доступ* } *
        | * { неактив* * доступ* } *
        | * { ниактив* * доступ* } *
        | * { ни актив* * доступ* } *
        | * { нет актив* * доступ* } *
        | * { не * актив* * доступ* } *)
        
    $120_SetupDecoder = ( * {[не] [@Successful] * налад* * @TVEquipment} *  
        | * {[не] [@Successful] * настрои* * @TVEquipment} *  
        | * {[не] [@Successful] * настраи* * @TVEquipment} *  
        | * {[не] [@Successful] * настрой* * @TVEquipment} * 
        | * {[не] [@Successful] * отлад* * @TVEquipment} * 
        | * {[не] [@Successful] * *регулир* * @TVEquipment} * 
        | * { не [@Successful] * *ключи* * @TVEquipment} * 
        | * { не [@Successful] * *ключа* * @TVEquipment} *
        | * { поставил* нов* @TVSet } * { как смотреть * @TVChannelPlural } * )
    
    $130_SetupRouter = ( * { не настроен* * @InternetEquipment } *
        | * { не настрои* * @InternetEquipment } *
        | * { не настраива* * @InternetEquipment } *
        | * { не настроен* * @WiFi @Equipment } *
        | * { не настрои* * @WiFi @Equipment } *
        | * { не настраива* * @WiFi @Equipment } *
        | * { ошибк* установк* * @InternetEquipment } *
        | * { ошибк* подключени* * @InternetEquipment } *
        | * { ошибк* настройк* * @InternetEquipment } *
        | * { ошибк* загрузк* * @InternetEquipment } *
        | * { ошибк* установк* * @WiFi @Equipment } *
        | * { ошибк* подключени* * @WiFi @Equipment } *
        | * { ошибк* настройк* * @WiFi @Equipment } *
        | * { ошибк* загрузк* * @WiFi @Equipment } *
        | * { перенастроить * @InternetEquipment } *
        | * { переподключить * @InternetEquipment } *
        | * { перенастроить * @WiFi @Equipment } *
        | * { переподключить * @WiFi @Equipment } *
        | * { сбрасыва* * настройк* * @InternetEquipment } *
        | * { сброс* * настройк* * @InternetEquipment } *
        | * { дроп* * настройк* * @InternetEquipment } *
        | * { сбрасыва* * настройк* * @WiFi @Equipment } *
        | * { сброс* * настройк* * @WiFi @Equipment } *
        | * { дроп* * настройк* * @WiFi @Equipment } *
        | * { купил* * нов* * @InternetEquipment } *
        | * { не включ* * @WiFi * @InternetEquipment } *
        | * { не включ* * сесси* * @InternetEquipment } *
        | * { не включ* * сет* * @InternetEquipment } *
        | * { не включ* * @WiFi * @Equipment } *
        | * { не включ* * сесси* * @Equipment } *
        | * { не включ* * сет* * @Equipment } *
        | * { не подключ* * @WiFi * @InternetEquipment } *
        | * { не подключ* * сесси* * @InternetEquipment } *
        | * { не подключ* * сет* * @InternetEquipment } *
        | * { не подключ* * @WiFi * @Equipment } *
        | * { не подключ* * сесси* * @Equipment } *
        | * { не подключ* * сет* * @Equipment } *
        | * { параметр* * @InternetEquipment } *
        | * { параметр* * @WiFi @Equipment } *
        | * { перестал * работать * @InternetEquipment } *
        | * { перестал * работать * @WiFi @Equipment } *
        | * { подключен* * нов* * @InternetEquipment } *
        | * { подключен* * друг* * @InternetEquipment } *
        | * { подключен* * нов* * @WiFi @Equipment } *
        | * { подключен* * друг* * @WiFi @Equipment } *
        | * { (поменял/поменяла/поменяли) * @InternetEquipment } *
        | * { (поменял/поменяла/поменяли) * @WiFi @Equipment } *
        | * { сбил* * настройк* * @InternetEquipment } *
        | * { сбил* * настройк* * @WiFi @Equipment } *
        | * { установ* * нов* * @InternetEquipment } *
        | * { установ* * друг* * @InternetEquipment } *
        | * { установ* * нов* * @WiFi @Equipment } *
        | * { установ* * друг* * @WiFi @Equipment } *
        | * { не @Successful * настроен* * @InternetEquipment } *
        | * { не @Successful * настрои* * @InternetEquipment } *
        | * { не @Successful * настраива* * @InternetEquipment } *
        | * { не @Successful * настрой* * @InternetEquipment } *
        | * { не @Successful * настроен* * @WiFi } *
        | * { не @Successful * настрои* * @WiFi } *
        | * { не @Successful * настраива* * @WiFi } *
        | * { не @Successful * настрой* * @WiFi } *
        | * { не @Successful * измен* парол* * @InternetEquipment } *
        | * { не @Successful * измен* парол* * @WiFi } *
        | * { настроен* * @InternetEquipment } *
        | * { настроен* * @WiFi @Equipment } *
        | * { настрои* * @InternetEquipment } *
        | * { настрои* * @WiFi @Equipment } *
        | * { настраива* * @InternetEquipment } *
        | * { настраива* * @WiFi @Equipment } *
        | * { настрой* * @InternetEquipment } *
        | * { настрой* * @WiFi @Equipment } *
        | * { (заменил/заменили/заменили) * @InternetEquipment } *
        | * { (заменил/заменили/заменили) * @WiFi @Equipment } *
        | * { установ* * @InternetEquipment } *
        | * { установ* * @WiFi @Equipment } *
        | * { @Operator * настрои* * @InternetEquipment } *
        | * { @Operator * настрои* * @WiFi @Equipment } *
        | * { @Operator * установ* * @InternetEquipment } *
        | * { @Operator * установ* * @WiFi @Equipment } *
        | * { @Personal * настрои* * @InternetEquipment } *
        | * { @Personal * настрои* * @WiFi @Equipment } *
        | * { @Personal * установ* * @InternetEquipment } *
        | * { @Personal * установ* * @WiFi @Equipment } * 
        | * { @Credentials * @InternetEquipment } *
        | * { @Credentials * @WiFi @Equipment } *
        | * { *менять * @Credentials * @WiFi @Equipment } *
        | * { *менить * @Credentials * @WiFi @Equipment } *
        | * { *менял* * @Credentials * @WiFi @Equipment } *
        | * { *менил* * @Credentials * @WiFi @Equipment } *
        | * { *меняй * @Credentials * @WiFi @Equipment } *
        | * { *меняйте * @Credentials * @WiFi @Equipment } *
        | * { *мените * @Credentials * @WiFi @Equipment } *
        | * { (смена/смену) * @Credentials * @WiFi @Equipment } *
        | * { изменени* * @Credentials * @WiFi @Equipment } * 
        | * { *менять * @Credentials * @InternetEquipment } *
        | * { *менить * @Credentials * @InternetEquipment } *
        | * { *менял* * @Credentials * @InternetEquipment } *
        | * { *менил* * @Credentials * @InternetEquipment } *
        | * { *меняй * @Credentials * @InternetEquipment } *
        | * { *меняйте * @Credentials * @InternetEquipment } *
        | * { *мените * @Credentials * @InternetEquipment } *
        | * { (смена/смену) * @Credentials * @InternetEquipment } *
        | * { изменени* * @Credentials * @InternetEquipment } * 
        | * { настроен* * подключен* * @InternetEquipment } *
        | * { настроен* * подключен* * @WiFi @Equipment } *
        | * { настрои* * подключен* * @InternetEquipment } *
        | * { настрои* * подключен* * @WiFi @Equipment } *
        | * { настраива* * подключен* * @InternetEquipment } *
        | * { настраива* * подключен* * @WiFi @Equipment } *
        | * { настрой* * подключен* * @InternetEquipment } *
        | * { настрой* * подключен* * @WiFi @Equipment } *
        | * { настроен* * соедин* * @InternetEquipment } *
        | * { настроен* * соедин* * @WiFi @Equipment } *
        | * { настрои* * соедин* * @InternetEquipment } *
        | * { настрои* * соедин* * @WiFi @Equipment } *
        | * { настраива* * соедин* * @InternetEquipment } *
        | * { настраива* * соедин* * @WiFi @Equipment } *
        | * { настрой* * соедин* * @InternetEquipment } *
        | * { настрой* * соедин* * @WiFi @Equipment } * 
        | * { *подключ* * @WiFi * (к/на) друг* сет* } *
        | * { *подключ* * @WiFi * (к/на) друг* частот* } *
        | * { *менять * @WiFi * (к/на) друг* сет* } *
        | * { *менить* * @WiFi * (к/на) друг* частот* } *
        | * { *меняй * @WiFi * (к/на) друг* сет* } *
        | * { *меняй * @WiFi * (к/на) друг* частот* } *
        | * { *мените * @WiFi * (к/на) друг* сет* } *
        | * { *мените * @WiFi * (к/на) друг* частот* } *
        | * { *меняйте * @WiFi * (к/на) друг* сет* } *
        | * { *меняйте * @WiFi * (к/на) друг* частот* } * 
        | * { *менять * @WiFi * (к/на) друг* сет* } *
        | * { *менить * пароль * @WiFi } *
        | * { *менять * пароль * @WiFi } *
        | * { *меняй * пароль * @WiFi } *
        | * { *меняй * пароль * @WiFi } *
        | * { *мените * пароль * @WiFi } *
        | * { *мените * пароль * @WiFi } *
        | * { *меняйте * пароль * @WiFi } *
        | * { *меняйте * пароль * @WiFi } * 
        | * { *менить * пароль * @InternetEquipment } *
        | * { *менять * пароль * @InternetEquipment } *
        | * { *меняй * пароль * @InternetEquipment } *
        | * { *меняй * пароль * @InternetEquipment } *
        | * { *мените * пароль * @InternetEquipment } *
        | * { *мените * пароль * @InternetEquipment } *
        | * { *меняйте * пароль * @InternetEquipment } *
        | * { *меняйте * пароль * @InternetEquipment } *
        | * { *менить * пароль * @WiFi @Equipment } *
        | * { *менять * пароль * @WiFi @Equipment } *
        | * { *меняй * пароль * @WiFi @Equipment } *
        | * { *меняй * пароль * @WiFi @Equipment } *
        | * { *мените * пароль * @WiFi @Equipment } *
        | * { *мените * пароль * @WiFi @Equipment } *
        | * { *меняйте * пароль * @WiFi @Equipment } *
        | * { *меняйте * пароль * @WiFi @Equipment } * 
        | * { забыл* * @Credentials * (@WiFi [@Equipment/@Router])} * 
        | * { забыт* * @Credentials * (@WiFi [@Equipment/@Router])} *
        | * { забыв* * @Credentials * (@WiFi [@Equipment/@Router])} *  
        | * { @Know * @Credentials * (@WiFi [@Equipment/@Router])} * 
        | * { @Know * @Credentials * (@WiFi [@Equipment/@Router])} *
        | * { @Know * @Credentials * (@WiFi [@Equipment/@Router])} *  )
        
    $150_AgreementStop  = ( * { @Give * приостановит* } *
        | * { временно * отключайте * @AllServices } *
        | * { на время * отключайте * @AllServices } *
        | * { временно * отключайте * @SynonymsForAgreement } *
        | * { на время * отключайте * @SynonymsForAgreement } *
        | * { временно * отключаться * @AllServices } *
        | * { на время * отключаться * @AllServices } *
        | * { временно * отключаться * @SynonymsForAgreement } *
        | * { на время * отключаться * @SynonymsForAgreement } *
        | * { временно * отключите * @AllServices } *
        | * { на время * отключите * @AllServices } *
        | * { временно * отключите * @SynonymsForAgreement } *
        | * { на время * отключите * @SynonymsForAgreement } *
        | * { временно * отключить * @AllServices } *
        | * { на время * отключить * @AllServices } *
        | * { временно * отключить * @SynonymsForAgreement } *
        | * { на время * отключить * @SynonymsForAgreement } *
        | * { временно * отключиться * @AllServices } *
        | * { на время * отключиться * @AllServices } *
        | * { временно * отключиться * @SynonymsForAgreement } *
        | * { на время * отключиться * @SynonymsForAgreement } *
        | * { временно * отключусь * @AllServices } *
        | * { на время * отключусь * @AllServices } *
        | * { временно * отключусь * @SynonymsForAgreement } *
        | * { на время * отключусь * @SynonymsForAgreement } *
        | * { @FIO * @Suspension} *
        | * { @FIO * заморозит* } *
        | * { @FIO * приостановит* } *
        | * { @Give * @Suspension } *
        | * { @Give * заморозит* } *
        | * { @Modal * @Suspension } *
        | * { @Modal * заморозит* } *
        | * { @Modal * приостановит* } *
        | * { @Seasons * @Suspension} *
        | * { @Seasons * заморозит* } *
        | * { @Seasons * приостановит* } *
        | * { @Suspension * @AllServices } *
        | * { @Suspension * @duckling.interval * [@Months] } *
        | * { @Suspension * @duckling.interval * [месяц*] } *
        | * { @Suspension * @duckling.interval * [числ*] } *
        | * { @Suspension * @SynonymsForAgreement } *
        | * { @Suspension * баланс } *
        | * { @Suspension * всё } *
        | * { @Suspension * все* услуг* } *
        | * { @Suspension * всего } *
        | * { @Suspension * дач* } *
        | * { @Suspension * до @duckling.number * ~дата } *
        | * { @Suspension * до @duckling.number * ~день } *
        | * { @Suspension * до @duckling.number * месяц* } *
        | * { @Suspension * до @duckling.number * числ* } *
        | * { @Suspension * до @Months } *
        | * { @Suspension * каникул* } *
        | * { @Suspension * командировк* } *
        | * { @Suspension * короткое * время } *
        | * { @Suspension * на @duckling.number * ~дата } *
        | * { @Suspension * на @duckling.number * ~день } *
        | * { @Suspension * на @duckling.number * месяц* } *
        | * { @Suspension * на @duckling.number * числ* } *
        | * { @Suspension * на @Months } *
        | * { @Suspension * начислени* } *
        | * { @Suspension * некоторое * время } *
        | * { @Suspension * несколько * ~день } *
        | * { @Suspension * несколько * месяц* } *
        | * { @Suspension * оплат* } *
        | * { @Suspension * отдых* } *
        | * { @Suspension * отпуск* } *
        | * { @Suspension * плат* } *
        | * { @Suspension * платеж* } *
        | * { @Suspension * по @duckling.number * ~дата } *
        | * { @Suspension * по @duckling.number * ~день } *
        | * { @Suspension * по @duckling.number * месяц* } *
        | * { @Suspension * по @duckling.number * числ* } *
        | * { @Suspension * по @Months } *
        | * { @Suspension * ремонт* } *
        | * { @Suspension * с @duckling.number * ~дата } *
        | * { @Suspension * с @duckling.number * ~день } *
        | * { @Suspension * с @duckling.number * месяц* } *
        | * { @Suspension * с @duckling.number * числ* } *
        | * { @Suspension * с @Months } *
        | * { @Suspension * сумм* } *
        | * { @Suspension * счет } *
        | * { @Suspension * тариф* } *
        | * { @Suspension * тарифн* план* } *
        | * { ~дата * @Suspension } *
        | * { возобновит* * @Suspension } *
        | * { вопрос* * @Suspension } *
        | * { временн* * блокировк* * @AllServices } *
        | * { временн* * блокировк* * @SynonymsForAgreement } *
        | * { временно * заблокировать * @AllServices } *
        | * { временно * заблокировать * @SynonymsForAgreement } *
        | * { временно * заблокируйте * @AllServices } *
        | * { временно * заблокируйте * @SynonymsForAgreement } *
        | * { временно * закрой* * @AllServices } *
        | * { временно * закрой* * @SynonymsForAgreement } *
        | * { временно * закрывать * @AllServices } *
        | * { временно * закрывать * @SynonymsForAgreement } *
        | * { временно * закрыти* * @AllServices } *
        | * { временно * закрыти* * @SynonymsForAgreement } *
        | * { временно * закрыть * @AllServices } *
        | * { временно * закрыть * @SynonymsForAgreement } *
        | * { временно * оставить * @Suspension } *
        | * { временно * откаж* * от * @AllServices } *
        | * { временно * откаж* * от * @SynonymsForAgreement } *
        | * { временно * отказ * @AllServices } *
        | * { временно * отказ * от * @AllServices } *
        | * { временно * отказ * от * @SynonymsForAgreement } *
        | * { временно * отказ* * @SynonymsForAgreement } *
        | * { временно * отказаться * от * @AllServices } *
        | * { временно * отказаться * от * @SynonymsForAgreement } *
        | * { временно * отказыва* * от * @AllServices } *
        | * { временно * отказыва* * от * @SynonymsForAgreement } *
        | * { временно * отказываться * от * @AllServices } *
        | * { временно * отказываться * от * @SynonymsForAgreement } *
        | * { временно * отключени* * @AllServices } *
        | * { временно * расторжени* * @AllServices } *
        | * { временно * убери* * @Internet } *
        | * { временно * убери* * @Suspension } *
        | * { временно * убери* * @Telephone } *
        | * { временно * убери* * @Television } *
        | * { временно * убрать * @Internet } *
        | * { временно * убрать * @Suspension } *
        | * { временно * убрать * @Telephone } *
        | * { временно * убрать * @Television } *
        | * { делай* * @Suspension } *
        | * { делать * @Suspension } *
        | * { заморозит* * @AllServices } *
        | * { заморозит* * @SynonymsForAgreement } *
        | * { заморозит* * до @duckling.number * ~дата } *
        | * { заморозит* * до @duckling.number * ~день } *
        | * { заморозит* * до @duckling.number * месяц* } *
        | * { заморозит* * до @duckling.number * числ* } *
        | * { заморозит* * до @Months } *
        | * { заморозит* * на @duckling.number * ~дата } *
        | * { заморозит* * на @duckling.number * ~день } *
        | * { заморозит* * на @duckling.number * месяц* } *
        | * { заморозит* * на @duckling.number * числ* } *
        | * { заморозит* * на @Months } *
        | * { заморозит* * по @duckling.number * ~дата } *
        | * { заморозит* * по @duckling.number * ~день } *
        | * { заморозит* * по @duckling.number * месяц* } *
        | * { заморозит* * по @duckling.number * числ* } *
        | * { заморозит* * по @Months } *
        | * { заморозит* * с @duckling.number * ~дата } *
        | * { заморозит* * с @duckling.number * ~день } *
        | * { заморозит* * с @duckling.number * месяц* } *
        | * { заморозит* * с @duckling.number * числ* } *
        | * { заморозит* * с @Months } *
        | * { запустит* * @Suspension } *
        | * { консультац* * @Suspension } *
        | * { на время * блокировк* * @AllServices } *
        | * { на время * блокировк* * @SynonymsForAgreement } *
        | * { на время * заблокировать * @AllServices } *
        | * { на время * заблокировать * @SynonymsForAgreement } *
        | * { на время * заблокируйте * @AllServices } *
        | * { на время * заблокируйте * @SynonymsForAgreement } *
        | * { на время * закрой* * @AllServices } *
        | * { на время * закрой* * @SynonymsForAgreement } *
        | * { на время * закрывать * @AllServices } *
        | * { на время * закрывать * @SynonymsForAgreement } *
        | * { на время * закрыти* * @AllServices } *
        | * { на время * закрыти* * @SynonymsForAgreement } *
        | * { на время * закрыть * @AllServices } *
        | * { на время * закрыть * @SynonymsForAgreement } *
        | * { на время * оставить * @Suspension } *
        | * { на время * откаж* * от * @AllServices } *
        | * { на время * откаж* * от * @SynonymsForAgreement } *
        | * { на время * отказ * @AllServices } *
        | * { на время * отказ* * @SynonymsForAgreement } *
        | * { на время * отказаться * от * @AllServices } *
        | * { на время * отказаться * от * @SynonymsForAgreement } *
        | * { на время * отказыва* * от * @AllServices } *
        | * { на время * отказыва* * от * @SynonymsForAgreement } *
        | * { на время * отказываться * от * @AllServices } *
        | * { на время * отказываться * от * @SynonymsForAgreement } *
        | * { на время * отключени* * @AllServices } *
        | * { на время * расторжени* * @AllServices } *
        | * { на время * убери* * @Internet } *
        | * { на время * убери* * @Suspension } *
        | * { на время * убери* * @Telephone } *
        | * { на время * убери* * @Television } *
        | * { на время * убрать * @Internet } *
        | * { на время * убрать * @Suspension } *
        | * { на время * убрать * @Telephone } *
        | * { на время * убрать * @Television } *
        | * { начать * @Suspension } *
        | * { приостановит* * @AllServices } *
        | * { приостановит* * @SynonymsForAgreement } *
        | * { приостановит* * до @duckling.number * ~дата } *
        | * { приостановит* * до @duckling.number * ~день } *
        | * { приостановит* * до @duckling.number * месяц* } *
        | * { приостановит* * до @duckling.number * числ* } *
        | * { приостановит* * до @Months } *
        | * { приостановит* * на @duckling.number * ~дата } *
        | * { приостановит* * на @duckling.number * ~день } *
        | * { приостановит* * на @duckling.number * месяц* } *
        | * { приостановит* * на @duckling.number * числ* } *
        | * { приостановит* * на @Months } *
        | * { приостановит* * по @duckling.number * ~дата } *
        | * { приостановит* * по @duckling.number * ~день } *
        | * { приостановит* * по @duckling.number * месяц* } *
        | * { приостановит* * по @duckling.number * числ* } *
        | * { приостановит* * по @Months } *
        | * { приостановит* * с @duckling.number * ~дата } *
        | * { приостановит* * с @duckling.number * ~день } *
        | * { приостановит* * с @duckling.number * месяц* } *
        | * { приостановит* * с @duckling.number * числ* } *
        | * { приостановит* * с @Months } *
        | * { провер* * @Suspension } *
        | * { продлев* * @Suspension } *
        | * { продлен* * @Suspension } *
        | * { продлит* * @Suspension } *
        | * { продолж* * @Suspension } *
        | * { продолжа* * @Suspension } *
        | * { продолжи* * @Suspension } *
        | * { расскаж* * @Suspension } *
        | * { рассказать * @Suspension } *
        | * { сделай* * @Suspension } *
        | * { сделать * @Suspension } *
        | * { срок* * @Suspension } *
        | * { узнать * @Suspension } *)
        
    $150_OtherProvider = ( * { менять * провайдер* } *
        | * { поменять * провайдер* } *
        | * { измени* * провайдер* } *
        | * { выбра* * друг* * провайдер* } *
        | * { использ* * друг* * провайдер* } *
        | * { переманил* * провайдер* } *
        | * { переход* * провайдер* } *
        | * { перехож* * провайдер* } *
        | * { подключ* * друг* * провайдер* } *
        | * { получ* опыт * провайдер* } *
        | * { пользован* * друг* * провайдер* } *
        | * { попольз* * друг* * провайдер* } *
        | * { попрактик* * друг* * провайдер* } *
        | * { попроб* * друг* * провайдер* } *
        | * { потести* * друг* * провайдер* } *
        | * { провер* * друг* * провайдер* } *
        | * { протести* * друг* * провайдер* } *
        | * { сравн* * провайдер* } *
        | * { переключ* * друг* * провайдер* } *
        | * { заключ* * друг* * провайдер* } *
        | * { менять * @Competitors } *
        | * { поменять * @Competitors } *
        | * { измени* * @Competitors } *
        | * { выбра* * @Competitors } *
        | * { использ* * @Competitors } *
        | * { переманил* * @Competitors } *
        | * { переход* * @Competitors } *
        | * { перехож* * @Competitors } *
        | * { подключ* * @Competitors } *
        | * { получ* опыт * @Competitors } *
        | * { пользован* * @Competitors } *
        | * { попольз* * @Competitors } *
        | * { попрактик* * @Competitors } *
        | * { попроб* * @Competitors } *
        | * { потести* * @Competitors } *
        | * { провер* * @Competitors } *
        | * { протести* * @Competitors } *
        | * { сравн* * @Competitors } *
        | * { переключ* * @Competitors } *
        | * { заключ* * @Competitors } *) 
        
    $155_CancelSuspend  = (  * { выключ* * @Suspension } *
        | * { отключ* * @Suspension } *
        | * { заблокир* * @Suspension } *
        | * { заверш* * @Suspension } *
        | * { (*канчивать/*кончить) * @Suspension } *
        | * { (закрывать/закрыть/закрой*) * @Suspension } *
        | * { (@Change/@ChangePast) * @Suspension } *
        | * { отказ* * @Suspension } *
        | * { закрыти* * @Suspension } *
        | * { сняти* * @Suspension } *
        | * { (перенос*/пернес*) * @Suspension } *
        | * { сброс* * @Suspension } *
        | * { дроп* * @Suspension } *
        | * { прерыва* * @Suspension } *
        | * { прерва* * @Suspension } *
        | * { снима* * @Suspension } *
        | * { снимай* * @Suspension } *
        | * { снимет* * @Suspension } *
        | * { снять * @Suspension } *
        | * { сними* * @Suspension } *
        | * { убери* * @Suspension } *
        | * { убрать * @Suspension } *
        | * { убирай* * @Suspension } *
        | * { аннулир* * @Suspension } *
        | * { отмен* * @Suspension } *
        | * { удали* * @Suspension } * )    
        
    $160_ContractDetails = ( * { перезаключ* * @SynonymsForAgreement } *
        | * { перезаключ* * услуг* } * 
        | * { переоформ* * @SynonymsForAgreement} *
        | * { переоформ* * услуг*} * ) 
        
    $160_ContractDetailAddressIntent = ( * { перенес* * договор* } *
        | * { перенос* * договор* } *
        | * { переез* * адрес* } *
        | * { переез* * место* } *
        | * { перенес* * адрес* } *
        | * { перенес* * мест* } *
        | * { перенос* * адрес* } *
        | * { перенос* * мест* } *
        | * { сменить * адрес* } *
        | * { сменить * мест* } *
        | * { изменить * адрес* } *
        | * { изменить * мест* } * )
        
    $160_ContractDetailPersonIntent = (  * { измен* * человек*} *
        | * { передел* * человек* } *
        | * { переоформ* * человек*} *
        | * { перепис* * человек* } *
        | * { сменит* * человек*} *
        | * { измен* * лицо} *
        | * { передел* * лицо} *
        | * { переоформ* * лицо} *
        | * { перепис* * лицо } *
        | * { сменит* * лицо} *
        | * { переоформ* * себя } *
        | * { переоформ* * меня } *
        | * { перезаключ* * себя } *
        | * { перезаключ* * меня } *
        | * { измен* * владел* * договор* } *
        | * { передел* * владел* * договор* } *
        | * { переоформ* * владел* * договор* } *
        | * { перепис* * владел* * договор* } *
        | * { сменит* * владел* * договор* } * )
        
    $170_SubscriptionFeeIncrease = ( * { [почему] * дорож* * начислени* } *
        | * { [почему] * дорож* * сумм* } *
        | * { [почему] * дорож* * @Payment } *
        | * { [почему] * дорож* * ~цена } *
        | * { [почему] * дорож* * (@Tariff/@SubscriptionFee) } *
        | * { [почему] * вырос* * начислени* } *
        | * { [почему] * вырос* * сумм* } *
        | * { [почему] * вырос* * @Payment } *
        | * { [почему] * вырос* * ~цена } *
        | * { [почему] * вырос* * (@Tariff/@SubscriptionFee) } *
        | * { [почему] * вырас* * начислени* } *
        | * { [почему] * вырас* * сумм* } *
        | * { [почему] * вырас* * @Payment } *
        | * { [почему] * вырас* * ~цена } *
        | * { [почему] * вырас* * (@Tariff/@SubscriptionFee) } *
        | * { [почему] * добавил* * начислени* } *
        | * { [почему] * добавил* * сумм* } *
        | * { [почему] * добавил* * @Payment } *
        | * { [почему] * добавил* * ~цена } *
        | * { [почему] * добавил* * (@Tariff/@SubscriptionFee) } *
        | * { [почему] * измен* * начислени* } *
        | * { [почему] * измен* * сумм* } *
        | * { [почему] * измен* * @Payment } *
        | * { [почему] * измен* * ~цена } *
        | * { [почему] * измен* * @SubscriptionFee } *
        | * { [почему] * повыси* * начислени* } *
        | * { [почему] * повыси* * сумм* } *
        | * { [почему] * повыси* * @Payment } *
        | * { [почему] * повыси* * ~цена } *
        | * { [почему] * повыси* * (@Tariff/@SubscriptionFee) } *
        | * { [почему] * повыш* * начислени* } *
        | * { [почему] * повыш* * сумм* } *
        | * { [почему] * повыш* * @Payment } *
        | * { [почему] * повыш* * ~цена } *
        | * { [почему] * повыш* * (@Tariff/@SubscriptionFee) } *
        | * { [почему] * поднят* * начислени* } *
        | * { [почему] * поднят* * сумм* } *
        | * { [почему] * поднят* * @Payment } *
        | * { [почему] * поднят* * ~цена } *
        | * { [почему] * поднят* * (@Tariff/@SubscriptionFee) } *
        | * { [почему] * поднял* * начислени* } *
        | * { [почему] * поднял* * сумм* } *
        | * { [почему] * поднял* * @Payment } *
        | * { [почему] * поднял* * ~цена } *
        | * { [почему] * поднял* * (@Tariff/@SubscriptionFee) } *
        | * { [почему] * подорож* * начислени* } *
        | * { [почему] * подорож* * сумм* } *
        | * { [почему] * подорож* * @Payment } *
        | * { [почему] * подорож* * ~цена } *
        | * { [почему] * подорож* * (@Tariff/@SubscriptionFee) } *
        | * { [почему] * прибавил* * начислени* } *
        | * { [почему] * прибавил* * сумм* } *
        | * { [почему] * прибавил* * @Payment } *
        | * { [почему] * прибавил* * ~цена } *
        | * { [почему] * прибавил* * (@Tariff/@SubscriptionFee) } *
        | * { [почему] * растет * начислени* } *
        | * { [почему] * растет * сумм* } *
        | * { [почему] * растет * @Payment } *
        | * { [почему] * растет * ~цена } *
        | * { [почему] * растут * начислени* } *
        | * { [почему] * растут * сумм* } *
        | * { [почему] * растут * @Payment } *
        | * { [почему] * растут * ~цена } *
        | * { [почему] * растет * (@Tariff/@SubscriptionFee) } *
        | * { [почему] * растут * (@Tariff/@SubscriptionFee) } *
        | * { [почему] * расти * (начислени*/сумм*/@Payment/~цена) } *
        | * { [почему] * расти * (@Tariff/@SubscriptionFee) } *
        | * { [почему] * стал* больш* * начислени* } *
        | * { [почему] * стал* больш* * сумм* } *
        | * { [почему] * стал* больш* * @Payment } *
        | * { [почему] * стал* больш* * ~цена } *
        | * { [почему] * стал* выше * начислени* } *
        | * { [почему] * стал* выше * сумм* } *
        | * { [почему] * стал* выше * @Payment } *
        | * { [почему] * стал* выше * ~цена } *
        | * { [почему] * стал* дорог* * начислени* } *
        | * { [почему] * стал* дорог* * сумм* } *
        | * { [почему] * стал* дорог* * @Payment } *
        | * { [почему] * стал* дорог* * ~цена } *
        | * { [почему] * стал* дорож* * начислени* } *
        | * { [почему] * стал* дорож* * сумм* } *
        | * { [почему] * стал* дорож* * @Payment } *
        | * { [почему] * стал* дорож* * ~цена } *
        | * { [почему] * увелич* * начислени* } *
        | * { [почему] * увелич* * сумм* } *
        | * { [почему] * увелич* * @Payment } *
        | * { [почему] * увелич* * ~цена } *
        | * { [почему] * увелич* * (@Tariff/@SubscriptionFee) } *
        | * { [почему] * удорож* * начислени* } *
        | * { [почему] * удорож* * сумм* } *
        | * { [почему] * удорож* * @Payment } *
        | * { [почему] * удорож* * ~цена } *
        | * { [почему] * удорож* * (@Tariff/@SubscriptionFee) } *
        | * { [почему] * случил* * повышени* * @Payment} *
        | * { [почему] * случил* * увеличени* * @Payment} *
        | * { [почему] * случил* * рост* * @Payment} *
        | * { [почему] * случил* * изменени* * @Payment} *
        | * { [почему] * произош* * повышени* * @Payment} *
        | * { [почему] * произош* * увеличени* * @Payment} *
        | * { [почему] * произош* * рост* * @Payment} *
        | * { [почему] * произош* * изменени* * @Payment} *
        | * { [почему] * изменил* * ежемесячн* * начислени* } *
        | * { [почему] * изменил* * ежемесячн* * сумм* } *
        | * { [почему] * изменил* * ежемесячн* * @Payment } *
        | * { [почему] * изменил* * ежемесячн* * ~цена } *
        | * { [почему] * изменил* * абон* * начислени* } *
        | * { [почему] * изменил* * абон* * сумм* } *
        | * { [почему] * изменил* * абон* * @Payment } *
        | * { [почему] * изменил* * абон* * ~цена } *
        | * { [почему] * изменил* * @Payment * @Month} *
        | * { [почему] * изменил* * @SubscriptionFee * @Month} *
        | * { [почему] * изменил* * ~цена * @Month} *
        | * { [почему] * изменил* * размер * начислени* } *
        | * { [почему] * изменил* * размер * сумм* } *
        | * { [почему] * изменил* * размер * @Payment } *
        | * { [почему] * изменил* * размер * ~цена } *
        | * { [почему] * изменил* * стоимост* * @Tariff } *
        | * { [почему] * изменил* * стоимост* * @SubscriptionFee } *
        | * { [почему] * изменил* * стоимост* * @Month} *
        | * { [почему] * изменил* * сумм* * @Month} *
        | * { [почему] * повысил* * ежемесячн* * начислени* } *
        | * { [почему] * повысил* * ежемесячн* * сумм* } *
        | * { [почему] * повысил* * ежемесячн* * @Payment } *
        | * { [почему] * повысил* * ежемесячн* * ~цена } *
        | * { [почему] * повысил* * абон* * начислени* } *
        | * { [почему] * повысил* * абон* * сумм* } *
        | * { [почему] * повысил* * абон* * @Payment } *
        | * { [почему] * повысил* * абон* * ~цена } *
        | * { [почему] * повысил* * @Payment * @Month} *
        | * { [почему] * повысил* * @SubscriptionFee * @Month} *
        | * { [почему] * повысил* * ~цена * @Month} *
        | * { [почему] * повысил* * размер * начислени* } *
        | * { [почему] * повысил* * размер * сумм* } *
        | * { [почему] * повысил* * размер * @Payment } *
        | * { [почему] * повысил* * размер * ~цена } *
        | * { [почему] * повысил* * стоимост* * @Tariff } *
        | * { [почему] * повысил* * стоимост* * @SubscriptionFee } *
        | * { [почему] * повысил* * стоимост* * @Month} *
        | * { [почему] * повысил* * сумм* * @Month} *
        | * { [почему] * поменял* * ежемесячн* * начислени* } *
        | * { [почему] * поменял* * ежемесячн* * сумм* } *
        | * { [почему] * поменял* * ежемесячн* * @Payment } *
        | * { [почему] * поменял* * ежемесячн* * ~цена } *
        | * { [почему] * поменял* * абон* * начислени* } *
        | * { [почему] * поменял* * абон* * сумм* } *
        | * { [почему] * поменял* * абон* * @Payment } *
        | * { [почему] * поменял* * абон* * ~цена } *
        | * { [почему] * поменял* * @Payment * @Month} *
        | * { [почему] * поменял* * @SubscriptionFee * @Month} *
        | * { [почему] * поменял* * ~цена * @Month} *
        | * { [почему] * поменял* * размер * начислени* } *
        | * { [почему] * поменял* * размер * сумм* } *
        | * { [почему] * поменял* * размер * @Payment } *
        | * { [почему] * поменял* * размер * ~цена } *
        | * { [почему] * поменял* * стоимост* * @Tariff } *
        | * { [почему] * поменял* * стоимост* * @SubscriptionFee } *
        | * { [почему] * поменял* * стоимост* * @Month} *
        | * { [почему] * поменял* * сумм* * @Month} *
        | * { слишком * дорог* @Payment } *
        | * { слишком * дорог* ~цена } *
        | * { был* * начислени* * стал*} *
        | * { был* * сумм* * стал*} *
        | * { был* * @Payment * стал*} *
        | * { был* * ~цена * стал*} *
        | * { был* * @duckling.amount-of-money * стал* * @duckling.number} *
        | * { был* * @duckling.amount-of-money * сейчас * @duckling.number} *)
        
    $180_OffersHelpIntent = ( * { @Tariff * @Internet} *
        | * { @Tariff * @Television} *
        | * { @Tariff @DomRu} *
        | * { безлимитн* * @Internet } *
        | * { безлимитн* * @Telephone } *
        | * { @Tariff * @SynonymsForAgreement } *
        | * { узна* * @Tariff } *
        | * { перечисл* * @Tariff } *
        | * { назов* * @Tariff } *
        | * { названи* * @Tariff } *
        | * { интерес* * @Tariff } *
        | * { @Modal * @Tariff * @Beneficiary } *
        | * { @Tariff * остается * @duckling.number * ~рубль } *
        | * { @Tariff * остался * @duckling.number * ~рубль } *
        | * { @Tariff * будет * @duckling.number * ~рубль } *
        | * @Tariff *
        | * { ~база * @Tariff * } *
        | * { линейк* * @Tariff * } *
        | * { компан* * @Tariff * } *
        | * { вопрос* * @Tariff * } *
        | * { консультац* * @Tariff * } *
        | * { информац* * @Tariff * } *
        | * { друг* * @Tariff * } *
        | * { актуальн* * @Tariff * } *
        | * { нов* * @Tariff * } *
        | * { каки* * @Tariff * } *
        | * { @Tariff * @PersonalAccount } *
        | * { какой * (меня/нас/наш/нашему) * @Tariff } *
        | * { какой * (свой/своего/своему/своим/своём/своем) * @Tariff } *
        | * { какой * @MyMale * @Tariff } *
        | * { какие * (меня/нас/наш/нашему) * @Tariff } *
        | * { какие * (свой/своего/своему/своим/своём/своем) * @Tariff } *
        | * { какие * @MyMale * @Tariff } *
        | * { @MyMale * @Tariff } *
        | * { разъясни* * @MyMale * @Tariff } *
        | * { вход* * @MyMale * @Tariff } * 
        | * { @Tariff * включает * [белый] @IPaddress } *   
        | * { какой * выбра* * @TariffSingular [@AllServices]} *   )
        
    $200_ApplicationHelp = ( * { *регистрир* * приложен* } *
        | * { авторизов* * приложен* } *
        | * { актив* * приложен* } *
        | * { интерес* * приложен* } *
        | * { вопрос* * приложен* } *
        | * { консультаци* * приложен* } *
        | * { информаци* * приложен* } *
        | * { вход* * приложен* } *
        | * { авториз* * приложен* } *
        | * { регистр* * приложен* } *
        | * { настра* * приложен* } *
        | * { настрой* * приложен* } *
        | * { настро* * приложен* } *
        | * { недоступ* * приложен* } *
        | * { не доступ* * приложен* } *
        | * { неисправ* * приложен* } *
        | * { не исправ* * приложен* } *
        | * { проблем* * приложен* } *
        | * { ошибк* * приложен* } *
        | * { жалоб* * приложен* } *
        | * { неисправност* * приложен* } *
        | * { неполадк* * приложен* } *
        | * { установ* * приложен* } *
        | * { устанав* * приложен* } *
        | * { восстанав* * приложен* } *
        | * { восстанов* * приложен* } *
        | * { заблокиров* * приложен* } *
        | * { не @Successful * войти * приложен* } *
        | * { не @Successful * зайти * приложен* } *
        | * { не @Successful * подключ* * приложен* } *
        | * { не @Successful * откры* * приложен* } *
        | * { не запуска* * приложен* } *
        | * { не отвеча* * приложен* } *
        | * { не работа* * приложен* } *
        | * { нет доступ* * приложен* } *
        | * { нету доступ* * приложен* } *
        | * { парол* * приложен* } *
        | * { перестал* * вход* * приложен* } *
        | * { перестал* * заход* * приложен* } *
        | * { перестал* * подключ* * приложен* } *
        | * { поставит* * приложен* } *
        | * { не откры* * приложен* } * )
        
    $220_ConfigureInternetConnection = ( * { восстанав* * мак * @InternetEquipment } *
        | * { восстанов* * мак * @InternetEquipment } *
        | * { восстанав* * мак * @WiFi @Equipment } *
        | * { восстанов* * мак * @WiFi @Equipment } *
        | * { менять * мак * @InternetEquipment } *
        | * { поменять * мак * @InternetEquipment } *
        | * { изменить * мак * @InternetEquipment } *
        | * { поменял* * мак * @InternetEquipment } *
        | * { изменил* * мак * @InternetEquipment } *
        | * { менять * мак * @WiFi @Equipment } *
        | * { поменять * мак * @WiFi @Equipment } *
        | * { изменить * мак * @WiFi @Equipment } *
        | * { поменял* * мак * @WiFi @Equipment } *
        | * { изменил* * мак * @WiFi @Equipment } * 
        | * { настр* * данные * @Internet } * 
        | * { купил* * @TVSet * (не [@Modal] подключ*) * @Internet } * )
        
    $230_RestoreLogoPass = (* { *регистрир* * @PersonalAccount } *
        | * { авториз* * @PersonalAccount } *
        | * { отправ* * @Credentials } *
        | * { направ* * @Credentials } *
        | * { пошли* * @Credentials } *
        | * { вышли* * @Credentials } *
        | * { *шлите * @Credentials } *
        | * { *шли * @Credentials } *
        | * { *слать * @Credentials } *
        | * { *сылать * @Credentials } *
        | * { @Credentials * для * вход* } *
        | * { @Credentials * для * авторизац* } *
        | * { @Credentials * для * регистрац* } *
        | * { данные * для * вход* } *
        | * { данные * для * авторизац* } *
        | * { данные * для * регистрац* } *
        | * { ввести * @Credentials } *
        | * { вводить * @Credentials } *
        | * { (войти/вход*) * @PersonalAccount } *
        | * { вопрос* * @Credentials } *
        | * { информаци* * @Credentials } *
        | * { восстанов* * @Credentials } *
        | * { консульт* * @Credentials } *
        | * { забыл* * @Credentials } *
        | * { забыть * @Credentials } *
        | * { забывать * @Credentials } *
        | * { *логин* * @PersonalAccount } *
        | * { зайти * @PersonalAccount } *
        | * { заходить * @PersonalAccount } *
        | * { (какой/какие/какая) * @Credentials } *
        | * { *кинуть * @SMS * @Credentials } *
        | * { *кинь * @SMS * @Credentials } *
        | * { *киньте * @SMS * @Credentials } *
        | * { *кидайте * @SMS * @Credentials } *
        | * { *кидай * @SMS * @Credentials } *
        | * { кинь* * @SMS * @Credentials } *
        | * { скинь* * @SMS * @Credentials } *
        | * { скидывай* * @SMS * @Credentials } *
        | * { кидай* * @SMS * @Credentials } *
        | * { *кинуть * сообщени* * @Credentials } *
        | * { *кинь * сообщени* * @Credentials } *
        | * { *киньте * сообщени* * @Credentials } *
        | * { *кидайте * сообщени* * @Credentials } *
        | * { *кидай * сообщени* * @Credentials } *
        | * { кинь* * сообщени* * @Credentials } *
        | * { скинь* * сообщени* * @Credentials } *
        | * { скидывай* * сообщени* * @Credentials } *
        | * { кидай* * сообщени* * @Credentials } *
        | * { *менять * @PersonalInformation } *
        | * { *менить * @PersonalInformation } *
        | * { *менял* * @PersonalInformation } *
        | * { *менил* * @PersonalInformation } *
        | * { *меняй * @PersonalInformation } *
        | * { *меняйте * @PersonalInformation } *
        | * { *мените * @PersonalInformation } *
        | * { (смена/смену) * @PersonalInformation } *
        | * { изменени* * @PersonalInformation } *
        | * { *менять * данные } *
        | * { *менить * данные } *
        | * { *менял* * данные } *
        | * { *менил* * данные } *
        | * { *меняй * данные } *
        | * { *меняйте * данные } *
        | * { *мените * данные } *
        | * { (смена/смену) * данные } *
        | * { изменени* * данные } *
        | * { найти * @Credentials } *
        | * { нашл* * @Credentials } *
        | * { нашел* * @Credentials } *
        | * { найден* * @Credentials } *
        | * { находит* * @Credentials } *
        | * { не подход* * @Credentials } *
        | * { не совпад* * (@Credentials/@PersonalInformation) } *
        | * { не совпал* * (@Credentials/@PersonalInformation) } *
        | * { неправильн* * (@Credentials/@PersonalInformation) } *
        | * { неверн* * (@Credentials/@PersonalInformation) } *
        | * { несовпад* * (@Credentials/@PersonalInformation) } *
        | * { ошиб* * (@Credentials/@PersonalInformation) } *
        | * { теря* * @Credentials } *
        | * { потеря* * @Credentials } *
        | * { утеря* * @Credentials } *
        | * { проблем* * @Credentials } *
        | * { сложност* * @Credentials } *
        | * { @Credentials * @SMS } *
        | * { @Credentials * сообщени* } *
        | * { @Credentials * для * @PersonalAccount } *
        | * { @Credentials * для * приложени* } *
        | * { @Credentials * на * сотов* } *
        | * { @Credentials * на * мобильн* } *
        | * { @Credentials * на * телефон* } *
        | * { @Credentials * на * почт* } *
        | * { @Credentials * на * смартфон* } *
        | * { @Credentials * на * @Email } *
        | * { @Credentials @DomRu} *
        | * { @Give * @Credentials } *
        | * { *помнит* * @Credentials } *
        | * { *помню * @Credentials } *
        | * { *помним * @Credentials } *
        | * { *помнят * @Credentials } *
        | * { *помни * @Credentials } *
        | * { не * получа* * @Credentials} *
        | * { не * получи* * @Credentials} *
        | * { не * попасть * @Credentials} *
        | * { не * попад* * @Credentials} *
        | * { не * пуска* * @Credentials} *
        | * { не @Successful * найти * @Credentials } *
        | * { не @Successful * находить * @Credentials } *
        | * { получи* * @Credentials } *
        | * { получа* * @Credentials } *
        | * { попасть * @PersonalAccount } *
        | * { попад* * @Credentials} *
        | * { пуска* * @Credentials} *
        | * { узнать * @Credentials } *
        | * { без понятия * @Credentials } *
        | * { номер счет* * @Credentials } *
        | * { номер @SynonymsForAgreement * @Credentials } * ) 
        
    $240_EquipmentReturn = ( * @RemoteControl дистанционного управления *
        | * { (плохо/ужасно) * включает* * @Equipment } *
        | * { (плохо/ужасно) * включает* * @RemoteControl } *
        | * { (плохо/ужасно) * переключает* * @Equipment } *
        | * { (плохо/ужасно) * переключает* * @RemoteControl } *
        | * { (плохо/ужасно) * работает * @Equipment } *
        | * { (плохо/ужасно) * работает * @RemoteControl } *
        | * { (плохо/ужасно) * реагирует * @Equipment } *
        | * { (плохо/ужасно) * реагирует * @RemoteControl } *
        | * { *менять * @Equipment } *
        | * { *менять * @InternetEquipment} *
        | * { *менять * @RemoteControl } *
        | * { *менять * @TVEquipment } *
        | * { *менять * @YandexAlisaEquipment } *
        | * { @RemoteControl * не включ* * @TVChannel } *
        | * { @RemoteControl * не включ* * @Television } *
        | * { @RemoteControl * не включ* * @TVSet } *
        | * { @RemoteControl * не переключ* * @TVChannel } *
        | * { @RemoteControl * не переключ* * @Television } *
        | * { @RemoteControl * не переключ* * @TVSet } *
        | * { стар* * @Equipment } *
        | * { вернуть * @Equipment } *
        | * { вернуть * @InternetEquipment } *
        | * { вернуть * @RemoteControl } *
        | * { вернуть * @TVEquipment } *
        | * { вернуть * @YandexAlisaEquipment } *
        | * { замен* * @Equipment } *
        | * { замен* * @InternetEquipment } *
        | * { замен* * @RemoteControl } *
        | * { замен* * @TVEquipment } *
        | * { замен* * @YandexAlisaEquipment } *
        | * { не включ* * @Equipment } *
        | * { не включ* * @RemoteControl } *
        | * { не нравится * нов* * @Equipment } *
        | * { не нравится * нов* * @InternetEquipment } *
        | * { не нравится * нов* * @RemoteControl } *
        | * { не нравится * нов* * @TVEquipment } *
        | * { не нравится * нов* * @YandexAlisaEquipment } *
        | * { не переключ* * @Equipment } *
        | * { не переключ* * @RemoteControl } *
        | * { не работа* * @Equipment } *
        | * { не работа* * @RemoteControl } *
        | * { не реагиру* * @Equipment } *
        | * { не реагиру* * @RemoteControl } *
        | * { оставить * @Equipment } *
        | * { оставить * @InternetEquipment } *
        | * { оставить * @RemoteControl } *
        | * { оставить * @TVEquipment } *
        | * { оставить * @YandexAlisaEquipment } *
        | * { отдать * @Equipment } *
        | * { отдать * @InternetEquipment } *
        | * { отдать * @RemoteControl } *
        | * { отдать * @TVEquipment } *
        | * { отдать * @YandexAlisaEquipment } *
        | * { ошибк* * @Equipment } *
        | * { ошибк* * @RemoteControl } *
        | * { поменя* * @Equipment } *
        | * { поменя* * @InternetEquipment } *
        | * { поменя* * @RemoteControl } *
        | * { поменя* * @TVEquipment } *
        | * { поменя* * @YandexAlisaEquipment } *
        | * { потерял* * @Equipment } *
        | * { потерял* * @RemoteControl } *
        | * { проблем* * @Equipment } *
        | * { проблем* * @RemoteControl } *
        | * { продать * @Equipment } *
        | * { продать * @InternetEquipment } *
        | * { продать * @RemoteControl } *
        | * { продать * @TVEquipment } *
        | * { продать * @YandexAlisaEquipment } *
        | * { ремонт* * @Equipment } *
        | * { ремонт* * @RemoteControl } *
        | * { ремонт* * @YandexAlisaEquipment } *
        | * { сгорел* * @Equipment } *
        | * { сгорел* * @InternetEquipment } *
        | * { сгорел* * @RemoteControl } *
        | * { сгорел* * @TVEquipment } *
        | * { сгорел* * @YandexAlisaEquipment } *
        | * { сдать * @Equipment } *
        | * { сдать * @InternetEquipment } *
        | * { сдать * @RemoteControl } *
        | * { сдать * @TVEquipment } *
        | * { сдать * @YandexAlisaEquipment } *
        | * { сломан* * @Equipment } *
        | * { сломан* * @RemoteControl } *
        | * { смен* * @Equipment } *
        | * { смен* * @InternetEquipment } *
        | * { смен* * @RemoteControl } *
        | * { смен* * @TVEquipment } *
        | * { смен* * @YandexAlisaEquipment } *
        | * { стар* @RemoteControl } * 
        | * { универсальн* @RemoteControl } *
        | * { возврат* * @Equipment } *
        | * { возврат* * @InternetEquipment} *
        | * { возврат* * @RemoteControl } *
        | * { возврат* * @TVEquipment } *
        | * { возврат* * @YandexAlisaEquipment } *
        | * { выдач* * @Equipment } *
        | * { выдач* * @InternetEquipment} *
        | * { выдач* * @RemoteControl } *
        | * { выдач* * @TVEquipment } *
        | * { выдач* * @YandexAlisaEquipment } *
        | * { выкуп* * (аренд*/рассрочк*) * @Equipment } *
        | * {остаток* * аренд*  } *
        | * {остаток* * рассрочк* } *
        | * {закр* * аренд*  } *
        | * {закр* * рассрочк* } *
        | * {*гасить * долг* * аренд* } *
        | * {*гасить * задолж* * аренд* } *
        | * {*гасить * минус* * аренд*  } *
        | * {*гасить * долг* * рассрочк* } *
        | * {*гасить * задолж* * рассрочк* } *
        | * {*гасить * минус* * рассрочк* } *
        | * {*плат* * долг* * аренд* } *
        | * {*плат* * задолж* * аренд* } *
        | * {*плат* * минус* * аренд*  } *
        | * {*плат* * долг* * рассрочк* } *
        | * {*плат* * задолж* * рассрочк* } *
        | * {*плат* * минус* * рассрочк* } *
        | * { аренд* * @Equipment } *
        | * { аренд* * @InternetEquipment} *
        | * { аренд* * @RemoteControl } *
        | * { аренд* * @TVEquipment } *
        | * { аренд* * @YandexAlisaEquipment } *
        | * { рассрочк* * @Equipment } *
        | * { рассрочк* * @InternetEquipment} *
        | * { рассрочк* * @RemoteControl } *
        | * { рассрочк* * @TVEquipment } *
        | * { рассрочк* * @YandexAlisaEquipment } *
        | * { обновит* * @Equipment } *
        | * { обновит* * @InternetEquipment} *
        | * { обновит* * @RemoteControl } *
        | * { обновит* * @TVEquipment } *
        | * { обновит* * @YandexAlisaEquipment } *
        | * { нов* * @Equipment } *
        | * { нов* * @InternetEquipment} *
        | * { нов* * @RemoteControl } *
        | * { нов* * @TVEquipment } *
        | * { нов* * @YandexAlisaEquipment } * )
        
    $260_PageNotOpen = ( * { ошибк* * @WebBrowser } *
        |* { ошибк* * @Website }
        |* { ничего * не откры* * @Website } *
        |* { ничего * не работа* * @Website } *
        |* { ничего * не откры* * @WebBrowser } *
        |* { ничего * не работа* * @WebBrowser } *
        |* { нихрена * не работа* * @Website } *
        |* { нихрена * не откры* * @Website } *
        |* { нихрена * не работа* * @WebBrowser } *
        |* { нихрена * не откры* * @WebBrowser } *
        |* { нифига * не работа* * @Website } *
        |* { нифига * не откры* * @Website } *
        |* { нифига * не работа* * @WebBrowser } *
        |* { нифига * не откры* * @WebBrowser } *
        |* { нихера * не работа* * @Website } *
        |* { нихера * не откры* * @Website } *
        |* { нихера * не работа* * @WebBrowser } *
        |* { нихера * не откры* * @WebBrowser } *
        |* { всё * не откры* * @WebBrowser } *
        |* { не @Successful * откры* * @WebBrowser } *
        |* { не @Successful * загруж* * @WebBrowser } *
        |* { не @Successful * загруз* * @WebBrowser } *
        |* { не @Successful * подгруз* * @WebBrowser } *
        |* { не @Successful * подгруж* * @WebBrowser } *
        |* { не @Successful * войти * @WebBrowser } *
        |* { не откры* * @WebBrowser } *
        |* { не загруж* * @WebBrowser } *
        |* { не загруз* * @WebBrowser } *
        |* { не подгруз* * @WebBrowser } *
        |* { не подгруж* * @WebBrowser } *
        |* { не войти * @WebBrowser } *
        |* { всё * не откры* * @Website } *
        |* { не @Successful * откры* * @Website } *
        |* { не @Successful * загруж* * @Website } *
        |* { не @Successful * загруз* * @Website } *
        |* { не @Successful * подгруз* * @Website } *
        |* { не @Successful * подгруж* * @Website } *
        |* { не @Successful * войти * @Website } *
        |* { не откры* * @Website } *
        |* { не загруж* * @Website } *
        |* { не загруз* * @Website } *
        |* { не подгруз* * @Website } *
        |* { не подгруж* * @Website } *
        |* { не войти * @Website } *
        |* { не работа* * @WebBrowser } *
        |* { не работа* * @Website } *
        |* { откры* * $color * @WebBrowser } *
        |* { откры* * $color * @Website } * )
        
    $270_TVQualityProblem = (* {$regexp<р[яеи]бит> * @Television } *
        | * {$regexp<р[яеи]бит> * @TVSet } *
        | * {$regexp<р[яеи]бит> * @TVChannel } *
        | * {$regexp<р[яеи]бят> * @TVChannel } *
        | * {$regexp<ряб[ьюи]> * @Television } *
        | * {$regexp<ряб[ьюи]> * @TVSet } *
        | * {@TVChannel * без * звук* [дорожк*] } *
        | * {$bad качество * @Television} *
        | * {$bad качество * @TVSet} *
        | * {$bad качество * видео } *
        | * {$bad качество * звук* } *
        | * {$bad качество * изображени* } *
        | * {$bad качество * пропорци* } *
        | * {$bad качество * разрешени* } *
        | * {$bad качество * экран* * @TVSet } *
        | * {$bad показывает * @Television } *
        | * {$bad показывает * @TVSet } *
        | * {$bad показывает * TVEquipment } *
        | * {$bad работает * видео } *
        | * {$bad работает * звук* } *
        | * {$bad работает * изображени* } *
        | * {$bad работает * картинк* } *
        | * {$bad работает * пропорци* } *
        | * {$bad работает * разрешени* } *
        | * {$bad качество * @TVChannel} *
        | * {беда * видео * @Television } *
        | * {беда * видео * @TVSet } *
        | * {беда * звук* * @Television } *
        | * {беда * звук* * @TVSet } *
        | * {беда * изображени* * @Television } *
        | * {беда * изображени* * @TVSet } *
        | * {беда * картинк* * @Television } *
        | * {беда * картинк* * @TVSet } *
        | * {беда * качеств* * @Television } *
        | * {беда * качеств* * @TVSet } *
        | * {беда * пропорци* * @Television } *
        | * {беда * пропорци* * @TVSet } *
        | * {беда * разрешени* * @Television } *
        | * {беда * разрешени* * @TVSet } *
        | * {видео * без * звук* [дорожк*] } *
        | * {жалоб* * видео * @Television } *
        | * {жалоб* * видео * @TVSet } *
        | * {жалоб* * звук* * @Television } *
        | * {жалоб* * звук* * @TVSet } *
        | * {жалоб* * изображени* * @Television } *
        | * {жалоб* * изображени* * @TVSet } *
        | * {жалоб* * картинк* * @Television } *
        | * {жалоб* * картинк* * @TVSet } *
        | * {жалоб* * качеств* * @Television } *
        | * {жалоб* * качеств* * @TVSet } *
        | * {жалоб* * пропорци* * @Television } *
        | * {жалоб* * пропорци* * @TVSet } *
        | * {жалоб* * разрешени* * @Television } *
        | * {жалоб* * разрешени* * @TVSet } *
        | * {изображени* * без * звук* [дорожк*] } *
        | * {искажает* * экран* * @TVSet } *
        | * {искажен* * экран* * @TVSet } *
        | * {картинк* * без * звук* [дорожк*] } *
        | * {квадрат* * @Television} *
        | * {квадрат* * @TVChannel * @TVSet} *
        | * {квадрат* * видео * @TVChannel } *
        | * {квадрат* * звук* * @TVChannel } *
        | * {квадрат* * изображени* * @TVChannel } *
        | * {квадрат* * картинк* * @TVChannel } *
        | * {квадрат* * пропорци* * @TVChannel } *
        | * {квадрат* * разрешени* * @TVChannel } *
        | * {квадрат* * экран* * @TVSet} *
        | * {клетк* * @Television} *
        | * {клетк* * @TVChannel * @TVSet } *
        | * {клетк* * видео * @TVChannel } *
        | * {клетк* * звук* * @TVChannel } *
        | * {клетк* * изображени* * @TVChannel } *
        | * {клетк* * картинк* * @TVChannel } *
        | * {клетк* * пропорци* * @TVChannel } *
        | * {клетк* * разрешени* * @TVChannel } *
        | * {клетк* * экран* * @TVSet } *
        | * {кубик* * @Television} *
        | * {кубик* * @TVChannel * @TVSet} *
        | * {кубик* * видео * @TVChannel } *
        | * {кубик* * звук* * @TVChannel } *
        | * {кубик* * изображени* * @TVChannel } *
        | * {кубик* * картинк* * @TVChannel } *
        | * {кубик* * пропорци* * @TVChannel } *
        | * {кубик* * разрешени* * @TVChannel } *
        | * {кубик* * экран* * @TVSet} *
        | * {нарушени* * @Television } *
        | * {нарушени* * видео * @Television } *
        | * {нарушени* * видео * @TVSet } *
        | * {нарушени* * звук* * @Television } *
        | * {нарушени* * звук* * @TVSet } *
        | * {нарушени* * изображени* * @Television } *
        | * {нарушени* * изображени* * @TVSet } *
        | * {нарушени* * картинк* * @Television } *
        | * {нарушени* * картинк* * @TVSet } *
        | * {нарушени* * качеств* * @Television } *
        | * {нарушени* * качеств* * @TVSet } *
        | * {нарушени* * пропорци* * @Television } *
        | * {нарушени* * пропорци* * @TVSet } *
        | * {нарушени* * разрешени* * @TVSet } *
        | * {не исправ* * видео * @Television } *
        | * {не исправ* * звук* * @Television } *
        | * {не исправ* * изображени* * @Television } *
        | * {не исправ* * картинк* * @Television } *
        | * {не исправ* * качеств* * @Television } *
        | * {не исправ* * пропорци* * @Television } *
        | * {не исправ* * разрешени* * @Television } *
        | * {не устраивает * качество * @Television} *
        | * {не устраивает * качество * @TVChannel} *
        | * {не устраивает * качество * @TVSet} *
        | * {не устраивает * качество * экран* * @TVSet } *
        | * {недоволен* * @Television } *
        | * {недоволен* * видео * @Television } *
        | * {недоволен* * видео * @TVSet } *
        | * {недоволен* * звук* * @Television } *
        | * {недоволен* * звук* * @TVSet } *
        | * {недоволен* * изображени* * @Television } *
        | * {недоволен* * изображени* * @TVSet } *
        | * {недоволен* * картинк* * @Television } *
        | * {недоволен* * картинк* * @TVSet } *
        | * {недоволен* * качеств* * @Television } *
        | * {недоволен* * качеств* * @TVSet } *
        | * {недоволен* * пропорци* * @Television } *
        | * {недоволен* * пропорци* * @TVSet } *
        | * {недоволен* * разрешени* * @TVSet } *
        | * {недовольств* * @Television } *
        | * {недовольств* * видео * @Television } *
        | * {недовольств* * видео * @TVSet } *
        | * {недовольств* * звук* * @Television } *
        | * {недовольств* * звук* * @TVSet } *
        | * {недовольств* * изображени* * @Television } *
        | * {недовольств* * изображени* * @TVSet } *
        | * {недовольств* * картинк* * @Television } *
        | * {недовольств* * картинк* * @TVSet } *
        | * {недовольств* * качеств* * @Television } *
        | * {недовольств* * качеств* * @TVSet } *
        | * {недовольств* * пропорци* * @Television } *
        | * {недовольств* * пропорци* * @TVSet } *
        | * {недовольств* * разрешени* * @TVSet } *
        | * {неисправ* * видео * @Television } *
        | * {неисправ* * звук* * @Television } *
        | * {неисправ* * изображени* * @Television } *
        | * {неисправ* * картинк* * @Television } *
        | * {неисправ* * качеств* * @Television } *
        | * {неисправ* * пропорци* * @Television } *
        | * {неисправ* * разрешени* * @Television } *
        | * {неполадк* * @Television } *
        | * {неполадк* * видео * @Television } *
        | * {неполадк* * видео * @TVSet } *
        | * {неполадк* * звук* * @Television } *
        | * {неполадк* * звук* * @TVSet } *
        | * {неполадк* * изображени* * @Television } *
        | * {неполадк* * изображени* * @TVSet } *
        | * {неполадк* * картинк* * @Television } *
        | * {неполадк* * картинк* * @TVSet } *
        | * {неполадк* * качеств* * @Television } *
        | * {неполадк* * качеств* * @TVSet } *
        | * {неполадк* * пропорци* * @Television } *
        | * {неполадк* * пропорци* * @TVSet } *
        | * {неполадк* * разрешени* * @TVSet } *
        | * {нет * видео * @TVChannel } *
        | * {нет * звук* * @TVChannel } *
        | * {нет * звук* * @TVSet } *
        | * {нет * изображени* * @TVChannel } *
        | * {нет * картинк* * @TVChannel } *
        | * {нету * видео * @TVChannel } *
        | * {нету * звук* * @TVChannel } *
        | * {нету * звук* * @TVSet } *
        | * {нету * изображени* * @TVChannel } *
        | * {нету * картинк* * @TVChannel } *
        | * {отсутству* * звук* * @TVChannel} *
        | * {отсутству* * звук* * видео} *
        | * {отсутству* * звук* * изображени*} *
        | * {отсутству* * звук* * картинк*} *
        | * {пиксел* * @Television} *
        | * {пиксел* * @TVChannel * @TVSet} *
        | * {пиксел* * видео * @TVChannel } *
        | * {пиксел* * звук* * @TVChannel } *
        | * {пиксел* * изображени* * @TVChannel } *
        | * {пиксел* * картинк* * @TVChannel } *
        | * {пиксел* * пропорци* * @TVChannel } *
        | * {пиксел* * разрешени* * @TVChannel } *
        | * {пиксел* * экран* * @TVSet} *
        | * {подвис* * @Television } *
        | * {подвис* * @TVSet } *
        | * {помехи* * @Television } *
        | * {помехи* * @TVSet } *
        | * {помехи* * экран* * @TVSet } *
        | * {прерывает* * @Television } *
        | * {прерывает* * @TVSet } *
        | * {прерывается * сигнал * @Television } *
        | * {прерывается * сигнал * @TVSet } *
        | * {прерыван* * @Television } *
        | * {прерыван* * @TVSet } *
        | * {претензи* * @Television } *
        | * {претензи* * видео * @Television } *
        | * {претензи* * видео * @TVSet } *
        | * {претензи* * звук* * @Television } *
        | * {претензи* * звук* * @TVSet } *
        | * {претензи* * изображени* * @Television } *
        | * {претензи* * изображени* * @TVSet } *
        | * {претензи* * картинк* * @Television } *
        | * {претензи* * картинк* * @TVSet } *
        | * {претензи* * качеств* * @Television } *
        | * {претензи* * качеств* * @TVSet } *
        | * {претензи* * пропорци* * @Television } *
        | * {претензи* * пропорци* * @TVSet } *
        | * {претензи* * разрешени* * @TVSet } *
        | * {проблем* * @Television } *
        | * {проблем* * видео * @Television } *
        | * {проблем* * видео * @TVSet } *
        | * {проблем* * звук* * @Television } *
        | * {проблем* * звук* * @TVSet } *
        | * {проблем* * изображени* * @Television } *
        | * {проблем* * изображени* * @TVSet } *
        | * {проблем* * картинк* * @Television } *
        | * {проблем* * картинк* * @TVSet } *
        | * {проблем* * качеств* * @Television } *
        | * {проблем* * качеств* * @TVSet } *
        | * {проблем* * пропорци* * @Television } *
        | * {проблем* * пропорци* * @TVSet } *
        | * {проблем* * разрешени* * @TVSet } *
        | * {сложност* * @Television } *
        | * {сложност* * видео * @Television } *
        | * {сложност* * видео * @TVSet } *
        | * {сложност* * звук* * @Television } *
        | * {сложност* * звук* * @TVSet } *
        | * {сложност* * изображени* * @Television } *
        | * {сложност* * изображени* * @TVSet } *
        | * {сложност* * картинк* * @Television } *
        | * {сложност* * картинк* * @TVSet } *
        | * {сложност* * качеств* * @Television } *
        | * {сложност* * качеств* * @TVSet } *
        | * {сложност* * пропорци* * @Television } *
        | * {сложност* * пропорци* * @TVSet } *
        | * {сложност* * разрешени* * @TVSet } *
        | * {стоп кадр* * @Television } *
        | * {стоп кадр* * @TVSet } *
        | * {стоп кадр* * экран* * @TVSet } *
        | * {фигов* качество * @Television} *
        | * {фигов* качество * @TVChannel} *
        | * {фигов* качество * @TVSet} *
        | * {фигов* качество * видео } *
        | * {фигов* качество * звук* } *
        | * {фигов* качество * изображени* } *
        | * {фигов* качество * пропорци* } *
        | * {фигов* качество * разрешени* } *
        | * {фигов* качество * экран* * @TVSet } *
        | * {фигов* показывает * @Television } *
        | * {фигов* показывает * @TVSet } *
        | * {фигов* показывает * TVEquipment } *
        | * {фигов* работает * видео } *
        | * {фигов* работает * звук* } *
        | * {фигов* работает * изображени* } *
        | * {фигов* работает * картинк* } *
        | * {фигов* работает * пропорци* } *
        | * {фигов* работает * разрешени* } *
        | * {завис* * экран* * @TVSet } *
        | * {завис* * @Television } *
        | * {завис* * @TVSet } *
        | * {перебо* * @Television } *
        | * {перебо* * видео * @Television } *
        | * {перебо* * видео * @TVSet } *
        | * {перебо* * звук* * @Television } *
        | * {перебо* * звук* * @TVSet } *
        | * {перебо* * изображени* * @Television } *
        | * {перебо* * изображени* * @TVSet } *
        | * {перебо* * картинк* * @Television } *
        | * {перебо* * картинк* * @TVSet } *
        | * {перебо* * качеств* * @Television } *
        | * {перебо* * качеств* * @TVSet } *
        | * {перебо* * пропорци* * @Television } *
        | * {перебо* * пропорци* * @TVSet } *
        | * {перебо* * разрешени* * @TVSet } * )
        
    $280_LowInetSpeed = (* {уменьшил* * скорост*} *
        | * {снизил* * скорост*} *
        | * {снизил* * @Internet} *
        | * {уменьшил* * @Internet} *
        | * {виснет * @Internet} *
        | * {виснуть * @Internet} *
        | * {завис* * @Internet} *
        | * {еле * ползает * скорост* } *
        | * {еле * ползет * скорост* } *
        | * {еле * работ* * скорост* } *
        | * {едва * ползает * скорост* } *
        | * {едва * ползет * скорост* } *
        | * {едва * работ* * скорост* } *
        | * {еле * ползает * @Internet } *
        | * {еле * ползет * @Internet } *
        | * {еле * работ* * @Internet } *
        | * {едва * ползает * @Internet } *
        | * {едва * ползет * @Internet } *
        | * {едва * работ* * @Internet } *
        | * {не прогружает * скорост*} *
        | * {не прогружается * скорост*} *
        | * {не прогружает * @Internet} *
        | * {не прогружается * @Internet} *
        | * {падени* * скорост*} *
        | * {упал* * скорост*} *
        | * {падает * скорост*} *
        | * {падени* * @Internet} *
        | * {упал* * @Internet} *
        | * {подвисани* * @Internet} *
        | * {подвисл* * @Internet} *
        | * {пропад* * скорост* } *
        | * {пропал* * скорост* } *
        | * {проседает * скорост*} *
        | * {просидает * скорост*} *
        | * {просел* * скорост*} *
        | * {проседает * @Internet} *
        | * {просидает * @Internet} *
        | * {просел* * @Internet} * 
        | * {просад* * @Internet} *
        | * {просад* * скорост*} *
        | * {просадк* * $regexp<име(ется|ются)>} *
        | * {просадк* * есть} *
        | * {плох* * @Internet} *
        | * {плох* * работ* * @Internet} *
        | * {слаб* * работ* * @Internet } *
        | * {слаб* * сесси* * @Internet } *
        | * {слаб* * связ* * @Internet } *
        | * {слаб* * соединени* * @Internet } *
        | * {слаб* * подключени* * @Internet } *
        | * {слаб* * доступ* * @Internet } *
        | * {слаб* * работ* * @InternetEquipment } *
        | * {слаб* * сесси* * @InternetEquipment } *
        | * {слаб* * связ* * @InternetEquipment } *
        | * {слаб* * соединени* * @InternetEquipment } *
        | * {слаб* * подключени* * @InternetEquipment } *
        | * {слаб* * доступ* * @InternetEquipment } *
        | * {огранич* * скорост* } *
        | * {огранич* * @Internet } *
        | * {уменьш* * скорост* } *
        | * {уменьш* * @Internet } *
        | * {не тянет * @Internet } *
        | * {медленн* * скорост* } *
        | * {задержк* * @Internet } *
        | * {задержк* * скорост* } *
        | * {добав* * скорост* } *
        | * {висит * @Internet } * 
        | * {проблем* * скорост* } * )
        
    $290_TVChannelProblem = ( * {не включ* * @TVSet} *
        | * {(что/что то/что-то) * случил* * @Television } *
        | * {(что/что то/что-то) * случил* * @TVChannel } *
        | * {(что/что то/что-то) * случил* * @TVSet } *
        | * {[код] ошибк* * @TVErrorCodes} *
        | * {код [ошибк*] * @TVErrorCodes} *
        | * {[куда/куда то/куда-то] * делась * @Television} *
        | * {[куда/куда то/куда-то] * делись * @Television} *
        | * {[куда/куда то/куда-то] * делось * @Television} *
        | * {[куда/куда то/куда-то] * делся * @Television} *
        | * {@TVChannel * не играет} *
        | * {@TVChannel * не играют} *
        | * {@TVChannel * не появил} *
        | * {@TVChannel * не появит} *
        | * {@TVChannel * не появля} *
        | * {@TVChannelName * пропаж* } *
        | * {@TVChannelName * пропал* } *
        | * {@TVSet * не запуска*} *
        | * {@TVSet * не идет} *
        | * {@TVSet * не идти} *
        | * {@TVSet * не идут} *
        | * {*кодирован* * @Television } *
        | * {*рвал* * @Television } *
        | * {выключен* * @TVChannel} *
        | * {через сколько * @Television * включа* } *
        | * {через сколько * @Television * восстанов*} *
        | * {через сколько * @Television * заработ*} *
        | * {через сколько * @Television * подключит*} *
        | * {через сколько * @Television * починит*} *
        | * {через сколько * @Television * работ*} *
        | * {через сколько * @TVChannel * включа* } *
        | * {через сколько * @TVSet * восстанов*} *
        | * {через сколько * @TVSet * заработ*} *
        | * {через сколько * @TVSet * подключит*} *
        | * {через сколько * @TVSet * починит*} *
        | * {через сколько * @TVSet * работ*} *
        | * {через сколько * антенн* * включа* } *
        | * {через сколько * видео * включа* } *
        | * {через сколько * картинк* * включа* } *
        | * {через сколько * сигнал* * включа* } *
        | * {когда * @Television * включа* } *
        | * {когда * @Television * восстанов*} *
        | * {когда * @Television * заработ*} *
        | * {когда * @Television * подключит*} *
        | * {когда * @Television * починит*} *
        | * {когда * @Television * работ*} *
        | * {когда * @TVChannel * включа* } *
        | * {когда * @TVSet * восстанов*} *
        | * {когда * @TVSet * заработ*} *
        | * {когда * @TVSet * подключит*} *
        | * {когда * @TVSet * починит*} *
        | * {когда * @TVSet * работ*} *
        | * {когда * антенн* * включа* } *
        | * {когда * видео * включа* } *
        | * {когда * картинк* * включа* } *
        | * {когда * сигнал* * включа* } *
        | * {надпис* * на * @TVSet * ошибк* } *
        | * {написан* * на * @TVSet * ошибк* } *
        | * {не @Successful * подключ* * @Television } *
        | * {не включ* * @Television} *
        | * {не включ* * @TVChannel} *
        | * {не включ* * @TVSet} *
        | * {не запуска* * @Television} *
        | * {не запуска* * @TVChannel} *
        | * {не заработ* * @Television} *
        | * {не заработ* * @TVChannel} *
        | * {не заработ* * @TVSet} *
        | * {не идет * @Television} *
        | * {не идет * @TVChannel} *
        | * {не идти * @Television} *
        | * {не идти * @TVChannel} *
        | * {не идут * @Television} *
        | * {не идут * @TVChannel} *
        | * {не отображ* * @Television} *
        | * {не отображ* * @TVChannel} *
        | * {не отображ* * @TVSet} *
        | * {не показыва* * @Television} *
        | * {не показыва* * @TVChannel} *
        | * {не показыва* * @TVSet} *
        | * {не показывает ты} *
        | * {не работа* * @Television} *
        | * {не работа* * @TVChannel} *
        | * {не работа* * @TVSet} *
        | * {нет подключени* * @Television } *
        | * {нет* * антенн* * @TVSet} *
        | * {нет* * вещани* * @Television} *
        | * {нет* * видео * @TVSet} *
        | * {нет* * изображени* * @TVSet} *
        | * {нет* * картинк* * @TVSet} *
        | * {нет* * приём* * @Television} *
        | * {нет* * просмотр* * @Television} *
        | * {нет* * работ* * @Television} *
        | * {нет* * сигнал* * @TVSet} *
        | * {нет* * трансляци* * @Television} *
        | * {обруб* * @Television} *
        | * {обруб* * @TVSet} *
        | * {отключил* * @Television} *
        | * {отключил* * @TVSet} *
        | * {отключил* * вещани* * @Television } *
        | * {отключил* * вещани* * @Television} *
        | * {отключил* * прием* * @Television } *
        | * {отключил* * прием* * @Television} *
        | * {отключил* * просмотр* * @Television } *
        | * {отключил* * просмотр* * @Television} *
        | * {отключил* * работ* * @Television } *
        | * {отключил* * работ* * @Television} *
        | * {отключил* * трансляци* * @Television } *
        | * {отключил* * трансляци* * @Television} *
        | * {отруб* * @Television} *
        | * {отруб* * @TVSet} *
        | * {отсутстви* * @Television} *
        | * {отсутство* * @Television} *
        | * {отсутству* * @Television} *
        | * {отсутствует * антенн* * @TVSet} *
        | * {отсутствует * видео * @TVSet} *
        | * {отсутствует * изображени* * @TVSet} *
        | * {отсутствует * картинк* * @TVSet} *
        | * {отсутствует * сигнал* * @TVSet} *
        | * {ошибк* * @TVSet } *
        | * {ошибк* * включ* * @TVChannel } *
        | * {перестал* * показыва* * @Television} *
        | * {перестал* * показыва* * @TVChannel} *
        | * {перестал* * показыва* * @TVSet} *
        | * {почему * отключил* * (@TVSet/@Television) } *
        | * {пропад* * @TVChannel} *
        | * {пропада* * @Television} *
        | * {пропаж* * @Television} *
        | * {пропал* * @Television} *
        | * {пропал* * @TVChannel} *
        | * {пропал* * @TVSet} *
        | * {профилактик* * @TVChannel * @TVSet } *
        | * {сбои * обычн* * @TVChannel} *
        | * {сбои * стандартн* * @TVChannel} *
        | * {сбои * федеральн* * @TVChannel} *
        | * {сбои * центральн* * @TVChannel} *
        | * {сбой * обычн* * @TVChannel} *
        | * {сбой * стандартн* * @TVChannel} *
        | * {сбой * федеральн* * @TVChannel} *
        | * {сбой * центральн* * @TVChannel} *
        | * {сдела* работ* * @TVSet} *
        | * {сдела* работающ* * @TVSet} *
        | * {сдела* рабоч* * @TVSet} * 
        | * {[@Debt] * оплатил*} * {@Television * (не подключ*)}
        | * {[@Debt] * оплатил*} * {@TVSet * (не подключ*)}
        | * {[@Debt] * оплатил*} * {@TVChannel * (не подключ*)} )
        
    $300_NoLink6xxIntent = ( * {нет * @Internet} *
        | * { (нет/нету) * подключени* * @Internet } *
        | * { (нет/нету) * связ* * @Internet } *
        | * { (нет/нету) * сет* * @Internet } *
        | * { (нет/нету) * соединени* * @Internet } *
        | * { (нет/нету) трафик*} *
        | * { не дает* * соединени* } *
        | * { не дает* * трафик* } *
        | * { не дает* * cвяз* } *
        | * { не подключает* * @Internet } *
        | * { не подключает* * связ* } *
        | * { не подключает* * сесси* } *
        | * { не подключает* * соединени* } *
        | * { не подключает* * трафик* } *
        | * { не предоставляет* * связ* } *
        | * { не предоставляет* * соединени* } *
        | * { не предоставляет* * трафик* } *
        | * { не работа* * @WiFi } *
        | * { починит* * работ* * @Internet } *
        | * { починят * работ* * @Internet } *
        | * { чинит* * работ* * @Internet } *
        | * { актив* * @Internet } *
        | * { неактив* * @Internet } *
        | * { ниактив* * @Internet } *
        | * { ни актив* * @Internet } *
        | * { нет актив* * @Internet } *
        | * { не * актив* * @Internet } *
        | * {*ладят * работ* * @Internet} *
        | * {*ладят * работ* * @WiFi} *
        | * {актив* * @WiFi} *
        | * {актив* * подключени* * @WiFi} *
        | * {актив* * работ* * @WiFi} *
        | * {актив* * связ* * @WiFi} *
        | * {актив* * соединени* * @WiFi} *
        | * {включит* * работ* * @Internet} *
        | * {включит* * работ* * @WiFi} *
        | * {возобнов* * работ* * @Internet} *
        | * {возобнов* * работ* * @WiFi} *
        | * {заблокирова* * @Internet } *
        | * {заблокирова* * @Internet} *
        | * {заблокирова* * подключени* * @Internet} *
        | * {заблокирова* * подключени* * @WiFi} *
        | * {заблокирова* * связ* * @Internet} *
        | * {заблокирова* * связ* * @WiFi} *
        | * {заблокирова* * соединени* * @Internet} *
        | * {заблокирова* * соединени* * @WiFi} *
        | * {заглох* * @Internet} *
        | * {заглох* * подключени* * @Internet} *
        | * {заглох* * подключени* * @WiFi} *
        | * {заглох* * связ* * @Internet} *
        | * {заглох* * связ* * @WiFi} *
        | * {заглох* * соединени* * @Internet} *
        | * {заглох* * соединени* * @WiFi} *
        | * {заглуши* * @Internet } *
        | * {заглуши* * подключени* * @Internet} *
        | * {заглуши* * подключени* * @WiFi} *
        | * {заглуши* * связ* * @Internet} *
        | * {заглуши* * связ* * @WiFi} *
        | * {заглуши* * соединени* * @Internet} *
        | * {заглуши* * соединени* * @WiFi} *
        | * {когда * *ладят * @Internet} *
        | * {когда * будет * @Internet} *
        | * {когда * включит* * @Internet} *
        | * {когда * возобновит* * @Internet} *
        | * {когда * возобновлять * @Internet} *
        | * {когда * заработа* * @Internet} *
        | * {когда * работа* * @Internet} *
        | * {когда * починит* * @Internet} *
        | * {когда * починят * @Internet} *
        | * {когда * сдела* * @Internet} *
        | * {через сколько * *ладят * @Internet} *
        | * {через сколько * будет * @Internet} *
        | * {через сколько * включит* * @Internet} *
        | * {через сколько * возобновит* * @Internet} *
        | * {через сколько * возобновлять * @Internet} *
        | * {через сколько * заработа* * @Internet} *
        | * {через сколько * работа* * @Internet} *
        | * {через сколько * починит* * @Internet} *
        | * {через сколько * починят * @Internet} *
        | * {через сколько * сдела* * @Internet} *
        | * {когда * *ладят * @WiFi} *
        | * {когда * будет * @WiFi} *
        | * {когда * включит* * @WiFi} *
        | * {когда * возобновит* * @WiFi} *
        | * {когда * возобновлять * @WiFi} *
        | * {когда * заработа* * [@WiFi]} *
        | * {когда * работа* * @WiFi} *
        | * {когда * починит* * @WiFi} *
        | * {когда * починят * @WiFi} *
        | * {когда * сдела* * @WiFi} *
        | * {через сколько * *ладят * @WiFi} *
        | * {через сколько * будет * @WiFi} *
        | * {через сколько * включит* * @WiFi} *
        | * {через сколько * возобновит* * @WiFi} *
        | * {через сколько * возобновлять * @WiFi} *
        | * {через сколько * заработа* * [@WiFi]} *
        | * {через сколько * работа* * @WiFi} *
        | * {через сколько * починит* * @WiFi} *
        | * {через сколько * починят * @WiFi} *
        | * {через сколько * сдела* * @WiFi} *
        | * {код * @InternetErrorCodes} *
        | * {не @Successful * установить * @Internet} *
        | * {не @Successful * установить * подключени*} *
        | * {не @Successful * установить * соединени*} *
        | * {не @Successful * установить * @WiFi} *
        | * {не @Successful * $regexp<подключи(ть|ться)> * @Internet} *
        | * {не @Successful * $regexp<подключи(ть|ться)> * подключени*} *
        | * {не @Successful * $regexp<подключи(ть|ться)> * соединени*} *
        | * {не @Successful * $regexp<подключи(ть|ться)> * @WiFi} *
        | * {не включает* * @Internet} *
        | * {не включает* * @WiFi} *
        | * {не включает* * связ*} *
        | * {не включает* * сесси*} *
        | * {не включает* * соединени*} *
        | * {не включает* * трафик*} *
        | * {не заработ* * @Internet} *
        | * {не подключает* * @Internet} *
        | * {не подключает* * связ*} *
        | * {не подключает* * сесси*} *
        | * {не подключает* * соединени*} *
        | * {не подсоединяет* * @Internet} *
        | * {не подсоединяет* * @WiFi} *
        | * {не получ* * установить * @Internet} *
        | * {не получ* * установить * подключени*} *
        | * {не получ* * установить * соединени*} *
        | * {не получ* * установить * @WiFi} *
        | * {не предоставляет* * @Internet} *
        | * {не предоставляет* * @WiFi} *
        | * {не работа* * @Internet} *
        | * {не работа* * @WiFi} *
        | * {не рабоч* * @Internet} *
        | * {не рабоч* * @WiFi} *
        | * {не соединяет* * @Internet} *
        | * {не соединяет* * @WiFi} *
        | * {не удается * установить * @Internet} *
        | * {не удается * установить * подключени*} *
        | * {не удается * установить * соединени*} *
        | * {не удается * установить * @WiFi} *
        | * {не удалось * установить * @Internet} *
        | * {не удалось * установить * подключени*} *
        | * {не удалось * установить * соединени*} *
        | * {не удалось * установить * @WiFi} *
        | * {не устанавлив* * @Internet} *
        | * {не устанавлив* * подключени* } *
        | * {не устанавлив* * связ*} *
        | * {не устанавлив* * соединени*} *
        | * {не устанавлив* * трафик*} *
        | * {нерабоч* * @Internet} *
        | * {нерабоч* * @WiFi} *
        | * {нет @Internet * @Device} *
        | * {нет @Internet * адрес*} *
        | * {нет @Internet * дом*} *
        | * {нет @Internet * квартир*} *
        | * {нет @Internet * комнат*} *
        | * {нет @Internet * парадн*} *
        | * {нет @Internet * подъезд*} *
        | * {нет @Internet * этаж*} *
        | * {нет * @WiFi} *
        | * {нет* * подключени*} *
        | * {нет* * связ*} *
        | * {нет* * соединени*} *
        | * {нету @Internet * @Device} *
        | * {нету @Internet * адрес*} *
        | * {нету @Internet * дом*} *
        | * {нету @Internet * квартир*} *
        | * {нету @Internet * комнат*} *
        | * {нету @Internet * парадн*} *
        | * {нету @Internet * подъезд*} *
        | * {нету @Internet * этаж*} *
        | * {нету * @Internet} *
        | * {нету * @WiFi} *
        | * {обруби* * @Internet } *
        | * {обруби* * @WiFi} *
        | * {обруби* * подключени* * @Internet} *
        | * {обруби* * подключени* * @WiFi} *
        | * {обруби* * связ* * @Internet} *
        | * {обруби* * связ* * @WiFi} *
        | * {обруби* * соединени* * @Internet} *
        | * {обруби* * соединени* * @WiFi} *
        | * {отруби* * @Internet } *
        | * {отруби* * @WiFi} *
        | * {отруби* * подключени* * @Internet} *
        | * {отруби* * подключени* * @WiFi} *
        | * {отруби* * связ* * @Internet} *
        | * {отруби* * связ* * @WiFi} *
        | * {отруби* * соединени* * @Internet} *
        | * {отруби* * соединени* * @WiFi} *
        | * {обнаруж* * сбои * @Internet} *
        | * {обнаруж* * сбои * @WiFi} *
        | * {ошиб* * @InternetErrorCodes} *
        | * {подключени* * не актив*} *
        | * {пропа* * @Internet } *
        | * {пропа* * @WiFi} *
        | * {пропа* * подключени* * @Internet} *
        | * {пропа* * подключени* * @WiFi} *
        | * {пропа* * связ* * @Internet} *
        | * {пропа* * соединени* * @Internet} *
        | * {пропа* * соединени* * @WiFi} *
        | * {связ* * не актив*} *
        | * {соединени* * не актив*} *
        | * {трафик* * не актив*} *
        | * {cдела* * работ* * @Internet} *
        | * {cдела* * работ* * @WiFi} *
        | * {$regexp<сбо[ий]> * @WiFi} *
        | * {$regexp<сбо[ий]> * @Internet} *
        | * {$regexp<сбо[ий]> * подключени*} *
        | * {$regexp<сбо[ий]> * соединени*} *
        | * {отключ* * свет*} * {включ* * @Equipment} *
        | * {отсутств* * @Internet} *
        | * {отсутств* * подключени*} *
        | * {отсутств* * соединени*} *
        | * {отсутств* * @WiFi} *
        | * {*платил* * где @Internet } *
        | * {*платил* * где @WiFi } *
        | * {*платил* * нет * @Internet } *
        | * {*платил* * нет * @WiFi } *
        | * {*платил* * @Internet * не появил* } *
        | * {*платил* * @WiFi * не появил* } *
        | * {*платил* * @Internet * не *работ* } *
        | * {*платил* * @WiFi * не *работ* } *
        | * {@Internet * долж* * заработать } *
        | * {@WiFi * долж* * заработать } *
        | * {подключени* * долж* * заработать } *
        | * {соединени* * долж* * заработать } *
        | * {связь * долж* * заработать } *
        | * {@Internet не восстановил*} *
        | * {@Internet не появил*} *
        | * {@WiFi не восстановил*} *
        | * {@WiFi не появил*} * 
        | * {@Internet не отображ*} *
        | * {@WiFi не отображ*} * 
        | * {срок* * устранен* * (проблем*/авари*)} *  )
        
    $310_ConnectProblem = ( * { @WiFi * то есть то нет } *
        | * { @Internet * то есть то нет } *
        | * { сесси* * то есть то нет } *
        | * { связ* * то есть то нет } *
        | * { (сеть/сети) * то есть то нет } *
        | * { соединени* * то есть то нет } *
        | * { подключени* * то есть то нет } *
        | * { @WiFi * то работа* то нет } *
        | * { @Internet * то работа* то нет } *
        | * { сесси* * то работа* то нет } *
        | * { связ* * то работа* то нет } *
        | * { (сеть/сети) * то работа* то нет } *
        | * { соединени* * то работа* то нет } *
        | * { подключени* * то работа* то нет } *
        | * { перебо* * соединени* * @InternetEquipment } *
        | * { перебо* * подключен* * @InternetEquipment } *
        | * { перебо* * связ* * @InternetEquipment } *
        | * { перебо* * сигнал* * @InternetEquipment } *
        | * { выбрасыва* * @WiFi } *
        | * { выбрасыва* * @Internet } *
        | * { выбрасыва* * сесси* } *
        | * { выбрасыва* * связ* } *
        | * { выбрасыва* * (сеть/сети) } *
        | * { выбрасыва* * соединени* } *
        | * { выбрасыва* * подключени* } *
        | * { выкидыва* * @WiFi } *
        | * { выкидыва* * @Internet } *
        | * { выкидыва* * сесси* } *
        | * { выкидыва* * связ* } *
        | * { выкидыва* * (сеть/сети) } *
        | * { выкидыва* * соединени* } *
        | * { выкидыва* * подключени* } *
        | * { вылета* * @WiFi } *
        | * { вылета* * @Internet } *
        | * { вылета* * сесси* } *
        | * { вылета* * связ* } *
        | * { вылета* * (сеть/сети) } *
        | * { вылета* * соединени* } *
        | * { вылета* * подключени* } *
        | * { нестабильн* * соединени* * @InternetEquipment } *
        | * { нестабильн* * подключен* * @InternetEquipment } *
        | * { нестабильн* * связ* * @InternetEquipment } *
        | * { нестабильн* * сигнал* * @InternetEquipment } *
        | * { нестабильн* * [соединени*] * @WiFi } *
        | * { нестабильн* * [соединени*] * @Internet } *
        | * { нестабильн* * [соединени*] * сесси* } *
        | * { нестабильн* * [соединени*] * связ* } *
        | * { нестабильн* * [соединени*] * сеть } *
        | * { нестабильн* * [соединени*] * сети } *
        | * { нестабильн* * [подключен*] * @WiFi } *
        | * { нестабильн* * [подключен*] * @Internet } *
        | * { нестабильн* * [подключен*] * сесси* } *
        | * { нестабильн* * [подключен*] * связ* } *
        | * { нестабильн* * [подключен*] * сеть } *
        | * { нестабильн* * [подключен*] * сети } *
        | * { нестабильн* * [связ*] * @WiFi } *
        | * { нестабильн* * [связ*] * @Internet } *
        | * { нестабильн* * [связ*] * сесси* } *
        | * { нестабильн* * [связ*] * сеть } *
        | * { нестабильн* * [связ*] * сети } *
        | * { постоянно * отключается * соединени* } *
        | * { постоянно * отключается * подключен* } *
        | * { постоянно * отключается * связ* } *
        | * { постоянно * отключается * @WiFi } *
        | * { постоянно * отключается * @Internet } *
        | * { постоянно * отключается * сесси* } *
        | * { постоянно * отключается * сеть } *
        | * { постоянно * отключается * сети } *
        | * { постоянно * отключается * @InternetEquipment } *
        | * { потерян* * соединени* * @Internet} *
        | * { потерян* * подключен* * @Internet} *
        | * { потерян* * связ* * @Internet} *
        | * { потерян* * соединени* * @InternetEquipment} *
        | * { потерян* * подключен* * @InternetEquipment} *
        | * { потерян* * связ* * @InternetEquipment} *
        | * { пропада* * @WiFi } *
        | * { пропада* * @Internet } *
        | * { пропада* * cесси* } *
        | * { пропада* * связ* } *
        | * { пропада* * (сеть/сети) } *
        | * { пропада* * соединени* } *
        | * { пропада* * соединени* * @InternetEquipment } *
        | * { пропада* * подключен* * @InternetEquipment } *
        | * { пропада* * связ* * @InternetEquipment } *
        | * { пропада* * сигнал* * @InternetEquipment } *
        | * { сбрасывает* * @WiFi } *
        | * { сбрасывает* * @Internet } *
        | * { сбрасывает* * сесси* } *
        | * { сбрасывает* * связ* } *
        | * { сбрасывает* * (сеть/сети) } *
        | * { сбрасывает* * соединени* } *
        | * { сбрасывает* * подключени* } *
        | * { теряется * @WiFi } *
        | * { теряется * @Internet } *
        | * { теряется * сесси* } *
        | * { теряется * связ* } *
        | * { теряется * (сеть/сети) } *
        | * { теряется * соединени* } *
        | * { теряется * подключени* } *
        | * { теряется * соединени* * @InternetEquipment } *
        | * { теряется * подключен* * @InternetEquipment } *
        | * { теряется * связ* * @InternetEquipment } *
        | * { теряется * сигнал* * @InternetEquipment } *
        | * { рвется * @WiFi } *
        | * { рвется * @Internet } *
        | * { рвется * сесси* } *
        | * { рвется * (сеть/сети) } *
        | * { рвется * соединени* } *
        | * { рвется * подключен* } *
        | * { рвется * связ* } *
        | * { разрыв* * @WiFi } *
        | * { разрыв* * @Internet } *
        | * { разрыв* * сесси* } *
        | * { разрыв* * (сеть/сети) } *
        | * { разрыв* * соединени* } *
        | * { разрыв* * подключен* } *
        | * { разрыв* * связ* } *
        | * { потерян* * @WiFi } *
        | * { потерян* * @Internet } *
        | * { потерян* * cесси* } *
        | * { потерян* * связ* } *
        | * { потерян* * (сеть/сети) } *
        | * { потерян* * соединени* } *
        | * { потерян* * подключение* } *
        | * { прерывает* * @WiFi } *
        | * { прерывает* * @Internet } *
        | * { прерывает* * сесси* } *
        | * { прерывает* * (сеть/сети) } *
        | * { прерывает* * соединени* } *
        | * { прерывает* * подключени* } *
        | * { прерван* * @WiFi } *
        | * { прерван* * @Internet } *
        | * { прерван* * сесси* } *
        | * { прерван* * (сеть/сети) } *
        | * { прерван* * соединени* } *
        | * { прерван* * подключени* } *)
        
    $330_BonusesOffers = ( * { акци* * @PersonalAccount} *
        | * { бонус* * @PersonalAccount } *
        | * { балл* * @PersonalAccount } *
        | * { льгот* * @PersonalAccount } *
        | * { акци* * @SubscriptionFee} *
        | * { бонус* * @SubscriptionFee} *
        | * { балл* * @SubscriptionFee} *
        | * { льгот* * @SubscriptionFee} *
        | * { *блокиров* * акци* } *
        | * { *блокиров* * балл* } *
        | * { *блокиров* * бонус* } *
        | * { *блокиров* * льгот* } *
        | * { *знать * акци* } *
        | * { *знать * балл* } *
        | * { *знать * бонус* } *
        | * { *знать * льгот* } *
        | * { *обнов* * акци* } *
        | * { *обнов* * балл* } *
        | * { *обнов* * бонус* } *
        | * { *обнов* * льгот* } *
        | * { *регистр* * программ* лояльност*} *
        | * { актив* * акци* } *
        | * { актив* * балл* } *
        | * { актив* * бонус* } *
        | * { актив* * льгот* } *
        | * { акци* * @Beneficiary } *
        | * { акци* * @DomRu } *
        | * { акци* * @Services } *
        | * { акци* * @SynonymsForAgreement } *
        | * { акци* * @Telephone } *
        | * { акци* * @Television } *
        | * { акци* * кешбэк } *
        | * { балл* * @Beneficiary } *
        | * { балл* * @DomRu } *
        | * { балл* * @Services } *
        | * { балл* * @SynonymsForAgreement } *
        | * { балл* * @Telephone } *
        | * { балл* * @Television } *
        | * { балл* * кешбэк } *
        | * { бонус* * @Beneficiary } *
        | * { бонус* * @DomRu } *
        | * { бонус* * @Services } *
        | * { бонус* * @SynonymsForAgreement } *
        | * { бонус* * @Telephone } *
        | * { бонус* * @Television } *
        | * { бонус* * кешбэк } *
        | * { бонус* благодарност* } *
        | * { бонус* лояльност*} *
        | * { бонус* льгот*} *
        | * { бонус* программ*} *
        | * { бонусн* баланс* } *
        | * { вариант* * акци* } *
        | * { выключ* * акци* } *
        | * { льгот* * @Beneficiary } *
        | * { льгот* * @DomRu } *
        | * { льгот* * @Services } *
        | * { льгот* * @SynonymsForAgreement } *
        | * { льгот* * @Telephone } *
        | * { льгот* * @Television } *
        | * { льгот* * кешбэк } *
        | * { не *блокиров* * акци* } *
        | * { не *блокиров* * балл* } *
        | * { не *блокиров* * бонус* } *
        | * { не *блокиров* * льгот* } *
        | * { не актив* * акци* } *
        | * { не актив* * балл* } *
        | * { не актив* * бонус* } *
        | * { не актив* * льгот* } *
        | * { не выключ* * акци* } *
        | * { не выключ* * балл* } *
        | * { не выключ* * бонус* } *
        | * { не выключ* * льгот* } *
        | * { не отключ* * акци* } *
        | * { не отключ* * балл* } *
        | * { не отключ* * бонус* } *
        | * { не подключ* * акци* } *
        | * { не подключ* * балл* } *
        | * { не подключ* * бонус* } *
        | * { не подключ* * льгот* } *
        | * { не работ* * акци* } *
        | * { не работ* * балл* } *
        | * { не работ* * бонус* } *
        | * { не работ* * льгот* } *
        | * { не удале* * акци* } *
        | * { не удале* * балл* } *
        | * { не удале* * бонус* } *
        | * { не удале* * льгот* } *
        | * { не удали* * акци* } *
        | * { не удали* * балл* } *
        | * { не удали* * бонус* } *
        | * { не удали* * льгот* } *
        | * { отключ* * акци* } *
        | * { отключ* * балл* } *
        | * { отключ* * бонус* } *
        | * { отключ* * доп услуг* } *
        | * { отключ* * дополнительн* услуг* } *
        | * { подключ* * акци* } *
        | * { подключ* * балл* } *
        | * { подключ* * бонус* } *
        | * { подключ* * льгот* } *
        | * { получ* * акци* } *
        | * { получ* * балл* } *
        | * { получ* * бонус* } *
        | * { получ* * льгот* } *
        | * { потратить * балл* } *
        | * { потратить * бонус* } *
        | * { прекратит* * акци* } *
        | * { приветственн* бонус* } *
        | * { приостан* * акци* } *
        | * { программ* лояльност*} *
        | * { программ* привилег*} *
        | * { продле* * акци* } *
        | * { продле* * балл* } *
        | * { продле* * бонус* } *
        | * { продле* * льгот* } *
        | * { продли* * акци* } *
        | * { продли* * балл* } *
        | * { продли* * бонус* } *
        | * { продли* * льгот* } *
        | * { пролонг* * акци* } *
        | * { пролонг* * балл* } *
        | * { пролонг* * бонус* } *
        | * { пролонг* * льгот* } *
        | * { рекламн* * акци* } *
        | * { рекламн* * балл* } *
        | * { рекламн* * бонус* } *
        | * { сколько * балл* * @AllServices } *
        | * { сколько * бонус* * @AllServices } *
        | * { убери* * акци* } *
        | * { убери* * балл* } *
        | * { убери* * бонус* } *
        | * { убери* * льгот* } *
        | * { убра* * акци* } *
        | * { убра* * балл* } *
        | * { убра* * бонус* } *
        | * { убра* * льгот* } *
        | * { удале* * акци* } *
        | * { удале* * балл* } *
        | * { удале* * бонус* } *
        | * { удале* * льгот* } *
        | * { удали* * акци* } *
        | * { удали* * балл* } *
        | * { удали* * бонус* } *
        | * { удали* * льгот* } *
        | * { управл* * акци* } *
        | * { управл* * балл* } *
        | * { управл* * бонус* } *
        | * { управл* * льгот* } *
        | * { уточн* * акци* } *
        | * { уточн* * балл* } *
        | * { уточн* * бонус* } *
        | * { уточн* * льгот* } *
        | * Sorry-бонус* * 
        | * { подключ* * программ* лояльност* } *
        | * { не подключ*  * программ* лояльност* } *
        | * { подключ* * программ* привилег*} *
        | * { не подключ*  * программ* привилег*} *
        | * { подключ* * программ* бонус*} *
        | * { не подключ*  * программ* бонус*} * 
        | * { будет * компенсац* } *
        | * { будет * перерасчет* } *  
        | * { @Discount * @PersonalAccount } * 
        | * { @Discount * акци* } * 
        | * { @Discount * балл* } *
        | * { @Discount * бонус* } *
        | * { @Discount * льгот* } *
        | * { @Discount * программ* лояльност* } *
        | * { @Discount * @Beneficiary } *
        | * { @Discount * @DomRu } * 
        | * { @Discount * @Services } * 
        | * { @Discount * @SynonymsForAgreement } * 
        | * { @Discount * @Telephone } * 
        | * { @Discount * @Television } * 
        | * { @Discount * кешбэк } *
        | * { @Discount * компенсац* } *
        | * { @Discount * перерасчет* } * 
        | * { @Discount * @SubscriptionFee } * )    
   
    $wholeCheaper = ( * { @Discount * [за/на] двенадцать месяцев } *
        | * { акци* * [за/на] двенадцать месяцев } *
        | * { @Payment * [за/на] двенадцать месяцев } *
        | * { @Discount * [за/на] три месяца } *
        | * { акци* * [за/на] три месяца } *
        | * { @Payment * [за/на] три месяца } 
        | * { @Discount * [за/на] полгода } * 
        | * { акци* * [за/на] полгода } * 
        | * { @Payment * [за/на] полгода } * 
        | * { @Discount * [за/на] шесть месяцев } * 
        | * { акци* * [за/на] шесть месяцев } * 
        | * { @Payment * [за/на] шесть месяцев } * 
        | * @Payment * [за/на] год * вперед *
        | * @Payment * [за/на] полгода * вперед *
        | * { *знать * оптом дешевле } *
        | * { *знай* * оптом дешевле } *
        | * { *знавать * оптом дешевле } *
        | * { будет *  оптом дешевле } *
        | * { будет * оптом дешевле } *
        | * { был* * оптом дешевле } *
        | * { вопрос* * оптом дешевле } *
        | * { консультаци* * оптом дешевле } *
        | * { информаци* * оптом дешевле } *
        | * { какие * оптом дешевле } *
        | * { какая * оптом дешевле } *
        | * { какую * оптом дешевле } *
        | * { какой * оптом дешевле } *
        | * { подтверд* * оптом дешевле } *
        | * { подтверж* * оптом дешевле } *
        | * { *делайте * оптом дешевле } *
        | * { *делать * оптом дешевле } *
        | * { *платить * заранее * [за/на] двенадцать месяцев } *
        | * { *платить * заранее * [за/на] три месяца} *
        | * { *платить * заранее * [за/на] полгода } *
        | * { *платить * заранее * [за/на] шесть месяцев } *
        | * { *платить * заблаговременно * [за/на] двенадцать месяцев } *
        | * { *платить * заблаговременно * [за/на] три месяца} *
        | * { *платить * заблаговременно * [за/на] полгода } *
        | * { *платить * заблаговременно * [за/на] шесть месяцев } *
        | * { *платить * наперед * [за/на] двенадцать месяцев } *
        | * { *платить * наперед * [за/на] три месяца} *
        | * { *платить * наперед * [за/на] полгода } *
        | * { *платить * наперед * [за/на] шесть месяцев } *
        | * { *платить * предварительно * [за/на] двенадцать месяцев } *
        | * { *платить * предварительно * [за/на] три месяца} *
        | * { *платить * предварительно * [за/на] полгода } *
        | * { *платить * предварительно * [за/на] шесть месяцев } *
        | * { *платить * сразу * [за/на] двенадцать месяцев } *
        | * { *платить * сразу * [за/на] три месяца} *
        | * { *платить * сразу * [за/на] полгода } *
        | * { *платить * сразу * [за/на] шесть месяцев } *
        | * { @Modal * оптом дешевле } *
        | * { оптом дешевле * @Beneficiary } *
        | * { оптом дешевле * @Services } *
        | * { оптом дешевле } *
        | * { актив* * оптом дешевле } *
        | * { есть * оптом дешевле } *
        | * { заверши* * оптом дешевле } *
        | * { закончи* * оптом дешевле } *
        | * { закры* * оптом дешевле } *
        | * { оптом дешевле * счет* } *
        | * { оптом дешевле * @Payment } *
        | * { оптом дешевле * @duckling.number процент* } *
        | * { подключ* * оптом дешевле } *
        | * { получит* * оптом дешевле } *
        | * { продле* * оптом дешевле } *
        | * { продли* * оптом дешевле } *
        | * { пролонг* * оптом дешевле } *
        | * { существу* * оптом дешевле } *
        | * { опц* оптом дешевле * }
        | * { абонемент* * [за/на] двенадцать месяцев } *
        | * { абонемент* * [за/на] три месяца } *
        | * { абонемент* * [за/на] шесть месяцев } *
        | * { актив* * абонемент* * [за/на] двенадцать месяцев } *
        | * { актив* * абонемент* * [за/на] три месяца } *
        | * { актив* * абонемент* * [за/на] шесть месяцев } *
        | * { абонемент* * [за/на] полгода } * )
        
    $340_PotentialClientService = ( * { заявк* * на * подключен* * [@AllServices] } *
        | { @Need услуг* }
        | * { стать * $client  * ваш* @Company} *
        | * { поддерживает* * @DomRu  * [по/в] * адрес*} *
        | * { поддерживает* * @DomRu  * [по/в] * дом*} *
        | * { поддерживает* * @DomRu  * [по/в] * квартир*} *
        | * { поддерживает* * @DomRu  * [по/в] * комнат*} *
        | * { поддерживает* * @DomRu  * [по/в] * этаж*} *
        | * { поддерживает* * @DomRu  * [по/в] * подъезд*} *
        | * { поддерживает* * @DomRu  * [по/в] * парадн*} *
        | * { подключен* * нов* * $client } *
        | * { открыть * нов* * @SynonymsForAgreement } *
        | * { открыть * нов* * услуг* } *
        | * { оформ* * ещё * @SynonymsForAgreement } *
        | * { оформ* * ещё * услуг* } *
        | * { подключить* * @SynonymsForAgreement } *
        | * { рассматрив* * ваш* @Company } *
        | * { оформлен* * @SynonymsForAgreement } *
        | * { оформит* * @SynonymsForAgreement } *
        | * { оформ* * подключен* [на @duckling.date] } *
        | * { заводит* * нов* * @Cable } *
        | * { заведит* * нов* * @Cable } *
        | * { завести * нов* * @Cable } *
        | * { проводит* * нов* * @Cable } *
        | * { проведит* * нов* * @Cable } *
        | * { провести * нов* * @Cable } *
        | * { @Want * [бы] * подключитьcя } *
        | * { потенциальн* * $client } *
        | * { оформ* * нов* * @SynonymsForAgreement } *
        | * { оформ* * нов* * услуг* } *
        | * { стать * $client  * ваш* @Company } *
        | * { провести * @AllServices } *
        | * { проводить * @AllServices } *
        | * { проведен* * @AllServices } *
        | * { стать * $client  * @DomRu } *
        | * { добав* * нов* * @SynonymsForAgreement } *
        | * { добав* * нов* * услуг* } *
        | * { подключен* * нов* * @Cable} *
        | * { @Need * [бы] * подключить* } *
        | * { заключа* * нов* * @SynonymsForAgreement } *
        | * { заключа* * нов* * услуг* } *
        | * { @Modal * [бы] * проложи* * @Cable } *
        | * { выбрать* * @AllServices } *
        | * { стать * [ваш*]* $client } *
        | * { сдела* * ещё одно * подключен* } *
        | * { сдела* * ещё один * @SynonymsForAgreement } *
        | * { сдела* * ещё одну * услуг* } *
        | * { подключит* * ещё одно * подключен* } *
        | * { подключит* * ещё один * @SynonymsForAgreement } *
        | * { подключит* * ещё одну * услуг* } *
        | * { @Want * [бы] * начать * @Services } *
        | * { выбрать * нов* * @SynonymsForAgreement } *
        | * { выбрать * нов* * услуг* } *
        | * { создать * нов* * @SynonymsForAgreement } *
        | * { создать * нов* * услуг* } *
        | * { @Want * [бы] * доподключить } *
        | * { подключен* * @SynonymsForAgreement } *
        | * { подключен* * услуг* } *
        | * { заключит* * нов* * @SynonymsForAgreement } *
        | * { заключит* * нов* * услуг* } *
        | * { @Want * [бы] * открыть * @SynonymsForAgreement } *
        | * { заключ* * @SynonymsForAgreement } *
        | * { @Want * [бы] * подcоединить } *
        | * { @Modal * [бы] * заведи* * @Cable } *
        | * { подключить* * @DomRu } *
        | * когда * { провед* @AllServices } *
        | * { установ* * @AllServices } *
        | * { устанав* * @AllServices } *
        | * { @Want * [бы] * начать * @SynonymsForAgreement } *
        | * { заключен* * нов* * @SynonymsForAgreement } *
        | * { заключен* * нов* * услуг* } *
        | * { проведен* * нов* * @Cable } *
        | * { заведен* * нов* * @Cable } *
        | * { подключить* * ваш* @Company} *
        | * { @Modal * [бы] * завести * @Cable } *
        | * { завести * нов* * @SynonymsForAgreement } *
        | * { завести * нов* * услуг* } *
        | * { поставит* * @Internet } *
        | * { поставит* * @Television } *
        | * { поставит* * @Telephone } *
        | * { поставит* * услуг* } *
        | * { подключить @Internet } *
        | * { подключить к @Internet } *
        | * { подключить к @Television } *
        | * { подключить к услугам } *
        | * { подключение @Internet } *
        | * { подключение @Television } *
        | * { отдел* подключен* } *
        | * { @Operator отдел* продаж* } *
        | * { сотрудник* отдел* продаж* } *
        | * { *консульт* * @Operator * [вопрос*] подключен* } * 
        | * { *консульт* * @Operator * [заявк*] подключен* } * 
        | * { техническ* * возможн* * подключен* } *
        | * { возможно* * $regexp<подключ(ить|иться|ение|ения)> * ([по/в/на] @Place) } * 
        | * { возможно* * $regexp<подключ(ить|иться|ение|ения)> * @Internet } *
        | * { возможно* * $regexp<подключ(ить|иться|ение|ения)> * @Television } *
        | * { возможно* * $regexp<подключ(ить|иться|ение|ения)> * @Services } * 
        | * { возможно* * $regexp<подключ(ить|иться|ение|ения)> * [домашн*] @Telephone } *
        | * { $regexp<подключ(ить|ение)> * @Cable } * 
        | * { есть * @DomRu * @Place * или нет } * 
        | * { есть * подключени* * @Place * или нет } *
        | * { подключен * ли * @DomRu * @Place } * )
        
    $client = (клиент*|абонент*|пользовател*)
    $arenda = (арендатор*|владелец*|гост*|должник*|жилец*|квартирант*|постоялец*|постояльц*|предпринимател*|хозяин*|хозяйк*|собственник*)
    $people = ($client|$relatives|$friend)
    
    $350_PhoneProblem = ( * { (@Bad/$bad) * работ* * номер* * @Telephone } *
        | * { (@Bad/$bad) * работ* * @SynonymsForCall * @Telephone } *
        | * { (@Bad/$bad) * работ* } * { @Telephone * [@Equipment] } *
        | * { (@Bad/$bad) * работ* * вызов* } *
        | * { домашн* * @Telephone } *
        | * { стационарн* * @Telephone } *
        | * { городск* * @Telephone } *
        | * { (еле/едва) работа* } * { @Telephone * [@Equipment] } *
        | * { лаг * @Telephone * [@Equipment] } *
        | * { лаги * @Telephone * [@Equipment] } *
        | * { лагать * @Telephone * [@Equipment] } *
        | * { подлагива* * @Telephone * [@Equipment] } *
        | * { лагает * @Telephone * [@Equipment] } *
        | * { (не исправ*/неисправ*) * @Telephone * [@Equipment] } *
        | * { не исправ* * трубк* * @Telephone } *
        | * { неисправ* * трубк* * @Telephone } *
        | * { не определ* * (номер*/@SynonymsForCall) * @Telephone } *
        | * { обрыв* * номер* * @Telephone } *
        | * { сбрасыва* * номер* * @Telephone } *
        | * { сброс* * номер* * @Telephone } *
        | * { дроп* * номер* * @Telephone } *
        | * { обрыв* * @SynonymsForCall * @Telephone } *
        | * { сбрасыва* * @SynonymsForCall * @Telephone } *
        | * { сброс* * @SynonymsForCall * @Telephone } *
        | * { дроп* * @SynonymsForCall * @Telephone } *
        | * { оборв* * @SynonymsForCall * @Telephone } *
        | * { не работ* * @Telephone * [@Equipment] } *
        | * { не работ* * трубк* * @Telephone * [@Equipment] } *
        | * { нерабоч* * @Telephone * [@Equipment] } *
        | * { нерабоч* * трубк* * @Telephone * [@Equipment] } *
        | * { (недовол*/не довол*) * @Telephone * [@Equipment] } *
        | * { (оборва*/обрыв*) * @Telephone * [@Equipment] } *
        | * { (отключен*/отключился/отключилась) * @Telephone * [@Equipment] } *
        | * { (отруби*/обруби*) * @Telephone * [@Equipment] } *
        | * { (отсутствует /отсутстви*) * @Telephone * [@Equipment] } *
        | * { ошибк* * @SynonymsForCall * @Telephone } *
        | * { пауз* * @SynonymsForCall * @Telephone } *
        | * { перебо* * @SynonymsForCall * @Telephone } *
        | * { подвис* * @SynonymsForCall * @Telephone } *
        | * { подлаг* * @SynonymsForCall * @Telephone } *
        | * { ошибк* * вызов* * @Telephone } *
        | * { пауз* * вызов* * @Telephone } *
        | * { перебо* * вызов* * @Telephone } *
        | * { подвис* * вызов* * @Telephone } *
        | * { подлаг* * вызов* * @Telephone } *
        | * { полома* * @SynonymsForCall * @Telephone } *
        | * { поломк* * @SynonymsForCall * @Telephone } *
        | * { полома* * [трубк*] * @Telephone } *
        | * { поломк* * [трубк*] * @Telephone } *
        | * { сломал* * [трубк*] * @Telephone } *
        | * { сломан* * [трубк*] * @Telephone } *
        | * { починят* * @SynonymsForCall * @Telephone } *
        | * { починит* * [трубк*] * @Telephone } *
        | * { починят* * [трубк*] * @Telephone } *
        | * { полома* * вызов* * @Telephone } *
        | * { сломал* * @SynonymsForCall * @Telephone } *
        | * { починит* * @SynonymsForCall * @Telephone } *
        | * { (сбои/сбой) * @Telephone } *
        | * { прерыва* * @SynonymsForCall * @Telephone } *
        | * { прерва* * @SynonymsForCall * @Telephone } *
        | * { (ухудшени*/ухудшил*) * @Telephone } *
        | * { чинит* * @SynonymsForCall * @Telephone } *
        | * { чинит* * [трубк*] * @Telephone } *
        | * { ремонт* * @SynonymsForCall * @Telephone } *
        | * { ремонт* * [трубк*] * @Telephone } *
        | * { барахл* * @Telephone } *
        | * { барахл* * [трубк*] * @Telephone } *
        | * { бед* * @Telephone } *
        | * { безобрази* * @Telephone } *
        | * { вопрос* * домашн* * @Telephone } *
        | * { вопрос* * стационарн* * @Telephone } *
        | * { вопрос* * городск* * @Telephone } *
        | * { глючит * @Telephone } *
        | * { глючит * [трубк*] * @Telephone } *
        | * { жалоб* * @Telephone } *
        | * { задержк* * @Telephone } *
        | * { молча* * @Telephone } *
        | * { молчи* * @Telephone } *
        | * { испорчен* * @Telephone } *
        | * { не заработа* * @Telephone } *
        | * { не проход* * @SynonymsForCall * [@Telephone] } *
        | * { не слышн* * @SynonymsForCall * @Telephone } *
        | * { неполадк* * @Telephone } *
        | * { отремонтир* * @Telephone } *
        | * { перестал* * работ* * номер* * @Telephone } *
        | * { перестал* * работ* * @SynonymsForCall * @Telephone } *
        | * { перестал* * работ* * @Telephone } *
        | * { помех* * телефон* * трубк* } *
        | * { помех* * телефон* * @SynonymsForCall } *
        | * { претензи* * @Telephone } *
        | * { проблем* * @Telephone } *
        | * { ряб* * телефон* * трубк* } *
        | * { ряб* * телефон* * @Telephone } *
        | * { ряб* * телефон* * @SynonymsForCall } *
        | * { сложност* * @Telephone } *
        | * { треск* * телефон* * трубк* } *
        | * { треск* * @Telephone } *
        | * { треск* * @SynonymsForCall } *
        | * { шум* * телефон* * трубк* } *
        | * { шум* * @Telephone } *
        | * { шум* * телефон* * @SynonymsForCall } *
        | * { не работа* * домашн* @Telephone } * 
        | * { не работа* * стационар* @Telephone } * 
        | * { не работа* * городск* @Telephone } * 
        | {@Telephone не восстановил*}
        | {@Telephone не появил*} )
    
    $360_StopAgreementFinal = (* {*рвать * отношени*} *
        | * { * отключайте * @AllServices [платн* $weight<-1>]} *
        | * { * отключайте * @SynonymsForAgreement} *
        | * { * отключаться * @AllServices} *
        | * { * отключаться * @SynonymsForAgreement} *
        | * { * отключите * @AllServices [платн* $weight<-1>]} *
        | * { * отключите * @SynonymsForAgreement} *
        | * { * отключить * @AllServices [платн* $weight<-1>]} *
        | * { * отключить * @SynonymsForAgreement} *
        | * { * отключиться * @AllServices} *
        | * { * отключиться * @SynonymsForAgreement} *
        | * { * отключусь * @AllServices} *
        | * { * отключусь * @SynonymsForAgreement} *
        | * {@Modal * убери* * @Internet} *
        | * {@Modal * убери* * @Telephone} *
        | * {@Modal * убери* * @Television} *
        | * {@Modal * убрать * @Internet} *
        | * {@Modal * убрать * @Telephone} *
        | * {@Modal * убрать * @Television} *
        | * {*рвать * @Services [платн* $weight<-1>]} *
        | * {*рвать * @SynonymsForAgreement} *
        | * {аннулир* * @Services [платн* $weight<-1>]} *
        | * {аннулир* * @SynonymsForAgreement} *
        | * {аннулир* * отношени*} *
        | * {вообще * убери* * @Internet} *
        | * {вообще * убери* * @Telephone} *
        | * {вообще * убери* * @Television} *
        | * {вообще * убрать * @Internet} *
        | * {вообще * убрать * @Telephone} *
        | * {вообще * убрать * @Television} *
        | * {вопрос* * отказ* * @Services [платн* $weight<-1>]} *
        | * {вопрос* * отказ* * @SynonymsForAgreement} *
        | * {выбра* * @Competitors} *
        | * {выбра* * друго* * @Company} *
        | * {выбра* * друго* * конкурент*} *
        | * {выбра* * друго* * оператор*} *
        | * {выбра* * нов* * @Company} *
        | * {выбра* * нов* * конкурент*} *
        | * {выбра* * нов* * оператор*} *
        | * {заблокир* * @SynonymsForAgreement} *
        | * {(заблокировать/заблокируй*) * @AllServices [платн* $weight<-1>]} *
        | * {заверш* * @Services} *
        | * {заверш* * @SynonymsForAgreement} *
        | * {заверш* * отношени*} *
        | * {заканч* * @Services} *
        | * {заканч* * @SynonymsForAgreement} *
        | * {заканч* * отношени*} *
        | * {законч* * @Services} *
        | * {законч* * @SynonymsForAgreement} *
        | * {законч* * отношени*} *
        | * {закрой* * @AllServices} *
        | * {закрой* * @SynonymsForAgreement} *
        | * {закрывать * @AllServices} *
        | * {закрывать * @SynonymsForAgreement} *
        | * {закрыти* * @AllServices} *
        | * {закрыти* * @SynonymsForAgreement номер @duckling.number } *
        | * {закрыти* * @SynonymsForAgreement} *
        | * {закрыть * @AllServices} *
        | * {закрыть * @SynonymsForAgreement} *
        | * {запрос* * отказ* * @AllServices} *
        | * {запрос* * отказ* * @SynonymsForAgreement} *
        | * {заявк* * отказ* * @AllServices} *
        | * {заявк* * отказ* * @SynonymsForAgreement} *
        | * {заявлени* * отказ* * @AllServices} *
        | * {заявлени* * отказ* * @SynonymsForAgreement} *
        | * {изменил* * @Competitors} *
        | * {изменить * @Company} *
        | * {изменить * @Competitors} *
        | * {изменить * оператор*} *
        | * {использ* * друго* * @Company} *
        | * {использ* * друго* * конкурент*} *
        | * {использ* * друго* * оператор*} *
        | * {использ* * нов* * @Company} *
        | * {использ* * нов* * конкурент*} *
        | * {использ* * нов* * оператор*} *
        | * {использу* * @Competitors} *
        | * {ликвидир* * @AllServices } *
        | * {ликвидир* * @SynonymsForAgreement} *
        | * {ликвидир* * отношени*} *
        | * {менять * @Company} *
        | * {менять * @Competitors} *
        | * {менять * оператор*} *
        | * {навсегда * убери* * @Internet} *
        | * {навсегда * убери* * @Telephone} *
        | * {навсегда * убери* * @Television} *
        | * {навсегда * убрать * @Internet} *
        | * {навсегда * убрать * @Telephone} *
        | * {навсегда * убрать * @Television} *
        | * {не @Need * @AllServices} *
        | * {не @Need * @SynonymsForAgreement номер @duckling.number } *
        | * {не пользу* * @AllServices} *
        | * {не пользу* * @SynonymsForAgreement номер @duckling.number } *
        | * {откаж* * от * @AllServices} *
        | * {откаж* * от * @SynonymsForAgreement} *
        | * {отказ * @SynonymsForAgreement номер @duckling.number } *
        | * {отказ * от * @AllServices} *
        | * {отказ * от * @SynonymsForAgreement} *
        | * {отказаться * от * @AllServices} *
        | * {отказаться * от * @SynonymsForAgreement} *
        | * {отказыва* * от * @AllServices} *
        | * {отказыва* * от * @SynonymsForAgreement} *
        | * {отказываться * от * @AllServices} *
        | * {отказываться * от * @SynonymsForAgreement} *
        | * {отключ* * @SynonymsForAgreement номер @duckling.number } *
        | * {отключени* * @AllServices} *
        | * {отключени* * @SynonymsForAgreement номер @duckling.number } *
        | * {перейдем * (на/к) * @Company} *
        | * {перейдем * (на/к) * конкурент*} *
        | * {перейдем * от * @DomRu} *
        | * {перейдем * от * вашей @Company} *
        | * {перейти * (на/к) * @Company} *
        | * {перейти * (на/к) * конкурент*} *
        | * {перейти * от * @DomRu} *
        | * {перейти * от * вашей @Company} *
        | * {переключ* * друго* * @Company} *
        | * {переключ* * друго* * конкурент*} *
        | * {переключ* * друго* * оператор*} *
        | * {переманил* * @Competitors} *
        | * {переход* * (на/к) * @Company} *
        | * {переход* * (на/к) * конкурент*} *
        | * {переход* * @Competitors} *
        | * {переход* * друго* * @Company} *
        | * {переход* * друго* * конкурент*} *
        | * {переход* * друго* * оператор*} *
        | * {переход* * от * @DomRu} *
        | * {переходи* * от * вашей @Company} *
        | * {подключ* * @Competitors} *
        | * {подключ* * друго* * @Company} *
        | * {подключ* * друго* * конкурент*} *
        | * {подключ* * друго* * оператор*} *
        | * {полностью * убери* * @Internet} *
        | * {полностью * убери* * @Telephone} *
        | * {полностью * убери* * @Television} *
        | * {полностью * убрать * @Internet} *
        | * {полностью * убрать * @Telephone} *
        | * {полностью * убрать * @Television} *
        | * {поменял* * @Competitors} *
        | * {поменять * @Company} *
        | * {поменять * @Competitors} *
        | * {поменять * оператор*} *
        | * {порвать* * @Services} *
        | * {порвать* * @SynonymsForAgreement} *
        | * {порвать* * отношени*} *
        | * {порвать* * сотрудничеств*} *
        | * {порви* * @Services} *
        | * {порви* * @SynonymsForAgreement} *
        | * {порви* * отношени*} *
        | * {прекратит* * @Services} *
        | * {прекратит* * @SynonymsForAgreement} *
        | * {прекратит* * отношени*} *
        | * {прекратит* * сотрудничеств*} *
        | * {прекраща* * @Services} *
        | * {прекраща* * @SynonymsForAgreement} *
        | * {прекраща* * отношени*} *
        | * {прекраща* * сотрудничеств*} *
        | * {разорв* * @Services} *
        | * {разорв* * @SynonymsForAgreement} *
        | * {разорв* * отношени*} *
        | * {разорв* * сотрудничеств*} *
        | * {разорви* * @Services} *
        | * {разорви* * @SynonymsForAgreement} *
        | * {разорви* * отношени*} *
        | * {разрыв* * @Services} *
        | * {разрыв* * @SynonymsForAgreement} *
        | * {разрыв* * отношени*} *
        | * {разрыв* * сотрудничеств*} *
        | * {расторг* * @AllServices} *
        | * {расторг* * @Services} *
        | * {расторг* * @SynonymsForAgreement} *
        | * {расторг* * отношени*} *
        | * {расторг* * сотрудничеств*} *
        | * {расторжен* * @AllServices} *
        | * {расторжен* * @Services} *
        | * {расторжен* * @SynonymsForAgreement} *
        | * {расторжен* * отношени*} *
        | * {расторжен* * сотрудничеств*} *
        | * {расторжени* * @AllServices} *
        | * {расторжени* * @SynonymsForAgreement номер @duckling.number } *
        | * {уйдем * (на/к) * @Company} *
        | * {уйдем * (на/к) * конкурент*} *
        | * {уйдем * от * @DomRu} *
        | * {уйдем * от * вашей @Company} *
        | * {уйти * (на/к) * @Company} *
        | * {уйти * (на/к) * конкурент*} *
        | * {уйти * от * @DomRu} *
        | * {уйти * от * вашей @Company} *
        | * {уходи* * (на/к) * @Company} *
        | * {уходи* * (на/к) * конкурент*} *
        | * {уходи* * от * @DomRu} *
        | * {уходи* * от * вашей @Company} *
        | * {закрыл* * @SynonymsForAgreement} * {как * *платить} *
        | * {закрыл* * @SynonymsForAgreement} * {как * *плачивать} *
        | * {расторг* * @SynonymsForAgreement} * {как * *платить} *
        | * {расторг* * @SynonymsForAgreement} * {как * *плачивать} *
        | * {расторж* * @SynonymsForAgreement} * {как * *платить} *
        | * {расторж* * @SynonymsForAgreement} * {как * *плачивать} *
        | * {убрал* * @SynonymsForAgreement} * {как * *платить} *
        | * {убрал* * @SynonymsForAgreement} * {как * *плачивать} * 
        | * {удал* * @SynonymsForAgreement} * {как * *платить} *
        | * {удал* * @SynonymsForAgreement} * {как * *плачивать} * )
        
    $370_IntercomAndCCTV = ( * { @Operator * (домофон*/аудиодомофон*) } * 
        | * домофон * )
        
    $keyIntercom = ( * @KeyIntercom *
        | * { @Give * @KeyIntercom } *
        | * { выдад* * @KeyIntercom } *
        | * { выдае* * @KeyIntercom } *
        | * { магнитн* * @KeyIntercom } *
        | * { электронн* * @KeyIntercom } *
        | * { домофон* * @KeyIntercom } *
        | * { цифров* * @KeyIntercom } * )
        
    $keyBuy = ( * { купи* * @KeyIntercom } *
        | * { покупк* * @KeyIntercom } *
        | * { покупать * @KeyIntercom } *
        | * { заказ* * @KeyIntercom } *
        | * { потеря* * @KeyIntercom } *
        | * { утеря* * @KeyIntercom } *
        | * { затеря* * @KeyIntercom } *
        | * { получи* * @KeyIntercom } *
        | * { получен* * @KeyIntercom } *
        | * { получа* * @KeyIntercom } *
        | * { выдав* * @KeyIntercom } *
        | * { выдал* * @KeyIntercom } *
        | * { взять * @KeyIntercom } *
        | * { брать * @KeyIntercom } *
        | * { взяти* * @KeyIntercom } *
        | * { взял* * @KeyIntercom } *
        | * { *менять * @KeyIntercom } *
        | * { *изменить * @KeyIntercom } *
        | * { *менял* * @KeyIntercom } *
        | * { *менил* * @KeyIntercom } *
        | * { меняю * @KeyIntercom } *
        | * { меняем * @KeyIntercom } *
        | * { *копир* * @KeyIntercom } *
        | * { делай* * @KeyIntercom } *
        | * { сделай* * @KeyIntercom } *
        | * { делать * @KeyIntercom } *
        | * { сделать * @KeyIntercom } *
        | * { (нет/нету) * @KeyIntercom } *
        | * { нов* * @KeyIntercom } *
        | * { друг* * @KeyIntercom } *
        | * { не @Give * @KeyIntercom } *
        | * { не выдад* * @KeyIntercom } *
        | * { не выдае* * @KeyIntercom } *
        | * { не выдав* * @KeyIntercom } *
        | * { не выдал* * @KeyIntercom } *
        | * { не получ* * @KeyIntercom } *
        | * { @Modal * @KeyIntercom } *
        | * { запрос* * @KeyIntercom } *
        | * { заявк* * @KeyIntercom } *)
        
    $keyActivate = (* { актив* * @KeyIntercom } *
        | * { добав* * @KeyIntercom } *
        | * { запрограммир* * @KeyIntercom } *
        | * { программир* * @KeyIntercom } *
        | * { настроен* * @KeyIntercom } *
        | * { настрои* * @KeyIntercom } *
        | * { настраива* * @KeyIntercom } *
        | * { настрой* * @KeyIntercom } *
        | * { не подключа* * @KeyIntercom } *
        | * { не подключи* * @KeyIntercom } *
        | * { не актив* * @KeyIntercom } *
        | * { не @Successful * подключ* * @KeyIntercom  } *
        | * { подключ* * @KeyIntercom } *
        | * { не @Successful * актив* * @KeyIntercom  } *
        | * { не @Successful * добав* * @KeyIntercom  } *
        | * { не @Successful * *программир* * @KeyIntercom  } *
        | * { не @Successful * настрой* * @KeyIntercom  } *
        | * { не @Successful * настроит* * @KeyIntercom  } *
        | * { не @Successful * связ* * @KeyIntercom  } *
        | * { не @Successful * свяж* * @KeyIntercom  } *
        | * { не @Successful * привяз* * @KeyIntercom  } *
        | * { связывай* * @KeyIntercom * } *
        | * { привяз* * @KeyIntercom * } *
        | * { привяж* * @KeyIntercom * } *
        | * { свяж* * @KeyIntercom * } *
        | * { связать* * @KeyIntercom * } *
        | * { *программир* * @KeyIntercom * } *)
        
    $keyNotWork = ( * { дефект* * @KeyIntercom } *
        | * { завис* * @KeyIntercom } *
        | * { испорчен* * @KeyIntercom } *
        | * { недействующ* * @KeyIntercom } *
        | * { медленно * работа* * @KeyIntercom } *
        | * { неправильно * работа* * @KeyIntercom } *
        | * { не правильно * работа* * @KeyIntercom } *
        | * { неверно * работа* * @KeyIntercom } *
        | * { не верно * работа* * @KeyIntercom } *
        | * { медленно * срабат* * @KeyIntercom } *
        | * { неправильно * срабат* * @KeyIntercom } *
        | * { не правильно * срабат* * @KeyIntercom } *
        | * { неверно * срабат* * @KeyIntercom } *
        | * { не верно * срабат* * @KeyIntercom } *
        | * { не работа* * @KeyIntercom } *
        | * { не заработа* * @KeyIntercom } *
        | * { не срабат* * @KeyIntercom } *
        | * { нефункционирую* * @KeyIntercom } *
        | * { поврежден* * @KeyIntercom } *
        | * { не функциониру* * @KeyIntercom } *
        | * { ошибк* * @KeyIntercom } *
        | * { сломал* * @KeyIntercom } *
        | * { сломан* * @KeyIntercom } *
        | * { поломал* * @KeyIntercom } *
        | * { поломан* * @KeyIntercom } *
        | * { проблем* * @KeyIntercom } *
        | * { жалоб* * @KeyIntercom } *
        | * { сложност* * @KeyIntercom } *
        | * { неполадк* * @KeyIntercom } *
        | * { неисправност* * @KeyIntercom } *
        | * { интерес* * @KeyIntercom } *
        | * { дефект* * @KeyIntercom } *
        | * { не открыв* * подъезд* * @KeyIntercom } *
        | * { не открыт* * подъезд* * @KeyIntercom } *
        | * { не открыв* * парадн* * @KeyIntercom } *
        | * { не открыт* * парадн* * @KeyIntercom } *
        | * { не действует * @KeyIntercom } *
        | * { неисправн* * @KeyIntercom } *
        | * { перестал* * работа* * @KeyIntercom } *
        | * { перестал* * срабат* * @KeyIntercom } *
        | * { перестал* * открыв* * @KeyIntercom } *)
        
    $domofonActivate = ( * { заключ* * домофон* * трубк* } *
        | * { заключ* * аудиодомофон* * трубк* } *
        | * { заключ* * домофон* * панел* } *
        | * { заключ* * аудиодомофон* * панел* } *
        | * { заключ* * @SynonymsForAgreement * домофон* } *
        | * { заключ* * @SynonymsForAgreement * аудиодомофон* } *
        | * { стать * $client * домофон*} *
        | * { стать * $client * аудиодомофон*} *
        | * { подключ* * домофон* } *
        | * { подключ* * аудиодомофон* } *
        | * { установ* * домофон* } *
        | * { устанав* * домофон* } *
        | * { установ* * аудиодомофон* } *
        | * { устанав* * аудиодомофон* } *
        | * { постав* * домофон* } *
        | * { постав* * аудиодомофон* } * )
        
    $smartDom = ( * @SmartDom *
        | * { *регистрир* * [приложени*] @SmartDom } *
        | * { авторизов* * [приложени*] @SmartDom } *
        | * { актив* * [приложени*] @SmartDom } *
        | * { интерес* * [приложени*] @SmartDom } *
        | * { вопрос* * [приложени*] @SmartDom } *
        | * { консультаци* * [приложени*] @SmartDom } *
        | * { информаци* * [приложени*] @SmartDom } *
        | * { вход* * [приложени*] @SmartDom } *
        | * { авториз* * [приложени*] @SmartDom } *
        | * { регистр* * [приложени*] @SmartDom } *
        | * { настра* * [приложени*] @SmartDom } *
        | * { настрой* * [приложени*] @SmartDom } *
        | * { настро* * [приложени*] @SmartDom } *
        | * { недоступ* * [приложени*] @SmartDom } *
        | * { не доступ* * [приложени*] @SmartDom } *
        | * { неисправ* * [приложени*] @SmartDom } *
        | * { не исправ* * [приложени*] @SmartDom } *
        | * { проблем* * [приложени*] @SmartDom } *
        | * { ошибк* * [приложени*] @SmartDom } *
        | * { жалоб* * [приложени*] @SmartDom } *
        | * { неисправност* * [приложени*] @SmartDom } *
        | * { неполадк* * [приложени*] @SmartDom } *
        | * { установ* * [приложени*] @SmartDom } *
        | * { устанав* * [приложени*] @SmartDom } *
        | * { восстанав* * [приложени*] @SmartDom } *
        | * { восстанов* * [приложени*] @SmartDom } *
        | * { [приложени*] @SmartDom } *
        | * { заблокиров* * [приложени*] @SmartDom } *
        | * { не @Successful * войти * [приложени*] @SmartDom } *
        | * { не @Successful * зайти * [приложени*] @SmartDom } *
        | * { не @Successful * подключ* * [приложени*] @SmartDom } *
        | * { не запуска* * [приложени*] @SmartDom } *
        | * { не отвеча* * [приложени*] @SmartDom } *
        | * { не работа* * [приложени*] @SmartDom } *
        | * { нет доступ* * [приложени*] @SmartDom } *
        | * { нету доступ* * [приложени*] @SmartDom } *
        | * { парол* * [приложени*] @SmartDom } *
        | * { перестал* * вход* * [приложени*] @SmartDom } *
        | * { перестал* * заход* * [приложени*] @SmartDom } *
        | * { перестал* * подключ* * [приложени*] @SmartDom } *
        | * { поставит* * [приложени*] @SmartDom } *
        | * { не откры* * [приложени*] @SmartDom } *
        | * { не @Successful откры* * [приложени*] @SmartDom } *)
        
    $domofonNotWork = ( * { не [@Successful] исправ* * домофон* * трубк* } *
        | * { не [@Successful] исправ* * домофон* * панел* } *
        | * { не [@Successful] исправ* * аудиодомофон* * трубк* } *
        | * { не [@Successful] исправ* * аудиодомофон* * панел* } *
        | * { неисправ* * домофон* * трубк* } *
        | * { неисправ* * домофон* * панел* } *
        | * { неисправ* * аудиодомофон* * трубк* } *
        | * { неисправ* * аудиодомофон* * панел* } *
        | * { не [@Successful] попасть * $regexp<дом(у|ой)> } *
        | * { не [@Successful] попасть * подъезд* } *
        | * { не [@Successful] попасть * парадн* } *
        | * { @Give * доступ* * $regexp<дом(у|ой)> } *
        | * { @Give * доступ* * подъезд* } *
        | * { @Give * доступ* * парадн* } *
        | * { не [@Successful] войти * домофон* } *
        | * { не [@Successful] войти * аудиодомофон* } *
        | * { не [@Successful] зайти * домофон* } *
        | * { не [@Successful] зайти * аудиодомофон* } *
        | * { не [@Successful] откры* * домофон* * телефона } *
        | * { не [@Successful] откры* * аудиодомофон* * телефона } *
        | * { не [@Successful] закры* * домофон* * телефона } *
        | * { не [@Successful] закры* * аудиодомофон* * телефона } *
        | * { не [@Successful] откры* * ворот* * домофон* } *
        | * { не [@Successful] откры* * ворот* * аудиодомофон* } *
        | * { не [@Successful] закры* * ворот* * домофон* } *
        | * { не [@Successful] закры* * ворот* * аудиодомофон* } *
        | * { не [@Successful] откры* * двер* * домофон* } *
        | * { не [@Successful] откры* * двер* * аудиодомофон* } *
        | * { не [@Successful] откры* * калитк* * домофон* } *
        | * { не [@Successful] откры* * калитк* * аудиодомофон* } *
        | * { не [@Successful] закры* * калитк* * домофон* } *
        | * { не [@Successful] закры* * калитк* * аудиодомофон* } *
        | * { не [@Successful] подключа* * домофон* } *
        | * { не [@Successful] подключа* * аудиодомофон* } *
        | * { не [@Successful] подключит* * домофон* } *
        | * { не [@Successful] подключит* * аудиодомофон* } *
        | * { не [@Successful] попад* * домофон* } *
        | * { не [@Successful] попад* * аудиодомофон* } *
        | * { не [@Successful] попасть * домофон* } *
        | * { не [@Successful] попасть * аудиодомофон* } *
        | * { не [@Successful] откры* * калитк* } *
        | * { не [@Successful] закры* * калитк* } *)
        
    $CCTV = ( * @VideoThings *
        | * { $money * @VideoThings } *
        | * { @Modal * $regexp<запис(ь|и|ей)> * подъезд* } *
        | * { @Modal * доступ* * подъезд* } *
        | * { @Modal * $regexp<запис(ь|и|ей)> * парадн* } *
        | * { @Modal * доступ* * парадн* } *
        | * { @Give * $regexp<запис(ь|и|ей)> * подъезд* } *
        | * { @Give * $regexp<запис(ь|и|ей)> * парадн* } *
        | * { @Successful * $regexp<запис(ь|и|ей)> * подъезд* } *
        | * { @Successful * доступ* * подъезд* } *
        | * { @Successful * $regexp<запис(ь|и|ей)> * парадн* } *
        | * { @Successful * доступ* * парадн* } *
        | * { @Modal * $regexp<запис(ь|и|ей)> * @VideoThings } *
        | * { @Modal * доступ* * @VideoThings } *
        | * { @Give * $regexp<запис(ь|и|ей)> * @VideoThings } *
        | * { @Give * доступ* * @VideoThings } *
        | * { @Successful * $regexp<запис(ь|и|ей)> * @VideoThings } *
        | * { @Successful * доступ* * @VideoThings } *
        | * { взять * камер* * @VideoThings } *
        | * { взять * видеокамер* * @VideoThings } *
        | * { возьм* * камер* * @VideoThings } *
        | * { возьм* * видеокамер* * @VideoThings } *
        | * { восстанав* * камер* * @VideoThings } *
        | * { восстанав* * видеокамер* * @VideoThings } *
        | * { дефект* * @VideoThings } *
        | * { завис* * @VideoThings } *
        | * { испорчен* * @VideoThings } *
        | * { недействующ* * @VideoThings } *
        | * { виснет * @VideoThings } *
        | * { виснут * @VideoThings } *
        | * { запис* * камер* * @VideoThings } *
        | * { запис* * видеокамер* * @VideoThings } *
        | * { камер* * домофон* } *
        | * { камер* * аудиодомофон* } *
        | * { камер* * @DomRu } *
        | * { видеокамер* * домофон* } *
        | * { видеокамер* * аудиодомофон* } *
        | * { видеокамер* * @DomRu } *
        | * { камер* * @VideoThings } *
        | * { видеокамер* * @VideoThings } *
        | * { настроен* * камер* * @VideoThings } *
        | * { настрои* * камер* * @VideoThings } *
        | * { настраива* * камер* * @VideoThings } *
        | * { настрой* * камер* * @VideoThings } *
        | * { настроен* * видеокамер* * @VideoThings } *
        | * { настрои* * видеокамер* * @VideoThings } *
        | * { настраива* * видеокамер* * @VideoThings } *
        | * { настрой* * видеокамер* * @VideoThings } *
        | * { не работа* * @VideoThings } *
        | * { не заработа* * @VideoThings } *
        | * { не срабат* * @VideoThings } *
        | * { неправильно * *работа* * @VideoThings } *
        | * { не правильно * *работа* * @VideoThings } *
        | * { неверно * *работа* * @VideoThings } *
        | * { не верно * *работа* * @VideoThings } *
        | * { нет * $regexp<запис(ь|и|ей)> * @VideoThings } *
        | * { нет * доступ* * @VideoThings } *
        | * { нету * $regexp<запис(ь|и|ей)> * @VideoThings } *
        | * { нету * доступ* * @VideoThings } *
        | * { нефункционирую* * @VideoThings } *
        | * { поврежден* * @VideoThings } *
        | * { не функциониру* * @VideoThings } *
        | * { оформи* * камер* * @VideoThings } *
        | * { оформи* * видеокамер* * @VideoThings } *
        | * { установи* * камер* * @VideoThings } *
        | * { установи* * видеокамер* * @VideoThings } *
        | * { устанав* * камер* * @VideoThings } *
        | * { устанав* * видеокамер* * @VideoThings } *
        | * { переоформи* * камер* * @VideoThings } *
        | * { переоформи* * видеокамер* * @VideoThings } *
        | * { переустанови* * камер* * @VideoThings } *
        | * { переустанови* * видеокамер* * @VideoThings } *
        | * { подключи* * камер* * @VideoThings } *
        | * { подключи* * видеокамер* * @VideoThings } *
        | * { переоформи* * @VideoThings } *
        | * { переустанови* * @VideoThings } *
        | * { подключи* * @VideoThings } *
        | * { подсоедини* * камер* * @VideoThings } *
        | * { вопрос* * камер* * @VideoThings } *
        | * { консультац* * камер* * @VideoThings } *
        | * { информац* * камер* * @VideoThings } *
        | * { подсоедини* * видеокамер* * @VideoThings } *
        | * { вопрос* * видеокамер* * @VideoThings } *
        | * { консультац* * видеокамер* * @VideoThings } *
        | * { информац* * видеокамер* * @VideoThings } *
        | * { подсоедини* * @VideoThings } *
        | * { вопрос* * @VideoThings } *
        | * { консультац* * @VideoThings } *
        | * { информац* * @VideoThings } *
        | * { интерес* * @VideoThings } *
        | * { поломал* * @VideoThings } *
        | * { поломан* * @VideoThings } *
        | * { починят * @VideoThings } *
        | * { починит* * @VideoThings } *
        | * { чинит* * @VideoThings } *
        | * { проблем* * @VideoThings } *
        | * { ошибк* * @VideoThings } *
        | * { жалоб* * @VideoThings } *
        | * { неисправн* * @VideoThings } *
        | * { неполадк* * @VideoThings } *
        | * { сломал* * @VideoThings } *
        | * { сломан* * @VideoThings } *
        | * { смотр* * камер* * @VideoThings } *
        | * { смотр* * видеокамер* * @VideoThings } *
        | * { просмотр* * камер* * @VideoThings } *
        | * { просмотр* * видеокамер* * @VideoThings } *
        | * { @TariffSingular * камер* } *
        | * { @TariffSingular * видеокамер* } *
        | * { @TariffSingular * @VideoThings } *
        | * { установк* * @VideoThings } *
        | * { подключени* * @VideoThings } *
        | * { установк* * камер* * @VideoThings } *
        | * { установк* * видеокамер* * @VideoThings } *
        | * { подключени* * камер* * @VideoThings } *
        | * { подключени* * видеокамер* * @VideoThings } *
        | * { работ* * камер* * @VideoThings } *
        | * { работ* * видеокамер* * @VideoThings } *
        | * { работ* * @VideoThings } *
        | * { @Credentials * @VideoThings } *
        | * { @LegalEntity * @VideoThings } *
        | * { @PhysicalEntities * @VideoThings } *
        | * { архив* * запис* * камер* * [@DomRu]} *
        | * { архив* * запис* * видеокамер* * [@DomRu]} *
        | * { архив* * запис* * @VideoThings * [@DomRu]} *
        | * { бракован* * камер* * @VideoThings } *
        | * { бракован* * камер* * домофон* } *
        | * { бракован* * видеокамер* * @VideoThings } *
        | * { бракован* * видеокамер* * домофон* } *
        | * { возможност* * подключен* @VideoThings * } *
        | * { заявк* * (камер*/видеокамер*) * @DomRu } *
        | * { заявк* * (камер*/видеокамер*) * @VideoThings } *
        | * { качеств* * камер* * @VideoThings } *
        | * { качеств* * камер* * домофон* } *
        | * { качеств* * видеокамер* * @VideoThings } *
        | * { качеств* * видеокамер* * домофон* } *
        | * { монтаж* * камер* * @VideoThings } *
        | * { монтаж* * камер* * домофон* } *
        | * { монтаж* * видеокамер* * @VideoThings } *
        | * { монтаж* * видеокамер* * домофон* } *
        | * { не записыва* * @VideoThings } *
        | * { не записат* * @VideoThings } *
        | * { не пишет * @VideoThings } *
        | * { не фиксир* * @VideoThings } *
        | * { не настроен* * @VideoThings } *
        | * { не настрои* * @VideoThings } *
        | * { не настраива* * @VideoThings } *
        | * { не @Successful * провер* * запис* } *
        | * { не @Successful * просмотр* * запис* } *
        | * { не @Successful * посмотр* * запис* } *
        | * { не @Successful * скачать * запис* } *
        | * { не @Successful * найти * запис* } *
        | * { не @Successful * увидеть * запис* } *
        | * { не действует * @VideoThings } *
        | * { не показ* * @VideoThings } *
        | * { нет изображени* * камер* * @VideoThings } *
        | * { нет изображени* * камер* * домофон* } *
        | * { нет изображени* * видеокамер* * @VideoThings } *
        | * { нет изображени* * видеокамер* * домофон* } *
        | * { отключил* * камер* * @VideoThings } *
        | * { отключил* * камер* * домофон* } *
        | * { отключил* * видеокамер* * @VideoThings } *
        | * { отключил* * видеокамер* * домофон* } *
        | * { отремонт* * камер* * @VideoThings } *
        | * { отремонт* * камер* * домофон* } *
        | * { отремонт* * видеокамер* * @VideoThings } *
        | * { отремонт* * видеокамер* * домофон* } *
        | * { пауз* * камер* * @VideoThings } *
        | * { пауз* * камер* * домофон* } *
        | * { пауз* * видеокамер* * @VideoThings } *
        | * { пауз* * видеокамер* * домофон* } *
        | * { перестал* * работа* * @VideoThings } *
        | * { перестал* * срабат* * @VideoThings } *
        | * { перестал* * показ* * @VideoThings } *
        | * { плохо показ* * @VideoThings } *
        | * { плохо работа* * @VideoThings } *
        | * { расфокусир* * камер* * @VideoThings } *
        | * { расфокусир* * камер* * домофон* } *
        | * { расфокусир* * видеокамер* * @VideoThings } *
        | * { расфокусир* * видеокамер* * домофон* } *
        | * { ремонт* * камер* * @VideoThings } *
        | * { ремонт* * камер* * домофон* } *
        | * { ремонт* * видеокамер* * @VideoThings } *
        | * { ремонт* * видеокамер* * домофон* } *
        | * { тормоз* * камер* * @VideoThings } *
        | * { тормоз* * камер* * домофон* } *
        | * { тормоз* * видеокамер* * @VideoThings } *
        | * { тормоз* * видеокамер* * домофон* } *
        | * { украл* * камер* * @VideoThings } *
        | * { украл* * камер* * домофон* } *
        | * { украл* * видеокамер* * @VideoThings } *
        | * { украл* * видеокамер* * домофон* } *
        | * { урывк* * камер* * @VideoThings } *
        | * { урывк* * камер* * домофон* } *
        | * { урывк* * видеокамер* * @VideoThings } *
        | * { урывк* * видеокамер* * домофон* } *
        | * { сколько * стоит * камер* * @VideoThings } *
        | * { сколько * стоит * видеокамер* * @VideoThings } *
        | * { сколько * стоят * камер* * @VideoThings } *
        | * { сколько * стоят * видеокамер* * @VideoThings } *
        | * { сколько * стоит * @VideoThings } *
        | * { сломал* микрофон * камер* * @VideoThings } *
        | * { сломал* микрофон * камер* * домофон* } *
        | * { сломал* микрофон * видеокамер* * @VideoThings } *
        | * { сломал* микрофон * видеокамер* * домофон* } *
        | * { услуг* * @VideoThings } *
        | * когда * будет * @VideoThings *
        | * когда * заработает * @VideoThings *
        | * когда * *ладят * @VideoThings *
        | * когда * сдела* * @VideoThings *
        | * когда * включит* * @VideoThings *
        | * когда * возобнов* * @VideoThings *
        | * когда * включ* * @VideoThings * 
        | * через сколько * будет * @VideoThings *
        | * через сколько * заработает * @VideoThings *
        | * через сколько * *ладят * @VideoThings *
        | * через сколько * сдела* * @VideoThings *
        | * через сколько * включит* * @VideoThings *
        | * через сколько * возобнов* * @VideoThings *
        | * через сколько * включ* * @VideoThings * )
        
    $460_ServiceRequestInfo = ( * { *менять * @Personal } *
        | * { *менить * @Personal } *
        | * { *меняйте * @Personal } *
        | * { *мените * @Personal } *
        | * { визит* * @Personal } *
        | * { приход* * @Personal } *
        | * { посещен* * @Personal } *
        | * { время * @Personal } *
        | * { встреч* * @Personal } *
        | * { выезд* * @Personal } *
        | * { вызов* * @Personal * } *
        | * { вызыв* * @Personal * } *
        | * { вызва* * @Personal * } *
        | * { где * @Personal } *
        | * { через сколько * @Personal } *
        | * { через сколько * @Personal } *
        | * { ждем * @Personal } *
        | * { ждать * @Personal } *
        | * { ожида* * @Personal } *
        | * { ждал* * @Personal } *
        | * { жду * @Personal } *
        | * { заказ* * @Personal } *
        | * { запрос* * @Personal } *
        | * { закрыт* * заказ* * @Personal } *
        | * { закрыт* * запрос* * @Personal } *
        | * { закрыв* * заказ* * @Personal } *
        | * { закрыв* * запрос* * @Personal } *
        | * { заявк* * @Personal } *
        | * { заявочк* * @Personal } *
        | * { сервисн* заявк* * @Personal } *
        | * { СЗ * @Personal } *
        | * { (нет/нету) * @Personal } *
        | * { отмен* * @Personal } *
        | * { перенес* * @Personal } *
        | * { перенос* * @Personal } *
        | * { приезд* * @Personal } *
        | * { подойд* * @Personal } *
        | * { подойт* * @Personal } *
        | * { подъед* * @Personal } *
        | * { подъеха* * @Personal } *
        | * { приезж* * @Personal } *
        | * { приех* * @Personal } *
        | * { пришел* * @Personal } *
        | * { приед* * @Personal } *
        | * { прийти * @Personal } *
        | * { стоит* * визит* * @Personal } *
        | * { стоит* * приход* * @Personal } *
        | * { стоит* * встреч* * @Personal } *
        | * { стоим* * визит* * @Personal } *
        | * { стоим* * приход* * @Personal } *
        | * { стоим* * встреч* * @Personal } *
        | * { стоит* * заказ* * @Personal } *
        | * { стоит* * запрос* * @Personal } *
        | * { стоит* * выезд* * @Personal } *
        | * { стоим* * заказ* * @Personal } *
        | * { стоим* * запрос* * @Personal } *
        | * { стоим* * выезд* * @Personal } *
        | * { стоит* * заявк* * @Personal} *
        | * { стоит* * заявочк* * @Personal} *
        | * { стоим* * заявк* * @Personal} *
        | * { стоим* * заявочк* * @Personal} *
        | * { @duckling.date * будет * @Personal } *
        | * { @duckling.date * придет * @Personal } *
        | * { @duckling.date * придет * @Personal } *
        | * { @duckling.date * заявк* * @Personal } *
        | * { @duckling.date * заявочк* * @Personal } *
        | * { @duckling.date * СЗ * @Personal } *
        | * { @duckling.date * сервисн* заявк* * @Personal } *
        | * { отмен* * визит* * @Personal } *
        | * { отмен* * приход* * @Personal } *
        | * { отмен* * время * @Personal } *
        | * { отмен* * встреч* * @Personal } *
        | * { отмен* * вызов* } * 
        | * { блокиров* * визит* * @Personal } *
        | * { блокиров* * приход* * @Personal } *
        | * { блокиров* * время * @Personal } *
        | * { блокиров* * встреч* * @Personal } *
        | * { отмен* * заказ* * @Personal } *
        | * { отмен* * запрос* * @Personal } *
        | * { отмен* * выезд* * @Personal } *
        | * { блокиров* * заказ* * @Personal } *
        | * { блокиров* * запрос* * @Personal } *
        | * { блокиров* * выезд* * @Personal } *
        | * { время * визит* * @Personal } *
        | * { время * приход* * @Personal } *
        | * { время * встреч* * @Personal } *
        | * { время * выезд* * @Personal } *
        | * { выполн* * визит* * @Personal } *
        | * { выполн* * приход* * @Personal } *
        | * { выполн* * время * @Personal } *
        | * { выполн* * встреч* * @Personal } *
        | * { выполн* * выезд* * @Personal } *
        | * { заблокиров* * визит* * @Personal } *
        | * { заблокиров* * приход* * @Personal } *
        | * { заблокиров* * время * @Personal } *
        | * { заблокиров* * встреч* * @Personal } *
        | * { заблокиров* * заказ* * @Personal } *
        | * { заблокиров* * запрос* * @Personal } *
        | * { заблокиров* * выезд* * @Personal } *
        | * { задерж* * @Personal } *
        | * { заказ* * визит* * @Personal } *
        | * { заказ* * приход* * @Personal } *
        | * { заказ* * время * @Personal } *
        | * { заказ* * встреч* * @Personal } *
        | * { заказ* * выезд* * @Personal } *
        | * { заявк* * визит* * @Personal } *
        | * { заявк* * приход* * @Personal } *
        | * { заявк* * время * @Personal } *
        | * { заявк* * встреч* * @Personal } *
        | * { исполн* * заказ* * @Personal } *
        | * { исполн* * запрос* * @Personal } *
        | * { назнач* * визит* * @Personal } *
        | * { назнач* * приход* * @Personal } *
        | * { назнач* * время * @Personal } *
        | * { назнач* * встреч* * @Personal } *
        | * { назнач* * заказ* * @Personal } *
        | * { назнач* * запрос* * @Personal } *
        | * { назнач* * выезд* * @Personal } *
        | * { не (@Need/@Want) * @Personal } *
        | * { не (был/было) * @Personal } *
        | * { не подойд* * @Personal } *
        | * { не подош* * @Personal } *
        | * { не подъед* * @Personal } *
        | * { не подъеха* * @Personal } *
        | * { не (появлялся/появился) * @Personal } *
        | * { не приезж* * @Personal } *
        | * { не приех* * @Personal } *
        | * { не пришел* * @Personal } *
        | * { не приед* * @Personal } *
        | * { не прийти * @Personal } *
        | * { не приход* * @Personal } *
        | * { не вызывал* * @Personal } *
        | * { не отправил* * @Personal } *
        | * { отзови* * визит* * @Personal } *
        | * { отзови* * приход* * @Personal } *
        | * { отзови* * время * @Personal } *
        | * { отзови* * встреч* * @Personal } *
        | * { отзови* * заказ* * @Personal } *
        | * { отзови* * запрос* * @Personal } *
        | * { отзови* * выезд* * @Personal } *
        | * { отзыв* * визит* * @Personal } *
        | * { отзыв* * приход* * @Personal } *
        | * { отзыв* * время * @Personal } *
        | * { отзыв* * встреч* * @Personal } *
        | * { отзыв* * заказ* * @Personal } *
        | * { отзыв* * запрос* * @Personal } *
        | * { отзыв* * выезд* * @Personal } *
        | * { открыт* * визит* * @Personal } *
        | * { открыт* * приход* * @Personal } *
        | * { открыт* * время * @Personal } *
        | * { открыт* * встреч* * @Personal } *
        | * { открыт* * заказ* * @Personal } *
        | * { открыт* * запрос* * @Personal } *
        | * { открыт* * выезд* * @Personal } *
        | * { отозв* * визит* * @Personal } *
        | * { отозв* * приход* * @Personal } *
        | * { отозв* * время * @Personal } *
        | * { отозв* * встреч* * @Personal } *
        | * { отозв* * заказ* * @Personal } *
        | * { отозв* * запрос* * @Personal } *
        | * { отозв* * выезд* * @Personal } *
        | * { перенес* * визит* * @Personal } *
        | * { перенес* * приход* * @Personal } *
        | * { перенес* * время * @Personal } *
        | * { перенес* * встреч* * @Personal } *
        | * { перенес* * заказ* * @Personal } *
        | * { перенес* * запрос* * @Personal } *
        | * { перенес* * выезд* * @Personal } *
        | * { перенос* * визит* * @Personal } *
        | * { перенос* * приход* * @Personal } *
        | * { перенос* * время * @Personal } *
        | * { перенос* * встреч* * @Personal } *
        | * { перенос* * заказ* * @Personal } *
        | * { перенос* * запрос* * @Personal } *
        | * { перенос* * выезд* * @Personal } *
        | * { провер* * визит* * @Personal } *
        | * { провер* * приход* * @Personal } *
        | * { провер* * время * @Personal } *
        | * { провер* * встреч* * @Personal } *
        | * { провер* * заказ* * @Personal } *
        | * { провер* * запрос* * @Personal } *
        | * { провер* * выезд* * @Personal } *
        | * { сегодня * будет * @Personal } *
        | * { сегодня * придет * @Personal } *
        | * { сними* * визит* * @Personal } *
        | * { сними* * приход* * @Personal } *
        | * { сними* * время * @Personal } *
        | * { сними* * встреч* * @Personal } *
        | * { сними* * заказ* * @Personal } *
        | * { сними* * запрос* * @Personal } *
        | * { сними* * выезд* * @Personal } *
        | * { созда* * визит* * @Personal } *
        | * { созда* * приход* * @Personal } *
        | * { созда* * время * @Personal } *
        | * { созда* * встреч* * @Personal } *
        | * { созда* * заказ* * @Personal } *
        | * { созда* * запрос* * @Personal } *
        | * { созда* * выезд* * @Personal } *
        | * { статус* * визит* * @Personal } *
        | * { статус* * приход* * @Personal } *
        | * { статус* * время * @Personal } *
        | * { статус* * встреч* * @Personal } *
        | * { статус* * заказ* * @Personal } *
        | * { статус* * запрос* * @Personal } *
        | * { статус* * выезд* * @Personal } *
        | * { убери* * визит* * @Personal } *
        | * { убери* * приход* * @Personal } *
        | * { убери* * время * @Personal } *
        | * { убери* * встреч* * @Personal } *
        | * { убери* * заказ* * @Personal } *
        | * { убери* * запрос* * @Personal } *
        | * { убери* * выезд* * @Personal } *
        | * { убрать * визит* * @Personal } *
        | * { убрать * приход* * @Personal } *
        | * { убрать * время * @Personal } *
        | * { убрать * встреч* * @Personal } *
        | * { убрать * заказ* * @Personal } *
        | * { убрать * запрос* * @Personal } *
        | * { убрать * выезд* * @Personal } *
        | * { узнать * визит* * @Personal } *
        | * { узнать * приход* * @Personal } *
        | * { узнать * время * @Personal } *
        | * { узнать * встреч* * @Personal } *
        | * { узнать * заказ* * @Personal } *
        | * { узнать * запрос* * @Personal } *
        | * { узнать * выезд* * @Personal } *
        | * { уточнить * визит* * @Personal } *
        | * { уточнить * приход* * @Personal } *
        | * { уточнить * время * @Personal } *
        | * { уточнить * встреч* * @Personal } *
        | * { уточнить * заказ* * @Personal } *
        | * { уточнить * запрос* * @Personal } *
        | * { уточнить * выезд* * @Personal } *
        | * { шаг* * визит* * @Personal } *
        | * { шаг* * приход* * @Personal } *
        | * { шаг* * время * @Personal } *
        | * { шаг* * встреч* * @Personal } *
        | * { шаг* * заказ* * @Personal } *
        | * { шаг* * запрос* * @Personal } *
        | * { шаг* * выезд* * @Personal } *
        | * { заявк* * восстанов* * @AllServices } * 
        | * {по повод* * заявк* } * 
        | * {вопрос* * (по/о/об/с/за) * заявк* } * 
        | * {интерес* * заявк* } * )
        
    $740_Pretension = ( * { претенз* * @Services } *
        | * { претенз* * @Payment } *
        | * { претенз* * @DomRu } *
        | * { жалоб* * @Payment } *
        | * { жалоб* * @Services } *
        | * { жалоб* * @DomRu } *  ) 
        
    $745_Thanks = ( * *благодар* *   
        | * {оцен* * отлич*} *
        | * {оцен* * хорош*} *
        | * *хвал* *
        | * наград* *
        | * низкий поклон * 
        | * спасибо [за] ~помощь * )
        
    $agentRequest = (* { приглас* * @Operator } *
        |* { приглас* * бухгалтер* } *
        |* { приглас* * работник* } *
        |* { приглас* * людей } *
        |* { приглас* * сотрудник* } *
        |* { приглаш* * @Operator } *
        |* { приглаш* * бухгалтер* } *
        |* { приглаш* * работник* } *
        |* { приглаш* * людей } *
        |* { приглаш* * сотрудник* } *
        |* { *зови * @Operator } *
        |* { *зови * бухгалтер* } *
        |* { *зови * работник* } *
        |* { *зови * людей } *
        |* { *зови * сотрудник* } *
        |* { *зовите * @Operator } *
        |* { *зовите * бухгалтер* } *
        |* { *зовите * работник* } *
        |* { *зовите * людей } *
        |* { *зовите * сотрудник* } *
        |* { *звать * @Operator } *
        |* { *звать * бухгалтер* } *
        |* { *звать * работник* } *
        |* { *звать * людей } *
        |* { *звать * сотрудник* } *
        |* { *зовешь * @Operator } *
        |* { *зовешь * бухгалтер* } *
        |* { *зовешь * работник* } *
        |* { *зовешь * людей } *
        |* { *зовешь * сотрудник* } *
        |* { соедин* * @Operator } *
        |* { соедин* * бухгалтер* } *
        |* { соедин* * работник* } *
        |* { соедин* * людей } *
        |* { соедин* * сотрудник* } *
        |* { перевед* * @Operator } *
        |* { перевед* * бухгалтер* } *
        |* { перевед* * работник* } *
        |* { перевед* * людей } *
        |* { перевед* * сотрудник* } *
        |* { пообща* * @Operator } *
        |* { пообща* * бухгалтер* } *
        |* { пообща* * работник* } *
        |* { пообща* * людей } *
        |* { пообща* * сотрудник* } *
        |* { свяж* * @Operator } *
        |* { свяж* * бухгалтер* } *
        |* { свяж* * работник* } *
        |* { свяж* * людей } *
        |* { свяж* * сотрудник* } *
        |* { связ* * @Operator } *
        |* { связ* * бухгалтер* } *
        |* { связ* * работник* } *
        |* { связ* * людей } *
        |* { связ* * сотрудник* } *
        |* { ~помощь * @Operator } *
        |* { ~помощь * бухгалтер* } *
        |* { ~помощь * работник* } *
        |* { ~помощь * людей } *
        |* { ~помощь * сотрудник* } *
        | ~помощь
        |* { ~консультация * @Operator } *
        |* { ~консультация * бухгалтер* } *
        |* { ~консультация * работник* } *
        |* { ~консультация * людей } *
        |* { ~консультация * сотрудник* } *
        |* { ~перевод * @Operator } *
        |* { ~перевод * бухгалтер* } *
        |* { ~перевод * работник* } *
        |* { ~перевод * людей } *
        |* { ~перевод * сотрудник* } *
        |* { @Modal @Operator } *
        |* { срочно @Operator } *
        |* { [@Modal] бухгалтер* } *
        |* { [срочно] бухгалтер* } *
        |* { [@Modal] сотрудник* } *
        |* { [срочно] сотрудник* } *
        |* жив* * @Operator *
        |* жив* * бухгалтер* *
        |* жив* * работник* *
        |* жив* * ~люди *
        |* жив* * сотрудник*
        |* главн* * @Operator *
        |* главн* * бухгалтер* *
        |* главн* * работник* *
        |* главн* * ~люди *
        |* главн* * сотрудник*
        |* старш* * @Operator *
        |* старш* * бухгалтер* *
        |* старш* * работник* *
        |* старш* * сотрудник*
        |* человеческ* * @Operator *
        |* человеческ* * работник* *
        |* обычн* * @Operator *
        |* обычн* * бухгалтер* *
        |* обычн* * работник* *
        |* обычн* * ~люди *
        |* обычн* * сотрудник*
        |* обыкновенн* * @Operator *
        |* обыкновенн* * бухгалтер* *
        |* обыкновенн* * работник* *
        |* обыкновенн* * ~люди *
        |* обыкновенн* * сотрудник*
        |* свой @Operator *
        |* своего @Operator *
        |* ваш* @Operator *
        |* @Operator @DomRu *
        |* @Operator * [отдел* (продаж*/подключ*) $weight<-1>] *
        |* сотрудник* @DomRu *
        |* { подключ* * @Operator } *
        |* подключ* * [(тариф*/пакет*) $weight<-1>] * сотрудник* *
        |* [(тариф*/пакет*) $weight<-1>] * сотрудник* * подключ* *
        |* { подключ* * бухгалтер* } *
        |* { подключ* * работник* } *
        |* { [есть] * кто * живой } *
        |* соедин* * { абонентск* отдел* } *
        |* соедин* * { абон отдел* } *
        |* соедин* * { бухгалтерск* отдел* } *
        |* соедин* * { абонентск* служб* } *
        |* соедин* * { абон служб* } *
        |* соедин* * { бухгалтерск* служб* } *
        |* не @Modal * *говаривать * $bot *
        |* не @Modal * говор* * $bot *
        |* не @Modal * разговор* * $bot *
        |* не @Modal * беседовать * $bot *
        |* не @Modal * болтать * $bot *
        |* не @Modal * базарить * $bot *
        |* не @Modal * общаться * $bot *
        |* не @Modal * объяснять * $bot *
        |* не @Modal * продолжать * $bot *
        |* { *консультироваться * людьми } *
        |* { *консультироваться * человек* } *
        |* { консультаци* * специалист* } *
        |* { со спецом человеческим соедините } *
        |* { оператор * @Modal } *
        |* { @Modal * соединени* работник* * @DomRu } *
        |* { позови* коллег* } *
        |* { переведи* * на * агент* } *
        |* { соедини* * со своим * начальник* } *
        |* { приглашени* старш* специалист* } *
        |* { помощ* специалист* } *
        |* { *зови* главного } *
        |* { пригласи* живого человек* } *
        |* { запрос на соединени* с оператором } *
        |* { переключи* * на * оператор* } *
        |* { горяч* лини* } *
        |* не буду * *говаривать * $bot *
        |* не буду * говор* * $bot *
        |* не буду * разговор* * $bot *
        |* не буду * беседовать * $bot *
        |* не буду * болтать * $bot *
        |* не буду * базарить * $bot *
        |* не буду * общаться * $bot *
        |* не буду * объяснять * $bot *
        |* не буду * продолжать * $bot *
        |* не будем * *говаривать * $bot *
        |* не будем * говор* * $bot *
        |* не будем * разговор* * $bot *
        |* не будем * беседовать * $bot *
        |* не будем * болтать * $bot *
        |* не будем * базарить * $bot *
        |* не будем * общаться * $bot *
        |* не будем * объяснять * $bot *
        |* не будем * продолжать * $bot *)
        
    $otherIntent = ( * { @Know * статус* * @SynonymsForAgreement } * 
        | * {друг* вопрос*} * 
        | * {друг* интент*} * )    
    
    $noMatch = ( @duckling.phone-number 
        | не помогл*
        | {как (отключ*/отмен*)}
        | {личн* вопрос*} 
        | (срочно/быстро) )
    
    $commonYes = ($yes| обязательно | непременно | а как же | жа
        | подтвержда*
        | (совершенно/абсолютно) точно
        | пожалуй
        | норм
        | (почему/что/че) бы и нет
        | было [бы] (неплохо|не плохо)
        | [очень] @Want
        | ладно
        | хорошо
        | ладно договорились
        | валяй*
        | естественно
        | разумеется
        | (еще|ещё) как
        | не (против|возражаю)
        | только за
        | безусловн*
        | все (так|верно)
        | (совершенно|абсолютно) верно
        | (давай|давайте|логично)
        | (конечно|конешно|канешна)
        | правильно
        | так точно )  
        
    $commonNo = ( $no | да ну | да не | нт
        | да нет [всё]
        | да нет [наверное]
        | да нет [ясно]
        | да [наверное] нет
        | ничего
        | { $no (конечно/конешно/канешна/спасибо/пока)}
        | не сейчас
        | ни капли
        | отнюдь
        | нискол*
        | [всё] не (@Want/@Need/думаю/нравится/стоит/буду/считаю/согла*/подтв*)
        | [всё] ни (@Want/@Need/думаю/нравится/стоит/буду/считаю/согла*/подтв*)
        | ненадо
        | нельзя
        | нехочу
        | ненавижу
        | невозможно
        | ни за что
        | против
        | вряд ли
        | сомневаюсь
        | нихрена
        | нихера
        | неправильно
        | неверно
        | ненужно
        | отказ*
        | ни в коем случае
        | ответ $no
        | {ответ отрицательный}
        | неа
        | нет всё ясно
        | нет не так
        | нифига 
        | никак нет
        | ни как нет 
        | не как нет)
        
    $yesOperator = ({$yes соедин*}
        | {$yes перевед*}
        | {$yes свяж*}
        | {$yes связ*}
        | {$yes *зови}
        | {$yes *зовите}
        | {$yes *звать}
        | {$yes *зовешь}
        | {$yes приглас*}
        | {$yes приглаш*}
        | {$yes срочно}
        | {$yes @Operator}
        | {$yes ~люди}
        | {$yes сотрудник*}
        | {$yes коллег*}
        | {$yes *консультироваться}
        | {$yes позови*}
        | {$yes переключи*} ) 
        
    $noOperator = (не * [@Need] * соедин*
        | не * [@Need] * перевед*
        | не * [@Need] * свяж*
        | не * [@Need] * связ*
        | не * [@Need] * *зови
        | не * [@Need] * *зовите
        | не * [@Need] * *звать
        | не * [@Need] * *зовешь
        | не * [@Need] * приглас*
        | не * [@Need] * приглаш*
        | не * [@Need] * переключа*
        | не * @Need * @Operator  
        | не * @Need * ~люди
        | не * @Need * сотрудник*
        | не * @Need * коллег* )
        
    $yesAtHome = ( нахожусь
        | дома
        | квартир*/комнат*
        | готов*
        | {есть возможност*}
        | обязательно 
        | непременно 
        | а как же
        | (еще/ещё) как
        | все (так/верно)
        | (совершенно/абсолютно) верно
        | логично
        | (конечно/конешно/канешна)
        | так точно)  
        
    $noAtHome = ( не нахожусь
        | не дома
        | не в квартир*
        | не готов*
        | {нет возможност*}
        | не в комнат* 
        | да не
        | { $no конечно}
        | { $no конешно}
        | { $no канешна}
        | { $no спасибо}
        | { $no пока}
        | нифига)
        
    $yesAccess = ({$yes восстановил*}
        | {$yes появил*}
        | {$yes доступ*}
        | {есть доступ*}
        | {$yes услуг*}
        | {(все/всё) работает}
        | {услуг* работает}
        | {услуг* работают}
        | {услуг* заработал*}
        | {(все/всё) восстановилось}
        | {услуг* восстановил*}
        | {доступ* восстановил*}
        | {@AllServices восстановил*}
        | {услуг* появил*}
        | {доступ* появил*}
        | {(все/всё) заработало}
        | {доступ* работает}
        | {доступ* работают}
        | {доступ* заработал*}
        | {@AllServices работает}
        | {@AllServices заработал*}
        | {@AllServices появил*}
        | есть
        | появил*
        | работает
        | работают
        | заработал*
        | восстановил*         
        | {[спасибо/спс] работает [работает]}
        | {[спасибо/спс] заработало [работает]}
        | {[спасибо/спс] получилось [работает]}
        | {[спасибо/спс] да [работает]}
        | {[спасибо/спс] запустилось [работает]}
        | {[все/всё] корректно}
        | {[все/всё] получилось}
        | {[все/всё] заработало}
        | {[$yes] * помогл*})
        
    $noAccess = ({$no не восстановил*}
        | {$no не появил*}
        | {$no доступ*}
        | не доступ*
        | {$no услуг*}
        | {все не работает}
        | {всё не работает}
        | {ничего не работает}
        | {нихрена не работает}
        | {нихера не работает}
        | {нифига не работает}       
        | {услуг* не работает}
        | {услуг* не работают}
        | {услуг* не заработал*}
        | {все не восстановилось}
        | {всё не восстановилось}
        | {ничего не восстановилось}
        | {нихрена не восстановилось}
        | {нихера не восстановилось}
        | {нифига не восстановилось}
        | {услуг* не восстановил*}
        | {доступ* не восстановил*}
        | {@AllServices не восстановил*}
        | {услуг* не появил*}
        | {доступ* не появил*}
        | {все не заработало}
        | {всё не заработало}
        | {ничего не заработало}
        | {нихрена не заработало}
        | {нихера не заработало}
        | {нифига не заработало}
        | {доступ* не работает}
        | {доступ* не работают}
        | {доступ* не заработал*}
        | {@AllServices не работает}
        | {@AllServices не работают}
        | {@AllServices не заработал*}
        | {@AllServices не появил*}
        | нету
        | не появил*
        | не работает
        | не работают
        | не заработал*
        | не восстановил*
        | не корректно
        | {[$no] * не помогл* }
        | {ничего не помогл*}
        | {нихрена не помогл*}
        | {нихера не помогл*}
        | {нифига не помогл*} 
        | {работает некорректно}
        | {работает неправильно}
        | {работает неверно}
        | {работает не как раньше}
        | {работает не так}
        | {работает * ошиб*}
        | {работает * плохо}
        | [есть] проблем* )
        
    $yesForPromisedPayment = ( { [$yes] подключ* }
        | { [$yes] подключай* }
        | { подключ* услуг* }
        | { подключ* @PromisedPayment }
        | { $yes @PromisedPayment }
        | { @Modal @PromisedPayment }
        | [$yes] @Modal
        | [$yes] @Give
        | интересно )
        
    $noForPromisedPayment = ( [$no] не подключ*
        | не подключ* услуг*
        | не подключ* @PromisedPayment
        | не @Modal @PromisedPayment 
        | не @Give
        | (неинтересно/не интересно) 
        | [$no] ни подключ*
        | ни подключ* услуг*
        | ни подключ* @PromisedPayment
        | ни @Modal @PromisedPayment 
        | ни @Give
        | (ниинтересно/ни интересно) )
        
    $yesForPaymentMethods = ( { [$yes] *скажи* }
        | { [$yes] *сказа* }
        | { [$yes] *говори* }
        | { [$yes] *назови* }
        | { [$yes] *назыв* }
        | { какие есть }
        | { $yes * способ* *плат* } )
        
    $noForPaymentMethods = ( не *скажи*
        | не *сказа*
        | не *говори*
        | не *назови*
        | не *назыв*
        | { $no * способ* *плат* }
        | не рассказыв*
        | не рассказыв* )
        
    $yesQuestions = ( [ну] [конечно|вроде|пожалуй|возможно] (да|даа|lf|ага|агась|точно|угу|верно|ок|ok|окей|окай|okay|оке|именно|подтвержд*|йес) [да|конечно|конешно|канешна|вроде|пожалуй|возможно]
        | (остались/осталось/осталс*)
        | { есть вопрос* }
        | да вопрос*
        | { вопрос* * (имею/имеем) }
        | { вопрос* * (имеются/имеется) }
        | { вопрос* (остались/осталось/осталс*) }
        | можете
        | можешь
        | помоги*
        | { @Need помощь }
        | обязательно 
        | непременно 
        | а как же
        | (совершенно/абсолютно) точно
        | пожалуй
        | (почему/что/че) бы и нет
        | было [бы] (неплохо/не плохо)
        | [очень] @Want
        | ладно
        | хорошо
        | ладно договорились
        | валяй*
        | естественно
        | разумеется
        | (еще|ещё) как
        | не (против|возражаю)
        | только за
        | безусловн*
        | все (так|верно)
        | (совершенно|абсолютно) верно
        | (давай|давайте|логично)
        | (конечно|конешно|канешна)
        | правильно
        | так точно )
    
    $noQuestions = ( $no 
        | не (остались/осталось/осталс*)
        | { нет вопрос* }
        | (никакого/никаких)
        | спасибо [всё] [понятно]
        | { вопрос* * не (имею/имеем)}
        | { вопрос* * не (имеются/имеется)}
        | { вопрос* не (остались/осталось/осталс*) }
        | { больше нет }
        | { больше нету }
        | не можете 
        | не можешь
        | { не @Need помощь }
        | да ну 
        | да не
        | да нет [всё]
        | да нет [наверное]
        | да нет [ясно]
        | да [наверное] нет
        | { $no (конечно/конешно/канешна/спасибо/пока)}
        | не сейчас
        | отнюдь
        | нискол*
        | [всё] не (@Want/@Need/думаю/нравится/стоит/согла*)
        | [всё] ни (@Want/@Need/думаю/нравится/стоит/согла*)
        | ненадо
        | нехочу
        | ни за что
        | против
        | вряд ли
        | сомневаюсь
        | ненужно
        | отказ*
        | ни в коем случае
        | ответ $no
        | {ответ отрицательный}
        | неа
        | нет всё ясно
        | никак нет
        | ни как нет
        | не как нет
        | ничем
        | ни чем
        | не поможете
        | не помогли)
    
    $helloAdvanced = ( $hello 
        | ghbdtn 
        | plhfdcndeqnt 
        | ghbdtn* [ghbdtn]
        | ghbdttn
        | ghbdtnbr
        | ghbdtnbrb
        | ghbdtl
        | plhfdcndeq
        | plhfdcndeq*
        | plhfcmnt
        | plhfcnb
        | plfhjd
        | plfhjdf
        | plfhjdtymrb
        | pljhjdtymrb
        | plhfcnt
        | cfk.n
        | ghbdtncnde.
        | ~lj,hsq ~ltym
        | ~lj,hsq ~dtxth
        | lj,hjuj dhtvtyb cenjr
        | plhfcndeq* )
        
    $byeAdvanced = ( $bye
        | [(kflyj|dct|lfdfq|ye)] gjrf gjrf
        | (lj|lf) (cdblfy*|dcnhtxb|pfdnhf|crjhjuj) cdblfybz
        | (lj|lf) crjhjq dcnhtxb
        | ljcdblfyb*
        | ljcdbljc
        | ljpfdnhf
        | ghjofq
        | gjrtljdf
        | gjrtlf
        | gjrfcbrb
        | dctuj lj,hjuj )  