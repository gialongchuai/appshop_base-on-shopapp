package com.gialongchuai.shopapp.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "products")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "name", columnDefinition = "VARCHAR(350) COLLATE utf8mb4_general_ci")
    String name;

    @Column(name = "price", precision = 10, scale = 2)
    BigDecimal price;

    @Column(name = "thumbnail", columnDefinition = "VARCHAR(255)")
    String thumbnail;

    @Column(name = "description", columnDefinition = "LONGTEXT COLLATE utf8mb4_general_ci")
    String description;

    @Column(name = "created_at")
    LocalDate createdAt;

    @Column(name = "updated_at")
    LocalDate updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductImage> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderDetail> orderDetails;
}
