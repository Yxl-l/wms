package com.lemon.service;

import com.lemon.domain.OutboundOrderPackage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.domain.PageResult;
import com.lemon.domain.dto.OutboundOrderPackageDto;
import com.lemon.domain.vo.OutboundOrderPackageVo;

public interface OutboundOrderPackageService extends IService<OutboundOrderPackage>{


    PageResult<OutboundOrderPackageVo> getPageAll(OutboundOrderPackageDto outboundOrderPackageDto);
}
