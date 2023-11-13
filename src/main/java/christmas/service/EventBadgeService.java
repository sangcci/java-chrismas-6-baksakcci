package christmas.service;

import christmas.domain.benefit.BenefitHistory;
import christmas.domain.constant.EventBadge;

public class EventBadgeService {

    // constructor
    private EventBadgeService() {
    }

    // static factory
    public static EventBadgeService of() {
        return new EventBadgeService();
    }

    // service
    public EventBadge apply(BenefitHistory benefitHistory) {
        long totalBenefitPrice = benefitHistory.getTotalBenefitPrice();
        return EventBadge.determineBadge(totalBenefitPrice);
    }
}
