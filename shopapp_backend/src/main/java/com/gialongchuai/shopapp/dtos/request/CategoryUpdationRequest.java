package com.gialongchuai.shopapp.dtos.request;

import jakarta.validation.constraints.NotBlank;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryUpdationRequest {
    @NotBlank(message = "CATEGORY_IS_REQUIRED")
    String name;
}
