// Запросы в Action Service

// выполняет переданный запрос в Action Service
function executeAction(method, inputParams){
    var isProd = $env.get("isProd", false);
    var actionServiceEndpoint = isProd ? $.injector.actionServiceEndpoint : $.injector.actionServiceEndpointTest;
    if($.testContext){
        actionServiceEndpoint = $.injector.actionServiceEndpointTest;
    }
    
    var url = actionServiceEndpoint + method;
    customLog('request URL: ' + url);
    customLog('inputParams: ' + toPrettyString(inputParams));
    var options = {
        dataType: "json",
        headers: {
            "Content-Type": "application/json",
            "Authorization": 'Bearer ' + $.session.token
        },
        body: inputParams.body,
        timeout: inputParams.timeout|| $.injector.actionTimeout
    };
    
    var response;
    try{
        response = $http.post(url, options);
        customLog('response: ' + toPrettyString(response));
    }catch(e){
        customLog('request failed. Error: ' + e);
    }
    
    var analyticErrorMessage = "";
    if(response.status != 200){
        analyticErrorMessage = "Status: " + response.status + ". ";
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                analyticErrorMessage += "Error code: " + response.data.errorCode + ". Message: " + response.data.errorMessage;
            }
        }
    }
    $analytics.setSessionData(method, analyticErrorMessage);
    
    // TODO обработка таймаутов - тут, либо в каждом методе отдельно
    if(response.status === -1){
        $.session.requestTimeout = true;
    }
    
    return response;
}

function sendDialogLog(){
    if(!$.session.dialogLog){
        customLog('dialogLog is undefined.');
        return;
    }
    
    var method = '/chatbot/bi/send/dialog/log'
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId,
          "genesysInteractionId": $.session.interactionId,
          "genesysUserData": $.session.uniqueFrom,
          "chatLog": $.session.dialogLog,
          "chatEndType": $.session.chatEndType
        }

    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        //throw new Error('Request failed with status ' + response.status);
        customLog('Request failed with status ' + response.status);
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
            }
        }
    }
    delete $.session.dialogLog;
}

function agreementTerminationRequest(){
    var method = '/chatbot/billing/agreement/termination/request'
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
        $.session.firstTerminationRequest = response.data.firstTerminationRequest;
    }
}

function getBalance(){
    var method = '/chatbot/billing/balance/'
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId
        }
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        throw new Error('Request failed with status ' + response.status);
    }
    
    if(response.data){
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                customLog(response.data.errorMessage);
                throw new Error(response.data.errorMessage);
            }
        }
        $.session.recommendSum = response.data.recommendSum;
        $.session.nextPeriodDate = response.data.nextPeriodDate;
        $.session.chargesSum = response.data.chargesSum;
        $.session.textParam = response.data.textParam;
        $.session.opEndDate = response.data.opEndDate;
        $.session.curBalance = response.data.curBalance;
        $.session.firstPay = response.data.firstPay;
        $.session.leasDz = response.data.leasDz;
        $.session.closeDate = response.data.closeDate;
        $.session.lastPayment = response.data.lastPayment;
        $.session.lastPaymentSum = response.data.lastPaymentSum;
        $.session.retStr = response.data.retStr;
        $.session.isDeny = response.data.isDeny;
        // peridoDates приходит либо числом - номер месяца, либо периодом в формате: 01.01.2023 - 15.02.2023
        $.session.periodDates = response.data.periodDates;
        if($.session.periodDates){
            if($.session.periodDates.length <= 2){
                switch($.session.periodDates){
                    case "1": $.session.periodDates = "январь";
                              break;
                    case "2": $.session.periodDates = "февраль";
                              break;
                    case "3": $.session.periodDates = "март";
                              break;
                    case "4": $.session.periodDates = "апрель";
                              break;
                    case "5": $.session.periodDates = "май";
                              break;
                    case "6": $.session.periodDates = "июнь";
                              break;
                    case "7": $.session.periodDates = "июль";
                               break;
                    case "8": $.session.periodDates = "август";
                              break;
                    case "9": $.session.periodDates = "сентябрь";
                              break;
                    case "10": $.session.periodDates = "октябрь";
                               break;
                    case "11": $.session.periodDates = "ноябрь";
                               break;
                    case "12": $.session.periodDates = "декабрь";
                               break;
                    default: break;
                }
            }else{
                $.session.periodDates = "период с " + $.session.periodDates.replace("-", " по ");
            }
        }
    }
}

