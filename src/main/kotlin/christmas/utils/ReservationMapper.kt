package christmas.utils

import christmas.constants.Constants
import christmas.constants.Constants.NONE
import christmas.constants.Constants.SEPARATOR_NAME_AMOUNT
import christmas.constants.Constants.SEPARATOR_ORDER_ITEM
import christmas.constants.ExceptionMessages.EXCEPTION_DAY_INPUT
import christmas.constants.ExceptionMessages.EXCEPTION_ORDER_INPUT
import christmas.domain.Menu.Companion.getMenuByName
import christmas.domain.OrderItem
import java.text.DecimalFormat

fun String.toDayNumberOrThrowIllegalArgumentException() = try {
    val dayNumber = this.toInt()
    if (dayNumber !in 1..31) throw IllegalArgumentException(EXCEPTION_DAY_INPUT)
    dayNumber
} catch (e: NumberFormatException) {
    throw IllegalArgumentException(EXCEPTION_DAY_INPUT)
}

fun String.toOrderListOrThrowIllegalArgumentException() = this.split(SEPARATOR_ORDER_ITEM)

fun String.toOrderItemOrThrowIllegalArgumentException() = try {
    val orderInfo = this.split(SEPARATOR_NAME_AMOUNT)
    if (orderInfo.size != 2) {
        throw IllegalArgumentException(EXCEPTION_ORDER_INPUT)
    }

    OrderItem(getMenuByName(orderInfo[0])!!, orderInfo[1].toInt())
} catch (e: NullPointerException) {
    throw IllegalArgumentException(EXCEPTION_ORDER_INPUT)
} catch (e: NumberFormatException) {
    throw IllegalArgumentException(EXCEPTION_ORDER_INPUT)
}

fun Int.toDecimalFormatPrice(): String {
    val decimalFormat = DecimalFormat(Constants.PRICE_FORMAT)
    return decimalFormat.format(this)
}

fun Int.toDecimalFormatAdvantagePrice(): String {
    val decimalFormat = DecimalFormat("${Constants.SIGN_DISCOUNT}${Constants.PRICE_FORMAT}")
    if (this == 0) return NONE
    return decimalFormat.format(this)
}