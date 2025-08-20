package com.lemon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.domain.SKU;
import com.lemon.domain.SPU;
import com.lemon.domain.dto.GoodsDto;
import com.lemon.domain.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper extends BaseMapper<SPU> {

    void addSku(SKU sku);

    List<GoodsVo> getGoodsAll(GoodsDto goodsDto);

    List<SKU> getAllSku();
}
