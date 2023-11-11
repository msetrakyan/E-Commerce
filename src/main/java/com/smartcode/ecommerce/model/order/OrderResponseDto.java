package com.smartcode.ecommerce.model.order;

import com.smartcode.ecommerce.util.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class OrderResponseDto {
    private Integer id;
    private OrderStatus status;
    private String note;
    private BigDecimal totalAmount;
    private List<OrderItemResponseDto> orderItems;

}
