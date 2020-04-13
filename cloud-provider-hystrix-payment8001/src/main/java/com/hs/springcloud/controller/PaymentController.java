package com.hs.springcloud.controller;


import com.hs.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/hystrix/ok")
    public String paymentOk(){
        return paymentService.paymentOk();
    }

    @GetMapping("/payment/hystrix/timeout")
    public String paymentTimeOut(){
        return paymentService.paymentTimeOut();
    }

    /**
     * 服务熔断
     */
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Long id){
        return paymentService.paymentCircuitBreaker(id);
    }
}
