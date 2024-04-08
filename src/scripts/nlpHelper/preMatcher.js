var miscLs = [
        /[зЗ]дравствуй(те)?/,
        /[зЗ]дась?те/,
        /[пП]риве+т(ы|ики?)?\s/,
        /[пП]риве+т(ы|ики?)?$/,
        /[дД]обр(ый|ого|ой)\s(день|дня|вечер[а]|утр[оа]|ночи|времени суток)/,
        /\sалл+[оёе]/,
        /^[аА]лл+[оёе]/,
        /[пП]ожалу?й?ста/,
        /([пП]од)?[сС]кажи(те)?\sпожалу?й?ста/,
        /[пП]ожалуй/,
        /[сС]?[пП]асиб[оа]?/
    ]
    
function replaceMiscWords(regex, str) {
    var new_str = str.replace(regex, "");
    if(/^\s/.test(new_str)) { new_str = new_str.trim(); };
    if(/\s\s/.test(new_str)) { new_str = new_str.replace([/\s\s/], [/\s/]); };
    return new_str.length == 0 || new_str == " " || new_str == "/[аАЭэ]м?\s/" ? str : new_str;
}

function checkMiscWordsInString(str){
    for (var i = 0; i < miscLs.length; i++) {
        str = replaceMiscWords(miscLs[i], str);
    }
    return str;
}