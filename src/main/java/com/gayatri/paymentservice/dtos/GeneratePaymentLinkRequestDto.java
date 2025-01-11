package com.gayatri.paymentservice.dtos;

public class GeneratePaymentLinkRequestDto {

    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}

//dtos and models sometimes look very close but they are independent of each other.
//models are entities which we store in dbs
//dtos are just objects which we might receive or send outside from my system