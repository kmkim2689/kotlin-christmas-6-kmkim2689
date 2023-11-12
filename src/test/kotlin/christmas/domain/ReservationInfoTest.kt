package christmas.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReservationInfoTest {

    @Test
    fun `예약 정보를 통해 할인 이전 총 주문 가격을 문자열 형태로 출력`() {
        val dayOfReservation = 31
        val items = listOf(
            OrderItem(Menu.TBONE_STEAK, 2),
            OrderItem(Menu.CHOCOLATE_CAKE, 2)
        )
        val reservationInfo = ReservationInfo(dayOfReservation, items)

        val actualResult = reservationInfo.getTotalPriceBeforeDiscounts()
        val expectedResult = "${55000 * 2 + 15000 * 2}"

        assertThat(actualResult).isEqualTo(expectedResult)
    }
}