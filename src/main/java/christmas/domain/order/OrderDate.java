package christmas.domain.order;

import java.util.stream.IntStream;

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
        return 1000 + ((date - 1) * 100L);
    }

    public boolean isWeekend() {
        return IntStream.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)
                .anyMatch(i -> date == i);
    }

    public boolean isWeekday() {
        return !isWeekend();
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
