package org.example.onlineshopping.controller;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopping.dto.request.OrderProductRequestDTO;
import org.example.onlineshopping.dto.response.OrderProductResponseDTO;
import org.example.onlineshopping.service.OrderProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-product")
@RequiredArgsConstructor
public class OrderProductController {
    private final OrderProductService orderProductService;

    @GetMapping
    public List<OrderProductResponseDTO> getAllOrderProducts() {
        return orderProductService.getAllOrderProducts();
    }

    @GetMapping("{id}")
    public OrderProductResponseDTO getOrderProductsById(@PathVariable("id") int id) {
        return orderProductService.getOrderProductsById(id);
    }

    @PostMapping
    public void creatOrderProduct(@RequestBody OrderProductRequestDTO orderProductRequestDTO) {
        orderProductService.creatOrderProduct(orderProductRequestDTO);
    }
}
