package christmas.view

import christmas.constants.Constants
import christmas.constants.StepMessages
import christmas.domain.OrderItem
import christmas.domain.ReservationInfo

object OutputView {

    fun printOrders(reservationInfo: ReservationInfo) {
        println("${StepMessages.STEP_MONTH_DECEMBER} " +
                "${reservationInfo.dayOfReservation}${StepMessages.STEP_DAY}${StepMessages.STEP_ON} " +
                "${StepMessages.STEP_PREVIEW}${Constants.LINE_FEED}")
        println(Constants.TITLE_ORDERS)

        reservationInfo.items.onEach {
            println("${it.menu} ${it.amount}${Constants.UNIT_AMOUNT}")
        }

        println()
    }

    fun printTotalPriceBeforeDiscounts(price: String) {
        println(price)
    }
}