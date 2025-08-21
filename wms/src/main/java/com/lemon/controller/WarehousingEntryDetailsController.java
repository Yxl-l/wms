package com.lemon.controller;

import com.lemon.domain.PageResult;
import com.lemon.domain.Result;
import com.lemon.domain.WarehousingEntry;
import com.lemon.domain.WarehousingEntryDetails;
import com.lemon.domain.dto.WarehousingEntryDetailsDto;
import com.lemon.service.WarehousingEntryDetailsService;
import com.lemon.service.WarehousingEntryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 入库明细表(warehousing_entry_details)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/warehousing_entry_details")
@Slf4j
@RequiredArgsConstructor
public class WarehousingEntryDetailsController {

    /**
     * 服务对象
     */
    private final WarehousingEntryDetailsService warehousingEntryDetailsService;
    private final WarehousingEntryService warehousingEntryService;

    /**
     * 分页查询入库明细
     */
    @GetMapping
    public PageResult<WarehousingEntryDetails> getPageAll(WarehousingEntryDetailsDto warehousingEntryDetailsDto) {
        log.info("分页查询入库明细");
        return warehousingEntryDetailsService.getPageAll(warehousingEntryDetailsDto);
    }

    /**
     * 新增入库明细
     */
    @PostMapping
    public Result add(@RequestBody WarehousingEntryDetails warehousingEntryDetails) {
        log.info("新增入库明细");
        warehousingEntryDetails.setCreateTime(new Date());
        return warehousingEntryDetailsService.save(warehousingEntryDetails) ? Result.success("添加成功") : Result.error("添加失败");
    }

    /**
     * 修改入库明细
     */
    @PutMapping
    public Result update(@RequestBody WarehousingEntryDetails warehousingEntryDetails) {
        log.info("修改入库明细");
        return warehousingEntryDetailsService.updateById(warehousingEntryDetails) ? Result.success("修改成功") : Result.error("修改失败");
    }

    /**
     * 删除入库明细
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        log.info("删除入库明细");
        return warehousingEntryDetailsService.removeById(id) ? Result.success("删除成功") : Result.error("删除失败");
    }

    /**
     * 根据id查询入库明细
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        log.info("根据id查询入库明细");
        return Result.success(warehousingEntryDetailsService.getById(id));
    }

    /**
     * 查询所有入库明细
     */
    @GetMapping("/all")
    public Result getAll() {
        log.info("查询所有入库明细");
        return Result.success(warehousingEntryDetailsService.list());
    }
    /**
     * 修改入库明细状态
     */
    @PutMapping("/status")
    public Result updateStatus(@RequestBody WarehousingEntryDetails warehousingEntryDetails) {
        log.info("修改入库明细状态");
        warehousingEntryDetails.setUpdateTime(new Date());
        if (warehousingEntryDetails.getStatus()==4||warehousingEntryDetails.getStatus()==5){
            //当入库明细修改为分拣中4或分拣完成5 时修改入库单状态为拣货中 4
            //获取入库单id
            WarehousingEntryDetails warehousingEntryDetailsNew = warehousingEntryDetailsService.getById(warehousingEntryDetails.getId());
            Long weId = warehousingEntryDetailsNew.getWarehousingEntryId();
            WarehousingEntry warehousingEntry = new WarehousingEntry();
            warehousingEntry.setId(weId);
            warehousingEntry.setStatus(4);
            //修改入库单状态
            warehousingEntryService.updateById(warehousingEntry);
        }
        return warehousingEntryDetailsService.updateById(warehousingEntryDetails) ? Result.success("修改成功") : Result.error("修改失败");
    }
}
