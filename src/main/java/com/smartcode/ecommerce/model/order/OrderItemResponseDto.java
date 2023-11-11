package com.smartcode.ecommerce.model.order;

import com.smartcode.ecommerce.model.product.ProductDetails;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemResponseDto {

    private Integer id;

    private Integer count;

    private BigDecimal totalPrice;

    private ProductDetails productDetails;
}
