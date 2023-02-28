package com.reggie.controller;


import com.reggie.config.Result;
import com.reggie.pojo.Employee;
import com.reggie.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/login")
    public Result<String> login(HttpServletRequest request, Employee employee) {
        return employeeService.login(request, employee);
    }

}