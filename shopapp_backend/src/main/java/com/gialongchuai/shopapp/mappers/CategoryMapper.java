package com.gialongchuai.shopapp.mappers;

import com.gialongchuai.shopapp.dtos.request.CategoryCreationRequest;
import com.gialongchuai.shopapp.dtos.request.CategoryUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.CategoryResponse;
import com.gialongchuai.shopapp.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryCreationRequest categoryCreationRequest);
    CategoryResponse toCategoryResponse(Category category);

    void updateCategory(@MappingTarget Category category, CategoryUpdationRequest categoryUpdationRequest);
}
