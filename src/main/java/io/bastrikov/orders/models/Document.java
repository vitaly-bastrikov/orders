package io.bastrikov.orders.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    private int id;
    private Customer customer;
    private Address address;
    private List<Product> products;
    private int time;
    private double shippingFee;
    private int deliveryTime;
    private double taxes;
    private double totalPrice;
    private double priceWithTax;
}
