package org.example.onlineshopping.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.onlineshopping.dto.request.ProductRequestDTO;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int price;

    public Product(ProductRequestDTO productRequestDTO) {
        this.name = productRequestDTO.getName();
        this.price = productRequestDTO.getPrice();
    }
}
