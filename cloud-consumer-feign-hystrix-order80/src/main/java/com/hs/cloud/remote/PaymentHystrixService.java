package com.hs.cloud.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = FallBackService.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok")
    String paymentOk();

    @GetMapping("/payment/hystrix/timeout")
    String paymentTimeOut();


}
