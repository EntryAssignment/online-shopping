package org.example.onlineshopping.service;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopping.dto.request.OrderProductRequestDTO;
import org.example.onlineshopping.dto.response.OrderProductResponseDTO;
import org.example.onlineshopping.entity.Order;
import org.example.onlineshopping.entity.OrderProduct;
import org.example.onlineshopping.entity.Product;
import org.example.onlineshopping.repository.CustomerRepository;
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
    private final CustomerRepository customerRepository;
    private final OrderProductRepository orderProductRepository;

    public List<OrderProductResponseDTO> getAllOrderProducts() {
        List<OrderProduct> orders = orderProductRepository.findAll();
        return orders.stream().map(OrderProductResponseDTO::new).toList();
    }

    public OrderProductResponseDTO getOrderProductsById(int id) {
        Optional<OrderProduct> orderProductOptional = orderProductRepository.findById(id);
        if (orderProductOptional.isPresent()) {
            return new OrderProductResponseDTO(orderProductOptional.get());
        } else {
            throw new IllegalArgumentException("order product not found with id: " + id);
        }
    }

    public void creatOrderProduct(OrderProductRequestDTO orderProductRequestDTO){
        int orderId = orderProductRequestDTO.getProductId();
        int productId = orderProductRequestDTO.getOrderId();

        Optional<Order> orderOptional = orderRepository.findById(orderId);
        Optional<Product> productOptional = productRepository.findById(productId);

        if (orderOptional.isPresent()) {
            if (productOptional.isPresent()) {
                OrderProduct orderProduct = new OrderProduct(orderOptional.get(), productOptional.get());
                orderProductRepository.save(orderProduct);
            } else {
                throw new IllegalArgumentException("product not found with id: " + productId);
            }
        } else {
            throw new IllegalArgumentException("order not found with id: " + orderId);
        }
    }
}