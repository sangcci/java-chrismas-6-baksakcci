package christmas;

import christmas.controller.ChristmasPromotionsController;
import christmas.service.DiscountService;
import christmas.service.EventBadgeService;
import christmas.service.GiftService;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

public class Application {
    public static void main(String[] args) {
        // setting
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        GiftService giftService = GiftService.of();
        DiscountService discountService = DiscountService.of();
        EventBadgeService eventBadgeService = EventBadgeService.of();
        ChristmasPromotionsController controller = new ChristmasPromotionsController(inputView,
                outputView,
                giftService,
                discountService,
                eventBadgeService);

        // run
        controller.run();
    }
}
