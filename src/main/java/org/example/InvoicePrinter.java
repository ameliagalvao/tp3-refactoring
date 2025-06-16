package org.example;

import java.util.List;

public interface InvoicePrinter {
    void print(Client client, List<Item> items, double discountRate);
}
