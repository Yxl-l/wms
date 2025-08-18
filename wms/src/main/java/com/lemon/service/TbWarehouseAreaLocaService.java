package com.lemon.service;

import com.lemon.domain.PageResult;
import com.lemon.domain.TbWarehouseAreaLoca;
import com.lemon.domain.dto.WarehouseAreaLocaDto;
import com.baomidou.mybatisplus.extension.service.IService;

public interface TbWarehouseAreaLocaService extends IService<TbWarehouseAreaLoca>{

    PageResult<TbWarehouseAreaLoca> getPageAll(WarehouseAreaLocaDto warehouseAreaLocaDto);
}