function checkPromisedPayment(){
    var method = '/chatbot/billing/check/promised/payment';
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
                $.session.promisedPaymentError = response.data.errorCode;
            }
        }
        $.session.canGetpromisedPayment = response.data.canGet;
        $.session.mono = response.data.mono;
        $.session.promisedPaymentMaxDay = response.data.promisedPaymentMaxDay;
        $.session.promisedPaymentCost = response.data.promisedPaymentCost.toString();
        $.session.balanceActiveFrom = response.data.balanceActiveFrom;
        $.session.after15 = response.data.after15;
    }
}

function getPromisedPayment(days){
    var method = '/chatbot/billing/get/promised/payment';
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
        $.session.promisedPaymentTotalCost = response.data.totalCost;
    }
}

function checkAccess(){
    var method = '/chatbot/billing/check/services/access';
    $.session.proactiveProblem = false;
    $.session.proactiveResult = $.session.proactiveResult || 'spas';
    $.session.productId = $.session.productId || 5;
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId,
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
                $.session.promisedPaymentError = response.data.errorMessage;
            }
        }
    
        if(response.data.servicePlanDate){
            var serviceDateFrom = new Date(response.data.servicePlanDate);
            var serviceDateTo   = new Date(response.data.servicePlanDate);
            $.session.srvPlanDateFrom = serviceDateFrom;
            $.session.srvPlanDateTo = serviceDateTo;
            $.session.srvPlanDateTo.setHours(serviceDateTo.getHours() + 1);
        }
        $.session.proactiveResult = response.data.result;
        $.session.recommendSum = response.data.recommendSum;
        $.session.curBalance = response.data.curBalance;
        $.session.promisedPaymentAvailable = response.data.promisedPaymentAvailable;
        $.session.serviceRequestStatus = response.data.serviceRequestStatus;
                        
        if($.session.accident || $.session.ppr || $.session.proactiveResult != 'spas' ||
           $.session.serviceRequestStatus == 'request-pending' || $.session.serviceRequestStatus == 'request-undefined'){
            $.session.proactiveProblem = true;
        }
    }
}

function refreshCktv(){
    var method = '/chatbot/billing/refresh/cktv';
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
}

function getSpasData(){
    var method = '/chatbot/spas/data';
    var inputParams = {};
    $.session.spas = {};
    $.session.spas.internetProblem = {};
    $.session.spas.tvProblem = {};
    $.session.spas.internetProblem.actionCategories = false;
    $.session.spas.tvProblem.actionCategories = false;
    inputParams.body = {
          "dialogId": $.sessionId
        }
    var response = executeAction(method, inputParams);
    
    if(response.status != 200){
        customLog('Request failed with status ' + response.status);
    }
    
    if(response.data){
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
}

function reboot(){
    var method = '/chatbot/billing/reboot';
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId,
          "timeoutCheck": 90,
          "timeoutReboot": 30
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
        $.session.rebooted = response.data.rebooted;
        $.session.rebootResult = response.data.rebootResult;
        $.session.rebootPortResult = response.data.rebootPortResult;
        $.session.dropSessionResult = response.data.dropSessionResult;
        $.session.rebootRouterResult = response.datarebootRouterResult;
        $.session.sessionStatus = response.data.sessionStatus;
    }
}


function getBaseInfo(){
    var method = '/chatbot/billing/get/base/info';
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
        $.session.ktv = response.data.ktv;
        $.session.gorsv = response.data.gorsv;
        $.session.dctv = response.data.dctv;
        $.session.mobile = response.data.mobile;
        $.session.ktvSocial = response.data.ktvSocial;
        $.session.internet = response.data.internet;
        $.session.domRuTV = response.data.domRuTV;
        $.session.appTypes = response.data.appTypes;
        $.session.askSms = response.data.askSms;
        $.session.accidentId = response.data.accidentId;
        $.session.accident = response.data.accident;
        $.session.accidentEndDate = response.data.accidentEndDate;
        $.session.ppr = response.data.ppr;
        $.session.currentPprEndDate = response.data.currentPprEndDate;
        $.session.futurePprStartDate = response.data.futurePprStartDate;
        $.session.futurePprEndDate = response.data.futurePprEndDate;
        $.session.potentialAccident = response.data.potentialAccident;
        $.session.errorMessage = response.data.errorMessage;
        $.session.errorCode = response.data.errorCode;
        $.session.octpAppTypeTransfer = response.data.octpAppTypeTransfer;
        $.session.subscriptionFeeIncrease = response.data.subscriptionFeeIncrease;
    }
}

function logDialogError(apiMethod, errorMessage){
    var method = '/chatbot/bi/send/dialog/error';
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
