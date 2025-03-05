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
public class ProductUpdationRequest {
    @NotBlank(message = "PRODUCT_IS_REQUIRED")
    String name;

    BigDecimal price;
    String description;
    MultipartFile thumbnail;
    LocalDate createdAt;

    @NotNull(message = "TIME_UPDATE_IS_REQUIRED")
    LocalDate updatedAt;

    List<MultipartFile> images;
    String categoryId;
    //CategoryCreationRequest categoryCreationRequest;
    //List<OrderDetail> orderDetails;
}
