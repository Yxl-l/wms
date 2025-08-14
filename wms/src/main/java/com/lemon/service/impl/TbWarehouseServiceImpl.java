package com.lemon.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lemon.domain.PageResult;
import com.lemon.domain.TbWarehouse;
import com.lemon.domain.dto.WarehouseDto;
import com.lemon.mapper.TbWarehouseMapper;
import com.lemon.service.TbWarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Warehouse业务层实现类
 * @author YXL
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class TbWarehouseServiceImpl extends ServiceImpl<TbWarehouseMapper, TbWarehouse> implements TbWarehouseService {
    private final TbWarehouseMapper warehouseMapper;

    /**
    *分页查询
     */
    @Override
    public PageResult<TbWarehouse> getPageAll(WarehouseDto warehouseDto) {
        PageHelper.startPage(warehouseDto.getPage(),warehouseDto.getPageSize());
        List<TbWarehouse> list = warehouseMapper.selectList(Wrappers.<TbWarehouse>lambdaQuery()
                .like(StrUtil.isNotEmpty(warehouseDto.getName()), TbWarehouse::getName, warehouseDto.getName())
                .eq(ObjectUtil.isNotEmpty(warehouseDto.getCityId()), TbWarehouse::getCityId, warehouseDto.getCityId())
        );
        Page<TbWarehouse> pageInfo = (Page<TbWarehouse>) list;
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getResult());
    }
}
