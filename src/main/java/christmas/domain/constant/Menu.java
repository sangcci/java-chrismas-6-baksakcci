package christmas.domain.constant;

import java.util.Arrays;
import java.util.List;

public enum Menu {

    // appetizer
    MUSHROOM_SOUP("양송이스프", MenuType.APPETIZER, 6_000L),
    TAPAS("타파스", MenuType.APPETIZER, 5_500L),
    CAESAR_SALAD("시저샐러드", MenuType.APPETIZER,  8_000L),

    // main
    T_BONE_STEAK("티본스테이크", MenuType.MAIN, 55_000L),
    BARBECUE_RIBS("바비큐립", MenuType.MAIN, 54_000L),
    SEAFOOD_PASTA("해산물파스타", MenuType.MAIN, 35_000L),
    CHRISTMAS_PASTA("크리스마스파스타", MenuType.MAIN, 25_000L),

    // dessert
    CHOCO_CAKE("초코케이크", MenuType.DESSERT, 15_000L),
    ICE_CREAM("아이스크림", MenuType.DESSERT, 5_000L),

    // beverage
    ZERO_COLA("제로콜라", MenuType.BEVERAGE, 3_000L),
    RED_WINE("레드와인", MenuType.BEVERAGE, 60_000L),
    CHAMPAGNE("샴페인", MenuType.BEVERAGE, 25_000L);

    private final String name;
    private final MenuType type;
    private final long price;

    Menu(String name, MenuType type, long price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    // utility
    public static Menu isManuPresent(String input) {
        return Arrays.stream(values())
                .filter(menu -> isExistMenu(input, menu.name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 메뉴입니다."));
    }

    public long calculatePricesPerMenu(int count) {
        return this.price * count;
    }

    public static List<Menu> getManu(MenuType menuType) {
        return Arrays.stream(values())
                .filter(menu -> menu.type.equals(menuType))
                .toList();
    }

    // validation
    private static boolean isExistMenu(String input, String name) {
        return name.equals(input);
    }

    public boolean isBeverage() {
        return type.equals(MenuType.BEVERAGE);
    }

    // getter
    public long getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }
}
