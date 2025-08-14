package com.lemon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.domain.Category;
import com.lemon.domain.Result;

import java.util.List;

public interface CategoryService extends IService<Category> {

    List<Category> getAll();

    void add(Category category);

    int deleteById(Integer id);
}
