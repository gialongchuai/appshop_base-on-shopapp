package com.gialongchuai.shopapp.services;

import com.gialongchuai.shopapp.dtos.request.ProductImageRequest;
import com.gialongchuai.shopapp.dtos.response.ProductImageResponse;
import com.gialongchuai.shopapp.services.impl.IProductImageService;
import org.springframework.stereotype.Service;

import com.gialongchuai.shopapp.repositories.ProductImageRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductImageService implements IProductImageService {
    ProductImageRepository productImageRepository;

    @Override
    public String deleteProductImage(String productImageId) {
        productImageRepository.deleteById(productImageId);
        return "Delete product image successfully!";
    }
}
