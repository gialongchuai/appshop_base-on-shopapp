package com.gialongchuai.shopapp.services.impl;

import com.gialongchuai.shopapp.dtos.request.CategoryCreationRequest;
import com.gialongchuai.shopapp.dtos.request.CategoryUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.CategoryResponse;

import java.util.List;

public interface ICategoryService {
    CategoryResponse create(CategoryCreationRequest categoryCreationRequest);
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategory(String categoryId);
    CategoryResponse updateCategory(String categoryId, CategoryUpdationRequest categoryUpdationRequest);
    String deleteCategory(String categoryId);
}
