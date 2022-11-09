package com.itheima.reggie.common;

/**
 * @Program: reggie_take_out
 * @Description: 自定义业务异常类
 * @Author: ATao
 * @Create: 2022-11-09 16:58
 * @Since version-1.0
 **/

public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
