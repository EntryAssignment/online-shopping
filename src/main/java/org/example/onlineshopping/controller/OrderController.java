package org.example.onlineshopping.controller;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopping.dto.request.OrderRequestDTO;
import org.example.onlineshopping.dto.response.OrderResponseDTO;
import org.example.onlineshopping.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderResponseDTO> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{id}")
    public OrderResponseDTO getOrderById(@PathVariable("id") int id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public void createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        orderService.createOrder(orderRequestDTO);
    }
}





