// Запросы в Action Service
function logAction(method, message, suffix) {
    var output = "Action " + method + " " + message;
    if (suffix) output += ": " + suffix;
    return customLog(output);
}

// выполняет переданный запрос в Action Service
function executeAction(method, inputParams){
    var isProd = $env.get("isProd", false);
    var actionServiceEndpoint = isProd ? $.injector.actionServiceEndpoint : $.injector.actionServiceEndpointTest;
    if($.testContext){
        actionServiceEndpoint = $.injector.actionServiceEndpointTest;
    }
    if($.request.channelType == 'chatwidget' && isProd && !$.testContext){
        // напоминаем тестирующему, что он выполняет запрос в прод
        $reactions.answer("ЗАПРОС ВЫПОЛНЯЕТСЯ НА ПРОДЕ! ТЕСТИРУЙ ОСОЗНАННО!");
    }
    
    var url = actionServiceEndpoint + method;
    inputParams.body.sendTimeout = inputParams.body.sendTimeout || $.injector.actionTimeout;
    inputParams.body.readTimeout = inputParams.body.readTimeout || $.injector.actionReadTimeout;
    if($.session.awaitAction){
        inputParams.body.readTimeout = $.session.awaitAction.readTimeout || inputParams.body.readTimeout;
    }
    logAction(method,'InputParams', toPrettyString(inputParams));
    inputParams.body.dialogId = $.sessionId || "test";
    var options = {
        dataType: "json",
        headers: {
            "Content-Type": "application/json",
            "Authorization": 'Bearer ' + $.session.token
        },
        body: inputParams.body,
        timeout: inputParams.body.timeout || $.injector.actionTimeout
    };
    
    var response;
    try{
        response = $http.post(url, options);
        logAction(method, 'Response', toPrettyString(response));
        if(response.data){
            if(response.data.status == 'pending'){
                if(!$.session.awaitRequestId){
                    // если это первый запрос по await, залогируем ошибку
                    if(method.indexOf($.session.rebootAwaitRequestId) > 0){
                        // чтобы не плодить ошибки, при получении данных по rebootAwaitRequestId, запишем только /ivr/billing/await/reboot
                        logDialogError("/ivr/billing/await/reboot", "request timed out");
                    }else{
                        logDialogError(method, "request timed out");
                    }
                }
                $.session.waitResponse = true;
                $.session.awaitRequestId = response.data.requestId;
                logAction(method, "request id for await method", $.session.awaitRequestId);
            }else{
                $.session.waitResponse = false;
                delete $.session.awaitRequestId;
            }
        }
    }catch(e){
        logAction(method, 'Request failed. Error', e);
    }
    
    var analyticErrorMessage = "";
    var analyticMethod = method.match(/.await./) ? "await" : method;
    if(response.status != 200){
        logAction(method, 'Request failed with status', response.status);
        analyticErrorMessage = "Status: " + response.status + ". ";
        $.session.waitResponse = false;
        delete $.session.awaitRequestId;
    }
    
    if(response.status === -1){
        $.session.requestTimeout = true;
    }
    
    if(response.data){
        // для await-запросов пересоберем response
        if(response.data.response){
            response.data = response.data.response;
        }
        
        if(response.data.error){
            try{
            response.data.errorCode = response.data.error.details.code;
            response.data.errorMessage = response.data.error.details.message;
            }catch(e){
                logAction(method, "failed to parse error message", e);
            }
        }
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                analyticErrorMessage += "Error code: " + response.data.errorCode + ". Message: " + response.data.errorMessage;
            }
        }
    }
    
    $analytics.setSessionData(analyticMethod, analyticErrorMessage);
    
    return response;
}

