package christmas.domain.benefit;

import christmas.domain.constant.EventBadge;

public class EventBadgeBenefit {

    // constructor
    private EventBadgeBenefit() {
    }

    // static factory
    public static EventBadgeBenefit of() {
        return new EventBadgeBenefit();
    }

    // service
    public EventBadge apply(BenefitHistory benefitHistory) {
        long totalBenefitPrice = benefitHistory.getTotalBenefitPrice();
        return EventBadge.determineBadge(totalBenefitPrice);
    }
}
