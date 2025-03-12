package com.gialongchuai.shopapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import com.gialongchuai.shopapp.exceptions.custom.BaseErrorCode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ProductErrorCode implements BaseErrorCode {
    PRODUCT_IS_REQUIRED(3001, "Product name is required!", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_EXISTED(3002, "Product not exist!", HttpStatus.NOT_FOUND),
    TIME_CREATE_IS_REQUIRED(3003, "Time create product is required!", HttpStatus.BAD_REQUEST),
    TIME_UPDATE_IS_REQUIRED(3004, "Time update product is required!", HttpStatus.BAD_REQUEST),
    THUMBNAIL_IS_REQUIRED(3005, "Thumbnail is required!", HttpStatus.BAD_REQUEST),
    IMAGE_IS_REQUIRED(3006, "Image is required!", HttpStatus.BAD_REQUEST);

    int code;
    String message;
    HttpStatusCode httpStatusCode;
}
