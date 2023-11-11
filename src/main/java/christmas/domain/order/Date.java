package christmas.domain.order;

public class Date {

    private int date;

    // constructor
    private Date(int date) {
        validateDate(date);
        this.date = date;
    }

    // static factory
    public static Date of(Integer input) {
        return new Date(input);
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
