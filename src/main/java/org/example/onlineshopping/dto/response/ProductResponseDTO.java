package org.example.onlineshopping.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDTO {
    private int id;
    private String name;
    private int price;
}
