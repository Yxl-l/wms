package com.lemon.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * 仓库表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_warehouse")//表名
public class TbWarehouse {
    //主键ID
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //仓库名字
    private String name;

    //所在城市ID
    private Integer cityId;

    //所在城市名称
    private String cityName;

    //所在省份ID
    private Integer provinceId;

    //所在省份名字
    private String provinceName;

    //详细地址
    private String address;

    //经纬度
    private String location;

    //负责人名字
    private String personChargeName="蔡徐坤";

    //SaaS标识
    private String saasId="cxk";

    //负责人ID
    private Integer personChargeId=158;

    //排序
    private Integer sort = 1;

    //创建时间
    private LocalDateTime createTime;

    //创建人账号
    private String createBy;

    //修改时间
    private LocalDateTime updateTime;

    //修改人账号
    private String updateBy;

    //是否删除 1 未删除 2 已删除
    @TableLogic(value = "1", delval = "2")
    @TableField("is_del")
    private Integer isDel;
}
