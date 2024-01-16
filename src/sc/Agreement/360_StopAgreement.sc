theme: /StopAgreement
    
    state: CheckGSKworkTime
        q!: $stopAgreement
        intent!: /360_StopAgreement
        script:
            startIntent('/360_StopAgreement');
            checkGSKtime($.session.city);
        
        if: $.session.gskWorkTime
            go!: /StopAgreement/CheckGSKworkTime/GSKwork
        else:
            go!: /StopAgreement/CheckGSKworkTime/GSKnotWork
            
        state: GSKwork
            if: $.session.userType == 'user'
                go!: /StopAgreement/CheckGSKworkTime/GSKwork/Auth
            else:
                go!: /StopAgreement/CheckGSKworkTime/GSKwork/NotAuth
                
            state: Auth
                script:
                    agreementTerminationRequest();
                    if ($.session.firstTerminationRequest){
                        $.session.callerInput = 'smart_nsk';
                    }else{
                        $.session.callerInput = 'smart2_nsk';
                    }
                    $.session.intent.resultCode = 8;
                    announceAudio(audioDict.PerevodNaStarshego);
                go!: /Transfer/Transfer
                
            state: NotAuth
                script:
                    $.session.callerInput = 'nrd_nua';
                    $.session.intent.resultCode = 8;
                    announceAudio(audioDict.PerevodNaStarshego);
                go!: /Transfer/Transfer
            
        state: GSKnotWork
            script:
                $.session.callerInput = 'operator';
                $.session.intent.resultCode = 6;
                announceAudio(audioDict.transferToAgentForFurther_3);
            go!: /Transfer/Transfer
