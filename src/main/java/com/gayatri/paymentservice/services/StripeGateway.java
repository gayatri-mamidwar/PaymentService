package com.gayatri.paymentservice.services;

import org.springframework.stereotype.Service;

@Service("stripe")
public class StripeGateway implements PaymentService{

    @Override
    public String generatePaymentLink(Long orderId) {
        return "";
    }
}
