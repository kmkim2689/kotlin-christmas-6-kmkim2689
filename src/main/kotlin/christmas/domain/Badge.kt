package christmas.domain

import christmas.constants.Constants.BADGE_NONE
import christmas.constants.Constants.BADGE_SANTA
import christmas.constants.Constants.BADGE_STAR
import christmas.constants.Constants.BADGE_TREE

enum class Badge(val badgeName: String) {
    SANTA(BADGE_SANTA),
    TREE(BADGE_TREE),
    STAR(BADGE_STAR),
    NONE(BADGE_NONE)
}