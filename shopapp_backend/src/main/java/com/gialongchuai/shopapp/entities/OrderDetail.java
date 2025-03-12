package com.gialongchuai.shopapp.entities;

import java.math.BigDecimal;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name = "order_details")
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    Product product;

    @Column(name = "price", precision = 10, scale = 2)
    BigDecimal price;

    @Column(name = "number_of_products", columnDefinition = "INT DEFAULT 1")
    Integer numberOfProducts;

    @Column(name = "total_money", precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    BigDecimal totalMoney;

    @Column(name = "color", columnDefinition = "VARCHAR(20) COLLATE utf8mb4_general_ci DEFAULT ''")
    String color;
}
