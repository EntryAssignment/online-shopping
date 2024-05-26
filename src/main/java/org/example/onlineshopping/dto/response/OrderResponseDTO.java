package org.example.onlineshopping.dto.response;

import org.example.onlineshopping.entity.Order;

import java.util.Date;

public class OrderResponseDTO {
    private int id;
    private int customerId;
    private Date orderDate;

    public OrderResponseDTO(Order order) {
        this.id = order.getId();
        this.customerId = order.getId();
        this.orderDate = order.getOrderDate();
    }
}
