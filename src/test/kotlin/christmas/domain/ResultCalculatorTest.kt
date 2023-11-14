package christmas.domain

import christmas.constants.Constants
import christmas.constants.Constants.NONE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultCalculatorTest {

    private lateinit var resultCalculator: ResultCalculator

    @Test
    fun `주문 금액이 10000원 이상인 경우 혜택 반환`() {
        val totalPrice = 10069
        val totalAdvantages = listOf(
            AdvantageItem("평일 할인", 8092),
            AdvantageItem("특별 할인", 1000)
        )
        resultCalculator = ResultCalculator(totalPrice, totalAdvantages)
        val actualValue = resultCalculator.getTotalAdvantages()
        assertThat(actualValue).isEqualTo(
            listOf(
                AdvantageItem("평일 할인", 8092),
                AdvantageItem("특별 할인", 1000)
            )
        )
    }

    @Test
    fun `주문 금액이 10000원 미만인 경우 헤택 빈 리스트 반환`() {
        val totalPrice = 9000
        val totalAdvantages = listOf(
            AdvantageItem("크리스마스 디데이 할인", 1900),
            AdvantageItem("평일 할인", 4046),
            AdvantageItem("특별 할인", 1000)
        )
        resultCalculator = ResultCalculator(totalPrice, totalAdvantages)
        val actualValue = resultCalculator.getTotalAdvantages()
        assertThat(actualValue).isEqualTo(emptyList<AdvantageItem>())
    }

    @Test
    fun `할인 전 주문 금액이 120,000원 이상일 때 샴페인 증정`() {
        val totalPrice = 120000
        val totalAdvantages = listOf(
            AdvantageItem("크리스마스 디데이 할인", 1900),
            AdvantageItem("평일 할인", 4046),
            AdvantageItem("특별 할인", 1000)
        )
        resultCalculator = ResultCalculator(totalPrice, totalAdvantages)

        val actualValue = resultCalculator.getPresentationResult()
        assertThat(actualValue).isEqualTo(Constants.CHAMPAGNE_PRESENTATION)
    }

    @Test
    fun `할인 전 주문 금액이 120,000원 미만일 때 샴페인 미증정`() {
        val totalPrice = 118000
        val totalAdvantages = listOf(
            AdvantageItem("크리스마스 디데이 할인", 1900),
            AdvantageItem("평일 할인", 4046),
            AdvantageItem("특별 할인", 1000)
        )
        resultCalculator = ResultCalculator(totalPrice, totalAdvantages)

        val actualValue = resultCalculator.getPresentationResult()
        assertThat(actualValue).isEqualTo(NONE)
    }

    @Test
    fun `총 혜택 금액이 20000원 이상일 때 산타 뱃지 반환`() {
        val totalPrice = 50000
        val totalAdvantages = listOf(
            AdvantageItem("크리스마스 디데이 할인", 1900),
            AdvantageItem("평일 할인", 18207),
            AdvantageItem("특별 할인", 1000)
        )
        resultCalculator = ResultCalculator(totalPrice, totalAdvantages)
        val actualValue = resultCalculator.getBadge()
        assertThat(actualValue).isEqualTo(Badge.SANTA)
    }

    @Test
    fun `총 혜택 금액이 10000원 이상일 때 트리 뱃지 반환`() {
        val totalPrice = 15000
        val totalAdvantages = listOf(
            AdvantageItem("크리스마스 디데이 할인", 3000),
            AdvantageItem("평일 할인", 6069),
            AdvantageItem("특별 할인", 1000)
        )
        resultCalculator = ResultCalculator(totalPrice, totalAdvantages)
        val actualValue = resultCalculator.getBadge()
        assertThat(actualValue).isEqualTo(Badge.TREE)
    }

    @Test
    fun `총 혜택 금액이 5000원 이상일 때 별 뱃지 반환`() {
        val totalPrice = 15000
        val totalAdvantages = listOf(
            AdvantageItem("크리스마스 디데이 할인", 3000),
            AdvantageItem("평일 할인", 2023),
            AdvantageItem("특별 할인", 1000)
        )
        resultCalculator = ResultCalculator(totalPrice, totalAdvantages)
        val actualValue = resultCalculator.getBadge()
        assertThat(actualValue).isEqualTo(Badge.STAR)
    }

    @Test
    fun `총 혜택 금액이 5000원 미만일 때 뱃지가 없음을 반환`() {
        val totalPrice = 12000
        val totalAdvantages = listOf(
            AdvantageItem("크리스마스 디데이 할인", 3000),
            AdvantageItem("특별 할인", 1000)
        )
        resultCalculator = ResultCalculator(totalPrice, totalAdvantages)
        val actualValue = resultCalculator.getBadge()
        assertThat(actualValue).isEqualTo(Badge.NONE)
    }
}