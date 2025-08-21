package com.lemon.controller;
import com.lemon.domain.PageResult;
import com.lemon.domain.Result;
import com.lemon.domain.WarehousingEntryDetailsSort;
import com.lemon.domain.dto.WarehousingEntryDetailsSortDto;
import com.lemon.service.WarehousingEntryDetailsSortService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 入库明细分拣表(warehousing_entry_details_sort)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/warehousing_entry_details_sort")
@Slf4j
@RequiredArgsConstructor
public class WarehousingEntryDetailsSortController {
    /**
     * 服务对象
     */
    private final WarehousingEntryDetailsSortService warehousingEntryDetailsSortService;

    /**
     * 分页查询入库明细分拣
     */
    @GetMapping
    public PageResult<WarehousingEntryDetailsSort> getPageAll(WarehousingEntryDetailsSortDto warehousingEntryDetailsSortDto) {
        log.info("分页查询入库明细分拣");
        return warehousingEntryDetailsSortService.getPageAll(warehousingEntryDetailsSortDto);
    }

    /**
     * 新增入库明细分拣
     */
    @PostMapping
    public Result add(@RequestBody WarehousingEntryDetailsSort warehousingEntryDetailsSort) {
        log.info("新增入库明细分拣");
        warehousingEntryDetailsSort.setCreateTime(new Date());
        return warehousingEntryDetailsSortService.save(warehousingEntryDetailsSort) ? Result.success("添加成功") : Result.error("添加失败");
    }

    /**
     * 修改入库明细分拣
     */
    @PutMapping
    public Result update(@RequestBody WarehousingEntryDetailsSort warehousingEntryDetailsSort) {
        log.info("修改入库明细分拣");
        return warehousingEntryDetailsSortService.updateById(warehousingEntryDetailsSort) ? Result.success("修改成功") : Result.error("修改失败");
    }

    /**
     * 删除入库明细分拣
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        log.info("删除入库明细分拣");
        return warehousingEntryDetailsSortService.removeById(id) ? Result.success("删除成功") : Result.error("删除失败");
    }

    /**
     * 根据id查询入库明细分拣
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        log.info("根据id查询入库明细分拣");
        return Result.success(warehousingEntryDetailsSortService.getById(id));
    }

    /**
     * 查询全部入库明细分拣
     */
    @GetMapping("/all")
    public Result getAll() {
        log.info("查询全部入库明细分拣");
        return Result.success(warehousingEntryDetailsSortService.list());
    }
}
