package com.gialongchuai.shopapp.services;

import com.gialongchuai.shopapp.constants.UploadFileConstant;
import com.gialongchuai.shopapp.dtos.request.CategoryCreationRequest;
import com.gialongchuai.shopapp.dtos.request.CategoryUpdationRequest;
import com.gialongchuai.shopapp.dtos.request.ProductCreationRequest;
import com.gialongchuai.shopapp.dtos.request.ProductUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.CategoryResponse;
import com.gialongchuai.shopapp.dtos.response.ProductResponse;
import com.gialongchuai.shopapp.entities.Category;
import com.gialongchuai.shopapp.entities.Product;
import com.gialongchuai.shopapp.entities.ProductImage;
import com.gialongchuai.shopapp.exceptions.CategoryErrorCode;
import com.gialongchuai.shopapp.exceptions.ProductErrorCode;
import com.gialongchuai.shopapp.exceptions.UploadFileErrorCode;
import com.gialongchuai.shopapp.exceptions.custom.AppException;
import com.gialongchuai.shopapp.mappers.ProductMapper;
import com.gialongchuai.shopapp.repositories.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;

    public ProductResponse create(ProductCreationRequest productCreationRequest) throws IOException {
        log.info("====== created at: {}", productCreationRequest.getCreatedAt());
        String thumbnailUrl = saveImage(productCreationRequest.getThumbnail());

        log.info("===== thumbnailUrl: " + thumbnailUrl);

        List<String> imageUrls = new ArrayList<>();
        if (productCreationRequest.getImages() != null && !productCreationRequest.getImages().isEmpty()) {
            for (MultipartFile image : productCreationRequest.getImages()) {
                String imageUrl = saveImage(image);
                imageUrls.add(imageUrl);
            }
        }

        var product = productMapper.toProduct(productCreationRequest);
        product.setThumbnail(thumbnailUrl);

        List<ProductImage> productImages = new ArrayList<>();
        for (String imageUrl : imageUrls) {
            ProductImage productImage = ProductImage.builder()
                    .product(product)
                    .imageUrl(imageUrl)
                    .build();
            productImages.add(productImage);
        }
        product.setImages(productImages);

        log.info("====== created at: {}", product.getCreatedAt());

        return productMapper.toProductResponse(productRepository.save(product));
    }

    private String saveImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new AppException(UploadFileErrorCode.EMPTY_FILE);
        }

        if(file.getSize() > UploadFileConstant.MAX_FILE_SIZE) {
            throw new AppException(UploadFileErrorCode.FILE_TOO_LARGE);
        }

        if(!isAllowedImageType(file.getContentType())) {
            throw new AppException(UploadFileErrorCode.INVALID_FILE_FORMAT);
        }

        // Create folder is not exist
        File uploadDir = new File(UploadFileConstant.UPLOAD_DIR);
        if(!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Change name file
        String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path path = Paths.get(UploadFileConstant.UPLOAD_DIR + uniqueFileName);

        Files.copy(file.getInputStream(), path);
        return uniqueFileName;
    }

    private boolean isAllowedImageType(String contentType) {
        for(String type : UploadFileConstant.ALLOWED_IMAGE_TYPES) {
            if(type.equalsIgnoreCase(contentType)) {
                return true;
            }
        }
        return false;
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
