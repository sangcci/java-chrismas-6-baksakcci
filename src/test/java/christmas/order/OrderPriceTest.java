package christmas.order;


import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderPrice;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class OrderPriceTest {

    @Nested
    @DisplayName("[new] 생성자 테스트")
    class newTest {

        public static Stream<Arguments> provideOrderMenuAndTotalPrice() {
            return Stream.of(
                    Arguments.of("티본스테이크-1,제로콜라-1", 58_000L),
                    Arguments.of("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", 142_000L),
                    Arguments.of("타파스-1,제로콜라-1", 8_500L)
            );
        }

        @ParameterizedTest
        @MethodSource("provideOrderMenuAndTotalPrice")
        @DisplayName("[SUCCESS] 메뉴와 갯수에 맞게 총 주문 금액을 계산 후 Price 객체 생성")
        void should_success_when_createPrice(String input, long totalOrderPrice) {
            OrderMenu orderMenu = OrderMenu.of(input);

            assertThat(OrderPrice.of(orderMenu))
                    .isInstanceOf(OrderPrice.class);
            assertThat(OrderPrice.of(orderMenu).getTotalPrice())
                    .isEqualTo(totalOrderPrice);
        }
    }
    @Nested
    @DisplayName("[utility] 유틸 메서드 테스트")
    class utilityTest {

        @Test
        @DisplayName("[SUCCESS] 총 주문 금액이 1만원이 넘지 못하면 실패한다")
        void should_failure_when_priceLessThan10000() {
            String input = "제로콜라-1";
            OrderMenu orderMenu = OrderMenu.of(input);
            OrderPrice orderPrice = OrderPrice.of(orderMenu);

            assertThat(orderPrice.isMoreThan10000())
                    .isFalse();
        }

        @Test
        @DisplayName("[SUCCESS] 총 주문 금액이 12만원을 넘으면 true를 반환한다")
        public void should_success_when_totalPriceLessThan120000() {
            String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
            OrderMenu orderMenu = OrderMenu.of(input);

            OrderPrice orderPrice = OrderPrice.of(orderMenu);

            assertThat(orderPrice.isMoreThan120_000())
                    .isTrue();
        }
    }
}
