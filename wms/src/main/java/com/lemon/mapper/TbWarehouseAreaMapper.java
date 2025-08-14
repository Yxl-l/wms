package com.lemon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.domain.TbWarehouseArea;
import com.lemon.domain.vo.TbDictionaryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbWarehouseAreaMapper extends BaseMapper<TbWarehouseArea> {

    List<TbDictionaryVo> getDictionary();
}