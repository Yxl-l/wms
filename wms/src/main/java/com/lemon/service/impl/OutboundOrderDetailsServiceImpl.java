package com.lemon.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lemon.domain.PageResult;
import com.lemon.domain.dto.OutboundOrderPickingDto;
import com.lemon.domain.vo.OutboundOrderPickingVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.domain.OutboundOrderDetails;
import com.lemon.mapper.OutboundOrderDetailsMapper;
import com.lemon.service.OutboundOrderDetailsService;
@Service
public class OutboundOrderDetailsServiceImpl extends ServiceImpl<OutboundOrderDetailsMapper, OutboundOrderDetails> implements OutboundOrderDetailsService{
    @Override
    public OutboundOrderPickingVo getPickingById(Long id) {
        return outboundOrderDetailsMapper.getPickingById(id);
    }

    @Autowired
    private OutboundOrderDetailsMapper outboundOrderDetailsMapper;
    @Override
    public PageResult<OutboundOrderPickingVo> getPageAll(OutboundOrderPickingDto outboundOrderPickingDto) {
        PageHelper.startPage(outboundOrderPickingDto.getPage(),outboundOrderPickingDto.getPageSize());
        List<OutboundOrderPickingVo> list = outboundOrderDetailsMapper.getPageAll(outboundOrderPickingDto);
        Page<OutboundOrderPickingVo> pageInfo = (Page<OutboundOrderPickingVo>) list;
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getResult());
    }
}
