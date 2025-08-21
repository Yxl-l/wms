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
 * 入库上架表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "warehousing_entry_up")
public class WarehousingEntryUp {
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
     * 上架仓库ID
     */
    @TableField(value = "warehouse_id")
    private Integer warehouseId;

    /**
     * 上架库区ID
     */
    @TableField(value = "warehouse_area_id")
    private Integer warehouseAreaId;

    /**
     * 上架库位ID
     */
    @TableField(value = "warehouse_area_loca_id")
    private Integer warehouseAreaLocaId;

    /**
     * 上架数量
     */
    @TableField(value = "`count`")
    private Integer count;

    /**
     * 分拣明细ID
     */
    @TableField(value = "details_sort_id")
    private Long detailsSortId;

    /**
     * 上架状态(1 未上架，2 已上架)
     */
    @TableField(value = "`status`")
    private Integer status;

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