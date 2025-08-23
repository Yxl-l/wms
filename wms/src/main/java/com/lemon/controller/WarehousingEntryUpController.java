package com.lemon.controller;

import com.lemon.domain.PageResult;
import com.lemon.domain.Result;
import com.lemon.domain.WarehousingEntryUp;
import com.lemon.domain.dto.WarehousingEntryUpDto;
import com.lemon.service.WarehousingEntryUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 入库上架表(warehousing_entry_up)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/warehousing_entry_up")
@Slf4j
@RequiredArgsConstructor
public class WarehousingEntryUpController {
    /**
     * 服务对象
     */
    private final WarehousingEntryUpService warehousingEntryUpService;

    /**
     * 分页查询入库上架
     */
    @GetMapping
    public PageResult<WarehousingEntryUp> getPageAll(WarehousingEntryUpDto warehousingEntryUpDto) {
        log.info("分页查询入库上架");
        return warehousingEntryUpService.getPageAll(warehousingEntryUpDto);
    }

    /**
     * 新增入库上架
     */
    @PostMapping
    public Result add(@RequestBody WarehousingEntryUp warehousingEntryUp) {
        log.info("新增入库上架");
        warehousingEntryUp.setCreateTime(new Date());
        return warehousingEntryUpService.save(warehousingEntryUp) ? Result.success("添加成功") : Result.error("添加失败");
    }

    /**
     * 修改入库上架
     */
    @PutMapping
    public Result update(@RequestBody WarehousingEntryUp warehousingEntryUp) {
        log.info("修改入库上架");
        return warehousingEntryUpService.updateById(warehousingEntryUp) ? Result.success("修改成功") : Result.error("修改失败");
    }

    /**
     * 删除入库上架
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        log.info("删除入库上架");
        return warehousingEntryUpService.removeById(id) ? Result.success("删除成功") : Result.error("删除失败");
    }

    /**
     * 根据id查询入库上架
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        log.info("根据id查询入库上架");
        return Result.success(warehousingEntryUpService.getById(id));
    }
}
