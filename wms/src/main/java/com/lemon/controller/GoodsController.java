package com.lemon.controller;

import com.lemon.domain.PageResult;
import com.lemon.domain.Result;
import com.lemon.domain.SKU;
import com.lemon.domain.SPU;
import com.lemon.domain.dto.GoodsDto;
import com.lemon.domain.vo.GoodsVo;
import com.lemon.mapper.GoodsMapper;
import com.lemon.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * 商品
 * @author YXL
 */
@RestController
@RequestMapping("/goods")
@Slf4j
@RequiredArgsConstructor
public class GoodsController {
private final GoodsService goodsService;

    /**
     * 添加商品
     * @param spu
     * @return
     */
    @PutMapping
    public Result addGoods(@RequestBody SPU spu){

    return  goodsService.addGoods(spu)? Result.success("添加成功"):Result.error("添加失败");
}

/**
 * 条件分页查询商品
 */
    @GetMapping
    private Result getGoodsAll (GoodsDto goodsDto){
        PageResult<GoodsVo> pageResult = goodsService.getGoodsAll(goodsDto);
        return Result.success(pageResult);
    }
}
