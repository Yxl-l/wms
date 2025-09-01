package com.lemon.controller;
import com.lemon.domain.OutboundOrderWeights;
import com.lemon.domain.dto.OutboundOrderWeightsDto;
import com.lemon.service.impl.OutboundOrderWeightsServiceImpl;
import com.lemon.domain.Result;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 出库单称重记录表(outbound_order_weights)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/outbound_order_weights")
public class OutboundOrderWeightsController {

    /**
     * 服务对象
     */
    @Autowired
    private OutboundOrderWeightsServiceImpl outboundOrderWeightsServiceImpl;
    /**
     * 分页查询
     */
    @GetMapping
    public Result getPageAll(OutboundOrderWeightsDto outboundOrderWeightsDto) {
        return Result.success(outboundOrderWeightsServiceImpl.getPageAll(outboundOrderWeightsDto));
    }
    /**
     * 新增出库单称重记录
     */
    @PostMapping
    public Result add(@RequestBody OutboundOrderWeights outboundOrderWeights) {
        outboundOrderWeights.setCreateTime(new Date());
        return outboundOrderWeightsServiceImpl.save(outboundOrderWeights) ? Result.success("添加成功") : Result.error("添加失败");
    }

    /**
     * 修改出库单称重记录
     */
    @PutMapping
    public Result update(@RequestBody OutboundOrderWeights outboundOrderWeights) {
        outboundOrderWeights.setUpdateTime(new Date());
        return outboundOrderWeightsServiceImpl.updateById(outboundOrderWeights) ? Result.success("修改成功") : Result.error("修改失败");
    }

    /**
     * 删除出库单称重记录
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return outboundOrderWeightsServiceImpl.removeById(id) ? Result.success("删除成功") : Result.error("删除失败");
    }

    /**
     * 根据id查询出库单称重记录
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        return Result.success(outboundOrderWeightsServiceImpl.getById(id));
    }

    /**
     * 查询全部出库单称重记录
     */
    @GetMapping("/all")
    public Result getAll() {
        return Result.success(outboundOrderWeightsServiceImpl.list());
    }

}
