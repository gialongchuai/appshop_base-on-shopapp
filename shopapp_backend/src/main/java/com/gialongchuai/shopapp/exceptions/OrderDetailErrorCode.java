package com.gialongchuai.shopapp.exceptions;

import com.gialongchuai.shopapp.exceptions.custom.BaseErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum OrderDetailErrorCode implements BaseErrorCode {
    ORDER_NOT_EXISTED(6002, "Order not exist!", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_EXISTED(6002, "Product not exist!", HttpStatus.NOT_FOUND),
    ORDER_DETAIL_NOT_EXISTED(6003, "Order detail not exist!", HttpStatus.NOT_FOUND);

    int code;
    String message;
    HttpStatusCode httpStatusCode;
}
