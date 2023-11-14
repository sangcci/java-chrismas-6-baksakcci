package christmas.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import christmas.domain.order.OrderMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class OrderMenuTest {

    @Nested
    @DisplayName("[parser] 입력 값 파서 테스트")
    class InputTest {

        @Test
        @DisplayName("[EXCEPTION] 주문 메뉴가 중복된다면 예외가 발생한다")
        void should_throwException_when_duplicateMenuName() {
            String input = "제로콜라-1,제로콜라-1";

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> OrderMenu.of(input));
        }
    }

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

        @Test
        @DisplayName("[EXCEPTION] 주문 메뉴가 존재하지 않는다면 예외가 발생한다")
        void should_throwException_when_isNotExistMenu() {
            String input = "함박스테이크-10,사이다-20";

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> OrderMenu.of(input));
        }

        @Test
        @DisplayName("[EXCEPTION] 주문 메뉴 총 수량이 20개가 넘는다면 예외가 발생한다")
        void should_throwException_when_moreThan20() {
            String input = "티본스테이크-10,제로콜라-20";

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> OrderMenu.of(input));
        }
    }

}
