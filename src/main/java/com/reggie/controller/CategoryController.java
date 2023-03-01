package com.reggie.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie.config.Result;
import com.reggie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 菜品及套餐分类 前端控制器
 * </p>
 *
 * @author luffy
 * @since 2023-02-28
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/page")
    public Result<Page> pageInfo(int page ,int pageSize ){
        return categoryService.pageInfo(page,pageSize);
    }

}
