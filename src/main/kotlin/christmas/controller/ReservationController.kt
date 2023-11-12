package christmas.controller

import christmas.constants.StepMessages.STEP_START_RESERVATION
import christmas.domain.ReservationInfo
import christmas.view.InputView

object ReservationController {
    fun startReservation() {
        println(STEP_START_RESERVATION)
        val dayOfReservation = InputView.getDayOfReservation()
        val orderItems = InputView.getOrderList()
        val reservationInfo = ReservationInfo(dayOfReservation, orderItems)
    }

}