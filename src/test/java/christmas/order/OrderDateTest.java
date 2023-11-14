package christmas.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import christmas.domain.order.OrderDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderDateTest {

    @Nested
    @DisplayName("[parser] 입력 값 파서 테스트")
    class inputParserTest {

        @Test
        @DisplayName("[EXCEPTION] 숫자가 아닐 경우 예외 발생")
        void should_throwException_when_isNotNumeric() {
            String input = "abc";

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> OrderDate.of(input));
        }
    }

    @Nested
    @DisplayName("[new] 생성자 테스트")
    class newTest {

        @Test
        @DisplayName("[SUCCESS] 날짜 일수를 가진 Date 객체 생성")
        void should_success_when_createDate() {
            String input = "30";

            assertThat(OrderDate.of(input))
                    .isInstanceOf(OrderDate.class);
        }

        @ParameterizedTest
        @ValueSource(strings = {"0", "40", "32"})
        @DisplayName("[EXCEPTION] 1일부터 31일 사이의 숫자가 아닐 경우 예외 발생")
        void should_throwException_when_isNotBetween1And31(String input) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> OrderDate.of(input));
        }
    }

    @Nested
    @DisplayName("[utility] 유틸 메서드 테스트")
    class utilityTest {

        public static Stream<Arguments> provideOrderDateforCorrectChristmasDDayDiscountPrice() {
            return Stream.of(
                    Arguments.of("1", 1_000L),
                    Arguments.of("2", 1_100L),
                    Arguments.of("10", 1_900L)
            );
        }

        @ParameterizedTest
        @ValueSource(strings = {"26", "27", "31"})
        @DisplayName("[SUCCESS] 크리스마스 디데이 날짜인 1일부터 25일 사이가 아닐 경우 실패한다")
        void should_failure_when_isNotBetween1And25(String input) {
            OrderDate orderDate = OrderDate.of(input);

            assertThat(orderDate.isContainChristmasDDay())
                    .isFalse();
        }

        @ParameterizedTest
        @MethodSource("provideOrderDateforCorrectChristmasDDayDiscountPrice")
        @DisplayName("[SUCCESS] 크리스마스 디데이 할인 가격을 날짜마다 맞게 계산했다면 성공한다")
        void should_success_when_calculateDDayDiscountPriceCorrectly(String input, long result) {
            OrderDate orderDate = OrderDate.of(input);

            assertThat(orderDate.calculateChristmasDDayDiscount())
                    .isEqualTo(result);
        }

        @ParameterizedTest
        @ValueSource(strings = {"1", "2", "8", "23"})
        @DisplayName("[SUCCESS] 주문 날짜가 주말인 경우 성공한다")
        void should_success_when_IsWeekend(String input) {
            OrderDate orderDate = OrderDate.of(input);

            assertThat(orderDate.isWeekend())
                    .isTrue();
        }

        @ParameterizedTest
        @ValueSource(strings = {"3", "17", "31"})
        @DisplayName("[SUCCESS] 주문 날짜가 주중인 경우 성공한다")
        void should_success_when_IsWeekday(String input) {
            OrderDate orderDate = OrderDate.of(input);

            assertThat(orderDate.isWeekday())
                    .isTrue();
        }

        @ParameterizedTest
        @ValueSource(strings = {"3", "17", "25"})
        @DisplayName("[SUCCESS] 주문 날짜가 별이 포함된 날짜일 경우 성공한다")
        void should_success_when_IsStarDay(String input) {
            OrderDate orderDate = OrderDate.of(input);

            assertThat(orderDate.isStarDay())
                    .isTrue();
        }
    }
}
