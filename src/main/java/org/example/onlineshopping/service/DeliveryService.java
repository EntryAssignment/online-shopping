package org.example.onlineshopping.service;

import lombok.RequiredArgsConstructor;

import org.example.onlineshopping.dto.request.DeliveryRequestDTO;
import org.example.onlineshopping.dto.request.DeliveryUpdateRequestDTO;
import org.example.onlineshopping.dto.response.DeliveryResponseDTO;
import org.example.onlineshopping.entity.Delivery;
import org.example.onlineshopping.entity.Order;
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
        return deliveries.stream().map(DeliveryResponseDTO::new).toList();
    }

    public DeliveryResponseDTO getDeliveryById(int id) {
        Optional<Delivery> deliveryOptional = deliveryRepository.findById(id);
        if (deliveryOptional.isPresent()) {
            return new DeliveryResponseDTO(deliveryOptional.get());
        } else {
            throw new IllegalArgumentException("Delivery not found with id: " + id);
        }
    }

    public void creatDelivery(DeliveryRequestDTO deliveryRequestDTO) {
        Optional<Order> orderOptional = orderRepository.findById(deliveryRequestDTO.getOrderId());
        if (orderOptional.isPresent()) {
            Delivery delivery = new Delivery(orderOptional.get(), deliveryRequestDTO.getStatus());
            deliveryRepository.save(delivery);
        } else {
            throw new IllegalArgumentException("Order not found with id: " + deliveryRequestDTO.getOrderId());
        }
    }

    public void updateDelivery(int id, DeliveryUpdateRequestDTO deliveryUpdateRequestDTO) {
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(id);
        if (optionalDelivery.isPresent()) {
            Delivery delivery = optionalDelivery.get();
            delivery.setStatus(deliveryUpdateRequestDTO.getStatus());
            deliveryRepository.save(delivery);
        } else {
            throw new IllegalArgumentException("Delivery not found with id: " + id);
        }
    }
}
