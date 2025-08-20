package com.lemon.controller;


import com.lemon.domain.Category;
import com.lemon.domain.Result;
import com.lemon.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 树形分类
 * @author YXL
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Result getAll(){
        return Result.success(categoryService.getAll());
    }

    @PostMapping
    public Result add(@RequestBody Category category){
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryService.add(category);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Category category){
        category.setUpdateTime(LocalDateTime.now());
        return categoryService.updateById(category)?Result.success():Result.error("修改失败");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        int count = categoryService.deleteById(id);
        return count > 0 ? Result.success() : Result.error("删除失败,请先删除子分类");
    }
    /**
     * 获取所有sku
     */
}
