package com.gialongchuai.shopapp.services.impl;

import com.gialongchuai.shopapp.dtos.request.OrderDetailCreationRequest;
import com.gialongchuai.shopapp.dtos.request.OrderDetailUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.OrderDetailResponse;

import java.util.List;

public interface IOrderDetailService {
    OrderDetailResponse create(OrderDetailCreationRequest orderDetailCreationRequest);
    List<OrderDetailResponse> getAllOrderDetails();
    List<OrderDetailResponse> getAllOrderDetailsOfOrder(String orderId);
    OrderDetailResponse getOrderDetail(String orderDetailId);
    OrderDetailResponse updateOrderDetail(
            String orderDetailId, OrderDetailUpdationRequest orderDetailUpdationRequest);
    String deleteOrderDetail(String orderDetailId);
}
