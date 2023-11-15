package christmas.view.output.constant;

public enum OutputFormat {

    ORDER_MENU("%s %d개\n"),
    WON("%,d원\n"),
    BENEFIT_PRICE("%s: -%,d원\n"),
    NOTHING("없음\n");

    private String format;

    OutputFormat(String format) {
        this.format = format;
    }

    public String get() {
        return format;
    }
}
