// общие функции

// делит номер договора по три символа
function splitAgreementNumber(agreementNumber) {
  $.session.agreementNumber0 = agreementNumber.substring(0, 3);
  $.session.agreementNumber1 = agreementNumber.substring(3, 6);
  $.session.agreementNumber2 = agreementNumber.substring(6, 9);
  $.session.agreementNumber3 = agreementNumber.substring(9, 12);
}

// счетчик попаданий в один и тот же стейт. Используется в catchAll
function countRepeatsInRow() {
  if ($.session.lastEntryState === $.currentState) {
    $.session.repeatsInRow += 1;
  } else {
    $.session.repeatsInRow = 1;
  }
  return $.session.repeatsInRow;
}

// определение рабочего времени ГСК по городу
function checkGSKtime(city) {
  if($.session.gskWorkTime === true || $.session.gskWorkTime === false){
      return;
  }
  $.session.gskWorkTime = false;
  try{
    var now = getDefaultTimeZoneDate();
    var gskWorkingTime = citiesData[city]["gskWorkingTime"];
    var timeFrom = citiesData[city]["gskTimeFrom"];
    var timeTo = citiesData[city]["gskTimeTo"];
    var timeFromHour = parseInt(timeFrom.substring(0, 2));
    var timeFromMin = parseInt(timeFrom.substring(3, 5));
    var timeFromSec = parseInt(timeFrom.substring(6, 8));
    var timeToHour = parseInt(timeTo.substring(0, 2));
    var timeToMin = parseInt(timeTo.substring(3, 5));
    var timeToSec = parseInt(timeTo.substring(6, 8));
    
    var gskTimeFromDate = getDefaultTimeZoneDate();
    var gskTimeToDate = getDefaultTimeZoneDate();
    if (timeFromHour > timeToHour) {
      gskTimeToDate.setDate(now.getDate() + 1);
    }
    
    gskTimeFromDate.setHours(timeFromHour, timeFromMin, timeFromSec);
    gskTimeToDate.setHours(timeToHour, timeToMin, timeToSec);
    
    $.session.gskWorkTime = now > gskTimeFromDate && now < gskTimeToDate && gskWorkingTime;
  }catch (e) {
    customLog("Failed to get GSK working hours. Error: " + e);
  }
}

// определение рабочего времени ОЦП по городу
function checkOCPtime(city) {
  if($.session.ocpWorkingTime === true || $.session.ocpWorkingTime === false){
      return;
  }
  $.session.ocpWorkingTime = false;
  try{
    var now = getDefaultTimeZoneDate();
    var ocpWorkingTime = citiesData[city]["ocpWorkingTime"];
    var timeFrom = citiesData[city]["ocpTimeFrom"];
    var timeTo = citiesData[city]["ocpTimeTo"];
    var timeFromHour = parseInt(timeFrom.substring(0, 2));
    var timeFromMin = parseInt(timeFrom.substring(3, 5));
    var timeFromSec = parseInt(timeFrom.substring(6, 8));
    var timeToHour = parseInt(timeTo.substring(0, 2));
    var timeToMin = parseInt(timeTo.substring(3, 5));
    var timeToSec = parseInt(timeTo.substring(6, 8));
    
    var ocpTimeFromDate = getDefaultTimeZoneDate();
    var ocpTimeToDate = getDefaultTimeZoneDate();
    if (timeFromHour > timeToHour) {
      ocpTimeToDate.setDate(now.getDate() + 1);
    }
    
    ocpTimeFromDate.setHours(timeFromHour, timeFromMin, timeFromSec);
    ocpTimeToDate.setHours(timeToHour, timeToMin, timeToSec);
    
    $.session.ocpWorkingTime = now > ocpTimeFromDate && now < ocpTimeToDate && ocpWorkingTime;
  }catch (e) {
    customLog("Failed to get OCP working hours. Error: " + e);
  }
}

// режим работы для переводов
function checkTransferTime() {
  if($.session.transferTime === true || $.session.transferTime === false){
      return;
  }
  if($.testContext){
      $.session.transferTime = true;
      return;
  }
  $.session.transferTime = true;
  try{
    var now = getDefaultTimeZoneDate();
    var timeFrom = $.injector.transferTimeFrom;
    var timeTo = $.injector.transferTimeTo;
    var timeFromHour = parseInt(timeFrom.substring(0, 2));
    var timeFromMin = parseInt(timeFrom.substring(3, 5));
    var timeToHour = parseInt(timeTo.substring(0, 2));
    var timeToMin = parseInt(timeTo.substring(3, 5));
    
    var transferTimeFromDate = getDefaultTimeZoneDate();
    var transferTimeToDate = getDefaultTimeZoneDate();
    if (timeFromHour > timeToHour) {
      transferTimeToDate.setDate(now.getDate() + 1);
    }
    
    transferTimeFromDate.setHours(timeFromHour, timeFromMin);
    transferTimeToDate.setHours(timeToHour, timeToMin);
    
    $.session.transferTime = now >= transferTimeFromDate && now < transferTimeToDate;
  }catch (e) {
    customLog("Failed to check transfer working hours. Error: " + e);
  }
}

function customLog(text){
    log("Custom log. " + text);
}
