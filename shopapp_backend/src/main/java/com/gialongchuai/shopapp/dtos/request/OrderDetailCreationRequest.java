package com.gialongchuai.shopapp.dtos.request;

import java.math.BigDecimal;

import jakarta.persistence.*;

import com.gialongchuai.shopapp.entities.Order;
import com.gialongchuai.shopapp.entities.Product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailCreationRequest {
    @NotBlank(message = "PRODUCT_IS_REQUIRED")
    String productId;
    @NotBlank(message = "ORDER_IS_REQUIRED")
    String orderId;

    @NotNull(message = "PRICE_IS_REQUIRED")
    @DecimalMin(value = "0.0", inclusive = true, message = "PRICE_INVALID")
    BigDecimal price;

    @NotNull(message = "NUMBER_OF_PRODUCTS_IS_REQUIRED")
    @Min(value = 1, message = "NUMBER_OF_PRODUCTS_INVALID")
    Integer numberOfProducts;

    @NotNull(message = "TOTAL_MONEY_IS_REQUIRED")
    @DecimalMin(value = "0.0", inclusive = true, message = "TOTAL_MONEY_INVALID")
    BigDecimal totalMoney;

    String color;
}
