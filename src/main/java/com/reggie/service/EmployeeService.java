package com.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.reggie.config.Result;
import com.reggie.pojo.Employee;

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

    // 登录功能
    Result<String> login(HttpServletRequest request, Employee employee);

    // 注销功能
    Result<String> logout(HttpServletRequest request);

    // 新增员工
    Result<String> save(HttpServletRequest request, Employee employee);

    // 页面查询
    Result<Page<Employee>> page(int page, int pageSize, String name);

    // 查询员工id
    Result<Employee> getById(Long id);

    // 修改员工信息
    Result<String> update(HttpServletRequest request, Employee employee);

}
