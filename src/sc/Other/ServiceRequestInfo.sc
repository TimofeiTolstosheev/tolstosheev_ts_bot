theme: /ServiceRequestInfo
    state: CheckServiceRequest
        q!: $460_ServiceRequestInfo
        intent!: /460_ServiceRequestInfo
        script:
            startIntent('460_ServiceRequestInfo');
            checkAccess();
        if: $.session.serviceRequestStatus == 'request-pending'
            go!: /ServiceRequestInfo/CheckServiceRequest/PendingServiceRequest
        else:
            if: $.session.serviceRequestStatus == 'request-undefined'
                go!: /ServiceRequestInfo/CheckServiceRequest/NoPlanDateServiceRequest
            else:
                go!: /Transfer/Перевод чата
        
        state: PendingServiceRequest
            script:
                $.temp.srvPlanDateFrom = dateToDefaultString($.session.srvPlanDateFrom).substring(0, 5); // из srvPlanDateFrom берем только время
                $.temp.srvPlanDateTo = dateToDefaultString($.session.srvPlanDateTo);
            a: На ваш адрес назначена заявка на выезд специалиста. Инженер приедет с {{$.temp.srvPlanDateFrom}} до {{$.temp.srvPlanDateTo}}. Если вы хотите отменить или перенести заявку, сделать это можно в <a href="https://dom.ru/lk/orders">личном кабинете</a>.
            go!: /Еще вопросы?
        
        state: NoPlanDateServiceRequest
            a: На ваш адрес назначена заявка на выезд специалиста. Инженер выполнит работы в ближайшее время. Если вы хотите отменить или перенести заявку, сделать это можно в <a href="https://dom.ru/lk/orders">личном кабинете</a>.
            go!: /Еще вопросы?
