package com.smartcode.ecommerce.service.payment.impl.card;

import com.smartcode.ecommerce.service.payment.strategy.PaymentStrategy;
import com.smartcode.ecommerce.util.PaymentType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CardPayment implements PaymentStrategy {

    private PaymentType paymentType = PaymentType.CARD;

    @Override
    public String pay(BigDecimal amount) {
        return new StringBuilder()
                .append("Payment type is:")
                .append(paymentType.toString())
                .append(" and total amount is ")
                .append(amount.toString()).toString();
    }

    @Override
    public PaymentType getPaymentType() {
        return paymentType;
    }
}
