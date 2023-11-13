package christmas.view.output.constant;

public enum Unit {
    EA("개"),
    WON("원"),
    NOTHING("없음");

    private String unit;

    Unit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}
