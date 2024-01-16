theme: /KeyBuy
    
    state: KeyBuyOKC
        q!: $keyBuy
        intent!: /372_KeyBuy
        script:
            startIntent('/372_KeyBuy');
        if: $.session.cifral
            go!: /KeyBuy/KeyBuyOKC/Cifral
        else:
            go!: /KeyBuy/KeyBuyOKC/SMSkey
        
        state: Cifral
            script:
                announceAudio(audioDict.CifralGo);
                $.session.intent.resultCode = 32;
                $.session.callerInput = 'dmf_cifral';
            go!: /Transfer/Transfer
            
        state: SMSkey
            script:
                announceAudio(audioDict.KeyBuy);           
                if($.session.cellPhone){
                    sendSMSbyTemplate('835_BuyKeySMS');
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
