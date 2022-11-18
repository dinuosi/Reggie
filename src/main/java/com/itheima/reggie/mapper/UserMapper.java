package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Program: reggie_take_out
 * @Description: 用户登录
 * @Author: ATao
 * @Create: 2022-11-18 16:00
 * @Since version-1.0
 **/

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