function getBalance(){
    var method = '/ivr/billing/await/balance'
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId
        }
    var response = {};
    
    // если есть request id, забираем ответ по нему
    if($.session.awaitRequestId){
        response = getResponseByRequestId($.session.awaitRequestId);
    }else{
        response = executeAction(method, inputParams);
    }
    
    if(!$.session.waitResponse){
        if(response.data){
            if(response.data.errorCode){
                if(response.data.errorCode !== 0){
                    throw new Error(response.data.errorMessage);
                }
            }
            $.session.recommendSum = response.data.recommendSum;
            $.session.chargesSum = response.data.chargesSum;
            $.session.textParam = response.data.textParam;
            $.session.periodDates = response.data.periodDates;
            $.session.opEndDate = response.data.opEndDate;
            $.session.curBalance = response.data.curBalance;
            $.session.firstPay = response.data.firstPay;
            $.session.leasDz = response.data.leasDz;
            $.session.closeDate = response.data.closeDate;
            $.session.lastPayment = response.data.lastPayment;
            $.session.lastPaymentSum = response.data.lastPaymentSum;
            $.session.retStr = response.data.retStr;
            $.session.isDeny = response.data.isDeny;
            $.session.nextPeriodDate = response.data.nextPeriodDate;
        }
    }
}

function sendDialogLog(){
    if($.testContext){
        // автотесты не отпрвляем
        return;
    }
    if(!$.session.dialogLog){
        customLog('dialogLog is undefined.');
        return;
    }
    
    var method = '/ivr/bi/send/dialog/log';
    $.session.cisco = $.session.cisco || {};
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId,
          "rck": $.session.cisco.rck || "undefined",
          "rckd": $.session.cisco.rckd || "undefined",
          "callerInput": $.session.callerInput || "undefined",
          "callEndType": $.session.callEndType || "undefined",
          "dialogLog": $.session.dialogLog
        }
    if($.request.channelType == 'chatwidget'){
        var intentCode = '';
        for(var i = 0; i < $.session.dialogLog.length; i++){
            intentCode += $.session.dialogLog[i].intentCode + '-' + $.session.dialogLog[i].exitCode + ';';
        }
        $reactions.answer("BI dialog log: " + intentCode);
    }
    $analytics.setSessionData("Caller input", $.session.callerInput);
    
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        customLog('Request failed with status ' + response.status);
        if($.request.channelType == 'chatwidget'){
            $reactions.answer("BI log failed! Status:  " + response.status);
        }
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
                if($.request.channelType == 'chatwidget'){
                    $reactions.answer("BI log failed! Error:  " + response.data.errorMessage);
                }
            }
        }
    }
    
    // примерно посчитаем размер session
    //var sessionSize = JSON.stringify($.session).length;
    //$analytics.setSessionData("session size", sessionSize);
    delete $.session.dialogLog;
}

function sendSMS(intent){
    $.session.SMSstatus = true;
    var intentCode = getIntentParam(intent, 'intentCode') || -1;
    var intentResultCode = 0;
    var method = getIntentParam(intent, 'method');
    var inputParams = {};
    
    // Иногда дата начала основного интента совпадает с датой начала интента отправки СМС.
    // Чтобы отдельть начало интента отправки СМС от основного, добавляем 1 секунду
    var d = getDefaultTimeZoneDate();
    d.setSeconds(d.getSeconds() + 1);
    var beginDt = d.toISOString();
    
    inputParams.body = {
          "dialogId": $.sessionId,
          "sendTimeout": 10000
        };
    inputParams.timeout = 7000;
    var response = executeAction(method, inputParams);
    if(response.status != 200){
        $.session.SMSstatus = false;
        intentResultCode = 3;
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                $.session.SMSstatus = false;
                intentResultCode = 3;
            }
        }
    }
    logIntent(intentCode, beginDt, getNow(), 0, intentResultCode, '', $.session.stateLog || $.session.prevStateLog);
}

