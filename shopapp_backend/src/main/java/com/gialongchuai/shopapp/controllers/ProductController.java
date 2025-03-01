package com.gialongchuai.shopapp.controllers;

import com.gialongchuai.shopapp.dtos.request.ProductCreationRequest;
import com.gialongchuai.shopapp.dtos.request.ProductUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.ApiResponse;
import com.gialongchuai.shopapp.dtos.response.ProductResponse;
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
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    @PostMapping
    ApiResponse<ProductResponse> create(@ModelAttribute @Valid ProductCreationRequest productCreationRequest) throws IOException {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.create(productCreationRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<ProductResponse>> getAllProducts() {
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getAllProducts())
                .build();
    }

    @GetMapping("/{productId}")
    ApiResponse<ProductResponse> getProduct(@PathVariable String productId) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.getProduct(productId))
                .build();
    }

    @PutMapping("/{productId}")
    ApiResponse<ProductResponse> updateProduct(@PathVariable String productId,
            @ModelAttribute @Valid ProductUpdationRequest productUpdationRequest) throws IOException {
        log.info("==== controller update: {}", productId);
        log.info("==== controller update: {}", productUpdationRequest);
        return ApiResponse.<ProductResponse>builder()
                .result(productService.updateProduct(productId, productUpdationRequest))
                .build();
    }

    @DeleteMapping("/{productId}")
    ApiResponse<String> delete(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return ApiResponse.<String>builder()
                .result(productService.deleteProduct(productId))
                .build();
    }
}
