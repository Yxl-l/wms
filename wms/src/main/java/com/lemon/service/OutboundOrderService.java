package com.lemon.service;

import com.lemon.domain.OutboundOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.domain.PageResult;
import com.lemon.domain.dto.OutboundOrderDto;

public interface OutboundOrderService extends IService<OutboundOrder>{


    PageResult<OutboundOrder> getPageAll(OutboundOrderDto outboundOrderDto);
}
