# скрипты
require: scripts/functions.js
require: scripts/dialogLog.js
require: scripts/converters.js
require: scripts/address.js
require: scripts/resteriskAdapter.js
require: scripts/backendActions/auth.js
require: scripts/backendActions/actions.js
require: scripts/backendActions/base64.js
require: scripts/realizers/numberRealizer.js
require: scripts/realizers/dateTimeRealizer.js
require: scripts/realizers/moneyRealizer.js

# это наши паттерны (часть создана с 0, часть - на основе встроенных)
require: patterns/AgreementPatterns.sc
require: patterns/BonusesOffersPatterns.sc
require: patterns/CommonPatterns.sc
require: patterns/DomofonPatterns.sc
require: patterns/FinancePatterns.sc
require: patterns/OtherPatterns.sc
require: patterns/TariffPatterns.sc
require: patterns/TechnicalProblemsPatterns.sc
require: patterns/AgentRequestPatterns.sc
require: patterns/YesNoOtherAnswersPatterns.sc
require: patterns/InitScenario.sc
#это встроенные. из них используем $yes, $no, $bot, $thanks, $money целиком
require: patterns.sc
    module = sys.zb-common

# инициализация
require: sc/InitScenario.sc

# технические стейты для автотестов
require: sc/techState.sc

# общие сценарии
require: sc/AgentRequest.sc
require: sc/LocalAgentRequest.sc
require: sc/LocalNoInput.sc
require: sc/NoMatch.sc
require: sc/transfer.sc
require: sc/whatElse.sc
require: sc/awaitAction.sc

# операции с договором
require: sc/Agreement/150_Suspension.sc
require: sc/Agreement/155_CancelSuspension.sc
require: sc/Agreement/160_ReissueAgreement.sc
require: sc/Agreement/360_StopAgreement.sc
require: sc/Agreement/420_AgreementNumber.sc
require: sc/Agreement/470_ChangeAgreementData.sc
require: sc/Agreement/480_RestoreAgreement.sc
require: sc/Agreement/730_OtherContract.sc

# бонусы
require: sc/BonusesOffers/330_BonusesOffers.sc
require: sc/BonusesOffers/331_ManageSubscription.sc
require: sc/BonusesOffers/332_Antivirus.sc
require: sc/BonusesOffers/333_ChannelPackage.sc
require: sc/BonusesOffers/334_NewBonuses.sc
require: sc/BonusesOffers/335_Discount.sc

# домофон
require: sc/Domofon/370_Domofon.sc
require: sc/Domofon/371_KeyIntercom.sc
require: sc/Domofon/372_KeyBuy.sc
require: sc/Domofon/373_KeyActivate.sc
require: sc/Domofon/374_KeyNotWork.sc
require: sc/Domofon/375_DomofonActivate.sc
require: sc/Domofon/376_OtherDomofon.sc
require: sc/Domofon/377_SmartDom.sc
require: sc/Domofon/378_DomofonNotWork.sc
require: sc/Domofon/379_CCTV.sc

# финансы
require: sc/Finance/10_PaymentsMethods.sc
require: sc/Finance/170_SubscriptionFeeIncrease.sc
require: sc/Finance/50_ReceiptIssue.sc
require: sc/Finance/520_Accruals.sc
require: sc/Finance/550_RentAccruals.sc
require: sc/Finance/530_PaymentIssue.sc
require: sc/Finance/560_Recalculation.sc
require: sc/Finance/5_FinanceQuestion.sc
require: sc/Finance/710_WholeCheaper.sc
require: sc/Finance/720_Autopayment.sc
require: sc/Finance/80_PromisedPayment.sc
require: sc/Finance/90_Balance.sc

# тариф
require: sc/Tariff/320_CommonInfoTariff.sc
require: sc/Tariff/321_ChangeTariff.sc
require: sc/Tariff/322_TariffInfo.sc
require: sc/Tariff/323_TariffType.sc

# технические сценарии
require: sc/TechnicalProblems/130_SetupRouter.sc
require: sc/TechnicalProblems/250_CommonTechnicalProblems.sc
require: sc/TechnicalProblems/260_280_310_PageSpeedConnection.sc
require: sc/TechnicalProblems/270_TVQualityProblem.sc
require: sc/TechnicalProblems/275_OneChannelProblem.sc
require: sc/TechnicalProblems/290_TvChannelProblem.sc
require: sc/TechnicalProblems/300_NoLink.sc
require: sc/TechnicalProblems/350_PhoneProblem.sc
require: sc/TechnicalProblems/475_TVServiceProblem.sc
require: sc/TechnicalProblems/485_TVEquipmentProblem.sc
require: sc/TechnicalProblems/490_CommonInternet.sc
require: sc/TechnicalProblems/495_CommonTV.sc
require: sc/TechnicalProblems/540_SetupTVchannels.sc
require: sc/TechnicalProblems/CreateServiceRequest.sc
require: sc/TechnicalProblems/proactive.sc
require: sc/TechnicalProblems/AreYouHome.sc

# остальное
require: sc/Other/20_ServiceCentersAddress.sc
require: sc/Other/230_WebCabCredentials.sc
require: sc/Other/240_DeviceReplacement.sc
require: sc/Other/340_PotentialClient.sc
require: sc/Other/380_NoInput.sc
require: sc/Other/390_NoAnswer.sc
require: sc/Other/430_UserMoving.sc
require: sc/Other/440_RestoreWiFiPassword.sc
require: sc/Other/460_ServiceRequestInfo.sc
require: sc/Other/510_HotSpotIssue.sc
require: sc/Other/580_LegalEntity.sc
require: sc/Other/590_OtherIntent.sc
require: sc/Other/610_CallsOnHold.sc
require: sc/Other/650_CommonQuestion.sc
require: sc/Other/740_Pretension.sc
require: sc/Other/750_CableReplacement.sc
