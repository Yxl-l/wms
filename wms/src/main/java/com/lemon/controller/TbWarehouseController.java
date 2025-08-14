package com.lemon.controller;

import com.lemon.domain.PageResult;
import com.lemon.domain.Result;
import com.lemon.domain.TbWarehouse;
import com.lemon.domain.dto.WarehouseDto;
import com.lemon.service.TbWarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 仓库管理
 * @author YXL
 */
@RestController
@RequestMapping("/warehouse")
@Slf4j
@RequiredArgsConstructor
public class TbWarehouseController {
    private final TbWarehouseService warehouseService;

    /**
     * 分页查询仓库
     * @param warehouseDto
     * @return
     */
    @GetMapping
    public PageResult<TbWarehouse> getPageAll(WarehouseDto warehouseDto) {
        log.info("分页查询仓库");
        return warehouseService.getPageAll(warehouseDto);
    }
    /**
     * 新增仓库
     */
    @PostMapping
    public Result add(@RequestBody TbWarehouse warehouse) {
        log.info("新增仓库");
        warehouse.setCreateTime(LocalDateTime.now());
        return warehouseService.save(warehouse)? Result.success("添加成功") : Result.error("添加失败");
    }
    /**
     * 获取全部仓库
     */
    @GetMapping("/all")
    public Result getAll() {
        log.info("获取全部仓库");
        return Result.success(warehouseService.list());
    }

}
