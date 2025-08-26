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
 * 出库单打包表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "outbound_order_package")
public class OutboundOrderPackage {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 打包单号
     */
    @TableField(value = "package_code")
    private String packageCode;

    /**
     * 出库单明细ID
     */
    @TableField(value = "outbound_order_detail_id")
    private Long outboundOrderDetailId;

    /**
     * 规格ID
     */
    @TableField(value = "sku_id")
    private Long skuId;

    /**
     * 打包数量
     */
    @TableField(value = "package_count")
    private Integer packageCount;

    /**
     * 打包员工ID
     */
    @TableField(value = "package_emp_id")
    private Integer packageEmpId;

    /**
     * 打包时间
     */
    @TableField(value = "package_time")
    private Date packageTime;

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