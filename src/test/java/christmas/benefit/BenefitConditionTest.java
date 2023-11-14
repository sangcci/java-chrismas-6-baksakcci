package christmas.benefit;

import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderPrice;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BenefitConditionTest {

    @Nested
    @DisplayName("[condition] 혜택 적용 가능 여부 판단")
    class conditionTest {

        @Test
        @DisplayName("[SUCCESS] 총 주문 금액이 1만원이 넘지 못하면 이벤트를 적용하지 않는다")
        void should_failure_when_priceLessThan10000() {
            String input = "제로콜라-1";
            OrderMenu orderMenu = OrderMenu.of(input);
            OrderPrice orderPrice = OrderPrice.of(orderMenu);

            Assertions.assertThat(orderPrice.isMoreThan10000())
                    .isFalse();
        }

        @Test
        @DisplayName("[SUCCESS] 주문 메뉴가 음료수만 있다면 이벤트를 적용하지 않는다")
        void should_failure_when_OrderMenuExistOnlyBeverage() {
            String input = "제로콜라-2,레드와인-1";
            OrderMenu orderMenu = OrderMenu.of(input);

            Assertions.assertThat(orderMenu.isOnlyBeverage())
                    .isTrue();
        }
    }
}
