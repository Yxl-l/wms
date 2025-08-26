package com.lemon.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.domain.OutboundOrderWeights;
import com.lemon.mapper.OutboundOrderWeightsMapper;
import com.lemon.service.OutboundOrderWeightsService;
@Service
public class OutboundOrderWeightsServiceImpl extends ServiceImpl<OutboundOrderWeightsMapper, OutboundOrderWeights> implements OutboundOrderWeightsService{

}
