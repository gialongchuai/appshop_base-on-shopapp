package com.gialongchuai.shopapp.entities;

import com.gialongchuai.shopapp.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

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
    LocalDateTime orderDate;

    @Column(name = "status", columnDefinition = "ENUM('pending', 'processing', 'shipped', 'delivered', 'cancelled') DEFAULT 'pending'")
    OrderStatus status;

    @Column(name = "total_money", columnDefinition = "FLOAT")
    Float totalMoney;

    @Column(name = "shipping_method", columnDefinition = "VARCHAR(100) COLLATE utf8mb4_general_ci")
    String shippingMethod;

    @Column(name = "shipping_address", columnDefinition = "VARCHAR(200) COLLATE utf8mb4_general_ci")
    String shippingAddress;

    @Column(name = "shipping_date", columnDefinition = "DATE")
    LocalDateTime shippingDate;

    @Column(name = "tracking_number", columnDefinition = "VARCHAR(100) COLLATE utf8mb4_general_ci")
    String trackingNumber;

    @Column(name = "payment_method", columnDefinition = "VARCHAR(100) COLLATE utf8mb4_general_ci")
    String paymentMethod;

    @Column(name = "active", columnDefinition = "TINYINT(1)")
    Boolean active;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderDetail> orderDetails;
}
