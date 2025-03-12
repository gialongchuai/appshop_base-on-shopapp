package com.gialongchuai.shopapp.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.gialongchuai.shopapp.dtos.request.OrderCreationRequest;
import com.gialongchuai.shopapp.dtos.request.OrderUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.OrderResponse;
import com.gialongchuai.shopapp.entities.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toOrder(OrderCreationRequest orderCreationRequest);

    OrderResponse toOrderResponse(Order order);

    void updateOrder(@MappingTarget Order order, OrderUpdationRequest orderUpdationRequest);
}
