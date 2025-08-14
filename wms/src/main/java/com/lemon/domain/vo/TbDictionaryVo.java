package com.lemon.domain.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 字典表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_dictionary")
public class TbDictionaryVo {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典类型
     */
    @TableField(value = "dict_type")
    private String dictType;

    /**
     * 字典编码
     */
    @TableField(value = "dict_code")
    private String dictCode;

    /**
     * 字典值
     */
    @TableField(value = "dict_value")
    private String dictValue;

    /**
     * 排序
     */
    @TableField(value = "sort_order")
    private Integer sortOrder;

    /**
     * 是否启用(1:启用, 0:禁用)
     */
    @TableField(value = "is_active")
    private Integer isActive;
}
