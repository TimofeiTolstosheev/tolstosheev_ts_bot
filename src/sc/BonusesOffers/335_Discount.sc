theme: /DiscountIntent
    
    state: DiscountIntentSMS
        q!: $discount
        intent!: /335_Discount
        script:
            startIntent('/335_Discount');
            announceAudio(audioDict.Discount);
        
        if: $.session.cellPhone
            script:
                sendSMSbyTemplate('821_DiscountSMS');
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
