package com.gialongchuai.shopapp.controllers;

import com.gialongchuai.shopapp.dtos.request.CategoryCreationRequest;
import com.gialongchuai.shopapp.dtos.request.CategoryUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.ApiResponse;
import com.gialongchuai.shopapp.dtos.response.CategoryResponse;
import com.gialongchuai.shopapp.services.CategoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/categories")
public class CategoryController {
    CategoryService categoryService;

    @PostMapping
    ApiResponse<CategoryResponse> create(@RequestBody CategoryCreationRequest categoryCreationRequest) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.create(categoryCreationRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<CategoryResponse>> getAllCategory() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(categoryService.getAllCategories())
                .build();
    }

    @GetMapping("/{categoryId}")
    ApiResponse<CategoryResponse> getCategory(@PathVariable String categoryId) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.getCategory(categoryId))
                .build();
    }

    @PutMapping("/{categoryId}")
    ApiResponse<CategoryResponse> updateCategory(@PathVariable String categoryId,
                                @RequestBody @Valid CategoryUpdationRequest categoryUpdationRequest) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.updateCategory(categoryId, categoryUpdationRequest))
                .build();
    }

    @DeleteMapping("/{categoryId}")
    ApiResponse<String> delete(@PathVariable String categoryId) {
        categoryService.deleteCategory(categoryId);
        return ApiResponse.<String>builder()
                .result(categoryService.deleteCategory(categoryId))
                .build();
    }
}
