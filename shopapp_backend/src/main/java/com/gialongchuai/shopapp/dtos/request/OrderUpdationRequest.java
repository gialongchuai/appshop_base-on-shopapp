package com.gialongchuai.shopapp.dtos.request;

import java.time.LocalDate;

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
    String fullname;
    String email;
    String phoneNumber;
    String address;
    String note;
    LocalDate orderDate;
    String status;
    Float totalMoney;
    String shippingMethod;
    String shippingAddress;
    LocalDate shippingDate;
    String trackingNumber;
    String paymentMethod;
    Boolean active;
    String userId;
    // List<OrderDetail> orderDetails;
}
