package com.gialongchuai.shopapp.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "orders")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "fullname", columnDefinition = "VARCHAR(100) COLLATE utf8mb4_general_ci DEFAULT ''")
    String fullname;

    @Column(name = "email", columnDefinition = "VARCHAR(100) COLLATE utf8mb4_general_ci DEFAULT ''")
    String email;

    @Column(name = "phone_number", nullable = false, columnDefinition = "VARCHAR(20) COLLATE utf8mb4_general_ci")
    String phoneNumber;

    @Column(name = "address", nullable = false, columnDefinition = "VARCHAR(200) COLLATE utf8mb4_general_ci")
    String address;

    @Column(name = "note", columnDefinition = "VARCHAR(100) COLLATE utf8mb4_general_ci DEFAULT ''")
    String note;

    @Column(name = "order_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    LocalDate orderDate;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(20) COLLATE utf8mb4_general_ci")
    String status;

    @Column(name = "total_money", columnDefinition = "FLOAT")
    Float totalMoney;

    @Column(name = "shipping_method", columnDefinition = "VARCHAR(100) COLLATE utf8mb4_general_ci")
    String shippingMethod;

    @Column(name = "shipping_address", columnDefinition = "VARCHAR(200) COLLATE utf8mb4_general_ci")
    String shippingAddress;

    @Column(name = "shipping_date", columnDefinition = "DATE")
    LocalDate shippingDate;

    @Column(name = "tracking_number", columnDefinition = "VARCHAR(100) COLLATE utf8mb4_general_ci")
    String trackingNumber;

    @Column(name = "payment_method", columnDefinition = "VARCHAR(100) COLLATE utf8mb4_general_ci")
    String paymentMethod;

    @Column(name = "active", columnDefinition = "TINYINT(1)")
    Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderDetail> orderDetails;
}
