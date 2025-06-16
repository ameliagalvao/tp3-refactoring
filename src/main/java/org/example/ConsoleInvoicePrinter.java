package org.example;

import java.util.List;
import java.util.Locale;

public class ConsoleInvoicePrinter implements InvoicePrinter {

    @Override
    public void print(Client client, List<Item> items, double discountRate) {
        double total = calculateTotal(items);

        System.out.println("Cliente: " + client.getName());
        for (Item item : items) {
            printLineItem(item);
        }
        printSummary(total, discountRate);
    }

    private double calculateTotal(List<Item> items) {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    private void printLineItem(Item item) {
        System.out.printf(Locale.US, "%dx %s - R$%.2f%n",
                item.getQuantity(),
                item.getProductName(),
                item.getPrice());
    }

    private void printSummary(double total, double discountRate) {
        System.out.printf(Locale.US, "Subtotal: R$%.2f%n", total);
        System.out.printf(Locale.US, "Desconto: R$%.2f%n", total * discountRate);
        System.out.printf(Locale.US, "Total final: R$%.2f%n", total * (1 - discountRate));
    }
}
