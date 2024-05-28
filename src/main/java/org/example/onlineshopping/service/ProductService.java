package org.example.onlineshopping.service;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopping.dto.request.ProductRequestDTO;
import org.example.onlineshopping.dto.response.ProductResponseDTO;
import org.example.onlineshopping.entity.Product;
import org.example.onlineshopping.exception.UserNotFoundException;
import org.example.onlineshopping.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice()).build()).toList();
    }

    public ProductResponseDTO getProductById(int id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            throw new UserNotFoundException("Product not found with id: " + id);
        }

        return ProductResponseDTO.builder()
                .id(productOptional.get().getId())
                .name(productOptional.get().getName())
                .price(productOptional.get().getPrice()).build();
    }

    public void createProduct(ProductRequestDTO productRequestDTO) {
        Product product = Product.builder()
                .name(productRequestDTO.getName())
                .price(productRequestDTO.getPrice()).build();

        productRepository.save(product);
    }

    public void updateProduct(int id, ProductRequestDTO productRequestDTO) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new UserNotFoundException("Product not found with id: " + id);
        }

        Product product = optionalProduct.get();

        if (productRequestDTO.getName() != null) {
            product.setName(productRequestDTO.getName());
        }
        if (productRequestDTO.getPrice() != 0) {
            product.setPrice(productRequestDTO.getPrice());
        }

        productRepository.save(product);
    }
}
