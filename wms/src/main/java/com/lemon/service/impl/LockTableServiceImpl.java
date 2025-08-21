package com.lemon.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.domain.LockTable;
import com.lemon.mapper.LockTableMapper;
import com.lemon.service.LockTableService;
@Service
public class LockTableServiceImpl extends ServiceImpl<LockTableMapper, LockTable> implements LockTableService{

}
