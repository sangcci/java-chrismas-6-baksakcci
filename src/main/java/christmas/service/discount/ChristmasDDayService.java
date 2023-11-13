package christmas.service.discount;

import christmas.domain.benefit.BenefitHistory;
import christmas.domain.constant.Benefit;
import christmas.domain.order.OrderDate;

public class ChristmasDDayService {

    // constructor
    private ChristmasDDayService() {
    }

    // static factory
    public static ChristmasDDayService of() {
        return new ChristmasDDayService();
    }

    // service
    public void applyDiscount(OrderDate orderDate, BenefitHistory benefitHistory) {
        if (orderDate.isContainChristmasDDay()) {
            long discountPrice = orderDate.calculateChristmasDDayDiscount();
            benefitHistory.addDiscountPrice(Benefit.CHRISTMAS_D_DAY_DISCOUNT, discountPrice);
        }
    }
}
