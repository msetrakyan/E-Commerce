package com.smartcode.ecommerce.mapper;

import com.smartcode.ecommerce.model.order.OrderEntity;
import com.smartcode.ecommerce.model.order.OrderResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponseDto toResponseDto(OrderEntity orderEntity);

    List<OrderResponseDto> toResponseDtoList(List<OrderEntity> orders);
}
