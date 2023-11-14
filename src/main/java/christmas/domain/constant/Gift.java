package christmas.domain.constant;

public enum Gift {
    CHAMPAGNE("샴페인"),
    NOTHING("없음");

    private String name;

    Gift(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
