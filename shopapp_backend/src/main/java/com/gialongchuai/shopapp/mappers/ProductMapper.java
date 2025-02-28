package com.gialongchuai.shopapp.mappers;

import com.gialongchuai.shopapp.dtos.request.ProductCreationRequest;
import com.gialongchuai.shopapp.dtos.request.ProductUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.ProductResponse;
import com.gialongchuai.shopapp.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductCreationRequest productCreationRequest);
    ProductResponse toProductResponse(Product product);

    void updateProduct(@MappingTarget Product product, ProductUpdationRequest productUpdationRequest);
}