function sendSMSbyTemplate(intent){
    $.session.SMSstatus = true;
    var smsTemplateName = getIntentParam(intent, 'smsTemplateName');
    var intentCode = getIntentParam(intent, 'intentCode') || -1;
    var intentResultCode = 0;
    var method = '/ivr/billing/sms/by-template';
    var inputParams = {
          "sendTimeout": 10000
        };
    inputParams.timeout = 7000;
    
    // Иногда дата начала основного интента совпадает с датой начала интента отправки СМС.
    // Чтобы отдельть начало интента отправки СМС от основного, добавляем 1 секунду
    var d = getDefaultTimeZoneDate();
    d.setSeconds(d.getSeconds() + 1);
    var beginDt = d.toISOString();
    
    inputParams.body = {
          "dialogId": $.sessionId,
          "smsTemplateName": smsTemplateName
        }
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        customLog('Request failed with status ' + response.status);
        $.session.SMSstatus = false;
        intentResultCode = 3;
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                $.session.SMSstatus = false;
                intentResultCode = 3;
            }
        }
    }
    logIntent(intentCode, beginDt, getNow(), 0, intentResultCode, '', $.session.stateLog || $.session.prevStateLog);
}

function createAppeal(comment, clientUtterance, productId, problem){
    var method = '/ivr/billing/create/appeal';
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId,
          "comment": comment,
          "clientUtterance": clientUtterance,
          "productId": productId,
          "problem": problem,
          "routerCallKey": $.session.cisco.rck,
          "routerCallKeyDay": $.session.cisco.rckd
        }
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        customLog('Request failed with status ' + response.status);
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
            }
        }
        $.session.applId = response.data.requestId;
    }
    return;
}

function closeAppeal(requestId){
    var method = '/ivr/billing/close/appeal';
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId,
          "requestId": requestId
        }
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        customLog('Request failed with status ' + response.status);
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
            }
        }
    }
    return;
}

function checkSwitchAccident(){
    var method = '/ivr/billing/await/check/switch/accident';
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId
        }
    var response = {};
    
    // если есть request id, забираем ответ по нему
    if($.session.awaitRequestId){
        response = getResponseByRequestId($.session.awaitRequestId);
    }else{
        response = executeAction(method, inputParams);
    }
    
    if(response.data && !$.session.waitResponse){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
            }
        }
        $.session.switchAccident = response.data.switchAccident;
    }
    return;
}

function checkSession(){
    var method = '/ivr/billing/await/check/session';
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId,
          "sendTimeout": 250000
        }
    var response = {};
    
    // если есть request id, забираем ответ по нему
    if($.session.awaitRequestId){
        response = getResponseByRequestId($.session.awaitRequestId);
    }else{
        response = executeAction(method, inputParams);
    }
    
    if(response.data && !$.session.waitResponse){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
            }
        }
        
        $.session.activeSessionAvailable = response.data.activeSessionAvailable;
        $.session.serviceRequestRequired = false;
        $.session.aao = false;
        // проверим, надо ли создать СЗ
        // если сессия поднялась, то СЗ не нужна
        if(!$.session.activeSessionAvailable){
            // если adminStatus был равен 1 (UP) и остался равен 1 (UP), a operStatus был равен 0 (DOWN) и остался равен 0 (DOWN), то нужна СЗ на ТС
            if($.session.adminStatus == 'up' && response.data.adminStatus == 'up' && $.session.operStatus == 'down' && response.data.operStatus == 'down'){
                $.session.serviceRequestRequired = true;
                $.session.serviceRequestComment = 'Роутер/Combo. Нет активной сессии.  Схема и плотность подключения проверены. По EQM oper status - Down.';
            }
            // если оба статуса стали down, то нужна СЗ на ААО
            if(response.data.adminStatus == 'down' && response.data.operStatus == 'down'){
                $.session.serviceRequestRequired = true;
                $.session.aao = true;
                $.session.serviceRequestComment = 'Роутер/Combo. Нет активной сессии.  Схема и плотность подключения проверены. По EQM admin status - Down.';
            }
        }
        $.session.adminStatus = response.data.adminStatus;
        $.session.operStatus = response.data.operStatus;
    }
}

