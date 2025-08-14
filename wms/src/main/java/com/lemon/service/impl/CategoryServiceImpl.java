package com.lemon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.domain.Category;
import com.lemon.domain.Result;
import com.lemon.mapper.CategoryMapper;
import com.lemon.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> getAll() {
        List<Category> all = categoryMapper.getAll();
        List<Category> fu = all.stream().filter(s -> s.getParentId().equals(0)).toList();
        fu.forEach(s->setZiList(s,all));
        return fu;
    }

    @Override
    public void add(Category category) {
        //Category byPId = categoryMapper.findByPId(category.getParentId());
        //if (byPId != null){
        //    category.setParentId(byPId.getId());
        //}else {
        //    category.setParentId(0);
        //}
        if(category.getParentId()==null){
            category.setParentId(0);
        }
        categoryMapper.insert(category);
    }

    @Override
    public int deleteById(Integer id) {
        List<Category> categories = categoryMapper.selectList(null);
        for (Category category : categories) {
            if (category.getParentId().equals(id)){
                return -1;
            }
        }
        return categoryMapper.deleteByIds(Arrays.asList(id));
    }

    private void setZiList(Category fu,List<Category> all){
        List<Category> ziList = all.stream().filter(s -> s.getParentId().equals(fu.getId())).toList();
        fu.setChildren(ziList);
        ziList.forEach(s->setZiList(s,all));
    }
}
