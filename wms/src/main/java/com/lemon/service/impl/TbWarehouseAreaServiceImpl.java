package com.lemon.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lemon.domain.PageResult;
import com.lemon.domain.dto.WarehouseAreaDto;
import com.lemon.domain.vo.TbDictionaryVo;
import com.lemon.service.TbWarehouseAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lemon.domain.TbWarehouseArea;
import com.lemon.mapper.TbWarehouseAreaMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TbWarehouseAreaServiceImpl extends ServiceImpl<TbWarehouseAreaMapper, TbWarehouseArea> implements TbWarehouseAreaService {
    private final TbWarehouseAreaMapper tbWarehouseAreaMapper;
    /**
     * 分页查询库区
     * @param warehouseDto
     * @return
     */
    @Override
    public PageResult<TbWarehouseArea> getPageAll(WarehouseAreaDto warehouseDto) {
        PageHelper.startPage(warehouseDto.getPage(),warehouseDto.getPageSize());
        List<TbWarehouseArea> list = list(Wrappers.<TbWarehouseArea>lambdaQuery()
                .like(StrUtil.isNotEmpty(warehouseDto.getName()),TbWarehouseArea::getName, warehouseDto.getName())
                .eq(ObjectUtil.isNotEmpty(warehouseDto.getWarehouseId()),TbWarehouseArea::getWarehouseId, warehouseDto.getWarehouseId()));
        Page<TbWarehouseArea> pageInfo = (Page<TbWarehouseArea>) list;
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getResult());
    }

    @Override
    public List<TbDictionaryVo> getDictionary() {
        return tbWarehouseAreaMapper.getDictionary();
    }
}
