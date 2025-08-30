package com.lemon.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lemon.domain.PageResult;
import com.lemon.domain.dto.OutboundOrderPackageDto;
import com.lemon.domain.vo.OutboundOrderPackageVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.mapper.OutboundOrderPackageMapper;
import com.lemon.domain.OutboundOrderPackage;
import com.lemon.service.OutboundOrderPackageService;
@Service
public class OutboundOrderPackageServiceImpl extends ServiceImpl<OutboundOrderPackageMapper, OutboundOrderPackage> implements OutboundOrderPackageService{

    @Autowired
    private OutboundOrderPackageMapper outboundOrderPackageMapper;
    @Override
    public PageResult<OutboundOrderPackageVo> getPageAll(OutboundOrderPackageDto outboundOrderPackageDto) {
        PageHelper.startPage(outboundOrderPackageDto.getPage(), outboundOrderPackageDto.getPageSize());
        List<OutboundOrderPackageVo> list = outboundOrderPackageMapper.getPageAll(outboundOrderPackageDto);
        Page<OutboundOrderPackageVo> pageInfo = (Page<OutboundOrderPackageVo>) list;
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getResult());
    }
}
