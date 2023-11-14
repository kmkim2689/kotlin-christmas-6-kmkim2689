package christmas.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AdvantageManagerTest {

    private lateinit var advantageManager: AdvantageManager

    @Test
    fun `예약일이 1일에서 25일 사이이면서 총 주문 금액이 10000원 이상일 때 크리스마스 디데이 할인 적용`() {
        val totalPrice = 10000
        val reservationInfo = ReservationInfo(
            dayOfReservation = 10,
            items = listOf(
                OrderItem(Menu.ICE_CREAM, 2)
            )
        )
        advantageManager = AdvantageManager(totalPrice, reservationInfo)
        val actualValue = advantageManager.getDdayDiscount()
        assertThat(actualValue).isEqualTo(AdvantageItem("크리스마스 디데이 할인", 1900))
    }

    @Test
    fun `예약일을 7로 나누었을 때 나머지가 3이거나 예약일이 25일인 경우 주문 금액이 10000원 이상인 경우 특별 할인 적용`() {
        val totalPrice = 10000
        val reservationInfo = ReservationInfo(
            dayOfReservation = 10,
            items = listOf(
                OrderItem(Menu.ICE_CREAM, 2)
            )
        )
        advantageManager = AdvantageManager(totalPrice, reservationInfo)
        val actualValue = advantageManager.getExclusiveDiscount()
        assertThat(actualValue).isEqualTo(AdvantageItem("특별 할인", 1000))
    }

    @Test
    fun `예약일이 일요일에서 목요일 사이 디저트를 주문하여 총 주문 금액이 10000원 이상인 경우 평일 할인 적용`() {
        val totalPrice = 10000
        val reservationInfo = ReservationInfo(
            dayOfReservation = 10,
            items = listOf(
                OrderItem(Menu.ICE_CREAM, 2)
            )
        )
        advantageManager = AdvantageManager(totalPrice, reservationInfo)
        val actualValue = advantageManager.getWeekdayDiscount()
        assertThat(actualValue).isEqualTo(AdvantageItem("평일 할인", 4046))
    }

    @Test
    fun `예약일이 금요일에서 토요일 사이 메인메뉴를 주문하여 총 주문 금액이 10000원 이상인 경우 주말 할인 적용`() {
        val totalPrice = 110000
        val reservationInfo = ReservationInfo(
            dayOfReservation = 15,
            items = listOf(
                OrderItem(Menu.TBONE_STEAK, 2)
            )
        )
        advantageManager = AdvantageManager(totalPrice, reservationInfo)
        val actualValue = advantageManager.getWeekendDiscount()
        assertThat(actualValue).isEqualTo(AdvantageItem("주말 할인", 4046))
    }
}