package com.gialongchuai.shopapp.dtos.response;

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
public class UserResponse {
    String id;
    String fullname;
    String phoneNumber;
    String address;
    String password;
    LocalDate createdAt;
    LocalDate updatedAt;
    Boolean isActive;
    LocalDate dateOfBirth;
    Integer facebookAccountId;
    Integer googleAccountId;
    RoleResponse roleResponse;
}
