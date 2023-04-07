require: slotfilling/slotFilling.sc
  module = sys.zb-common
require: patterns.sc

require: phoneNumber/phoneNumber.sc
  module = sys.zb-common

init:
    bind("postProcess", function($context){
        $context.session.lastState = $context.currentState;
    });
    
theme: /

    state: Start
        q!: $regex</start>
        q!: $hi
        script:
            $jsapi.startSession();
        
        random:
            a: Здравствуйте!
            a: Привет!
        a: Меня зовут {{$injector.botName}}.
        go!: /Service/SuggestHelp

    state: CatchAll || noContext = true
        event!: noMatch
        random:
            a: Я не понял. Скажите по-другому.
            a: Прошу прощения, я не понял. Перефразируйте.
        go!: {{$context.session.lastState}}

theme: /Service
    state: SuggestHelp
        q: отмена || fromState = "/Phone/Ask"
        a: Я помогу вам купить билет. Хорошо?
     
    state: Accepted
        q: * (да / давай* ) *
        a: Отлично!
        if: $client.phone
            go!: /Phone/Confirm
        go!: /Phone/Ask
 
    state: Rejected
        q: * (нет/не ) *
        a: Больше ничем помочь не смогу.

theme: /Phone
    state: Ask || modal = true
        a: Для продолжения укажите номер телефона.
        buttons:
            "Отмена"
            
        state: Get
            q: * $mobilePhoneNumber *
            script:
                $session.probablyPhone = $parseTree._mobilePhoneNumber
            go!: /Phone/Confirm
    
        state: CatchAll
            event: noMatch
            a: Это не номер телефона!
            go!: ..
    
    state: Confirm
        script:
            $session.probablyPhone = $client.phone || $session.probablyPhone
                
        a: Ваш номер телефона: {{$session.probablyPhone}} ?
        buttons:
            "Да"
            "Нет"

        state: Yes
            q: (да/верно)
            script:
                $client.phone = $session.probablyPhone;
                delete $session.probablyPhone;
            go!: /Discount/Inform

        state: No
            q: (нет/не [верно]/неверно)
            script:
                delete $session.probablyPhone;
            go!: /Phone/Ask

theme: /Discount
    state: Inform
        script:
            var nowDate = $jsapi.dateForZone("Europe/Moscow", "dd.MM");
            var answerText = "Сегодня " + nowDate;
            
            $reactions.answer(answerText);
