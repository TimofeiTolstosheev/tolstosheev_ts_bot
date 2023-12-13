import { axios } from "axios";

// Запросы к сервису авторизации

// Выполняет переданный запрос в Auth Service
const executeAuthAction = (method, inputParams) => {
    var isProd = $env.get("isProd", false);
    var authServiceEndpoint = isProd ? $context.injector.authServiceEndpoint : $context.injector.authServiceEndpointTest;
    if($context.testContext){
        authServiceEndpoint = $context.injector.authServiceEndpointTest;
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
        timeout: inputParams.timeout|| $context.injector.actionTimeout
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
        $context.session.requestTimeout = true;
    }
    
    return response;
};

const authByAgreementId = () => {
    var method = '/chatbot/token';
    var inputParams = {};
    inputParams.body = {
          "dialogId": $context.sessionId,
          "region": $context.session.region || 'perm', // если неизвестный регион, пробуем в Пермь
          "userId": $context.session.userId,
          "agreementId": $context.session.agreementId
    };
    var response = executeAuthAction(method, inputParams);
    
    if(response.status != 200){
        var errorMessage = toPrettyString(response.error) || 'unknown error';
        customLog('Request failed with status ' + response.status +'. Error: ' + errorMessage);
        $context.session.userType = 'guest'; // считаем пользователя неавторизованным
        return;
    }
    
    if(response.data){
        $context.session.token = response.data.token;
        $context.session.userType = response.data.type;
        $context.session.userAuth = $context.session.userType == 'user';
        if(response.data.errorCode){
            if(response.data.errorCode !== 0){
                log(response.data.errorMessage);
                $context.session.authErrorcode = response.data.errorCode;
            }
        }
    }else{
        log('Custom log.No response data');
    }
    
};

export default { authByAgreementId };
