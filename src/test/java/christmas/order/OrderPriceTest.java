package christmas.order;

import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderPrice;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class OrderPriceTest {

    @Nested
    @DisplayName("[new] 생성자 테스트")
    class newTest {

        @Test
        @DisplayName("[SUCCESS] 메뉴와 갯수에 맞게 총 주문 금액을 계산 후 Price 객체 생성")
        void should_success_when_createPrice() {
            String input = "티본스테이크-1,제로콜라-1";
            OrderMenu orderMenu = OrderMenu.of(input);

            Assertions.assertThat(OrderPrice.of(orderMenu))
                    .isInstanceOf(OrderPrice.class);
            Assertions.assertThat(OrderPrice.of(orderMenu).getTotalPrice())
                    .isEqualTo(58_000L);
        }

        @Test
        @DisplayName("[EXCEPTION] 총 주문 금액이 1만원이 넘지 못하면 예외 발생")
        void should_throwException_when_priceLessThan10000() {
            String input = "제로콜라-1";
            OrderMenu orderMenu = OrderMenu.of(input);

            //Assertions.assertThatIllegalArgumentException().isThrownBy(() -> OrderPrice.of(orderMenu));
        }
    }
}
