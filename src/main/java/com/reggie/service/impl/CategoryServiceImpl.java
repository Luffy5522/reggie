package com.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie.config.Result;
import com.reggie.mapper.CategoryMapper;
import com.reggie.pojo.Category;
import com.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜品及套餐分类 服务实现类
 * </p>
 *
 * @author luffy
 * @since 2023-02-28
 */
@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public Result<Page> pageInfo(int page, int pageSize) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        Page<Category> categoryPage = new Page<>(page,pageSize);
        wrapper.orderByDesc(Category::getSort);
        log.info("执行分页查询");
        // 执行分页查询
        categoryMapper.selectPage(categoryPage, wrapper);
        return Result.success(categoryPage);

    }
}
