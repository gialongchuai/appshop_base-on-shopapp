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
public enum SecurityErrorCode implements BaseErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error!", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(9001, "Unauthenticated!", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(9002, "You don't have permission!", HttpStatus.FORBIDDEN);

    int code;
    String message;
    HttpStatusCode httpStatusCode;
}
