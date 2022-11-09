package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.common.CustomException;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.Setmeal;
import com.itheima.reggie.mapper.CategoryMapper;
import com.itheima.reggie.service.CategoryService;
import com.itheima.reggie.service.DishService;
import com.itheima.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Program: reggie_take_out
 * @Description:
 * @Author: ATao
 * @Create: 2022-11-08 20:31
 * @Since version-1.0
 **/

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper,Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除分类,删除之前需要进行判断
     * @param id
     */
    @Override
    public void remove(long id) {
        //查询当前分类是否关联了菜品,如果已经关联,则抛出一个业务异常
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count = dishService.count(dishLambdaQueryWrapper);
        if (count > 0){
            throw new CustomException("删除失败,当前分类下关联了菜品");
        }
        //查询当前分类是否关联了套餐,如果已经关联,则抛出一个业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        count = setmealService.count(setmealLambdaQueryWrapper);
        if (count > 0){
            throw new CustomException("删除失败,当前分类下关联了套餐");
        }
        //若孤立无援则直接删除
        super.removeById(id);
    }
}
