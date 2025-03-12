package com.gialongchuai.shopapp.controllers;

import com.gialongchuai.shopapp.services.impl.IProductImageService;
import org.springframework.web.bind.annotation.*;

import com.gialongchuai.shopapp.dtos.response.ApiResponse;
import com.gialongchuai.shopapp.services.ProductImageService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/products/images")
public class ProductImageController {
    IProductImageService iProductImageService;

    @DeleteMapping("/{productImageId}")
    ApiResponse<String> delete(@PathVariable String productImageId) {
        return ApiResponse.<String>builder()
                .result(iProductImageService.deleteProductImage(productImageId))
                .build();
    }
}