function refreshCktv(){
    var method = '/ivr/billing/refresh/cktv';
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId
        }
    inputParams.timeout = 500;
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        customLog('Request failed with status ' + response.status);
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
            }
        }
    }
    return;
}

function checkPromisedPayment(){
    var method = '/ivr/billing/await/check/promised/payment';
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId
        }
    inputParams.timeout = 500;
    var response = {};
    
    // если есть request id, забираем ответ по нему
    if($.session.awaitRequestId){
        response = getResponseByRequestId($.session.awaitRequestId);
    }else{
        response = executeAction(method, inputParams);
    }
    
    if(response.data && !$.session.waitResponse){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
                $.session.promisedPaymentError = response.data.errorCode;
            }
        }
        $.session.canGetpromisedPayment = response.data.canGet;
        $.session.mono = response.data.mono;
        $.session.promisedPaymentMaxDay = response.data.promisedPaymentMaxDay;
        $.session.promisedPaymentCost = response.data.promisedPaymentCost;
        $.session.balanceActiveFrom = response.data.balanceActiveFrom;
        $.session.after15 = response.data.after15;
    }
    
    return;
}

function getPromisedPayment(days){
    var method = '/ivr/billing/get/promised/payment';
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId,
          "days": days
        }
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        customLog('Request failed with status ' + response.status);
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
                $.session.promisedPaymentError = response.data.errorMessage;
            }
        }
        $.session.requestSuccess = response.data.requestSuccess;
        $.session.promisedPaymentTotalCost = $.session.promisedPaymentCost * days;
    }
    return;
}

function activateTP(){
    var method = '/ivr/billing/activate/tariff/plan';
    var inputParams = {};
    $.session.tpActivationStatus = true;
    inputParams.body = {
          "dialogId": $.sessionId
        }
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        customLog('Request failed with status ' + response.status);
        $.session.tpActivationStatus = false;
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
                $.session.tpActivationStatus = false;
            }
        }
    }
    return;
}

function agreementTerminationRequest(){
    var method = '/ivr/billing/agreement/termination/request';
    var inputParams = {};
    $.session.firstTerminationRequest = true;
    inputParams.body = {
          "dialogId": $.sessionId,
          "routerCallKey": $.session.cisco.rck,
          "routerCallKeyDay": $.session.cisco.rckd
        }
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        customLog('Request failed with status ' + response.status);
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
            }
        }
        $.session.firstTerminationRequest = response.data.firstTerminationRequest;
    }
    return;
}

function checkTVpackageByChannels(){
    var method = '/ivr/billing/check/packets/by/channels';
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId,
          "tvChannelNameList": $.session.malfunctionChannels
        }
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        customLog('Request failed with status ' + response.status);
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
                $.session.tPactivationStatus = false;
            }
        }
        $.session.hasActivePacket = response.data.hasActivePacket;
        $.session.monthAgoInactivePacketId = response.data.monthAgoInactivePacketId;
    }
    return;
}

function checkOptom(){
    var method = '/ivr/billing/special/offer/check/optom';
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId,
          "optomNew": "new",
          "sendTimeout": 10000,
          "timeout": 30000
        }
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        customLog('Request failed with status ' + response.status);
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
            }
        }
        $.session.optom3soId = response.data.optom3soId;
        $.session.optom3active = response.data.optom3active;
        $.session.optom3available = response.data.optom3available;
        $.session.optom3paySum = response.data.optom3paySum;
        $.session.optom3enoughBalance = response.data.optom3enoughBalance;
        
        $.session.optom6soId = response.data.optom6soId;
        $.session.optom6active = response.data.optom6active;
        $.session.optom6available = response.data.optom6available;
        $.session.optom6paySum = response.data.optom6paySum;
        $.session.optom6enoughBalance = response.data.optom6enoughBalance;
        
        $.session.optom12soId = response.data.optom12soId;
        $.session.optom12active = response.data.optom12active;
        $.session.optom12available = response.data.optom12available;
        $.session.optom12paySum = response.data.optom12paySum;
        $.session.optom12enoughBalance = response.data.optom12enoughBalance;
        $.session.optomDateTo = response.data.optomDateTo;
    }

}

