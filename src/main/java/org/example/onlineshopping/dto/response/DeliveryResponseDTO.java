package org.example.onlineshopping.dto.response;

import org.example.onlineshopping.entity.Delivery;

public class DeliveryResponseDTO {
    private int id;
    private int orderId;
    private String status;

    public DeliveryResponseDTO(Delivery delivery) {
        this.id = delivery.getId();
        this.orderId = delivery.getOrder().getId();
        this.status = delivery.getStatus();
    }
}
