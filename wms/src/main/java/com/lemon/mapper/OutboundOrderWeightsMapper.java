package com.lemon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.domain.OutboundOrderWeights;
import com.lemon.domain.dto.OutboundOrderWeightsDto;
import com.lemon.domain.vo.OutboundOrderWeightsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OutboundOrderWeightsMapper extends BaseMapper<OutboundOrderWeights> {
    List<OutboundOrderWeightsVo> getPageAll(OutboundOrderWeightsDto outboundOrderWeightsDto);
}