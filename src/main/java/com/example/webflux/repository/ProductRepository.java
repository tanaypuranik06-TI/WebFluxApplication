package com.example.webflux.repository;

import com.example.webflux.model.Product;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository {
    
    // In-memory data store
    private static final Map<Integer, Product> productMap = new HashMap<>();
    
    static {
        // Initialize with sample data
        productMap.put(1, new Product(1, "Laptop", 999.99));
        productMap.put(2, new Product(2, "Smartphone", 699.99));
        productMap.put(3, new Product(3, "Tablet", 499.99));
        productMap.put(4, new Product(4, "Headphones", 199.99));
        productMap.put(5, new Product(5, "Smartwatch", 299.99));
    }
    
    /**
     * Find all products - Returns Flux
     * Flux represents 0 to N elements
     */
    public Flux<Product> findAll() {
        return Flux.fromIterable(productMap.values())
                .delayElements(Duration.ofMillis(100)); // Simulate async behavior
    }
    
    /**
     * Find product by ID - Returns Mono
     * Mono represents 0 to 1 element
     */
    public Mono<Product> findById(Integer id) {
        return Mono.justOrEmpty(productMap.get(id))
                .delayElement(Duration.ofMillis(100)); // Simulate async behavior
    }
    
    /**
     * Save a new product
     */
    public Mono<Product> save(Product product) {
        if (product.getId() == null) {
            product.setId(productMap.size() + 1);
        }
        productMap.put(product.getId(), product);
        return Mono.just(product)
                .delayElement(Duration.ofMillis(100));
    }
    
    /**
     * Update an existing product
     */
    public Mono<Product> update(Integer id, Product product) {
        return findById(id)
                .flatMap(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setPrice(product.getPrice());
                    productMap.put(id, existingProduct);
                    return Mono.just(existingProduct);
                })
                .delayElement(Duration.ofMillis(100));
    }
    
    /**
     * Delete a product by ID
     */
    public Mono<Void> deleteById(Integer id) {
        return Mono.fromRunnable(() -> productMap.remove(id))
                .then()
                .delayElement(Duration.ofMillis(100));
    }
}
