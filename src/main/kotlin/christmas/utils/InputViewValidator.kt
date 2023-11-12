package christmas.utils

import christmas.constants.ExceptionMessages.EXCEPTION_ORDER_INPUT
import christmas.domain.OrderItem

object InputViewValidator {

    fun validateOrders(orders: List<OrderItem>) {
        orders.onEach {
            if (it.amount <= 0 || it.amount > 20) throwIllegalArgumentException()
        }

        val uniqueMenuCount = orders.map { it.menu }.toSet().size
        if (uniqueMenuCount != orders.size) throwIllegalArgumentException()

        val totalAmount = orders.sumOf { it.amount }
        if (totalAmount > 20) throwIllegalArgumentException()
    }

    private fun throwIllegalArgumentException() {
        throw IllegalArgumentException(EXCEPTION_ORDER_INPUT)
    }
}