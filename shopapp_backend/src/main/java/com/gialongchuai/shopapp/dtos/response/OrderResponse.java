package com.gialongchuai.shopapp.dtos.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    String id;
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
    //List<OrderDetail> orderDetails;
}
