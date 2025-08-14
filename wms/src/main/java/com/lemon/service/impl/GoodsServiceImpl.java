package com.lemon.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ClassUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lemon.domain.PageResult;
import com.lemon.domain.SKU;
import com.lemon.domain.SPU;
import com.lemon.domain.dto.GoodsDto;
import com.lemon.domain.vo.GoodsVo;
import com.lemon.mapper.GoodsMapper;
import com.lemon.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * spu
 * @author YXL
 */
@Service
@RequiredArgsConstructor
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, SPU> implements GoodsService {
    private final GoodsMapper goodsMapper;
    @Override
    public Boolean addGoods(SPU spu) {
        if (CollUtil.isEmpty(spu.getSkuList())){
            //为空则给一个空集合
            spu.setSkuList(new ArrayList<>());
        }
        List<SKU> skus = spu.getSkuList();
        //保存spu
       Boolean b = save(spu);
        //批量保存sku
        Long spuId = spu.getId();
        skus.forEach(sku -> {
            sku.setCreateTime(LocalDateTime.now());
            sku.setSpuId(spuId);
            // 添加空值检查，避免 NullPointerException
            if (sku.getProductHeight() != null && sku.getProductLength() != null && sku.getProductWidth() != null) {
                sku.setProductVolume(sku.getProductHeight() * sku.getProductLength() * sku.getProductWidth());
            } else {
                sku.setProductVolume(0); // 如果任一维度为null，则将体积设为0
            }
            goodsMapper.addSku(sku);
        });

        return b;
    }

    @Override
    public PageResult<GoodsVo> getGoodsAll(GoodsDto goodsDto) {
        PageHelper.startPage(goodsDto.getPage(),goodsDto.getPageSize());
       List<GoodsVo> goodsVos = goodsMapper.getGoodsAll(goodsDto);
        Page<GoodsVo> page = (Page<GoodsVo>) goodsVos;
        return new PageResult<>(page.getTotal(),page.getResult());
    }
}
