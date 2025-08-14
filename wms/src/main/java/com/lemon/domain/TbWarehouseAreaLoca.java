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
 * 库位表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_warehouse_area_loca")
public class TbWarehouseAreaLoca {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 库位编号
     */
    @TableField(value = "loca_code")
    private String locaCode;

    /**
     * 库位长度
     */
    @TableField(value = "loca_len")
    private Integer locaLen;

    /**
     * 库位宽度
     */
    @TableField(value = "loca_width")
    private Integer locaWidth;

    /**
     * 库位高度
     */
    @TableField(value = "loca_height")
    private Integer locaHeight;

    /**
     * 库位容积
     */
    @TableField(value = "local_volume")
    private Integer localVolume;

    /**
     * 库位承重
     */
    @TableField(value = "local_bearing_capacity")
    private Integer localBearingCapacity;

    /**
     * 巷道号
     */
    @TableField(value = "tunnel_number")
    private String tunnelNumber;

    /**
     * 货架号
     */
    @TableField(value = "shelf_number")
    private String shelfNumber;

    /**
     * 层号
     */
    @TableField(value = "layer_number")
    private String layerNumber;

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