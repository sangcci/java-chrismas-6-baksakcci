package christmas.controller;

import christmas.domain.benefit.BenefitHistory;
import christmas.domain.constant.EventBadge;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderPrice;
import christmas.service.DiscountService;
import christmas.service.EventBadgeService;
import christmas.service.GiftService;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;
import java.util.function.Supplier;

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

    public OrderDate inputOrderDate() {
        return OrderDate.of(inputView.inputDate());
    }

    public OrderMenu inputOrderMenu() {
        return OrderMenu.of(inputView.inputMenu());
    }

    public void run() {
        // input
        inputView.openingComment();
        OrderDate orderDate = retryOnException(this::inputOrderDate);
        OrderMenu orderMenu = retryOnException(this::inputOrderMenu);
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
        outputView.printBeforeApplyBenefit(orderMenu, orderPrice);

        long totalPriceAfterDiscount = orderPrice.getTotalPrice() - benefitHistory.getTotalDiscountPrice();
        outputView.printAfterApplyBenefit(benefitHistory,
                benefitHistory.getTotalBenefitPrice(),
                totalPriceAfterDiscount,
                eventBadge);
    }
}
