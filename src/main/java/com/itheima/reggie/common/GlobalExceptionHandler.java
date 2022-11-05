package com.itheima.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @ClassName GlobalExceptionHandler
 * @Author ATao
 * @Date 2022/11/4 17:23
 * @Description 全局异常处理
 *
 * 底层基于代理,代理其他Controller,通过AOP将save等其他方法拦截到,
 * 若抛异常,统一在本类中的方法中进行抛
 *
 * @Since version-1.0
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})//作用哪些Controller
@ResponseBody//方法返回的是JSON,此注解可以转换为JSON格式返回
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error(ex.getMessage());

        if (ex.getMessage().contains("Duplicate entry")){

            String msg = ex.getMessage().split(" ")[2] + "已存在";

            return R.error(msg);
        }

        return R.error("未知错误");

    }
}
