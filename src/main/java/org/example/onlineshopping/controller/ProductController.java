package org.example.onlineshopping.controller;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopping.dto.request.ProductRequestDTO;
import org.example.onlineshopping.dto.response.ProductResponseDTO;
import org.example.onlineshopping.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductResponseDTO> getAllProduct() {
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public ProductResponseDTO getProductById(@PathVariable("id") int id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public void createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        productService.createProduct(productRequestDTO);
    }

    @PatchMapping("{id}")
    public void updateProduct(@PathVariable("id") int id, @RequestBody ProductRequestDTO productRequestDTO) {
        productService.updateProduct(id, productRequestDTO);
    }
}



