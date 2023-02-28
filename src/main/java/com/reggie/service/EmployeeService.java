package com.reggie.service;

import com.reggie.config.Result;
import com.reggie.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 员工信息 服务类
 * </p>
 *
 * @author luffy
 * @since 2023-02-28
 */
public interface EmployeeService extends IService<Employee> {

    Result<String> login(HttpServletRequest request,Employee employee);

}