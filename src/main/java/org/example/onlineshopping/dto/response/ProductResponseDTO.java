package org.example.onlineshopping.dto.response;

import org.example.onlineshopping.entity.Product;

public class ProductResponseDTO {
    private int id;
    private String name;
    private int price;

    public ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
