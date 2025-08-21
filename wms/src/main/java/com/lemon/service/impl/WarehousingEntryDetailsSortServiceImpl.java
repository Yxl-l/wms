package com.lemon.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lemon.domain.PageResult;
import com.lemon.domain.WarehousingEntryDetailsSort;
import com.lemon.domain.dto.WarehousingEntryDetailsSortDto;
import com.lemon.mapper.WarehousingEntryDetailsSortMapper;
import com.lemon.service.WarehousingEntryDetailsSortService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehousingEntryDetailsSortServiceImpl extends ServiceImpl<WarehousingEntryDetailsSortMapper, WarehousingEntryDetailsSort> implements WarehousingEntryDetailsSortService{

    @Override
    public PageResult<WarehousingEntryDetailsSort> getPageAll(WarehousingEntryDetailsSortDto warehousingEntryDetailsSortDto) {
        PageHelper.startPage(warehousingEntryDetailsSortDto.getPage(), warehousingEntryDetailsSortDto.getPageSize());
        List<WarehousingEntryDetailsSort> list = list(Wrappers.<WarehousingEntryDetailsSort>lambdaQuery()
                // 仓库入库单详情id
                .eq(ObjectUtil.isNotEmpty(warehousingEntryDetailsSortDto.getWarehousingEntryDetailsId()), WarehousingEntryDetailsSort::getWarehousingEntryDetailsId, warehousingEntryDetailsSortDto.getWarehousingEntryDetailsId())
                // skuId
                .eq(ObjectUtil.isNotEmpty(warehousingEntryDetailsSortDto.getSkuId()), WarehousingEntryDetailsSort::getSkuId, warehousingEntryDetailsSortDto.getSkuId())
                // 状态
                .eq(ObjectUtil.isNotEmpty(warehousingEntryDetailsSortDto.getStatus()), WarehousingEntryDetailsSort::getStatus, warehousingEntryDetailsSortDto.getStatus()));
        Page<WarehousingEntryDetailsSort> pageInfo = (Page<WarehousingEntryDetailsSort>) list;
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getResult());
    }
}
