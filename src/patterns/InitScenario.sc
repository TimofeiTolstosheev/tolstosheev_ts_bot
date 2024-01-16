patterns:
    
    # добавила $callSupport в $agentRequest и отделила $techSupport
    # $callSupport = * ({((есть/можно/можешь/можете) [ли]/надо/нужен/хочу/дай/дайте/давай*) [мне/у вас] [тут/здесь] [на] [~живой] ($serviceHelperHuman/$notARobot/~человек [а] [$notARobot])}
        #| {((есть/можно/можешь/можете) [ли]/надо/нужен/хочу/дай/дайте/давай*) [мне/у вас] [тут/здесь] [на] ~другой $serviceHelperHuman}
        #| {~живой ($serviceHelperHuman/$notARobot)}
        #| [как] [можно [ли]/надо/есть [ли] возможность] {(([(есть/можно/можешь/можете) [ли]/надо/нужен/хочу/дай/дайте/давай*] [ли] (соедин*/связать*/свяжи*/переключ*/переведи*/перевести) [меня])/*консультир* [меня]/пригласи*/пообщаться/напиши*/написать/[получить/дать/нужна] ~консультация/перевод/позов*/зови*/*звать/$need/чат/связь*/дай/перейти к разговору) * [с/со/до/на] ($techSupport/$serviceHelperHuman/$notARobot/(~человек/(живог*/~живой) [$serviceHelperHuman/$notARobot/~человек]) [а] [$notARobot])}
        #| [как] {(можно/надо/есть возможность) (с (кем-нибудь/кем-либо/кем-то)) [~другой] проконсультироваться}
        #| (департам* связи)
        #| (~написать/~пообщаться/узнать/спроси*/вопрос*/заяв*/обрати*/обращени*/переключ*/перевести/переведи*/связать*/свяж*/соедин*/предостав*) * $techSupport
        #| (сообщить/сообщени*/репорт*/уведомить) [о] [технич*] (проблем*/неполадк*)
        #| $techSupport) *
    
    # заменила $agreement на @SynonymsForAgreement
    # $agreement = ~договор
    $isNotMyAgreement = * ({(не (мой/на (меня/мое имя))/[на] $relatives) * [заключ*/оформл*/запис*/припис*/подпис*] * [@SynonymsForAgreement]}
        | {{я [$oneWord] не} * (владел*/заключ*/оформл*/подписыва*)} | не (мой/по моему/по своему) ~договор ) *
    $dontRememberWhoseAgreement = * {$dontRemember * (на (кого/чье имя)/{(на (кого/чье имя)/кто) * (оформл*/запис*/припис*) * [@SynonymsForAgreement]}/{чей * @SynonymsForAgreement})} *
    $haveNoPatronymic = * {(нет/нету/без/отсутствует) * отчеств*} *

    $cant = (не (cмогу/сможем/получилось/удается/удалось/в силах)/$cannot)
    $dontRemember = * (не (знаю/узна*/помн*/вспомн*/в курсе/сохран*)/~забыть/запамят*
        | {$cant * (найти/вспомнить/припомнить)}
        | {(вылете*/вышибл*) * (из (~голова/~память))} 
        | {(не ~приходить) * (в ~голова)}) *

    # позаимствую паттерны из репо Дом.ру при переезде
    # добавила $getRouter полностью в $potentialClientDevice
    # $getRouter = * ({(~купить/покуп*/приобр*) * (роутер*/обурудовани*)}/{хочу роутер [$oneWord] [себе]}) *
    # $potentialClientService включает в себя более точные фразы, $activateService заменила на $potentialClientService
    # $activateService = * {подключ* * услуг*} *
    
    # прилагательного м, ср рода И и Р падежи
    $AdjectiveSg = $regexp_i<([А-Яа-яЁё]+(([ыио]й)|(ого|ова|ово)))>
    # прилагательного ж рода И и Р падежи
    $AdjectiveSgF = $regexp_i<([А-Яа-яЁё]+(([ое]й)|([ая]я)))>
    
    # улицы из csv файлов
    $streetsDict1 = $entity<streetsDict1>
    $streetsDict2 = $entity<streetsDict2>
    $streetsDict3 = $entity<streetsDict3>
    $streetsDict4 = $entity<streetsDict4>
    $streetsDict = ($streetsDict1/$streetsDict2/$streetsDict3/$streetsDict4)
    
    $City = $entity<Cities> || converter = $converters.cityConverter;
    $CityWord = (в городе/город)
    $CityCombo = ( [$CityWord] $City
        | {(станица/в станице) $AdjectiveSgF} )
    # поселок, деревня, село, сельская местность/поселение (ед ч, все падежи)
    $villageWordSg = ( ($regexp_i<п[оа]село?к(а|у|ом|е)?>)
        | ($regexp_i<д[ие]ревн(я|и|е[юй]?|ю)>)/($regexp_i<сел(ом?|а|у|е)>)
        | (сельск* ($regexp_i<м[еи]ст?н[ао]ст(ью?|и)>/($regexp_i<п[оа]с[еи]лени(ем?|и|я|ю)>))) )
    $Village = $villageWordSg ([$AdjectiveSg/$AdjectiveSgF] $oneWord)
    # область, регион, край, район (ед ч, все падежи)
    $regionWordSg = ( ($regexp_i<област(ью?|и)>)
        | ($regexp_i<регион(а|у|ом|е)?>)
        | ($regexp_i<кра(й|я|ю|ем?)>)
        | ($regexp_i<район(а|у|ом|е)?>) )
    $Region = ($AdjectiveSg/$AdjectiveSgF) $regionWordSg 
    # снт, садовое товарищество, коттетжный поселок
    $gardenCommunityWord = ( снт
        | эсэнтэ
        | эс эн тэ
        | сдт
        | днп
        | садов* $regexp<т[оа]варищ[еи]ст?в(ом?|а|у|е)>
        | $regexp<к[оа]тт?[еэ][тд]жн((ы[йм])|(ого|ова|ово)|(ому?))> $regexp_i<п[оа]село?к(а|у|ом|е)?>)
    
    # словарик встретившихся снт
    $gardenCommunityDict = ( [$regexp<[0-9]+>] кмз
        | [$regexp<[0-9]+>] кургансельмаш
        | {малинка территория}
        | 1 кемеровский пертворчермета улгск металлист терметаллург [номер] [$regexp<[0-9]+>]
        | 20 лет победы
        | 40 лет октября
        | 50 лет октября
        | авиаспортклуб
        | аврора
        | алма
        | антей
        | артем
        | астра
        | бережок
        | березка
        | березовая роща
        | булавина
        | буревестник [номер] [$regexp<[0-9]+>]
        | бытовик
        # в снт ветеран есть улицы
        | ветеран
        | взлет
        | вита
        | владимирский квартал [номер] [$regexp<[0-9]+>]
        | водник
        | волга
        | волжская заводь
        | восход
        | восход [номер] [$regexp<[0-9]+>]
        | вэс
        | голубой огонек
        | горизонт [номер] [$regexp<[0-9]+>]
        | горняк
        | городское
        | госучреждений
        | дрожжевик
        | дубрава
        | екатеринбургского электровозоремонтного завода
        | жигули
        | заволжье
        | загородное
        | заря
        | звезд*
        | зеленый мыс
        | земледелец
        | золот* песк*
        | импульс
        | инвалидов алтын-в
        | исток
        | казенный сад
        | кама [номер] [$regexp<[0-9]+>]
        | кинотехник
        | книисх
        | колос [номер] [$regexp<[0-9]+>]
        | колосок
        | коммунальник
        | кооператор
        | красава [номер] [$regexp<[0-9]+>]
        | краснодар гор строй
        | краснодаргорстрой
        | крохово
        | культурник
        | лесное
        | луговой
        | любитель природы
        | малиновая гора [номер] [$regexp<[0-9]+>]
        | медик [номер] [$regexp<[0-9]+>]
        | металлург [номер] [$regexp<[0-9]+>]
        | мечта [номер] [$regexp<[0-9]+>]
        | надежда
        | наука
        | нефтяник [номер] [$regexp<[0-9]+>]
        | нива
        # в снт новокалищенское № есть улицы
        | новокалищенское [номер] [$regexp<[0-9]+>]
        | новые сады
        | номер $regexp<[0-9]+>
        | октябрьск* революц*
        | отставник
        | первенец
        | передельцы
        | пищевик
        # в днп планета есть улицы
        | планета
        | полезный отдых [$regexp<[0-9]+>]
        | проектировщик
        | рабочий поселок
        | раздолье
        | рассвет
        | расцвет
        | риск [номер] [$regexp<[0-9]+>]
        | родник
        | ромашка
        | руно
        | садовод [номер] [$regexp<[0-9]+>]
        | светлый путь
        | светлый яр
        # в снт северное есть улицы
        | северное
        | сельмаш
        | семья
        | сибиряк
        | сокол [номер] [$regexp<[0-9]+>]
        | союз [н|эн]
        | спутник [номер] [$regexp<[0-9]+>]
        | строитель
        | тмз [номер] [$regexp<[0-9]+>]
        | труд
        | труженик
        | урожай
        | химик [номер] [$regexp<[0-9]+>]
        | хуторок
        | цепник
        | чайка [номер] [$regexp<[0-9]+>]
        | черемушк*
        | черняевское [номер] [$regexp<[0-9]+>]
        | энергетик [номер] [$regexp<[0-9]+>]
        | юбилейное
        | южный
        | яровское )
        
    # снт (обязательно!!!, чтобы не конфликтовать с улицей) + название
    $gardenCommunity = { $gardenCommunityWord $gardenCommunityDict}
    
    $Street = ( $addressStreetRegexp 
        | [$regexp<[0-9]+>] $MicroDistrict
        # | [$addressStreetRegexp] $addressWordsRegexp [$addressWord]
        | [$addressStreetRegexp] $addressWordsRegexp
        | $regexp_i<(ул|п(ер|р|л)|ш|бульв|м(кр.?)|наб)\.[А-Яа-я0-9]+(((\-|\s)(\w|[А-Яа-я]+))?((\-|\s)(\w|[А-Яа-я]+))?)?+>
        | $addressWordsRegexp $regexp_i<(л(иния|.?|-я)|пр(-кт|-т|оспект.?))> [$regexp_i<((в(\.|\s)?о\.?|п(\.|\s)?с\.?|васильевского о(строва|-ва))|петроградской стороны)?>]
        | $addressWordsRegexp [$addressWord] $addressStreetRegexp
        | $addressWordsRegexp [$addressWord] $regexp_i<((у)л\.\,?)>
        | (МКАД|КАД) {($Number|$regexp_i<\d+-?й>) км}
        | [$addressStreetRegexp] {[$miscDict $weight<+0.7>] $streetsDict}
        | $streetsDict {[$miscDict $weight<+0.7>] [$addressStreetRegexp]}  
        | $gardenCommunity [{[$addressStreetRegexp] $streetsDict}] )

    #перечисление только слов: ул., улица, переулок, проспект ... в разных вариантах
    $addressStreetRegexp = $regexp_i<(ул(\.[А-Яа-я]+|иц[а-я]{1,3})?|пер(-к|еул(ок)?|.?)?|пр(-кт|-т|оспект[а-я]{0,2}|осп[а-я]{0,5}|-д[а-я]?|оезд[а-я]{0,2})?|б(-р|ульвар[а-я]?|ульв[а-я]?)|ш(оссе|[а-я]?)|п(лощадь|л[а-я]?|-дь)|а(л[а-я]?|-я|ллея|лея)|с(ъезд|-д)|наб([а-я]{0,2}|ережная)|о(строва|-ва)|д(орога|ор|-а)|к(анал|-л)[а-я]{0,2}|тупик[а-я]{0,2}|магистрал[а-я]{1,3}|спуск[а-я]{0,2}|тракт[а-я]{0,2}|станц[ыи][ая])>

    #три любых слова из букв/цифр: первое обязательно, второе и третье не обязательны кроме слова дом на конце
    # $addressWordsRegexp = $regexp_i<[А-Яа-я0-9]+(((\-|\s)([А-Яа-я]+|\w))?((\-|\s)(\w|[А-Яа-я]+))?)?>
    $addressWordsRegexp = $regexp_i<([А-Яа-я0-9]+(((\-|\s)([А-Яа-я]+|\w))?((\-|\s)(\w|[А-Яа-я]+))?)?)>
    # слово, за исключением слова дом на конце
    $addressWord = $regexp_i<([А-Яа-я]+)[^(дом|д\.|д)]>
    
    $MicroDistrict = м(икрорайон|-н|кр|рн|крн|ийон|икр-он)
    
    $excludeAddressWord = ( ( [$miscDict] [$miscDict] ( [я] (~проживать/живу) [на/по]
        | [мы] (~проживать/живем)
        | [он] (~проживать/живет)
        | [@My] адрес
        | адрес у (меня/нас)
        | (мой/наш) адрес
        | [на/по/с/со] адрес*
        | (обращ* / обратил*) [с/со]
        | сегодня / сейчас / подключить/ проводили 
        | где / понял* ) [$miscDict] [$miscDict] )
        | на )
        
    $miscDict = ( ну / так [с/вот] / такс / значит / это / вот / мм / хм / короче / кароче / мол / вообще [то] / в общем / говорю / собственно говоря / как говорится / итак / как его [там] / типа / это самое / знаешь / как бы / по моему / если правильно помню / судя по всему / блин / понимаешь [меня/нас/вообще/ваще] / а именно / вроде [бы] / возможно)

    $corpus = $regexp_i<((к(орп?)?(\.?|\.?\s))|((к(орпус|орп?\.?))\s))\d+[А-Яа-я0-9]*>
    $liter = $regexp_i<((литера?|(л(ит)?(\.?)))\s[А-Яа-яa-zA-Z0-9]+)>
    $building = $regexp_i<(строени[ея])\s\d+[А-Яа-я0-9]*>
    $entrance = $regexp_i<((п(\.?|\.?\s))|((п(одъезд|ар(адная)?))\s))\d+[А-Яа-я0-9]*>
    $Housing = ($regexp_i<[а-я]{1}>
        | {$corpus [$liter] [$building] [$entrance]}
        | {[$corpus] $liter [$building] [$entrance]}
        | {[$corpus] [$liter] $building [$entrance]}
        | {[$corpus] [$liter] [$building] $entrance})
    # дом или участок, если это снт
    $HouseNumber = $regexp_i<((д(\.?\s|\.?))?|((дом)\s)?|(участо?к(а|е|у|ом)?))(\d+(-|\/)?(\sдробь\s)?[А-Яа-я0-9]*)+>
    #NOTE не использовать в квартир*, иначе корпус в уходит в $Flat
    $FlatWord = (квартира/в квартире/на квартире)
    $FlatNumber = $regexp<[0-9]+>
    $Flat = {[$FlatWord] $FlatNumber::FlatNumber}

    # не разрешать свободный порядок для города, иначе название записывается в улицу
    # TODO по адресу октябрьской квартира 49 - должно попадать в неполный адрес
    $Address = [$CityCombo/$Village] {($Street {$HouseNumber [$Housing] [$Flat]})} || converter = $converters.addressConverter
 
    
    # $IncompleteAddress = ( {$Street [{$HouseNumber [$Housing]}]}
    #     | $addressWordsRegexp $Flat) || converter = $converters.addressConverter
    $IncompleteAddress = ( $addressStreetRegexp ([{$HouseNumber [$Housing]}]/[$Flat])
        | [$addressWordsRegexp $weight<-0.5>] $FlatWord $FlatNumber 
        | {[$addressStreetRegexp] $streetsDict} $FlatWord $FlatNumber
        | $Village
        | $Region ) || converter = $converters.addressConverter
    $NonExplicitStreet = ($HouseNumber|$Housing) || converter = $converters.addressConverter

    $Name = $entity<Names> || converter = $converters.nameConverter;
    $UserName = замзами*
    $MiddleName = $regexp_i<[А-яЁё]+((ич)|(вна)|(чна))> || converter = $converters.middleAndLastNameConverter;
    # $LastName = $regexp_i<[А-яЁё]+((-|')[А-яЁё]+)?(ов|ова|ев|ева|ин|ина|ын|ына|ской|ский|ская|цкой|цкая|цкий|их|ых|енко|ко|ук|юк|ман|швили|ян|янц|ава|ты|ти|фельд|штерн|ес|ез|айтис|айте|сон)> || converter = $converters.middleAndLastNameConverter;
    $LastName = $regexp<[А-яЁё]+((-|')[А-яЁё]+)?> || converter = $converters.middleAndLastNameConverter;
    $FIO = {($Name/$UserName) $MiddleName $LastName} || converter = $converters.FIOConverter;
    $IncompleteFIO = ({(($Name/$UserName) [$MiddleName]) [$LastName]}
        | {(($Name/$UserName) [$MiddleName]) $LastName}
        | $LastName) || converter = $converters.FIOConverter;
