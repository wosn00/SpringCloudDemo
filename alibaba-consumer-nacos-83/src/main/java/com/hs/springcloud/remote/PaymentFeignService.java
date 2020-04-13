package com.hs.springcloud.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "nacos-payment-provider")
public interface PaymentFeignService {

    @GetMapping("/payment/nacos")
    String getPayment();
}
