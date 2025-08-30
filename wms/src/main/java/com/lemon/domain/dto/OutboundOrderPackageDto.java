package com.lemon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 打包单表分页查询dto
 */
public class OutboundOrderPackageDto {

    private String title;//商品名称
    /**
     * 状态(1 没打包、2已打包、
     */
    private Integer packageStatus;//打包单状态

    private Integer page = 1;
    private Integer pageSize = 10;
}
