package com.gialongchuai.shopapp.services;

import java.util.List;

import com.gialongchuai.shopapp.services.impl.IOrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.gialongchuai.shopapp.dtos.request.*;
import com.gialongchuai.shopapp.dtos.response.OrderResponse;
import com.gialongchuai.shopapp.entities.*;
import com.gialongchuai.shopapp.exceptions.*;
import com.gialongchuai.shopapp.exceptions.custom.AppException;
import com.gialongchuai.shopapp.mappers.OrderMapper;
import com.gialongchuai.shopapp.repositories.OrderRepository;
import com.gialongchuai.shopapp.repositories.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderService implements IOrderService {
    OrderRepository orderRepository;
    OrderMapper orderMapper;
    UserRepository userRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public OrderResponse create(OrderCreationRequest orderCreationRequest) {
        User user = userRepository
                .findById(orderCreationRequest.getUserId())
                .orElseThrow(() -> new AppException(OrderErrorCode.USER_ORDER_NOT_EXISTED));

        Order order = orderMapper.toOrder(orderCreationRequest);
        order.setUser(user);

        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<OrderResponse> getAllOrders() {
        var orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toOrderResponse).toList();
    }

    @Override
    public OrderResponse getOrder(String orderId) {
        return orderMapper.toOrderResponse(orderRepository
                .findById(orderId)
                .orElseThrow(() -> new AppException(OrderErrorCode.ORDER_NOT_EXISTED)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public OrderResponse updateOrder(String orderId, OrderUpdationRequest orderUpdationRequest) {
        Order order =
                orderRepository.findById(orderId).orElseThrow(() -> new AppException(OrderErrorCode.ORDER_NOT_EXISTED));
        User user = userRepository
                .findById(orderUpdationRequest.getUserId())
                .orElseThrow(() -> new AppException(OrderErrorCode.USER_ORDER_NOT_EXISTED));

        orderMapper.updateOrder(order, orderUpdationRequest);
        order.setUser(user);

        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public String deleteOrder(String orderId) {
        Order order =
                orderRepository.findById(orderId).orElseThrow(() -> new AppException(OrderErrorCode.ORDER_NOT_EXISTED));
        order.setActive(false);
        orderRepository.save(order);
        return "Delete order successfully!";
    }
}
