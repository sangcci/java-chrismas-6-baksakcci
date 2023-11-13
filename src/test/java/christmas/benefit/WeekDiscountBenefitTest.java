package christmas.benefit;

import christmas.domain.benefit.BenefitHistory;
import christmas.service.discount.WeekService;
import christmas.domain.constant.DiscountBenefit;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenu;
import christmas.view.input.InputUtil;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class WeekDiscountBenefitTest {

    @Nested
    @DisplayName("[service] 일주일 할인 혜택을 적용한다")
    class serviceTest {

        @Test
        @DisplayName("[SUCCESS] 주말이라면 메인 음식의 갯수 * 2023 만큼 할인 받는다")
        void should_discountMainDish_when_isWeekend() {
            // given
            OrderDate orderDate = OrderDate.of(15);
            String input = "티본스테이크-1,해산물파스타-2,아이스크림-2,제로콜라-1";
            Map<String, Integer> orderMenuInput = InputUtil.convertNameAndCount(input);
            OrderMenu orderMenu = OrderMenu.of(orderMenuInput);
            BenefitHistory benefitHistory = BenefitHistory.of();

            // when
            WeekService weekService = WeekService.of();
            weekService.applyDiscount(orderDate, orderMenu, benefitHistory);

            // then
            Assertions.assertThat(benefitHistory.getBenefitDiscountEachPrice(DiscountBenefit.WEEKEND_DISCOUNT))
                    .isEqualTo(2_023L * 3);
        }

        @Test
        @DisplayName("[SUCCESS] 주중이라면 디저트 음식의 갯수 * 2023 만큼 할인 받는다")
        void should_discountDessertDish_when_isWeekday() {
            // given
            OrderDate orderDate = OrderDate.of(18);
            String input = "티본스테이크-1,해산물파스타-2,아이스크림-2,제로콜라-1";
            Map<String, Integer> orderMenuInput = InputUtil.convertNameAndCount(input);
            OrderMenu orderMenu = OrderMenu.of(orderMenuInput);
            BenefitHistory benefitHistory = BenefitHistory.of();

            // when
            WeekService weekService = WeekService.of();
            weekService.applyDiscount(orderDate, orderMenu, benefitHistory);

            // then
            Assertions.assertThat(benefitHistory.getBenefitDiscountEachPrice(DiscountBenefit.WEEKDAY_DISCOUNT))
                    .isEqualTo(2_023L * 2);
        }
    }
}
