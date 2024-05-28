package org.example.onlineshopping.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponseDTO {
    private int id;
    private String name;
}
