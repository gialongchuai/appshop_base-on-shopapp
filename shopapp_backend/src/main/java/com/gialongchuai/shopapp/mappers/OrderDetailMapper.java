package com.gialongchuai.shopapp.mappers;

import com.gialongchuai.shopapp.dtos.request.OrderDetailCreationRequest;
import com.gialongchuai.shopapp.dtos.request.OrderDetailUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.OrderDetailResponse;
import com.gialongchuai.shopapp.entities.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface OrderDetailMapper {
    OrderDetail toOrderDetail(OrderDetailCreationRequest orderDetailCreationRequest);

    @Mapping(target = "productResponse", source = "product", qualifiedByName = "toProductResponse")
    @Mapping(target = "orderResponse", source = "order")
    OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail);

    void updateOrderDetail(@MappingTarget OrderDetail orderDetail, OrderDetailUpdationRequest orderDetailUpdationRequest);
}
