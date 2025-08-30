package com.lemon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.domain.OutboundOrderPackage;
import com.lemon.domain.dto.OutboundOrderPackageDto;
import com.lemon.domain.vo.OutboundOrderPackageVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OutboundOrderPackageMapper extends BaseMapper<OutboundOrderPackage> {
    List<OutboundOrderPackageVo> getPageAll(OutboundOrderPackageDto outboundOrderPackageDto);
}