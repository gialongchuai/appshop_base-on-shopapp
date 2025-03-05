package com.gialongchuai.shopapp.dtos.request;

import com.gialongchuai.shopapp.entities.Order;
import com.gialongchuai.shopapp.entities.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailUpdationRequest {
    String productId;
    String orderId;
    Order order;
    Product product;
    BigDecimal price;
    Integer numberOfProducts;
    BigDecimal totalMoney;
    String color;
}
