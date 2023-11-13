package christmas.controller;

import christmas.domain.benefit.BenefitHistory;
import christmas.service.discount.ChristmasDDayService;
import christmas.service.EventBadgeService;
import christmas.service.GiftService;
import christmas.service.discount.SpecialService;
import christmas.service.discount.WeekService;
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
        GiftService giftService = GiftService.of();
        giftService.present(orderPrice, benefitHistory);

        ChristmasDDayService christmasDDayService = ChristmasDDayService.of();
        christmasDDayService.applyDiscount(orderDate, benefitHistory);

        WeekService weekService = WeekService.of();
        weekService.applyDiscount(orderDate, orderMenu, benefitHistory);

        SpecialService specialService = SpecialService.of();
        specialService.applyDiscount(orderDate, benefitHistory);

        EventBadgeService eventBadgeService = EventBadgeService.of();
        EventBadge eventBadge = eventBadgeService.apply(benefitHistory);

        // output
        outputView.printTitle();
        outputView.printOrderMenu(orderMenu);
        outputView.printTotalOrderPrice(orderPrice);
        outputView.printGiftMenu(benefitHistory);
        outputView.printBenefitHistory(benefitHistory);
        outputView.printTotalBenefitPrice(benefitHistory.getTotalBenefitPrice());
        long totalPriceAfterDiscount = orderPrice.getTotalPrice() - benefitHistory.getTotalDiscountPrice();
        outputView.printTotalPriceAfterDiscount(totalPriceAfterDiscount);
        outputView.printEventBadge(eventBadge);
    }
}
