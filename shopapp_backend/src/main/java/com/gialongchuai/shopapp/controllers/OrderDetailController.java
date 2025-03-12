package com.gialongchuai.shopapp.controllers;

import java.util.List;

import com.gialongchuai.shopapp.services.impl.IOrderDetailService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.gialongchuai.shopapp.dtos.request.OrderDetailCreationRequest;
import com.gialongchuai.shopapp.dtos.request.OrderDetailUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.ApiResponse;
import com.gialongchuai.shopapp.dtos.response.OrderDetailResponse;
import com.gialongchuai.shopapp.services.OrderDetailService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/orders/details")
public class OrderDetailController {
    IOrderDetailService iOrderDetailService;

    @PostMapping
    ApiResponse<OrderDetailResponse> create(@RequestBody @Valid OrderDetailCreationRequest orderDetailCreationRequest) {
        log.info("=== create order: " + orderDetailCreationRequest);
        return ApiResponse.<OrderDetailResponse>builder()
                .result(iOrderDetailService.create(orderDetailCreationRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<OrderDetailResponse>> getAllOrderDetail() {
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .result(iOrderDetailService.getAllOrderDetails())
                .build();
    }

    @GetMapping("/{orderDetailId}")
    ApiResponse<OrderDetailResponse> getOrderDetail(@PathVariable String orderDetailId) {
        return ApiResponse.<OrderDetailResponse>builder()
                .result(iOrderDetailService.getOrderDetail(orderDetailId))
                .build();
    }

    @PutMapping("/{orderDetailId}")
    ApiResponse<OrderDetailResponse> updateOrderDetail(
            @PathVariable String orderDetailId,
            @RequestBody @Valid OrderDetailUpdationRequest orderDetailUpdationRequest) {
        return ApiResponse.<OrderDetailResponse>builder()
                .result(iOrderDetailService.updateOrderDetail(orderDetailId, orderDetailUpdationRequest))
                .build();
    }

    @DeleteMapping("/{orderDetailId}")
    ApiResponse<String> delete(@PathVariable String orderDetailId) {
        return ApiResponse.<String>builder()
                .result(iOrderDetailService.deleteOrderDetail(orderDetailId))
                .build();
    }
}
