package com.lemon.controller;
import cn.hutool.core.lang.UUID;
import com.lemon.domain.*;
import com.lemon.domain.dto.OutboundOrderPackageDto;
import com.lemon.domain.vo.OutboundOrderPackageVo;
import com.lemon.service.OutboundOrderDetailsService;
import com.lemon.service.impl.OutboundOrderDetailsServiceImpl;
import com.lemon.service.impl.OutboundOrderPackageServiceImpl;
import com.lemon.service.impl.OutboundOrderWeightsServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class OutboundOrderPackageController {
    /**
     * 服务对象
     */
    @Autowired
    private OutboundOrderPackageServiceImpl outboundOrderPackageServiceImpl;
    @Autowired
    private OutboundOrderWeightsServiceImpl outboundOrderWeightsServiceImpl;
    @Autowired
    private OutboundOrderDetailsServiceImpl outboundOrderDetailsServiceImpl;
    /**
     * 分页查询
     */
    @GetMapping
    public PageResult<OutboundOrderPackageVo> getPageAll(OutboundOrderPackageDto outboundOrderPackageDto) {
        log.info("分页查询出库单打包记录");
        return outboundOrderPackageServiceImpl.getPageAll(outboundOrderPackageDto);

    }

    /**
     * 新增出库单打包记录
     */
    @PostMapping
    public Result add(@RequestBody OutboundOrderPackage outboundOrderPackage) {
        //获取该打保单完整数据
        OutboundOrderPackage oop = outboundOrderPackageServiceImpl.getById(outboundOrderPackage.getId());
        //获取能打包的最大数量
        OutboundOrderDetails ood = outboundOrderDetailsServiceImpl.getById(oop.getOutboundOrderDetailId());
        //判断打包数量是否合法
        if (outboundOrderPackage.getPackageCount()> ood.getMaxCount()){
            return Result.error("打包数量超出最大数量,最大数量为"+ood.getMaxCount());
        }
        //合法创建称重单
        OutboundOrderWeights outboundOrderWeights = new OutboundOrderWeights();
        outboundOrderWeights.setWeightsCode(UUID.fastUUID().toString());
        outboundOrderWeights.setOutboundOrderDetailId(outboundOrderPackage.getOutboundOrderDetailId());
        outboundOrderWeights.setWeightsEmpId(1);
        outboundOrderWeights.setCreateTime(new Date());
        outboundOrderWeights.setWeightsUnit(1);
        outboundOrderWeights.setCreateBy("蔡徐坤");
        outboundOrderWeights.setWeightsStatus(1);
        //创建称重单
        outboundOrderWeightsServiceImpl.save(outboundOrderWeights);
        //修改打保单
        outboundOrderPackage.setUpdateTime(new Date());
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
