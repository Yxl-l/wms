package com.lemon.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 供应商
 * @author YXL
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    //主键
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    //供应商名称
    private String supplierName;
    //联系人
    private String linkName;
    //联系电话
    private String linkPhone;
    //城市id
    private Integer cityCode;
    //省id
    private Integer provinceId;
    //地址
    private String address;
    //SaaS标识
    private String saasId;
    //创建时间
    private LocalDateTime createTime;
    //创建人
    private String createBy;
    //更新时间
    private LocalDateTime updateTime;
//    修改人账号
    private String updateBy;
    /**
     * 逻辑删除,value未删除delval已删除
     */
    @TableLogic(value = "1",delval = "2")
    private Integer isDel;
}
