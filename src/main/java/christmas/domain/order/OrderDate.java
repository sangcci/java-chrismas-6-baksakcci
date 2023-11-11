package christmas.domain.order;

public class OrderDate {

    private int date;

    // constructor
    private OrderDate(int date) {
        validateDate(date);
        this.date = date;
    }

    // static factory
    public static OrderDate of(Integer input) {
        return new OrderDate(input);
    }

    // utility
    public boolean isContainChristmasDDay() {
        return date >= 1 && date <= 25;
    }

    public long calculateChristmasDDayDiscount() {
        return 1000 + ((date - 1) * 100);
    }

    // exception handling
    private void validateDate(int date) {
        if (isIncludeDate(date)) {
            throw new IllegalArgumentException("[ERROR] 유효한 날짜 형식이 아닙니다.");
        }
    }

    // validation
    private boolean isIncludeDate(int date) {
        return date < 1 || date > 31;
    }
}
