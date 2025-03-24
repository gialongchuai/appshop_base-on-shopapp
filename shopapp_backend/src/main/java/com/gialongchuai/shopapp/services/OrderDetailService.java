package com.gialongchuai.shopapp.services;

import java.util.List;

import com.gialongchuai.shopapp.exceptions.OrderErrorCode;
import com.gialongchuai.shopapp.services.impl.IOrderDetailService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.gialongchuai.shopapp.dtos.request.OrderDetailCreationRequest;
import com.gialongchuai.shopapp.dtos.request.OrderDetailUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.OrderDetailResponse;
import com.gialongchuai.shopapp.entities.Order;
import com.gialongchuai.shopapp.entities.OrderDetail;
import com.gialongchuai.shopapp.entities.Product;
import com.gialongchuai.shopapp.exceptions.OrderDetailErrorCode;
import com.gialongchuai.shopapp.exceptions.custom.AppException;
import com.gialongchuai.shopapp.mappers.OrderDetailMapper;
import com.gialongchuai.shopapp.repositories.OrderDetailRepository;
import com.gialongchuai.shopapp.repositories.OrderRepository;
import com.gialongchuai.shopapp.repositories.ProductRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderDetailService implements IOrderDetailService {
    OrderDetailRepository orderDetailRepository;
    OrderDetailMapper orderDetailMapper;
    OrderRepository orderRepository;
    ProductRepository productRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public OrderDetailResponse create(OrderDetailCreationRequest orderDetailCreationRequest) {
        Order order = orderRepository
                .findById(orderDetailCreationRequest.getOrderId())
                .orElseThrow(() -> new AppException(OrderErrorCode.ORDER_NOT_EXISTED));

        Product product = productRepository
                .findById(orderDetailCreationRequest.getProductId())
                .orElseThrow(() -> new AppException(OrderDetailErrorCode.PRODUCT_NOT_EXISTED));
        OrderDetail orderDetail = orderDetailMapper.toOrderDetail(orderDetailCreationRequest);
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);

        return orderDetailMapper.toOrderDetailResponse(orderDetailRepository.save(orderDetail));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<OrderDetailResponse> getAllOrderDetails() {
        var orderDetails = orderDetailRepository.findAll();
        return orderDetails.stream()
                .map(orderDetailMapper::toOrderDetailResponse)
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<OrderDetailResponse> getAllOrderDetailsOfOrder(String orderId) {
        var orderDetails = orderDetailRepository.findAllByOrderId(orderId);
        return orderDetails.stream()
                .map(orderDetailMapper::toOrderDetailResponse)
                .toList();
    }

    @Override
    public OrderDetailResponse getOrderDetail(String orderDetailId) {
        return orderDetailMapper.toOrderDetailResponse(orderDetailRepository
                .findById(orderDetailId)
                .orElseThrow(() -> new AppException(OrderDetailErrorCode.ORDER_DETAIL_NOT_EXISTED)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public OrderDetailResponse updateOrderDetail(
            String orderDetailId, OrderDetailUpdationRequest orderDetailUpdationRequest) {
        OrderDetail orderDetail = orderDetailRepository
                .findById(orderDetailId)
                .orElseThrow(() -> new AppException(OrderDetailErrorCode.ORDER_DETAIL_NOT_EXISTED));
        orderDetailMapper.updateOrderDetail(orderDetail, orderDetailUpdationRequest);

        Order order = orderRepository
                .findById(orderDetailUpdationRequest.getOrderId())
                .orElseThrow(() -> new AppException(OrderErrorCode.ORDER_NOT_EXISTED));

        Product product = productRepository
                .findById(orderDetailUpdationRequest.getProductId())
                .orElseThrow(() -> new AppException(OrderDetailErrorCode.PRODUCT_NOT_EXISTED));

        orderDetail.setOrder(order);
        orderDetail.setProduct(product);

        return orderDetailMapper.toOrderDetailResponse(orderDetailRepository.save(orderDetail));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public String deleteOrderDetail(String orderDetailId) {
        orderDetailRepository.deleteById(orderDetailId);
        return "Delete order successfully!";
    }
}
