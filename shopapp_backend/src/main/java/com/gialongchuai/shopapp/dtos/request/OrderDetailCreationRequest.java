package com.gialongchuai.shopapp.dtos.request;

import com.gialongchuai.shopapp.entities.Order;
import com.gialongchuai.shopapp.entities.Product;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailCreationRequest {
    String productId;
    String orderId;
    Order order;
    Product product;
    BigDecimal price;
    Integer numberOfProducts;
    BigDecimal totalMoney;
    String color;
}
