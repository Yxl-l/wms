package com.lemon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.domain.Pcd;

import java.util.List;

/**
 * 省市区业务层
 * @author YXL
 */
public interface PcdService extends IService<Pcd> {
    List<Pcd> getAll();
}
