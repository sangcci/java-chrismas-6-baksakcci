package christmas.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Named.named;

import christmas.domain.constant.MenuType;
import christmas.domain.order.OrderMenu;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class OrderMenuTest {

    @Nested
    @DisplayName("[new] 생성자 테스트")
    class newTest {
        @Test
        @DisplayName("[SUCCESS] 주문 메뉴와 수량을 가진 OrderMenu 객체 생성")
        void should_success_when_createOrderMenu() {
            String input = "티본스테이크-10,제로콜라-10";

            assertThat(OrderMenu.of(input))
                    .isInstanceOf(OrderMenu.class);
        }

        static Stream<Arguments> provideOrderMenu(){
            return Stream.of(
                    Arguments.of(named("하이픈(-) 기호가 없을 경우 예외가 발생한다", "제로콜라=1,타파스=1")),
                    Arguments.of(named("주문 메뉴가 중복된다면 예외가 발생한다", "제로콜라-1,제로콜라-1")),
                    Arguments.of(named("주문 메뉴가 존재하지 않을 경우 예외가 발생한다", "함박스테이크-1,사이다-1")),
                    Arguments.of(named("주문 메뉴 총 수량이 20개가 넘을 경우 예외가 발생한다", "티본스테이크-10,제로콜라-20"))
            );
        }

        @ParameterizedTest()
        @MethodSource("provideOrderMenu")
        @DisplayName("[EXCEPTION] 생성자 유효성 검사 테스트")
        void invalidCreate(String input) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> OrderMenu.of(input));
        }
    }

    @Nested
    @DisplayName("[utility] 유틸 메서드 테스트 테스트")
    class utilityTest {

        public static Stream<Arguments> provideOrderMenuForTotalPrice() {
            return Stream.of(
                    Arguments.of("티본스테이크-1,제로콜라-1", 58_000L),
                    Arguments.of("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", 142_000L),
                    Arguments.of("타파스-1,제로콜라-1", 8_500L)
            );
        }

        @ParameterizedTest
        @MethodSource("provideOrderMenuForTotalPrice")
        @DisplayName("[SUCCESS] 주문 메뉴의 총 금액을 계산한다")
        void should_success_when_calculateTotalPrice(String input, long totalOrderPrice) {
            OrderMenu orderMenu = OrderMenu.of(input);

            assertThat(orderMenu.calculateTotalPrice())
                    .isEqualTo(totalOrderPrice);
        }

        public static Stream<Arguments> provideOrderMenuForCount() {
            return Stream.of(
                    Arguments.of("티본스테이크-1,바비큐립-3", 4, MenuType.MAIN),
                    Arguments.of("티본스테이크-1,바비큐립-1,초코케이크-2,아이스크림-3", 5, MenuType.DESSERT),
                    Arguments.of("타파스-1", 1, MenuType.APPETIZER),
                    Arguments.of("제로콜라-1", 1, MenuType.BEVERAGE)
            );
        }

        @ParameterizedTest
        @MethodSource("provideOrderMenuForCount")
        @DisplayName("[SUCCESS] 주문 메뉴에서 메뉴 타입에 따라 주문 수량을 출력한다")
        void should_success_when_orderMenuCountForMenuType(String input, int count, MenuType menuType) {
            OrderMenu orderMenu = OrderMenu.of(input);

            assertThat(orderMenu.getMenuCount(menuType))
                    .isEqualTo(count);
        }
    }
}
