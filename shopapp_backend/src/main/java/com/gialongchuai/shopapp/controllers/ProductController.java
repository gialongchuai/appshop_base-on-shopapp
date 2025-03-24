package com.gialongchuai.shopapp.controllers;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import com.gialongchuai.shopapp.services.impl.IProductService;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.gialongchuai.shopapp.dtos.request.ProductCreationRequest;
import com.gialongchuai.shopapp.dtos.request.ProductUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.ApiResponse;
import com.gialongchuai.shopapp.dtos.response.ProductResponse;
import com.gialongchuai.shopapp.services.ProductService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/products")
public class ProductController {
    IProductService iProductService;

    @PostMapping
    ApiResponse<ProductResponse> create(@ModelAttribute @Valid ProductCreationRequest productCreationRequest)
            throws IOException {
        return ApiResponse.<ProductResponse>builder()
                .result(iProductService.create(productCreationRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<ProductResponse>> getAllProducts(
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        List<ProductResponse> productPage = iProductService.getAllProducts(keyword, categoryId, page, limit);

        return ApiResponse.<List<ProductResponse>>builder()
                .result(productPage)
                .build();
    }

    @GetMapping("/{productId}")
    ApiResponse<ProductResponse> getProduct(@PathVariable String productId) {
        return ApiResponse.<ProductResponse>builder()
                .result(iProductService.getProduct(productId))
                .build();
    }

    @PutMapping("/{productId}")
    ApiResponse<ProductResponse> updateProduct(
            @PathVariable String productId, @ModelAttribute @Valid ProductUpdationRequest productUpdationRequest)
            throws IOException {
        return ApiResponse.<ProductResponse>builder()
                .result(iProductService.updateProduct(productId, productUpdationRequest))
                .build();
    }

    @DeleteMapping("/{productId}")
    ApiResponse<String> delete(@PathVariable String productId) {
        return ApiResponse.<String>builder()
                .result(iProductService.deleteProduct(productId))
                .build();
    }
}
