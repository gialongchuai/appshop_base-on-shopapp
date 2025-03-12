package com.gialongchuai.shopapp.controllers;

import java.util.List;

import com.gialongchuai.shopapp.services.impl.ICategoryService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.gialongchuai.shopapp.dtos.request.CategoryCreationRequest;
import com.gialongchuai.shopapp.dtos.request.CategoryUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.ApiResponse;
import com.gialongchuai.shopapp.dtos.response.CategoryResponse;
import com.gialongchuai.shopapp.services.CategoryService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/categories")
public class CategoryController {
    ICategoryService iCategoryService;

    @PostMapping
    ApiResponse<CategoryResponse> create(@RequestBody CategoryCreationRequest categoryCreationRequest) {
        return ApiResponse.<CategoryResponse>builder()
                .result(iCategoryService.create(categoryCreationRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<CategoryResponse>> getAllCategory() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(iCategoryService.getAllCategories())
                .build();
    }

    @GetMapping("/{categoryId}")
    ApiResponse<CategoryResponse> getCategory(@PathVariable String categoryId) {
        return ApiResponse.<CategoryResponse>builder()
                .result(iCategoryService.getCategory(categoryId))
                .build();
    }

    @PutMapping("/{categoryId}")
    ApiResponse<CategoryResponse> updateCategory(
            @PathVariable String categoryId, @RequestBody @Valid CategoryUpdationRequest categoryUpdationRequest) {
        return ApiResponse.<CategoryResponse>builder()
                .result(iCategoryService.updateCategory(categoryId, categoryUpdationRequest))
                .build();
    }

    @DeleteMapping("/{categoryId}")
    ApiResponse<String> delete(@PathVariable String categoryId) {
        return ApiResponse.<String>builder()
                .result(iCategoryService.deleteCategory(categoryId))
                .build();
    }
}
