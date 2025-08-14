package com.lemon.controller;
import com.lemon.domain.PageResult;
import com.lemon.domain.Result;
import com.lemon.domain.TbWarehouseArea;
import com.lemon.domain.dto.WarehouseAreaDto;
import com.lemon.mapper.TbWarehouseAreaMapper;
import com.lemon.service.impl.TbWarehouseAreaServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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


    private final TbWarehouseAreaServiceImpl tbWarehouseAreaService;
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
    public boolean add(@RequestBody TbWarehouseArea tbWarehouseArea) {
        log.info("添加库区");
        tbWarehouseArea.setCreateTime(new Date());
        return tbWarehouseAreaService.save(tbWarehouseArea);
    }
    /**
     * 数据字典
     */
    @GetMapping("/tb_dictionary")
    public Result getDictionary() {
        log.info("数据字典");
        return Result.success(tbWarehouseAreaService.getDictionary());
    }



}
