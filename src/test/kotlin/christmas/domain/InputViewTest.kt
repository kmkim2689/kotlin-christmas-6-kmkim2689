package christmas.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InputViewTest {
    @Test
    fun `유효한 문자열 형태의 날짜가 입력될 경우 정수 형태로 정상적으로 반환`() {
        val inputList = listOf("1", "2", "3", "30", "31")
        val expectedValues = listOf(1, 2, 3, 30, 31)
        inputList.zip(expectedValues) { inputText, expectedValue ->
            val actualValue = InputView.getValidatedNumberOfDay(inputText)
            assertThat(actualValue).isEqualTo(expectedValue)
        }
    }

    @Test
    fun `문자열 형태의 날짜가 1부터 31 사이의 정수가 아닌 경우 예외 발생`() {
        val input = "32"
        assertThrows<IllegalArgumentException> {
            val actualValue = InputView.getValidatedNumberOfDay(input)
        }
    }

    @Test
    fun `문자열 형태의 날짜가 정수로 변환할 수 없는 경우 예외 발생`() {
        val input = "23일"
        assertThrows<IllegalArgumentException> {
            val actualValue = InputView.getValidatedNumberOfDay(input)
        }
    }

    @Test
    fun `문자열 형태의 날짜가 소수인 경우 예외 발생`() {
        val input = "2.3"
        assertThrows<IllegalArgumentException> {
            val actualValue = InputView.getValidatedNumberOfDay(input)
        }
    }

    @Test
    fun `문자열 형태의 날짜가 음수인 경우 예외 발생`() {
        val input = "-20"
        assertThrows<IllegalArgumentException> {
            val actualValue = InputView.getValidatedNumberOfDay(input)
        }
    }
}