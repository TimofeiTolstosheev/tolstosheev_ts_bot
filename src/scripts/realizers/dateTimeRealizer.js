// получить аудио для даты
function announceDate(dt){
    var day = dt.getDate();
    var month = dt.getMonth() + 1; // считаются с нуля
    var dayAudio = '';
    var monthAudio = '';
    dayAudio = dateTimeAudioDict.day[day];
    monthAudio = dateTimeAudioDict.month_gen[month];
    announceAudio(audioDict[dayAudio]);
    announceAudio(audioDict[monthAudio]);
}
// получить аудио для даты в родительном падеже
function announceDateGen(dt){
    var day = dt.getDate();
    var month = dt.getMonth() + 1; // считаются с нуля
    var dayAudio = '';
    var monthAudio = '';
    if(day > 20){
        var dayUnits = day % 10;
        var dayDecimal = day - dayUnits;
        if(dayUnits != 0){
            var audioNamePattern = 'ru_digit_SUM_nom';
            dayAudio = audioNamePattern.replace('SUM', dayDecimal);
            announceAudio(audioDict[dayAudio]);
            dayAudio = dateTimeAudioDict.dayGen[dayUnits];
            announceAudio(audioDict[dayAudio]);
        }else{
            dayAudio = dateTimeAudioDict.dayGen[dayDecimal];
            announceAudio(audioDict[dayAudio]);
        }
    }else{
        dayAudio = dateTimeAudioDict.dayGen[day];
        announceAudio(audioDict[dayAudio]);
    }
    monthAudio = dateTimeAudioDict.month_gen[month];
    announceAudio(audioDict[monthAudio]);
}

// время: часы и минуты
function announceTime(hours, minutes){
    if(hours > 24){
        // если больше 24 часов, то озвучиваем в днях
        var days = Math.round(hours / 24);
        if(days == 1){
            announceAudio(audioDict.one_day);
        }else{
            if(days <= 4){
                var audio = "amount_NUM_ord".replace('NUM', days);
                announceAudio(audioDict[audio]);
            }else{
                announceNumber(days);
            }
            announceAudio(audioDict.days);
        }
        return;
    }
    
    var hoursAudio = dateTimeAudioDict.hour[hours];
    var minutesAudio = '';
    var hoursUnits = hours % 10;
    announceAudio(audioDict[hoursAudio]);
    if(hours == 1 || (hours > 20 && hoursUnits == 1)){
        announceAudio(audioDict.Ru_unit_hour_nom_sn);
    }else{
        if((hours == 2 || hours == 3 || hours == 4) || 
           (hours > 20 && (hoursUnits == 2 || hoursUnits == 3 || hoursUnits == 4))){
                announceAudio(audioDict.Ru_unit_hour_gen_sn);
        }else{
            announceAudio(audioDict.Ru_unit_hour_gen_pl);
        }
    }
    
    if(minutes == 0){
        return;
    }
    
    var minutesUnits = minutes % 10;
    var minutesDecimal = minutes - minutesUnits;
    if(minutes <= 20){
        minutesAudio = dateTimeAudioDict.minute[minutes];
        announceAudio(audioDict[minutesAudio]);
    }else{
        minutesAudio = dateTimeAudioDict.minute[minutesDecimal];
        announceAudio(audioDict[minutesAudio]);
        if(minutesUnits > 0){
            minutesAudio = dateTimeAudioDict.minute[minutesUnits];
            announceAudio(audioDict[minutesAudio]);
        }
    }
    
    if(minutes == 1 || (minutes > 20 && minutesUnits == 1)){
        announceAudio(audioDict.Ru_unit_min_nom_sn);
    }else{
        if((minutes == 2 || minutes == 3 || minutes == 4) || 
           (minutes > 20 && (minutesUnits == 2 || minutesUnits == 3 || minutesUnits == 4))){
                announceAudio(audioDict.Ru_unit_min_gen_sn);
        }else{
            announceAudio(audioDict.Ru_unit_min_gen_pl);
        }
    }
}

