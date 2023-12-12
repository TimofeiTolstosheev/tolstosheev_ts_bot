// интеграция с ChatAPI

function getGenesysData(){
    customLog('Request: ' + toPrettyString($.request));
    var genesysData;
    if($.request.channelType == 'chatapi'){
        if($.request.data){
            genesysData = $.request.data;
        }else{
            //TODO log dialog error?
            customLog("No data from Genesys found.");
        }
    }else{
        // имитация данных от Генезиса. В $.session.genesysData смотрим для автотестов (неавторизованный: '{"uniqueFrom":"Mobile_chat_353618_556_b2c","alias":"perm-dev","region":"perm-dev","cityId":"556","newVer":"0","os":"Android","ver":"3.56.1","bld":"3056010","InteractionId":"jaicpTest"}';)
        var genesysDataStr = $.session.genesysData || '{"agreementId":"2604160","agreementNumber":"780001894866","uniqueFrom":"Mobile_chat_353618_556_b2c","alias":"","region":"dev-dcc-spb","cityId":"556","newVer":"0","os":"Android","ver":"3.56.1","bld":"3056010","InteractionId":"jaicpTest"}';
        genesysData = JSON.parse(genesysDataStr);
    }
    customLog('GenesysData: ' + toPrettyString(genesysData));
    parseGenesysData(genesysData);
    
    $analytics.setSessionData("Agreement number", $.session.agreementNumber || "");
    $analytics.setSessionData("Region", $.session.region || "");
    $analytics.setSessionData("Interaction ID", $.session.interactionId || "");
    $analytics.setSessionData("OS", $.session.os || "");
    $analytics.setSessionData("New OS version", $.session.newVer || "");
}

// парсинг json от Генезиса
function parseGenesysData(genesysData){
    $.session.userAuth = $.session.userAuth || false;
    $.session.uniqueFrom = genesysData.uniqueFrom || genesysData.clientId;
    $.session.agreementId = genesysData.agreementId;
    $.session.agreementNumber = genesysData.agreementNumber;
    $.session.cityId = genesysData.cityId;
    $.session.region = '';
    $.session.interactionId = genesysData.InteractionId;
    $.session.region = genesysData.alias || genesysData.region;
    $.session.city = citiesByRegion[$.session.region] || 'perm'; // не нашли город, считаем, что Пермь
    $.session.newVer = genesysData.newVer;
    $.session.os = genesysData.os;
    $.session.ver = genesysData.ver;
    $.session.bld = genesysData.bld;

    if($.session.uniqueFrom){
        var pattern = '([\\w]+)_([\\d]+)_([\\d]+)_([\\w]+)';
        var matcher = $.session.uniqueFrom.match(pattern);
    
        if(matcher){
            $.session.userChat = matcher[1];
            $.session.userId = matcher[2];
            $.session.userType = matcher[4];
        }
    }
    
    if($.session.agreementId){
        $.session.userAuth = true;
    }
    
    customLog("Genesys parsed data. userAuth: " + $.session.userAuth);
    customLog("Genesys parsed data. uniqueFrom: " + $.session.uniqueFrom);
    customLog("Genesys parsed data. agreementId: " + $.session.agreementId);
    customLog("Genesys parsed data. agreementNumber: " + $.session.agreementNumber);
    customLog("Genesys parsed data. cityId: " + $.session.cityId);
    customLog("Genesys parsed data. interactionId: " + $.session.interactionId);
    customLog("Genesys parsed data. region: " + $.session.region);
    customLog("Genesys parsed data. city: " + $.session.city);
    customLog("Genesys parsed data. newVer: " + $.session.newVer);
    customLog("Genesys parsed data. os: " + $.session.os);
    customLog("Genesys parsed data. ver: " + $.session.ver);
    customLog("Genesys parsed data. bld: " + $.session.bld);
    customLog("Genesys parsed data. userChat: " +$.session.userChat);
    customLog("Genesys parsed data. userId: " +$.session.userId);
    customLog("Genesys parsed data. userType: " +$.session.userType);
}
