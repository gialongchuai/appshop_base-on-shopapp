package com.gialongchuai.shopapp.controllers;

import com.gialongchuai.shopapp.dtos.request.ProductCreationRequest;
import com.gialongchuai.shopapp.dtos.request.ProductUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.ApiResponse;
import com.gialongchuai.shopapp.dtos.response.ProductResponse;
import com.gialongchuai.shopapp.services.ProductImageService;
import com.gialongchuai.shopapp.services.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/products/images")
public class ProductImageController {
    ProductImageService productImageService;

    @DeleteMapping("/{productImageId}")
    ApiResponse<String> delete(@PathVariable String productImageId) {
        return ApiResponse.<String>builder()
                .result(productImageService.deleteProductImage(productImageId))
                .build();
    }
}
