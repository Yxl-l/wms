package com.lemon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.domain.OutboundOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OutboundOrderMapper extends BaseMapper<OutboundOrder> {
}