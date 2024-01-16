// взаимодействие с Resterisk
function parseResteriskInputParams(){
    customLog('Request: ' + toPrettyString($.request));
    
    $.session.cisco = {};
    $.session.region = '';
    $.session.cisco.rckd = '';
    $.session.cisco.rck = '';
    $.session.cisco.var4 = '';
    $.session.cisco.icm = '';
    $.session.cisco.a = '';
    $.session.cisco.uw = '';
    
    if ($.request.channelType == "resterisk") {
        $.session.phoneNumber = fixPhoneNumber($dialer.getCaller());
        $.session.sipHeaders = $dialer.getSipHeaders();
        $.session.botId = $.request.botId;
        if($.session.sipHeaders.Tojustaivars || $.session.sipHeaders.tojustaivars){
            var tojustaivars = $.session.sipHeaders.Tojustaivars || $.session.sipHeaders.tojustaivars;
            var splitHeaders = tojustaivars.split(';');
            var headersObject = {};
            //для экономии переменных в циске переменные называются по буквам алфавита по порядку
            for(var i = 0; i < splitHeaders.length; i++){
                var s = splitHeaders[i].split('=');
                headersObject[s[0].toLowerCase()] = s[1];
            }
            $.session.region = headersObject.b || '';
            $.session.cisco.rckd = headersObject.c || '';
            $.session.cisco.rck = headersObject.d || '';
            $.session.cisco.var4 = headersObject.f || '';
            $.session.cisco.icm = headersObject.g || '';
            $.session.cisco.a = headersObject.h || '';
            $.session.cisco.uw = headersObject.i || '';
        }else{
            customLog('tojustaivars parameter is undefined');
        }
        
        customLog("sipHeaders: " + toPrettyString($.session.sipHeaders));
        customLog("phoneNumber: " + $.session.phoneNumber);
        customLog("region: " + $.session.region);
        customLog("rckd: " + $.session.cisco.rckd);
        customLog("rck: " + $.session.cisco.rck);
        customLog("a: " + $.session.cisco.a);
        customLog("icm: " + $.session.cisco.icm);
        customLog("uw: " + $.session.cisco.uw);
    }
    if($.session.phoneNumber){
        // исправим номер, если пришел больше 11 знаков
        $.session.phoneNumber = fixPhoneNumber($.session.phoneNumber);
    }
    $analytics.setSessionResult('Input headers: ' + toPrettyString($.session.sipHeaders));
    return;
}

function transferByCallerInput(callerInput){
    customLog("calerInput: " + callerInput);
    callerInput = callerInput || $.injector.defaultCallerInput;
    $.session.cisco = $.session.cisco || {};
    $.response.replies = $.response.replies || [];
    var h = {
            "Fj": "a=" + $.session.callerInput + ";" +
                  "b=" + $.session.region + ";" +
                  "c=" + $.session.cisco.rckd + "," + $.session.cisco.rck + ";" +
                  "e=" + ($.session.agreementNumber || "") + ";" +
                  "f=" + $.session.cisco.var4 + ";" +
                  "g=" + $.session.cisco.icm + ";" +
                  "h=" + $.session.cisco.a + ";" +
                  "i=" + $.session.cisco.uw
        };
    
    $analytics.setSessionResult('Input headers: ' + toPrettyString($.session.sipHeaders) + '. Output headers: ' + toPrettyString(h));
    var phoneNumber = $env.get("isProd", false) ? $.injector.ciscoExitPhoneNumberProd : $.injector.ciscoExitPhoneNumberTest;
    $.response.replies.push({
        type: "switch",
        phoneNumber: phoneNumber,
        method: "refer",
        timeout: $.injector.transferTimeout,
        transferChannel: $.session.botId,
        headers: h
    });
}

function fixPhoneNumber(phoneNumber){
    var phoneNumberLength = 11;
    var resultPhoneNumber;
	var startPos = Math.max(0, phoneNumber.length() - phoneNumberLength);
	var endPos = phoneNumber.length();
	resultPhoneNumber = phoneNumber.substring(startPos, endPos);
	// если номер 11 знаков, то меняем первую 8 на 7
	if(resultPhoneNumber.length() == 11){
	    resultPhoneNumber = '7' + resultPhoneNumber.substring(1);
	}
	return resultPhoneNumber;
}