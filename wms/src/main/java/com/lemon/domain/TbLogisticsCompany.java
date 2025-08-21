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
 * 物流公司表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_logistics_company")
public class TbLogisticsCompany {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 物流公司名字
     */
    @TableField(value = "company_name")
    private String companyName;

    /**
     * 物流运输最低费用
     */
    @TableField(value = "min_price")
    private Integer minPrice;

    /**
     * 联系人
     */
    @TableField(value = "link_user")
    private String linkUser;

    /**
     * 联系电话
     */
    @TableField(value = "linke_phone")
    private String linkePhone;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

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