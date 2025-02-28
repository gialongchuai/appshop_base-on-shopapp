package com.gialongchuai.shopapp.services;

import com.gialongchuai.shopapp.dtos.request.CategoryCreationRequest;
import com.gialongchuai.shopapp.dtos.request.CategoryUpdationRequest;
import com.gialongchuai.shopapp.dtos.request.ProductCreationRequest;
import com.gialongchuai.shopapp.dtos.request.ProductUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.CategoryResponse;
import com.gialongchuai.shopapp.dtos.response.ProductResponse;
import com.gialongchuai.shopapp.entities.Category;
import com.gialongchuai.shopapp.entities.Product;
import com.gialongchuai.shopapp.exceptions.CategoryErrorCode;
import com.gialongchuai.shopapp.exceptions.ProductErrorCode;
import com.gialongchuai.shopapp.exceptions.custom.AppException;
import com.gialongchuai.shopapp.mappers.ProductMapper;
import com.gialongchuai.shopapp.repositories.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;

    public ProductResponse create(ProductCreationRequest productCreationRequest) {
        log.info("====== created at: {}", productCreationRequest.getCreatedAt());

        var product = productMapper.toProduct(productCreationRequest);

        log.info("====== created at: {}", product.getCreatedAt());

        return productMapper.toProductResponse(productRepository
                .save(product));
    }

    public List<ProductResponse> getAllProducts() {
        var products = productRepository.findAll();
        return products.stream().map(productMapper::toProductResponse).toList();
    }

    public ProductResponse getProduct(String productId) {
        return productMapper.toProductResponse(
                productRepository.findById(productId).orElseThrow(()
                        -> new AppException(ProductErrorCode.PRODUCT_NOT_EXISTED)));
    }

    public ProductResponse updateProduct(String productId, ProductUpdationRequest productUpdationRequest) {
        Product product = productRepository.findById(productId).orElseThrow(()
                -> new AppException(ProductErrorCode.PRODUCT_NOT_EXISTED));

        productMapper.updateProduct(product, productUpdationRequest);

        return productMapper.toProductResponse(productRepository.save(product));
    }

    public String deleteProduct(String productId) {
        productRepository.deleteById(productId);
        return "Delete product successfully!";
    }
}
