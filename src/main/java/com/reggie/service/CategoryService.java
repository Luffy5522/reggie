package com.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.reggie.config.Result;
import com.reggie.pojo.Category;

/**
 * <p>
 * 菜品及套餐分类 服务类
 * </p>
 *
 * @author luffy
 * @since 2023-02-28
 */
public interface CategoryService extends IService<Category> {
    Result<Page> pageInfo(int page, int pageSize);
}
