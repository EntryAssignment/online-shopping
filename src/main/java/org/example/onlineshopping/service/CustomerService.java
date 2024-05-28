package org.example.onlineshopping.service;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopping.dto.request.CustomerRequestDTO;
import org.example.onlineshopping.dto.response.CustomerResponseDTO;
import org.example.onlineshopping.entity.Customer;
import org.example.onlineshopping.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<CustomerResponseDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream().map(customer -> CustomerResponseDTO.builder()
                .id(customer.getId())
                .name(customer.getName()).build()).toList();
    }

    public CustomerResponseDTO getCustomerById(int id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isEmpty()) {
            throw new IllegalArgumentException("Customer not found with id: " + id);
        }

        return CustomerResponseDTO.builder()
                .id(customerOptional.get().getId())
                .name(customerOptional.get().getName()).build();
    }

    public void createCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = Customer.builder()
                .name(customerRequestDTO.getName()).build();

        customerRepository.save(customer);
    }
}
