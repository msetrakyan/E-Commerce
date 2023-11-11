package com.smartcode.ecommerce.service.payment.strategy;

import com.smartcode.ecommerce.util.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public interface PaymentStrategy {

    String pay(BigDecimal amount);

    PaymentType getPaymentType();

    @Autowired
    default void register(@NotNull PaymentService paymentService) {
        paymentService.register(getPaymentType(), this);
    }
}
