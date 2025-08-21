package com.lemon.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lemon.domain.PageResult;
import com.lemon.domain.dto.TbLogisticsCompanyDto;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.mapper.TbLogisticsCompanyMapper;
import com.lemon.domain.TbLogisticsCompany;
import com.lemon.service.TbLogisticsCompanyService;
@Service
public class TbLogisticsCompanyServiceImpl extends ServiceImpl<TbLogisticsCompanyMapper, TbLogisticsCompany> implements TbLogisticsCompanyService{

    @Override
    public PageResult<TbLogisticsCompany> getPageAll(TbLogisticsCompanyDto tbLogisticsCompanyDto) {
        PageHelper.startPage(tbLogisticsCompanyDto.getPage(),tbLogisticsCompanyDto.getPageSize());
        List<TbLogisticsCompany> list = list(Wrappers.<TbLogisticsCompany>lambdaQuery()
                .like(StrUtil.isNotEmpty(tbLogisticsCompanyDto.getCompanyName()),TbLogisticsCompany::getCompanyName, tbLogisticsCompanyDto.getCompanyName()));
        Page<TbLogisticsCompany> pageInfo = (Page<TbLogisticsCompany>) list;
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getResult());

    }
}
