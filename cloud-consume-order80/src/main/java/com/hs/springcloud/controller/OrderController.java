package com.hs.springcloud.controller;

import com.hs.springcloud.entites.CommonResult;
import com.hs.springcloud.entites.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment){
        return restTemplate.postForObject("http://CLOUD-PAYMENT-SERVICE/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject("http://CLOUD-PAYMENT-SERVICE/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/zipkin")
    public String getPayment1(){
        String strp = strp();
        return restTemplate.getForObject("http://CLOUD-PAYMENT-SERVICE/payment/zipkin",String.class);
    }
    public String strp(){
        return "2";
    }


}
