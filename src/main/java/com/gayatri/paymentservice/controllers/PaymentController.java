package com.gayatri.paymentservice.controllers;

import com.gayatri.paymentservice.dtos.GeneratePaymentLinkRequestDto;
import com.gayatri.paymentservice.services.PaymentService;
import com.gayatri.paymentservice.services.RazorPayGateway;
import com.gayatri.paymentservice.services.StripeGateway;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    /* If u r getting RazorPay bean creation error/placeholder error,add below 2 lines i.e RAZORPAY_KEY_ID & RAZORPAY_KEY_SECREt in env vari.
    env vari-> 3 dots -> edit config-> env vari

    dummy example- PROVIDE UR OWN ID AND SECRET
    RAZORPAY_KEY_ID=rzp_test_l0iP1PIPAD8plf;RAZORPAY_KEY_SECRET=Nur7rPbF0LgefglsueN3gm0e
    */
    private RazorPayGateway razorPayGateway;
    private StripeGateway stripeGateway;

    public PaymentController(StripeGateway stripeGateway, RazorPayGateway razorPayGateway){
        this.razorPayGateway = razorPayGateway;
        this.stripeGateway = stripeGateway;
    }

    //Are we just fetching info already there? or we are creating something??
    @PostMapping("/payments")
    public String generatePaymentLink(@RequestBody GeneratePaymentLinkRequestDto generatePaymentLinkRequestDto) throws RazorpayException, StripeException {

        //Return the payment link
        //custom logic
        //2 payment gateways. 90% of transactions  - rp, 10% - stripe
//        if(rp turn){
//            rpservice.gener
//        }else {
//            stripepservoce.gener
//        }

        //Health check, decide which gateway to call
        return stripeGateway.generatePaymentLink(generatePaymentLinkRequestDto.getOrderId());
    }
}
