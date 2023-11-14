package christmas.benefit;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.benefit.BenefitHistory;
import christmas.domain.constant.Benefit;
import christmas.domain.constant.EventBadge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class EventBadgeBenefitTest {

    @Nested
    @DisplayName("[service] 이벤트 뱃지 혜택을 적용한다")
    class serviceTest {

        @Test
        @DisplayName("[SUCCESS] 총 혜택 금액이 5000원 이상 10000원 이하라면 별 뱃지를 받는다")
        void should_getStarBadge_when_isBetween5000And10000() {
            // given
            BenefitHistory benefitHistory = BenefitHistory.of();
            benefitHistory.addDiscountPrice(Benefit.WEEKEND_DISCOUNT, 5000);

            // when
            benefitHistory.addEventBadge();

            // then
            assertThat(benefitHistory.getEventBadge()).isEqualTo(EventBadge.STAR);
        }

        @Test
        @DisplayName("[SUCCESS] 총 혜택 금액이 10000원 이상 20000원 이하라면 트리 뱃지를 받는다")
        void should_getTreeBadge_when_isBetween10000And20000() {
            // given
            BenefitHistory benefitHistory = BenefitHistory.of();
            benefitHistory.addDiscountPrice(Benefit.WEEKEND_DISCOUNT, 10000);

            // when
            benefitHistory.addEventBadge();

            // then
            assertThat(benefitHistory.getEventBadge()).isEqualTo(EventBadge.TREE);
        }

        @Test
        @DisplayName("[SUCCESS] 총 혜택 금액이 20000원 이상 이라면 트리 뱃지를 받는다")
        void should_getSantaBadge_when_isMoreThan20000() {
            // given
            BenefitHistory benefitHistory = BenefitHistory.of();
            benefitHistory.addDiscountPrice(Benefit.WEEKEND_DISCOUNT, 20000);

            // when
            benefitHistory.addEventBadge();

            // then
            assertThat(benefitHistory.getEventBadge()).isEqualTo(EventBadge.SANTA);
        }

        @Test
        @DisplayName("[SUCCESS] 총 혜택 금액이 5000원 이하 라면 뱃지 혜택을 받지 않는다")
        void should_null_when_isLessThan5000() {
            // given
            BenefitHistory benefitHistory = BenefitHistory.of();
            benefitHistory.addDiscountPrice(Benefit.WEEKEND_DISCOUNT, 1000);

            // when
            benefitHistory.addEventBadge();

            // then
            assertThat(benefitHistory.getEventBadge())
                    .isEqualTo(EventBadge.NOTHING);
        }
    }
}
