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
 * 入库明细分拣表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "warehousing_entry_details_sort")
public class WarehousingEntryDetailsSort {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分拣数量
     */
    @TableField(value = "`count`")
    private Integer count;

    /**
     * 入库单明细ID
     */
    @TableField(value = "warehousing_entry_details_id")
    private Long warehousingEntryDetailsId;

    /**
     * 规格编号
     */
    @TableField(value = "sku_id")
    private Long skuId;

    /**
     * 状态(1 分拣中，2 已完成分拣，3 已上架)
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 破损个数
     */
    @TableField(value = "damage_count")
    private Integer damageCount;

    /**
     * 超出数量
     */
    @TableField(value = "surpass_count")
    private Integer surpassCount;

    /**
     * 缺少数量
     */
    @TableField(value = "lack_count")
    private Integer lackCount;

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