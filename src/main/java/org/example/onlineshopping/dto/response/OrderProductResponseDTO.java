package org.example.onlineshopping.dto.response;

import org.example.onlineshopping.entity.OrderProduct;

public class OrderProductResponseDTO {
    private int id;
    private int orderId;
    private int productId;

    public OrderProductResponseDTO(OrderProduct orderProduct) {
        this.id = orderProduct.getId();
        this.orderId = orderProduct.getOrder().getId();
        this.productId = orderProduct.getProduct().getId();
    }
}
