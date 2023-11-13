package christmas.service.discount;

import christmas.domain.benefit.BenefitHistory;
import christmas.domain.constant.DiscountBenefit;
import christmas.domain.order.OrderDate;

public class SpecialService {

    // constructor
    private SpecialService() {
    }

    // static factory
    public static SpecialService of() {
        return new SpecialService();
    }

    // service
    public void applyDiscount(OrderDate orderDate, BenefitHistory benefitHistory) {
        if (orderDate.isStarDay()) {
            benefitHistory.addDiscountPrice(DiscountBenefit.SPECIAL_DISCOUNT, 1_000L);
        }
    }
}
