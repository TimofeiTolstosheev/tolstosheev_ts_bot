theme: /ServiceCentersAddress
    
    state: AddressSMS
        q!: $serviceCentersAddress
        intent!: /20_ServiceCentersAddress
        script:
            startIntent('/20_ServiceCentersAddress');
            $.session.intent.stepsCnt++;
            if(countRepeatsInRow() < 3) {
                announceAudio(audioDict.no_servicecenters);
            }else{
                $reactions.transition('/Transfer/IntentLimiTransfer');
            }
            
        if: $.session.cellPhone
            script:
                sendSMSbyTemplate('815_SCAddressesSMS');
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
