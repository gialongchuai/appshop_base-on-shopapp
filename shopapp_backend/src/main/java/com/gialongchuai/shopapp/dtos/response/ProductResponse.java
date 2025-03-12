package com.gialongchuai.shopapp.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    String id;
    String name;
    BigDecimal price;
    String thumbnail;
    String description;
    LocalDate createdAt;
    LocalDate updatedAt;
    List<ProductImageResponse> images;
    CategoryResponse categoryResponse;
    // List<OrderDetail> orderDetails;
}
