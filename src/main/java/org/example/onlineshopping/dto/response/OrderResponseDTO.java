package org.example.onlineshopping.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OrderResponseDTO {
    private int id;
    private int customerId;
    private Date orderDate;
}
