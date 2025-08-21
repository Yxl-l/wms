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
 * 入库明细表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "warehousing_entry_details")
public class WarehousingEntryDetails {
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
     * 数量
     */
    @TableField(value = "`count`")
    private Integer count;

    /**
     * 供应商ID(默认选中，但允许灵活修改)
     */
    @TableField(value = "supplier_id")
    private Long supplierId;

    /**
     * 单价(默认查询，但允许灵活修改)
     */
    @TableField(value = "price")
    private Integer price;

    /**
     * 入库单ID
     */
    @TableField(value = "warehousing_entry_id")
    private Long warehousingEntryId;

    /**
     * 实际到达时间
     */
    @TableField(value = "real_arrival_time")
    private Date realArrivalTime;

    /**
     * 预测到达时间
     */
    @TableField(value = "predicted_arrival_time")
    private Date predictedArrivalTime;

    /**
     * 卸货员工ID
     */
    @TableField(value = "unload_emp_id")
    private Integer unloadEmpId;

    /**
     * 卸货时间
     */
    @TableField(value = "unload_time")
    private Date unloadTime;

    /**
     * 状态(1 未到货，2 已到货，3 已卸货，4 分拣中，5 完成分拣)
     */
    @TableField(value = "`status`")
    //默认未确认
    private Integer status=1;

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