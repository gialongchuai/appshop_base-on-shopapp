package com.gialongchuai.shopapp.exceptions;

import org.springframework.http.HttpStatusCode;

public interface BaseErrorCode {
    int getCode();
    String getMessage();
    HttpStatusCode getHttpStatusCode();
}
