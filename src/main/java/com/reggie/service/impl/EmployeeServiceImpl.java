package com.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.reggie.Dto.EmployeeDto;
import com.reggie.config.Result;
import com.reggie.pojo.Employee;
import com.reggie.mapper.EmployeeMapper;
import com.reggie.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import freemarker.template.utility.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * <p>
 * 员工信息 服务实现类
 * </p>
 *
 * @author luffy
 * @since 2023-02-28
 */
@Slf4j
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * @description 对用户登录进行响应
     * @date 2023/2/28 16:35
     */
    @Override
    public Result<String> login(HttpServletRequest request, Employee employee) {

        // 1.将页面提交的密码password进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // 2.根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getUsername, employee.getUsername());
        Employee selectOne = employeeMapper.selectOne(wrapper);

        //  3、如果没有查询到则返回登录失败结果
        if (selectOne == null) {
            log.info("用户名不存在");
            return Result.error("用户名不存在");
        }

        // 4、密码比对，如果不一致则返回登录失败结果
        if (!selectOne.getPassword().equals(password)){
            log.info("密码错误");
            return Result.error("密码错误");
        }

        //  5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if (selectOne.getStatus() == 0){
            log.info("员工已被禁用");
            return Result.error("员工已被禁用");
        }

        //  6、登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employee", selectOne.getId());

        log.info("登录成功");
        return Result.success("登录成功");
    }

    @Override
    public Result<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        log.info("退出成功");
        return Result.success("退出成功");
    }

    @Override
    public Result<String> add(HttpServletRequest request,Employee employee) {
        // 员工的用户名是唯一的 所以需要先进行判断
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getUsername, employee.getUsername());
        Employee selectOne = employeeMapper.selectOne(wrapper);

        if (selectOne != null) {
            log.info("添加员工失败");
            return Result.error("用户名不唯一");
        }

        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        long id = (long) request.getSession().getAttribute("employee");
        employee.setCreateUser(id);
        employee.setUpdateUser(id);
        // 设置密码
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));


        employeeMapper.insert(employee);

        return Result.success("添加员工成功");

    }
}
