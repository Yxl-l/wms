package com.lemon.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 出库单明细
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "outbound_order_details")
public class OutboundOrderDetails {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 规格ID
     */
    @TableField(value = "sku_id")
    private Long skuId;

    /**
     * 出库数量
     */
    @TableField(value = "out_count")
    private Integer outCount;
    /**
     * 最大数量
     */
    @TableField(value = "max_count")
    private Integer maxCount;
    /**
     * 仓库ID
     */
    @TableField(value = "target_warehouse_id")
    private Integer targetWarehouseId;

    /**
     * 库位ID
     */
    @TableField(value = "target_warehouse_area_id")
    private Integer targetWarehouseAreaId;

    /**
     * 库位ID
     */
    @TableField(value = "target_warehouse_area_loca_id")
    private Integer targetWarehouseAreaLocaId;

    /**
     * 出库单ID
     */
    @TableField(value = "outbound_order_id")
    private Long outboundOrderId;

    /**
     * 拣货状态(1 未拣货、2 已拣货)
     */
    @TableField(value = "pick_status")
    private Integer pickStatus;

    /**
     * 拣货复核员工ID
     */
    @TableField(value = "pick_emp_id")
    private Integer pickEmpId;

    /**
     * 拣货复核时间
     */
    @TableField(value = "pick_time")
    private Date pickTime;

    /**
     * SaaS标识
     */
    @TableField(value = "saas_id")
    private String saasId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 创建人账号
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 修改人账号
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 是否删除  1 未删除  2 已删除
     */
    @TableField(value = "is_del")
    private Integer isDel;
}