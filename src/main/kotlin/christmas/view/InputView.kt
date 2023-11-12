package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.constants.Constants.SEPARATOR_ORDER_ITEM
import christmas.constants.ExceptionMessages.ERROR
import christmas.constants.ExceptionMessages.EXCEPTION_UNEXPECTED
import christmas.constants.StepMessages.STEP_INPUT_DAY_OF_RESERVATION
import christmas.constants.StepMessages.STEP_INPUT_ORDER
import christmas.domain.OrderItem
import christmas.utils.InputViewValidator
import christmas.utils.toDayNumberOrThrowIllegalArgumentException
import christmas.utils.toOrderItemOrThrowIllegalArgumentException
import christmas.utils.toOrderListOrThrowIllegalArgumentException

object InputView {

    fun getDayOfReservation(): Int {
        return getValidatedNumberOfDay()
    }

    fun getOrderList(): List<OrderItem> {
        return getValidatedOrderList()
    }

    private fun getValidatedNumberOfDay(): Int = try {
        println(STEP_INPUT_DAY_OF_RESERVATION)
        val numberOfDay = Console.readLine().trim()
        numberOfDay.toDayNumberOrThrowIllegalArgumentException()
    } catch (e: IllegalArgumentException) {
        println(getErrorMessage(e.message))
        getValidatedNumberOfDay()
    }

    private fun getValidatedOrderList(): List<OrderItem> = try {
        println(STEP_INPUT_ORDER)

        val order = Console.readLine().trim()
            .toOrderListOrThrowIllegalArgumentException()
        val orderItems = order.map {
            it.toOrderItemOrThrowIllegalArgumentException()
        }

        InputViewValidator.validateOrders(orderItems)

        orderItems
    } catch (e: IllegalArgumentException) {
        println(getErrorMessage(e.message))
        getValidatedOrderList()
    }

    private fun getErrorMessage(message: String? = EXCEPTION_UNEXPECTED) = "$ERROR $message"

}