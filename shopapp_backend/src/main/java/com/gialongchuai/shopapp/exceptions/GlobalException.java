package com.gialongchuai.shopapp.exceptions;

import com.gialongchuai.shopapp.dtos.response.ApiResponse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Objects;

@ControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> sampleHandlingRunTimeException(Exception exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(SecurityErrorCode.UNAUTHENTICATED.getCode());
        apiResponse.setMessage(SecurityErrorCode.UNAUTHENTICATED.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    //    @ExceptionHandler(value = RuntimeException.class)
    //    ResponseEntity<ApiResponse> handlingRunTimeException(RuntimeException runtimeException){
    //        return ResponseEntity.badRequest().body(ApiResponse.builder()
    //                        .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
    //                        .message(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage())
    //                .build());
    //    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException appException) {
        BaseErrorCode baseErrorCode = appException.getBaseErrorCode();
        return ResponseEntity.status(baseErrorCode.getHttpStatusCode())
                .body(ApiResponse.builder()
                        .code(baseErrorCode.getCode())
                        .message(baseErrorCode.getMessage())
                        .build());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AccessDeniedException accessDeniedException) {
        SecurityErrorCode securityErrorCode = SecurityErrorCode.UNAUTHORIZED;

        return ResponseEntity.status(securityErrorCode.getHttpStatusCode())
                .body(ApiResponse.builder()
                        .code(securityErrorCode.getCode())
                        .message((securityErrorCode.getMessage()))
                        .build());
    }

    //    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    //    ResponseEntity<ApiResponse> sampleHandlingMethodArgumentNotValidException(MethodArgumentNotValidException
    // methodArgumentNotValidException){
    //        String enumKey = methodArgumentNotValidException.getFieldError().getDefaultMessage();
    //        ErrorCode errorCode;
    //
    //        try {
    //            errorCode = ErrorCode.valueOf(enumKey);
    //        } catch (Exception e){
    //            errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;
    //        }
    //
    //        ApiResponse apiResponse = new ApiResponse();
    //        apiResponse.setCode(errorCode.getCode());
    //        apiResponse.setMessage(errorCode.getMessage());
    //
    //        return ResponseEntity.badRequest().body(apiResponse);
    //    }

    private static final String MIN_ATTRIBUTE = "min";

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException) {
        String enumKey = methodArgumentNotValidException.getFieldError().getDefaultMessage();
        SecurityErrorCode securityErrorCode = SecurityErrorCode.UNCATEGORIZED_EXCEPTION;
        Map<String, Object> attributes = null;
        try {
            securityErrorCode = SecurityErrorCode.valueOf(enumKey);

            var constraintViolation = methodArgumentNotValidException
                    .getBindingResult()
                    .getAllErrors()
                    .get(0)
                    .unwrap(ConstraintViolation.class);

            attributes = constraintViolation.getConstraintDescriptor().getAttributes();

            log.info(attributes.toString());
        } catch (Exception e) {

        }

        return ResponseEntity.badRequest()
                .body(ApiResponse.builder()
                        .code((securityErrorCode.getCode()))
                        .message(
                                Objects.nonNull(attributes)
                                        ? mapAttribute(securityErrorCode.getMessage(), attributes)
                                        : securityErrorCode.getMessage())
                        .build());
    }

    private String mapAttribute(String message, Map<String, Object> attributes) {
        String minValue = String.valueOf(attributes.get(MIN_ATTRIBUTE));

        return message.replace("{" + MIN_ATTRIBUTE + "}", minValue);
    }
}
