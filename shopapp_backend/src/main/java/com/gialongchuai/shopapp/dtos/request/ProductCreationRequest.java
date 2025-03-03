package com.gialongchuai.shopapp.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
    String description;
    MultipartFile thumbnail;

    @NotNull(message = "TIME_CREATE_IS_REQUIRED")
    LocalDate createdAt;

    LocalDate updatedAt;
    List<MultipartFile> images;
    //CategoryCreationRequest categoryCreationRequest;
    //List<OrderDetail> orderDetails;
}
