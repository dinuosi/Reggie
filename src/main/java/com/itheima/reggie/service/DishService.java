package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Program: reggie_take_out
 * @Description: 餐品
 * @Author: ATao
 * @Create: 2022-11-09 16:23
 * @Since version-1.0
 **/

@Service
public interface DishService extends IService<Dish> {

    /**
     * 新增菜品,同事擦如菜品对应的口味数据,需要操作两张表:dish dish_flavor
     * @param dishDto
     */
    public void saveWithFlavor(DishDto dishDto);

    /**
     * 根据ID查询菜品信息对应的口味信息
     *
     * @param id@return
     */
    public DishDto getByIdWithFlavor(Long id);

    /**
     * 更新菜品信息,口味信息
     * @param dishDto
     */
    public void uploadWithFlavor(DishDto dishDto);

    public  void workbookForDish() throws IOException;
}
