package org.example.onlineshopping.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryResponseDTO {
    private int id;
    private int orderId;
    private String status;
}
