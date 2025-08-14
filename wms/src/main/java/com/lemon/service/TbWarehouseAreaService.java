package com.lemon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.domain.PageResult;
import com.lemon.domain.TbWarehouseArea;
import com.lemon.domain.dto.WarehouseAreaDto;
import com.lemon.domain.vo.TbDictionaryVo;

import java.util.List;

public interface TbWarehouseAreaService extends IService<TbWarehouseArea> {


    PageResult<TbWarehouseArea> getPageAll(WarehouseAreaDto warehouseDto);

    List<TbDictionaryVo> getDictionary();
}
