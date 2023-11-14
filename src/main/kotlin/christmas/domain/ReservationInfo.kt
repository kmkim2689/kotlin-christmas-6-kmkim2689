package christmas.domain

data class ReservationInfo(
    val dayOfReservation: Int,
    val items: List<OrderItem>
) {
    fun getTotalPriceBeforeDiscounts(): Int = items.sumOf {
        it.menu.price * it.amount
    }
}