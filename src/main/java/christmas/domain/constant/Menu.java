package christmas.domain.constant;

import java.util.Arrays;

public enum Menu {

    MUSHROOM_SOUP("양송이스프", 6_000L),
    TAPAS("타파스", 5_500L),
    CAESAR_SALAD("시저샐러드", 8_000L),
    T_BONE_STEAK("티본스테이크", 55_000L),
    BARBECUE_RIBS("바비큐립", 54_000L),
    SEAFOOD_PASTA("해산물파스타", 35_000L),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000L),
    CHOCO_CAKE("초코케이크", 15_000L),
    ICE_CREAM("아이스크림", 5_000L),
    ZERO_COLA("제로콜라", 3_000L),
    RED_WINE("레드와인", 60_000L),
    CHAMPAGNE("샴페인", 25_000L);

    private String name;
    private long price;

    Menu(String name, long price) {
        this.name = name;
        this.price = price;
    }

    // utility
    public static Menu isManuPresent(String input) {
        return Arrays.stream(values())
                .filter(menu -> isExistMenu(input, menu.name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 메뉴입니다."));
    }

    // validation
    private static boolean isExistMenu(String input, String name) {
        return name.equals(input);
    }
}
