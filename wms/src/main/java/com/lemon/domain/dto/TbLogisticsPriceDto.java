package com.lemon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TbLogisticsPriceDto {
    /**
     *类型类型(1 同城、2 同省、3 跨省、4 经济区互寄)
     */
    private Integer category;
    /**
     * 关联物流公司id
     */
    private Integer logisticsCompanyId;

    private Integer page = 1;
    private Integer pageSize = 10;
}
