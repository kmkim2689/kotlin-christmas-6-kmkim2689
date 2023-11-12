package christmas.controller

import christmas.controller.ReservationController.toDecimalFormat
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReservationControllerTest {

    @Test
    fun `정수 형태의 가격을 1,000 단위의 정규 표현식 형태로 변환하는 기능 테스트`() {
        val price = 10000
        val actualValue = price.toDecimalFormat()
        val expectedValue = "10,000원"

        assertThat(actualValue).isEqualTo(expectedValue)
    }
}