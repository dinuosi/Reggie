package com.itheima.reggie.common;

/**
 * @program: reggie_take_out
 * @description: 基于ThreadLocl封装工具类, 用户保存和获取当钱登录用户ID
 * @author: ATao
 * @create: 2022-11-08 16:15
 **/

public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
