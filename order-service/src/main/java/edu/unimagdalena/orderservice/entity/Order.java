package edu.unimagdalena.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private String id;

    private String productName;

    private Integer quantity;

    private BigDecimal price;

    @Column(name = "date", nullable = false, updatable = false)
    private LocalDateTime date;

    @PrePersist
    protected void onCreate(){
        this.date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }
}
