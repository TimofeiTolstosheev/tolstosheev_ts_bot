theme: /ManageSubscription
    
    state: ManageSubscriptionSMS
        q!: $manageSubscription
        intent!: /331_ManageSubscription
        script:
            startIntent('/331_ManageSubscription');
            announceAudio(audioDict.Subscription);
          
        if: $.session.cellPhone
            script:
                sendSMSbyTemplate('824_ManageSubscriptionSMS');
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
