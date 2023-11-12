package christmas.domain

import christmas.constants.Constants.CHAMPAGNE_PRESENTATION
import christmas.constants.Constants.CHAMPAGNE_PRESENTATION_PRICE
import christmas.constants.Constants.NONE

class AdvantageManager(
    private val totalPrice: Int,
    private val reservationInfo: ReservationInfo
) {

    fun getPresentationResult() = when (getFreeChampagneOrNot()) {
        true -> CHAMPAGNE_PRESENTATION
        false -> NONE
    }

    fun getFreeChampagneOrNot() = totalPrice >= CHAMPAGNE_PRESENTATION_PRICE
}