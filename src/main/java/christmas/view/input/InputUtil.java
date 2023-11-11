package christmas.view.input;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class InputUtil {

    private static final String COMMA = ",";
    private static final String HYPHEN = "-";

    // utility
    public static Map<String, Integer> convertNameAndCount(String input) {
        return Arrays.stream(splitByComma(input))
                .map(i -> splitByHyphen(i))
                .collect(Collectors.toMap(
                        parts -> parts[0],
                        parts -> InputUtil.parseStringToInt(parts[1]),
                        (oldValue, newValue) -> { throw new IllegalArgumentException("[ERROR] 중복된 값이 존재한다."); },
                        () -> new HashMap<>()
                ));
    }

    private static String[] splitByComma(String input) {
        return input.split(COMMA);
    }

    private static String[] splitByHyphen(String input) {
        return input.split(HYPHEN);
    }

    public static Integer parseStringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해 주세요.");
        }
    }

    // exception handling
    private static void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 빈 값은 인식할 수 없습니다.");
        }
    }
}
