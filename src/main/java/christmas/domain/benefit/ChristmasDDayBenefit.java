package christmas.domain.benefit;

import christmas.domain.constant.Benefit;
import christmas.domain.order.OrderDate;

public class ChristmasDDayBenefit {

    // constructor
    private ChristmasDDayBenefit() {
    }

    // static factory
    public static ChristmasDDayBenefit of() {
        return new ChristmasDDayBenefit();
    }

    // service
    public void applyDiscount(OrderDate orderDate, BenefitHistory benefitHistory) {
        if (orderDate.isContainChristmasDDay()) {
            long discountPrice = orderDate.calculateChristmasDDayDiscount();
            benefitHistory.addDiscountPrice(Benefit.CHRISTMAS_D_DAY_DISCOUNT, discountPrice);
        }
    }
}
