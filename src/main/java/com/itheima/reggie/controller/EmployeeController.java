package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @ClassName EmployeeController
 * @Author ATao
 * @Date 2022/11/3 15:58
 * @Description 员工登录控制器
 * @Since version-1.0
 */

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     * request 存session
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){

        //1.将页面提交的密码password进行MD5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2.根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        //3.如果没有查询到则返回登录失败结果
        if (emp==null){
            return R.error("登录失败");
        }

        //4.密码比对,如果不一致则返回登录失败结果
        if (!emp.getPassword().equals(password)){
            return R.error("登录失败");
        }

        //5.查看员工状态,如果为已禁用状态,则返回员工已禁用结果
        if (emp.getStatus() == 0){
            return R.error("账号已禁用");
        }

        //6.登录成功,将员工id存入Session并返回登陆成功结果
        request.getSession().setAttribute("employee",emp.getId());
        log.info("登录成功");
        return R.success(emp);
    }

    @PostMapping("/logout")
    public R<String> logout (HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        log.info("登出成功");
        return R.success("登出成功");
    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @PostMapping
    public R<Employee> save(HttpServletRequest request,@RequestBody Employee employee){
        // 设置初始密码123456,需要进行MD5加密处理
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        Long empId = (Long) request.getSession().getAttribute("employee");

        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);

        employeeService.save(employee);
        log.info("新增员工,员工信息:{}",employee.toString());
        return null;
    }
}
