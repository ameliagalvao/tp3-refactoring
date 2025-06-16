import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.DiscountPolicy;

class DiscountPolicyTest {

    @Test
    void deveCalcularDescontoCorretamente() {
        double amount = 100.0;
        double rate = 0.1;

        double desconto = DiscountPolicy.calculateDiscount(amount, rate);

        assertEquals(10.0, desconto, 0.0001);
    }

}