// аудио для времени из даты
function announceHoursFromDate(dt){
    var hours = dt.getHours();
    var minutes = dt.getMinutes();
    announceTime(hours, minutes);
}

// время с до
function announceTimeFromTimeTo(hoursFrom, minutesFrom, hoursTo, minutesTo){
    var hoursAudio = '';
    var minutesAudio = '';
    
    if(hoursFrom > 0){
        hoursAudio = hoursFrom == 1 ? "from_one_night" : dateTimeAudioDict.hourFrom[hoursFrom];
        announceAudio(audioDict[hoursAudio]);
        if(hoursFrom > 1){
            announceAudio(audioDict.Ru_unit_hour_gen_pl);
        }
        
        if(minutesFrom > 0){
            var minutesUnits = minutesFrom % 10;
            var minutesDecimal = minutesFrom - minutesUnits;
            if(minutesFrom <= 20){
                minutesAudio = dateTimeAudioDict.minuteGen[minutesFrom];
                announceAudio(audioDict[minutesAudio]);
            }else{
                minutesAudio = dateTimeAudioDict.minuteGen[minutesDecimal];
                announceAudio(audioDict[minutesAudio]);
                if(minutesUnits > 0){
                    minutesAudio = dateTimeAudioDict.minuteGen[minutesUnits];
                    announceAudio(audioDict[minutesAudio]);
                }
            }
            
            if(minutesFrom == 1 || (minutesFrom > 20 && minutesUnits == 1)){
                announceAudio(audioDict.Ru_unit_min_gen_sn);
            }else{
                announceAudio(audioDict.Ru_unit_min_gen_pl);
            }
        }
    }
    
    if(hoursTo > 0){
        
        hoursAudio = hoursTo == 1 ? "till_one_night" : dateTimeAudioDict.hourTo[hoursTo];
        announceAudio(audioDict[hoursAudio]);
        if(hoursTo > 1){
            announceAudio(audioDict.Ru_unit_hour_gen_pl);
        }
    
        if(minutesTo > 0){
            var minutesUnits = minutesTo % 10;
            var minutesDecimal = minutesTo - minutesUnits;
            if(minutesTo <= 20){
                minutesAudio = dateTimeAudioDict.minuteGen[minutesTo];
                announceAudio(audioDict[minutesAudio]);
            }else{
                minutesAudio = dateTimeAudioDict.minuteGen[minutesDecimal];
                announceAudio(audioDict[minutesAudio]);
                if(minutesUnits > 0){
                    minutesAudio = dateTimeAudioDict.minuteGen[minutesUnits];
                    announceAudio(audioDict[minutesAudio]);
                }
            }
            
            if(minutesTo == 1 || (minutesTo > 20 && minutesUnits == 1)){
                announceAudio(audioDict.Ru_unit_min_gen_sn);
            }else{
                announceAudio(audioDict.Ru_unit_min_gen_pl);
            }
        }
    }
}


// часы для СЗ с (c 1 до 23)
//цель не повторять "часов" если hoursFrom >0
function announceTimeFrom(hoursFrom){
    var hoursAudio = '';
    
    if(hoursFrom > 0){
        hoursAudio = dateTimeAudioDict.hourFrom[hoursFrom];
        announceAudio(audioDict[hoursAudio]);
    }
}

// озвучивание периода (для сценария Баланс)
// на входе либо число - номер месяца
// либо диапазон в формате 01.01.2023 - 15.02.2023
function announcePeriod(period){
    var month;
    var firstDate;
    var secondDate;
    
    if(period.length <= 2){
        announceAudio(audioDict[dateTimeAudioDict.month[period]]);
    }else{
        var pattern = /(\d{2})\.(\d{2})\.(\d{4})/;
        var firstDateStr = period.substring(0, 10);
        var secondDateStr = period.substring(period.length - 10);
        
        firstDate = new Date(firstDateStr.replace(pattern,'$3-$2-$1'));
        secondDate = new Date(secondDateStr.replace(pattern,'$3-$2-$1'));
        
        announceDateGen(firstDate);
        announceAudio(audioDict.balance_period_po);
        announceDate(secondDate);
    }
}
