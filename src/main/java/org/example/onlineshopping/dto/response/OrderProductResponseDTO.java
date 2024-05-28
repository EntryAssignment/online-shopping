package org.example.onlineshopping.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderProductResponseDTO {
    private int id;
    private int orderId;
    private int productId;
}
