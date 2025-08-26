package com.lemon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 出库单表分页查询dto
 */
public class OutboundOrderDto {
    /**
     * 客户name
     */
    private String customerName;
    /**
     * 发货单号
     */
    private String invoiceCode;
    /**
     * 状态(1 预发货、2确认发货、3 拣货中、4 拣货完成 、5 待打包、6 待称重、7 待发货、8 已发货、9 已签收)
     */
    private Integer status;

    private Integer page = 1;
    private Integer pageSize = 10;
}
