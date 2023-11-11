package christmas.domain.constant;

public enum Benefit {

    GIFT_EVENT("증정 이벤트"),
    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인"),
    WEEKDAY_DISCOUNT("평일 할인"),
    WEEKEND_DISCOUNT("주말 할인"),
    SPECIAL_DISCOUNT("특별 할인");

    private final String name;

    Benefit(String name) {
        this.name = name;
    }
}
