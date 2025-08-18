package com.lemon.controller;

import com.lemon.domain.PageResult;
import com.lemon.domain.Result;
import com.lemon.domain.TbWarehouseAreaLoca;
import com.lemon.domain.dto.WarehouseAreaLocaDto;
import com.lemon.service.TbWarehouseAreaLocaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 库位表(tb_warehouse_area_loca)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/tb_warehouse_area_loca")
@Slf4j
@RequiredArgsConstructor
public class TbWarehouseAreaLocaController {
    /**
     * 库位表服务
     */
    private final TbWarehouseAreaLocaService tbWarehouseAreaLocaService;

    /**
     * 分页查询库位
     */
    @GetMapping
    public PageResult<TbWarehouseAreaLoca> getPageAll(WarehouseAreaLocaDto warehouseAreaLocaDto) {
        log.info("分页查询库位");
        return tbWarehouseAreaLocaService.getPageAll(warehouseAreaLocaDto);
    }

    /**
     * 新增库位
     */
    @PostMapping
    public Result add(@RequestBody TbWarehouseAreaLoca tbWarehouseAreaLoca) {
        log.info("新增库位");
        tbWarehouseAreaLoca.setCreateTime(new Date());
        return tbWarehouseAreaLocaService.save(tbWarehouseAreaLoca) ? Result.success("添加成功") : Result.error("添加失败");
    }

    /**
     * 修改库位
     */
    @PutMapping
    public Result update(@RequestBody TbWarehouseAreaLoca tbWarehouseAreaLoca) {
        log.info("修改库位");
        return tbWarehouseAreaLocaService.updateById(tbWarehouseAreaLoca) ? Result.success("修改成功") : Result.error("修改失败");
    }

    /**
     * 删除库位
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        log.info("删除库位");
        return tbWarehouseAreaLocaService.removeById(id) ? Result.success("删除成功") : Result.error("删除失败");
    }

    /**
     * 根据id查询库位
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        log.info("根据id查询库位");
        return Result.success(tbWarehouseAreaLocaService.getById(id));
    }
}
