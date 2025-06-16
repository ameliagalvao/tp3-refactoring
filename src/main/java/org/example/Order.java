package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        double total = 0;
        System.out.println("Cliente: " + client.getName());
        for (Item item : items) {
            System.out.printf(Locale.US, "%dx %s - R$%.2f%n", item.getQuantity(), item.getProductName(), item.getPrice());
            total += item.getPrice() * item.getQuantity();
        }

        System.out.printf(Locale.US, "Subtotal: R$%.2f%n", total);
        System.out.printf(Locale.US, "Desconto: R$%.2f%n", total * discountRate);
        System.out.printf(Locale.US, "Total final: R$%.2f%n", total * (1 - discountRate));
    }

    public void sendEmail() {
        EmailService.sendEmail(client.getEmail(), "Pedido recebido! Obrigado pela compra.");
    }
}