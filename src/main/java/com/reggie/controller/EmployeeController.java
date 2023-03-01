package com.reggie.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie.config.Result;
import com.reggie.pojo.Employee;
import com.reggie.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 员工信息 前端控制器
 * </p>
 *
 * @author luffy
 * @since 2023-02-28
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    EmployeeService employeeService;

    @PostMapping("/login")
    public Result<String> login(HttpServletRequest request, @RequestBody Employee employee) {
        return employeeService.login(request, employee);
    }

    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest request) {
        return employeeService.logout(request);
    }

    @PostMapping
    public Result<String> add(HttpServletRequest request, @RequestBody Employee employee) {
        return employeeService.add(request, employee);
    }

    @GetMapping("/page")
    public Result<Page<Employee>> page(int page, int pageSize, String name) {
        return employeeService.page(page, pageSize, name);

    }

}
