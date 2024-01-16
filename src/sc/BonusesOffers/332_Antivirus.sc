theme: /Antivirus
    
    state: AntivirusSMS
        q!: $antivirus
        intent!: /332_Antivirus
        script:
            startIntent('/332_Antivirus');
            announceAudio(audioDict.Antivirus);
          
        if: $.session.cellPhone
            script:
                sendSMSbyTemplate('822_AntivirusSMS');
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
