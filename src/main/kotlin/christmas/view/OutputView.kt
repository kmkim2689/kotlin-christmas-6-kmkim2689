package christmas.view

import christmas.constants.Constants
import christmas.constants.Constants.LINE_FEED
import christmas.constants.Constants.NONE
import christmas.constants.Constants.SIGN_COLON
import christmas.constants.Constants.TITLE_ADVANTAGES
import christmas.constants.Constants.TITLE_ADVANTAGE_AMOUNT
import christmas.constants.Constants.TITLE_BADGE
import christmas.constants.Constants.TITLE_BEFORE_DISCOUNT
import christmas.constants.Constants.TITLE_PRESENT
import christmas.constants.Constants.TITLE_PRICE_AFTER_DISCOUNT
import christmas.constants.StepMessages
import christmas.domain.*
import christmas.utils.toDecimalFormatAdvantagePrice

object OutputView {

    fun printOrders(reservationInfo: ReservationInfo) {
        println("${StepMessages.STEP_MONTH_DECEMBER} " +
                "${reservationInfo.dayOfReservation}${StepMessages.STEP_DAY}${StepMessages.STEP_ON} " +
                "${StepMessages.STEP_PREVIEW}${Constants.LINE_FEED}")
        println(Constants.TITLE_ORDERS)

        reservationInfo.items.onEach {
            println("${it.menu.menuName} ${it.amount}${Constants.UNIT_AMOUNT}")
        }

        println()
    }

    fun printTotalPriceBeforeDiscounts(price: String) {
        println(TITLE_BEFORE_DISCOUNT)
        println("$price$LINE_FEED")
    }

    fun printPresentationResult(result: String) {
        println(TITLE_PRESENT)
        println("$result$LINE_FEED")
    }

    fun printAdvantages(advantageItems: List<AdvantageItem>) {
        println(TITLE_ADVANTAGES)
        if (advantageItems.isEmpty()) println(NONE)
        advantageItems.onEach {
            println("${it.advantageName}$SIGN_COLON ${it.advantageAmount.toDecimalFormatAdvantagePrice()}")
        }
        println()
    }

    fun printTotalAdvantageAmount(amount: String) {
        println(TITLE_ADVANTAGE_AMOUNT)
        println("$amount$LINE_FEED")
    }

    fun printPriceAfterDiscount(amount: String) {
        println(TITLE_PRICE_AFTER_DISCOUNT)
        println("$amount$LINE_FEED")
    }
}