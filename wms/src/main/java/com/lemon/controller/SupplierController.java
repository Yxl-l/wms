package com.lemon.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lemon.domain.PageResult;
import com.lemon.domain.Result;
import com.lemon.domain.Supplier;
import com.lemon.domain.pageParam.SupplierPageParam;
import com.lemon.service.SupplierService;
import com.lemon.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 供应商管理、
 * @author YXL
 */
@RestController
@RequestMapping("/supplier")
@Slf4j
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;
    /**
     * 分页查询获取供应商列表
     * @return
     */
    @GetMapping
    public Result getAll(SupplierPageParam supplierPageParam) {
        log.info("分页查询获取供应商列表");
        LambdaQueryWrapper<Supplier> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(supplierPageParam.getSupplierName())){
            wrapper.like(Supplier::getSupplierName,supplierPageParam.getSupplierName());
        }
        if (supplierPageParam.getCityCode()!=null){
            wrapper.eq(Supplier::getCityCode,supplierPageParam.getCityCode());
        }

        //分页查询 - 先设置分页参数
        PageHelper.startPage(supplierPageParam.getPage(),supplierPageParam.getPageSize());
        // 将查询结果转换为Page对象
        Page<Supplier> pageInfo = (Page<Supplier>) supplierService.list(wrapper);
        return Result.success(new PageResult<>(pageInfo.getTotal(),pageInfo.getResult()) );
    }
    @GetMapping("/getAll")
    public Result getAll() {
        log.info("获取所有供应商");
        return Result.success(supplierService.list());
    }
}
