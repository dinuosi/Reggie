package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.entity.Setmeal;
import com.itheima.reggie.service.SetmealDishService;
import com.itheima.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Program: reggie_take_out
 * @Description: 设置套餐
 * @Author: ATao
 * @Create: 2022-11-16 17:33
 * @Since version-1.0
 **/

@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * 添加套餐
     * @param setmealDto
     * @return
     */
    @PostMapping
    private R<String> add (@RequestBody SetmealDto setmealDto){
        log.info(setmealDto.toString());
        setmealService.saveWithDish(setmealDto);
        return R.success("添加成功");
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    private R<Page> page (int page , int pageSize , String name){
        //log.info("page = {},pageSize = {}",page,pageSize);

        Page pageWithDish = setmealService.pageWithDish(page, pageSize, name);

        return R.success(pageWithDish);
    }

    /**
     * 修改套餐回显
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    private R<SetmealDto> getById (@PathVariable Long id){
        //log.info("id = {}",id);
        SetmealDto dish = setmealService.getByIdWithDishes(id);
        return R.success(dish);
    }

    /**
     * 修改套餐
     * @param setmealDto
     * @return
     */
    @PutMapping
    private R<String> upload(@RequestBody SetmealDto setmealDto){
        setmealService.uploadWithDish(setmealDto);
        return R.success("修改成功");
    }
    /**
     * 根据条件查询套餐数据
     * @param setmeal
     * @return
     */
    @GetMapping("/list")
    public R<List<Setmeal>> list(Setmeal setmeal){
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(setmeal.getCategoryId() != null,Setmeal::getCategoryId,setmeal.getCategoryId());
        queryWrapper.eq(setmeal.getStatus() != null,Setmeal::getStatus,setmeal.getStatus());
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        List<Setmeal> list = setmealService.list(queryWrapper);

        return R.success(list);
    }
}
