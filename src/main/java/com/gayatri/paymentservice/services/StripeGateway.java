package com.gayatri.paymentservice.services;

import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.stereotype.Service;

@Service("stripe")
public class StripeGateway implements PaymentService{

        @Override
        public String generatePaymentLink(Long orderId) throws RazorpayException, StripeException {
            Stripe.apiKey = "";

            PriceCreateParams priceCreateParams =
                    PriceCreateParams.builder()
                            .setCurrency("INR")
                            .setUnitAmount(10000L)//100
                            .setRecurring(
                                    PriceCreateParams.Recurring.builder()
                                            .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                            .build()
                            )
                            .setProductData(
                                    PriceCreateParams.ProductData.builder().setName("Payment for a fivestar chocolate").build()
                            )
                            .build();
            Price price = Price.create(priceCreateParams);

            PaymentLinkCreateParams paymentLinkparams =
                    PaymentLinkCreateParams.builder()
                            .addLineItem(
                                    PaymentLinkCreateParams.LineItem.builder()
                                            .setPrice(price.getId())
                                            .setQuantity(1L)
                                            .build()
                            )
                            .setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                                    .setRedirect(
                                            PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                    .setUrl("https://scaler.com")
                                                    .build()
                                    )
                                    .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                    .build()
                            )
                            .build();

            PaymentLink paymentLink = PaymentLink.create(paymentLinkparams);
            return paymentLink.toString();
        }
    }
