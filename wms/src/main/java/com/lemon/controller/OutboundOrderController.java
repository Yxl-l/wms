package com.lemon.controller;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lemon.domain.OutboundOrder;
import com.lemon.domain.OutboundOrderDetails;
import com.lemon.domain.dto.OutboundOrderDetailsDto;
import com.lemon.domain.dto.OutboundOrderDto;
import com.lemon.service.OutboundOrderDetailsService;
import com.lemon.service.OutboundOrderService;
import com.lemon.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import java.util.List;

/**
 * 出库单表(outbound_order)表控制层
 *
 * @author xxxxx
 */
@Slf4j
@RestController
@RequestMapping("/outbound_order")
public class OutboundOrderController {
    /**
     * 服务对象
     */
    @Autowired
    private OutboundOrderService outboundOrderServiceImpl;
    @Autowired
    private OutboundOrderDetailsService outboundOrderDetailsServiceImpl;

    /**
     * 新增出库单
     */
    @PostMapping
    @Transactional // 添加事务注解确保数据一致性
    public Result add(@RequestBody OutboundOrderDetailsDto outboundOrderDetailsDto) {
        try {
            // 创建出库单
            OutboundOrder outboundOrder = BeanUtil.toBean(outboundOrderDetailsDto, OutboundOrder.class);
            outboundOrder.setCreateTime(new Date());
            outboundOrder.setInvoiceCode(UUID.fastUUID().toString());

            // 保存出库单
            boolean orderSaved = outboundOrderServiceImpl.save(outboundOrder);
            if (!orderSaved) {
                log.error("出库单保存失败: {}", outboundOrderDetailsDto);
                return Result.error("出库单创建失败");
            }

            // 确认ID已生成（正常情况下MyBatis-Plus会自动回填）
            if (outboundOrder.getId() == null) {
                log.error("出库单ID获取失败: {}", outboundOrder);
                return Result.error("系统错误：无法获取出库单ID");
            }
            // 创建出库单详情
            List<OutboundOrderDetails> outboundOrderDetails = outboundOrderDetailsDto.getOutboundOrderDetails();
            if (outboundOrderDetails != null && !outboundOrderDetails.isEmpty()) {
                for (OutboundOrderDetails detail : outboundOrderDetails) {
                    detail.setOutboundOrderId(outboundOrder.getId());
                    detail.setCreateTime(new Date());
                    detail.setMaxCount(detail.getOutCount());
                    boolean saved = outboundOrderDetailsServiceImpl.save(detail);
                    if (!saved) {
                        log.error("出库单明细保存失败: {}", detail);
                        return Result.error("出库单明细保存失败");
                    }
                }
            }

            return Result.success("添加成功");

        } catch (Exception e) {
            log.error("新增出库单异常: {}", e.getMessage(), e);
            return Result.error("系统异常：" + e.getMessage());
        }
    }

        /**
         * 修改出库单
         */
    @Transactional // 添加事务注解确保数据一致性
    @PutMapping
    public Result update(@RequestBody OutboundOrder outboundOrder) {
        if (outboundOrder.getStatus()==666){
            //自定义当传来Status为10时表明是设置承运单位
            Integer weight = outboundOrder.getTotalWeight();
            Integer type = outboundOrder.getLogisticsType();
            if (weight != null) {
                //物流物流类型：1，省内、2，普通国内，3，边远地区
                outboundOrder.setPrice(type==1 ? weight * 3 + 5: type==2 ? weight * 6 + 10 :  weight * 10 + 20 );
            } else {
                outboundOrder.setPrice(0);
            }
            //物流单号
            outboundOrder.setLogisticsNumber(UUID.fastUUID().toString());
            //恢复原有状态，撤销掉用于识别的10
            outboundOrder.setStatus(null);
        }

        outboundOrder.setUpdateTime(new Date());
        return outboundOrderServiceImpl.updateById(outboundOrder) ? Result.success("修改成功") : Result.error("修改失败");
    }

    /**
     * 删除出库单
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return outboundOrderServiceImpl.removeById(id) ? Result.success("删除成功") : Result.error("删除失败");
    }

    /**
     * 根据id查询出库单
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        // 查询出库单
        OutboundOrder outboundOrder = outboundOrderServiceImpl.getById(id);
        // 检查出库单是否存在
        if (outboundOrder == null) {
            return Result.error("出库单不存在");
        }

        // 查询出库单详情
        List<OutboundOrderDetails> outboundOrderDetails = outboundOrderDetailsServiceImpl.list(
                Wrappers.<OutboundOrderDetails>lambdaQuery()
                        .eq(OutboundOrderDetails::getOutboundOrderId, outboundOrder.getId())
        );

        OutboundOrderDetailsDto bean = BeanUtil.toBean(outboundOrder, OutboundOrderDetailsDto.class);
        bean.setOutboundOrderDetails(outboundOrderDetails);
        return Result.success(bean);
    }


    /**
     * 查询全部出库单
     */
    @GetMapping("/all")
    public Result getAll() {
        return Result.success(outboundOrderServiceImpl.list());
    }

    /**
     * 修改出库单和明细
     */
    @Transactional // 添加事务注解确保数据一致性
    @PutMapping("/details")
    public Result updateDetails(@RequestBody OutboundOrderDetailsDto outboundOrderDetailsDto) {

        try {
            // 创建出库单
            OutboundOrder outboundOrder = BeanUtil.toBean(outboundOrderDetailsDto, OutboundOrder.class);
            outboundOrder.setUpdateTime(new Date());
            // 保存出库单
            boolean orderSaved = outboundOrderServiceImpl.updateById(outboundOrder);
            if (!orderSaved) {
                log.error("出库单保存失败: {}", outboundOrderDetailsDto);
                return Result.error("出库单创建失败");
            }

            // 创建出库单详情
            List<OutboundOrderDetails> outboundOrderDetails = outboundOrderDetailsDto.getOutboundOrderDetails();
            if (outboundOrderDetails != null && !outboundOrderDetails.isEmpty()) {
                for (OutboundOrderDetails detail : outboundOrderDetails) {
                    detail.setOutboundOrderId(outboundOrder.getId());
                    detail.setUpdateTime(new Date());
                    boolean saved = outboundOrderDetailsServiceImpl.updateById(detail);
                    if (!saved) {
                        log.error("出库单明细保存失败: {}", detail);
                        return Result.error("出库单明细保存失败");
                    }
                }
            }

            return Result.success("添加成功");

        } catch (Exception e) {
            log.error("新增出库单异常: {}", e.getMessage(), e);
            return Result.error("系统异常：" + e.getMessage());
        }
    }
    /**
     * 分页查询出库单
     */
    @GetMapping
    public Result getPageAll(OutboundOrderDto outboundOrderDto) {
        log.info("分页查询出库单");
      return Result.success(outboundOrderServiceImpl.getPageAll(outboundOrderDto));

    }

}
