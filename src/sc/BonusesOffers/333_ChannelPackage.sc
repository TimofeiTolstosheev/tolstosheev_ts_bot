theme: /ChannelPackage
    
    state: ChannelPackageSMS
        q!: $channelPackage
        intent!: /333_ChannelPackage
        script:
            startIntent('/333_ChannelPackage');
            announceAudio(audioDict.Channel_package);
          
        if: $.session.cellPhone
            script:
                sendSMSbyTemplate('823_TVpackagesSMS');
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
