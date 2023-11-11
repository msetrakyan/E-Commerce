package com.smartcode.ecommerce.model.basket;

import com.smartcode.ecommerce.model.product.dto.ProductDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BasketItemResponseDto {
    private Integer id;
    private Integer count;
    private ProductDto product;
}
