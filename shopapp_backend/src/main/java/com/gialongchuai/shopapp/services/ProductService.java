package com.gialongchuai.shopapp.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.gialongchuai.shopapp.services.impl.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gialongchuai.shopapp.constants.UploadFileConstant;
import com.gialongchuai.shopapp.dtos.request.ProductCreationRequest;
import com.gialongchuai.shopapp.dtos.request.ProductUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.ProductResponse;
import com.gialongchuai.shopapp.entities.Category;
import com.gialongchuai.shopapp.entities.Product;
import com.gialongchuai.shopapp.entities.ProductImage;
import com.gialongchuai.shopapp.exceptions.CategoryErrorCode;
import com.gialongchuai.shopapp.exceptions.ProductErrorCode;
import com.gialongchuai.shopapp.exceptions.UploadFileErrorCode;
import com.gialongchuai.shopapp.exceptions.custom.AppException;
import com.gialongchuai.shopapp.mappers.ProductMapper;
import com.gialongchuai.shopapp.repositories.CategoryRepository;
import com.gialongchuai.shopapp.repositories.ProductRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductService implements IProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;
    CategoryRepository categoryRepository;

    @Override
    public ProductResponse create(ProductCreationRequest productCreationRequest) throws IOException {
        Category category = categoryRepository
                    .findById(productCreationRequest.getCategoryId())
                    .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_EXISTED));

        String thumbnailUrl = saveImage(productCreationRequest.getThumbnail());
        List<String> imageUrls = new ArrayList<>();
        if (productCreationRequest.getImages() != null
                && !productCreationRequest.getImages().isEmpty()) {
            for (MultipartFile image : productCreationRequest.getImages()) {
                String imageUrl = saveImage(image);
                imageUrls.add(imageUrl);
            }
        } else {
            throw new AppException(UploadFileErrorCode.EMPTY_FILE);
        }

        var product = productMapper.toProduct(productCreationRequest);
        product.setCategory(category);
        product.setThumbnail(thumbnailUrl);

        List<ProductImage> productImages = new ArrayList<>();
        for (String imageUrl : imageUrls) {
            ProductImage productImage =
                    ProductImage.builder().product(product).imageUrl(imageUrl).build();
            productImages.add(productImage);
        }
        product.setImages(productImages);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    private String saveImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new AppException(UploadFileErrorCode.EMPTY_FILE);
        }

        if (file.getSize() > UploadFileConstant.MAX_FILE_SIZE) {
            throw new AppException(UploadFileErrorCode.FILE_TOO_LARGE);
        }

        if (!isAllowedImageType(file.getContentType())) {
            throw new AppException(UploadFileErrorCode.INVALID_FILE_FORMAT);
        }

        // Create folder is not exist
        File uploadDir = new File(UploadFileConstant.UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Change name file
        String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path path = Paths.get(UploadFileConstant.UPLOAD_DIR + uniqueFileName);

        Files.copy(file.getInputStream(), path);
        return uniqueFileName;
    }

    private boolean isAllowedImageType(String contentType) {
        for (String type : UploadFileConstant.ALLOWED_IMAGE_TYPES) {
            if (type.equalsIgnoreCase(contentType)) {
                return true;
            }
        }
        return false;
    }

    // chat
    @Override
    public ProductResponse updateProduct(String productId, ProductUpdationRequest productUpdationRequest)
            throws IOException {
        Category category = categoryRepository
                .findById(productUpdationRequest.getCategoryId())
                .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_EXISTED));

        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new AppException(ProductErrorCode.PRODUCT_NOT_EXISTED));

        // Map tất cả các trường trừ thumbnail và images
        productMapper.updateProduct(product, productUpdationRequest);

        // Xử lý thumbnail
        if (productUpdationRequest.getThumbnail() != null
                && !productUpdationRequest.getThumbnail().isEmpty()) {
            String thumbnailUrl = saveImage(productUpdationRequest.getThumbnail());
            product.setThumbnail(thumbnailUrl);
        }

        // Xử lý images
        List<ProductImage> existingImages = product.getImages(); // Lấy danh sách ảnh hiện có
        if (productUpdationRequest.getImages() != null
                && !productUpdationRequest.getImages().isEmpty()) {
            for (MultipartFile image : productUpdationRequest.getImages()) {
                String imageUrl = saveImage(image); // Lưu ảnh mới
                ProductImage productImage = ProductImage.builder()
                        .product(product)
                        .imageUrl(imageUrl)
                        .build();
                existingImages.add(productImage); // Thêm ảnh mới vào danh sách hiện có
            }
        }

        // Cập nhật danh sách ảnh cho sản phẩm
        product.setImages(existingImages);
        product.setCategory(category);
        // Lưu sản phẩm đã cập nhật
        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        var products = productRepository.findAll();
        return products.stream().map(productMapper::toProductResponse).toList();
    }

    @Override
    public ProductResponse getProduct(String productId) {
        return productMapper.toProductResponse(productRepository
                .findById(productId)
                .orElseThrow(() -> new AppException(ProductErrorCode.PRODUCT_NOT_EXISTED)));
    }

    @Override
    public String deleteProduct(String productId) {
        productRepository.deleteById(productId);
        return "Delete product successfully!";
    }
}
