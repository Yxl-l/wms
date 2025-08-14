package com.lemon.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品sku
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("sku")//表名
public class SKU {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 真实价格
     */
    private BigDecimal realPrice;

    /**
     * Spu关联ID
     */
    private Long spuId;

    /**
     * 商品条码
     */
    private String code;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品单位
     */
    private Integer unit;

    /**
     * 商品重量
     */
    private Integer productWeight;

    /**
     * 商品长度
     */
    private Integer productLength;

    /**
     * 商品宽度
     */
    private Integer productWidth;

    /**
     * 商品高度
     */
    private Integer productHeight;

    /**
     * 商品体积
     */
    private Integer productVolume;

    /**
     * 其他规格
     */
    private String spec;

    /**
     * SaaS标识
     */
    private String saasId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人账号
     */
    private String createBy;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 修改人账号
     */
    private String updateBy;

    /**
     * 是否删除  1 未删除  2 已删除
     */
    @TableLogic(value = "1", delval = "2")
    private Integer isDel=1;
}


