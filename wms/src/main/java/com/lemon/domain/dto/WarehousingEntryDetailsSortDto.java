package com.lemon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehousingEntryDetailsSortDto {
    private Long warehousingEntryDetailsId;//入库单明细id
    private Long skuId;//规格id
    private Integer status;//状态(1 分拣中，2 已完成分拣，3 已上架)


    private Integer page = 1;
    private Integer pageSize = 10;
}
