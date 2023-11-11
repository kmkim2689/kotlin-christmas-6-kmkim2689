package christmas.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InputViewMapperTest {

    @Test
    fun `문자열 형태로 입력받은 날이 1에서 31 사이일 경우 정상적으로 정수 형태 반환`() {
        val input = "31"
        val actualValue = input.toDayNumberOrThrowIllegalArgumentException()
        val expectedValue = 31

        assertThat(actualValue).isEqualTo(expectedValue)
    }

    @Test
    fun `문자열 형태로 입력받은 날이 1에서 31 사이가 아닌 정수일 경우 예외 발생`() {
        val input = "32"
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

}