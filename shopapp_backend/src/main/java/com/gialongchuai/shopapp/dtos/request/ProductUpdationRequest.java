package com.gialongchuai.shopapp.dtos.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdationRequest {
    @NotBlank(message = "PRODUCT_IS_REQUIRED")
    String name;

    @NotNull(message = "PRICE_IS_REQUIRED")
    @DecimalMin(value = "0.0", inclusive = true, message = "PRICE_INVALID")
    BigDecimal price;
    String description;
    MultipartFile thumbnail;
    LocalDate createdAt;

    @NotNull(message = "TIME_UPDATE_IS_REQUIRED")
    LocalDate updatedAt;

    List<MultipartFile> images;

    @NotBlank(message = "CATEGORY_IS_REQUIRED")
    String categoryId;
    // CategoryCreationRequest categoryCreationRequest;
    // List<OrderDetail> orderDetails;
}
