package org.example;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static final double DEFAULT_DISCOUNT_RATE = 0.1;

    private Client client;
    private List<Item> items = new ArrayList<>();
    private double discountRate = DEFAULT_DISCOUNT_RATE;

    public Order(Client client) {
        this.client = client;
    }

    public void addItem(String productName, int quantity, double price) {
        items.add(new Item(productName, quantity, price));
    }

    public void printInvoice() {
        InvoicePrinter printer = new ConsoleInvoicePrinter();
        printer.print(client, items, discountRate);
    }

    public void sendEmail() {
        EmailService.sendEmail(client.getEmail(), "Pedido recebido! Obrigado pela compra.");
    }
}