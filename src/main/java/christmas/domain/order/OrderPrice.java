package christmas.domain.order;

public class OrderPrice {

    private long totalPrice;
    private long benefitPrice;

    // constructor
    public OrderPrice(long totalPrice) {
        validateMinimumPrice(totalPrice);
        this.totalPrice = totalPrice;
        this.benefitPrice = 0;
    }

    // static factory
    public static OrderPrice of(OrderMenu orderMenu) {
        long totalPrice = orderMenu.calculateTotalPrice();
        return new OrderPrice(totalPrice);
    }

    // exception handling
    private void validateMinimumPrice(long totalPrice) {
        if (isLessThan10000(totalPrice)) {
            throw new IllegalArgumentException("[ERROR] 총 주문 금액이 1만원이 넘지 않습니다.");
        }
    }

    // validation
    private boolean isLessThan10000(long totalPrice) {
        return totalPrice < 10_000L;
    }

    // getter
    public long getTotalPrice() {
        return totalPrice;
    }
}
