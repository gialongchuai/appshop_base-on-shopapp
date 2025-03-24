package com.gialongchuai.shopapp.services.impl;

import com.gialongchuai.shopapp.dtos.request.OrderCreationRequest;
import com.gialongchuai.shopapp.dtos.request.OrderUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.OrderResponse;

import java.util.List;

public interface IOrderService {
    OrderResponse create(OrderCreationRequest orderCreationRequest);
    List<OrderResponse> getAllOrders();
    List<OrderResponse> getAllOrdersByUserId(String userId);
    OrderResponse getOrder(String orderId);
    OrderResponse updateOrder(String orderId, OrderUpdationRequest orderUpdationRequest);
    String deleteOrder(String orderId);
}
