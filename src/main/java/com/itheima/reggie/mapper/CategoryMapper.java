package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Program: reggie_take_out
 * @Description: 分类Mapper
 * @Author: ATao
 * @Create: 2022-11-08 20:28
 * @Since version-1.0
 **/

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
