theme: /ServiceRequestInfo
    state: CheckAuth
        q!: $serviceRequestInfo
        intent!: /460_ServiceRequestInfo
        script:
            startIntent('460_ServiceRequestInfo');
        if: $.session.userType == 'user'
            go!: /ServiceRequestInfo/CheckSRV
        else:
            go!: /ServiceRequestInfo/ToOperator
            
    state: CheckSRV
        script:
            // параметры для await-экшна
            $.session.awaitAction = $.session.awaitAction || {};
            $.session.awaitAction.returnState = $context.currentState;
            $.session.awaitAction.action = "checkServices";
            $.session.awaitAction.readTimeout = 4000;
            $.session.awaitAction.audio = audioDict.initialWait;
            if($.session.awaitAction.status || $.session.proactiveResult){
                delete $.session.awaitAction;
                
                if(!$.session.serviceRequest || $.session.serviceRequestStatus == 'request-overdue'){
                    // заявки нет или просрочена
                    $reactions.transition("/ServiceRequestInfo/ToOperator");
                }else{
                    if($.session.serviceRequestStatus == 'request-pending'){
                        // заявка не просрочена
                        $reactions.transition("/ServiceRequestInfo/CheckSRV/AnnouncePlanTime");
                    }else{
                        // плановое время не указано
                        $reactions.transition("/ServiceRequestInfo/CheckSRV/NoPlanTime");
                    }
                }
            }else{
                $reactions.transition("/AwaitAction/RunAction");
            }
        
        state: AnnouncePlanTime
            script:
                announceAudio(audioDict.TimeAvailable);
                $temp.servicePlanDate = new Date($.session.servicePlanDate);
                announceDateGen($temp.servicePlanDate);
                announceAudio(audioDict.at);
                announceHoursFromDate($temp.servicePlanDate);
            if: $.session.cellPhone
                go!: /ServiceRequestInfo/CheckSRV/AskToCancelOrPostpone
            else:
                go!: /ServiceRequestInfo/CheckSRV/AskToCancel
                
        state: NoPlanTime
            script:
                announceAudio(audioDict.TimeNoAvailable);
            if: $.session.srvResult === 3
                go!: /WhatElse/WhatElse
            else:
                go!: /ServiceRequestInfo/CheckSRV/AskToCancel
            
        state: AskToCancelOrPostpone
            script:
                announceAudio(audioDict.ZayavkaBezOperatorPerenos);
            
            state: Postpone
                q: * $transferServiceRequest *
                script:
                    $.session.intent.stepsCnt++;
                    sendSMS('810_ServiceRequestSMS');
                    if($.session.SMSstatus){
                        announceAudio(audioDict.sms_OtpravlenaPerenosSZ);
                        $reactions.transition("/WhatElse/WhatElse");
                    }else{
                        announceAudio(audioDict.sms_neOtpravlena_transfer);
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = 1;
                        // если попали в сценарий из проактива, то переводим с проверкой меток ОЦТП
                        var transferState = $.session.intent.currentIntent == "460_ServiceRequestInfo" ? "/Transfer/Transfer" : "/Transfer/CheckOCTP";
                        stopIntent();
                        $reactions.transition(transferState);
                    }
                
            state: Cancel
                q: * $cancelServiceRequest *
                script:
                    $.session.intent.stepsCnt++;
                go!: /ServiceRequestInfo/CheckSRV/Cancel
                
            state: ServiceRequestInfo
                q: $anotherQuestion || toState = "/WhatElse/WhatElse/HaveQuestions"
                q: @Need помощ* || toState = "/WhatElse/WhatElse/HaveQuestions"
                q: $serviceRequestInfo
                intent: /460_ServiceRequestInfo
                script:
                    $.session.intent.stepsCnt++;
                go!: /ServiceRequestInfo/CheckSRV/ToOperatorWithServiceRequest
    
            state: СatchAll || noContext = true
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        $reactions.transition("/ServiceRequestInfo/CheckSRV/AskToCancelOrPostpone");
                    }else{
                        $reactions.transition("/ServiceRequestInfo/ToOperator");
                    }
            
        state: AskToCancel
            script:
                announceAudio(audioDict.ZayavkaBezOperator);

            state: Cancel
                q: * $cancelServiceRequest *
                script:
                    $.session.intent.stepsCnt++;
                go!: /ServiceRequestInfo/CheckSRV/Cancel
                
            state: ServiceRequestInfo
                q: $serviceRequestInfo
                intent: /460_ServiceRequestInfo
                script:
                    $.session.intent.stepsCnt++;
                go!: /ServiceRequestInfo/CheckSRV/ToOperatorWithServiceRequest
    
            state: СatchAll || noContext = true
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    if(countRepeatsInRow() < $injector.noMatchLimit){
                        $reactions.transition("/ServiceRequestInfo/CheckSRV/AskToCancel");
                    }else{
                        announceAudio(audioDict.perevod_na_okc);
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = 6;
                        $reactions.transition("/Transfer/CheckOCTP");
                    }
        
        state: Cancel
            script:
                announceAudio(audioDict.PodtverdiOtmeny);

            state: Confirm
                q: $yes
                q: * $cancelServiceRequest *
                script:
                    $.session.intent.stepsCnt++;
                    cancelServiceRequest();
                    if(!$.session.cancelSrvRequestStatus){
                        announceAudio(audioDict.sms_neOtpravlena_transfer);
                        $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                        $.session.intent.resultCode = 3;
                        // если попали в сценарий из проактива, то переводим с проверкой меток ОЦТП
                        var transferState = $.session.intent.currentIntent == "460_ServiceRequestInfo" ? "/Transfer/Transfer" : "/Transfer/CheckOCTP";
                        $reactions.transition(transferState);
                    }else{
                        announceAudio(audioDict.ZayakaOtmena);
                        $reactions.transition("/WhatElse/WhatElse");
                    }
                
            state: Deny
                q: $no
                q: необходим*
                q: @Need
                script:
                    $.session.intent.stepsCnt++;
                go!: /ServiceRequestInfo/CheckSRV/ToOperatorWithServiceRequest
    
            state: СatchAll || noContext = true
                event: noMatch
                script:
                    $.session.intent.stepsCnt++;
                    if(countRepeatsInRow() < $injector.noMatchLimit) {
                        $reactions.transition("/ServiceRequestInfo/CheckSRV/Cancel");
                    }else{
                        $reactions.transition("/ServiceRequestInfo/CheckSRV/ToOperatorWithServiceRequest");
                    }
    
        state: ToOperatorWithServiceRequest
            script:
                announceAudio(audioDict.OperatorZayvkaYes);
                $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
                $.session.intent.resultCode = 6;
                // если попали в сценарий из проактива, то переводим с проверкой меток ОЦТП
                var transferState = $.session.intent.currentIntent == "460_ServiceRequestInfo" ? "/Transfer/Transfer" : "/Transfer/CheckOCTP";
                $reactions.transition(transferState);
        
    state: ToOperator
        script:
            announceAudio(audioDict.OperatorIzSZ);
            $.session.callerInput = getIntentParam($.session.intent.currentIntent, 'callerInput') || $.injector.defaultCallerInput;
            $.session.intent.resultCode = 1;
            // если попали в сценарий из проактива, то переводим с проверкой меток ОЦТП
            var transferState = $.session.intent.currentIntent == "460_ServiceRequestInfo" ? "/Transfer/Transfer" : "/Transfer/CheckOCTP";
            $reactions.transition(transferState);
