package christmas.benefit;

import christmas.domain.benefit.BenefitHistory;
import christmas.domain.constant.DiscountBenefit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class DiscountBenefitHistoryTest {

    @Nested
    @DisplayName("[new] 생성자 테스트")
    class newTest {

        @Test
        @DisplayName("[SUCCESS] 혜택 내역 객체 생성 성공")
        void should_success_when_createBenefitHistory() {
            assertThat(BenefitHistory.of())
                    .isInstanceOf(BenefitHistory.class);
        }
    }

    @Nested
    @DisplayName("[utility method] 각 혜택에 혜택 금액을 추가한다")
    class utilityTest {

        @Test
        @DisplayName("[SUCCESS] 혜택 금액을 비교하여 일치하면 true를 반환")
        void name() {
            // given
            BenefitHistory benefitHistory = BenefitHistory.of();

            // when
            benefitHistory.addDiscountPrice(DiscountBenefit.GIFT_EVENT, 25_000L);
            benefitHistory.addDiscountPrice(DiscountBenefit.WEEKDAY_DISCOUNT, 2_023L);

            // then
            assertThat(benefitHistory.getBenefitDiscountEachPrice(DiscountBenefit.GIFT_EVENT))
                    .isEqualTo(25_000L);
            assertThat(benefitHistory.getBenefitDiscountEachPrice(DiscountBenefit.WEEKDAY_DISCOUNT))
                    .isEqualTo(2_023L);
        }
    }
}
