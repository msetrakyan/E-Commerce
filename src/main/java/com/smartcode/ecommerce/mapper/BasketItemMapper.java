package com.smartcode.ecommerce.mapper;

import com.smartcode.ecommerce.model.basket.BasketItemEntity;
import com.smartcode.ecommerce.model.basket.BasketItemResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface BasketItemMapper {

    List<BasketItemResponseDto> toResponseDtoList(List<BasketItemEntity> basketItems);

}
