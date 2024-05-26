package org.example.onlineshopping.service;

import lombok.RequiredArgsConstructor;

import org.example.onlineshopping.dto.request.OrderRequestDTO;
import org.example.onlineshopping.dto.response.OrderResponseDTO;
import org.example.onlineshopping.entity.Customer;
import org.example.onlineshopping.entity.Order;
import org.example.onlineshopping.repository.CustomerRepository;
import org.example.onlineshopping.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public List<OrderResponseDTO> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(OrderResponseDTO::new).toList();
    }

    public OrderResponseDTO getOrderById(int id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            return new OrderResponseDTO(orderOptional.get());
        } else {
            throw new IllegalArgumentException("order not found with id: " + id);
        }
    }

    public void createOrder(OrderRequestDTO orderRequestDTO) {
        Optional<Customer> customerOptional = customerRepository.findById(orderRequestDTO.getCustomerId());
        if (customerOptional.isPresent()) {
            Order order = new Order(customerOptional.get(), new Date());
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("customer not found with id: " + orderRequestDTO.getCustomerId());
        }
    }
}
