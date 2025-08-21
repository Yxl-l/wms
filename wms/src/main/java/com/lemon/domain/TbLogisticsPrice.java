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
 * 物流价格表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_logistics_price")
public class TbLogisticsPrice {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 每立方费用（单位：分）
     */
    @TableField(value = "cube_price")
    private Integer cubePrice;

    /**
     * 每千克费用（单位：分）
     */
    @TableField(value = "kilogram_price")
    private Integer kilogramPrice;

    /**
     * 每千米运输费用
     */
    @TableField(value = "trans_kilometer_price")
    private Integer transKilometerPrice;

    /**
     * 类型(1 同城、2 同省、3 跨省、4 经济区互寄)
     */
    @TableField(value = "category")
    private Integer category;

    /**
     * 关联物流公司
     */
    @TableField(value = "logistics_company_id")
    private Integer logisticsCompanyId;

    /**
     * SaaS标识
     */
    @TableField(value = "saas_id")
    private String saasId="cxk123";

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
    private Integer isDel=1;
}