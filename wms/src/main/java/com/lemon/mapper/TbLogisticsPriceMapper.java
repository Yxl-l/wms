package com.lemon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.domain.PageResult;
import com.lemon.domain.TbLogisticsPrice;
import com.lemon.domain.dto.TbLogisticsPriceDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbLogisticsPriceMapper extends BaseMapper<TbLogisticsPrice> {

}