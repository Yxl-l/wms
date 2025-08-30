package com.lemon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.domain.OutboundOrderDetails;
import com.lemon.domain.dto.OutboundOrderPickingDto;
import com.lemon.domain.vo.OutboundOrderPickingVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OutboundOrderDetailsMapper extends BaseMapper<OutboundOrderDetails> {
    List<OutboundOrderPickingVo> getPageAll(OutboundOrderPickingDto outboundOrderPickingDto);


}