// функции для логирования интентов

// получение параметра из справочника по интентам
function getIntentParam(intent, param) {
  // берем из всего пути до интента только название
  var intentName;
  var value;
  try {
    intentName = intent.substring(intent.lastIndexOf("/") + 1) || intent;
    value = intentDict[intentName][param];
  } catch (e) {
    log("Failed to get " + param + " for intent " + intent + ". Error: " + e);
  }

  return value;
}

// запись интента в dialogLog
function logIntent(intentId, beginDt, endDt, stepsCnt, resultCode, info, states) {
  $.session.dialogLog = $.session.dialogLog || [];
  $.session.dialogLog.push({
    intentCode: intentId,
    beginDate: beginDt,
    endDate: endDt,
    stepCount: stepsCnt,
    exitCode: resultCode,
    accidentBillingId: info,
    states: states
  });
  $.session.intentAnalyticData = $.session.intentAnalyticData || '';
  $.session.intentAnalyticData += $.session.intentAnalyticData.length > 0 ? ";": "";
  $.session.intentAnalyticData += intentId + "/" + resultCode;
  $analytics.setSessionData("Intents", $.session.intentAnalyticData);
  customLog("dialogLog: " + toPrettyString($.session.dialogLog));
}

// начинает интент
function startIntent(intent) {
  intent = intent.substring(intent.lastIndexOf("/") + 1) || intent; // убираем слэш
  $.session.intent = $.session.intent || {};
  // если уже в интенте, то логируем его и переключаемся на новый
  if ($.session.intent.currentIntent) {
    try { 
        var intentId = getIntentParam($.session.intent.currentIntent, "intentCode");
        if (typeof intentId == 'undefined') intentId = -1;
    } catch (e) { 
        var intenId = -1;
    }
    
    logIntent(
      intentId,
      $.session.intent.beginDt,
      getNow(),
      $.session.intent.stepsCnt,
      $.session.intent.resultCode,
      $.session.intent.info,
      $.session.stateLog || $.session.prevStateLog || ["undefined"]
    );
    $.session.prevStateLog = $.session.stateLog; // сохраняем stateLog, чтобы можно было использовать последний лог, если уже очистили текущий
    delete $.session.stateLog;
    delete $.session.callerInput;
  }
  $.session.intent.currentIntent = intent;
  $.session.intent.beginDt = getNow();
  $.session.intent.stepsCnt = 1;
  $.session.intent.resultCode = 0; // изначально считаем, что интент завершится успешно
  $.session.intent.info = "";
  $.session.lastIntentCode = getIntentParam($.session.intent.currentIntent, "intentCode");
  // попадания в интенты
  $.session.intentCount = $.session.intentCount || {};
  var curCount = $.session.intentCount[intent] || 0;
  $.session.intentCount[intent] = curCount + 1;
  customLog('$.session.intentCount: ' + toPrettyString($.session.intentCount));
  $analytics.setSessionData("Last intent", $.session.intent.currentIntent);
  if($.session.intentCount[intent] > $.injector.intentInArowLimit) {
    $reactions.transition("/Transfer/IntentLimiTransfer");
  }
}

// завершает интент
function stopIntent() {
  $.session.intent = $.session.intent || {};
  if ($.session.intent.currentIntent) {
    try { 
        var intentId = getIntentParam($.session.intent.currentIntent, "intentCode");
        if (typeof intentId == 'undefined') intentId = -1;
    } catch (e) { 
        var intenId = -1;
    }
    logIntent(
      intentId,
      $.session.intent.beginDt,
      getNow(),
      $.session.intent.stepsCnt,
      $.session.intent.resultCode,
      $.session.intent.info,
      $.session.stateLog || $.session.prevStateLog
    );
    $.session.prevStateLog = $.session.stateLog; // сохраняем stateLog, чтобы можно было использовать последний лог, если уже очистили текущий
    delete $.session.stateLog;
    delete $.session.intent;
  }
}
