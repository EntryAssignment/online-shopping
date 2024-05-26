package org.example.onlineshopping.dto.request;

import lombok.Getter;
import org.example.onlineshopping.entity.OrderProduct;

@Getter
public class OrderProductRequestDTO {
    private int orderId;
    private int productId;
}
