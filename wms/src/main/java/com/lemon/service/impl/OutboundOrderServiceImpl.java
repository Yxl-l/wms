package com.lemon.service.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lemon.domain.PageResult;
import com.lemon.domain.dto.OutboundOrderDto;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.mapper.OutboundOrderMapper;
import com.lemon.domain.OutboundOrder;
import com.lemon.service.OutboundOrderService;
@Service
public class OutboundOrderServiceImpl extends ServiceImpl<OutboundOrderMapper, OutboundOrder> implements OutboundOrderService{


    @Override
    public PageResult<OutboundOrder> getPageAll(OutboundOrderDto outboundOrderDto) {
        PageHelper.startPage(outboundOrderDto.getPage(),outboundOrderDto.getPageSize());
        Page<OutboundOrder> pageInfo = (Page<OutboundOrder>) list(Wrappers.<OutboundOrder>lambdaQuery()
                //客户名称
                .like(StrUtil.isNotEmpty(outboundOrderDto.getCustomerName()),OutboundOrder::getCustomerName, outboundOrderDto.getCustomerName())
                //发货单号
                .eq(StrUtil.isNotEmpty(outboundOrderDto.getInvoiceCode()),OutboundOrder::getInvoiceCode, outboundOrderDto.getInvoiceCode())
                //状态
                .eq(ObjUtil.isNotEmpty(outboundOrderDto.getStatus()),OutboundOrder::getStatus, outboundOrderDto.getStatus()));


        return new PageResult<OutboundOrder>(pageInfo.getTotal(), pageInfo.getResult());
    }
}
