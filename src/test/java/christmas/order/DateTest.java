package christmas.order;

import christmas.domain.order.Date;
import christmas.view.input.InputUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class DateTest {

    @Nested
    @DisplayName("[parser] 입력 값 파서 테스트")
    class inputParserTest {

        @Test
        @DisplayName("[SUCCESS] 숫자를 입력받은 객체 생성")
        void should_success_when_createInteger() {
            String input = "30";

            assertThat(InputUtil.parseStringToInt(input))
                    .isEqualTo(30);
        }

        @Test
        @DisplayName("[EXCEPTION] 숫자가 아닐 경우 예외 발생")
        void should_throwException_when_isNotNumeric() {
            String input = "abc";

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> InputUtil.parseStringToInt(input))
                    .withMessageContaining("숫자를 입력해 주세요");
        }
    }

    @Nested
    @DisplayName("[new] 생성자 테스트")
    class newTest {

        @Test
        @DisplayName("[SUCCESS] 날짜 일수를 가진 Date 객체 생성")
        void should_success_when_createDate() {
            String input = "30";

            int dateInput = InputUtil.parseStringToInt(input);

            assertThat(Date.of(dateInput))
                    .isInstanceOf(Date.class);
        }

        @Test
        @DisplayName("[EXCEPTION] 1일부터 31일 사이의 숫자가 아닐 경우 예외 발생")
        void should_throwException_when_isNotBetween1And31() {
            String input = "40";

            int dateInput = InputUtil.parseStringToInt(input);

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> Date.of(dateInput))
                    .withMessageContaining("유효한 날짜 형식이 아닙니다.");
        }
    }
}
