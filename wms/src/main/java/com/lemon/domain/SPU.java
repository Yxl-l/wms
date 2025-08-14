package com.lemon.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品spu
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("spu")//表名
public class SPU {
    /**
     * 商品ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subTitle;

    /**
     * 分类ID1
     */
    private Integer categoryId1;

    /**
     * 分类ID2
     */
    private Integer categoryId2;

    /**
     * 分类ID3
     */
    private Integer categoryId3;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品详情
     */
    private String content;

    /**
     * 品牌ID
     */
    private Integer brandId;

    /**
     * 供应商ID
     */
    private Integer supplierId;

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

    /**
     * SKU集合，非数据库字段
     */
    @TableField(exist = false)
    private List<SKU> skuList;
}



