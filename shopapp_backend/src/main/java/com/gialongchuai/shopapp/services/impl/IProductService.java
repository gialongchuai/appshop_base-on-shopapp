package com.gialongchuai.shopapp.services.impl;

import com.gialongchuai.shopapp.dtos.request.ProductCreationRequest;
import com.gialongchuai.shopapp.dtos.request.ProductUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.ProductResponse;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    ProductResponse create(ProductCreationRequest productCreationRequest) throws IOException;
    ProductResponse updateProduct(String productId, ProductUpdationRequest productUpdationRequest)
            throws IOException;
    List<ProductResponse> getAllProducts(String keyword, String categoryId, int page, int limit);
    ProductResponse getProduct(String productId);
    String deleteProduct(String productId);
}
