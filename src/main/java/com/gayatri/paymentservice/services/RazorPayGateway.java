package com.gayatri.paymentservice.services;

import com.gayatri.paymentservice.configs.RazorPayClient;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
//import org.springframework.boot.configurationprocessor.json.JSONObject;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("razorpay")
public class RazorPayGateway implements  PaymentService{

    private RazorpayClient razorpayClient;          //focus "RazorpayClient" ,not "RazorPayClient"

    public RazorPayGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String generatePaymentLink(Long orderId) throws RazorpayException {

         /*
        Will you first make a call to order service to validate the orderId and get some order details?
        Rest template can be used to interact with order service. This will done in future classes.
        Assuming that orderId is valid & we will assume some random amount for it.
         */
        //code for calling the razorpay to get the link
        /*
        {
            amount :
            currency:
            accept_partial:
            expireBy:
            ..
            customer : {
                name:
                email:
                phone:
            },

         }
         */

        JSONObject paymentLinkRequest = new JSONObject();       // requestBody / payload to be send to razor pay
        paymentLinkRequest.put("amount",1000);      //10 rupees. PG supports amounts upto 2 decimal places. 10.50 => 1050
        paymentLinkRequest.put("currency","INR");
//        paymentLinkRequest.put("accept_partial",true);
//        paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",System.currentTimeMillis() + 10*60*1000); //epoch, 10 minutes = 6*10^5 ms  //epoch(std way of representing time) value in milliseconds
        paymentLinkRequest.put("reference_id",orderId.toString());
        paymentLinkRequest.put("description","Test payment for Integration of Payment gateway session on 11th Jan 25");

        JSONObject customer = new JSONObject();
        customer.put("name","Gayatri mdr");
        customer.put("contact","9049690418");
        customer.put("email","prajyot88@gmail.com");
        paymentLinkRequest.put("customer",customer);

        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify", notify);
        paymentLinkRequest.put("reminder_enable",true);

//        JSONObject notes = new JSONObject();
//        notes.put("policy_name","Jeevan Bima");
//        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://google.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        return payment.toString();
    }
}
