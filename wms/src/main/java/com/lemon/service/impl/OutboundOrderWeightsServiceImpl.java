package com.lemon.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lemon.domain.PageResult;
import com.lemon.domain.dto.OutboundOrderWeightsDto;
import com.lemon.domain.vo.OutboundOrderWeightsVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.domain.OutboundOrderWeights;
import com.lemon.mapper.OutboundOrderWeightsMapper;
import com.lemon.service.OutboundOrderWeightsService;
@Service
public class OutboundOrderWeightsServiceImpl extends ServiceImpl<OutboundOrderWeightsMapper, OutboundOrderWeights> implements OutboundOrderWeightsService{
    @Autowired
private OutboundOrderWeightsMapper outboundOrderWeightsMapper;
    @Override
    public PageResult<OutboundOrderWeightsVo> getPageAll(OutboundOrderWeightsDto outboundOrderWeightsDto) {
        PageHelper.startPage(outboundOrderWeightsDto.getPage(),outboundOrderWeightsDto.getPageSize());
        List<OutboundOrderWeightsVo> list = outboundOrderWeightsMapper.getPageAll(outboundOrderWeightsDto);
        Page<OutboundOrderWeightsVo> page = (Page<OutboundOrderWeightsVo>) list;
        return new PageResult<>(page.getTotal(),page.getResult());
    }


}
