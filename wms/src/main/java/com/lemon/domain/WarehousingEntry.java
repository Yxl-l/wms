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
 * 入库单表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "warehousing_entry")
public class WarehousingEntry {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 货主ID
     */
    @TableField(value = "consignor_id")
    private Long consignorId=1L;

    /**
     * 批次号
     */
    @TableField(value = "batch_number")
    private String batchNumber;

    /**
     * 批次商品数量
     */
    @TableField(value = "total_count")
    private Integer totalCount;

    /**
     * 预测到达时间
     */
    @TableField(value = "predicted_arrival_time")
    private Date predictedArrivalTime;

    /**
     * 仓库ID
     */
    @TableField(value = "warehouse_id")
    private Integer warehouseId;

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
     * 状态(1 未确认，2 部分已确认，3 全部确认、 4 拣货中、5 已上架)
     */
    @TableField(value = "`status`")
    //默认未确认
    private Integer status=1;


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