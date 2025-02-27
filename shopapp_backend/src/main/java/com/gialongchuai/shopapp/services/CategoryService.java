package com.gialongchuai.shopapp.services;

import com.gialongchuai.shopapp.dtos.request.CategoryCreationRequest;
import com.gialongchuai.shopapp.dtos.request.CategoryUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.ApiResponse;
import com.gialongchuai.shopapp.dtos.response.CategoryResponse;
import com.gialongchuai.shopapp.entities.Category;
import com.gialongchuai.shopapp.exceptions.AppException;
import com.gialongchuai.shopapp.exceptions.CategoryErrorCode;
import com.gialongchuai.shopapp.mappers.CategoryMapper;
import com.gialongchuai.shopapp.repositories.CategoryRepository;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    public CategoryResponse create(CategoryCreationRequest categoryCreationRequest) {
        return categoryMapper.toCategoryResponse(categoryRepository
                .save(categoryMapper.toCategory(categoryCreationRequest)));
    }

    public List<CategoryResponse> getAllCategories() {
        var categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::toCategoryResponse).toList();
    }

    public CategoryResponse getCategory(String categoryId) {
        return categoryMapper.toCategoryResponse(
                categoryRepository.findById(categoryId).orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_EXISTED)));
    }

    public CategoryResponse updateCategory(String categoryId, CategoryUpdationRequest categoryUpdationRequest) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_EXISTED));

        categoryMapper.updateCategory(category, categoryUpdationRequest);

        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public String deleteCategory(String categoryId) {
        categoryRepository.deleteById(categoryId);
        return "Delete category successfully!";
    }
}
