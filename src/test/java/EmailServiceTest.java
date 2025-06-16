import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.example.EmailService;

import static org.junit.jupiter.api.Assertions.*;

class EmailServiceTest {

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
    void deveImprimirMensagemDeEmailCorretamente() {
        String email = "cliente@teste.com";
        String mensagem = "Olá! Seu pedido foi recebido.";

        EmailService.sendEmail(email, mensagem);

        String saida = outContent.toString().trim();
        assertEquals("Enviando e-mail para " + email + ": " + mensagem, saida);
    }
}
