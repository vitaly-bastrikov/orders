package io.bastrikov.orders.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class Product {
    private int id;
    private String name;
    private double price;
    private String category;
}
