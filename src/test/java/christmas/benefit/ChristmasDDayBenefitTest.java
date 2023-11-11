package christmas.benefit;

import christmas.domain.benefit.BenefitHistory;
import christmas.domain.benefit.ChristmasDDayBenefit;
import christmas.domain.constant.Benefit;
import christmas.domain.order.OrderDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ChristmasDDayBenefitTest {

    @Nested
    @DisplayName("[service] 크리스마스 할인 혜택을 받는다")
    class serviceTest {

        @Test
        @DisplayName("[SUCCESS] 1일부터 25일 사이의 기간이 아니라면 혜택을 받지 못한다")
        void should_notBenefit_when_isNotBetween1And25() {
            // given
            OrderDate orderDate = OrderDate.of(27);
            BenefitHistory benefitHistory = BenefitHistory.of();

            // when
            ChristmasDDayBenefit benefit = ChristmasDDayBenefit.of();
            benefit.applyDiscount(orderDate, benefitHistory);

            // then
            assertThat(orderDate.isContainChristmasDDay())
                    .isFalse();
            assertThat(benefitHistory.getBenefitDiscountEachPrice(Benefit.CHRISTMAS_D_DAY_DISCOUNT))
                    .isEqualTo(0L);
        }

        @Test
        @DisplayName("[SUCCESS] 1일부터 25일 사이의 기간이라면 혜택을 받는다")
        void should_benefit_when_isBetween1And25() {
            // given
            OrderDate orderDate = OrderDate.of(10);
            BenefitHistory benefitHistory = BenefitHistory.of();

            // when
            ChristmasDDayBenefit benefit = ChristmasDDayBenefit.of();
            benefit.applyDiscount(orderDate, benefitHistory);

            // then
            assertThat(orderDate.isContainChristmasDDay())
                    .isTrue();
            assertThat(benefitHistory.getBenefitDiscountEachPrice(Benefit.CHRISTMAS_D_DAY_DISCOUNT))
                    .isEqualTo(1_900L);
        }
    }
}
