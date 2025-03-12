package edu.unimagdalena.productservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

@Data
@ToString
@Document(collection = "product")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
}
