package org.example.onlineshopping.service;

import lombok.RequiredArgsConstructor;

import org.example.onlineshopping.dto.request.OrderRequestDTO;
import org.example.onlineshopping.dto.response.OrderResponseDTO;
import org.example.onlineshopping.entity.Customer;
import org.example.onlineshopping.entity.Order;
import org.example.onlineshopping.exception.UserNotFoundException;
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

    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(order -> OrderResponseDTO.builder()
                .id(order.getId())
                .customerId(order.getCustomer().getId())
                .orderDate(order.getOrderDate()).build()).toList();
    }

    public OrderResponseDTO getOrderById(int id) {
        Optional<Order> orderOptional = orderRepository.findById(id);

        if (orderOptional.isEmpty()) {
            throw new UserNotFoundException("Order not found with id: " + id);
        }

        return OrderResponseDTO.builder()
                .id(orderOptional.get().getId())
                .customerId(orderOptional.get().getCustomer().getId())
                .orderDate(orderOptional.get().getOrderDate()).build();
    }

    public void createOrder(OrderRequestDTO orderRequestDTO) {
        Optional<Customer> customerOptional = customerRepository.findById(orderRequestDTO.getCustomerId());

        if (customerOptional.isEmpty()) {
            throw new UserNotFoundException("customer not found with id: " + orderRequestDTO.getCustomerId());
        }

        Order order = Order.builder()
                .customer(customerOptional.get())
                .orderDate(new Date()).build();

        orderRepository.save(order);
    }
}
