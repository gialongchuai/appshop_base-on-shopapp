package com.gialongchuai.shopapp.dtos.request;

import com.gialongchuai.shopapp.entities.Role;
import jakarta.validation.constraints.NotBlank;
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
public class UserUpdationRequest {
    String fullname;

    @NotBlank(message = "PHONE_NUMBER_REQUIRED")
    String phoneNumber;

    String address;

    @NotBlank(message = "PASSWORD_REQUIRED")
    String password;

    LocalDate createdAt;
    LocalDate updatedAt;
    Boolean isActive;
    LocalDate dateOfBirth;
    Integer facebookAccountId;
    Integer googleAccountId;
    RoleUpdationRequest roleUpdationRequest;
}
