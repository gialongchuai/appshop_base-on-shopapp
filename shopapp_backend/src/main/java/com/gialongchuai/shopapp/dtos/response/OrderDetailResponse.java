package com.gialongchuai.shopapp.dtos.response;

import java.math.BigDecimal;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
