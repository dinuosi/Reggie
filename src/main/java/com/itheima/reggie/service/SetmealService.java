package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.entity.Setmeal;
import org.springframework.stereotype.Service;

/**
 * @Program: reggie_take_out
 * @Description: 套餐
 * @Author: ATao
 * @Create: 2022-11-09 16:29
 * @Since version-1.0
 **/

@Service
public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐,同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    public SetmealDto getByIdWithDishes(Long id);

    public void uploadWithDish(SetmealDto setmealDto);

    public Page pageWithDish(int page,int pageSize,String name);

}
