package com.lemon.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lemon.domain.PageResult;
import com.lemon.domain.WarehousingEntry;
import com.lemon.domain.dto.WarehousingEntryDto;
import com.lemon.mapper.WarehousingEntryMapper;
import com.lemon.service.WarehousingEntryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehousingEntryServiceImpl extends ServiceImpl<WarehousingEntryMapper, WarehousingEntry> implements WarehousingEntryService{

    @Override
    public PageResult<WarehousingEntry> getPageAll(WarehousingEntryDto warehousingEntryDto) {
        PageHelper.startPage(warehousingEntryDto.getPage(), warehousingEntryDto.getPageSize());
        List<WarehousingEntry> list = list(Wrappers.<WarehousingEntry>lambdaQuery()
                .like(StrUtil.isNotEmpty(warehousingEntryDto.getBatchNumber()), WarehousingEntry::getBatchNumber, warehousingEntryDto.getBatchNumber())
                .eq(ObjectUtil.isNotEmpty(warehousingEntryDto.getStatus()), WarehousingEntry::getStatus, warehousingEntryDto.getStatus()));
        Page<WarehousingEntry> pageInfo = (Page<WarehousingEntry>) list;
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getResult());
    }
}
