package com.lemon.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 省市区
 * @author YXL
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pcd {
    //主键
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    //名称
    private String name;
    //级别1 省  2  市  3  区
    private Integer level;
    //父级id
    private Integer parentId;
    //排序
    private Integer sort;
    //创建时间
//    @TableField(fill = TableField.Fill.INSERT)
    private LocalDateTime createTime;
    //创建人
    private String createBy;
    //更新时间
//    @TableField(fill = TableField.Fill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    //更新人
    private String updateBy;
    /**
     * 逻辑删除,value未删除delval已删除
     */
    @TableLogic(value = "1",delval = "2")
    private Integer isDel;
    /**
     * 非表字段
     */
    @TableField(exist = false)
    private List<Pcd> children;

}
