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
public enum OrderErrorCode implements BaseErrorCode {
    USER_ORDER_NOT_EXISTED(5002, "User orders not exist!", HttpStatus.NOT_FOUND),
    ORDER_NOT_EXISTED(5002, "Order not exist!", HttpStatus.NOT_FOUND);

    int code;
    String message;
    HttpStatusCode httpStatusCode;
}
