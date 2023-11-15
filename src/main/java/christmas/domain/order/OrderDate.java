package christmas.domain.order;

import christmas.exception.IllegalDateInputException;
import java.util.stream.IntStream;

public class OrderDate {

    private final int date;

    // constructor
    private OrderDate(String input) {
        validateIsNumeric(input);
        int parseInt = Integer.parseInt(input);
        validateDate(parseInt);
        this.date = parseInt;
    }

    // static factory
    public static OrderDate of(String input) {
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

    public boolean isStarDay() {
        return IntStream.of(3, 10, 17, 24, 25, 31)
                .anyMatch(i -> date == i);
    }

    // exception handling
    private void validateIsNumeric(String input) {
        if (!isNumeric(input)) {
            throw new IllegalDateInputException();
        }
    }

    private void validateDate(int date) {
        if (isIncludeDate(date)) {
            throw new IllegalDateInputException();
        }
    }

    // validation
    private boolean isIncludeDate(int date) {
        return date < 1 || date > 31;
    }

    private boolean isNumeric(String input) {
        return input.chars().allMatch(Character::isDigit);
    }
}
