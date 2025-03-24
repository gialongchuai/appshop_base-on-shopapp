package com.gialongchuai.shopapp.services.impl;

import com.gialongchuai.shopapp.dtos.request.ProductCreationRequest;
import com.gialongchuai.shopapp.dtos.request.ProductImageRequest;
import com.gialongchuai.shopapp.dtos.response.ProductImageResponse;
import com.gialongchuai.shopapp.dtos.response.ProductResponse;

public interface IProductImageService {
    String deleteProductImage(String productImageId);
}
