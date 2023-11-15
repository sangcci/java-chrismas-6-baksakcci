package christmas.domain.constant;

import java.util.Arrays;
import java.util.function.Predicate;

public enum EventBadge {
    STAR("별", (price) -> price >= 5_000L && price < 10_000L),
    TREE("트리", (price) -> price >= 10_000L && price < 20_000L),
    SANTA("산타", (price) -> price >= 20_000L),
    NOTHING("없음", (price) -> price >= 0 & price < 5_000L);

    private final String name;
    private final Predicate<Long> predicate;

    // enum constructor
    EventBadge(String name, Predicate<Long> predicate) {
        this.name = name;
        this.predicate = predicate;
    }

    // utility
    public static EventBadge determineBadge(long price) {
        return Arrays.stream(values())
                .filter(eventBadge -> eventBadge.predicate.test(price))
                .findFirst()
                .orElse(NOTHING);
    }

    // getter
    public String getName() {
        return name;
    }
}
