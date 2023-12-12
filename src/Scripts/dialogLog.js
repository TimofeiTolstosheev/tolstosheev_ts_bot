// функции для логирования интентов

// получение параметра из справочника по интентам
function getIntentParam(intent, param) {
  // берем из всего пути до интента только название
  var intentName = intent.substring(intent.lastIndexOf("/") + 1) || intent;
  var value;
  try {
    value = intentDict[intentName][param];
  } catch (e) {
    log("Custom log. failed to get " + param + " for intent " + intent + ". Error: " + e);
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
  customLog("Intent log: " + toPrettyString($.session.dialogLog));
}

// начинает интент
function startIntent(intent) {
  $.session.intent = $.session.intent || {};
  $.session.transferLine = getIntentParam(intent, "transferLine");
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
      $.session.stateLog || $.session.prevStateLog
    );
    $.session.prevStateLog = $.session.stateLog; // сохраняем stateLog, чтобы можно было использовать последний лог, если уже очистили текущий
    delete $.session.stateLog;
  }
  $.session.intent.currentIntent = intent;
  $.session.intent.beginDt = getNow();
  $.session.intent.stepsCnt = 1;
  $.session.intent.resultCode = 0; // изначально считаем, что интент завершится успешно
  $.session.intent.info = "";
  // попадания в интенты
  $.session.intentCount = $.session.intentCount || {};
  var curCount = $.session.intentCount[intent] || 0;
  // определяем лимит на интент. Для AgentRequest свои лимит
  var limit = intent == "405_AgentRequest" ? $.injector.agentRequestLimit : $.injector.intentInArowLimit;
  $.session.intentCount[intent] = curCount + 1;
  if($.session.intentCount[intent] > limit) {
      $reactions.transition("/Transfer/IntentLimitTransfer");
  }
  log('Custom log. Intents count: ' + toPrettyString($.session.intentCount));
}

// завершает интент
function stopIntent() {
  $.session.intent = $.session.intent || {};
  if ($.session.intent.currentIntent) {
    $analytics.setSessionData("Last intent", $.session.intent.currentIntent);
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
    $.session.intentAnalyticData = $.session.intentAnalyticData || '';
    $.session.intentAnalyticData += $.session.intentAnalyticData.length > 0 ? ";": "";
    $.session.intentAnalyticData = $.session.intentAnalyticData + intentId + "/" + $.session.intent.resultCode;
    $analytics.setSessionData("Intents", $.session.intentAnalyticData);
    delete $.session.intent;
  }
}
