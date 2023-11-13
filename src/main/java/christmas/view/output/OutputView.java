package christmas.view.output;

import christmas.domain.benefit.BenefitHistory;
import christmas.service.GiftService;
import christmas.domain.constant.Benefit;
import christmas.domain.constant.EventBadge;
import christmas.domain.constant.Menu;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderPrice;
import christmas.view.output.constant.OutputMessage;
import christmas.view.output.constant.Unit;
import java.util.Map;

public class OutputView {

    public void printTitle() {
        System.out.println(OutputMessage.TITLE.getMessage());
        System.out.println();
    }

    public void printOrderMenu(OrderMenu orderMenu) {
        System.out.println(OutputMessage.ORDER_MENU.getMessage());
        Map<Menu, Integer> orderMenuCount = orderMenu.getOrderMenuCount();
        orderMenuCount.keySet().stream()
                .forEach(menu -> System.out.println(menu.getName() + " " + orderMenuCount.get(menu) + Unit.EA.getUnit()));
        System.out.println();
    }

    public void printTotalOrderPrice(OrderPrice orderPrice) {
        System.out.println(OutputMessage.TOTAL_ORDER_PRICE_BEFORE_DISCOUNT.getMessage());
        System.out.println(orderPrice.getTotalPrice() + Unit.WON.getUnit());
        System.out.println();
    }

    public void printGiftMenu(BenefitHistory benefitHistory) {
        System.out.println(OutputMessage.GIFT_MENU.getMessage());
        if (benefitHistory.getHasChampagne()) {
            System.out.println("샴페인 1개");
        } else {
            System.out.println(Unit.NOTHING.getUnit());
        }
        System.out.println();
    }

    public void printBenefitHistory(BenefitHistory benefitHistory) {
        System.out.println(OutputMessage.BENEFIT_HISTORY.getMessage());
        Map<Benefit, Long> benefitDiscountPrice = benefitHistory.getBenefitDiscountPrice();
        benefitDiscountPrice.keySet().stream()
                .forEach(benefit -> System.out.println(benefit.getName() + ": " + benefitDiscountPrice.get(benefit) + "원"));
        System.out.println();
    }

    public void printTotalBenefitPrice(long price) {
        System.out.println(OutputMessage.TOTAL_BENEFIT_PRICE.getMessage());
        System.out.println(price + "원");
        System.out.println();
    }

    public void printTotalPriceAfterDiscount(long price) {
        System.out.println(OutputMessage.PAYMENT_AFTER_DISCOUNT.getMessage());
        System.out.println(price + "원");
        System.out.println();
    }

    public void printEventBadge(EventBadge eventBadge) {
        System.out.println(OutputMessage.DECEMBER_EVENT_BADGE.getMessage());
        System.out.println(eventBadge.getName());
    }
}
