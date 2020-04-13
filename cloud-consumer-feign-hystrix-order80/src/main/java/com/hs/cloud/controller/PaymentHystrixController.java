package com.hs.cloud.controller;

import com.hs.cloud.remote.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "globalHandler")
public class PaymentHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok")
    @HystrixCommand
    public String paymentOk(){
        //自己造异常，让Hystrix走全局降级方法！
        int i=1/0;
        return paymentHystrixService.paymentOk();
    }

    @GetMapping("/consumer/payment/hystrix/timeout")
    @HystrixCommand(fallbackMethod = "timeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentTimeOut(){
        return paymentHystrixService.paymentTimeOut();
    }

    /**
     * 特定方法的降级方法
     */
    public String timeOutHandler(){
        return "我是降级方法：职业备胎0v0";
    }

    /**
     * 全局降级方法
     */
    public String globalHandler(){
        return "我是全局降级方法：职业备胎一号！";
    }

    /**
     * 集中的降级处理
     */
    @GetMapping("/consumer/payment/hystrix/ok1")
    public String paymentOk1(){
        return paymentHystrixService.paymentTimeOut();
    }




}
