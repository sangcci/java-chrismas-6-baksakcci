package christmas.benefit;

import christmas.domain.benefit.BenefitHistory;
import christmas.service.GiftService;
import christmas.domain.constant.Benefit;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderPrice;
import christmas.view.input.InputUtil;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class GiftBenefitDiscountServiceTest {

    @Nested
    @DisplayName("[service] 선물 혜택을 받는다")
    class serviceTest {

        @Test
        @DisplayName("[SUCCESS] 총 주문 금액이 12만원을 넘지 못하면 혜택을 받지 못한다")
        void should_failure_when_isNotMoreThen120000() {
            // given
            GiftService giftService = GiftService.of();
            String input = "티본스테이크-1,제로콜라-1";
            Map<String, Integer> orderMenuInput = InputUtil.convertNameAndCount(input);
            OrderMenu orderMenu = OrderMenu.of(orderMenuInput);
            OrderPrice orderPrice = OrderPrice.of(orderMenu);
            BenefitHistory benefitHistory = BenefitHistory.of();

            // when
            giftService.present(orderPrice, benefitHistory);

            // then
            assertThat(benefitHistory.getHasChampagne())
                    .isFalse();
            assertThat(benefitHistory.getBenefitDiscountEachPrice(Benefit.GIFT_EVENT))
                    .isEqualTo(0L);
        }

        @Test
        @DisplayName("[SUCCESS] 총 주문 금액이 12만원이 넘는다면 혜택을 받는다.")
        void should_success_when_isMoreThen120000() {
            // given
            GiftService giftService = GiftService.of();
            String input = "티본스테이크-3,제로콜라-1";
            Map<String, Integer> orderMenuInput = InputUtil.convertNameAndCount(input);
            OrderMenu orderMenu = OrderMenu.of(orderMenuInput);
            OrderPrice orderPrice = OrderPrice.of(orderMenu);
            BenefitHistory benefitHistory = BenefitHistory.of();

            // when
            giftService.present(orderPrice, benefitHistory);

            // then
            assertThat(benefitHistory.getHasChampagne())
                    .isTrue();
        }
    }
}
