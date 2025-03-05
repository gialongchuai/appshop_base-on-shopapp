package com.gialongchuai.shopapp.dtos.response;

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
public class OrderDetailResponse {
    String id;
    BigDecimal price;
    Integer numberOfProducts;
    BigDecimal totalMoney;
    String color;
    OrderResponse orderResponse;
    ProductResponse productResponse;
}
