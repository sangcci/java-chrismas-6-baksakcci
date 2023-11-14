package christmas.service;

import christmas.domain.benefit.BenefitHistory;

public class EventBadgeService {

    // constructor
    private EventBadgeService() {
    }

    // static factory
    public static EventBadgeService of() {
        return new EventBadgeService();
    }

    // service
    public void apply(BenefitHistory benefitHistory) {
        benefitHistory.addEventBadge();
    }
}
