patterns:

    $agentRequest = * ({((есть/можно/можешь/можете) [ли]/надо/нужен/хочу/дай/дайте/давай*) [мне/у вас] [тут/здесь] [на] [~живой] (@Operator/$notARobot/~человек [а] [$notARobot])}
        | {((есть/можно/можешь/можете) [ли]/надо/нужен/хочу/дай/дайте/давай*) [мне/у вас] [тут/здесь] [на] ~другой @Operator}
        | {~живой (@Operator/$notARobot)}
        | [как] [можно [ли]/надо/есть [ли] возможность] {(([(есть/можно/можешь/можете) [ли]/надо/нужен/хочу/дай/дайте/давай*] [ли] (соедин*/связать*/свяжи*/переключ*/переведи*/перевести) [меня])/*консультир* [меня]/пригласи*/пообщаться/напиши*/написать/[получить/дать/нужна] ~консультация/перевод/позов*/зови*/*звать/@Need/чат/связь*/дай/перейти к разговору) * [с/со/до/на] (@Operator/$notARobot/(~человек/(живог*/~живой) [@Operator
        /$notARobot/~человек]) [а] [$notARobot])}
        | [как] {(можно/надо/есть возможность) (с (кем-нибудь/кем-либо/кем-то)) [~другой] проконсультироваться}
        |* (департам* связи) *
        |* {[ @Transfer ] @Operator} *
        |* {[ @Modal] @Operator} *
        |* { приглас* * @Operator } *
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
        |* { переведи* * на * агент* } *
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
        |* подключ* * [(@Tariff/пакет*) $weight<-1>] * сотрудник* *
        |* [(@Tariff/пакет*) $weight<-1>] * сотрудник* * подключ* *
        |* { подключ* * бухгалтер* } *
        |* { подключ* * работник* } *
        |* { [есть] * кто * живой } *
        |* соедин* * { абонентск* отдел* } *
        |* соедин* * { абон отдел* } *
        |* соедин* * { бухгалтерск* отдел* } *
        |* соедин* * { абонентск* служб* } *
        |* соедин* * { абон служб* } *
        |* соедин* * { бухгалтерск* служб* } *
        |* { служб* поддержк* } *
        |* { соедин* * служб* поддержк* } *
        |* (не @Modal) * @Talk * ($bot/@Bot) *
        |* (не @Modal) * беседовать * ($bot/@Bot) *
        |* (не @Modal) * болтать * ($bot/@Bot) *
        |* (не @Modal) * базарить * ($bot/@Bot) *
        |* (не @Modal) * общаться * ($bot/@Bot) *
        |* (не @Modal) * объяснять * ($bot/@Bot) *
        |* (не @Modal) * продолжать * ($bot/@Bot) *
        | * { @Modal * @Talk * @Operator } *
        | * { @Modal * @Talk * сотрудник* } *
        | * { @Modal * @Talk * специалист* } *
        |* { *консультироваться * людьми } *
        |* { *консультироваться * человек* } *
        |* { консультаци* * специалист* } *
        |* { со спецом человеческим соедините } *
        |* { оператор * @Modal } *
        |* { @Modal * соединени* работник* * @DomRu } *
        |* { позови* коллег* } *
        |* { соедини* * со своим * начальник* } *
        |* { приглашени* старш* специалист* } *
        |* { помощ* специалист* } *
        |* { *зови* главного } *
        |* { пригласи* живого человек* } *
        |* { запрос на соединени* с оператором } *
        |* { переключи* * на * оператор* } *
        |* { горяч* лини* } *
        |* не буду * @Talk * ($bot/@Bot) *
        |* не буду * беседовать * ($bot/@Bot) *
        |* не буду * болтать * ($bot/@Bot) *
        |* не буду * базарить * ($bot/@Bot) *
        |* не буду * общаться * ($bot/@Bot) *
        |* не буду * объяснять * ($bot/@Bot) *
        |* не буду * продолжать * ($bot/@Bot) *
        |* не будем * @Talk * ($bot/@Bot) *
        |* не будем * беседовать * ($bot/@Bot) *
        |* не будем * болтать * ($bot/@Bot) *
        |* не будем * базарить * ($bot/@Bot) *
        |* не будем * общаться * ($bot/@Bot) *
        |* не будем * объяснять * ($bot/@Bot) *
        |* не будем * продолжать * ($bot/@Bot) * 
        |* (не @Want) * (разговаривать/говорить/беседовать/болтать/базарить/общаться/объяснять/продолжать) * ($bot/@Bot) *
        |* не будем * @Talk * ($bot/@Bot) *
        |* не будем * беседовать * ($bot/@Bot) *
        |* не будем * болтать * ($bot/@Bot) *
        |* не будем * базарить * ($bot/@Bot) *
        |* не будем * общаться * ($bot/@Bot) *
        |* не будем * объяснять * ($bot/@Bot) *
        |* не будем * продолжать * ($bot/@Bot) *
        | @Operator
        | специалист*
        |* соедин* * с @FIO * 
        |* перевед* * на @FIO * )
