package christmas.benefit;

import christmas.domain.benefit.BenefitHistory;
import christmas.service.discount.SpecialService;
import christmas.domain.constant.DiscountBenefit;
import christmas.domain.order.OrderDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SpecialDiscountBenefitTest {

    @Nested
    @DisplayName("[service] 특별 할인 혜택을 적용한다")
    class serviceTest {

        @Test
        @DisplayName("[SUCCESS] 별이 있는 날이라면 1000원 할인 받는다")
        void should_discount_when_isStarDay() {
            // given
            OrderDate orderDate = OrderDate.of(10);
            BenefitHistory benefitHistory = BenefitHistory.of();

            // when
            SpecialService specialService = SpecialService.of();
            specialService.applyDiscount(orderDate, benefitHistory);

            // then
            assertThat(benefitHistory.getBenefitDiscountEachPrice(DiscountBenefit.SPECIAL_DISCOUNT))
                    .isEqualTo(1_000L);
        }

        @Test
        @DisplayName("[SUCCESS] 별이 없는 날이라면 할인 받지 않는다")
        void should_notDiscount_when_isNotStarDay() {
            // given
            OrderDate orderDate = OrderDate.of(13);
            BenefitHistory benefitHistory = BenefitHistory.of();

            // when
            SpecialService specialService = SpecialService.of();
            specialService.applyDiscount(orderDate, benefitHistory);

            // then
            assertThat(benefitHistory.getBenefitDiscountEachPrice(DiscountBenefit.SPECIAL_DISCOUNT))
                    .isEqualTo(0L);
        }
    }
}
