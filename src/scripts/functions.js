// общие функции
function getCurrentMode() {
    return $.testContext || !$.injector.mode ? "test" : $.injector.mode;
}

function getCityByRegion(region, citiesByRegionDict, citiesDataDict) {
    var cityName;
    var cityCode = citiesByRegionDict[region];
    if (cityCode) cityName = citiesDataDict[cityCode] && citiesDataDict[cityCode].ruCity;
    return {
        code: cityCode,
        name: cityName
    };
}

function getCityDataByName(cityName, citiesDataDict) {
    var cityCode = _.findKey(citiesDataDict, function(city) {
      return city.ruCity.toLowerCase() === cityName.toLowerCase();
    });
    var region;
    if(citiesDataDict[cityCode]){
        region = citiesDataDict[cityCode].region;
        // если тестовый бот и по региону есть тестовый биллинг, то используе его
        if(!$env.get("isProd", false) && citiesDataDict[cityCode].testRegion){
            region = citiesDataDict[cityCode].testRegion;
        }
    }
    return {
      code: cityCode,
      name: cityName,
      region: region
    };
}

// инициализация
function init() {
    $.session.cisco = $.session.cisco || {};
    // определим тестовые данные
    if($.request.channelType == 'chatwidget' || $.testContext){
        $.session.cisco.rck = $.session.cisco.rck  || '11111';
        $.session.cisco.rckd = $.session.cisco.rckd || '222';
        $.session.region = $.session.region || "dev-dcc-spb";
        $.session.phoneNumber = $.session.phoneNumber || "79524002897";
    }
    $.session.cellPhone = $.session.cellPhone || $.session.phoneNumber.substring(1, 2) == "9";
    $.session.city = citiesByRegion[$.session.region] || 'perm'; // не нашли город, считаем, что Пермь
    $.session.callEndType = 'near-hup'; // по-умолчанию, считаем, что звонок завершится в боте
    $.session.dialogLogIsSent = false;
    $.session.dialogId = $.sessionId;
    $.session.clientPathExperiment = "B";
    try{
        $.session.clientPathExperiment = $analytics.joinExperiment("Путь клиента");
    }catch(e){
        customLog("Failed to join experiment. Error: " + e);
    }

    $analytics.setSessionData("Region", $.session.region);
    $analytics.setSessionData("RCKD", $.session.cisco.rckd);
    $analytics.setSessionData("RCK", $.session.cisco.rck);
}

// делит номер договора по три символа
function splitAgreementNumber(agreementNumber) {
  $.session.agreementNumber0 = agreementNumber.substring(0, 2);
  $.session.agreementNumber1 = agreementNumber.substring(2, 4);
  $.session.agreementNumber2 = agreementNumber.substring(4, 6);
  $.session.agreementNumber3 = agreementNumber.substring(6, 8);
  $.session.agreementNumber4 = agreementNumber.substring(8, 10);
  $.session.agreementNumber5 = agreementNumber.substring(10, 12);
}

// счетчик попаданий в один и тот же стейт. Используется в catchAll
function countRepeatsInRow() {
  $.temp.entryState = $.currentState;
  if ($.session.prevContext ==  $.contextPath) {
    $.session.repeatsInRow = $.session.repeatsInRow || 0;
    $.session.repeatsInRow += 1;
  } else {
    $.session.repeatsInRow = 1;
    $.session.noInputCount = 0;
    $.session.agentRequestCount = $.session.agentRequestCount || 0;
  }
  $.session.prevContext =  $.contextPath;
  return $.session.repeatsInRow;
}

// счетчик попаданий в один и тот же стейт внутри сценария (не подряд)
function countRepeatsPerState(flag) {
  var flag = flag || "default";
  $.session.repeatPerState =  $.session.repeatPerState || {};
  $.session.repeatPerState[$.currentState] =  $.session.repeatPerState[$.currentState] || {};
  $.session.repeatPerState[$.currentState][flag] = $.session.repeatPerState[$.currentState][flag] + 1 || 1;
  return $.session.repeatPerState[$.currentState][flag];
}

// счетчик вызова оператора
function countAgentRequests(pt) {
  if(pt._agentRequest){
    $.session.agentRequestCount = $.session.agentRequestCount || 0;
    $.session.agentRequestCount += 1;
  }
  return $.session.agentRequestCount;
}

