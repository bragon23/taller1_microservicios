package edu.unimagdalena.productservice.services;

import edu.unimagdalena.productservice.entity.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IServicesProduct {
    Mono<Product>   save(Product product);

    Flux<Product> findAll();

    Mono<Product> findById(String  id);

    Mono<Void> deleteById(String id);
}

