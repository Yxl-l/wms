package com.lemon.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.domain.OutboundOrderDetails;
import com.lemon.mapper.OutboundOrderDetailsMapper;
import com.lemon.service.OutboundOrderDetailsService;
@Service
public class OutboundOrderDetailsServiceImpl extends ServiceImpl<OutboundOrderDetailsMapper, OutboundOrderDetails> implements OutboundOrderDetailsService{

}
