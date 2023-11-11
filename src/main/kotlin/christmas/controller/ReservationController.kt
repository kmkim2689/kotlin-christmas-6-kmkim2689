package christmas.controller

import christmas.constants.StepMessages.STEP_START_RESERVATION
import christmas.domain.InputView

object ReservationController {
    fun startReservation() {
        println(STEP_START_RESERVATION)
        val dayOfReservation = InputView.getDayOfReservation()
    }

}