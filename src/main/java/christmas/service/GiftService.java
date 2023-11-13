package christmas.service;

import christmas.domain.benefit.BenefitHistory;
import christmas.domain.benefit.GiftHistory;
import christmas.domain.order.OrderPrice;

public class GiftService {

    // constructor
    private GiftService() {
    }

    // static factory
    public static GiftService of() {
        return new GiftService();
    }

    // service
    public void present(OrderPrice orderPrice, BenefitHistory benefitHistory, GiftHistory giftHistory) {
        if (hasQualificationForGift(orderPrice)) {
        }
    }

    // validation
    private boolean hasQualificationForGift(OrderPrice orderPrice) {
        return orderPrice.isMoreThan120_000();
    }
}
