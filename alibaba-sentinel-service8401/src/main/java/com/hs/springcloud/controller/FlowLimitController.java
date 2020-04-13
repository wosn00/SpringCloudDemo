package com.hs.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {


    @GetMapping("/testA/{num}")
    public String testA(@PathVariable("num") long num)
    {
        if (num < 0 ){
            int i=1/0;
        }
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "-------testB";
    }

}
