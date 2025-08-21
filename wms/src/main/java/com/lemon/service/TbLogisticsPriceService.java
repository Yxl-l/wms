package com.lemon.service;

import com.lemon.domain.PageResult;
import com.lemon.domain.TbLogisticsPrice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.domain.dto.TbLogisticsPriceDto;

public interface TbLogisticsPriceService extends IService<TbLogisticsPrice>{


    PageResult<TbLogisticsPrice> getPageAll(TbLogisticsPriceDto tbLogisticsPriceDto);
}
