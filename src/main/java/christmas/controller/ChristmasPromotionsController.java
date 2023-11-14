package christmas.controller;

import christmas.domain.benefit.BenefitHistory;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderPrice;
import christmas.service.DiscountService;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;
import java.util.function.Supplier;

public class ChristmasPromotionsController {

    private InputView inputView;
    private OutputView outputView;
    private DiscountService discountService;

    public ChristmasPromotionsController(InputView inputView,
            OutputView outputView,
            DiscountService discountService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.discountService = discountService;
    }

    // exception handler
    public <T> T retryOnException(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    // run
    public void run() {
        // input
        inputView.openingComment();
        OrderDate orderDate = retryOnException(this::inputOrderDate);
        OrderMenu orderMenu = retryOnException(this::inputOrderMenu);
        OrderPrice orderPrice = OrderPrice.of(orderMenu);

        // apply benefit
        BenefitHistory benefitHistory = applyBenefit(orderDate, orderMenu, orderPrice);

        // output
        outputBenefit(orderMenu, orderPrice, benefitHistory);
    }

    private OrderDate inputOrderDate() {
        return OrderDate.of(inputView.inputDate());
    }

    private OrderMenu inputOrderMenu() {
        return OrderMenu.of(inputView.inputMenu());
    }

    private BenefitHistory applyBenefit(OrderDate orderDate, OrderMenu orderMenu, OrderPrice orderPrice) {
        BenefitHistory benefitHistory = BenefitHistory.of();

        if (orderPrice.isMoreThan10000() && !orderMenu.isOnlyBeverage()) {
            benefitHistory.addGiftChampagne(orderPrice);

            discountService.applyChristmasDDayDiscount(orderDate, benefitHistory);
            discountService.applyWeekDiscount(orderDate, orderMenu, benefitHistory);
            discountService.applySpecialDiscount(orderDate, benefitHistory);

            benefitHistory.addEventBadge();
        }
        return benefitHistory;
    }

    private void outputBenefit(OrderMenu orderMenu, OrderPrice orderPrice, BenefitHistory benefitHistory) {
        outputView.printTitle();
        outputView.printBeforeApplyBenefit(orderMenu, orderPrice);

        outputView.printAfterApplyBenefit(benefitHistory,
                benefitHistory.getTotalBenefitPrice(),
                benefitHistory.getTotalPriceAfterDiscount(orderPrice),
                benefitHistory.getEventBadge());
    }
}
