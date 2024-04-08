theme: /KeyActivate
    
    state: KeyActivateOKC
        q!: $keyActivate
        intent!: /373_KeyActivate
        script:
            startIntent('/373_KeyActivate');
        if: $.session.cifral
            go!: /KeyActivate/KeyActivateOKC/Cifral
        else:
            go!: /KeyActivate/KeyActivateOKC/SMSkey
        
        state: Cifral
            script:
                announceAudio(audioDict.CifralGo);
                $.session.intent.resultCode = 32;
                $.session.callerInput = 'dmf_cifral';
            go!: /Transfer/Transfer
            
        state: SMSkey
            script:
                announceAudio(audioDict.KeyActivate);           
                if($.session.cellPhone){
                    sendSMSbyTemplate('836_ActivateKeySMS');
                    if($.session.SMSstatus){
                        $reactions.transition("SMSsuccess");
                    }else{
                        $reactions.transition("SMSerror");
                    }
                }else{
                    $reactions.transition("/WhatElse/WhatElse");
                }
                       
            state: SMSsuccess
                script:
                    announceAudio(audioDict.sms_Otpravlena);
                go!: /WhatElse/WhatElse
                
            state: SMSerror
                script:
                    announceAudio(audioDict.sms_neOtpravlena_transfer);
                    $.session.callerInput = 'DMF';
                    $.session.intent.resultCode = 6;
                go!: /Transfer/Transfer
