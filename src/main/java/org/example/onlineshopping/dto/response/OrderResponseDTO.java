package org.example.onlineshopping.dto.response;

import lombok.Builder;

import java.util.Date;

@Builder
public class OrderResponseDTO {
    private int id;
    private int customerId;
    private Date orderDate;
}
