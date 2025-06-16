package org.example;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Client client;
    private List<Item> items = new ArrayList<>();
    private double discountRate = 0.1;

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