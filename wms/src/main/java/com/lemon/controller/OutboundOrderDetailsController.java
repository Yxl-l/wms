package com.lemon.controller;
import com.lemon.domain.OutboundOrderDetails;
import com.lemon.domain.dto.OutboundOrderPickingDto;
import com.lemon.service.impl.OutboundOrderDetailsServiceImpl;
import com.lemon.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 出库单明细(outbound_order_details)表控制层
 * 待检货-已拣货组件
 *
 * @author xxxxx
 */
@Slf4j
@RestController
@RequestMapping("/outbound_order_details")
public class OutboundOrderDetailsController {
    /**
     * 服务对象
     */
    @Autowired
    private OutboundOrderDetailsServiceImpl outboundOrderDetailsServiceImpl;


    /**
     * 根据id查询出库单明细
     */
    @GetMapping("/{id}")
    public Result getPickingById(@PathVariable Long id) {
        return Result.success(outboundOrderDetailsServiceImpl.getPickingById(id));
    }
    /**
     * 分页查询待检货-已拣货组件的数据
     */
    @GetMapping
    public Result getPageAll(OutboundOrderPickingDto outboundOrderPickingDto) {
        log.info("分页查询待检货-已拣货组件的数据");
        return Result.success(outboundOrderDetailsServiceImpl.getPageAll(outboundOrderPickingDto));
    }
    /**
     * 新增出库单明细
     */
    @PostMapping
    public Result add(@RequestBody OutboundOrderDetails outboundOrderDetails) {
        outboundOrderDetails.setCreateTime(new Date());
        return outboundOrderDetailsServiceImpl.save(outboundOrderDetails) ? Result.success("添加成功") : Result.error("添加失败");
    }

    /**
     * 修改出库单明细
     */
    @PutMapping
    public Result update(@RequestBody OutboundOrderDetails outboundOrderDetails) {
        outboundOrderDetails.setUpdateTime(new Date());
        return outboundOrderDetailsServiceImpl.updateById(outboundOrderDetails) ? Result.success("修改成功") : Result.error("修改失败");
    }

    /**
     * 删除出库单明细
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return outboundOrderDetailsServiceImpl.removeById(id) ? Result.success("删除成功") : Result.error("删除失败");
    }



    /**
     * 查询全部出库单明细
     */
    @GetMapping("/all")
    public Result getAll() {
        return Result.success(outboundOrderDetailsServiceImpl.list());
    }

}
