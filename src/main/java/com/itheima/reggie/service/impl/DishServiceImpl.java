package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.mapper.DishMapper;
import com.itheima.reggie.service.DishService;
import org.springframework.stereotype.Service;

/**
 * @Program: reggie_take_out
 * @Description: 餐品
 * @Author: ATao
 * @Create: 2022-11-09 16:24
 * @Since version-1.0
 **/

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper,Dish> implements DishService {
}
