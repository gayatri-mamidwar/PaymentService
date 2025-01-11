package com.gayatri.paymentservice.configs;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorPayClient {

    @Value("${razorpay.key.id}")
    private String razorPayKeyId;

    @Value("${razorpay.key.secret}")
    private String razorPaySecret;

    @Bean
    public RazorpayClient createRazorPayClient() throws RazorpayException {       //RazorpayClient => 'pay' p is small case
        return new RazorpayClient(razorPayKeyId, razorPaySecret);
    }
}