function setOptom(promo){
    var method = '/ivr/billing/special/offer/set/optom';
    var inputParams = {};
    var soId;
    $.session.setOptomStatus = true;

    switch(promo){
        case 3:
            soId = $.session.optom3soId;
            break;
        case 6:
            soId = $.session.optom6soId;
            break;
        case 12:
            soId = $.session.optom12soId;
            break;
        default:
            customLog('incorrect promo: ' + promo);
            $.session.setOptomStatus = false;
            return;
        }
        
    inputParams.body = {
          "dialogId": $.sessionId,
          "specialOfferId": soId,
          "optomNew": "new"
        }
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        customLog('Request failed with status ' + response.status);
                $.session.setOptomStatus = false;
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
                $.session.setOptomStatus = false;
            }
        }
    }
}

function cancelServiceRequest(){
    var method = '/ivr/billing/cancel/service/request';
    var inputParams = {};
    $.session.cancelSrvRequestStatus = true;
    inputParams.body = {
          "dialogId": $.sessionId
        }
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        customLog('Request failed with status ' + response.status);
        $.session.cancelSrvRequestStatus = false;
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
                $.session.cancelSrvRequestStatus = false;
            }
        }
    }
    return;
}

function checkAddress(){
    var method = '/ivr/billing/check/address';
    var inputParams = {};
    inputParams.body = {
          dialogId: $.sessionId,
          city: citiesData[$.session.city].ruCity,
          street: $.session.address.street,
          house: $.session.address.house,
          housing: $.session.address.housing
        }
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        customLog('Request failed with status ' + response.status);
    }
    $.session.checkAddressError = false;
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
                // $.session.canConnect = false;
                $.session.checkAddressError = true;
            }
        }
        $.session.privateSector = response.data.privateSector;
        $.session.houseId = response.data.houseId;
        $.session.xpon = response.data.xpon;
        $.session.streetName = response.data.streetName;
        $.session.canConnect = response.data.canConnect;
    }
    return;
}

function cancelSuspension(){
    var method = '/ivr/billing/cancel/suspension';
    var inputParams = {};
    $.session.cancelSuspensionStatus = true;
    inputParams.body = {
          "dialogId": $.sessionId
        }
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        customLog('Request failed with status ' + response.status);
        $.session.cancelSuspensionStatus = false;
    }
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
                $.session.cancelSuspensionStatus = false;
            }
        }
    }
    return;
}

function checkServices(){
    if($.session.userType == 'user'){
        checkServicesAuthorized();
    }else{
        checkAccidentByHouse();
    }
    
}

