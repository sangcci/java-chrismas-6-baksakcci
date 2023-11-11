package christmas.domain.benefit;

import christmas.domain.constant.Benefit;
import christmas.domain.constant.Menu;
import christmas.domain.order.OrderPrice;

public class GiftBenefit {

    private boolean hasChampagne;

    // constructor
    private GiftBenefit() {
        this.hasChampagne = false;
    }

    // static factory
    public static GiftBenefit of() {
        return new GiftBenefit();
    }

    // service
    public void present(OrderPrice orderPrice, BenefitHistory benefitHistory) {
        if (hasQualificationForGift(orderPrice)) {
            hasChampagne = true;
            benefitHistory.addDiscountPrice(Benefit.GIFT_EVENT, Menu.CHAMPAGNE.getPrice());
        }
    }

    // validation
    private boolean hasQualificationForGift(OrderPrice orderPrice) {
        return orderPrice.isMoreThan120_000();
    }

    // getter
    public boolean hasChampagne() {
        return hasChampagne;
    }
}
