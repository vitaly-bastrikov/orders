package io.bastrikov.orders.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private int addressId;
}
