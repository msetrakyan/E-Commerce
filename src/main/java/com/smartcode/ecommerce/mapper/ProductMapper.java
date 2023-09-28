package com.smartcode.ecommerce.mapper;

import com.smartcode.ecommerce.model.product.ProductEntity;
import com.smartcode.ecommerce.model.product.dto.ProductCreateRequest;
import com.smartcode.ecommerce.model.product.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    @Mapping(target = "")
    ProductDto toDto(ProductEntity productEntity);

    @Mapping(target = "")
    ProductEntity toEntity(ProductCreateRequest productCreateRequest);



}
