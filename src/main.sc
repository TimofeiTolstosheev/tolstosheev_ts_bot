require: slotfilling/slotFilling.sc
  module = sys.zb-common
require: patterns.sc

theme: /

    state: Start
        q!: $regex</start>
        q!: $hi
        random:
            a: Здравствуйте!
            a: Привет!
        go!: /Service/SuggestHelp

    state: CatchAll || noContext = true
     event!: noMatch
     random:
         a: Я не понял. Скажите по-другому.
         a: Прошу прощения, я не понял. Перефразируйте.

theme: /Service
    state: SuggestHelp
        q: отмена || fromState = "/Phone/Ask"
        a: Я помогу вам купить билет. Хорошо?
     
    state: Accepted
        q: * (да / давай* ) *
        a: Отлично!
        go!: /Phone/Ask
 
    state: Rejected
        q: * (нет/не ) *
        a: Больше ничем помочь не смогу.

theme: /Phone
    state: Ask || modal = true
        a: Для продолжения укажите номер телефона.
        buttons:
            "Отмена"
            
    state: localCatchAll
        event: noMatch