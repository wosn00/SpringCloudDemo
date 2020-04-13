package com.hs.springcloud.controller;

import com.hs.springcloud.remote.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class NacosController {


    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/nacos")
    public String nacos(){
        return paymentFeignService.getPayment();
    }



}
