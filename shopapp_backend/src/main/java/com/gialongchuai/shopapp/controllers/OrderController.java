package com.gialongchuai.shopapp.controllers;

import com.gialongchuai.shopapp.dtos.request.OrderCreationRequest;
import com.gialongchuai.shopapp.dtos.request.OrderUpdationRequest;
import com.gialongchuai.shopapp.dtos.request.ProductCreationRequest;
import com.gialongchuai.shopapp.dtos.request.ProductUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.ApiResponse;
import com.gialongchuai.shopapp.dtos.response.OrderResponse;
import com.gialongchuai.shopapp.dtos.response.ProductResponse;
import com.gialongchuai.shopapp.services.OrderService;
import com.gialongchuai.shopapp.services.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/orders")
public class OrderController {
    OrderService orderService;

    @PostMapping
    ApiResponse<OrderResponse> create(@RequestBody @Valid OrderCreationRequest orderCreationRequest) {
        log.info("=== create order: " + orderCreationRequest);
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.create(orderCreationRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<OrderResponse>> getAllOrders() {
        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderService.getAllOrders())
                .build();
    }

    @GetMapping("/{orderId}")
    ApiResponse<OrderResponse> getOrder(@PathVariable String orderId) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.getOrder(orderId))
                .build();
    }

    @PutMapping("/{orderId}")
    ApiResponse<OrderResponse> updateOrder(@PathVariable String orderId,
            @RequestBody @Valid OrderUpdationRequest orderUpdationRequest) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.updateOrder(orderId, orderUpdationRequest))
                .build();
    }

    @DeleteMapping("/{orderId}")
    ApiResponse<String> delete(@PathVariable String orderId) {
        return ApiResponse.<String>builder()
                .result(orderService.deleteOrder(orderId))
                .build();
    }
}
