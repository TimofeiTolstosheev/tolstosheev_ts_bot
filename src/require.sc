# скрипты
require: Scripts/dateTimeFuncs.js
require: Scripts/functions.js
require: Scripts/dialogLog.js
require: Scripts/chatApiAdapter.js
require: Scripts/BackendActions/auth.js
require: Scripts/BackendActions/actions.js
require: Scripts/BackendActionsES6/auth.js
    type = scriptEs6
    name = auth

# паттерны
require: Patterns/patterns.sc
#это встроенные. из них используем $yes, $no, $bot, $thanks, $money целиком
require: patterns.sc
    module = sys.zb-common

# сценарии
require: sc/Transfer.sc

# операции с договором
require: sc/Agreement/AgreementStop.sc
require: sc/Agreement/ChangeTariff.sc
require: sc/Agreement/EquipmentReturn.sc
require: sc/Agreement/ManageServicePacket.sc
require: sc/Agreement/RestoreLogoPass.sc
require: sc/Agreement/StopAgreementFinal.sc
require: sc/Agreement/SubscriptionFeeIncrease.sc
require: sc/Agreement/TarifActivate.sc
require: sc/Agreement/CancelSuspend.sc


# финансы
require: sc/Finance/BalanceInquiry.sc
require: sc/Finance/Invoice.sc
require: sc/Finance/PaymentsMethods.sc
require: sc/Finance/PromisedPayment.sc
require: sc/Finance/StillNotGetCharge.sc

# смс
require: sc/SMS/SMS.sc
require: sc/SMS/SMSInformIntent.sc

# технические сценарии
require: sc/TechnicalProblems/Accident.sc
require: sc/TechnicalProblems/ApplicationTVtoGoHelp.sc
require: sc/TechnicalProblems/ChannelsPackages.sc
require: sc/TechnicalProblems/ConfigureEquipment.sc
require: sc/TechnicalProblems/ConfigureInternetConnection.sc
require: sc/TechnicalProblems/PageNotOpenLowSpeedNoLinkPPoE.sc
require: sc/TechnicalProblems/Proactive.sc
require: sc/TechnicalProblems/SetupDecoder.sc
require: sc/TechnicalProblems/SetupRouter.sc
require: sc/TechnicalProblems/TechQuestion.sc
require: sc/TechnicalProblems/TVChannelProblem.sc
require: sc/TechnicalProblems/SPAS.sc
require: sc/TechnicalProblems/PhoneProblem.sc


# остальные сценарии
require: sc/Other/AgentRequest.sc
require: sc/Other/ApplicationHelp.sc
require: sc/Other/BonusesOffers.sc
require: sc/Other/Consultation.sc
require: sc/Other/ContractDetails.sc
require: sc/Other/IntercomAndCCTV.sc
require: sc/Other/OffersHelpIntent.sc
require: sc/Other/PotentionalClient.sc
require: sc/Other/Pretension.sc
require: sc/Other/ServiceCentersAddress.sc
require: sc/Other/ServiceRequestInfo.sc
require: sc/Other/Thanks.sc
require: sc/Other/Greeting.sc