theme: /NewBonuses
    
    state: NewBonusesSMS
        q!: $newBonuses
        intent!: /334_NewBonuses
        script:
            startIntent('/334_NewBonuses');
            announceAudio(audioDict.bonuses);
          
        if: $.session.cellPhone
            script:
                sendSMSbyTemplate('825_NewBonusesSMS');
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
