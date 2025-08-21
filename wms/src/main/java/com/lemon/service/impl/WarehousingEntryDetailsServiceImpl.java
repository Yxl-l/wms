package com.lemon.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lemon.domain.PageResult;
import com.lemon.domain.WarehousingEntryDetails;
import com.lemon.domain.dto.WarehousingEntryDetailsDto;
import com.lemon.mapper.WarehousingEntryDetailsMapper;
import com.lemon.service.WarehousingEntryDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehousingEntryDetailsServiceImpl extends ServiceImpl<WarehousingEntryDetailsMapper, WarehousingEntryDetails> implements WarehousingEntryDetailsService{

    @Override
    public PageResult<WarehousingEntryDetails> getPageAll(WarehousingEntryDetailsDto warehousingEntryDetailsDto) {
        PageHelper.startPage(warehousingEntryDetailsDto.getPage(), warehousingEntryDetailsDto.getPageSize());
        List<WarehousingEntryDetails> list = list(Wrappers.<WarehousingEntryDetails>lambdaQuery()
                //入库单
                .like(ObjectUtil.isNotEmpty(warehousingEntryDetailsDto.getWarehousingEntryId()), WarehousingEntryDetails::getWarehousingEntryId, warehousingEntryDetailsDto.getWarehousingEntryId())
                //商品skuId
                .eq(ObjectUtil.isNotEmpty(warehousingEntryDetailsDto.getSkuId()), WarehousingEntryDetails::getSkuId, warehousingEntryDetailsDto.getSkuId())
                //供应商id
                .eq(ObjectUtil.isNotEmpty(warehousingEntryDetailsDto.getSupplierId()), WarehousingEntryDetails::getSupplierId, warehousingEntryDetailsDto.getSupplierId())
                //卸货员工id
                .eq(ObjectUtil.isNotEmpty(warehousingEntryDetailsDto.getUnloadEmpId()), WarehousingEntryDetails::getUnloadEmpId, warehousingEntryDetailsDto.getUnloadEmpId())
                //状态
                .and(ObjectUtil.isNotEmpty(warehousingEntryDetailsDto.getStatus()), wrapper -> {
                    if (warehousingEntryDetailsDto.getStatus() == 4) {//如果状态为4，则查询4和5
                        wrapper.in(WarehousingEntryDetails::getStatus, 4, 5);
                    } else {
                        wrapper.eq(WarehousingEntryDetails::getStatus, warehousingEntryDetailsDto.getStatus());
                    }
                }));
        Page<WarehousingEntryDetails> pageInfo = (Page<WarehousingEntryDetails>) list;
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getResult());
    }
}
