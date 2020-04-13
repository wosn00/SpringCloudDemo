package com.hs.springcloud.controller;

import com.hs.springcloud.entites.CommonResult;
import com.hs.springcloud.entites.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;


    @PostMapping("/payment/create")
    public CommonResult create(Payment payment) {

        log.info("*****插入结果: {},端口：{}", payment,serverPort);

        return CommonResult.builder()
                .code(200)
                .message("插入成功+8001")
                .build();
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {

        Payment payment = new Payment(233L, "张三");
        log.info("*****查询结果: {},端口：{}", payment,serverPort);
        return CommonResult.builder()
                .code(200)
                .message("查询成功+8001")
                .data(payment)
                .build();

    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return "我是zipkin链接";
    }


}
