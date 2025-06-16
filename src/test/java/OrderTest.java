import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.example.Order;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void deveImprimirNotaFiscalCorretamente() {
        Order order = new Order();
        order.clientName = "João";
        order.clientEmail = "joao@email.com";
        order.products.add("Notebook");
        order.quantities.add(1);
        order.prices.add(3500.0);
        order.products.add("Mouse");
        order.quantities.add(2);
        order.prices.add(80.0);

        order.printInvoice();

        String output = outContent.toString().trim();

        assertTrue(output.contains("Cliente: João"));
        assertTrue(output.contains("1x Notebook - R$3500.0"));
        assertTrue(output.contains("2x Mouse - R$80.0"));
        assertTrue(output.contains("Subtotal: R$3660.0"));
        assertTrue(output.contains("Desconto: R$366.0"));
        assertTrue(output.contains("Total final: R$3294.0"));
    }

    @Test
    void deveEnviarEmailCorretamente() {
        Order order = new Order();
        order.clientName = "João";
        order.clientEmail = "joao@email.com";

        order.sendEmail();

        String output = outContent.toString().trim();
        assertTrue(output.contains("Enviando e-mail para joao@email.com"));
        assertTrue(output.contains("Pedido recebido! Obrigado pela compra."));
    }
}
