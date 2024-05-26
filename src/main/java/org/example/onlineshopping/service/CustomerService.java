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

    public List<CustomerResponseDTO> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(CustomerResponseDTO::new).toList();
    }

    public CustomerResponseDTO getCustomerById(int id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            return new CustomerResponseDTO(customerOptional.get());
        } else {
            throw new IllegalArgumentException("customer not found with id: " + id);
        }
    }

    public void createCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = new Customer(customerRequestDTO);
        customerRepository.save(customer);
    }
}
