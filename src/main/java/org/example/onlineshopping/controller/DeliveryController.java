package org.example.onlineshopping.controller;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopping.dto.request.DeliveryRequestDTO;
import org.example.onlineshopping.dto.request.DeliveryUpdateRequestDTO;
import org.example.onlineshopping.dto.response.DeliveryResponseDTO;
import org.example.onlineshopping.service.DeliveryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    @GetMapping
    public List<DeliveryResponseDTO> getAllDelivery() {
        return deliveryService.getAllDelivery();
    }

    @GetMapping("/{id}")
    public DeliveryResponseDTO getDeliveryById(@PathVariable("id") int id) {
        return deliveryService.getDeliveryById(id);
    }

    @PostMapping
    public void creatDelivery(@RequestBody DeliveryRequestDTO deliveryRequestDTO) {
        deliveryService.creatDelivery(deliveryRequestDTO);
    }

    @PutMapping("{id}")
    public void updateDelivery(@PathVariable("id") int id, @RequestBody DeliveryUpdateRequestDTO deliveryUpdateRequestDTOl) {
        deliveryService.updateDelivery(id, deliveryUpdateRequestDTOl);
    }
}
