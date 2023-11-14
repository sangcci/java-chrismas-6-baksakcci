package christmas.exception;

public class IllegalDateInputException extends IllegalArgumentException {

    @Override
    public String getMessage() {
        return "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    }
}
