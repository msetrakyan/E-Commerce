package com.smartcode.ecommerce.mapper;

import com.smartcode.ecommerce.model.product.ProductDetails;
import com.smartcode.ecommerce.model.product.ProductEntity;
import com.smartcode.ecommerce.model.product.dto.ProductCreateRequest;
import com.smartcode.ecommerce.model.product.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    @Mapping(target = "name")
    ProductDto toDto(ProductEntity productEntity);

    @Mapping(target = "name")
    ProductEntity toEntity(ProductCreateRequest productCreateRequest);


    ProductDetails toProductDetails(ProductEntity product);
}
