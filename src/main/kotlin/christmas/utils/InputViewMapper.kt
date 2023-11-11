package christmas.utils

import christmas.constants.ExceptionMessages.EXCEPTION_DAY_INPUT_RANGE
import christmas.constants.ExceptionMessages.EXCEPTION_DAY_INPUT_STRING

fun String.toDayNumberOrThrowIllegalArgumentException() = try {
    val dayNumber = this.toInt()
    if (dayNumber !in 1..31) throw IllegalArgumentException(EXCEPTION_DAY_INPUT_RANGE)
    dayNumber
} catch (e: NumberFormatException) {
    throw IllegalArgumentException(EXCEPTION_DAY_INPUT_STRING)
}