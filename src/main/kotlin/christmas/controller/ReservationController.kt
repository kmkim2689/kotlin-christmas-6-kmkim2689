package christmas.controller

import christmas.constants.Constants.LINE_FEED
import christmas.constants.Constants.TITLE_ORDERS
import christmas.constants.Constants.UNIT_AMOUNT
import christmas.constants.StepMessages.STEP_DAY
import christmas.constants.StepMessages.STEP_MONTH_DECEMBER
import christmas.constants.StepMessages.STEP_ON
import christmas.constants.StepMessages.STEP_PREVIEW
import christmas.constants.StepMessages.STEP_START_RESERVATION
import christmas.domain.OrderItem
import christmas.domain.ReservationInfo
import christmas.view.InputView
import christmas.view.OutputView

object ReservationController {
    fun startReservation() {
        println(STEP_START_RESERVATION)
        val dayOfReservation = InputView.getDayOfReservation()
        val orderItems = InputView.getOrderList()
        val reservationInfo = ReservationInfo(dayOfReservation, orderItems)

        OutputView.printOrders(reservationInfo)
    }



}