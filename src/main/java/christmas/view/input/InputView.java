package christmas.view.input;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.input.constant.InputMessage;
import java.util.Map;

public class InputView {

    public int inputDate() {
        System.out.println(InputMessage.OPENING.getMessage());
        System.out.println(InputMessage.ASKING_DATE.getMessage());
        String input = Console.readLine();
        return InputUtil.parseStringToInt(input);
    }

    public Map<String, Integer> inputOrderMenuAndCount() {
        System.out.println(InputMessage.ASKING_MENU.getMessage());
        String input = Console.readLine();
        return InputUtil.convertNameAndCount(input);
    }
}
