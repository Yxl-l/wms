package com.lemon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.domain.PageResult;
import com.lemon.domain.TbWarehouse;
import com.lemon.domain.dto.WarehouseDto;

/**
 * Warehouse业务层
 */
public interface TbWarehouseService extends IService<TbWarehouse> {
    PageResult<TbWarehouse> getPageAll(WarehouseDto warehouseDto);
}
