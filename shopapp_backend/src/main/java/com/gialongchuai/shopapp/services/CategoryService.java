package com.gialongchuai.shopapp.services;

import com.gialongchuai.shopapp.dtos.request.CategoryCreationRequest;
import com.gialongchuai.shopapp.dtos.response.CategoryResponse;
import com.gialongchuai.shopapp.mappers.CategoryMapper;
import com.gialongchuai.shopapp.repositories.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

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
}
