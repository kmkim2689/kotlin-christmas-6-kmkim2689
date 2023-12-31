package christmas.utils

import christmas.domain.Menu
import christmas.domain.OrderItem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ReservationMapperTest {

    @Test
    fun `문자열 형태로 입력받은 날이 1에서 31 사이일 경우 정상적으로 정수 형태 반환`() {
        val inputList = listOf("1", "2", "3", "30", "31")
        val expectedValues = listOf(1, 2, 3, 30, 31)
        inputList.zip(expectedValues) { inputText, expectedValue ->
            val actualValue = inputText.toDayNumberOrThrowIllegalArgumentException()
            assertThat(actualValue).isEqualTo(expectedValue)
        }
    }

    @Test
    fun `문자열 형태로 입력받은 날이 1에서 31 사이가 아닌 정수일 경우 예외 발생`() {
        val input = "32"
        assertThrows<IllegalArgumentException> {
            val actualValue = input.toDayNumberOrThrowIllegalArgumentException()
        }
    }

    @Test
    fun `문자열 형태로 입력받은 날이 소수일 경우 예외 발생`() {
        val input = "3.2"
        assertThrows<IllegalArgumentException> {
            val actualValue = input.toDayNumberOrThrowIllegalArgumentException()
        }
    }

    @Test
    fun `문자열 형태로 입력받은 날이 음수일 경우 예외 발생`() {
        val input = "-1"
        assertThrows<IllegalArgumentException> {
            val actualValue = input.toDayNumberOrThrowIllegalArgumentException()
        }
    }

    @Test
    fun `문자열 형태로 입력받은 날이 정수로 변환할 수 없는 문자열인 경우 예외 발생`() {
        val input = "1요일"
        assertThrows<IllegalArgumentException> {
            val actualValue = input.toDayNumberOrThrowIllegalArgumentException()
        }
    }

    @Test
    fun `주문 메뉴 입력 시 정상적으로 입력된 경우 주문 리스트 반환`() {
        val input = listOf("양송이수프-2", "티본스테이크-1", "초코케이크-2")
        val expectedResult = listOf(
            OrderItem(Menu.MUSHROOM_SOUP, 2),
            OrderItem(Menu.TBONE_STEAK, 1),
            OrderItem(Menu.CHOCOLATE_CAKE, 2),
        )
        val actualResult = input.map {
            it.toOrderItemOrThrowIllegalArgumentException()
        }

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun `주문 메뉴 입력 시 -를 기준으로 메뉴명과 수량을 제대로 입력하지 않았을 때 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            val input = listOf("양송이수프+2", "티본스테이크-1", "초코케이크-2")
            val expectedResult = listOf(
                OrderItem(Menu.MUSHROOM_SOUP, 2),
                OrderItem(Menu.TBONE_STEAK, 1),
                OrderItem(Menu.CHOCOLATE_CAKE, 2),
            )
            input.map { it.toOrderItemOrThrowIllegalArgumentException() }
        }
    }

    @Test
    fun `주문 메뉴 입력 시 유효하지 않은 메뉴명을 입력했을 때 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            val input = listOf("양송이-2", "티본스테이크-1", "초코케이크-2")
            input.map { it.toOrderItemOrThrowIllegalArgumentException() }
        }
    }

    @Test
    fun `주문 메뉴 입력 시 주문 수량으로 문자열을 입력했을 때 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            val input = listOf("양송이-2개", "티본스테이크-1", "초코케이크-2")
            input.map { it.toOrderItemOrThrowIllegalArgumentException() }
        }
    }

    @Test
    fun `주문 메뉴 입력 시 주문 수량으로 소수를 입력했을 때 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            val input = listOf("양송이-2.5", "티본스테이크-1", "초코케이크-2")
            input.map { it.toOrderItemOrThrowIllegalArgumentException() }
        }
    }

    @Test
    fun `정수 형태의 가격을 1,000 단위의 정규 표현식 형태로 변환하는 기능 테스트`() {
        val price = 10000
        val actualValue = price.toDecimalFormatPrice()
        val expectedValue = "10,000원"

        assertThat(actualValue).isEqualTo(expectedValue)
    }

    @Test
    fun `정수 형태의 가격을 음수 형태의 할인가격으로 변환하는 기능 테스트`() {
        val price = 10000000
        val actualValue = price.toDecimalFormatAdvantagePrice()
        val expectedValue = "-10,000,000원"

        assertThat(actualValue).isEqualTo(expectedValue)
    }
}