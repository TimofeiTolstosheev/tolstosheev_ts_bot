// озвучивание стоимости
function announceSum(sum){
    sum = sum.toString();
    var isNegative = sum.substring(0, 1) == '-';
    var s = sum.replace('-', '').replace(' ','').split(',');
    var rub = s[0];
    var kop = s[1];
    var rubUnits;
    var rubDecimal;
    var rubHundred;
    var rubThousandUnits;
    var rubThousandDecimal;
    var kopUnits;
    var kopDecimal;

    if(kop){
        if(kop.charAt(0) == '1') {
            kopDecimal = kop;
        }else{
            kopUnits = kop.substring(1);
            kopDecimal = kop.substring(0, 1) + "0";
        }
    }
    
    var rankList = getNumberRankList(rub);
    if(rankList.length > 1) {
        if(rankList[1].charAt(0) == '1') {
            rubDecimal = rankList[1].substring(0, 1) + rankList[0];
        }else{
            rubUnits = rankList[0];
            rubDecimal = rankList[1];
        }
        rubHundred = rankList[2];
        rubThousandUnits = rankList[3];
        rubThousandDecimal = rankList[4];
    }else{
        if(rankList.length == 1) {
            rubUnits = rankList[0];
        }
    }
    
    // озвучиваем рубли
    var audioNamePattern = 'ru_digit_SUM_nom';
    var audio = '';
    if(isNegative){
        // минус
        announceAudio(audioDict.minus);
    }
    if(rubThousandDecimal && rubThousandDecimal  != '0'){
        if(rubThousandDecimal > 10000){
            // если больше 10000, проговариваем раздельно число и "тысяч"
            audio = audioNamePattern.replace('SUM', rubThousandDecimal / 1000);
            announceAudio(audioDict[audio]);
            if(rubThousandUnits  == '0'){
                announceAudio(audioDict["digit_1000"]);
            }
        }else{
            audio = audioNamePattern.replace('SUM', rubThousandDecimal);
            announceAudio(audioDict[audio]);
        }
    }
    if(rubThousandUnits && rubThousandUnits  != '0'){
        audio = audioNamePattern.replace('SUM', rubThousandUnits);
        announceAudio(audioDict[audio]);
    }
    if(rubHundred && rubHundred  != '0'){
        audio = audioNamePattern.replace('SUM', rubHundred);
        announceAudio(audioDict[audio]);
    }
    if(rubDecimal && rubDecimal  != '0'){
        audio = audioNamePattern.replace('SUM', rubDecimal);
        announceAudio(audioDict[audio]);
    }
    if(rubUnits && (rubUnits != '0' || rub == 0)){
        audio = audioNamePattern.replace('SUM', rubUnits);
        announceAudio(audioDict[audio]);
    }
    if(rubUnits == '1'){
        // рубль
        announceAudio(audioDict.ru_unit_rub_nom_sn);
    }else{
        if(rubUnits == '2' || rubUnits == '3' || rubUnits == '4'){
            // рубля
            announceAudio(audioDict.ru_unit_rub_gen_sn);
        }else{
            // рублей
            announceAudio(audioDict.ru_unit_rub_gen_pl);
        }
    }
    
    // озвучиваем копейки
    if(kopDecimal && kopDecimal != '00'){
        audio = audioNamePattern.replace('SUM', kopDecimal);
        announceAudio(audioDict[audio]);
    }
    if(kopUnits && kopUnits  != '0'){
        if(kopUnits == '1'){
            // копейка
            announceAudio(audioDict.ru_digit_1_nom_fem);
        }else{
            audio = audioNamePattern.replace('SUM', kopUnits);
            announceAudio(audioDict[audio]);
        }
    }
    if(kopDecimal && kopDecimal != '00' || kopUnits && kopUnits  != '0'){
        if(kopUnits == '1'){
            // копейка
            announceAudio(audioDict.ru_unit_kop_nom_sn);
        }else{
            if(kopUnits == '2' || kopUnits == '3' || kopUnits == '4'){
                // копейки
                announceAudio(audioDict.ru_unit_kop_gen_sn);
            }else{
                // копеек
                announceAudio(audioDict.ru_unit_kop_gen_pl);
            }
        }
    }
}
