package com.lemon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.domain.Pcd;
import com.lemon.mapper.PcdMapper;
import com.lemon.service.PcdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 省市区业务层实现类
 * @author YXL
 */
@Service
@RequiredArgsConstructor
public class PcdServiceImpl extends ServiceImpl<PcdMapper, Pcd> implements PcdService {
    @Override
    public List<Pcd> getAll() {
        // 获取所有数据
        List<Pcd> all = list();
        // 获取所有省
        List<Pcd> fu = all.stream().filter(s -> s.getParentId().equals(0)).toList();
        // 递归为每个fu设置其子级列表，构建完整的层级结构
        fu.forEach(s->setChildren(s,all));
         return fu;
    }
   public void setChildren(Pcd fu,List<Pcd> all){
        List<Pcd> childrenList = all.stream().filter(s -> s.getParentId().equals(fu.getId())).toList();
        fu.setChildren(childrenList);
        childrenList.forEach(s->setChildren(s,all));
   }
}