function checkServicesAuthorized(){
    var method = '/ivr/billing/await/check/services';
    var inputParams = {};
    var reboot = $.session.reboot || 0;
    $.session.proactiveProblems = false;
    inputParams.body = {
          "dialogId": $.sessionId,
          "productId": $.session.productId,
          "sendTimeout": 50000,
          "reboot": reboot
        }
    inputParams.timeout = 500;
    var response = {};
    
    // если есть request id, забираем ответ по нему
    if($.session.awaitRequestId){
        response = getResponseByRequestId($.session.awaitRequestId);
    }else{
        response = executeAction(method, inputParams);
    }
    
    if(response.data && !$.session.waitResponse){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
            }
        }
        $.session.proactiveResult = response.data.result;
        $.session.askSms = response.data.askSms;
        $.session.accidentId = response.data.accidentId;
        $.session.accident = response.data.accident;
        $.session.accidentEndDate = response.data.accidentEndDate;
        $.session.ppr = response.data.ppr > 0;
        $.session.currentPprEndDate = response.data.currentPprEndDate;
        $.session.futurePprStartDate = response.data.futurePprStartDate;
        $.session.futurePprEndDate = response.data.futurePprEndDate;
        $.session.potentialAccident = response.data.potentialAccident;
        $.session.recommendSum = response.data.recommendSum;
        $.session.subscriptionFee = response.data.subscriptionFee;
        $.session.promisedPaymentAvailable = response.data.promisedPaymentAvailable;
        $.session.serviceRequest = response.data.serviceRequest;
        $.session.serviceRequestId = response.data.serviceRequestId;
        $.session.serviceRequestStatus = response.data.serviceRequestStatus;
        $.session.serviceResult = response.data.serviceResult;
        $.session.servicePlanDate = response.data.servicePlanDate;
        $.session.connectionRequest = response.data.connectionRequest;
        $.session.rebooted = response.data.rebooted;
        $.session.rebootResult = response.data.rebootResult;
        $.session.rebootPortResult = response.data.rebootPortResult;
        $.session.dropSessionResult = response.data.dropSessionResult;
        $.session.rebootRouterResult = response.data.rebootRouterResult;
        $.session.checkSessionResult = response.data.checkSessionResult;
        $.session.sessionStatus = response.data.sessionStatus;
        $.session.authorizationType = response.data.authorizationType;
        $.session.tariffPlanActive = response.data.tariffPlanActive;
        $.session.adminStatus = response.data.adminStatus;
        $.session.operStatus = response.data.operStatus;
        $.session.portStateError = response.data.portStateError;
        
        if(($.session.proactiveResult && $.session.proactiveResult != 'spas') ||
            $.session.accident || $.session.ppr > 0 ||
            ($.session.serviceRequest && $.session.serviceRequestStatus != 'request-overdue') ||
            $.session.potentialAccident){
            // отправляем в проактив
            $.session.proactiveProblems = true;
        }
    }
    return;
}

function checkAccidentByHouse(){
    var method = '/ivr/billing/check/accidents/by/house';
    var inputParams = {};
    $.session.proactiveProblems = false;
    inputParams.body = {
          "dialogId": $.sessionId,
          "sendTimeout": 30000,
          "productId": $.session.productId
        }
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        customLog('Request failed with status ' + response.status);
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
            }
        }
        $.session.askSms = response.data.askSms;
        $.session.accidentId = response.data.accidentId;
        $.session.accident = response.data.accident;
        $.session.accidentEndDate= response.data.accidentEndDate;
        $.session.ppr = response.data.ppr > 0;
        $.session.currentPprEndDate = response.data.currentPprEndDate;
        $.session.futurePprStartDate = response.data.futurePprStartDate;
        $.session.futurePprEndDate = response.data.futurePprEndDate;
        $.session.potentialAccident = response.data.potentialAccident;
        
        if($.session.accident || $.session.ppr > 0 || $.session.potentialAccident){
            // отправляем в проактив
            $.session.proactiveProblems = true;
        }
    }
    return;
}

function setTechServiceSmsNotification(){
    var method = '/ivr/billing/sms/tech-service/set/sms/notification';
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId
        }
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        customLog('Request failed with status ' + response.status);
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
            }
        }
    }
    return;
}

