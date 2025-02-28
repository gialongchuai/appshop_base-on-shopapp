package com.gialongchuai.shopapp.mappers;

import com.gialongchuai.shopapp.dtos.request.ProductCreationRequest;
import com.gialongchuai.shopapp.dtos.request.ProductUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.ProductResponse;
import com.gialongchuai.shopapp.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "thumbnail", ignore = true)
    @Mapping(target = "images", ignore = true)
    Product toProduct(ProductCreationRequest productCreationRequest);
    ProductResponse toProductResponse(Product product);

    @Mapping(target = "thumbnail", ignore = true)
    @Mapping(target = "images", ignore = true)
    void updateProduct(@MappingTarget Product product, ProductUpdationRequest productUpdationRequest);
}
