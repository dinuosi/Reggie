package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Program: reggie_take_out
 * @Description: 菜品风味mapper
 * @Author: ATao
 * @Create: 2022-11-14 16:21
 * @Since version-1.0
 **/

@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {
}
