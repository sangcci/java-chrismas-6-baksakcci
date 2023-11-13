package christmas;

import christmas.controller.ChristmasPromotionsController;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

public class Application {
    public static void main(String[] args) {
        // setting
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ChristmasPromotionsController controller = new ChristmasPromotionsController(inputView, outputView);
        // run
        controller.run();
    }
}
