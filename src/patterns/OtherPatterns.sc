patterns:

    $potentialClientDevice = (* { @Modal * купить * @Equipment } *
        | * { @Modal * покупк* * @Equipment } *
        | * { @Modal * купить * @InternetEquipment } *
        | * { @Modal * покупк* * @InternetEquipment } *
        | * { @Modal * купить * @TVEquipment } *
        | * { @Modal * покупк* * @TVEquipment } *
        | * { @Modal * купить * @YandexAlisaEquipment } *
        | * { @Modal * покупк* * @YandexAlisaEquipment } *
        | * { сколько * (стоит*/стоят) * @YandexAlisaEquipment } *
        | * { сколько * (стоит*/стоят) * @Equipment } *
        | * { сколько * (стоит*/стоят) * @TVEquipment } *
        | * { сколько * (стоит*/стоят) * @InternetEquipment } *
        | * { подключени* * @YandexAlisaEquipment } *
        | * { подключени* * @Equipment } *
        | * { подключени* * @TVEquipment } *
        | * { подключени* * @InternetEquipment } *
        | * { подключи* * (нов*/лучше) * @YandexAlisaEquipment } *
        | * { подключи* * (нов*/лучше) * @Equipment } *
        | * { подключи* * (нов*/лучше) * @TVEquipment } *
        | * { подключи* * (нов*/лучше) * @InternetEquipment } *
        | * [@Want] * { @Know * (нов*/лучше) * @YandexAlisaEquipment } *
        | * [@Want] * { @Know * (нов*/лучше) * @Equipment } *
        | * [@Want] * { @Know * (нов*/лучше) * @TVEquipment } *
        | * [@Want] * { @Know * нов* * @InternetEquipment } *
        | * [@Want] * { @Know * лучше * @InternetEquipment } *
        | * { @Modal * подключени* * @YandexAlisaEquipment } *
        | * { @Modal * подключени* * @Equipment } *
        | * { @Modal * подключени* * @TVEquipment } *
        | * { @Modal * подключени* * @InternetEquipment } *
        | * { @Modal * подключи* * [нов*/лучше] * @YandexAlisaEquipment } *
        | * { @Modal * подключи* * [нов*/лучше] * @Equipment } *
        | * { @Modal * подключи* * [нов*/лучше] * @TVEquipment } *
        | * { @Modal * подключи* * [нов*/лучше] * @InternetEquipment } *
        | * { @Modal * @Change * @InternetEquipment * на ваш } *
        | * { @Modal * замен* * @InternetEquipment * на ваш } *
        | * { вопрос* * подключени* * @YandexAlisaEquipment } *
        | * { вопрос* * подключени* * @Equipment } *
        | * { вопрос* * подключени* * @TVEquipment } *
        | * { вопрос* * подключени* * @InternetEquipment } *
        | * { консультац* * подключени* * @YandexAlisaEquipment } *
        | * { консультац* * подключени* * @Equipment } *
        | * { консультац* * подключени* * @TVEquipment } *
        | * { консультац* * подключени* * @InternetEquipment } *
        | * { подсоедини* * (нов*/лучше) * @YandexAlisaEquipment } *
        | * { подсоедини* * (нов*/лучше) * @Equipment } *
        | * { подсоедини* * (нов*/лучше) * @TVEquipment } *
        | * { подсоедини* * (нов*/лучше) * @InternetEquipment } *
        | * { установ* * лучше * @YandexAlisaEquipment } *
        | * { установ* * лучше * @Equipment } *
        | * { установ* * лучше * @TVEquipment } *
        | * { установ* * лучше * @InternetEquipment } *
        | * { (@Need/@Want) @YandexAlisaEquipment } *
        | * { (@Need/@Want) @Equipment } *
        | * { (@Need/@Want) @TVEquipment } *
        | * { (@Need/@Want) @InternetEquipment } * 
        | * { давно * @Want * @YandexAlisaEquipment } *
        | * { давно * @Want * @Equipment } *
        | * { давно * @Want * @TVEquipment } *
        | * { давно * @Want * @InternetEquipment } * 
        | * { обновить * @YandexAlisaEquipment } *
        | * { обновить * @Equipment } *
        | * { обновить * @TVEquipment } *
        | * { обновить * @InternetEquipment } * 
        | * {(~купить/покуп*/приобр*) * (роутер*/обурудовани*)}
        | * {хочу роутер [$oneWord] [себе]} * )

    $client = (клиент*|абонент*|пользовател*)

    $arenda = (арендатор*|владелец*|владельц*|гост*|должник*|жилец*|квартирант*|постоялец*|постояльц*|предпринимател*|хозяин*|хозяйк*|собственник*)

    $people = ($client|$relatives|$friend)

    $potentialClientService = ( * { @Request * на * подключен* * [@AllServices] } *
        | * { оставлял* * @Request } * { @Modal подключ* } * @duckling.date *
        | { @Need услуг* }
        | * { стать * $client  * ваш* @Company} *
        | * { поддерживает* * @DomRu  * @Place} *
        | * { подключен* * нов* * $client } *
        | * { открыть * нов* * @SynonymsForAgreement } *
        | * { открыть * нов* * услуг* } *
        | * { оформ* * ещё * @SynonymsForAgreement } *
        | * { оформ* * ещё * услуг* } *
        | * { оформ* * ещё * @TVSet } *
        | * { подключить* * @SynonymsForAgreement } *
        | * { рассматрив* * ваш* @Company } *
        | * { оформлен* * @SynonymsForAgreement } *
        | * { оформит* * @SynonymsForAgreement } *
        | * { заводит* * @Cable } *
        | * { заведит* * @Cable } *
        | * { завести * @Cable } *
        | * { проводит* * @Cable } *
        | * { проведит* * @Cable } *
        | * { провести * @Cable } *
        | * { проведен* * @Cable } *
        | * { заведен* * @Cable } *
        | * { @Want * [бы] * подключитьcя } *
        | * { потенциальн* * $client } *
        | * { оформ* * нов* * @SynonymsForAgreement } *
        | * { оформ* * нов* * услуг* } *
        | * { стать * $client * ваш* @Company } *
        | * { провести * @AllServices } *
        | * { проводить * @AllServices } *
        | * { проведен* * @AllServices } *
        | * { стать * $client * @DomRu } *
        | * { добав* * нов* * @SynonymsForAgreement } *
        | * { добав* * нов* * услуг* } *
        | * { заключа* * нов* * @SynonymsForAgreement } *
        | * { заключа* * нов* * услуг* } *
        | * { @Modal * [бы] * проложи* * @Cable } *
        | * { выбрать* * @AllServices } *
        | * { стать * [ваш*]* $client } *
        | * { сдела* * ещё одно * подключен* } *
        | * { сдела* * ещё один * @SynonymsForAgreement } *
        | * { сдела* * ещё одну * услуг* } *
        | * { сдела* * ещё один * @TVSet } *
        | * { подключит* * ещё одно * подключен* } *
        | * { подключит* * ещё один * @SynonymsForAgreement } *
        | * { подключит* * ещё одну * услуг* } *
        | * { подключит* * ещё один * @TVSet } *
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
        | * { подключить* * ваш* @Company} *
        | * { @Modal * [бы] * завести * @Cable } *
        | * { завести * нов* * @SynonymsForAgreement } *
        | * { завести * нов* * услуг* } *
        | * { поставит* * @Internet } *
        | * { поставит* * @Television } *
        | * { поставит* * @Telephone } *
        | * { поставит* * услуг* } *
        | * { отдел* подключен* } *
        | * { @Operator отдел* продаж* } *
        | * { сотрудник* отдел* продаж* } *
        | * { *консульт* * @Operator * [вопрос*] подключен* } * 
        | * { *консульт* * @Operator * [@Request] подключен* } * 
        | * { @Need * [бы] * подключить* } *
        | * { подключить @Internet } *
        | * { подключить к @Internet } *
        | * { подключить к @Television } *
        | * { подключить к услугам } *
        | * { подключение @Internet } *
        | * { подключение @Television } *
        | * { подключить * @Internet } *
        | * { подключить * @Television } *
        | * { подключить * @Services } *
        | * { подключить * [домашн*] @Telephone } *
        | * { подключение * @Television } *
        | * { подключение * @Services } *
        | * { подключение * [домашн*] @Telephone } * 
        | * { возможно* * $regexp<подключ(ить|иться|ение|ения)> * ([по/в/на] @Place) } * 
        | * { возможно* * $regexp<подключ(ить|иться|ение|ения)> * @Internet } *
        | * { возможно* * $regexp<подключ(ить|иться|ение|ения)> * @Television } *
        | * { возможно* * $regexp<подключ(ить|иться|ение|ения)> * @Services } * 
        | * { возможно* * $regexp<подключ(ить|иться|ение|ения)> * [домашн*] @Telephone } *
        | * { $regexp<подключ(ить|ение)> * @Cable } * 
        | * { есть * @DomRu * @Place * или нет } * 
        | * { есть * подключени* * @Place * или нет } *
        | * { подключен * ли * @DomRu * @Place } * )

    $commonQuestion = ( @DomRu
        | * { (@Modal/@Give) * консультаци* } *
        | * { консультаци* * вашей @Company } *
        | * { консультаци* * @DomRu } *
        | * { консультаци* * @SynonymsForAgreement } *
        | * { консультаци* * @Services } *
        | * { (@Modal/@Give/есть) * вопрос* } *
        | * { вопрос* * вашей @Company } *
        | * { вопрос* * ваше* @DomRu } *
        | * { вопрос* * @SynonymsForAgreement } *
        | * { вопрос* * @Services } *
        | * { вопрос* * оказан* * @Services } *
        | * { @Need * информац* } *
        | * { @Need * помощь } *
        | * { @Need * подробност* } *
        | * { (помоги*/проконсультир*) [$pls] } *
        | * { @Need * подробност* } *
        | * { что ты умеешь } *
        | * { чему тебя *учили } *
        | { (ты/вы/это) * $bot }
        | { (я/мы) (звоним/звоню) }
        | { (что/чтобы) (звоним/звоню) }
        | { (я/мы/они) @Want [бы] }
        | { @Allo * (звоним/звоню) }
        | { (ты/вы/это) * виртуальн* сист* }
        | { (ты/вы/это) * виртуальн* ассистент* }
        | { (ты/вы/это) * виртуальн* помощ* }
        | { (ты/вы/это) * виртуальн* коллег* }
        | { (ты/вы/это) * виртуальн* сотрудн* }
        | * @Know *
        | * стар* вопрос* *
        | { [вопрос*] по договору } 
        | { по повод* договор* } )

    $callsOnHold = (* { (сможет*/может*) * @SynonymsForCall * продолжит* } *
        | * { @SynonymsForCall * (продолжен*/продолжил*/продолжит) } *
        | * { @SynonymsForCall * удержани* } *
        | * { @SynonymsForCall * через * время * продолжен* } *
        | * { @Talk * персональн* помощник* } *
        | * { @Talk * личн* помощник* } *
        | * { @Talk * бесплатн* помощник* } *
        | * голосов* сообщени* * звуков* сигнал* * 
        | * будете привлечены * административн* ответственност* *
        | * единый оператор *  )

    $legalEntity = ( * { @WiFi * @LegalEntity } *
        | * { данные * @LegalEntity } *
        | * { документ* * @LegalEntity } *
        | * { счет* * @LegalEntity } *
        | * { консультац* * @LegalEntity } *
        | * { вопрос* * @LegalEntity } *
        | * { помощ* * @LegalEntity } *
        | * { проблем* * @LegalEntity } *
        | * { жалоб* * @LegalEntity } *
        | * { сложност* * @LegalEntity } *
        | * { неполадк* * @LegalEntity } *
        | * { неисправност* * @LegalEntity } *
        | * { (@Tariff/@PersonalAccount) * @LegalEntity } *
        | * { (телефон*/номер*) * @LegalEntity } *
        | * { $techSupport * @LegalEntity } *
        | * { юридическ* * @Services } *
        | * { корпоративн* * @Services } *
        | * { бухгалтерск* * @Services } *
        | * { @AllServices * @LegalEntity } *
        | * { @SynonymsForAgreement * @LegalEntity } *
        | * { не работает * @Internet * @LegalEntity } *
        | * { @PromisedPayment * @LegalEntity * } *
        | * { ошибк* * @WiFi * @LegalEntity } *
        | * { ошибк* * @AllServices * @LegalEntity } *
        | * { подключ* * [@DomRu] * @LegalEntity * } *
        | * { стать $client * [@DomRu] * @LegalEntity * } *
        | * @LegalEntity *
        | * { ошибк* * подключ* * @LegalEntity } * 
        | * { подключ* * @AllServices } * офис* * 
        | * { подключ* * @AllServices } * отдел* *
        | * { подключ* * @AllServices } * фирм* *
        | * { подключ* * @AllServices } * гостиниц* *
        | * { подключ* * @AllServices } * магазин* *
        | * { подключ* * @AllServices } * (торгов*/бизнес* центр*) *
        | * { юридическ* * (отдел*/объект*) } *
        | * { корпоративн* * (отдел*/объект*) } *
        | * { бухгалтерск* * (отдел*/объект*) } *
        | * { бизнес* офис* } *
        | * { бизнес* (атээс/а тэ эс) } *
        | * { бизнес* @SynonymsForAgreement } *
        | * { облачн* офис* } *
        | * { облачн* (атээс/а тэ эс) } *
        | * { облачн* @SynonymsForAgreement } * 
        | * { зарегистрировать* * @PersonalAccount * @LegalEntity} * )

    $webCabCredentials = (* { (*регистрировать*/авторизовать*) * @PersonalAccount } *
        | * { (*сылать/отправ*/пошли*/*слать/направь*/направит*) * @Credentials } *
        | * { (@Credentials/данные) * для * (вход*/авторизац*/регистрац*) } *
        | * { (ввести/вводить) * @Credentials } *
        | * { (войти/входить/вход) * @PersonalAccount } *
        | * { (вопрос*/информаци*) * @Credentials } *
        | * { (восстанов*/восстанав*) * @Credentials } *
        | * { (забыл*/забыть/забыва*) * @Credentials } *
        | * { (залогинить*/зайти/заходить) * @PersonalAccount } *
        | * { (какой/какие/какая) * @Credentials } *
        | * { (кинуть/скинуть/кидайте/кинь*/скинь*/скидывай*) * [@SMS/сообщени*] * @Credentials } *
        | * { (@Change/@ChangePast) * @Credentials * [@WiFi $weight<-1>] } *
        | * { (не найти/не нашл*/не нашел*/не найден*/не находит*) * @Credentials } *
        | * { (не подход*) * @Credentials } *
        | * { (не совпада*/не совпал*) * @Credentials } *
        | * { (неправильн*/неверн*/несовпадающ*/ошибочн*) * @Credentials } *
        | * { ошибк* * @Credentials } *
        | * { (потеря*/утеря*) * @Credentials } *
        | * { (проблем*/сложност*) * @Credentials } *
        | * { @Credentials * (@SMS/сообщени*) } *
        | * { @Credentials * для * (@PersonalAccount/приложени*) } *
        | * { @Credentials * на * (сотовый/мобильный/телефон/почту/смартфон) } *
        | * { @Credentials @DomRu} *
        | * { @Give * @Credentials } *
        | * { вспомнить * @Credentials } *
        | * { зарегистрировать* * @PersonalAccount } *
        | * { не * (помню/*помни*/получа*/получи*/попасть/попад*/пускать/пускает) * @Credentials} *
        | * { (не @Successful) * найти * @Credentials } *
        | * { (не @Successful) * находить * @Credentials } *
        | * { (не @Successful) * *регистрировать* * @PersonalAccount } *
        | * { (не @Successful) * *регистрировать* * приложен* } *
        | * { (не @Successful) * авторизовать* * @PersonalAccount } *
        | * { (не @Successful) * авторизовать* * приложен* } *
        | * { (не @Successful) * попасть * @PersonalAccount } *
        | * { (не @Successful) * попасть * приложен* } *
        | * { (не @Successful) * @Change * @Credentials * [@WiFi $weight<-1>] } *
        | * { (не @Successful) * @ChangePast * @Credentials * [@WiFi $weight<-1>] } *
        | * { (не @Successful) * ввести * @Credentials } *
        | * { (не @Successful) * вводить * @Credentials } *
        | * { (не @Successful) * войти * @PersonalAccount } *
        | * { (не @Successful) * войти * приложен* } * [*платить] *
        | * { (не @Successful) * входить * @PersonalAccount } *
        | * { (не @Successful) * вход * @PersonalAccount } *
        | * { (не @Successful) * восстанов* * @Credentials } *
        | * { (не @Successful) * восстанав* * @Credentials } *
        | * { (не @Successful) * залогинить* * @PersonalAccount } *
        | * { (не @Successful) * залогинить* * приложен* } *
        | * { (не @Successful) * зайти * @PersonalAccount } *
        | * { (не @Successful) * зайти * приложен* } *
        | * { (не @Successful) * заходить * @PersonalAccount } *
        | * { (не @Successful) * найти * @Credentials } *
        | * { (не @Successful) * нашл* * @Credentials } *
        | * { (не @Successful) * нашел* * @Credentials } *
        | * { (не @Successful) * найден* * @Credentials } *
        | * { (не @Successful) * находит* * @Credentials } *
        | * { (не @Successful) * помню * @Credentials} *
        | * { (не @Successful) * *помни* * @Credentials} *
        | * { (не @Successful) * получа* * @Credentials} *
        | * { (не @Successful) * получи* * @Credentials} *
        | * { (не @Successful) * попасть * @Credentials} *
        | * { (не @Successful) * попад* * @Credentials} *
        | * { (не @Successful) * пускать * @Credentials} *
        | * { (не @Successful) * пускает * @Credentials} *
        | * { (не @Successful) * вспомнить * @Credentials } *
        | * { (не @Successful) * зарегистрировать* * @PersonalAccount } *
        | * { (не @Successful) * зарегистрировать* * приложен* } *
        | * { (не @Successful) * получить * @Credentials } *
        | * { (не @Successful) @Know * @Credentials } *
        | * { (вопрос*/информаци*) * @Credentials } *
        | * { получить * @Credentials } *
        | * { попасть * @PersonalAccount } *
        | * { @Know * @Credentials } *
        | * { без понятия * @Credentials } *
        | * { напомнит* * @Credentials } *
        | * { номер (счет*/@SynonymsForAgreement) * @Credentials } * 
        | * { не откры* * @PersonalAccount } *
        | * { (не @Successful) откры* * @PersonalAccount } * 
        | * { (восстанов*/восстанав*) * пин код* } *)
    
    $serviceCentersAddress = ( * @ServiceCenters * @DayOfWeek *
        | * {@ServiceCenters * @DomRu} *
        | * {адрес* * @ServiceCenters} *
        | * {врем* работ* * @ServiceCenters} *
        | * {адрес* * @DomRu} *
        | * {врем* работ* * @DomRu} *
        | * { @Know * @ServiceCenters } *
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
        | * { расписан* * @ServiceCenters } * 
        | * { где * прием* * @PhysicalEntity } *
        | * где { @Can * принять * жив* @Operator } *
        | * где { @Can * принять * $notARobot } *
        | * где { принимает * жив* @Operator } *    
        | * где { принимает * $notARobot } * )

    $userMoving = ( * (переезд*/переезж*/переех*) *
        | * { заключ* * @SynonymsForAgreement } * { друг* * @Plaсe } *
        | * { перевеcти * @SynonymsForAgreement } * { друг* * @Plaсe } *
        | * { перевезти * @SynonymsForAgreement } * { друг* * @Plaсe } *
        | * { перевеcти * @AllServices } * { друг* * @Plaсe } *
        | * { перевезти * @AllServices } * { друг* * @Plaсe } *
        | * { передел* * @SynonymsForAgreement } * { друг* * @Plaсe } *
        | * { переключ* * @SynonymsForAgreement } * { друг* * @Plaсe } *
        | * { перенос* * @AllServices * (нов*/друг*) * @Place } *
        | * { перенос* * @Internet } *
        | * { перенос* * @Television } *
        | * { перенос* * точк* (@WiFi/доступ*) } *
        | * { перенос* * точк* * стар* * нов*  } *
        | * { перенос* * @InternetEquipment } *
        | * { перенести * @Internet } *
        | * { перенести * @Television } *
        | * { перенести * точк* (@WiFi/доступ*) } *
        | * { перенести * точк* * стар* * нов*  } *
        | * { перенести * @InternetEquipment } *
        | * { перенос* * @SynonymsForAgreement } * друг* * адрес *
        | * { переоформ* * @SynonymsForAgreement } * друг* * адрес *
        | * { переподключ* * @SynonymsForAgreement } * друг* * адрес *
        | * { перерегистр* * @SynonymsForAgreement } * друг* * адрес *
        | * { переустанов* * @AllServices * (нов*/друг*) * @Place } *
        | * { переустанов* * @SynonymsForAgreement } * друг* * @Place *
        | * { подключени* * @AllServices } * (нов*/друг*) * @Place *
        | * { подключени* * @SynonymsForAgreement } * друг* * @Place *
        | * { перенести * @AllServices * (нов*/друг*) * @Place } *
        | * { (перенос*/перенести) * @SynonymsForAgreement }
        | * { отключ* @AllServices } * одно* @Place * (на/по) друго*
        | * { отключ* @AllServices } * одно* @Place * (на/по) нов*
        | * { отключ* все } * одно* @Place * (на/по) друго*
        | * { отключ* все } * одно* @Place * (на/по) нов*
        | * { пере* @AllServices } * одно* @Place * (на/по) нов*
        | * { пере* @SynonymsForAgreement } * одно* @Place * (на/по) нов*
        | * { пере* @AllServices } * одно* @Place * (на/по) друго*
        | * { пере* @SynonymsForAgreement } * одно* @Place * (на/по) друго*
        | * { перенос* * [одно*] @Place * (на/по) друго* } *
        | * { перенести * [одно*] @Place * (на/по) друго* } *
        | * { переез* * [одно*] @Place * (на/по) друго* } *
        | * { перевез* * [одно*] @Place * (на/по) друго* } *
        | * { перевес* * [одно*] @Place * (на/по) друго* } *
        | * { перенос* * одно* @Place * (на/по) нов* } *
        | * { перенести * одно* @Place * (на/по) нов* } *
        | * { переез* * одно* @Place * (на/по) нов* } *
        | * { отключ* @AllServices } * стар* @Place * (на/по) нов*
        | * { пере* @AllServices } * стар* @Place * (на/по) нов*
        | * { пере* @SynonymsForAgreement } * стар* @Place * (на/по) нов*
        | * { перенос* * стар* @Place * (на/по) нов* } *
        | * { перенести * стар* @Place * (на/по) нов* } *
        | * { переез* * стар* @Place * (на/по) нов* } *
        | * { @Change * @Place * подключени* } * )

    $hotSpotIssue = ( * { не получает* * @HotSpot } *
        | * { не удает* * @HotSpot } *
        | * { подключ* * @HotSpot } *
        | * { (проблем*/ошибк*/жалоб*/неисправност*/неполадк*) * @HotSpot } *
        | * { соедин* * @HotSpot } *
        | * { (получи*/получен*/получа*) * @HotSpot } *
        | * { авториз* * @HotSpot } *
        | * { вход* * @HotSpot } *
        | * { @Credentials * @HotSpot } *
        | * { доступ* * @HotSpot } *
        | * { @HotSpot * @DomRu} *
        | * { @HotSpot * на улице} *
        | * { @HotSpot * (торгов* центр*/тэ цэ)} *
        | * { @HotSpot * (магазин*/кафе/ресторан*)} *
        | * { не работ* * @HotSpot } *
        | * @HotSpot *)

    $deviceReplacement = ( * @RemoteControl дистанционного управления *
        | * { (@Bad/$bad) * включ* * @Equipment } *
        | * { (@Bad/$bad) * включ* * @RemoteControl } *
        | * { (@Bad/$bad) * переключ* * @Equipment } *
        | * { (@Bad/$bad) * переключ* * @RemoteControl } *
        | * { (@Bad/$bad) * работ* * @Equipment } *
        | * { (@Bad/$bad) * работ* * @RemoteControl } *
        | * { (@Bad/$bad) * реагир* * @Equipment } *
        | * { (@Bad/$bad) * реагир* * @RemoteControl } *
        | * { *менять * @Equipment } *
        | * { *менять * @InternetEquipment} *
        | * { *менять * @RemoteControl } *
        | * { *менять * @TVEquipment } *
        | * { *менять * @YandexAlisaEquipment } *
        | * { смен* * @Equipment } *
        | * { смен* * @InternetEquipment} *
        | * { смен* * @RemoteControl } *
        | * { смен* * @TVEquipment } *
        | * { смен* * @YandexAlisaEquipment } *
        | * { @RemoteControl * не включ* * @TVChannel } *
        | * { @RemoteControl * не включ* * @Television } *
        | * { @RemoteControl * не включ* * @TVSet } *
        | * { @RemoteControl * не переключ* * @TVChannel } *
        | * { @RemoteControl * не переключ* * @Television } *
        | * { @RemoteControl * не переключ* * @TVSet } *
        | * { ~старый * @Equipment } *
        | * { вернуть * @Equipment } *
        | * { вернуть * @InternetEquipment } *
        | * { вернуть * @RemoteControl } *
        | * { вернуть * @TVEquipment } *
        | * { вернуть * @YandexAlisaEquipment } *
        | * { [ @Operator $weight<+0.3>] * ~замена * @Equipment } *
        | * { [ @Operator $weight<+0.3>] * ~замена * @InternetEquipment } *
        | * { [ @Operator $weight<+0.3>] * ~замена * @RemoteControl } *
        | * { [ @Operator $weight<+0.3>] * ~замена * @TVEquipment } *
        | * { [ @Operator $weight<+0.3>] * ~замена * @YandexAlisaEquipment } *
        | * { [ @Operator $weight<+0.3>] * замени* * @Equipment } *
        | * { [ @Operator $weight<+0.3>] * замени* * @InternetEquipment } *
        | * { [ @Operator $weight<+0.3>] * замени* * @RemoteControl } *
        | * { [ @Operator $weight<+0.3>] * замени* * @TVEquipment } *
        | * { [ @Operator $weight<+0.3>] * замени* * @YandexAlisaEquipment } *
        | * { [ @Operator $weight<+0.3>] * заменя* * @Equipment } *
        | * { [ @Operator $weight<+0.3>] * заменя* * @InternetEquipment } *
        | * { [ @Operator $weight<+0.3>] * заменя* * @RemoteControl } *
        | * { [ @Operator $weight<+0.3>] * заменя* * @TVEquipment } *
        | * { [ @Operator $weight<+0.3>] * заменя* * @YandexAlisaEquipment } *
        | * { не включ* * @Equipment } *
        | * { не включ* * @RemoteControl } *
        | * { не нравится * ~новый * @Equipment } *
        | * { не нравится * ~новый * @InternetEquipment } *
        | * { не нравится * ~новый * @RemoteControl } *
        | * { не нравится * ~новый * @TVEquipment } *
        | * { не нравится * ~новый * @YandexAlisaEquipment } *
        | * { не переключ* * @Equipment } *
        | * { не переключ* * @RemoteControl } *
        | * { не работ* * @Equipment } *
        | * { не работ* * @RemoteControl } *
        | * { не реагир* * @Equipment } *
        | * { не реагир* * @RemoteControl } *
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
        | * { [ @Operator $weight<+0.3>] * поменя* * @Equipment } *
        | * { [ @Operator $weight<+0.3>] * поменя* * @InternetEquipment } *
        | * { [ @Operator $weight<+0.3>] * поменя* * @RemoteControl } *
        | * { [ @Operator $weight<+0.3>] * поменя* * @TVEquipment } *
        | * { [ @Operator $weight<+0.3>] * поменя* * @YandexAlisaEquipment } *
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
        | * { сгорел* * @Equipment } *
        | * { сгорел* * @InternetEquipment } *
        | * { сгорел* * @RemoteControl } *
        | * { сгорел* * @TVEquipment } *
        | * { сдать * @Equipment } *
        | * { сдать * @InternetEquipment } *
        | * { сдать * @RemoteControl } *
        | * { сдать * @TVEquipment } *
        | * { сдать * @YandexAlisaEquipment } *
        | * { сломан* * @RemoteControl } *
        | * { смен* * @Equipment } *
        | * { смен* * @InternetEquipment } *
        | * { смен* * @RemoteControl } *
        | * { смен* * @TVEquipment } *
        | * { смен* * @YandexAlisaEquipment } *
        | * { старый @RemoteControl } *)

    $otherIntent = ( * { был * звонок * @DomRu } *
        | * { почему * (отправил*/поступил*) * @SMS } *
        | * { куда * $regexp<дел(ись|ся|ось)> * @duckling.amount-of-money * @My * (баланс*/@SynonymsForAgreement/счет*) } *
        | * { (пришл*/пришел*) * @SMS * @duckling.amount-of-money * откуда * $regexp<взял(ись|ся|ось)>} *
        | * { (@Change/@ChangePast) * ~дата * @Payment } *
        | * { не $regexp<прош(ла|ли|ло)> @Payment } *
        | * { (вы/мне) звонили } *
        | * { (пришел/поступил*/получил*/оценить) * звонок } *
        | * { (пропущенн*/неотвеченн*) * звонок } *
        | * { дать оценк* * (звонок/звонк*) } *
        | * { дать оценк* * работ* } *
        | * { звонил* * (не взял*/не ответил*) } *
        | * { звонил* * (не успел*/не *мог*) } *
        | * { уже звонил* * (перезвон*/перезван*) } *
        | * { звон* * (от/из) (вас/вашей) * (компании/организации) } *
        | * { звон* * (от/из) * (компании/организации) * @DomRu} *
        | * { сегодня * звон* * (от/из) * @DomRu} *
        | * { *звонил* * (от/из) (вас/вашей) * (компании/организации) } *
        | * { *звонил* * (от/из) * (компании/организации) * @DomRu} *
        | * { не заметил* * звонок } *
        | * { оценить * работу } *
        | * { оценить * работу * @Operator} *
        | * { оценить * работу * @Personal} *
        | * { оценить * работу * сотрудник*} *
        | * { оценить * работу * специалист*} *
        | * { подключ* * второй * телевизор* } *
        | * { почему * (отправил*/поступил*) * (звонок/звонк*) } *
        | * { проглядел* * звонок } *
        | * { пропустил* * звонок } *
        | * { прослушал* * звонок } *
        | * { просмотрел* * звонок } *
        | * { сбросил* * звонок } *
        | * { упустил* * звонок } *
        | * { кто [мне] звонил } *
        | * { звонил* с этого номера } * 
        | * { подключен * домаш* @Telephone } * 
        | * { модернизац* * @Place } *  
        | * { помощ* * (приложен* @DomRu) } * 
        | * { помог* * (приложен* @DomRu) } * 
        | * { установ* * (приложен* @DomRu) } * 
        | * { привяз* * (номер* телефон*) } * 
        | * { привяз* * профил* } * 
        | * { [сообщ*] установ* * пин код* } * 
        | * { потом * @Be * что то вносить } *  
        | * { не согл* * @Payment } *  )

    $thanksForOtherIntent = ( { (выраз*/объявит*/оставит*) * благодар* }
        | { (премного/очень) благодарен}
        | { (сказать/скаж*/говори*) * $thanks }
        | { (сказать/скаж*/говори*) * (мерси/спасиб*) }
        | { @Personal * $thanks }
        | { оставит* * (положит*/хорош*) * отзыв* }
        | { сделать благодарность }
        | { сделать комплимент }
        | благодар*
        | поблагодарит* )

    $complaintsAndPretension = (* { (претенз*/жалоб*) * (@Services/@DomRu) } *
        | * { (претенз*/жалоб*) * @Payment } *)

    $cableReplacement = ( * { (*ремонтировать/ремонт*/отремонтир*) * @Cable } *
        | * { (нарастит*/наращ*) * @Cable } *
        | * { *совершенствов* * @Cable } *
        | * { апгрейд* * @Cable } *
        | * { восстанов* * @Cable } *
        | * { замен* * @Cable } *
        | * { помен* * @Cable } *
        | * { модерниз* * @Cable } *
        | * { не работ* * @Cable } *
        | * { @Wrong * подключ* * @Cable } *
        | * { обновлен* * @Cable } *
        | * { (@Bad/$bad) * работ* * @Cable } *
        | * { (@Bad/$bad)  * подключ* * @Cable } *
        | * { (испорч*/испортил*) * @Cable } *
        | * { (попорч*/попортил*) * @Cable } *
        | * { *вредил* * @Cable } *
        | * { *врежд* * @Cable } *
        | * { *грыз* * @Cable } *
        | * { барахл* * @Cable } *
        | * { изгадил* * @Cable } *
        | * { оборв* * @Cable } *
        | * { порвал* * @Cable } *
        | * { порван* * @Cable } *
        | * { порезал* * @Cable } *
        | * { порезан* * @Cable } *
        | * { починил* * @Cable } *
        | * { починит* * @Cable } *
        | * { *чинит* * @Cable } *
        | * { @Personal * *менять * @Cable } *
        | * { @Personal * замен* * @Cable } *
        | * { @Personal * помен* * @Cable } *)
        
    $noAnswer = ( $hello
        | @Allo [@Allo]
        | { @Allo [@Modal] }
        | { [@Modal] [$hello] @Allo [@Allo] } 
        | { (* слыш* *) [$hello] @Allo [@Allo] } 
        | { (* слыш* *) $hello } 
        | { девушк* @Allo [@Allo] } 
        | [$oneWord] [$oneWord] { вы слышите меня } [$oneWord] [$oneWord] 
        | поговорить )