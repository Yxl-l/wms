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
 * 出库单表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "outbound_order")
public class OutboundOrder {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 客户name
     */
    @TableField(value = "customer_name")
    private String customerName;

    /**
     * 发货单号
     */
    @TableField(value = "invoice_code")
    private String invoiceCode;

    /**
     * 状态(1 预发货、2确认发货、3 拣货中、4 拣货完成 、5 待打包、6 待称重、7 待发货、8 已发货、9 已签收)
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 总数量
     */
    @TableField(value = "total_count")
    private Integer totalCount;

    /**
     * 总重量
     */
    @TableField(value = "total_weight")
    private Integer totalWeight;

    /**
     * 总体积
     */
    @TableField(value = "total_volume")
    private Integer totalVolume;

    /**
     * 操作员工ID
     */
    @TableField(value = "emp_id")
    private Integer empId;

    /**
     * 承运费用
     */
    @TableField(value = "price")
    private Integer price;

    /**
     * 物流公司ID
     */
    @TableField(value = "logistics_company_id")
    private Long logisticsCompanyId;

    /**
     * 物流单号
     */
    @TableField(value = "logistics_number")
    private String logisticsNumber;

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