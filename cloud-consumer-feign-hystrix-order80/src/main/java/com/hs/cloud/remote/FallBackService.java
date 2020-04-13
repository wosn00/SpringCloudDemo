package com.hs.cloud.remote;

import org.springframework.stereotype.Component;

@Component
public class FallBackService implements PaymentHystrixService{

    @Override
    public String paymentOk() {
        return "我是对应的降级方法/(ㄒoㄒ)/~~";
    }

    @Override
    public String paymentTimeOut() {
        return "我是对应的降级方法/(ㄒoㄒ)/~~";
    }
}
