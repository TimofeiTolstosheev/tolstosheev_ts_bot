// Функции для работы с датами

// возвращает Date в дефолтном часовом поясе
function getDefaultTimeZoneDate(){
    return new Date($jsapi.timeForZone($.injector.timeZone));
}

// текущая дата в формате ISO "2023-10-05T09:11:46.097Z"
function getNow() {
  var now = getDefaultTimeZoneDate();
  return now.toISOString();
}

// возвращает строку из даты в формате dd.mm.yyyy hh:mi
function dateToDefaultString(dt){
    var date = dt.getDate();
    var month = dt.getMonth() + 1;
    var year = dt.getFullYear();
    var hours = dt.getHours();
    var minutes = dt.getMinutes();
    if(date < 10){
        date = '0' + date;
    }
    if(month < 10){
        month = '0' + month;
    }
    if(hours < 10){
        hours = '0' + hours;
    }
    if(minutes < 10){
        minutes = '0' + minutes;
    }
    
    return hours + ':' + minutes + ' ' + date + '.' + month + '.' + year;
}