package christmas.service.discount;

import christmas.domain.benefit.BenefitHistory;
import christmas.domain.constant.Benefit;
import christmas.domain.constant.MenuType;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenu;

public class WeekService {

    // constant
    private static final long ONE_TIME_DISCOUNT_PRICE = 2_023L;

    // constructor
    private WeekService() {
    }

    // static factory
    public static WeekService of() {
        return new WeekService();
    }

    // service
    public void applyDiscount(OrderDate orderDate, OrderMenu orderMenu, BenefitHistory benefitHistory) {
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
}
