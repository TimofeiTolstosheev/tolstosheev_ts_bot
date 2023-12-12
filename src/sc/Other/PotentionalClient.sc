theme: /PotentionalClient
    state: Потенциальный клиент
        q!: $340_PotentialClientService
        intent!: /340_PotentionalClient
        script:
            startIntent('340_PotentionalClient');
            if($.session.userAuth){
                $reactions.transition("/Transfer/Перевод чата");
            }else{
                checkOCPtime($.session.city);
                if($.session.ocpWorkingTime){
                    $reactions.transition("/Transfer/Перевод чата OCP");
                }else{
                    $reactions.transition("/Transfer/Перевод чата");
                }
            }
