package christmas.domain

import christmas.constants.Constants.DAYS_PER_WEEK
import christmas.constants.Constants.DAY_OF_WEEK_WEEKDAY
import christmas.constants.Constants.DAY_OF_WEEK_WEEKEND
import java.text.DecimalFormat

data class ReservationInfo(
    val dayOfReservation: Int,
    val items: List<OrderItem>
) {
    fun getTotalPriceBeforeDiscounts(): Int = items.sumOf {
        it.menu.price * it.amount
    }
}