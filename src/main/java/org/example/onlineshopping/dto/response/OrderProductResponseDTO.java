package org.example.onlineshopping.dto.response;

import lombok.Builder;

@Builder
public class OrderProductResponseDTO {
    private int id;
    private int orderId;
    private int productId;
}
