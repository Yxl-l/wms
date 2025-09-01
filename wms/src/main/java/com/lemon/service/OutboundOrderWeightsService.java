package com.lemon.service;

import com.lemon.domain.OutboundOrderWeights;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.domain.PageResult;
import com.lemon.domain.dto.OutboundOrderWeightsDto;
import com.lemon.domain.vo.OutboundOrderWeightsVo;

public interface OutboundOrderWeightsService extends IService<OutboundOrderWeights>{


    PageResult<OutboundOrderWeightsVo> getPageAll(OutboundOrderWeightsDto outboundOrderWeightsDto);
}