//счетчик попаданий в noMatch внутри темы, даже если тема менялась в течение диалога
function countRepeatsInNoMatchThroughDialog(intent_name) {
    if (!("no_match_dict" in $.session)){
        $.session.no_match_dict = {};
    }
    if (!(intent_name in $.session.no_match_dict)){
        $.session.no_match_dict[intent_name] = 0;
    }
    $.session.no_match_dict[intent_name] += 1;

    return $.session.no_match_dict[intent_name];
}

// возвращает Date в дефолтном часовом поясе
function getDefaultTimeZoneDate(){
    return new Date($jsapi.timeForZone($.injector.timeZone));
}

// текущая дата в формате ISO "2023-10-05T09:11:46.097Z"
function getNow() {
  var now = getDefaultTimeZoneDate();
  return now.toISOString();
}

// определение рабочего времени ГСК по городу
function checkGSKtime(city) {
  customLog("Check GSK working time for city: " + city);
  if($.session.gskWorkTime === true || $.session.gskWorkTime === false){
      return;
  }
  try{
    var now = new Date();
    var gskWorkingTime = citiesData[city]["gskWorkingTime"];
    var timeFrom = citiesData[city]["gskTimeFrom"];
    var timeTo = citiesData[city]["gskTimeTo"];
    var timeFromHour = parseInt(timeFrom.substring(0, 2));
    var timeFromMin = parseInt(timeFrom.substring(3, 5));
    var timeFromSec = parseInt(timeFrom.substring(6, 8));
    var timeToHour = parseInt(timeTo.substring(0, 2));
    var timeToMin = parseInt(timeTo.substring(3, 5));
    var timeToSec = parseInt(timeTo.substring(6, 8));
    
    var gskTimeFromDate = new Date();
    var gskTimeToDate = new Date();
    if (timeFromHour > timeToHour) {
      gskTimeToDate.setDate(now.getDate() + 1);
    }
    
    gskTimeFromDate.setHours(timeFromHour, timeFromMin, timeFromSec);
    gskTimeToDate.setHours(timeToHour, timeToMin, timeToSec);
    
    $.session.gskWorkTime = now > gskTimeFromDate && now < gskTimeToDate && gskWorkingTime;
  }catch(e){
      $.session.gskWorkTime = false;
      customLog("Failed to check GSK working time. Error: " + e);
  }
}

// получение списка каналов/пакетов из сущностей
function getTvChannelNamesFromEntities(){
    var tvChannels = [];
    
    for (var i = 0; i < $.entities.length; i++) {
        if($.entities[i].pattern === 'TVChannelName' ||
           $.entities[i].pattern === 'TVChannelPackageName'){
            if(tvChannels.indexOf($.entities[i].value) < 0){
                tvChannels.push($.entities[i].value);
            }
        }
    }
    
    return tvChannels;
}

// обертка для воспроизведения аудио. На вход принимает запись из audioDict
// если тестовый контекст или виджет, то выводим текст
// если не тестовый контекст, то из справочника audioDict по value воспроизводим аудио, по text - проставляем имя аудио
function announceAudio(audio){
    $analytics.setSessionData('Last reply', audio.value || "");
    if ($.testContext || $.request.channelType == 'chatwidget'){
        $reactions.answer(audio.value);
        return;
    }
    try{
        $reactions.audio({name: audio.value, value: audio.audio});
    }catch(e){
        $analytics.setScenarioAction("Не найден текст аудио");
        throw new Error("Audio announce not found");
    }
}

function customLog(text){
    log("Custom log. " + text);
}

// убирает повторения в строке
function removeRepeats(s){
    var res = "";
    var s_arr = s.split(" ");
    for(var i = 0; i < s_arr.length; i++){
        if(s_arr[i] == s_arr[i+1]){
            continue;
        }else{
            res += s_arr[i] + " ";
        }
    }
    return res.trim();
}

// преобразовываем все слова в тексте с заглавной буквы
function capitalizeFirstLetters(str) {
    var array = str.toLowerCase().split(" ");
    for (var i = 0; i < array.length; i++) {
        array[i] = array[i].charAt(0).toUpperCase() + array[i].slice(1);
    }
    return array.join(' ');
}

function logState(stateName){
    // логируем только ветку А
    if($.session.clientPathExperiment == "A"){
        $.session.stateLog = $.session.stateLog || [];
        $.session.stateLog.push(stateName);
    }
}
