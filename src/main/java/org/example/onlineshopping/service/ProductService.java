package org.example.onlineshopping.service;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopping.dto.request.ProductRequestDTO;
import org.example.onlineshopping.dto.response.ProductResponseDTO;
import org.example.onlineshopping.entity.Product;
import org.example.onlineshopping.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductResponseDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductResponseDTO::new).toList();
    }

    public ProductResponseDTO getProductById(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return new ProductResponseDTO(productOptional.get());
        } else {
            throw new IllegalArgumentException("Product not found with id: " + id);
        }
    }

    public void createProduct(ProductRequestDTO productRequestDTO) {
        Product product = new Product(productRequestDTO);
        productRepository.save(product);
    }

    public void updateProduct(int id, ProductRequestDTO productRequestDTO) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (productRequestDTO.getName() != null) {
                product.setName(productRequestDTO.getName());
            }
            if (productRequestDTO.getPrice() != 0) {
                product.setPrice(productRequestDTO.getPrice());
            }
            productRepository.save(product);
        } else {
            throw new IllegalArgumentException("Product not found with id: " + id);
        }
    }
}
