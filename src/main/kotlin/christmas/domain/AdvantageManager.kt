package christmas.domain

import christmas.constants.Constants.ADVANTAGE_DISCOUNT_CHRISTMAS_DDAY
import christmas.constants.Constants.ADVANTAGE_CRITERION_PRICE
import christmas.constants.Constants.ADVANTAGE_DISCOUNT_EXCLUSIVE
import christmas.constants.Constants.ADVANTAGE_DISCOUNT_WEEKDAY
import christmas.constants.Constants.ADVANTAGE_DISCOUNT_WEEKEND
import christmas.constants.Constants.ADVANTAGE_PRESENTATION
import christmas.constants.Constants.BADGE_SANTA_TOTAL_ADVANTAGE
import christmas.constants.Constants.BADGE_STAR_TOTAL_ADVANTAGE
import christmas.constants.Constants.BADGE_TREE_TOTAL_ADVANTAGE
import christmas.constants.Constants.CATEGORY_DESSERT
import christmas.constants.Constants.CATEGORY_DISCOUNT_PRICE
import christmas.constants.Constants.CATEGORY_MAIN
import christmas.constants.Constants.CHAMPAGNE_PRESENTATION
import christmas.constants.Constants.CHAMPAGNE_PRESENTATION_PRICE
import christmas.constants.Constants.DAYS_PER_WEEK
import christmas.constants.Constants.DAY_CHRISTMAS
import christmas.constants.Constants.DAY_MOD_EXCLUSIVE
import christmas.constants.Constants.DAY_MOD_WEEKEND_END
import christmas.constants.Constants.DAY_MOD_WEEKEND_START
import christmas.constants.Constants.DISCOUNT_DDAY_DEFAULT
import christmas.constants.Constants.DISCOUNT_DDAY_PER_DAY
import christmas.constants.Constants.DISCOUNT_EXCLUSIVE
import christmas.constants.Constants.NONE
import christmas.constants.Constants.PRICE_CHAMPAGNE

class AdvantageManager(
    private val totalPrice: Int,
    private val reservationInfo: ReservationInfo
) {

    private val dayOfReservation = reservationInfo.dayOfReservation
    private val getFreeChampagneOrNot = totalPrice >= CHAMPAGNE_PRESENTATION_PRICE

    fun getCalculatedTotalAdvantages(): List<AdvantageItem> = mutableListOf(
        getDdayDiscount(),
        getWeekdayDiscount(),
        getWeekendDiscount(),
        getExclusiveDiscount(),
        getPresentationEventAmount()
    ).filter { it.advantageAmount > 0 }

    fun getPresentationEventAmount() = when (getFreeChampagneOrNot) {
        true -> AdvantageItem(ADVANTAGE_PRESENTATION, PRICE_CHAMPAGNE)
        false -> AdvantageItem(ADVANTAGE_PRESENTATION)
    }

    fun getDdayDiscount(): AdvantageItem {
        if (dayOfReservation in 1..DAY_CHRISTMAS) {
            return AdvantageItem(
                ADVANTAGE_DISCOUNT_CHRISTMAS_DDAY,
                DISCOUNT_DDAY_PER_DAY * (dayOfReservation - 1) + DISCOUNT_DDAY_DEFAULT
            )
        }
        return AdvantageItem(ADVANTAGE_DISCOUNT_CHRISTMAS_DDAY)
    }

    fun getExclusiveDiscount(): AdvantageItem {
        if (dayOfReservation % DAYS_PER_WEEK == DAY_MOD_EXCLUSIVE || dayOfReservation == DAY_CHRISTMAS) {
            return AdvantageItem(ADVANTAGE_DISCOUNT_EXCLUSIVE, DISCOUNT_EXCLUSIVE)
        }
        return AdvantageItem(ADVANTAGE_DISCOUNT_EXCLUSIVE)
    }

    fun getWeekdayDiscount(): AdvantageItem {
        if (dayOfReservation % DAYS_PER_WEEK !in DAY_MOD_WEEKEND_START..DAY_MOD_WEEKEND_END) {
            return AdvantageItem(ADVANTAGE_DISCOUNT_WEEKDAY, getWeekdayDiscountPrice())
        }
        return AdvantageItem(ADVANTAGE_DISCOUNT_WEEKDAY)
    }

    private fun getWeekdayDiscountPrice(): Int {
        var discount = 0
        reservationInfo.items.onEach {
            if (it.menu.category.categoryName == CATEGORY_DESSERT) {
                discount += it.amount * CATEGORY_DISCOUNT_PRICE
            }
        }
        return discount
    }

    fun getWeekendDiscount(): AdvantageItem {
        if (dayOfReservation % DAYS_PER_WEEK in DAY_MOD_WEEKEND_START..DAY_MOD_WEEKEND_END) {
            return AdvantageItem(ADVANTAGE_DISCOUNT_WEEKEND, getWeekendDiscountPrice())
        }
        return AdvantageItem(ADVANTAGE_DISCOUNT_WEEKEND)
    }

    private fun getWeekendDiscountPrice(): Int {
        var discount = 0
        reservationInfo.items.onEach {
            if (it.menu.category.categoryName == CATEGORY_MAIN) {
                discount += it.amount * CATEGORY_DISCOUNT_PRICE
            }
        }
        return discount
    }
}