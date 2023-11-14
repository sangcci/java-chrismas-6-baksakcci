package christmas.benefit;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.benefit.BenefitHistory;
import christmas.domain.constant.Benefit;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenu;
import christmas.service.DiscountService;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DiscountServiceTest {
    private DiscountService discountService;

    @BeforeEach
    void setUp() {
        discountService = DiscountService.of();
    }

    @Nested
    @DisplayName("[service] 크리스마스 할인 혜택을 받는다")
    class ChristmasDDayServiceTest {

        @Test
        @DisplayName("[SUCCESS] 1일부터 25일 사이의 기간이 아니라면 혜택을 받지 못한다")
        void should_notBenefit_when_isNotBetween1And25() {
            // given
            OrderDate orderDate = OrderDate.of("27");
            BenefitHistory benefitHistory = BenefitHistory.of();

            // when
            discountService.applyChristmasDDayDiscount(orderDate, benefitHistory);

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
            OrderDate orderDate = OrderDate.of("10");
            BenefitHistory benefitHistory = BenefitHistory.of();

            // when
            discountService.applyChristmasDDayDiscount(orderDate, benefitHistory);

            // then
            assertThat(orderDate.isContainChristmasDDay())
                    .isTrue();
            assertThat(benefitHistory.getBenefitDiscountEachPrice(Benefit.CHRISTMAS_D_DAY_DISCOUNT))
                    .isEqualTo(1_900L);
        }
    }

    @Nested
    @DisplayName("[service] 일주일 할인 혜택을 적용한다")
    class WeekServiceTest {

        @Test
        @DisplayName("[SUCCESS] 주말이라면 메인 음식의 갯수 * 2023 만큼 할인 받는다")
        void should_discountMainDish_when_isWeekend() {
            // given
            OrderDate orderDate = OrderDate.of("15");
            String input = "티본스테이크-1,해산물파스타-2,아이스크림-2,제로콜라-1";
            Map<String, Integer> orderMenuInput = InputUtil.convertNameAndCount(input);
            OrderMenu orderMenu = OrderMenu.of(input);
            BenefitHistory benefitHistory = BenefitHistory.of();

            // when
            discountService.applyWeekDiscount(orderDate, orderMenu, benefitHistory);

            // then
            Assertions.assertThat(benefitHistory.getBenefitDiscountEachPrice(Benefit.WEEKEND_DISCOUNT))
                    .isEqualTo(2_023L * 3);
        }

        @Test
        @DisplayName("[SUCCESS] 주중이라면 디저트 음식의 갯수 * 2023 만큼 할인 받는다")
        void should_discountDessertDish_when_isWeekday() {
            // given
            OrderDate orderDate = OrderDate.of("18");
            String input = "티본스테이크-1,해산물파스타-2,아이스크림-2,제로콜라-1";
            Map<String, Integer> orderMenuInput = InputUtil.convertNameAndCount(input);
            OrderMenu orderMenu = OrderMenu.of(input);
            BenefitHistory benefitHistory = BenefitHistory.of();

            // when
            discountService.applyWeekDiscount(orderDate, orderMenu, benefitHistory);

            // then
            Assertions.assertThat(benefitHistory.getBenefitDiscountEachPrice(Benefit.WEEKDAY_DISCOUNT))
                    .isEqualTo(2_023L * 2);
        }
    }

    @Nested
    @DisplayName("[service] 특별 할인 혜택을 적용한다")
    class SpecialServiceTest {

        @Test
        @DisplayName("[SUCCESS] 별이 있는 날이라면 1000원 할인 받는다")
        void should_discount_when_isStarDay() {
            // given
            OrderDate orderDate = OrderDate.of("10");
            BenefitHistory benefitHistory = BenefitHistory.of();

            // when
            discountService.applySpecialDiscount(orderDate, benefitHistory);

            // then
            assertThat(benefitHistory.getBenefitDiscountEachPrice(Benefit.SPECIAL_DISCOUNT))
                    .isEqualTo(1_000L);
        }

        @Test
        @DisplayName("[SUCCESS] 별이 없는 날이라면 할인 받지 않는다")
        void should_notDiscount_when_isNotStarDay() {
            // given
            OrderDate orderDate = OrderDate.of("13");
            BenefitHistory benefitHistory = BenefitHistory.of();

            // when
            discountService.applySpecialDiscount(orderDate, benefitHistory);

            // then
            assertThat(benefitHistory.getBenefitDiscountEachPrice(Benefit.SPECIAL_DISCOUNT))
                    .isEqualTo(0L);
        }
    }
}
