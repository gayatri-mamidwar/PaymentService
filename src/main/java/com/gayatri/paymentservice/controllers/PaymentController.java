package com.gayatri.paymentservice.controllers;

import com.gayatri.paymentservice.dtos.GeneratePaymentLinkRequestDto;
import com.gayatri.paymentservice.services.PaymentService;
import com.gayatri.paymentservice.services.RazorPayGateway;
import com.gayatri.paymentservice.services.StripeGateway;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private RazorPayGateway razorPayGateway;
    private StripeGateway stripeGateway;

    public PaymentController(RazorPayGateway razorPayGateway, StripeGateway stripeGateway){
        this.razorPayGateway = razorPayGateway;
        this.stripeGateway = stripeGateway;
    }

    //Are we just fetching info already there? or we are creating something??
    @PostMapping("/payments")
    public String generatePaymentLink(@RequestBody GeneratePaymentLinkRequestDto generatePaymentLinkRequestDto) throws RazorpayException {

        //Health check, decide which gateway to call
        return razorPayGateway.generatePaymentLink(generatePaymentLinkRequestDto.getOrderId());
    }
}
