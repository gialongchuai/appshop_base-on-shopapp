package com.gialongchuai.shopapp.controllers;

import com.gialongchuai.shopapp.dtos.request.OrderDetailCreationRequest;
import com.gialongchuai.shopapp.dtos.request.OrderDetailUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.ApiResponse;
import com.gialongchuai.shopapp.dtos.response.OrderDetailResponse;
import com.gialongchuai.shopapp.services.OrderDetailService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/orders/details")
public class OrderDetailController {
    OrderDetailService orderDetailService;

    @PostMapping
    ApiResponse<OrderDetailResponse> create(@RequestBody @Valid OrderDetailCreationRequest orderDetailCreationRequest) {
        log.info("=== create order: " + orderDetailCreationRequest);
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.create(orderDetailCreationRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<OrderDetailResponse>> getAllOrderDetail() {
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .result(orderDetailService.getAllOrderDetails())
                .build();
    }

    @GetMapping("/{orderDetailId}")
    ApiResponse<OrderDetailResponse> getOrderDetail(@PathVariable String orderDetailId) {
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.getOrderDetail(orderDetailId))
                .build();
    }

    @PutMapping("/{orderDetailId}")
    ApiResponse<OrderDetailResponse> updateOrderDetail(@PathVariable String orderDetailId,
            @RequestBody @Valid OrderDetailUpdationRequest orderDetailUpdationRequest) {
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.updateOrderDetail(orderDetailId, orderDetailUpdationRequest))
                .build();
    }

    @DeleteMapping("/{orderDetailId}")
    ApiResponse<String> delete(@PathVariable String orderDetailId) {
        return ApiResponse.<String>builder()
                .result(orderDetailService.deleteOrderDetail(orderDetailId))
                .build();
    }
}
