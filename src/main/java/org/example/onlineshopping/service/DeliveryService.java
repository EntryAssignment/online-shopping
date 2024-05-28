package org.example.onlineshopping.service;

import lombok.RequiredArgsConstructor;

import org.example.onlineshopping.dto.request.DeliveryRequestDTO;
import org.example.onlineshopping.dto.request.DeliveryUpdateRequestDTO;
import org.example.onlineshopping.dto.response.DeliveryResponseDTO;
import org.example.onlineshopping.entity.Delivery;
import org.example.onlineshopping.entity.Order;
import org.example.onlineshopping.exception.UserNotFoundException;
import org.example.onlineshopping.repository.DeliveryRepository;
import org.example.onlineshopping.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;

    public List<DeliveryResponseDTO> getAllDelivery() {
        List<Delivery> deliveries = deliveryRepository.findAll();

        return deliveries.stream().map(delivery -> DeliveryResponseDTO.builder()
                .id(delivery.getId())
                .orderId(delivery.getOrder().getId())
                .status(delivery.getStatus()).build()).toList();
    }

    public DeliveryResponseDTO getDeliveryById(int id) {
        Optional<Delivery> deliveryOptional = deliveryRepository.findById(id);

        if (deliveryOptional.isEmpty()) {
            throw new UserNotFoundException("Delivery not found with id: " + id);
        }

        return DeliveryResponseDTO.builder()
                .id(deliveryOptional.get().getId())
                .orderId(deliveryOptional.get().getOrder().getId())
                .status(deliveryOptional.get().getStatus()).build();
    }

    public void creatDelivery(DeliveryRequestDTO deliveryRequestDTO) {
        Optional<Order> orderOptional = orderRepository.findById(deliveryRequestDTO.getOrderId());

        if (orderOptional.isEmpty()) {
            throw new UserNotFoundException("Order not found with id: " + deliveryRequestDTO.getOrderId());
        }

        Delivery delivery = Delivery.builder()
                .order(orderOptional.get())
                .status(deliveryRequestDTO.getStatus()).build();

        deliveryRepository.save(delivery);
    }

    public void updateDelivery(int id, DeliveryUpdateRequestDTO deliveryUpdateRequestDTO) {
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(id);

        if (optionalDelivery.isEmpty()) {
            throw new UserNotFoundException("Delivery not found with id: " + id);
        }

        Delivery delivery = optionalDelivery.get();
        delivery.setStatus(deliveryUpdateRequestDTO.getStatus());

        deliveryRepository.save(delivery);
    }
}
