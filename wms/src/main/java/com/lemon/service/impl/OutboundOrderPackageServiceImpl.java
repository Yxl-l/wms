package com.lemon.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.mapper.OutboundOrderPackageMapper;
import com.lemon.domain.OutboundOrderPackage;
import com.lemon.service.OutboundOrderPackageService;
@Service
public class OutboundOrderPackageServiceImpl extends ServiceImpl<OutboundOrderPackageMapper, OutboundOrderPackage> implements OutboundOrderPackageService{

}
