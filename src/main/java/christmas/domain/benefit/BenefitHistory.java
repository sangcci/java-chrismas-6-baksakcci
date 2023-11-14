package christmas.domain.benefit;

import christmas.domain.constant.Benefit;
import christmas.domain.constant.EventBadge;
import christmas.domain.constant.Gift;
import christmas.domain.constant.Menu;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BenefitHistory {

    private final Map<Benefit, Long> benefitDiscountPrice;
    private Gift gift;
    private EventBadge eventBadge;

    // constructor
    private BenefitHistory(Map<Benefit, Long> benefitDiscountPrice) {
        this.benefitDiscountPrice = benefitDiscountPrice;
        this.gift = Gift.NOTHING;
        this.eventBadge = EventBadge.NOTHING;
    }
    
    // static factory
    public static BenefitHistory of() {
        // init
        EnumMap<Benefit, Long> benefitDiscountPrice = Arrays.stream(Benefit.values())
                .collect(Collectors.toMap(
                        benefit -> benefit,
                        benefit -> 0L,
                        (oldValue, newValue) -> newValue,
                        () -> new EnumMap<>(Benefit.class)
                ));
        return new BenefitHistory(benefitDiscountPrice);
    }

    // utility
    public void addDiscountPrice(Benefit benefit, long price) {
        Long past = benefitDiscountPrice.get(benefit);
        benefitDiscountPrice.replace(benefit, past + price);
    }

    public void addGiftChampagne() {
        addDiscountPrice(Benefit.GIFT_EVENT, Menu.CHAMPAGNE.getPrice());
        gift = Gift.CHAMPAGNE;
    }

    public void addEventBadge() {
        long totalDiscountPrice = getTotalBenefitPrice();
        this.eventBadge = EventBadge.determineBadge(totalDiscountPrice);
    }

    public Long getBenefitDiscountEachPrice(Benefit benefit) {
        return benefitDiscountPrice.get(benefit);
    }

    public long getTotalBenefitPrice() {
        return benefitDiscountPrice.values().stream()
                .mapToLong(p -> p)
                .sum();
    }

    public long getTotalDiscountPrice() {
        return benefitDiscountPrice.keySet().stream()
                .filter(benefit -> benefit != Benefit.GIFT_EVENT)
                .mapToLong(benefitDiscountPrice::get)
                .sum();
    }

    // getter
    public Map<Benefit, Long> getBenefitDiscountPrice() {
        return benefitDiscountPrice;
    }

    public Gift getGift() {
        return gift;
    }

    public EventBadge getEventBadge() {
        return eventBadge;
    }
}
