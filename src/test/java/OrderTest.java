import org.example.Client;
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
        Client client = new Client("João", "joao@email.com");
        Order order = new Order(client);
        order.addItem("Notebook", 1, 3500.0);
        order.addItem("Mouse", 2, 80.0);
        order.printInvoice();
        String output = outContent.toString().trim();
        String expectedOutput = String.join(System.lineSeparator(),
                "Cliente: João",
                "1x Notebook - R$3500.00",
                "2x Mouse - R$80.00",
                "Subtotal: R$3660.00",
                "Desconto: R$366.00",
                "Total final: R$3294.00"
        );
        assertEquals(expectedOutput, output);
    }

    @Test
    void deveEnviarEmailCorretamente() {
        Client client = new Client("João", "joao@email.com");
        Order order = new Order(client);
        order.sendEmail();
        String output = outContent.toString().trim();
        assertTrue(output.contains("Enviando e-mail para joao@email.com"));
        assertTrue(output.contains("Pedido recebido! Obrigado pela compra."));
    }
}
