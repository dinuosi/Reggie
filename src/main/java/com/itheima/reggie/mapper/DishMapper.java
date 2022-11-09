package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Program: reggie_take_out
 * @Description: 菜品mapper
 * @Author: ATao
 * @Create: 2022-11-09 16:21
 * @Since version-1.0
 **/

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
