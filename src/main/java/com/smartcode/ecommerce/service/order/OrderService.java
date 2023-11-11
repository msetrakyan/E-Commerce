package com.smartcode.ecommerce.service.order;


import com.smartcode.ecommerce.model.order.OrderResponseDto;
import com.smartcode.ecommerce.util.PaymentType;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(PaymentType paymentType, String note);

    List<OrderResponseDto> getAllOrders();

    OrderResponseDto getOrderById(Integer orderId);
}