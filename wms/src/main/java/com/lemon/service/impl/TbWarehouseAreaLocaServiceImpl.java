package com.lemon.service.impl;

import cn.hutool.core.util.ObjectUtil;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lemon.domain.PageResult;
import com.lemon.domain.TbWarehouseAreaLoca;
import com.lemon.domain.dto.WarehouseAreaLocaDto;
import com.lemon.mapper.TbWarehouseAreaLocaMapper;
import com.lemon.service.TbWarehouseAreaLocaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbWarehouseAreaLocaServiceImpl extends ServiceImpl<TbWarehouseAreaLocaMapper, TbWarehouseAreaLoca> implements TbWarehouseAreaLocaService{

    @Override
    public PageResult<TbWarehouseAreaLoca> getPageAll(WarehouseAreaLocaDto warehouseAreaLocaDto) {
        PageHelper.startPage(warehouseAreaLocaDto.getPage(), warehouseAreaLocaDto.getPageSize());
        List<TbWarehouseAreaLoca> list = list(Wrappers.<TbWarehouseAreaLoca>lambdaQuery()
                .eq(ObjectUtil.isNotEmpty(warehouseAreaLocaDto.getAreaId()), TbWarehouseAreaLoca::getAreaId, warehouseAreaLocaDto.getAreaId()));
        Page<TbWarehouseAreaLoca> pageInfo = (Page<TbWarehouseAreaLoca>) list;
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getResult());
    }
}
