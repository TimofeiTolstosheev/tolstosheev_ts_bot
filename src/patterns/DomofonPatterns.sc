patterns:

    $domofon = ( $regexp<домофон(у|а|ом|е|ы)> / $regexp<аудиодомофон(у|а|ом|е|ы)> )
    
    $commonDomofon = ( * $domofon *
        | * { *говор* * $domofon } *
        | * { вопрос* * $domofon } *
        | * { инфор* * $domofon } *
        | * { консульт* * $domofon } * )

    $keyIntercom = ( * @KeyIntercom *
        | * { @Give * @KeyIntercom } *
        | * { магнитн* * @KeyIntercom } *
        | * { электронн* * @KeyIntercom } *
        | * { домофон* * @KeyIntercom } *
        | * { цифров* * @KeyIntercom } * 
        | * { интерес* * @KeyIntercom } * 
        | * { установил* * домофон* } * { @Be * @KeyIntercom } * 
        | * { установил* * аудиодомофон* } * { @Be * @KeyIntercom } *)

    $keyBuy = ( * { купи* * @KeyIntercom } *
        | * { покупк* * @KeyIntercom } *
        | * { покупать * @KeyIntercom } *
        | * { [@Modal] * заказ* * @KeyIntercom } *
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
        | * { (@Change/@ChangePast) * @KeyIntercom } *
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
        | * { @Request * @KeyIntercom } *
        | * { выдад* * @KeyIntercom } *
        | * { выдае* * @KeyIntercom } *
        | * { выдай* * @KeyIntercom } * 
        | * { @Give * @KeyIntercom } * )

    $keyActivate = (* { актив* * @KeyIntercom } *
        | * { добав* * @KeyIntercom } *
        | * { запрограммир* * @KeyIntercom } *
        | * { программир* * @KeyIntercom } *
        | * { настроен* * @KeyIntercom } *
        | * { настрои* * @KeyIntercom } *
        | * { настраива* * @KeyIntercom } *
        | * { настрой* * @KeyIntercom } *
        | * { не подключ* * @KeyIntercom } *
        | * { не актив* * @KeyIntercom } *
        | * { (не @Successful) * подключ* * @KeyIntercom  } *
        | * { подключ* * @KeyIntercom } *
        | * { (не @Successful) * актив* * @KeyIntercom  } *
        | * { (не @Successful) * добав* * @KeyIntercom  } *
        | * { (не @Successful) * *программир* * @KeyIntercom  } *
        | * { (не @Successful) * настрой* * @KeyIntercom  } *
        | * { (не @Successful) * настроит* * @KeyIntercom  } *
        | * { (не @Successful) * связ* * @KeyIntercom  } *
        | * { (не @Successful) * свяж* * @KeyIntercom  } *
        | * { (не @Successful) * привяз* * @KeyIntercom  } *
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
        | * { @Wrong * работа* * @KeyIntercom } *
        | * { медленно * срабат* * @KeyIntercom } *
        | * { @Wrong * срабат* * @KeyIntercom } *
        | * { не *работ* * @KeyIntercom } *
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
        | * { дефект* * @KeyIntercom } *
        | * { не открыв* * подъезд* * @KeyIntercom } *
        | * { не открыт* * подъезд* * @KeyIntercom } *
        | * { не открыв* * парадн* * @KeyIntercom } *
        | * { не открыт* * парадн* * @KeyIntercom } *
        | * { не открыт* * двер* * @KeyIntercom } *
        | * { не открыв* * двер* * @KeyIntercom } *
        | * { не открыт* * домофон* * @KeyIntercom } *
        | * { не открыв* * домофон* * @KeyIntercom } *
        | * { не действует * @KeyIntercom } *
        | * { неисправн* * @KeyIntercom } *
        | * { перестал* * работа* * @KeyIntercom } *
        | * { перестал* * срабат* * @KeyIntercom } *
        | * { перестал* * открыв* * @KeyIntercom } * 
        | * { (@Bad/$bad) * *работ* * @KeyIntercom } *
        | * { (@Bad/$bad) * срабат* * @KeyIntercom } * )

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
        | * { установ* * домофон* [установил* $weight<-1>]} *
        | * { устанав* * домофон* } *
        | * { установ* * аудиодомофон* [установил* $weight<-1>]} *
        | * { устанав* * аудиодомофон* } *
        | * { установ* * [домофон*] трубк* } *
        | * { устанав* * [домофон*] трубк* } *
        | * { постав* * домофон* } *
        | * { постав* * аудиодомофон* } * 
        | * { подключ* * домофон* * @Place} *
        | * { подключ* * аудиодомофон* * @Place} * )

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
        | * { (не @Successful) * войти * [приложени*] @SmartDom } *
        | * { (не @Successful) * зайти * [приложени*] @SmartDom } *
        | * { (не @Successful) * подключ* * [приложени*] @SmartDom } *
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
        | * { (не @Successful) откры* * [приложени*] @SmartDom } *)

    $domofonNotWork = ( * { не [@Successful] исправ* * домофон* * трубк* } *
        | * { не [@Successful] исправ* * домофон* * панел* } *
        | * { не [@Successful] исправ* * аудиодомофон* * трубк* } *
        | * { не [@Successful] исправ* * аудиодомофон* * панел* } *
        | * { не [@Successful] исправ* * доводчик* } *
        | * { неисправ* * домофон* * трубк* } *
        | * { неисправ* * домофон* * панел* } *
        | * { неисправ* * аудиодомофон* * трубк* } *
        | * { неисправ* * аудиодомофон* * панел* } *
        | * { неисправ* * доводчик* } *
        | * { *ремонт* * домофон* * трубк* } *
        | * { *ремонт* * домофон* * панел* } *
        | * { *ремонт* * аудиодомофон* * трубк* } *
        | * { *ремонт* * аудиодомофон* * панел* } *
        | * { *ремонт* * домофон* } *
        | * { *ремонт* * аудиодомофон* } *
        | * { *ремонт* * калитк* } *
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
        | * { не [@Successful] подключ* * домофон* } *
        | * { не [@Successful] подключ* * аудиодомофон* } **
        | * { не [@Successful] попад* * домофон* } *
        | * { не [@Successful] попад* * аудиодомофон* } *
        | * { не [@Successful] попасть * домофон* } *
        | * { не [@Successful] попасть * аудиодомофон* } *
        | * { не [@Successful] откры* * калитк* } *
        | * { не [@Successful] закры* * калитк* } *
        | * { не *работ* * домофон* } *
        | * { не *работ* * аудиодомофон* } *
        | * { не *работ* * калитк* } *
        | * { (@Bad/$bad) * подключ* * домофон* } *
        | * { (@Bad/$bad) * подключ* * аудиодомофон* } *
        | * { (@Bad/$bad) * попад* * домофон* } *
        | * { (@Bad/$bad) * попад* * аудиодомофон* } *
        | * { (@Bad/$bad) * попасть * домофон* } *
        | * { (@Bad/$bad) * попасть * аудиодомофон* } *
        | * { (@Bad/$bad) * откры* * калитк* } *
        | * { (@Bad/$bad) * закры* * калитк* } *
        | * { (@Bad/$bad) * *работ* * домофон* } *
        | * { (@Bad/$bad) * *работ* * аудиодомофон* } *
        | * { (@Bad/$bad) * *работ* * калитк* } *
        | * { провер* * домофон* } *
        | * { провер* * аудиодомофон* } * 
        | * { домофон* * @Personal } *
        | * { аудиодомофон* * @Personal } * 
        | * { (вырван*/*ломан*) * доводчик* } * )

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
        | * { восстан* * камер* * @VideoThings } *
        | * { восстан* * видеокамер* * @VideoThings } *
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
        | * { @Wrong * *работа* * @VideoThings } *
        | * { нет * $regexp<запис(ь|и|ей)> * @VideoThings } *
        | * { нет * доступ* * @VideoThings } *
        | * { нету * $regexp<запис(ь|и|ей)> * @VideoThings } *
        | * { нету * доступ* * @VideoThings } *
        | * { нефункционир* * @VideoThings } *
        | * { поврежден* * @VideoThings } *
        | * { не функционир* * @VideoThings } *
        | * { оформ* * камер* * @VideoThings } *
        | * { оформ* * видеокамер* * @VideoThings } *
        | * { установ* * камер* * @VideoThings } *
        | * { установ* * видеокамер* * @VideoThings } *
        | * { устанав* * камер* * @VideoThings } *
        | * { устанав* * видеокамер* * @VideoThings } *
        | * { переоформ* * камер* * @VideoThings } *
        | * { переоформ* * видеокамер* * @VideoThings } *
        | * { переустанов* * камер* * @VideoThings } *
        | * { переустанов* * видеокамер* * @VideoThings } *
        | * { подключ* * камер* * @VideoThings } *
        | * { подключ* * видеокамер* * @VideoThings } *
        | * { подключ* * @VideoThings } *
        | * { (не @Successful) * подключ* * камер* * [@VideoThings] } *
        | * { (не @Successful) * подключ* * видеокамер* * [@VideoThings] } *
        | * { (не @Successful) * подключ* * @VideoThings } *
        | * { переоформ* * @VideoThings } *
        | * { переустанов* * @VideoThings } *
        | * { подсоедин* * камер* * @VideoThings } *
        | * { приложен* * камер* * [@VideoThings]} *
        | * { приложен* * видеокамер* * [@VideoThings] } *
        | * { приложен* * @VideoThings } *
        | * { вопрос* * камер* * @VideoThings } *
        | * { консультац* * камер* * @VideoThings } *
        | * { информац* * камер* * @VideoThings } *
        | * { подсоедин* * видеокамер* * @VideoThings } *
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
        | * { установ* * @VideoThings } *
        | * { установ* * камер* * @VideoThings } *
        | * { установ* * видеокамер* * @VideoThings } *
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
        | * { возможност* * подключ* @VideoThings * } *
        | * { @Request * (камер*/видеокамер*) * @DomRu } *
        | * { @Request * (камер*/видеокамер*) * @VideoThings } *
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
        | * { (не @Successful) * провер* * запис* } *
        | * { (не @Successful) * просмотр* * запис* } *
        | * { (не @Successful) * посмотр* * запис* } *
        | * { (не @Successful) * скачать * запис* } *
        | * { (не @Successful) * найти * запис* } *
        | * { (не @Successful) * увидеть * запис* } *
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
        | * { (@Bad/$bad) * показ* * @VideoThings } *
        | * { (@Bad/$bad) * работа* * @VideoThings } *
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
        
    $otherDomofon = ( * { доступ* * управ* * домофон* } *
        | * { доступ* * управ* * аудиодомофон* } * )   