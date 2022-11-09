package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @Program: reggie_take_out
 * @Description: 分类管理控制器
 * @Author: ATao
 * @Create: 2022-11-08 20:37
 * @Since version-1.0
 **/
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public R<String> add(HttpServletRequest request, @RequestBody Category category){

        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getName,category.getName());
        Category cat = categoryService.getOne(queryWrapper);

        categoryService.save(category);
        return R.success("插入成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize){
        log.info("接收到请求: page = {},pageSize = {}", page, pageSize);

        // 构造分页构造器
        Page pageInfo = new Page(page, pageSize);

        // 构造条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加排序条件
        queryWrapper.orderByAsc(Category::getSort);

        // 执行查询
        categoryService.page(pageInfo, queryWrapper);

        return R.success(pageInfo);
    }

    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Category category) {
        log.info("接收到请求: id = {},name = {},sort = {}", category.getId(), category.getName(),category.getSort());
        category.setUpdateUser((Long) request.getSession().getAttribute("employee"));
        category.setUpdateTime(LocalDateTime.now());
        categoryService.updateById(category);
        return R.success("修改成功");
    }

    @DeleteMapping
    public R<String> delete( Long id){
        log.info("id = {}",id);

        categoryService.remove(id);

        return R.success("操作成功");
    }

    }
