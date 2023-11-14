package christmas.view.output;

import christmas.domain.benefit.BenefitHistory;
import christmas.domain.constant.Benefit;
import christmas.domain.constant.EventBadge;
import christmas.domain.constant.Menu;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderPrice;
import christmas.view.output.constant.OutputMessage;
import christmas.view.output.constant.OutputFormat;
import java.util.Map;

public class OutputView {

    public void printTitle() {
        System.out.println(OutputMessage.TITLE.getMessage());
        System.out.println();
    }

    public void printBeforeApplyBenefit(OrderMenu orderMenu, OrderPrice orderPrice) {
        printOrderMenu(orderMenu);
        printTotalOrderPrice(orderPrice);
    }

    private void printOrderMenu(OrderMenu orderMenu) {
        System.out.println(OutputMessage.ORDER_MENU.getMessage());
        Map<Menu, Integer> orderMenuCount = orderMenu.getOrderMenuCount();
        orderMenuCount.keySet().stream()
                .forEach(menu -> System.out.format(OutputFormat.ORDER_MENU.get(),
                        menu.getName(),
                        orderMenuCount.get(menu)));
        System.out.println();
    }

    private void printTotalOrderPrice(OrderPrice orderPrice) {
        System.out.println(OutputMessage.TOTAL_ORDER_PRICE_BEFORE_DISCOUNT.getMessage());
        System.out.format(OutputFormat.WON.get(), orderPrice.getTotalPrice());
        System.out.println();
    }

    public void printAfterApplyBenefit(BenefitHistory benefitHistory,
            long totalBenefitPrice,
            long totalPriceAfterDiscount,
            EventBadge eventBadge) {
        printGiftMenu(benefitHistory);
        printBenefitHistory(benefitHistory);
        printTotalBenefitPrice(totalBenefitPrice);
        printTotalPriceAfterDiscount(totalPriceAfterDiscount);
        printEventBadge(eventBadge);
    }

    private void printGiftMenu(BenefitHistory benefitHistory) {
        System.out.println(OutputMessage.GIFT_MENU.getMessage());
        System.out.format(OutputFormat.ORDER_MENU.get(), benefitHistory.getGift().getName(), 1);
        System.out.println();
    }

    private void printBenefitHistory(BenefitHistory benefitHistory) {
        System.out.println(OutputMessage.BENEFIT_HISTORY.getMessage());
        Map<Benefit, Long> benefitDiscountPrice = benefitHistory.getBenefitDiscountPrice();
        benefitDiscountPrice.keySet().stream()
                .forEach(benefit -> System.out.format(OutputFormat.BENEFIT_PRICE.get(),
                        benefit.getName(),
                        benefitDiscountPrice.get(benefit)));
        System.out.println();
    }

    private void printTotalBenefitPrice(long price) {
        System.out.println(OutputMessage.TOTAL_BENEFIT_PRICE.getMessage());
        System.out.format(OutputFormat.WON.get(), price);
        System.out.println();
    }

    private void printTotalPriceAfterDiscount(long price) {
        System.out.println(OutputMessage.PAYMENT_AFTER_DISCOUNT.getMessage());
        System.out.format(OutputFormat.WON.get(), price);
        System.out.println();
    }

    private void printEventBadge(EventBadge eventBadge) {
        System.out.println(OutputMessage.DECEMBER_EVENT_BADGE.getMessage());
        System.out.println(eventBadge.getName());
    }
}
