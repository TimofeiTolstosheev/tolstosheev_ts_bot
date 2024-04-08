patterns:

    $agreementNumber = (* { (войти/зайти/входить/заходить) * номер* * @SynonymsForAgreement } *
        |  * { (вышлите/высылка/присылает*/пришли*/шлите/шлёте/посылка/передай*) * номер* * @SynonymsForAgreement } *
        |  * { (говори*/переговори*) * номер* * @SynonymsForAgreement } *
        |  * { (забыл*/забыть) * номер* * @SynonymsForAgreement } *
        |  * { (какая/какой/какие) * номер* * @SynonymsForAgreement } *
        |  * { (кинуть/скинуть/кидайте) * @SMS * номер* @SynonymsForAgreement } *
        |  * { (кинуть/скинуть/кидайте) * сообщени* * номер* @SynonymsForAgreement } *
        |  * { (кинь*/скинь*/скидывай*) * @SMS * номер* * @SynonymsForAgreement } *
        |  * { (кинь*/скинь*/скидывай*) * @SMS * номер* * счет* } *
        |  * { (вышлите/высылка/присылает*/пришли*/шлите/шлёте/посылка/передай*) * @SMS * @SynonymsForAgreement } *
        |  * { (вышлите/высылка/присылает*/пришли*/шлите/шлёте/посылка/передай*) * сообщени* * @SynonymsForAgreement } *
        |  * { (вышлите/высылка/присылает*/пришли*/шлите/шлёте/посылка/передай*) * @SMS * @SynonymsForAgreement } *
        |  * { (вышлите/высылка/присылает*/пришли*/шлите/шлёте/посылка/передай*) * @SMS * (номер* * котор* * (*плат*/*плач*)) } *
        |  * { (вышлите/высылка/присылает*/пришли*/шлите/шлёте/посылка/передай*) * сообщени* * (номер* * котор* * (*плат*/*плач*)) } *
        |  * { (вышлите/высылка/присылает*/пришли*/шлите/шлёте/посылка/передай*) * @SMS * (номер* * котор* * (*плат*/*плач*)) } *
        |  * { (@Wrong/некорректн*/ошиб*) * номер* * @SynonymsForAgreement } *
        |  * { (потеря*/утеря*) * номер* * @SynonymsForAgreement } *
        |  * { @Modal * номер* * @SynonymsForAgreement } *
        |  * { @Modal * номер* * счет* } *
        |  * { восстановит* * номер* * @SynonymsForAgreement } *
        |  * { напомни* * номер* * @SynonymsForAgreement } *
        |  * { не помню * номер* @SynonymsForAgreement } *
        |  * { номер* * @SynonymsForAgreement } *
        |  * номер* * котор* * (*плат*/*плач*) *
        |  * { номер* * @SynonymsForAgreement * (адрес*/улиц*/телефон*/@FIO) } *
        |  * { номер* * @SynonymsForAgreement * личн* кабинет* } *
        |  * { отправ* * @SMS * номер* * @SynonymsForAgreement } *
        |  * { отправ* * @SMS * номер* * счет* } *
        |  * { повтори* * номер* * @SynonymsForAgreement } *
        |  * { покаж* * номер* * @SynonymsForAgreement } *
        |  * { @Know * номер* * [свой/своего/мой/моего/наш/нашего/ваш/вашего] * @SynonymsForAgreement } *
        |  * { уточни* * номер* * @SynonymsForAgreement } *
        |  * номер* @SynonymsForAgreement *
        |  * @Modal номер* @SynonymsForAgreement чтоб* перечисл* @duckling.amount-of-money *)

    $stopAgreement = ( * {*рвать * отношени*} *
        | * { * отключайте * @AllServices} *
        | * { * отключайте * @SynonymsForAgreement} *
        | * { * отключаться * @AllServices} *
        | * { * отключаться * @SynonymsForAgreement} *
        | * { * отключите * @AllServices} *
        | * { * отключите * @SynonymsForAgreement} *
        | * { * отключить * @AllServices} *
        | * { повыш* стоимост* } * { * отключ* * @AllServices} *
        | * { * отключить * @SynonymsForAgreement} *
        | * { * отключиться * @AllServices} *
        | * { * отключиться * @SynonymsForAgreement} *
        | * { * отключусь * @AllServices} *
        | * { * отключусь * @SynonymsForAgreement} *
        | * {(заблокировать/заблокируй*) * @AllServices} *
        | * {@Modal * убери* * @Internet} *
        | * {@Modal * убери* * @Telephone} *
        | * {@Modal * убери* * @Television} *
        | * {@Modal * убрать * @Internet} *
        | * {@Modal * убрать * @Telephone} *
        | * {@Modal * убрать * @Television} *
        | * {*рвать * @Services} *
        | * {*рвать * @SynonymsForAgreement} *
        | * {аннулир* * @Services} *
        | * {аннулир* * @SynonymsForAgreement} *
        | * {аннулир* * отношени*} *
        | * {вообще * убери* * @Internet} *
        | * {вообще * убери* * @Telephone} *
        | * {вообще * убери* * @Television} *
        | * {вообще * убрать * @Internet} *
        | * {вообще * убрать * @Telephone} *
        | * {вообще * убрать * @Television} *
        | * {вопрос* * отказ* * @Services} *
        | * {вопрос* * отказ* * @SynonymsForAgreement} *
        | * {выбра* * @Competitors} *
        | * {выбра* * друго* * @Company} *
        | * {выбра* * друго* * конкурент*} *
        | * {выбра* * друго* * оператор*} *
        | * {выбра* * нов* * @Company} *
        | * {выбра* * нов* * конкурент*} *
        | * {выбра* * нов* * оператор*} *
        | * {заблокир* * @SynonymsForAgreement} *
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
        | * {закрыти* * @SynonymsForAgreement номер* @duckling.number } *
        | * {закрыти* * @SynonymsForAgreement} *
        | * {закрыть * @AllServices} *
        | * {закрыть * @SynonymsForAgreement} *
        | * {запрос* * отказ* * @AllServices} *
        | * {запрос* * отказ* * @SynonymsForAgreement} *
        | * {@Request * отказ* * @AllServices} *
        | * {@Request * отказ* * @SynonymsForAgreement} *
        | * {(@Change/@ChangePast) * @Competitors} *
        | * {изменить * @Company} *
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
        | * {не @Need * @SynonymsForAgreement номер* @duckling.number } *
        | * {не пользу* * @AllServices} *
        | * {не пользу* * @SynonymsForAgreement номер* @duckling.number } *
        | * {откаж* * от * @AllServices} *
        | * {откаж* * от * @SynonymsForAgreement} *
        | * {отказ * @SynonymsForAgreement номер* @duckling.number } *
        | * {отказ * от * @AllServices} *
        | * {отказ * от * @SynonymsForAgreement} *
        | * {отказаться * от * @AllServices} *
        | * {отказаться * от * @SynonymsForAgreement} *
        | * {отказыва* * от * @AllServices} *
        | * {отказыва* * от * @SynonymsForAgreement} *
        | * {отказываться * от * @AllServices} *
        | * {отказываться * от * @SynonymsForAgreement} *
        | * {отключ* * @SynonymsForAgreement номер* @duckling.number } *
        | * {отключени* * @AllServices} *
        | * {отключени* * @SynonymsForAgreement номер* @duckling.number } *
        | * {перейдем * (на/к) * @Company} *
        | * {перейдем * (на/к) * конкурент*} *
        | * {перейдем * (на/к) * @Competitors} *
        | * {перейдем * от * @DomRu} *
        | * {перейдем * от * вашей @Company} *
        | * {перейти * (на/к) * @Company} *
        | * {перейти * (на/к) * конкурент*} *
        | * {перейти * (на/к) * @Competitors} *
        | * {перейти * от * @DomRu} *
        | * {перейти * от * вашей @Company} *
        | * {переключ* * друго* * @Company} *
        | * {переключ* * друго* * конкурент*} *
        | * {переключ* * (на/к) * @Competitors} *
        | * {переключ* * друго* * оператор*} *
        | * {переманил* * @Competitors} *
        | * {переход* * (на/к) * @Company} *
        | * {переход* * (на/к) * конкурент*} *
        | * {переход* * (на/к) * @Competitors} *
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
        | * {расторжени* * @SynonymsForAgreement номер* @duckling.number } *
        | * {@GoAway * (на/к) * @Company} *
        | * {@GoAway * (на/к) * конкурент*} *
        | * {@GoAway * (от/из) * (@DomRu/вас/~ваш)} *
        | * {@GoAway * (от/из) * вашей @Company} *
        | * {закрыл* * @SynonymsForAgreement} * {как * *платить} *
        | * {закрыл* * @SynonymsForAgreement} * {как * *плачивать} *
        | * {расторг* * @SynonymsForAgreement} * {как * *платить} *
        | * {расторг* * @SynonymsForAgreement} * {как * *плачивать} *
        | * {расторж* * @SynonymsForAgreement} * {как * *платить} *
        | * {расторж* * @SynonymsForAgreement} * {как * *плачивать} *
        | * {убрал* * @SynonymsForAgreement} * {как * *платить} *
        | * {убрал* * @SynonymsForAgreement} * {как * *плачивать} *
        | * {удал* * @SynonymsForAgreement} * {как * *платить} *
        | * {удал* * @SynonymsForAgreement} * {как * *плачивать} *     )

    $agreementSuspend = ( @Suspension 
        | * { @Give * приостановит* } *
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
        | * { @Suspension * отдых* } *
        | * { @Suspension * отпуск* } *
        | * { @Suspension * @Payment } *
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
        | * { @Suspension * @Tariff } *
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
        | * { временно * $regexp<отключ(ить|иться|ение|ения)> } *
        | * { на время * $regexp<отключ(ить|иться|ение|ения)> } *
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
        | * { приостанов* * @AllServices } *
        | * { приостанов* * @Services * (@Internet/@Telephone/@Television) } *
        | * { приостановит* * @SynonymsForAgreement } *
        | * { приостанов* * до @duckling.number * ~дата } *
        | * { приостанов* * до @duckling.number * ~день } *
        | * { приостанов* * до @duckling.number * месяц* } *
        | * { приостанов* * до @duckling.number * числ* } *
        | * { приостанов* * до @Months } *
        | * { приостанов* * на @duckling.number * ~дата } *
        | * { приостанов* * на @duckling.number * ~день } *
        | * { приостанов* * на @duckling.number * месяц* } *
        | * { приостанов* * на @duckling.number * числ* } *
        | * { приостанов* * на @Months } *
        | * { приостанов* * по @duckling.number * ~дата } *
        | * { приостанов* * по @duckling.number * ~день } *
        | * { приостанов* * по @duckling.number * месяц* } *
        | * { приостанов* * по @duckling.number * числ* } *
        | * { приостанов* * по @Months } *
        | * { приостанов* * с @duckling.number * ~дата } *
        | * { приостанов* * с @duckling.number * ~день } *
        | * { приостанов* * с @duckling.number * месяц* } *
        | * { приостанов* * с @duckling.number * числ* } *
        | * { приостанов* * с @Months } *
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
        | * { @Know * @Suspension } *
        | * { @Request * @Suspension } *
        | * { @Request * подключ* @Suspension } *)

    $agreementSuspendReason = ( * { не @Need } *
        | * { высок* (цен*/стоимост*/@Payment) } *
        | * { менять * провайдер* } *
        | * { поменять * провайдер* } *
        | * { (@Change/@ChangePast) * провайдер* } *
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
        | * { (@Change/@ChangePast) * @Competitors } *
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
        | * { заключ* * @Competitors } * )

    $cancelSuspend  = (  * { выключ* * @Suspension } *
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
        | * { удали* * @Suspension } *) 

    $otherContract = ( * { [подключен* $weight<-1>] @AllServices * (друг* * договор*) } *
        | * { [подключен* $weight<-1>] @AllServices * (по друг* * контракт*) } *
        | * { [подключен* $weight<-1>] @AllServices * (по друг* * аккаунт*) } *
        | * { [подключен* $weight<-1>] @AllServices * (по друг* * лицев* счет*) } *
        | * { [подключен* $weight<-1>] @AllServices * (друг* * адрес*) } *
        | * { [подключен* $weight<-1>] @AllServices * ((втор*/2) * договор*) } *
        | * { [подключен* $weight<-1>] @AllServices * ((втор*/2) * контракт*) } *
        | * { [подключен* $weight<-1>] @AllServices * ((втор*/2) * аккаунт*) } *
        | * { [подключен* $weight<-1>] @AllServices * ((втор*/2) * лицев* счет*) } *
        | * { [подключен* $weight<-1>] @AllServices * ((втор*/2) * адрес*) } *
        | * { @AllServices * (ещё (одном*/одним*) * договор*) } *
        | * { @AllServices * (ещё (одном*/одним*) * контракт*) } *
        | * { @AllServices * (ещё (одном*/одним*) * аккаунт*) } *
        | * { @AllServices * (ещё (одном*/одним*) * лицев* счет*) } *
        | * { @FIO * (друг* * договор*) } *
        | * { @FIO * (друг* * контракт*) } *
        | * { @FIO * (друг* * аккаунт*) } *
        | * { @FIO * (друг* * лицев* счет*) } *
        | * { @FIO * ((втор*/2) * договор*) } *
        | * { @FIO * ((втор*/2) * контракт*) } *
        | * { @FIO * ((втор*/2) * аккаунт*) } *
        | * { @FIO * ((втор*/2) * лицев* счет*) } *
        | * { @FIO * (ещё (одн*/один) * договор*) } *
        | * { @FIO * (ещё (одн*/один) * контракт*) } *
        | * { @FIO * (ещё (одн*/один) * аккаунт*) } *
        | * { @FIO * (ещё (одн*/один) * лицев* счет*) } *
        | * { номер* друг* * договор* } *
        | * { номер* (втор*/2) * договор* } *
        | * { номер* друг* * контракт* } *
        | * { номер* (втор*/2) * контракт* } *
        | * { номер* друг* * аккаунт* } *
        | * { номер* (втор*/2) * аккаунт* } *
        | * { номер* друг* * лицев* счет* } *
        | * { номер* (втор*/2) * лицев* счет* } *
        | * { номер* ещё (одн*/один) * договор* } *
        | * { номер* ещё (одн*/один) * контракт* } *
        | * { номер* ещё (одн*/один) * аккаунт* } *
        | * { номер* ещё (одн*/один) * лицев* счет* } * 
        | * { номер* @SynonymsForAgreement * (друг* @Place) } *
        | * { номер* @SynonymsForAgreement * ((втор*/2) @Place) } * 
        | * { @Know * (номер* @SynonymsForAgreement) * (друг* адрес*) } *
        | * { @Know * (номер* @SynonymsForAgreement) * ((втор*/2) адрес*) } * )
        
    $reissueAgreement = ( * [как] * { переоформ* * (@Tariff/счет*) * (нов*/друг*) * $client } *
        | * {[@Operator $weight<+0.3>] * $regexp<переоформ(ить|иться|ление|ления|лению)> } *
        | * { *заключ* * (нов*/друг*) * $arenda } *
        | * { *заключ* * (нов*/друг*) * $client } *
        | * { *заключ* * [нов*/друг*] * $relatives } *
        | * { *заключен* * (нов*/друг*) * $arenda } *
        | * { *заключен* * (нов*/друг*) * $client } *
        | * { *заключен* * [нов*/друг*] * $relatives } *
        | * { (@Change/@ChangePast) * (нов*/друг*) * $arenda } *
        | * { (@Change/@ChangePast) * (нов*/друг*) * $client } *
        | * { (@Change/@ChangePast) * [нов*/друг*] * $relatives } *
        | * { *оформи* * (нов*/друг*) * $arenda } *
        | * { *оформи* * (нов*/друг*) * $client } *
        | * { *оформи* * [нов*/друг*] * $relatives } *
        | * { *оформлен* * (нов*/друг*) * $arenda } *
        | * { *оформлен* * (нов*/друг*) * $client } *
        | * { *оформлен* * [нов*/друг*] * $relatives } *
        | * { перевест* * (нов*/друг*) * $arenda } *
        | * { перевест* * (нов*/друг*) * $client } *
        | * { перевест* * [нов*/друг*] * $relatives } *
        | * { передел* * (нов*/друг*) * $arenda } *
        | * { передел* * (нов*/друг*) * $client } *
        | * { передел* * [нов*/друг*] * $relatives } *
        | * { перезапис* * (нов*/друг*) * $arenda } *
        | * { перезапис* * (нов*/друг*) * $client } *
        | * { перезапис* * [нов*/друг*] * $relatives } *
        | * { перезапиш* * (нов*/друг*) * $arenda } *
        | * { перезапиш* * (нов*/друг*) * $client } *
        | * { перезапиш* * [нов*/друг*] * $relatives } *
        | * { перенес* * (нов*/друг*) * $arenda } *
        | * { перенес* * (нов*/друг*) * $client } *
        | * { перенес* * [нов*/друг*] * $relatives } *
        | * { перенос* * (нов*/друг*) * $arenda } *
        | * { перенос* * (нов*/друг*) * $client } *
        | * { перенос* * [нов*/друг*] * $relatives } *
        | * { перепис* * (нов*/друг*) * $arenda } *
        | * { перепис* * (нов*/друг*) * $client } *
        | * { перепис* * [нов*/друг*] * $relatives } *
        | * { перепиш* * (нов*/друг*) * $arenda } *
        | * { перепиш* * (нов*/друг*) * $client } *
        | * { перепиш* * [нов*/друг*] * $relatives } *
        | * { переустанов* * (нов*/друг*) * $arenda } *
        | * { переустанов* * (нов*/друг*) * $client } *
        | * { переустанов* * [нов*/друг*] * $relatives } *
        | * { умер* * $client } *
        | * { умер* * $relatives } *
        | * [@Modal] * { переоформ* * (@Tariff/счет*) * $client } *
        | * [@Modal] * { переоформ* * (@Tariff/счет*) * (лиц*/человек*) } *
        | * [@Modal] * { переоформ* * (@Tariff/счет*) * имя } *
        | * [@Modal] * { переоформ* * (@Tariff/счет*) * фамили*} *
        | * [@Modal] * { переоформ* * (@Tariff/счет*) * на себя} *
        | * [@Modal] * { переоформ* * (@Tariff/счет*) * на меня} *
        | * [@Modal] * { переоформ* * @SynonymsForAgreement * $client } *
        | * [@Modal] * { переоформ* * @SynonymsForAgreement * (лиц*/человек*) } *
        | * [@Modal] * { переоформ* * @SynonymsForAgreement * имя } *
        | * [@Modal] * { переоформ* * @SynonymsForAgreement * фамили*} *
        | * [@Modal] * { переоформ* * @SynonymsForAgreement * на себя} *
        | * [@Modal] * { переоформ* * @SynonymsForAgreement * на меня} *
        | * [как] * { переоформ* * @SynonymsForAgreement * $client } *
        | * [как] * { переоформ* * @SynonymsForAgreement * (лиц*/человек*) } *
        | * [как] * { переоформ* * @SynonymsForAgreement * имя } *
        | * [как] * { переоформ* * @SynonymsForAgreement * фамили*} *
        | * [как] * { переоформ* * @SynonymsForAgreement * на себя} *
        | * [как] * { переоформ* * @SynonymsForAgreement * на меня} *
        | * { *заключ* * @AllServices * (нов*/друг*) * имя } *
        | * { *заключ* * @AllServices * (нов*/друг*) * фамили* } *
        | * { *заключ* * @AllServices * (нов*/друг*) * фио } *
        | * { *заключ* * @Equipment * (нов*/друг*) * имя } *
        | * { *заключ* * @Equipment * (нов*/друг*) * фамили* } *
        | * { *заключ* * @Equipment * (нов*/друг*) * фио } *
        | * { *заключ* * @SynonymsForAgreement * (нов*/друг*) * имя } *
        | * { *заключ* * @SynonymsForAgreement * (нов*/друг*) * фамили* } *
        | * { *заключ* * @SynonymsForAgreement * (нов*/друг*) * фио } *
        | * { (@Change/@ChangePast) * @AllServices * (нов*/друг*) * имя } *
        | * { (@Change/@ChangePast) * @AllServices * (нов*/друг*) * фамили* } *
        | * { (@Change/@ChangePast) * @AllServices * (нов*/друг*) * фио } *
        | * { (@Change/@ChangePast) * @AllServices * на себя } *
        | * { (@Change/@ChangePast) * @AllServices * на меня } *
        | * { (@Change/@ChangePast) * @Equipment * (нов*/друг*) * имя } *
        | * { (@Change/@ChangePast) * @Equipment * (нов*/друг*) * фамили* } *
        | * { (@Change/@ChangePast) * @Equipment * (нов*/друг*) * фио } *
        | * { (@Change/@ChangePast) * @SynonymsForAgreement * (нов*/друг*) * имя } *
        | * { (@Change/@ChangePast) * @SynonymsForAgreement * (нов*/друг*) * фамили* } *
        | * { (@Change/@ChangePast) * @SynonymsForAgreement * (нов*/друг*) * фио } *
        | * { (@Change/@ChangePast) * @SynonymsForAgreement * на себя } *
        | * { (@Change/@ChangePast) * @SynonymsForAgreement * на меня } *
        | * [@Modal] * { @Change * ([номер*] @SynonymsForAgreement) * $arenda } *
        | * { *оформи* * @AllServices * (нов*/друг*) * имя } *
        | * { *оформи* * @AllServices * (нов*/друг*) * фамили* } *
        | * { *оформи* * @AllServices * (нов*/друг*) * фио } *
        | * { *оформи* * @Equipment * (нов*/друг*) * имя } *
        | * { *оформи* * @Equipment * (нов*/друг*) * фамили* } *
        | * { *оформи* * @Equipment * (нов*/друг*) * фио } *
        | * { *оформи* * @SynonymsForAgreement * (нов*/друг*) * имя } *
        | * { *оформи* * @SynonymsForAgreement * (нов*/друг*) * фамили* } *
        | * { *оформи* * @SynonymsForAgreement * (нов*/друг*) * фио } *
        | * { *оформлен* * @AllServices * (нов*/друг*) * имя } *
        | * { *оформлен* * @AllServices * (нов*/друг*) * фамили* } *
        | * { *оформлен* * @AllServices * (нов*/друг*) * фио } *
        | * { *оформлен* * @Equipment * (нов*/друг*) * имя } *
        | * { *оформлен* * @Equipment * (нов*/друг*) * фамили* } *
        | * { *оформлен* * @Equipment * (нов*/друг*) * фио } *
        | * { *оформлен* * @SynonymsForAgreement * (нов*/друг*) * имя } *
        | * { *оформлен* * @SynonymsForAgreement * (нов*/друг*) * фамили* } *
        | * { *оформлен* * @SynonymsForAgreement * (нов*/друг*) * фио } *
        | * { перевест* * @AllServices * (нов*/друг*) * имя } *
        | * { перевест* * @AllServices * (нов*/друг*) * фамили* } *
        | * { перевест* * @AllServices * (нов*/друг*) * фио } *
        | * { перевест* * @AllServices * на себя } *
        | * { перевест* * @AllServices * на меня } *
        | * { перевест* * @Equipment * (нов*/друг*) * имя } *
        | * { перевест* * @Equipment * (нов*/друг*) * фамили* } *
        | * { перевест* * @Equipment * (нов*/друг*) * фио } *
        | * { перевест* * @SynonymsForAgreement * (нов*/друг*) * имя } *
        | * { перевест* * @SynonymsForAgreement * (нов*/друг*) * фамили* } *
        | * { перевест* * @SynonymsForAgreement * (нов*/друг*) * фио } *
        | * { перевест* * @SynonymsForAgreement * на себя } *
        | * { перевест* * @SynonymsForAgreement * на меня } *
        | * { передел* * @AllServices * (нов*/друг*) * имя } *
        | * { передел* * @AllServices * (нов*/друг*) * фамили* } *
        | * { передел* * @AllServices * (нов*/друг*) * фио } *
        | * { передел* * @AllServices * на себя } *
        | * { передел* * @AllServices * на меня } *
        | * { передел* * @Equipment * (нов*/друг*) * имя } *
        | * { передел* * @Equipment * (нов*/друг*) * фамили* } *
        | * { передел* * @Equipment * (нов*/друг*) * фио } *
        | * { передел* * @SynonymsForAgreement * (нов*/друг*) * имя } *
        | * { передел* * @SynonymsForAgreement * (нов*/друг*) * фамили* } *
        | * { передел* * @SynonymsForAgreement * (нов*/друг*) * фио } *
        | * { передел* * @SynonymsForAgreement * на себя } *
        | * { передел* * @SynonymsForAgreement * на меня } *
        | * { перезапис* * @AllServices * (нов*/друг*) * имя } *
        | * { перезапис* * @AllServices * (нов*/друг*) * фамили* } *
        | * { перезапис* * @AllServices * (нов*/друг*) * фио } *
        | * { перезапис* * @AllServices * на себя } *
        | * { перезапис* * @AllServices * на меня } *
        | * { перезапис* * @Equipment * (нов*/друг*) * имя } *
        | * { перезапис* * @Equipment * (нов*/друг*) * фамили* } *
        | * { перезапис* * @Equipment * (нов*/друг*) * фио } *
        | * { перезапис* * @SynonymsForAgreement * (нов*/друг*) * имя } *
        | * { перезапис* * @SynonymsForAgreement * (нов*/друг*) * фамили* } *
        | * { перезапис* * @SynonymsForAgreement * (нов*/друг*) * фио } *
        | * { перезапис* * @SynonymsForAgreement * на себя } *
        | * { перезапис* * @SynonymsForAgreement * на меня } *
        | * { перезапиш* * @AllServices * (нов*/друг*) * имя } *
        | * { перезапиш* * @AllServices * (нов*/друг*) * фамили* } *
        | * { перезапиш* * @AllServices * (нов*/друг*) * фио } *
        | * { перезапиш* * @AllServices * на себя } *
        | * { перезапиш* * @AllServices * на меня } *
        | * { перезапиш* * @Equipment * (нов*/друг*) * имя } *
        | * { перезапиш* * @Equipment * (нов*/друг*) * фамили* } *
        | * { перезапиш* * @Equipment * (нов*/друг*) * фио } *
        | * { перезапиш* * @SynonymsForAgreement * (нов*/друг*) * имя } *
        | * { перезапиш* * @SynonymsForAgreement * (нов*/друг*) * фамили* } *
        | * { перезапиш* * @SynonymsForAgreement * (нов*/друг*) * фио } *
        | * { перезапиш* * @SynonymsForAgreement * на себя } *
        | * { перезапиш* * @SynonymsForAgreement * на меня } *
        | * { перепис* * @AllServices * (нов*/друг*) * имя } *
        | * { перепис* * @AllServices * (нов*/друг*) * фамили* } *
        | * { перепис* * @AllServices * (нов*/друг*) * фио } *
        | * { перепис* * @AllServices * на себя } *
        | * { перепис* * @AllServices * на меня } *
        | * { перепис* * @Equipment * (нов*/друг*) * имя } *
        | * { перепис* * @Equipment * (нов*/друг*) * фамили* } *
        | * { перепис* * @Equipment * (нов*/друг*) * фио } *
        | * { перепис* * @SynonymsForAgreement * (нов*/друг*) * имя } *
        | * { перепис* * @SynonymsForAgreement * (нов*/друг*) * фамили* } *
        | * { перепис* * @SynonymsForAgreement * (нов*/друг*) * фио } *
        | * { перепис* * @SynonymsForAgreement * на себя } *
        | * { перепис* * @SynonymsForAgreement * на меня } *
        | * { перепиш* * @AllServices * (нов*/друг*) * имя } *
        | * { перепиш* * @AllServices * (нов*/друг*) * фамили* } *
        | * { перепиш* * @AllServices * (нов*/друг*) * фио } *
        | * { перепиш* * @AllServices * на себя } *
        | * { перепиш* * @AllServices * на меня } *
        | * { перепиш* * @Equipment * (нов*/друг*) * имя } *
        | * { перепиш* * @Equipment * (нов*/друг*) * фамили* } *
        | * { перепиш* * @Equipment * (нов*/друг*) * фио } *
        | * { перепиш* * @SynonymsForAgreement * (нов*/друг*) * имя } *
        | * { перепиш* * @SynonymsForAgreement * (нов*/друг*) * фамили* } *
        | * { перепиш* * @SynonymsForAgreement * (нов*/друг*) * фио } *
        | * { перепиш* * @SynonymsForAgreement * на себя } * 
        | * { перепиш* * @SynonymsForAgreement * на меня } *)

    $restoreAgreement = ( * { аннулир* * блокиров* * @SynonymsForAgreement } *
        | * { аннулир* * закрытие * @SynonymsForAgreement } *
        | * { аннулир* * расторжение * @SynonymsForAgreement } *
        | * { бэкап * аннулирован* * @SynonymsForAgreement } *
        | * { бэкап * закрыт* * @SynonymsForAgreement } *
        | * { бэкап * ликвидирован* * @SynonymsForAgreement } *
        | * { бэкап * прекращен* * @SynonymsForAgreement } *
        | * { бэкап * прикрыт* * @SynonymsForAgreement } *
        | * { бэкап * расторжен* * @SynonymsForAgreement } *
        | * { возобнов* * @SynonymsForAgreement } *
        | * { возобнов* * ликвидирован* * @SynonymsForAgreement } *
        | * { возобнов* * прекращен* * @SynonymsForAgreement } *
        | * { возобнов* * расторжен* * @SynonymsForAgreement } *
        | * { возрожден* * аннулирован* * @SynonymsForAgreement } *
        | * { возрожден* * закрыт* * @SynonymsForAgreement } *
        | * { возрожден* * ликвидирован* * @SynonymsForAgreement } *
        | * { возрожден* * прекращен* * @SynonymsForAgreement } *
        | * { возрожден* * прикрыт* * @SynonymsForAgreement } *
        | * { возрожден* * расторжен* * @SynonymsForAgreement } *
        | * { воскрешен* * аннулирован* * @SynonymsForAgreement } *
        | * { воскрешен* * закрыт* * @SynonymsForAgreement } *
        | * { воскрешен* * ликвидирован* * @SynonymsForAgreement } *
        | * { воскрешен* * прекращен* * @SynonymsForAgreement } *
        | * { воскрешен* * прикрыт* * @SynonymsForAgreement } *
        | * { воскрешен* * расторжен* * @SynonymsForAgreement } *
        | * { восстанов* * @SynonymsForAgreement } *
        | * { восстанов* * аннулирован* * @SynonymsForAgreement } *
        | * { восстанов* * аннулирован* * @SynonymsForAgreement } *
        | * { восстанов* * закрыт* * @SynonymsForAgreement } *
        | * { восстанов* * закрыт* * @SynonymsForAgreement } *
        | * { восстанов* * ликвидирован* * @SynonymsForAgreement } *
        | * { восстанов* * прекращен* * @SynonymsForAgreement } *
        | * { восстанов* * прикрыт* * @SynonymsForAgreement } *
        | * { восстанов* * прикрыт* * @SynonymsForAgreement } *
        | * { восстанов* * (расторжен*/расторг*) * @SynonymsForAgreement } *
        | * { закончит* * блокиров* * @SynonymsForAgreement } *
        | * { закончит* * блокиров* * @SynonymsForAgreement } *
        | * { закончит* * закрытие * @SynonymsForAgreement } *
        | * { закончит* * закрытие * @SynonymsForAgreement } *
        | * { закончит* * расторжение * @SynonymsForAgreement } *
        | * { закончит* * расторжение * @SynonymsForAgreement } *
        | * { закрой* * блокиров* * @SynonymsForAgreement } *
        | * { закрой* * расторжение * @SynonymsForAgreement } *
        | * { закрыть * блокиров* * @SynonymsForAgreement } *
        | * { закрыть * расторжение * @SynonymsForAgreement } *
        | * { оживлен* * аннулирован* * @SynonymsForAgreement } *
        | * { оживлен* * закрыт* * @SynonymsForAgreement } *
        | * { оживлен* * ликвидирован* * @SynonymsForAgreement } *
        | * { оживлен* * прекращен* * @SynonymsForAgreement } *
        | * { оживлен* * прикрыт* * @SynonymsForAgreement } *
        | * { оживлен* * расторжен* * @SynonymsForAgreement } *
        | * { отключи* * блокиров* * @SynonymsForAgreement } *
        | * { отключи* * закрытие * @SynonymsForAgreement } *
        | * { отключи* * расторжение * @SynonymsForAgreement } *
        | * { отключил* * (@DomRu/@SynonymsForAgreement)} * {потом * (подключил*/включил*)} *
        | * { отмен* * блокиров* * @SynonymsForAgreement } *
        | * { отмен* * закрытие * @SynonymsForAgreement } *
        | * { отмен* * расторжение * @SynonymsForAgreement } *
        | * { прекрати* * блокиров* * @SynonymsForAgreement } *
        | * { прекрати* * закрытие * @SynonymsForAgreement } *
        | * { прекрати* * расторжение * @SynonymsForAgreement } *
        | * { реанимир* * аннулирован* * @SynonymsForAgreement } *
        | * { реанимир* * закрыт* * @SynonymsForAgreement } *
        | * { реанимир* * ликвидирован* * @SynonymsForAgreement } *
        | * { реанимир* * прекращен* * @SynonymsForAgreement } *
        | * { реанимир* * прикрыт* * @SynonymsForAgreement } *
        | * { реанимир* * расторжен* * @SynonymsForAgreement } *
        | * { @Change * @SynonymsForAgreement } * )   

    $changeAgreementData = ( * { убери* * @PersonalInformation } *
        | * { убери* * данные } *
        | * { убери* * @Place } *
        | * { убрат* * @PersonalInformation } *
        | * { убрат* * @Place } *
        | * { убрат* * данные } *
        | * { удал* * @PersonalInformation } *
        | * { удал* * @Place } *
        | * { удал* * данные } *
        | * { *корректир* * @PersonalInformation } *
        | * { *корректир* * @Place } *
        | * { *корректир* * данные } *
        | * { @Change * @PersonalInformation } *
        | * { @Change * данные } *
        | * { (@Change/@ChangePast) * @PersonalInformation * [@WiFi $weight<-1>] } *
        | * { (не совпада*/не совпал*) * @PersonalInformation } *
        | * { (не совпада*/не совпал*) * @Place } *
        | * { (@Wrong/несовпадающ*/ошибочн*) * @PersonalInformation } *
        | * { (@Wrong/несовпадающ*/ошибочн*) * @Place } *
        | * { ошибк* * @PersonalInformation } *
        | * { ошибк* * @Place } *
        | * { *редактир* * @PersonalInformation } *
        | * { *редактир* * @Place } *
        | * { *редактир* * данные } *
        | * { исправ* * @PersonalInformation } *
        | * { исправ* * @Place } *
        | * { исправ* * данные } *
        | * { поправ* * @PersonalInformation } *
        | * { поправ* * @Place } *
        | * { поправ* * данные } *
        | * { *править -на * @PersonalInformation } *
        | * { *править -на * @Place } *
        | * { *править -на * данные } *
        | * { @Wrong * @PersonalInformation } *
        | * { @Wrong * @Place } *
        | * { @Wrong * данные } *
        | * { *корректир* * профил* } *
        | * { *менить * * профил* } *
        | * { *менять * * профил* } *
        | * { *редактир* * профил* } *
        | * { исправ* * профил* } *
        | * { @Wrong * профил* } *
        | * { @Wrong * профил* } *
        | * { прав* * профил* } * )