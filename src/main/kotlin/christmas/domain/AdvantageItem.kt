package christmas.domain

data class Advantage(
    val dDayDiscount: Int = 0,
    val weekdayDiscount: Int = 0,
    val weekendDiscount: Int = 0,
    val exclusiveDiscount: Int = 0,
    val presentationEventAmount: Int = 0
)
