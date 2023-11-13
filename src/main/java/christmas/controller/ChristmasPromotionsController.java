package christmas.controller;

import christmas.domain.benefit.BenefitHistory;
import christmas.domain.benefit.ChristmasDDayBenefit;
import christmas.domain.benefit.EventBadgeBenefit;
import christmas.domain.benefit.GiftBenefit;
import christmas.domain.benefit.SpecialBenefit;
import christmas.domain.benefit.WeekBenefit;
import christmas.domain.constant.EventBadge;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderPrice;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

public class ChristmasPromotionsController {

    private InputView inputView;
    private OutputView outputView;

    public ChristmasPromotionsController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        // input
        OrderDate orderDate = OrderDate.of(inputView.inputDate());
        OrderMenu orderMenu = OrderMenu.of(inputView.inputOrderMenuAndCount());
        OrderPrice orderPrice = OrderPrice.of(orderMenu);
        BenefitHistory benefitHistory = BenefitHistory.of();

        // service
        GiftBenefit giftBenefit = GiftBenefit.of();
        giftBenefit.present(orderPrice, benefitHistory);

        ChristmasDDayBenefit christmasDDayBenefit = ChristmasDDayBenefit.of();
        christmasDDayBenefit.applyDiscount(orderDate, benefitHistory);

        WeekBenefit weekBenefit = WeekBenefit.of();
        weekBenefit.applyDiscount(orderDate, orderMenu, benefitHistory);

        SpecialBenefit specialBenefit = SpecialBenefit.of();
        specialBenefit.applyDiscount(orderDate, benefitHistory);

        EventBadgeBenefit eventBadgeBenefit = EventBadgeBenefit.of();
        EventBadge eventBadge = eventBadgeBenefit.apply(benefitHistory);

        // output
        outputView.printTitle();
        outputView.printOrderMenu(orderMenu);
        outputView.printTotalOrderPrice(orderPrice);
        outputView.printGiftMenu(giftBenefit);
        outputView.printBenefitHistory(benefitHistory);
        outputView.printTotalBenefitPrice(benefitHistory.getTotalBenefitPrice());
        long totalPriceAfterDiscount = orderPrice.getTotalPrice() - benefitHistory.getTotalDiscountPrice();
        outputView.printTotalPriceAfterDiscount(totalPriceAfterDiscount);
        outputView.printEventBadge(eventBadge);
    }
}
