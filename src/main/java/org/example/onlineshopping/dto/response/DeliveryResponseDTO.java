package org.example.onlineshopping.dto.response;

import lombok.Builder;

@Builder
public class DeliveryResponseDTO {
    private int id;
    private int orderId;
    private String status;
}
