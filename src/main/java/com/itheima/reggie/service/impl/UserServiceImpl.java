package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.entity.User;
import com.itheima.reggie.mapper.UserMapper;
import com.itheima.reggie.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Program: reggie_take_out
 * @Description: 用户登录
 * @Author: ATao
 * @Create: 2022-11-18 16:02
 * @Since version-1.0
 **/

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
