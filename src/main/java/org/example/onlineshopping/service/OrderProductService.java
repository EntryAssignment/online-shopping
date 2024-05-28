package org.example.onlineshopping.service;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopping.dto.request.OrderProductRequestDTO;
import org.example.onlineshopping.dto.response.OrderProductResponseDTO;
import org.example.onlineshopping.entity.Order;
import org.example.onlineshopping.entity.OrderProduct;
import org.example.onlineshopping.entity.Product;
import org.example.onlineshopping.exception.UserNotFoundException;
import org.example.onlineshopping.repository.OrderProductRepository;
import org.example.onlineshopping.repository.OrderRepository;
import org.example.onlineshopping.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderProductService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    public List<OrderProductResponseDTO> getAllOrderProducts() {
        List<OrderProduct> orderProducts = orderProductRepository.findAll();

        return orderProducts.stream().map(orderProduct -> OrderProductResponseDTO.builder()
                .id(orderProduct.getId())
                .orderId(orderProduct.getOrder().getId())
                .productId(orderProduct.getProduct().getId()).build()).toList();
    }

    public OrderProductResponseDTO getOrderProductsById(int id) {
        Optional<OrderProduct> orderProductOptional = orderProductRepository.findById(id);

        if (orderProductOptional.isEmpty()) {
            throw new UserNotFoundException("order product not found with id: " + id);
        }

        return OrderProductResponseDTO.builder()
                .id(orderProductOptional.get().getId())
                .orderId(orderProductOptional.get().getOrder().getId())
                .productId(orderProductOptional.get().getProduct().getId()).build();
    }

    public void creatOrderProduct(OrderProductRequestDTO orderProductRequestDTO) {
        int orderId = orderProductRequestDTO.getProductId();
        int productId = orderProductRequestDTO.getOrderId();

        Optional<Order> orderOptional = orderRepository.findById(orderId);
        Optional<Product> productOptional = productRepository.findById(productId);

        if (orderOptional.isEmpty()) {
            throw new UserNotFoundException("order not found with id: " + orderId);
        }
        if (productOptional.isEmpty()) {
            throw new UserNotFoundException("product not found with id: " + productId);
        }

        OrderProduct orderProduct = OrderProduct.builder()
                .order(orderOptional.get())
                .product(productOptional.get()).build();

        orderProductRepository.save(orderProduct);
    }
}