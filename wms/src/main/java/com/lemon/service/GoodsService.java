package com.lemon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.domain.PageResult;
import com.lemon.domain.SKU;
import com.lemon.domain.SPU;
import com.lemon.domain.dto.GoodsDto;
import com.lemon.domain.vo.GoodsVo;

import java.util.List;

public interface GoodsService extends IService<SPU> {
    Boolean addGoods(SPU spu);

    PageResult<GoodsVo> getGoodsAll(GoodsDto goodsDto);

    List<SKU> getAllSku();
}