function getSpasData(){
    var method = '/ivr/await/spas/data';
    var inputParams = {};
    $.session.spas = {};
    $.session.spas.internetProblem = {};
    $.session.spas.tvProblem = {};
    $.session.spas.internetProblem.actionCategories = false;
    $.session.spas.tvProblem.actionCategories = false;
    inputParams.body = {
          "dialogId": $.sessionId
        }
    inputParams.timeout = 500;
    var response = {};
    
    // если есть request id, забираем ответ по нему
    if($.session.awaitRequestId){
        response = getResponseByRequestId($.session.awaitRequestId);
    }else{
        response = executeAction(method, inputParams);
    }
    
    if(response.data && !$.session.waitResponse){
        if(response.data.errorMessage){
            if(response.data.errorMessage !== ''){
                customLog(response.data.details.message);
            }
        }
        
        $.session.spas.internetProblem = response.data.internetProblem;
        $.session.spas.tvProblem = response.data.tvProblem;
        // есть ли метрики по интернету
        if($.session.spas.internetProblem.technicalServiceRequest ||
           $.session.spas.internetProblem.networkAdministrationRequest ||
           $.session.spas.internetProblem.firstActionCategoryIsRequest ||
           $.session.spas.internetProblem.diagnostics ||
           $.session.spas.internetProblem.technicalSupport){
               $.session.spas.internetProblem.actionCategories = true;
        }
        // есть ли метрики по тв
        if($.session.spas.tvProblem.technicalServiceRequest ||
           $.session.spas.tvProblem.networkAdministrationRequest ||
           $.session.spas.tvProblem.firstActionCategoryIsRequest ||
           $.session.spas.tvProblem.diagnostics ||
           $.session.spas.tvProblem.technicalSupport ||
           $.session.spas.tvProblem.drtvTicket ||
           $.session.spas.tvProblem.flowLoss){
                $.session.spas.tvProblem.actionCategories = true;
        }
    }
    return;
    
}

function checkRedirectOctp(){
    var method = '/ivr/billing/check/redirect/octp';
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId
        }
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        customLog('Request failed with status ' + response.status);
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
            }
        }
        $.session.flagOctp = response.data.flagOctp;
        $.session.sessionOctp = response.data.sessionOctp;
    }
    return;
}

function getAvaialbeServiceRequestDays(){
    var method = '/ivr/billing/await/get/available/service/days';
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId,
          "productId": $.session.productId,
          "sendTimeout": 30000
        }
    var response = {};
    
    if($.session.awaitRequestId){
        response = getResponseByRequestId($.session.awaitRequestId);
    }else{
        response = executeAction(method, inputParams);
    }
    
    if(response.data && !$.session.waitResponse){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
            }
        }
        $.session.billingCurrentDate = response.data.currentDate;
        $.session.today = response.data.today;
        $.session.tomorrow = response.data.tomorrow;
        $.session.afterTomorrow = response.data.afterTomorrow;
    }
    return;
}

function getAvaialbeServiceRequestTimeSlots(){
    var method = '/ivr/billing/await/get/time-slots';
    var inputParams = {};
    $.session.firstHalf = false;
    $.session.secondHalf = false;
    inputParams.body = {
          "dialogId": $.sessionId,
          "productId": $.session.productId,
          "day": $.session.serviceRequestDay,
          "sendTimeout": 10000
        }
    var response = {};
    
    // если есть request id, забираем ответ по нему
    if($.session.awaitRequestId){
        response = getResponseByRequestId($.session.awaitRequestId);
    }else{
        response = executeAction(method, inputParams);
    }
    
    if(response.data && !$.session.waitResponse){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
            }
        }
        $.session.firstHalf = response.data.firstHalf;
        $.session.secondHalf = response.data.secondHalf;
        $.session.timeSlots = response.data.timeSlots;
    }
    return;
}

