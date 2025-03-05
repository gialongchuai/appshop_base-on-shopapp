package com.gialongchuai.shopapp.services;

import com.gialongchuai.shopapp.constants.UploadFileConstant;
import com.gialongchuai.shopapp.dtos.request.*;
import com.gialongchuai.shopapp.dtos.response.CategoryResponse;
import com.gialongchuai.shopapp.dtos.response.OrderResponse;
import com.gialongchuai.shopapp.dtos.response.ProductResponse;
import com.gialongchuai.shopapp.entities.*;
import com.gialongchuai.shopapp.exceptions.*;
import com.gialongchuai.shopapp.exceptions.custom.AppException;
import com.gialongchuai.shopapp.mappers.CategoryMapper;
import com.gialongchuai.shopapp.mappers.OrderMapper;
import com.gialongchuai.shopapp.mappers.ProductMapper;
import com.gialongchuai.shopapp.repositories.CategoryRepository;
import com.gialongchuai.shopapp.repositories.OrderRepository;
import com.gialongchuai.shopapp.repositories.ProductRepository;
import com.gialongchuai.shopapp.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderService {
    OrderRepository orderRepository;
    OrderMapper orderMapper;
    UserRepository userRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public OrderResponse create(OrderCreationRequest orderCreationRequest) {
        User user = userRepository.findById(orderCreationRequest.getUserId()).orElseThrow(()
                -> new AppException(OrderErrorCode.USER_ORDER_NOT_EXISTED));

        Order order = orderMapper.toOrder(orderCreationRequest);
        order.setUser(user);

        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderResponse> getAllOrders() {
        var orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toOrderResponse).toList();
    }

    public OrderResponse getOrder(String orderId) {
        return orderMapper.toOrderResponse(
                orderRepository.findById(orderId).orElseThrow(()
                        -> new AppException(OrderErrorCode.ORDER_NOT_EXISTED)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public OrderResponse updateOrder(String orderId, OrderUpdationRequest orderUpdationRequest) {
        Order order = orderRepository.findById(orderId).orElseThrow(()
                -> new AppException(OrderErrorCode.ORDER_NOT_EXISTED));
        User user = userRepository.findById(orderUpdationRequest.getUserId()).orElseThrow(()
                -> new AppException(OrderErrorCode.USER_ORDER_NOT_EXISTED));

        orderMapper.updateOrder(order, orderUpdationRequest);
        order.setUser(user);

        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String deleteOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()
                -> new AppException(OrderErrorCode.ORDER_NOT_EXISTED));
        order.setActive(false);
        orderRepository.save(order);
        return "Delete order successfully!";
    }
}
