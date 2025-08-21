package com.lemon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.domain.LockTable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LockTableMapper extends BaseMapper<LockTable> {
}