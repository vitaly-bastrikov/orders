package io.bastrikov.orders.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxRequestEntity {
    private double totalPrice;
    private Address address;
}
