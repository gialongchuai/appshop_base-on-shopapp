package com.gialongchuai.shopapp.dtos.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImageResponse {
    String id;
    String imageUrl;
}
