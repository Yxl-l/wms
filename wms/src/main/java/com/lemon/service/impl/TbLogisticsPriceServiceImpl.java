package com.lemon.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lemon.domain.PageResult;
import com.lemon.domain.dto.TbLogisticsPriceDto;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.domain.TbLogisticsPrice;
import com.lemon.mapper.TbLogisticsPriceMapper;
import com.lemon.service.TbLogisticsPriceService;
@Service
public class TbLogisticsPriceServiceImpl extends ServiceImpl<TbLogisticsPriceMapper, TbLogisticsPrice> implements TbLogisticsPriceService{

    @Override
    public PageResult<TbLogisticsPrice> getPageAll(TbLogisticsPriceDto tbLogisticsPriceDto) {
        PageHelper.startPage(tbLogisticsPriceDto.getPage(),tbLogisticsPriceDto.getPageSize());
        List<TbLogisticsPrice> list = list(Wrappers.<TbLogisticsPrice>lambdaQuery()
                .eq(ObjectUtil.isNotEmpty(tbLogisticsPriceDto.getCategory()),TbLogisticsPrice::getCategory,tbLogisticsPriceDto.getCategory())
                .eq(ObjectUtil.isNotEmpty(tbLogisticsPriceDto.getLogisticsCompanyId()),TbLogisticsPrice::getLogisticsCompanyId,tbLogisticsPriceDto.getLogisticsCompanyId()));
        Page<TbLogisticsPrice> pageInfo = (Page<TbLogisticsPrice>) list;
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getResult());
    }
}
