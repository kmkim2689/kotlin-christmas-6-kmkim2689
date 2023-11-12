package christmas.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AdvantageManagerTest {

    private lateinit var advantageManager: AdvantageManager

    @BeforeEach
    fun setUp() {

    }

    @Test
    fun `할인 전 주문 금액이 120,000원 이상일 때 샴페인 증정`() {
        val totalPrice = 120000
        val reservationInfo = ReservationInfo(
            dayOfReservation = 26,
            items = listOf(
                OrderItem(Menu.SEAFOOD_PASTA, 2),
                OrderItem(Menu.CHRISTMAS_PASTA, 2),
            )
        )
        advantageManager = AdvantageManager(totalPrice, reservationInfo)

        val actualValue = advantageManager.getFreeChampagneOrNot()
        val expectedValue = true
        assertThat(actualValue).isEqualto(expectedValue)
    }

    @Test
    fun `할인 전 주문 금액이 120,000원 미만일 때 샴페인 미증정`() {
        val totalPrice = 118000
        val reservationInfo = ReservationInfo(
            dayOfReservation = 26,
            items = listOf(
                OrderItem(Menu.TBONE_STEAK, 2),
                OrderItem(Menu.CAESAR_SALAD, 1)
            )
        )
        advantageManager = AdvantageManager(totalPrice, reservationInfo)

        val actualValue = advantageManager.getFreeChampagneOrNot()
        val expectedValue = false
        assertThat(actualValue).isEqualto(expectedValue)
    }
}