function createServiceRequest(){
    var method = '/ivr/billing/await/send/service/request';
    var inputParams = {};
    var pattern = /(\d{4})\-(\d{2})\-(\d{2})T(\d{2}):(\d{2}).+/;
    var serviceRequestDate = "";
    
    // нельзя перезаписывать в $.session.serviceRequestDate т.к. она используется для анонса даты и времени
    // дата отправляться должна в формате ""dd.MM.yyyy HH:mm""
    // проверяем есть ли дата, так как для network_administrator_request дата не нужна
    if($.session.serviceRequestDate){
        serviceRequestDate = $.session.serviceRequestDate.replace(pattern,'$3.$2.$1 $4:$5');
    }
    inputParams.body = {
          "dialogId": $.sessionId,
          "productId": $.session.productId,
          "comment": $.session.serviceRequestComment,
          "aao": $.session.aao,
          "dateTime": serviceRequestDate,
          "sendTimeout": 10000
        }
    var response = {};
    
    // если есть request id, забираем ответ по нему
    if($.session.awaitRequestId){
        response = getResponseByRequestId($.session.awaitRequestId);
    }else{
        response = executeAction(method, inputParams);
    }
    
    if(response.data && !$.session.waitResponse){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
            }
        }
        $.session.serviceRequestStatus = response.data.status;
        $.session.serviceRequestInfo = response.data.info;
    }
    return;
}

function checkRouter(){
    var method = '/ivr/billing/await/check/router';
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId
        }
    inputParams.timeout = 500;
    var response = {};
    
    // если есть request id, забираем ответ по нему
    if($.session.awaitRequestId){
        response = getResponseByRequestId($.session.awaitRequestId);
    }else{
        response = executeAction(method, inputParams);
    }
    if(response.data && !$.session.waitResponse){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
            }
        }
        $.session.haveRouter = response.data.haveRouter;
        $.session.activeSession= response.data.activeSession;
        $.session.sessionType = response.data.sessionType;
    }
    return;
    
}

function getBaseInfo(){
    var method = '/ivr/billing/get/base/info';
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId
        }
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        customLog('Request failed with status ' + response.status);
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
            }
        }
        $.session.agreementId = response.data.agreementId;
        $.session.agreementNumber = response.data.agreementNumber;
        $.session.phoneStatus = response.data.phoneStatus;
        $.session.suspension = response.data.suspension;
        $.session.domofon = response.data.domofon;
        $.session.cifral = response.data.cifral;
        $.session.ktv = response.data.ktv;
        $.session.gorsv = response.data.gorsv;
        $.session.dctv = response.data.dctv;
        $.session.mobile = response.data.mobile;
        $.session.tvPackagesInfo = response.data.tvPackagesInfo;
        $.session.ktv_social = response.data.ktvSocial;
        $.session.int = response.data.internet;
        $.session.domrutv = response.data.domRuTV;
        $.session.appTypes = response.data.appTypes;
        $.session.octpAppTypeTransfer = response.data.octpAppTypeTransfer;
        $.session.subscriptionFeeIncrease = response.data.subscriptionFeeIncrease;
        $.session.tvSetup = response.data.tvSetup;
        $.session.hasIptv = response.data.hasIptv;
        $.session.b2bexists = response.data.b2bexists;
        $.session.iktvDevices = response.data.iktvDevices;
        
        if($.session.agreementNumber){
            $analytics.setSessionData("Agreement number", $.session.agreementNumber);
        }
    }
}

function logDialogError(apiMethod, errorMessage){
    if($.testContext){
        // автотесты не отпрвляем
        return;
    }
    var method = '/bi/send/dialog/error';
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId,
          "errorMessage": errorMessage,
          "apiMethod": apiMethod
        }
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        customLog('Request failed with status ' + response.status);
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
            }
        }
    }
}

function reboot(){
    var method = '/ivr/billing/await/reboot';
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId,
          "sendTimeout": 120000,
          "readTimeout": 100, // выставляем короткий таймаут, чтобы не ждать выполнения, а получить результат позже по id
          "timeoutCheck": 90,
          "timeoutReboot": 30
        }
    var response = {};
    
    response = executeAction(method, inputParams);
    $.session.rebootAwaitRequestId = $.session.awaitRequestId;
    $.session.waitResponse = false;
    delete $.session.awaitRequestId;
    customLog("rebootAwaitRequestId: " + $.session.rebootAwaitRequestId);
}

function getResponseByRequestId(requestId){
    var method = '/ivr/await/' + requestId;
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId
        }
    return executeAction(method, inputParams);
}
