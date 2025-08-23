package com.lemon.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lemon.domain.PageResult;
import com.lemon.domain.WarehousingEntryUp;
import com.lemon.domain.dto.WarehousingEntryUpDto;
import com.lemon.mapper.WarehousingEntryUpMapper;
import com.lemon.service.WarehousingEntryUpService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WarehousingEntryUpServiceImpl extends ServiceImpl<WarehousingEntryUpMapper, WarehousingEntryUp> implements WarehousingEntryUpService{

    @Override
    public PageResult<WarehousingEntryUp> getPageAll(WarehousingEntryUpDto warehousingEntryUpDto) {
        PageHelper.startPage(warehousingEntryUpDto.getPage(), warehousingEntryUpDto.getPageSize());
        List<WarehousingEntryUp> list = list(Wrappers.<WarehousingEntryUp>lambdaQuery()
                // 仓库id
                .eq(ObjectUtil.isNotEmpty(warehousingEntryUpDto.getWarehouseId()), WarehousingEntryUp::getWarehouseId, warehousingEntryUpDto.getWarehouseId())
                // skuId
                .eq(ObjectUtil.isNotEmpty(warehousingEntryUpDto.getSkuId()), WarehousingEntryUp::getSkuId, warehousingEntryUpDto.getSkuId())
                // 状态
                .eq(ObjectUtil.isNotEmpty(warehousingEntryUpDto.getStatus()), WarehousingEntryUp::getStatus, warehousingEntryUpDto.getStatus()));
        Page<WarehousingEntryUp> pageInfo = (Page<WarehousingEntryUp>) list;
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getResult());
    }
}
