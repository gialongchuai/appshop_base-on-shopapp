package com.gialongchuai.shopapp.services;

import java.util.List;

import com.gialongchuai.shopapp.services.impl.ICategoryService;
import org.springframework.stereotype.Service;

import com.gialongchuai.shopapp.dtos.request.CategoryCreationRequest;
import com.gialongchuai.shopapp.dtos.request.CategoryUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.CategoryResponse;
import com.gialongchuai.shopapp.entities.Category;
import com.gialongchuai.shopapp.exceptions.CategoryErrorCode;
import com.gialongchuai.shopapp.exceptions.custom.AppException;
import com.gialongchuai.shopapp.mappers.CategoryMapper;
import com.gialongchuai.shopapp.repositories.CategoryRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService implements ICategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    @Override
    public CategoryResponse create(CategoryCreationRequest categoryCreationRequest) {
        return categoryMapper.toCategoryResponse(
                categoryRepository.save(categoryMapper.toCategory(categoryCreationRequest)));
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        var categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::toCategoryResponse).toList();
    }

    @Override
    public CategoryResponse getCategory(String categoryId) {
        return categoryMapper.toCategoryResponse(categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_EXISTED)));
    }

    @Override
    public CategoryResponse updateCategory(String categoryId, CategoryUpdationRequest categoryUpdationRequest) {
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_EXISTED));

        categoryMapper.updateCategory(category, categoryUpdationRequest);

        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public String deleteCategory(String categoryId) {
        categoryRepository.deleteById(categoryId);
        return "Delete category successfully!";
    }
}
