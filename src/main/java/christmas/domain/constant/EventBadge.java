package christmas.domain.constant;

public enum EventBadge {
    STAR("별", 5_000L),
    TREE("트리", 10_000L),
    SANTA("산타", 20_000L),
    NOTHING("없음", 0L);

    private String name;
    private long minimumPrice;

    // enum constructor
    EventBadge(String name, long minimumPrice) {
        this.name = name;
        this.minimumPrice = minimumPrice;
    }

    // utility
    public static EventBadge determineBadge(long price) {
        if (price >= SANTA.minimumPrice) {
            return EventBadge.SANTA;
        }
        if (price >= TREE.minimumPrice) {
            return EventBadge.TREE;
        }
        if (price >= STAR.minimumPrice) {
            return EventBadge.STAR;
        }
        return EventBadge.NOTHING;
    }

    // getter
    public String getName() {
        return name;
    }
}
