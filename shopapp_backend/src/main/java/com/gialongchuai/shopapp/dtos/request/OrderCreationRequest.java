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
public class OrderCreationRequest {
    String fullname;
    String email;
    String phoneNumber;
    String address;
    String note;
    LocalDate orderDate;
    Float totalMoney;
    String status;
    String shippingMethod;
    String shippingAddress;
    LocalDate shippingDate;
    String trackingNumber;
    String paymentMethod;
    Boolean active;
    String userId;
    // List<OrderDetail> orderDetails;
}
