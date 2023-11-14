package christmas.domain

import christmas.constants.Constants

class ResultCalculator(
    private val totalPrice: Int,
    private val totalAdvantages: List<AdvantageItem>
) {

    private val totalAdvantagePrice = getTotalAdvantagePrice()
    private val getFreeChampagneOrNot = totalPrice >= Constants.CHAMPAGNE_PRESENTATION_PRICE

    fun getTotalAdvantages(): List<AdvantageItem> {
        if (totalPrice < Constants.ADVANTAGE_CRITERION_PRICE) return emptyList()
        return totalAdvantages
    }

    fun getTotalAdvantagePrice(): Int {
        return totalAdvantages.sumOf {
            it.advantageAmount
        }
    }

    fun getTotalPriceAfterDiscount(): Int {
        return totalPrice - totalAdvantages.sumOf {
            getAdvantageAmount(it)
        }
    }

    private fun getAdvantageAmount(advantageItem: AdvantageItem): Int {
        if (advantageItem.advantageName != Constants.ADVANTAGE_PRESENTATION) {
            return advantageItem.advantageAmount
        }

        return 0
    }

    fun getBadge(): Badge = when {
        totalAdvantagePrice >= Constants.BADGE_SANTA_TOTAL_ADVANTAGE -> Badge.SANTA
        totalAdvantagePrice >= Constants.BADGE_TREE_TOTAL_ADVANTAGE -> Badge.TREE
        totalAdvantagePrice >= Constants.BADGE_STAR_TOTAL_ADVANTAGE -> Badge.STAR
        else -> Badge.NONE
    }

    fun getPresentationResult() = when (getFreeChampagneOrNot) {
        true -> Constants.CHAMPAGNE_PRESENTATION
        false -> Constants.NONE
    }
}