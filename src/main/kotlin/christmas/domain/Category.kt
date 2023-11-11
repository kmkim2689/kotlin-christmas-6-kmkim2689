package christmas.domain

import christmas.constants.Constants.CATEGORY_APPETIZER
import christmas.constants.Constants.CATEGORY_BEVERAGE
import christmas.constants.Constants.CATEGORY_DESSERT
import christmas.constants.Constants.CATEGORY_DISCOUNT_PRICE
import christmas.constants.Constants.CATEGORY_MAIN

enum class Category(
    val categoryName: String,
    val weekdayDiscountAmount: Int,
    val weekendDiscountAmount: Int
) {
    APPETIZER(CATEGORY_APPETIZER, 0, 0),
    MAIN(CATEGORY_MAIN, 0, CATEGORY_DISCOUNT_PRICE),
    DESSERT(CATEGORY_DESSERT, CATEGORY_DISCOUNT_PRICE, 0),
    BEVERAGE(CATEGORY_BEVERAGE, 0, 0)
}