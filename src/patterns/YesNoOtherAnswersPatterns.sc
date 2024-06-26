patterns:
    
    $commonYes = ($yes [$yes]| [$yes] обязательно | непременно | а как же | жа | ес [ес]
        | [$yes] подтвержда*
        | [$yes] (совершенно/абсолютно) точно
        | [$yes] пожалуй
        | [$yes] норм
        | [$yes] (почему/что/че) бы и нет
        | [$yes] было [бы] (неплохо|не плохо)
        | [$yes] [очень] @Want
        | {$yes @Want}
        | ну да
        | ладно
        | [$yes] хорошо
        | [$yes] отлично
        | ладно договорились
        | [$yes] валяй*
        | естественно
        | [$yes] разумеется
        | [$yes] (еще|ещё) как
        | [$yes] не (против|возражаю)
        | [$yes] только за
        | [$yes] безусловн*
        | [$yes] все (так|верно)
        | [$yes] (совершенно|абсолютно) верно
        | [$yes] (давай|давайте|логично)
        | [$yes] (конечно|конешно|канешна)
        | [$yes] [все/$yes] правильно
        | [$yes] так точно
        | {$yes [$yes] *пасиб*} 
        | lf 
        | {манда $yes}
        | {$yes (господи/госпади/хоспади/хоспадя/боже/о боже)} )
    
    $yesWhatElse = ($yes / ад / lf / даа / $yes или $no $yes / { $need $yes } )
    
    $noWhatElse = ($no / нт / ytn / нетт / нету / $yes или $no $no / нет все)
        
    $commonNo = ( $no | да ну | да не
        | [или/и] $no $no [$no] [$no] [$no]
        | {[да] нет [все]}
        | {[да] нет [наверное]}
        | {[да] нет [ясно]}         
        | [$no] ничего
        | {$no (конечно/конешно/канешна/*пасиб*/пока)}
        | [$no] не сейчас
        | [$no] ни капли
        | [$no] отнюдь
        | [$no] нискол*
        | {$no не (@Want/@Need/думаю/нравится/стоит/буду/считаю/согла*/подтв*)}
        | [$no] [все] не (@Want/@Need/думаю/нравится/стоит/буду/считаю/согла*/подтв*)
        | [$no] [все] ни (@Want/@Need/думаю/нравится/стоит/буду/считаю/согла*/подтв*)
        | [$no] ненадо
        | [$no] нельзя
        | [$no] нехочу
        | [$no] ненавижу
        | [$no] невозможно
        | [$no] ни за что
        | [$no (я/мы)] против
        | [$no] вряд ли
        | [$no] сомневаюсь
        | [$no] нихрена
        | [$no] нихера
        | [$no] @Wrong
        | [$no] ненужно
        | [$no] отказ*
        | [$no] ни в коем случае
        | [$no] ответ $no
        | [$no] {ответ отрицательный}
        | {[$no] неа}
        | [$no] {[*пасиб*] все ясно }*
        | [$no] {[*пасиб*] все понятно }*
        | [$no] {[*пасиб*] все [я/мы] понял* }*
        | [$no] не так
        | [$no] нифига
        | [$no] никак $no
        | ни как $no
        | не как $no
        | (нт / ytn / нетт )
        | {$no * (@Say/@Talk)} )    
        
    $totally = (абсолютно/полностью/совершенно/максимально)
    $never = (никогда/ни ко гда/ни ка гда/не ко гда/нмкогда/ниуогда/никогла/ни в коем (случае/разе)/ни за что [на свете])
    $ok = (ок/ok/okay/окей/оукей/оук/окис/окич/окс/окай/оки/оке)    
    
    $yesForSms = ( { [$yes] @Give [@SMS/ссылк*/сообщен*/~телеграмма] }
        | {[$yes] @Need [@SMS/ссылк*/сообщен*/~телеграмма] }
        | {[$yes] @Want [@SMS/ссылк*/сообщен*/~телеграмма] }
        | $yes (@SMS/ссылк*/сообщен*/~телеграмма)
        | [$yes] {[$pls] отправ*}
        | [$yes] {[$pls] *сылать}
        | [$yes] {[$pls] *сылай}
        | [$yes] {[$pls] *сылайте}
        | [$yes] {[$pls] пошли*}
        | [$yes] {[$pls] пришли*}
        | [$yes] {[$pls] *слать}
        | [$yes] соглас*
        | [$yes] получит*
        | [$yes] интерес*
        | {(конечно|конешно|канешна) @Give [@SMS/ссылк*/сообщен*/~телеграмма] }
        | {(конечно|конешно|канешна) @Need [@SMS/ссылк*/сообщен*/~телеграмма] }
        | {(конечно|конешно|канешна) @Want [@SMS/ссылк*/сообщен*/~телеграмма] }
        | (конечно|конешно|канешна) {[$pls] отправ*}
        | (конечно|конешно|канешна) {[$pls] *сылать}
        | (конечно|конешно|канешна) {[$pls] *сылай}
        | (конечно|конешно|канешна) {[$pls] *сылайте}
        | (конечно|конешно|канешна) {[$pls] пошли*}
        | (конечно|конешно|канешна) {[$pls] пришли*}
        | (конечно|конешно|канешна) {[$pls] *слать}
        | (конечно|конешно|канешна) соглас*
        | (конечно|конешно|канешна) получит*
        | (конечно|конешно|канешна) интерес*
        | отправ* на этот номер 
        | *сылать на этот номер
        | *сылай на этот номер
        | *сылайте на этот номер
        | пошли* на этот номер
        | пришли* на этот номер
        | *слать на этот номер)
        
    $noForSms = ( { [$no] (не @Need) [@SMS/ссылк*/сообщен*/~телеграмма] }
        | { [$no] (не @Want) [@SMS/ссылк*/сообщен*/~телеграмма] }
        | $no (@SMS/ссылк*/сообщен*/~телеграмма)
        | [$no] не отправ*
        | [$no] не *сылать
        | [$no] не *сылай
        | [$no] не *сылайте
        | [$no] не пошли*
        | [$no] не *слать
        | [$no] не соглас*
        | [$no] не получит*
        | [$no] не интерес*
        | { (конечно|конешно|канешна) (не @Need) [@SMS/ссылк*/сообщен*/~телеграмма] }
        | { (конечно|конешно|канешна) (не @Want) [@SMS/ссылк*/сообщен*/~телеграмма] }  )
    
    $yesAtHome = ( [$oneWord] нахожусь [$oneWord/дома]
        | [$yes] {[$oneWord] [$oneWord] (мы/я)}  дома
        | {[$yes] [$oneWord] [$oneWord]} дома
        | [$yes] [$oneWord] (квартир*/комнат*)
        | [$yes] [$oneWord] готов*
        | [$yes] {есть возможност*} 
        | [$no] {(мы/я) [уже] дома}  )
        
    $noMatchAtHome = ( {(мы/я) звон*} )
        
    # нет меня дома/нет как раз дома/нет нас сейчас дома
    $noAtHome = ( [$oneWord] не нахожусь [$oneWord/дома]
        | [$oneWord] не находимся [$oneWord/дома]
        | {(не/нет/нету) [$oneWord] (меня/нас/мы/я) } дома
        | {(нет/нету) [$oneWord] (меня/нас/никого) }
        | { $no не [$oneWord] } дома
        | [$no] (не/нет/нету) дома
        | [мы/я] не в квартир*
        | [$no] не готов*
        | [$no] {нет возможност*}
        | [$no] [мы/я] не в комнат*
        | ($no/не) {(меня/нас/мы/я) $relations *просил* *звони* } 
        | [$no] (там/здесь) {$relations [@My] (жив*/прожив*)} 
        | {(на работе) [я/мы]} 
        | у сосед*
        | в магазине
        | $regexp<отоше?л(а|и)?> )    
    
    $yesAccess = ({[$yes] восстановил*}
        | {[$yes] появил*}
        | {$yes доступ*}
        | {есть доступ*}
        | {$yes услуг*}
        | [$yes] {(все/всё) работает}
        | [$yes] {услуг* (работает/работают/заработал*)}
        | [$yes] {(все/всё) восстановилось}
        | [$yes] {услуг* восстановил*}
        | [$yes] {доступ* восстановил*}
        | [$yes] {@AllServices восстановил*}
        | [$yes] {услуг* появил*}
        | [$yes] {доступ* появил*}
        | [$yes] {(все/всё) заработало}
        | [$yes] {доступ* (работает/работают/заработал*)}
        | [$yes] {@AllServices (работает/работают/заработал*)}
        | [$yes] {@AllServices появил*}
        | [$yes] (есть/появил*/работает/работают/заработал*/восстановил*)
        | [$yes] (решил*/заработал*)
        | * { проблем* (нет/не сохран*/ушл*) }
        | [$yes] появил*)
        
    $noAccess = ({[$no] не восстановил*}
        | {[$no] не появил*}
        | {[$no] доступ*}
        | [$no] не доступ*
        | {[$no] услуг*}
        | [$no] не изменил*
        | [$no] {(все/всё/ничего/нихрена/нихера/нифига) не работает}
        | [$no] {@Services не (работает/работают/заработал*)}
        | [$no] {(все/всё/ничего/нихрена/нихера/нифига) не восстановилось}
        | [$no] {@Services не восстановил*}
        | [$no] {доступ* не восстановил*}
        | [$no] {@Services не появил*}
        | [$no] {доступ* не появил*}
        | [$no] {(все/всё/ничего/нихрена/нихера/нифига) не заработало}
        | [$no] {доступ* не (работает/работают/заработал*)}
        | [$no] (нету/не появил*/не работает/не работают/не заработал*/не восстановил*)
        | [$no] не решил*
        | [$no] {@DomRu не восстановил*}
        | [$no] {@DomRu не (работает/работают/заработал*)}
        | [$no] {@DomRu не появил*}
        | * { проблем* (есть/сохран*/остал*) }
        | [$no] { ситуац* не изменил* } [$no] )    
    
    $yesOperator = ( {[$yes]  (соедин*/перевед*/свяж*/связ*)}
        | {[$yes]  (*зови/*зовите/*звать/*зовешь)}
        | {[$yes]  (приглас*/приглаш*)}
        | {[$yes]  срочно}
        | {[$yes]  (@Operator/~люди/сотрудник*/коллег*)}
        | {[$yes]  *консультироваться}
        | {[$yes]  позови*}
        | {[$yes]  переключи*})
        
    $yesForPromisedPayment = ( { [$yes] подключ* }
        | [$yes] { подключ* услуг* }
        | [$yes] { подключ* @PromisedPayment }
        | [$yes]  @PromisedPayment 
        | [$yes] { @Modal @PromisedPayment }
        | [$yes] @Modal
        | [$yes] @Give 
        | [$yes] интерес* )
        
    $noForPromisedPayment = ( {[$no] (не подключ*)}  
        | {[$no] {(не подключ*) услуг*}}
        | {[$no] {(не подключ*) @PromisedPayment}}
        | {[$no] (не @Modal @PromisedPayment)}
        | {[$no] [уже/больше] (не (@Modal/@Give/@Need/@Want))}
        | {[$no] {[уже] [я/мы] передумал*}}
        | {[$no] {[мне/нам] [уже] (неинтерес*/не интерес*)}}
        | {[$no] [уже] (не (согласен/согласн*/согласовыва*))}
        | {[слишком/черечур/уж очень] дорого*}
        | уж очень много )     
        
    $yesForPaymentMethods = ( { [$yes] *скажи* }
        | { [$yes] *сказа* }
        | { [$yes] рассказы* }
        | { [$yes] *говори* }
        | { [$yes] *назови* }
        | { [$yes] *назыв* }
        | [$yes] { какие есть }
        | { [$yes] * способ* *плат* } 
        | [ну] давай* )   
        
    $yesForConsultationAboutPackages = ( { [$yes] @Want }
        | { [$yes] * консульт* }
        | [$yes] { @Give * консульт* }
        | [$yes] {@Need * консульт* }
        | { [$yes] @Need }
        | { [$yes] @Can }
        | { [$yes] получ* }
        | [$yes] соглас* 
        | [$yes] чего (сразу/ради/это/$oneWord) не подключ* то 
        | [$yes] что (сразу/ради/это/$oneWord) не подключ* то
        | [$yes] че (сразу/ради/это/$oneWord) не подключ* то 
        | [$yes] как (это/$oneWord) не подключ* [то])
        
    $noForConsultationAboutPackages =  ( [$no] не @Want
        | { [$no] $no * консультац* }
        | { [$no] не @Give * консультац* }
        | {[$no] не @Need * консультац* }
        | [$no] не соглас* )    
        
    $yesForOpeningPages = ({ [$yes] (открываются/открыл*) }
        | { [$yes] (работает/заработал*) }
        | { [$yes] все (открываются/открыл*) }
        | { [$yes] все (работает/заработал*) }
        | [$yes] { @Internet * (открываются/открыл*/открывает*) }
        | [$yes] { @Internet * (работает/заработал*/*ключает*/*ключил*) }
        | [$yes] { @Website * (открываются/открыл*/открывает*) }
        | [$yes] { @Website * (работает/заработал*/*ключает*/*ключил*) }
        | [$yes] { сесси* * (открываются/открыл*/открывает*) }
        | [$yes] { сесси* * (работает/заработал*/*ключает*/*ключил*) }
        | [$yes] (установилось/установилась))    
    
    $yesForNewService = ( { [$yes] (подключит*/подключай*) }
        | { [$yes] (подключит*/подключай*) [услуг*] }
        | { [$yes] нов* услуг* }
        | [$yes] @Modal
        | [$yes] @Give )
        
    $noForNewService = ( [$no] (не подключит*/не подключай*)
        | [$no] { (не подключит*/не подключай*) * [услуг*] }
        | [$no] { (не @Modal) [нов*] услуг* }
        | [$no] не @Give
        | [$no] неправильно 
        | [$no] [я] (не @Want) (подключит*/подключат*) [нов* услуг*] )    
        
    $yesForPromo = ( { [$yes] (подключит*/подключай*) }
        | { [$yes] (подключит*/подключай*) * (@Discount/акци*) }
        | { [$yes] (подключит*/подключай*) * оптом дешевле }
        | { [$yes]  * оптом дешевле }
        | { [$yes] @Modal * оптом дешевле }
        | [$yes] @Modal
        | [$yes] @Give
        | { [$yes] (включит*/включай*) } )    
    
    $allChannels = ( { все [@TVChannelPlural] } ) 
        
    $noChannels = ( {(все/на всех/никакие/никакой/ничего/нихрена/нихера/нифига) [не (работает/работают/заработал*/*ключает*/*ключил*/открываются/открыл*/открывает*)]}
        | {картинк* (не (появил*/появл*/показывает/показывают/нет))} 
        | { все [@TVChannelPlural] (не показыв*) } 
        | (ни один/ни 1/0) [@TVChannelSingular] )
    
    $partOfTheChannels = ( { некотор* @TVChannelPlural }
        | { часть [@TVChannelPlural] }
        | { на части [@TVChannelPlural] }
        | { половин* [@TVChannelPlural] }
        | { (не все) @TVChannelPlural }
        | { (не [на] всех) @TVChannelPlural }
        | { (мало/меньше) @TVChannelPlural }
        | { (немног*/немнож*) @TVChannelPlural } 
        | { чуть чуть @TVChannelPlural } 
        | (@duckling.number/пара/тройка) [@TVChannelSingular/@TVChannelPlural] 
        | [@TVChannelSingular/@TVChannelPlural] [[с/под] номер*] @duckling.number )
    
    $oneTVSet = ( { (один/1) [@TVSet] }
        | { только в (комнат*/гостин*/зал*) }
        | { только на кухн* }
        | { (одном/1) [@TVSet] } 
        | на (одном/1) )
    
    $moreThanOneTVSet = ( { несколько [@TVSet] }
        | { (два/2) [@TVSet] }
        | { (три/3) [@TVSet] }
        | { (четыре/4) [@TVSet] }
        | { (пять/5) [@TVSet] }
        | { нескольких [@TVSet] }
        | { всех [@TVSet] }
        | { все [@TVSet] }
        | на всех [@TVSet]
        | на (двух/2) [@TVSet]
        | на обоих [@TVSet]
        | на (трех/3) [@TVSet]
        | на (четырех/4) [@TVSet]
        | на (пяти/5) [@TVSet] )
    
    $noOperator = ([$no]  не * [@Need] * (соедин*/перевед*/свяж*/связ*)
        | [$no] не * [@Need] * (*зови/*зовите/*звать/*зовешь)
        | [$no] не * [@Need] * (приглас*/приглаш*)
        | [$no] не * [@Need] * переключа*
        | [$no] не * @Need * (@Operator/~люди/сотрудник*/коллег*))
        
    $noForPaymentMethods = ( [$no] не *скажи*
        | [$no] не *сказа*
        | [$no] не *говори*
        | [$no] не *говори* 
        | [$no] не *назови*
        | [$no] не *назыв*
        | { $no * способ* *плат* }
        | [$no] не рассказыв* 
        | [$no] не @Need
        | [$no] не @Want )  
    
    $noForOpeningPages = ( { [$no] не открываются }
        | { [$no] не открывается }
        | { [$no] не работает }
        | { [$no] не заработал* }
        | { [$no] не *ключил*}
        | { [$no] не *ключает* }
        | { $no (конечно/конешно/канешна/пока)}
        | [$no] [все] не (@Want/@Need/могу/думаю/нравится/стоит/буду/считаю/согла*/подтв*)
        | [$no] невозможно
        | [$no] вряд ли
        | [$no] сомневаюсь
        | [$no] нихрена
        | [$no] нихера
        | [$no] ответ $no
        | [$no] {ответ отрицательный}
        | {[$no] неа}
        | [$no] не восстановил*
        | [$no] не @ChangePast
        | [$no] { @Internet * не (открываются/открыл*/открывает*) }
        | [$no] { @Internet * не (работает/заработал*/*ключает*/*ключил*) }
        | [$no] { @Website * не (открываются/открыл*/открывает*) }
        | [$no] { @Website * не (работает/заработал*/*ключает*/*ключил*) }
        | [$no] { сесси* * не (открываются/открыл*/открывает*) }
        | [$no] { сесси* * не (работает/заработал*/*ключает*/*ключил*) }
        | [$no] { (ничего/нихрена/нихера/нифига) * не (открываются/открыл*/открывает*) }
        | [$no] { (ничего/нихрена/нихера/нифига) * не (работает/заработал*/*ключает*/*ключил*) }
        | [$no] { не (установилось/установилась) } )
        
    $noForPromo = ( [$no] не (подключит*/подключай*)
        | [$no] { не (подключит*/подключай*) * (@Discount/акци*) }
        | [$no] { не (подключит*/подключай*) * оптом дешевле }
        | [$no] не @Modal * оптом дешевле 
        | [$no] не @Give
        | [$no] не (включит*/включай*))
        
    $cancelServiceRequest = ( [$no] отмена [@Request]
        | [$no] отмен* [@Request]
        | [$no] отменит* [@Request]
        | [$no] убрать [@Request]
        | [$no] убери* [@Request]
        | [$no] удали* [@Request]
        | [$no] аннулир* [@Request]
        | [$no] удален* [@Request]
        | [$no] удали* [@Request]
        | [$no] закрыт* [@Request]
        | [$no] закрой* [@Request]
        | [$no] отказ* [@Request] 
        | [$no] (снять/сними*) [@Request] )
    
    $transferServiceRequest = ( перенос [@Request]
        | перенос* [@Request]
        | перенес* [@Request]
        | изменит* [@Request]
        | передел* [@Request]
        | переоформ* [@Request] ) 
        
    $noQuestions = ( $thanks (не (остались/осталось/остал*))
        | { [больше] $no вопрос* } 
        | (нт / ytn / нетт )
        | больше $no 
        | { [$no] [вопрос*] (никакого/никаких) }
        | { $no [больше] вопрос*} 
        | [$no] { больше [вопрос*] нет }
        | [$no] { больше [вопрос*] нету }
        | { [$no] [вопрос*] {[больше] (не имею/не имеем)} }
        | { [$no] [вопрос*] (не имеются/не имеется) }
        | { [$no] [вопрос*] (не остались/не осталось/не осталс*) }
        | [$no] { (не @Need) помощь }
        | [$no] {ответ отрицательный}
        | [$no] все (ясно/понятно)
        | { $no все }
        | $no все включено
        | {[$no] неа}
        | {[да] нет [все]}
        | { *пасиб* [все] [понятно/ясно] }
        | {[да] нет [[все] понятно]}
        | {[да] нет [наверное]}
        | {[да] нет [[все] ясно]}
        | [$no] ни в коем случае
        | [$no] никак $no
        | [$no] ни как $no
        | [$no] нискол*
        | [$no] ответ $no
        | [а] {все спасибо}
        | [$no] [все] {(разговор/беседа/диалог) (окончен*/законч*)} 
        | [да] { (никакого/никаких) [вопрос*] } [$no]
        | [$no] { (никакого/никаких) [вопрос*] } [$no]
        | { $no (конечно/конешно/канешна/пока)}
        | { $no *пасиб*}
        | { короче $no [$no] } 
        | {спасибо * (получилось/подключилось)} 
        | {[меня] все устраивает} 
        | [премного] благодар* 
        | $callYouBack 
        | если ( заработ* / { [будет] работ*} ) { [больше] $no вопрос*}
        | если ( заработ* / { [будет] работ*} ) * спасибо )

    $yesQuestions = ( [$no] { [вопрос*] (остались/осталось/осталс*) }
        | { [$yes] [вопрос*] (остались/осталось/осталс*) }
        | { [$yes] [вопрос*] (хотя /до сих пор/ есть) }
        | $no * {(имею/имеем) [вопрос*]}
        | $no * {(имеются/имеется) [вопрос*]}
        | [$yes] (давай|давайте|логично)
        | [$yes] (еще|ещё) как
        | [$yes] (конечно|конешно|канешна)
        | [$yes] а как же
        | [$yes] [все] (верно/правильноj)
        | [$yes] непременно 
        | [$yes] обязательно 
        | [$yes] подтвержда*
        | [$yes] помоги*
        | {[$yes] @Need помощь }
        | [$yes] разумеется
        | {манда $yes}
        | lf 
        | [$yes] естественно
        | [какими] другими словами 
        | не договори*
        | так точно 
        | { (не получил*) * [никак*] ответ*}
        | {вопрос* $no} {вы [@My] (не ответили) ([на] вопрос*) }
        | {вы [@My] (не ответили) * вопрос* }
        | {спасибо * (не (получилось/подключилось))} 
        | { вы мне (не помогл*) }
        | $notUnderstand 
        | $noPersonalAccount
        | $repeat
        | $notReceivedSMS
        | $yesAtHome
        | $noCard
        | другими словами 
        | { [у (меня/нас)] (нарисовался/нарисовались) вопрос* } 
        | [а] {$regexp<(под|с)кажи(те)?> $pls}) 
        
    $anotherQuestion = ( {еще кое-что}
        | {~хотеть [бы] [еще] (кое-что/кое о чем) (уточнить/спросить/@Know) [[у] (тебя/вас)]}
        | {@Want [бы] [еще] (кое-что/кое о чем) (уточнить/спросить/@Know) [[у] (тебя/вас)]}
        | {[у меня/{[у меня] ~есть}/~хотеть [бы]/~мочь] [еще] [один [важный]/[один] важный/другой/много] вопрос* [задать] [[к] (тебе/вам)]}
        | [я/мы/у меня] [по] друг* вопрос* 
        | (другой/по другому)
        | [$yes] (еще|ещё) * вопрос*
        | [$yes] {есть (еще|ещё) вопрос*}
        | [$yes] {есть вопрос*}
        | [$yes] да вопрос*
        | {$no вопрос*} * (только/единственное)
        | {$no вопрос*} * 1 (момент/вопрос*/деталь)
        | {$no вопрос*} * (хотя /до сих пор/есть) 
        | {[$yes] вопрос* (остались/осталось/осталс*) }
        | {[$yes] вопрос* * (имею/имеем)}
        | {[$yes] вопрос* * (имеются/имеется)} ) 
        
    $notUnderstand = ( { (не поймешь) [ничего] (что @Say) }
        | { (не поймешь) [ничего] (что @Talk) } 
        | { (не понял*) [ничего] (что @Say) } 
        | { (не понял*) [ничего] (что @Talk) }
        | { (не понят*) [ничего] (что @Say) }
        | { (не понят*) [ничего] (что @Talk) }
        | { (я/мы) нискол* (не понял*) } 
        | { (я/мы) нифига (не понял*) } 
        | { (я/мы) нихера (не понял*) } 
        | { (я/мы) нихрена (не понял*) }
        | { (я/мы) ничего (не понял*) } 
        | не понял*
        | не @Can понять )
        
    $noPersonalAccount =  ( { у меня (нет/нету) * @PersonalAccount } 
        | { у нас (нет/нету) * @PersonalAccount } )
    
    $noCard = ( {$no карт*} 
        | {$no (никак*/ни 1) карт*}  
        | {(ни карт*) ничего} ) 
        
    $repeat = ( {повтори* ((свой/ваш) ответ) [$pls]}
        | {повтори* ((свой/ваш) вопрос) [$pls]}
        | {повтори* [$pls] (не поймешь)}
        | {повтори* [$pls] (не понял*)}
        | {повтори* [$pls] (не понят*)}
        | {повтори* [$pls] непонят*})
        
    $callYouBack = ( { [я/мы] (перезвоню / перезвоним) [@duckling.time / еще раз / *позже / позднее ] }
        | если * {[будет] @Need} * (перезвоню/перезвоним) )
        
    $notReceivedSMS = ( {[$yes] [$no]} {(вы/@My) * (~ссылка/~сообщение/@SMS/~телеграмма) (отправлял*/отправил*)}
        | {[$yes] [$no]} * {(~ссылка/~сообщение/@SMS/~телеграмма) (получил*/пришл*/приход*)}
        | {(~ссылка/~сообщение/@SMS/~телеграмма) [никак*/ни 1] (не получил*/не пришл*/не приход*)}
        | {[$yes] [$no]} * {(~ссылка/~сообщение/@SMS/~телеграмма) * (жду/ждем/ждать)}
        | {[$yes] [$no]} * {(~ссылка/~сообщение/@SMS/~телеграмма) * посмотр* }
        | [$no] * {(не @Can) найти (~ссылка/~сообщение/@SMS/~телеграмма)}
        | {($no/нету) [никак*/ни 1] (~ссылка/~сообщение/@SMS/~телеграмма)} )
        
    $notClient = ( [$no] (не/ни) являюсь
        | [$no] (не/ни)
        | [$no] (не/ни) являемся
        | [$no] (не/ни) ваш
        | [$no] { ((не/ни) ваш) $client }
        | [$no] { я (не/ни) $client }
        | [$no] { нов* $client }
        | {[$no] ((не/ни) явля*) $client * }
        | [$no] (не/ни) $client 
        | [$no] (не/ни) пользу*
        | [$no] не (пользуюсь/пользуемся)
        | [$no] (не/ни) иcпользу* 
        | [$no] * @Modal подключ* [@AllServices/$oneWord] 
        | [$no] {@My @Modal подключ*}
        | [$no] раньше *пользовал* 
        | [$no] [oneWord] (не пользу*) * @Services * вашей компании
        | { $no @SynonymsForAgreement } 
        | [$no] { (не оформлен*) @SynonymsForAgreement } 
        | [$no] { (не заключ*) @SynonymsForAgreement } 
        | [$no] { (не подписа*) @SynonymsForAgreement } )
        
    $newClient = ( $regexp<новы[йм]> $client 
        | {(@Want [стать/быть]) ([~ваш [~новый]] $client)}
        | {(@Want ([начать] $regexp<(ис)?пользовать(ся)?>)) ([~ваш] (услуг*/@Internet/@Television))} )
    
    $yesClient = ( [$yes] являюсь 
        | [$yes] являемся
        | [$yes] { я $client }
        | [$yes] пользу* $weight<+0.5>
        | [$yes] использу*
        | [$yes] { использу* ~ваш @Services }
        | { [$yes] * явля* * $client * }
        | [$yes] * $client
        | { [$yes] ( (мой/наш) @SynonymsForAgreement ) }
        | [$yes] * уже пользу* 
        | [$no] * уже (подключен*/подключил*) [@AllServices/$oneWord]
        | [$yes] [$oneWord] пользу* * @Services * вашей @Company
        | * пользу* * [ваш*] @Services * [вашей] компании
        | { @SynonymsForAgreement [заключ*/оформл*/запис*/припис*/подпис*] (я/на меня/[со] мной/на мое имя) }
        | { (стоит/установлен*/подключен*/устанавливал*/устонавливал*/подключал*) * (~ваш (@Internet/@Television/@Decoder/@Router/@Company) ) }
        | { (у (меня/нас)) [@Internet] (@DomRu/акадо) }
        | { (~ваш (@Internet/@Television/@Decoder/@Router/@Company) ) (у (меня/нас)) } 
        | [$yes] { (оформлен* на) @SynonymsForAgreement } 
        | [$yes] { (заключ* на) @SynonymsForAgreement } 
        | [$yes] { подписа* @SynonymsForAgreement } )
    
    