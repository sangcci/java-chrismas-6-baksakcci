package christmas.controller;

import christmas.domain.benefit.BenefitHistory;
import christmas.service.DiscountService;
import christmas.service.EventBadgeService;
import christmas.service.GiftService;
import christmas.domain.constant.EventBadge;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderPrice;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

public class ChristmasPromotionsController {

    private InputView inputView;
    private OutputView outputView;
    private GiftService giftService;
    private DiscountService discountService;
    private EventBadgeService eventBadgeService;

    public ChristmasPromotionsController(InputView inputView,
            OutputView outputView,
            GiftService giftService,
            DiscountService discountService,
            EventBadgeService eventBadgeService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.giftService = giftService;
        this.discountService = discountService;
        this.eventBadgeService = eventBadgeService;
    }

    public OrderDate inputOrderDate() {
        return OrderDate.of(inputView.inputDate());
    }

    public OrderMenu inputOrderMenu() {
        return OrderMenu.of(inputView.inputOrderMenuAndCount());
    }

    public void run() {
        // input
        OrderDate orderDate = inputOrderDate();
        OrderMenu orderMenu = inputOrderMenu();
        OrderPrice orderPrice = OrderPrice.of(orderMenu);
        BenefitHistory benefitHistory = BenefitHistory.of();

        // service
        giftService.present(orderPrice, benefitHistory);

        discountService.applyChristmasDDayDiscount(orderDate, benefitHistory);

        discountService.applyWeekDiscount(orderDate, orderMenu, benefitHistory);

        discountService.applySpecialDiscount(orderDate, benefitHistory);

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
