package christmas.domain

import christmas.constants.Constants.MENU_BARBECUE_LIBS
import christmas.constants.Constants.MENU_CAESAR_SALAD
import christmas.constants.Constants.MENU_CHAMPAGNE
import christmas.constants.Constants.MENU_CHOCOLATE_CAKE
import christmas.constants.Constants.MENU_CHRISTMAS_PASTA
import christmas.constants.Constants.MENU_ICE_CREAM
import christmas.constants.Constants.MENU_MUSHROOM_SOUP
import christmas.constants.Constants.MENU_RED_WINE
import christmas.constants.Constants.MENU_SEAFOOD_PASTA
import christmas.constants.Constants.MENU_TAPAS
import christmas.constants.Constants.MENU_TBONE_STEAK
import christmas.constants.Constants.MENU_ZERO_COKE
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
    val menuName: String,
    val category: Category,
    val price: Int
) {
    MUSHROOM_SOUP(MENU_MUSHROOM_SOUP, Category.APPETIZER, PRICE_MUSHROOM_SOUP),
    TAPAS(MENU_TAPAS, Category.APPETIZER, PRICE_TAPAS),
    CAESAR_SALAD(MENU_CAESAR_SALAD, Category.APPETIZER, PRICE_CAESAR_SALAD),

    TBONE_STEAK(MENU_TBONE_STEAK, Category.MAIN, PRICE_TBONE_STEAK),
    BARBECUE_LIBS(MENU_BARBECUE_LIBS, Category.MAIN, PRICE_BARBECUE_LIBS),
    SEAFOOD_PASTA(MENU_SEAFOOD_PASTA, Category.MAIN, PRICE_SEAFOOD_PASTA),
    CHRISTMAS_PASTA(MENU_CHRISTMAS_PASTA, Category.MAIN, PRICE_CHRISTMAS_PASTA),

    CHOCOLATE_CAKE(MENU_CHOCOLATE_CAKE, Category.DESSERT, PRICE_CHOCOLATE_CAKE),
    ICE_CREAM(MENU_ICE_CREAM, Category.DESSERT, PRICE_ICE_CREAM),

    ZERO_COKE(MENU_ZERO_COKE, Category.BEVERAGE, PRICE_ZERO_COKE),
    RED_WINE(MENU_RED_WINE, Category.BEVERAGE, PRICE_RED_WINE),
    CHAMPAGNE(MENU_CHAMPAGNE, Category.BEVERAGE, PRICE_CHAMPAGNE);

    companion object {
        fun getMenuByName(name: String): Menu? {
            return entries.find { it.menuName == name }
        }
    }
}