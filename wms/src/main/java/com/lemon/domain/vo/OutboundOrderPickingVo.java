package com.lemon.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
    /**
    * 分页查询待检货-已拣货组件的数据
     */
public class OutboundOrderPickingVo {
    //sku表
    private String id;//商品编码
    private String title;//商品名称
    private String spuId;//规格编码
    private String code;//条码
    //出库单明细表
    private String odId;//出库单明细id
    private String outboundOrderId;//出库单编码
    private Integer maxCount;//订单数量
    private Integer outCount;//待检货数量
    private Integer pickingCount;//已拣货数量
    private Integer weight;//货重量
    private Integer volume;//货体积
    private Date createTime;//创建时间
    private String createBy;//创建人
    private Integer  pickStatus; //状态
    //出库单表
    private String customerName;//客户名称
    private String invoiceCode;//发货单号
}
