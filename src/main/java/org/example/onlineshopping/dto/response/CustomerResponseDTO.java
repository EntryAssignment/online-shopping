package org.example.onlineshopping.dto.response;

import org.example.onlineshopping.entity.Customer;

public class CustomerResponseDTO {
    private int id;
    private String name;

    public CustomerResponseDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
    }
}
