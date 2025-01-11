package com.gayatri.paymentservice.services;

import com.razorpay.RazorpayException;

public interface PaymentService {

    //Can have multiple variations/implementations?
    String generatePaymentLink(Long orderId) throws RazorpayException;
}
