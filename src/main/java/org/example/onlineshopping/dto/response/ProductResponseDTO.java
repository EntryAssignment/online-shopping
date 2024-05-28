package org.example.onlineshopping.dto.response;

import lombok.Builder;

@Builder
public class ProductResponseDTO {
    private int id;
    private String name;
    private int price;
}
