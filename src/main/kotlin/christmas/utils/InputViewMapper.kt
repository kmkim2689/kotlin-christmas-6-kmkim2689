package christmas.utils

import christmas.constants.ExceptionMessages.EXCEPTION_DAY_INPUT_STRING

fun String.toDayNumber() = try {
    this.toInt()
} catch (e: NumberFormatException) {
    throw IllegalArgumentException(EXCEPTION_DAY_INPUT_STRING)
}