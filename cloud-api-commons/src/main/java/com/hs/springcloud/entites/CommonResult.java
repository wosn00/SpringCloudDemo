package com.hs.springcloud.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer integer, String message) {
        this(integer, message, null);
    }


}