package com.lemon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 称重单表分页查询dto
 */
public class OutboundOrderWeightsDto {
    private String title;//商品名称
    /**
     * 状态(1 没打包、2已打包、
     */
    private Integer weightsStatus;//称重单状态
    private String invoiceCode;//发货单单号
    private String weightsCode;//称重单号


    private Integer page = 1;
    private Integer pageSize = 10;
}
