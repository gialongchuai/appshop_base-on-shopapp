package com.gialongchuai.shopapp.services;

import com.gialongchuai.shopapp.repositories.ProductImageRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductImageService {
    ProductImageRepository productImageRepository;

    public String deleteProductImage(String productImageId) {
        productImageRepository.deleteById(productImageId);
        return "Delete product image successfully!";
    }   
}
