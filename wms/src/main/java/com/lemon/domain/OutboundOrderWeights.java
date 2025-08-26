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
 * 出库单称重记录表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "outbound_order_weights")
public class OutboundOrderWeights {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 称重单号
     */
    @TableField(value = "weights_code")
    private String weightsCode;

    /**
     * 出库单明细ID
     */
    @TableField(value = "outbound_order_detail_id")
    private Long outboundOrderDetailId;

    /**
     * 称重员工ID
     */
    @TableField(value = "weights_emp_id")
    private Integer weightsEmpId;

    /**
     * 称重时间
     */
    @TableField(value = "weights_time")
    private Date weightsTime;

    /**
     * 称重质量
     */
    @TableField(value = "weight_val")
    private Integer weightVal;

    /**
     * 称重单位
     */
    @TableField(value = "weights_unit")
    private Integer weightsUnit;

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