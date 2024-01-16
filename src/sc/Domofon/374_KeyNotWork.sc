theme: /KeyNotWork
    
    state: KeyNotWorkOKC
        q!: $keyNotWork
        intent!: /374_KeyNotWork
        script:
            startIntent('/374_KeyNotWork');
        if: $.session.cifral
            go!: /KeyNotWork/KeyNotWorkOKC/Cifral
        else:
            go!: /KeyNotWork/KeyNotWorkOKC/SMSkey
        
        state: Cifral
            script:
                announceAudio(audioDict.CifralGo);
                $.session.intent.resultCode = 32;
                $.session.callerInput = 'cifral';
            go!: /Transfer/Transfer
            
        state: SMSkey
            script:
                announceAudio(audioDict.KeyNotWork);           
                if($.session.cellPhone){
                    sendSMSbyTemplate('837_NotWorkingKeySMS');
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
                