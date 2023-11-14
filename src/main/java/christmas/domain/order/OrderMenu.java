package christmas.domain.order;

import christmas.domain.constant.Menu;
import christmas.domain.constant.MenuType;
import christmas.exception.IllegalMenuInputException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderMenu {

    private final Map<Menu, Integer> orderMenuCount;

    // constant
    private static final String COMMA = ",";
    private static final String HYPHEN = "-";

    // constructor
    private OrderMenu(Map<Menu, Integer> orderMenuCount) {
        validateCountLimit(orderMenuCount);
        this.orderMenuCount = orderMenuCount;
    }

    // static factory
    public static OrderMenu of(String input) {
        return new OrderMenu(
                Arrays.stream(splitByComma(input))
                        .map(OrderMenu::splitByHyphen)
                        .collect(Collectors.toMap(
                            split -> Menu.isManuPresent(split[0]),
                            split -> parseStringToCount(split[1]),
                            (oldValue, newValue) -> { throw new IllegalMenuInputException(); },
                            () -> new EnumMap<>(Menu.class))
                ));
    }

    // parser
    private static String[] splitByComma(String input) {
        return input.split(COMMA);
    }

    private static String[] splitByHyphen(String input) {
        validateNotContainHyphen(input);
        return input.split(HYPHEN);
    }

    private static int parseStringToCount(String input) {
        validateIsNumeric(input);
        int parseInt = Integer.parseInt(input);
        validateIsCount(parseInt);
        return parseInt;
    }

    // utility
    public long calculateTotalPrice() {
        return Arrays.stream(Menu.values())
                .filter(menu -> getCount(menu) != 0)
                .mapToLong(menu -> menu.calculatePricesPerMenu(getCount(menu)))
                .sum();
    }

    private int getCount(Menu menu) {
        return orderMenuCount.getOrDefault(menu, 0);
    }

    public int getMenuCount(MenuType menuType) {
        return Menu.getManu(menuType).stream()
                .mapToInt(menu -> orderMenuCount.getOrDefault(menu, 0))
                .sum();
    }

    private int getOrderMenuCountSum(Map<Menu, Integer> orderMenuCount) {
        return orderMenuCount.values().stream()
                .mapToInt(i -> i)
                .sum();
    }

    // exception handling
    private void validateCountLimit(Map<Menu, Integer> orderMenuCount) {
        if (20 < getOrderMenuCountSum(orderMenuCount)) {
            throw new IllegalMenuInputException();
        };
    }

    private static void validateNotContainHyphen(String input) {
        if (!input.contains("-")) {
            throw new IllegalMenuInputException();
        }
    }

    private static void validateIsNumeric(String input) {
        if (!isNumeric(input)) {
            throw new IllegalMenuInputException();
        }
    }

    private static void validateIsCount(int input) {
        if (isNotLessThan1(input)) {
            throw new IllegalMenuInputException();
        }
    }

    // validation
    private static boolean isNumeric(String input) {
        return input.chars().allMatch(Character::isDigit);
    }

    private static boolean isNotLessThan1(int input) {
        return input < 1;
    }
    public boolean isOnlyBeverage() {
        return orderMenuCount.keySet().stream()
                .allMatch(menu -> menu.isBeverage());
    }

    // getter
    public Map<Menu, Integer> getOrderMenuCount() {
        return orderMenuCount;
    }
}
