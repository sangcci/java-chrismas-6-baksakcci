package christmas.domain.order;

import christmas.domain.constant.Menu;
import christmas.domain.constant.MenuType;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class OrderMenu {

    private final Map<Menu, Integer> orderMenuCount;

    // constructor
    private OrderMenu(Map<Menu, Integer> orderMenuCount) {
        validateCountLimit(orderMenuCount);
        this.orderMenuCount = orderMenuCount;
    }

    // static factory
    public static OrderMenu of(Map<String, Integer> input) {
        // convert to EnumMap
        EnumMap<Menu, Integer> enumMap = new EnumMap<>(Menu.class);
        input.forEach((k, v) -> {
            Menu menu = Menu.isManuPresent(k);
            enumMap.put(menu, v);
        });
        return new OrderMenu(enumMap);
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
            throw new IllegalArgumentException("[ERROR] 메뉴는 최대 20개까지 선택 가능하다.");
        };
    }

    // getter
    public Map<Menu, Integer> getOrderMenuCount() {
        return orderMenuCount;
    }
}
