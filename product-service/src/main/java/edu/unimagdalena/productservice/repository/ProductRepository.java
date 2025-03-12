package edu.unimagdalena.productservice.repository;

import edu.unimagdalena.productservice.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

}
