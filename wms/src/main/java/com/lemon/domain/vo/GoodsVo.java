package com.lemon.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVo {
    /**
     * 商品编码
     */
    private String saasId;

    /**
     * 商品名称
     */
    private String title;

    /**
     * 商品类别
     */
    private String category;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 供应商名称
     */
    private String supplier;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 商品条码
     */
    private String code;

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
     * 商品成本
     */
    private BigDecimal realPrice;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 其他规格信息
     */
    private String spec;
}
