package com.smartcode.ecommerce.service.payment.impl.idram;

import com.smartcode.ecommerce.service.payment.strategy.PaymentStrategy;
import com.smartcode.ecommerce.util.PaymentType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class IdramPayment implements PaymentStrategy {

    private final PaymentType paymentType = PaymentType.IDRAM;
    @Override
    public String pay(BigDecimal amount) {
        return new StringBuilder()
                .append("Payment type is:")
                .append(paymentType)
                .append(" and total amount is ")
                .append(amount.toString()).toString();
    }

    @Override
    public PaymentType getPaymentType() {
        return paymentType;
    }
}
