package com.gialongchuai.shopapp.dtos.request;

import java.time.LocalDate;

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
public class OrderUpdationRequest {
    @NotBlank(message = "ORDER_NAME_IS_REQUIRED")
    String fullname;

    String email;

    @NotBlank(message = "PHONE_NUMBER_IS_REQUIRED")
    String phoneNumber;

    @NotBlank(message = "ADDRESS_IS_REQUIRED")
    String address;

    String note;
    LocalDate orderDate;

    @NotNull(message = "TOTAL_MONEY_IS_REQUIRED")
    @Min(value = 0, message = "TOTAL_MONEY_INVALID")
    Float totalMoney;

    @NotBlank(message = "STATUS_IS_REQUIRED")
    String status;

    String shippingMethod;
    String shippingAddress;
    LocalDate shippingDate;
    String trackingNumber;
    String paymentMethod;
    Boolean active;

    @NotBlank(message = "USER_IS_REQUIRED")
    String userId;
    // List<OrderDetail> orderDetails;
}
