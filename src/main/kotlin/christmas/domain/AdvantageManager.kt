package christmas.domain

import christmas.constants.Constants.ADVANTAGE_CRITERION_PRICE
import christmas.constants.Constants.CATEGORY_DESSERT
import christmas.constants.Constants.CATEGORY_MAIN
import christmas.constants.Constants.CHAMPAGNE_PRESENTATION
import christmas.constants.Constants.CHAMPAGNE_PRESENTATION_PRICE
import christmas.constants.Constants.DISCOUNT_DDAY_DEFAULT
import christmas.constants.Constants.DISCOUNT_DDAY_PER_DAY
import christmas.constants.Constants.NONE
import christmas.constants.Constants.PRICE_CHAMPAGNE

class AdvantageManager(
    private val totalPrice: Int,
    private val reservationInfo: ReservationInfo
) {

    private val dayOfReservation = reservationInfo.dayOfReservation

    fun getPresentationResult() = when (getFreeChampagneOrNot()) {
        true -> CHAMPAGNE_PRESENTATION
        false -> NONE
    }

    fun getPresentationEventAmount() = when (getFreeChampagneOrNot()) {
        true -> AdvantageItem("증정 이벤트", PRICE_CHAMPAGNE)
        false -> AdvantageItem("증정 이벤트")
    }

    fun getFreeChampagneOrNot() = totalPrice >= CHAMPAGNE_PRESENTATION_PRICE

    fun getDdayDiscount(): AdvantageItem {
        if (dayOfReservation in 1..25) {
            return AdvantageItem(
                "크리스마스 디데이 할인",
                DISCOUNT_DDAY_PER_DAY * (dayOfReservation - 1) + DISCOUNT_DDAY_DEFAULT
            )
        }
        return AdvantageItem("크리스마스 디데이 할인")
    }

    fun getExclusiveDiscount(): AdvantageItem {
        if (dayOfReservation % 7 == 3 || dayOfReservation == 25) {
            return AdvantageItem("특별 할인", 1000)
        }
        return AdvantageItem("특별 할인")
    }

    fun getWeekdayDiscount(): AdvantageItem {
        if (dayOfReservation % 7 !in 1..2) {
            return AdvantageItem("평일 할인", getWeekdayDiscountPrice())
        }
        return AdvantageItem("평일 할인")
    }

    private fun getWeekdayDiscountPrice(): Int {
        var discount = 0
        reservationInfo.items.onEach {
            if (it.menu.category.categoryName == CATEGORY_DESSERT) discount += it.amount * 2023
        }

        return discount
    }
}