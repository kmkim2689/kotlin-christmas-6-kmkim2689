package christmas.controller

import christmas.constants.Constants.PRICE_FORMAT
import christmas.constants.Constants.SIGN_DISCOUNT
import christmas.constants.Constants.UNIT_PRICE
import christmas.constants.StepMessages.STEP_START_RESERVATION
import christmas.domain.AdvantageManager
import christmas.domain.ReservationInfo
import christmas.domain.ResultCalculator
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
        val advantageManager = AdvantageManager(
            reservationInfo.getTotalPriceBeforeDiscounts(),
            reservationInfo
        )
        val resultCalculator = ResultCalculator(
            reservationInfo.getTotalPriceBeforeDiscounts(),
            advantageManager.getCalculatedTotalAdvantages()
        )

        printReservationResult(reservationInfo, resultCalculator)
    }

    private fun printReservationResult(
        reservationInfo: ReservationInfo,
        resultCalculator: ResultCalculator
    ) {
        OutputView.apply {
            printOrders(reservationInfo)
            printTotalPriceBeforeDiscounts(reservationInfo.getTotalPriceBeforeDiscounts().toDecimalFormatPrice())
            printPresentationResult(resultCalculator.getPresentationResult())
            printAdvantages(resultCalculator.getTotalAdvantages())
            printTotalAdvantageAmount(resultCalculator.getTotalAdvantagePrice().toDecimalFormatAdvantagePrice())
            printPriceAfterDiscount(resultCalculator.getTotalPriceAfterDiscount().toDecimalFormatPrice())
            printBadge(resultCalculator.getBadge())
        }
    }
}