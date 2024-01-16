// озвучивание целых чисел (-99 000 - 99 000)
function announceNumber(value){
    value = value.toString();
    var isNegative = value.substring(0, 1) == '-';
    var a = value.replace('-', '').replace(' ', '');
    var units;
    var decimal;
    var hundred;
    var thousandUnits;
    var thousandDecimal;
    var leadingZero = (a.length > 1 && a.charAt(0) == '0')

    var rankList = getNumberRankList(a);
    if(rankList.length > 1) {
        if(rankList[1].charAt(0) == '1') {
            decimal = rankList[1].substring(0, 1) + rankList[0];
        }else{
            units = rankList[0];
            decimal = rankList[1];
        }
        hundred = rankList[2];
        thousandUnits = rankList[3];
        thousandDecimal = rankList[4];
    }else{
        if(rankList.length == 1) {
            units = rankList[0];
        }
    }
    
    // озвучиваем
    var audioNamePattern = 'ru_digit_NUM_nom';
    var audio = '';
    if(isNegative){
        // минус
        announceAudio(audioDict.minus);
    }
    if(leadingZero){
        audio = audioNamePattern.replace('NUM', '0');
        announceAudio(audioDict[audio]);
    }
    if(thousandDecimal && thousandDecimal  != '0'){
        if(thousandDecimal > 10000){
            // если больше 10000, проговариваем раздельно число и "тысяч"
            audio = audioNamePattern.replace('NUM', thousandDecimal / 1000);
            announceAudio(audioDict[audio]);
            if(thousandUnits  == '0'){
                announceAudio(audioDict["digit_1000"]);
            }
        }else{
            audio = audioNamePattern.replace('NUM', thousandDecimal);
            announceAudio(audioDict[audio]);
        }
    }
    if(thousandUnits && thousandUnits  != '0'){
        audio = audioNamePattern.replace('NUM', thousandUnits);
        announceAudio(audioDict[audio]);
    }
    if(hundred && hundred  != '0'){
        audio = audioNamePattern.replace('NUM', hundred);
        announceAudio(audioDict[audio]);
    }
    if(decimal && decimal  != '0'){
        audio = audioNamePattern.replace('NUM', decimal);
        announceAudio(audioDict[audio]);
    }
    if(units && (units != '0' || value == 0)){
        audio = audioNamePattern.replace('NUM', units);
        announceAudio(audioDict[audio]);
    }
    
}

// озвучка двузначных целых чисел
function announceTwoDigitNumber(number){
    var value = number.toString();
    var isNegative = value.substring(0, 1) == '-';
    
    if(value.length != 2){
        customLog("Failed to announceTwoDigitNumber. Number is not two-digit.");
        return;
    }
    
    // озвучиваем
    if(isNegative){
        // минус
        announceAudio(audioDict.minus);
    }
    var audioNamePattern = 'DRT_NUM';
    var audio = audioNamePattern.replace('NUM', value);
    announceAudio(audioDict[audio]);
}

// получаем массив из числа по разрядам
function getNumberRankList(sum) {
    var rankList = [];
    var reverseSum = sum.split("").reverse();

    for (var i = 0; i < reverseSum.length; i++){
        var char = reverseSum[i];
        if(char != '0'){
            for (var count = 0; count < i; count++) {
                char = char + "0";
            }
        }

        rankList.push(char);
    }

    return rankList;
}