// Запросы к сервису авторизации

// Выполняет переданный запрос в Auth Service
function executeAuthAction(method, inputParams){
    var isProd = $env.get("isProd", false);
    var authServiceEndpoint = isProd ? $.injector.authServiceEndpoint : $.injector.authServiceEndpointTest;
    if($.testContext){
        authServiceEndpoint = $.injector.authServiceEndpointTest;
    }
    
    var url = authServiceEndpoint + method;
    customLog('request URL: ' + url);
    customLog('inputParams: ' + toPrettyString(inputParams));
    inputParams.body.secretKey = $secrets.get("auth_secret_key", "null");
    var options = {
        dataType: "json",
        headers: {
            "Content-Type": "application/json"
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
    
    // TODO обработка таймаутов - тут, либо в каждом методе отдельно
    if(response.status === -1){
        $.session.requestTimeout = true;
    }
    
    return response;
}


function authByAgreementId() {
    var method = '/chatbot/token';
    var inputParams = {};
    inputParams.body = {
          "dialogId": $.sessionId,
          "region": $.session.region || 'perm', // если неизвестный регион, пробуем в Пермь
          "userId": $.session.userId,
          "agreementId": $.session.agreementId
    };
    var response = executeAuthAction(method, inputParams);
    
    if(response.status != 200){
        var errorMessage = toPrettyString(response.error) || 'unknown error';
        customLog('Request failed with status ' + response.status +'. Error: ' + errorMessage);
        $.session.userType = 'guest'; // считаем пользователя неавторизованным
        return;
    }
    
    if(response.data){
        $.session.token = response.data.token;
        $.session.userType = response.data.type;
        $.session.userAuth = $.session.userType == 'user';
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                log(response.data.errorMessage);
                $.session.authErrorcode = response.data.errorCode;
            }
        }
    }else{
        log('Custom log.No response data');
    }
    
}
