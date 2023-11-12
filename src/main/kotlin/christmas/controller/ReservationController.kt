package christmas.controller

import christmas.constants.Constants.UNIT_PRICE
import christmas.constants.StepMessages.STEP_START_RESERVATION
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

        OutputView.printOrders(reservationInfo)
    }

    fun Int.toDecimalFormat(): String {
        val decimalFormat = DecimalFormat("#,###$UNIT_PRICE")
        return decimalFormat.format(this)
    }
}