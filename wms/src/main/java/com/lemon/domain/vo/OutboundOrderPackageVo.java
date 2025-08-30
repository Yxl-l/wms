package com.lemon.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 打包界面vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutboundOrderPackageVo {
    //明细单数据
   private Integer weight;//重量
   private Integer volume;//体积
    private String pickEmpId;//复检员
    private Integer maxCount;//订单数量
    //sku表
    private String skuId;//商品编码
    private String title;//商品名称
    private String spuId;//规格编码
    private String code;//条码
    //出库单表
    private String customerName;//客户名称
    private String invoiceCode;//发货单号
     private String outboundOrderId;//出库单id
    //打包单表
     private Integer id;//打包单id
    private Integer packageStatus;//打包单状态
    private String createBy;//创建人
    private Date createTime;//创建时间
    private Integer packageEmpId;//打包员工
    private Integer packageCount;//已打包数量

}
