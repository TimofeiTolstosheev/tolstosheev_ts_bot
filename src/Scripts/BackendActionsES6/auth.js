// Запросы к сервису авторизации
import axios from "axios";

const logAuthAction = (method, message, suffix) => {
    var output = "Auth action " + method + " " + message;
    if (suffix) output += ": " + suffix;
    return customLog(output);
}

// Выполняет переданный запрос в Auth Service
const executeAuthAction = (method, inputParams) => {
    var isProd = $env.get("isProd", false);
    var authServiceEndpoint = isProd ? $context.injector.authServiceEndpoint : $context.injector.authServiceEndpointTest;
    if($context.testContext){
        authServiceEndpoint = $context.injector.authServiceEndpointTest;
    }
    
    var url = authServiceEndpoint + method;
    logAuthAction(method, 'request URL', url);
    logAuthAction(method, 'inputParams', toPrettyString(inputParams));
    inputParams.body.secretKey = $secrets.get("auth_secret_key", "null");
    inputParams.body.dialogId = $.sessionId ?? "test";
    var options = {
        timeout: inputParams.timeout ?? $context.injector.actionTimeout,
        dataType: "json",
        headers: {
            "Content-Type": "application/json"
            }
        };
    
    var response = {};
    axios.post(url, inputParams.body, options)
        .then((r) => {
            response = r;
            logAuthAction(method, 'response', toPrettyString(response));
        }, (e) => {
            response.status = -1;
            if (e.code === 'ECONNABORTED') {
                logAuthAction(method, 'request timed out', toPrettyString(e));
            }else{
                logAuthAction(method, 'request failed with error', toPrettyString(e));
            }
        });
    
    await sleep(500);
    while(!response.status){
        await sleep(inputParams.waitTimeout ?? $context.injector.waitTimeout);
        $conversationApi.sendTextToClient(inputParams.waitTimeoutPrompt ?? $context.injector.waitTimeoutPrompt);
    }
    return response;
};

const authByAgreementId = () => {
    var method = '/chatbot/token';
    var inputParams = {};
    inputParams.body = {
          "region": $context.session.region ?? 'perm', // если неизвестный регион, пробуем в Пермь
          "userId": $context.session.userId,
          "agreementId": $context.session.agreementId
    };
    var response = executeAuthAction(method, inputParams);
    
    if(response.status != 200){
        var errorMessage = toPrettyString(response.error) ?? 'unknown error';
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

const sleep = async (ms) => {
  return new Promise(resolve => setTimeout(resolve, ms));
};

const req = async () =>{
    $conversationApi.sendTextToClient("start request");
    var r = {};
    axios.get("https://www.cbr-xml-daily.ru/latest.js")
        .then((response) => {
            $conversationApi.sendTextToClient("response: " + toPrettyString(response.status));
            r = response;
            customLog('Axios response: ' + toPrettyString(response));
        }, (error) => {
            $conversationApi.sendTextToClient("error: " + error);
            customLog('request failed. Error: ' + error);
        });
    $conversationApi.sendTextToClient("r before sleep: " + toPrettyString(r.status));
    await sleep(5000);
    $conversationApi.sendTextToClient("r after sleep: " + toPrettyString(r.status));
}

export default { authByAgreementId, req };
