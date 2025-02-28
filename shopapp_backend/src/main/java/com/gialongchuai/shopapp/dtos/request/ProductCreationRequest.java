package com.gialongchuai.shopapp.dtos.request;

import jakarta.validation.constraints.NotBlank;
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
public class ProductCreationRequest {
    @NotBlank(message = "PRODUCT_IS_REQUIRED")
    String name;
    BigDecimal price;
    String thumbnail;
    String description;
    LocalDate createdAt;
    LocalDate updatedAt;
    //CategoryCreationRequest categoryCreationRequest;
    //List<ProductImage> images;
    //List<OrderDetail> orderDetails;
}
