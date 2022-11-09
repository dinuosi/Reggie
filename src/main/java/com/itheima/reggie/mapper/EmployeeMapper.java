package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ATao
 * @Date 2022/11/3 15:53
 * @Description
 * @Since version-1.0
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
