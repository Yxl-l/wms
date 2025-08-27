package com.lemon.service;

import com.lemon.domain.OutboundOrderDetails;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.domain.PageResult;
import com.lemon.domain.dto.OutboundOrderPickingDto;
import com.lemon.domain.vo.OutboundOrderPickingVo;

public interface OutboundOrderDetailsService extends IService<OutboundOrderDetails>{


    PageResult<OutboundOrderPickingVo> getPageAll(OutboundOrderPickingDto outboundOrderPickingDto);

    OutboundOrderPickingVo getPickingById(Long id);
}
