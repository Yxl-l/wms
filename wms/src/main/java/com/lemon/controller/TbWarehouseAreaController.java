package com.lemon.controller;
import com.lemon.domain.PageResult;
import com.lemon.domain.Result;
import com.lemon.domain.TbWarehouseArea;
import com.lemon.domain.dto.WarehouseAreaDto;
import com.lemon.mapper.TbWarehouseAreaMapper;
import com.lemon.service.TbWarehouseAreaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * 仓库库区表(tb_warehouse_area)表控制层
 *
 * @author xxxxx
 */
@Slf4j
@RestController
@RequestMapping("/tb_warehouse_area")
@RequiredArgsConstructor
public class TbWarehouseAreaController {


    private final TbWarehouseAreaService tbWarehouseAreaService;
    private  final TbWarehouseAreaMapper tbWarehouseAreaMapper;

    /**
     * 分页查询库区

     */
    @GetMapping
    public PageResult<TbWarehouseArea> getPageAll(WarehouseAreaDto warehouseDto) {
        log.info("分页查询仓库");
        return tbWarehouseAreaService.getPageAll(warehouseDto);
    }
    /**
     * 添加库区
     */
    @PostMapping
    public Result add(@RequestBody TbWarehouseArea tbWarehouseArea) {
        log.info("添加库区");
        tbWarehouseArea.setCreateTime(new Date());
        return tbWarehouseAreaService.save(tbWarehouseArea) ? Result.success("添加成功") : Result.error("添加失败");
    }
    /**
     * 数据字典
     */
    @GetMapping("/tb_dictionary")
    public Result getDictionary() {
        log.info("数据字典");
        return Result.success(tbWarehouseAreaService.getDictionary());
    }
    /**
     * 修改库区
     */
    @PutMapping
    public Result update(@RequestBody TbWarehouseArea tbWarehouseArea) {
        log.info("修改库区");
        return tbWarehouseAreaService.updateById(tbWarehouseArea) ? Result.success("修改成功") : Result.error("修改失败");
    }

    /**
     * 删除库区
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        log.info("删除库区");
        return tbWarehouseAreaService.removeById(id) ? Result.success("删除成功") : Result.error("删除失败");
    }

    /**
     * 根据id查询库区
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        log.info("根据id查询库区");
        return Result.success(tbWarehouseAreaMapper.selectById(id));
    }
}
