package com.example.webflux.service;

import com.example.webflux.model.Product;
import com.example.webflux.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    /**
     * Get all products
     * Returns Flux<Product> - stream of products
     */
    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    /**
     * Get a single product by ID
     * Returns Mono<Product> - single product or empty
     */
    public Mono<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }
    
    /**
     * Create a new product
     */
    public Mono<Product> createProduct(Product product) {
        return productRepository.save(product);
    }
    
    /**
     * Update an existing product
     */
    public Mono<Product> updateProduct(Integer id, Product product) {
        return productRepository.update(id, product);
    }
    
    /**
     * Delete a product
     */
    public Mono<Void> deleteProduct(Integer id) {
        return productRepository.deleteById(id);
    }
}
