package org.example.onlineshopping.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.onlineshopping.dto.request.CustomerRequestDTO;

@Entity
@Getter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Customer(CustomerRequestDTO customerRequestDTO) {
        this.name = customerRequestDTO.getName();
    }
}
