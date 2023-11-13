package christmas.controller

import christmas.constants.Constants.PRICE_FORMAT
import christmas.constants.Constants.SIGN_DISCOUNT
import christmas.constants.Constants.UNIT_PRICE
import christmas.constants.StepMessages.STEP_START_RESERVATION
import christmas.domain.AdvantageManager
import christmas.domain.ReservationInfo
import christmas.utils.toDecimalFormatAdvantagePrice
import christmas.utils.toDecimalFormatPrice
import christmas.view.InputView
import christmas.view.OutputView
import java.text.DecimalFormat

object ReservationController {
    fun startReservation() {
        println(STEP_START_RESERVATION)
        val dayOfReservation = InputView.getDayOfReservation()
        val orderItems = InputView.getOrderList()
        val reservationInfo = ReservationInfo(dayOfReservation, orderItems)
        val advantageManager = AdvantageManager(reservationInfo.getTotalPriceBeforeDiscounts(), reservationInfo)

        printReservationResult(reservationInfo, advantageManager)
    }

    private fun printReservationResult(
        reservationInfo: ReservationInfo,
        advantageManager: AdvantageManager
    ) {
        OutputView.apply {
            printOrders(reservationInfo)
            printTotalPriceBeforeDiscounts(reservationInfo.getTotalPriceBeforeDiscounts().toDecimalFormatPrice())
            printPresentationResult(advantageManager.getPresentationResult())
            printAdvantages(advantageManager.getTotalAdvantages())
            printTotalAdvantageAmount(advantageManager.getTotalAdvantagePrice().toDecimalFormatAdvantagePrice())
            printPriceAfterDiscount(advantageManager.getTotalPriceAfterDiscount().toDecimalFormatPrice())
            printBadge(advantageManager.getBadge(advantageManager.getTotalAdvantagePrice()))
        }
    }
}