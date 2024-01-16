// Запросы к сервису авторизации

function logAuthAction(method, message, suffix) {
    var output = "Auth action " + method + " " + message;
    if (suffix) output += ": " + suffix;
    return customLog(output);
}

// Выполняет переданный запрос в Auth Service
function executeAuthAction(method, inputParams){
    var isProd = $env.get("isProd", false);
    var authServiceEndpoint = isProd ? $.injector.authServiceEndpoint.prod : $.injector.authServiceEndpoint.test;
    if($.testContext){
        authServiceEndpoint = $.injector.authServiceEndpoint.test;
    }
    if($.request.channelType == 'chatwidget' && isProd && !$.testContext){
        // напоминаем тестирующему, что он выполняет запрос в прод
        $reactions.answer("ЗАПРОС ВЫПОЛНЯЕТСЯ НА ПРОДЕ! ТЕСТИРУЙ ОСОЗНАННО!");
    }
    var url = authServiceEndpoint + method;
    logAuthAction(method, "Request URL", url);

    inputParams.body = inputParams.body || {};
    logAuthAction(method, "InputParams", toPrettyString(inputParams));
    inputParams.body.secretKey = $secrets.get("auth_secret_key", "null");
    inputParams.body.dialogId = $.sessionId || "test";
    var options = {
        dataType: "json",
        headers: {
            "Content-Type": "application/json",
            "Authorization": inputParams.auth ? "Bearer " + inputParams.auth : undefined
        },
        body: inputParams.body,
        timeout: inputParams.timeout || $.injector.actionTimeout
    };

    var response;
    try {
        response = $http.post(url, options);
        delete $.session.authErrorCode;
        logAuthAction(method, "Response", toPrettyString(response));

        if (response.status == 500) {
            try{
                var j = JSON.parse(response.error); // response.error приходит строкой
                $.session.authErrorCode = j.details[0].code;
                $.session.userType = "guest"; // считаем пользователя неавторизованным
                logAuthAction(method, "AuthErrorMessage", j.details[0].message);
                return response;
            }catch(e){
                customLog("Failed to parse error response. Error: " + e);
            }
        }
        
        if (response.status !== 200) {
            var errorMessage = toPrettyString(response.error) || "unknown error";
            if (response.status === -1) {
                $.session.requestTimeout = true;
            }
            $.session.userType = "guest"; // считаем пользователя неавторизованным
            throw new Error("Request failed with status " + response.status + ". Error: " + errorMessage);
        }
        
        if (response.data) {
            $.session.token = response.data.token;
            $.session.userType = response.data.type;
            if(response.data.errorCode){
                if (response.data.errorCode !== 0) {
                    logAuthAction(method, "AuthErrorMessage", response.data.errorMessage);
                    $.session.authErrorCode = response.data.errorCode;
                }
            }
        } else {
            throw new Error("Response data undefined");
        }
    } catch(e) {
        logAuthAction(method, e.message);
    }
    
    return response;
}

function authByPhoneNumber() {
    var method = '/ivr/token';
    var inputParams = {};
    inputParams.body = {
          "phoneNumber": $.session.phoneNumber,
          "region": billingAlias[$.session.region] || 'perm' // если неизвестный регион, пробуем в Пермь
    };
    return executeAuthAction(method, inputParams);
}

function authByAddress() {
    var method = "/ivr/token/by/address";
    var city;
    if($.session.cityData){
        city = $.session.cityData.name || $.session.cityData.code;
    }else{
        city = "Пермь";
    }
    var inputParams = {};
    inputParams.auth = $.session.addressAuthToken;
    inputParams.body = {
        phoneNumber: $.session.phoneNumber,
        region: billingAlias[$.session.region] || "perm",
        city: city,
        street: $.session.address.street,
        house: $.session.address.house,
        housing: $.session.address.housing
    };
    
    return executeAuthAction(method, inputParams);
}

function authByFIOAndHouseId() {
    var method = "/ivr/token/by/fio/and/house-id";
    var inputParams = {};
    inputParams.auth = $.session.houseIdAuthToken;
    inputParams.body = {
        phoneNumber: $.session.phoneNumber,
        region: billingAlias[$.session.region] || "perm",
        fio: $.session.fio
    }
    return executeAuthAction(method, inputParams);
}

function authByFIOAndHouse() {
    var method = "/ivr/token/by/fio/and/house";
    var inputParams = {};
    var house = "";
    inputParams.auth = $.session.houseAuthToken;
    if($.session.address){
        house = $.session.address.house;
    }
    inputParams.body = {
        phoneNumber: $.session.phoneNumber,
        region: billingAlias[$.session.region] || "perm",
        fio: $.session.fio,
        house: house
    }
    return executeAuthAction(method, inputParams);
}
