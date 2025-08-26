package com.lemon.controller;
import com.lemon.domain.OutboundOrderPackage;
import com.lemon.service.impl.OutboundOrderPackageServiceImpl;
import com.lemon.domain.Result;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 出库单打包表(outbound_order_package)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/outbound_order_package")
public class OutboundOrderPackageController {
    /**
     * 服务对象
     */
    @Autowired
    private OutboundOrderPackageServiceImpl outboundOrderPackageServiceImpl;

    /**
     * 新增出库单打包记录
     */
    @PostMapping
    public Result add(@RequestBody OutboundOrderPackage outboundOrderPackage) {
        outboundOrderPackage.setCreateTime(new Date());
        return outboundOrderPackageServiceImpl.save(outboundOrderPackage) ? Result.success("添加成功") : Result.error("添加失败");
    }

    /**
     * 修改出库单打包记录
     */
    @PutMapping
    public Result update(@RequestBody OutboundOrderPackage outboundOrderPackage) {
        outboundOrderPackage.setUpdateTime(new Date());
        return outboundOrderPackageServiceImpl.updateById(outboundOrderPackage) ? Result.success("修改成功") : Result.error("修改失败");
    }

    /**
     * 删除出库单打包记录
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return outboundOrderPackageServiceImpl.removeById(id) ? Result.success("删除成功") : Result.error("删除失败");
    }

    /**
     * 根据id查询出库单打包记录
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        return Result.success(outboundOrderPackageServiceImpl.getById(id));
    }

    /**
     * 查询全部出库单打包记录
     */
    @GetMapping("/all")
    public Result getAll() {
        return Result.success(outboundOrderPackageServiceImpl.list());
    }

}
