package com.hs.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentOk(){
        return "线程池： "+Thread.currentThread().getName()+"成功了！！！";

    }


    /**
     * 如果被RPC调用的话执行时间超过三秒的话就要服务降级了！！！降级方法timeOutHandler
     */
    @HystrixCommand(fallbackMethod = "timeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： "+Thread.currentThread().getName()+"失败咯。。。";
    }

    /**
     * 降级方法
     */
    public String timeOutHandler(){
        return "我是降级方法：职业备胎0v0";
    }

    //==================服务熔断 ，  如果在10秒内有10次请求，6次是错误的，那么就会自动熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期10秒内
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Long id){
        if (id<0){
            throw new RuntimeException("id 不能为负数！！！");
        }
        String s = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"调用成功！流水号："+s;
    }

    /**
     * 服务熔断的降级方法
     */
    public String paymentCircuitBreaker_fallback(Long id){
        return "请稍后再试，该服务现在已经熔断了！！！"+id;
    }

}
