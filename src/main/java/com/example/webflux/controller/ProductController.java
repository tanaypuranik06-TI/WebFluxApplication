package com.example.webflux.controller;

import com.example.webflux.model.Product;
import com.example.webflux.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Product> getAllProducts() {
        System.out.println("GET /api/products called");  // Debug line
        return productService.getAllProducts();
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Product> getProductById(@PathVariable Integer id) {
        System.out.println("GET /api/products/" + id + " called");  // Debug line
        return productService.getProductById(id)
                .switchIfEmpty(Mono.error(
                    new RuntimeException("Product not found with id: " + id)));
    }
}
