package christmas.domain.order;

public class OrderPrice {

    private long totalPrice;
    private long benefitPrice;

    // constructor
    private OrderPrice(long totalPrice) {
        this.totalPrice = totalPrice;
        this.benefitPrice = 0;
    }

    // static factory
    public static OrderPrice of(OrderMenu orderMenu) {
        long totalPrice = orderMenu.calculateTotalPrice();
        return new OrderPrice(totalPrice);
    }

    // utility
    public boolean isMoreThan120_000() {
        return totalPrice >= 120_000L;
    }

    // validation
    public boolean isLessThan10000(long totalPrice) {
        return totalPrice < 10_000L;
    }

    // getter
    public long getTotalPrice() {
        return totalPrice;
    }
}
