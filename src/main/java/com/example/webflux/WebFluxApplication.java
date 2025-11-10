package com.example.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebFluxApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WebFluxApplication.class, args);
        System.out.println("Spring WebFlux Application Started Successfully!");
        System.out.println("Access endpoints at: http://localhost:8080/api/products");
    }
}
