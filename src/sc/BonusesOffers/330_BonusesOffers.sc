theme: /OtherPromo
    
    state: OtherPromoSMS
        q!: $otherPromo
        intent!: /330_OtherPromo
        script:
            startIntent('/330_OtherPromo');
        go!: /OtherPromo/OtherPromoSMS/SendSMSwithPromo
            
        state: SendSMSwithPromo    
            script:
                announceAudio(audioDict.bonuses);
          
            if: $.session.cellPhone
                script:
                    sendSMSbyTemplate('826_BonusesSMS');
                if: $.session.SMSstatus
                    go!: SMSsuccess
                else: 
                    go!: SMSerror 
            else: 
                go!: /WhatElse/WhatElse
                       
            state: SMSsuccess
                script:
                    announceAudio(audioDict.sms_Otpravlena);
                go!: /WhatElse/WhatElse
            
            state: SMSerror
                go!: /WhatElse/WhatElse
