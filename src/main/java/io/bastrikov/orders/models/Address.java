package io.bastrikov.orders.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Address {
    private int id;
    private String country;
    private String city;
    private String street;
    private int streetNumber;

    @Override
    public String toString(){
        return country + ", " + city + ", " + streetNumber + " " + street;
    }
}
