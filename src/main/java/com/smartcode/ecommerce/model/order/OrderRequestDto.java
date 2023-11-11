package com.smartcode.ecommerce.model.order;

import com.smartcode.ecommerce.util.PaymentType;
import lombok.Getter;

@Getter
public class OrderRequestDto {

    private PaymentType paymentType;
    private String note;

}
