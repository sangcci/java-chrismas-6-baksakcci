package christmas.domain.benefit;

import christmas.domain.constant.Benefit;
import christmas.domain.order.OrderDate;

public class SpecialBenefit {

    // constructor
    private SpecialBenefit() {
    }

    // static factory
    public static SpecialBenefit of() {
        return new SpecialBenefit();
    }

    // service
    public void applyDiscount(OrderDate orderDate, BenefitHistory benefitHistory) {
        if (orderDate.isStarDay()) {
            benefitHistory.addDiscountPrice(Benefit.SPECIAL_DISCOUNT, 1_000L);
        }
    }
}
