package com.gialongchuai.shopapp.dtos.response;

import com.gialongchuai.shopapp.entities.Category;
import com.gialongchuai.shopapp.entities.OrderDetail;
import com.gialongchuai.shopapp.entities.ProductImage;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
public class ProductResponse {
    String id;
    String name;
    BigDecimal price;
    String thumbnail;
    String description;
    LocalDate createdAt;
    LocalDate updatedAt;
    Category category;
    List<ProductImage> images;
    List<OrderDetail> orderDetails;
}
