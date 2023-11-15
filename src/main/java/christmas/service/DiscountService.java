package christmas.service;

import christmas.domain.benefit.BenefitHistory;
import christmas.domain.constant.Benefit;
import christmas.domain.constant.MenuType;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenu;

public class DiscountService {

    // constant
    private static final long ONE_TIME_DISCOUNT_PRICE = 2_023L;

    // constructor
    private DiscountService() {
    }

    // static factory
    public static DiscountService of() {
        return new DiscountService();
    }

    // service - christmas-d-day-service
    public void applyChristmasDDayDiscount(OrderDate orderDate, BenefitHistory benefitHistory) {
        if (orderDate.isContainChristmasDDay()) {
            long discountPrice = orderDate.calculateChristmasDDayDiscount();
            benefitHistory.addDiscountPrice(Benefit.CHRISTMAS_D_DAY_DISCOUNT, discountPrice);
        }
    }

    // service - week-service
    public void applyWeekDiscount(OrderDate orderDate, OrderMenu orderMenu, BenefitHistory benefitHistory) {
        if (orderDate.isWeekend()) {
            applyWeekendDiscount(orderMenu, benefitHistory);
        }
        if (orderDate.isWeekday()) {
            applyWeekdayDiscount(orderMenu, benefitHistory);
        }
    }

    private void applyWeekendDiscount(OrderMenu orderMenu, BenefitHistory benefitHistory) {
        int mainMenuCount = orderMenu.getMenuCount(MenuType.MAIN);
        benefitHistory.addDiscountPrice(Benefit.WEEKEND_DISCOUNT, mainMenuCount * ONE_TIME_DISCOUNT_PRICE);
    }

    private void applyWeekdayDiscount(OrderMenu orderMenu, BenefitHistory benefitHistory) {
        int dessertMenuCount = orderMenu.getMenuCount(MenuType.DESSERT);
        benefitHistory.addDiscountPrice(Benefit.WEEKDAY_DISCOUNT, dessertMenuCount * ONE_TIME_DISCOUNT_PRICE);
    }

    // service - special-service
    public void applySpecialDiscount(OrderDate orderDate, BenefitHistory benefitHistory) {
        if (orderDate.isStarDay()) {
            benefitHistory.addDiscountPrice(Benefit.SPECIAL_DISCOUNT, 1_000L);
        }
    }
}
