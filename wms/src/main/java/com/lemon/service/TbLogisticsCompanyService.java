package com.lemon.service;

import com.lemon.domain.PageResult;
import com.lemon.domain.TbLogisticsCompany;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.domain.dto.TbLogisticsCompanyDto;

public interface TbLogisticsCompanyService extends IService<TbLogisticsCompany>{


    PageResult<TbLogisticsCompany> getPageAll(TbLogisticsCompanyDto tbLogisticsCompanyDto);
}
