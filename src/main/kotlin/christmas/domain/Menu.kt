package christmas.domain

import christmas.constants.Constants.PRICE_BARBECUE_LIBS
import christmas.constants.Constants.PRICE_CAESAR_SALAD
import christmas.constants.Constants.PRICE_CHAMPAGNE
import christmas.constants.Constants.PRICE_CHOCOLATE_CAKE
import christmas.constants.Constants.PRICE_CHRISTMAS_PASTA
import christmas.constants.Constants.PRICE_ICE_CREAM
import christmas.constants.Constants.PRICE_MUSHROOM_SOUP
import christmas.constants.Constants.PRICE_RED_WINE
import christmas.constants.Constants.PRICE_SEAFOOD_PASTA
import christmas.constants.Constants.PRICE_TAPAS
import christmas.constants.Constants.PRICE_TBONE_STEAK
import christmas.constants.Constants.PRICE_ZERO_COKE

enum class Menu(
    val category: Category,
    val price: Int
) {
    MUSHROOM_SOUP(Category.APPETIZER, PRICE_MUSHROOM_SOUP),
    TAPAS(Category.APPETIZER, PRICE_TAPAS),
    CAESAR_SALAD(Category.APPETIZER, PRICE_CAESAR_SALAD),

    TBONE_STEAK(Category.MAIN, PRICE_TBONE_STEAK),
    BARBECUE_LIBS(Category.MAIN, PRICE_BARBECUE_LIBS),
    SEAFOOD_PASTA(Category.MAIN, PRICE_SEAFOOD_PASTA),
    CHRISTMAS_PASTA(Category.MAIN, PRICE_CHRISTMAS_PASTA),

    CHOCOLATE_CAKE(Category.DESSERT, PRICE_CHOCOLATE_CAKE),
    ICE_CREAM(Category.DESSERT, PRICE_ICE_CREAM),

    ZERO_COKE(Category.BEVERAGE, PRICE_ZERO_COKE),
    RED_WINE(Category.BEVERAGE, PRICE_RED_WINE),
    CHAMPAGNE(Category.BEVERAGE, PRICE_CHAMPAGNE)
}