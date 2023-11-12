package christmas.utils

import christmas.domain.Menu
import christmas.domain.OrderItem
import christmas.view.InputView
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class InputViewValidatorTest {

    fun `주문 메뉴 정상적인 형태로 입력 시 정상 반환`() {
        assertDoesNotThrow {
            val input = listOf(
                OrderItem(Menu.MUSHROOM_SOUP, 1),
                OrderItem(Menu.CHOCOLATE_CAKE, 2),
                OrderItem(Menu.TBONE_STEAK, 3)
            )
            InputViewValidator.validateOrders(input)
        }
    }

    @Test
    fun `주문 메뉴 입력 시 유효하지 않은 숫자를 입력한 경우 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            val input = listOf(
                OrderItem(Menu.MUSHROOM_SOUP, 0),
                OrderItem(Menu.CHOCOLATE_CAKE, 2),
                OrderItem(Menu.TBONE_STEAK, 3)
            )

            InputViewValidator.validateOrders(input)
        }
    }

    @Test
    fun `주문 메뉴 입력 시 중복된 메뉴 입력 시 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            val input = listOf(
                OrderItem(Menu.MUSHROOM_SOUP, 1),
                OrderItem(Menu.MUSHROOM_SOUP, 2),
                OrderItem(Menu.TBONE_STEAK, 3)
            )

            InputViewValidator.validateOrders(input)
        }
    }

    @Test
    fun `주문 메뉴 입력 시 총 개수가 20개를 넘어가는 경우 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            val input = listOf(
                OrderItem(Menu.MUSHROOM_SOUP, 18),
                OrderItem(Menu.MUSHROOM_SOUP, 2),
                OrderItem(Menu.TBONE_STEAK, 3)
            )

            InputViewValidator.validateOrders(input)
        }
    }


}