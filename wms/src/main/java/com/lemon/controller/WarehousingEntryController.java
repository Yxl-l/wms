package com.lemon.controller;

import com.lemon.domain.PageResult;
import com.lemon.domain.Result;
import com.lemon.domain.WarehousingEntry;
import com.lemon.domain.dto.WarehousingEntryDto;
import com.lemon.service.WarehousingEntryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 入库单表(warehousing_entry)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/warehousing_entry")
@Slf4j
@RequiredArgsConstructor
public class WarehousingEntryController {
    /**
     * 服务对象
     */
    private final WarehousingEntryService warehousingEntryService;

    /**
     * 分页查询入库单
     */
    @GetMapping
    public PageResult<WarehousingEntry> getPageAll(WarehousingEntryDto warehousingEntryDto) {
        log.info("分页查询入库单");
        return warehousingEntryService.getPageAll(warehousingEntryDto);
    }

    /**
     * 新增入库单
     */
    @PostMapping
    public Result add(@RequestBody WarehousingEntry warehousingEntry) {
        log.info("新增入库单");
        warehousingEntry.setCreateTime(new Date());
        return warehousingEntryService.save(warehousingEntry) ? Result.success("添加成功") : Result.error("添加失败");
    }

    /**
     * 修改入库单
     */
    @PutMapping
    public Result update(@RequestBody WarehousingEntry warehousingEntry) {
        log.info("修改入库单");
        return warehousingEntryService.updateById(warehousingEntry) ? Result.success("修改成功") : Result.error("修改失败");
    }

    /**
     * 删除入库单
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        log.info("删除入库单");
        return warehousingEntryService.removeById(id) ? Result.success("删除成功") : Result.error("删除失败");
    }

    /**
     * 根据id查询入库单
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        log.info("根据id查询入库单");
        return Result.success(warehousingEntryService.getById(id));
    }
    /**
     * 查询全部入库单
     */
    @GetMapping("/all")
    public Result getAll() {
        log.info("查询全部入库单");
        return Result.success(warehousingEntryService.list());
    }
    /**
     * 修改入库单状态
     */
    @PutMapping("/status")
    public Result updateStatus(@RequestBody WarehousingEntry warehousingEntry) {
        log.info("修改入库单状态");
        warehousingEntry.setUpdateTime(new Date());
        return warehousingEntryService.updateById(warehousingEntry) ? Result.success("修改成功") : Result.error("修改失败");
    }
}
