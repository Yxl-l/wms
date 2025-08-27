package com.lemon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页查询待检货-已拣货组件的分页查询dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutboundOrderPickingDto {
    private String title;//商品名称
    private String customerName;//客户名称
    private String invoiceCode;//发货单号
    private Integer pickStatus;//状态
    private Integer page = 1;
    private Integer pageSize = 10;
}
