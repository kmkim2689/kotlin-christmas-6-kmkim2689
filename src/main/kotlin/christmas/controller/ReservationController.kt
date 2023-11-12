package christmas.controller

import christmas.constants.Constants.UNIT_PRICE
import christmas.constants.StepMessages.STEP_START_RESERVATION
import christmas.domain.AdvantageManager
import christmas.domain.ReservationInfo
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

        OutputView.apply {
            printOrders(reservationInfo)
            printTotalPriceBeforeDiscounts(reservationInfo.getTotalPriceBeforeDiscounts().toDecimalFormat())
            printPresentationResult(advantageManager.getPresentationResult())
        }
    }

    fun Int.toDecimalFormat(): String {
        val decimalFormat = DecimalFormat("#,###$UNIT_PRICE")
        return decimalFormat.format(this)
    }
}