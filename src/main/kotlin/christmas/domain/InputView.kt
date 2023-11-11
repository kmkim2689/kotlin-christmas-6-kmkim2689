package christmas.domain

import camp.nextstep.edu.missionutils.Console
import christmas.constants.ExceptionMessages.ERROR
import christmas.constants.ExceptionMessages.EXCEPTION_UNEXPECTED
import christmas.constants.StepMessages.STEP_INPUT_DAY_OF_RESERVATION
import christmas.utils.toDayNumberOrThrowIllegalArgumentException

object InputView {

    fun getDayOfReservation(): Int {
        return getValidatedNumberOfDay()
    }

    private fun getValidatedNumberOfDay(): Int = try {
        println(STEP_INPUT_DAY_OF_RESERVATION)
        val numberOfDay = Console.readLine()
        numberOfDay.toDayNumberOrThrowIllegalArgumentException()
    } catch (e: IllegalArgumentException) {
        println(getErrorMessage(e.message))
        getDayOfReservation()
    }

    private fun getErrorMessage(message: String? = EXCEPTION_UNEXPECTED) = "$ERROR $message"

}