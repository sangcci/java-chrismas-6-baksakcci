package christmas.view.input;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.input.constant.InputMessage;

public class InputView {

    public void openingComment() {
        System.out.println(InputMessage.OPENING.getMessage());
    }

    public String inputDate() {
        System.out.println(InputMessage.ASKING_DATE.getMessage());
        return input();
    }

    public String inputMenu() {
        System.out.println(InputMessage.ASKING_MENU.getMessage());
        return input();
    }

    private String input() {
        String input = Console.readLine();
        validateBlank(input);
        return input;
    }

    // exception handling
    private static void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 빈 값은 인식할 수 없습니다.");
        }
    }
}
