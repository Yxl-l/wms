package com.lemon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehousingEntryDetailsDto {
    private Long warehousingEntryId;//入库单id
    private Long skuId;//规格id
    private Long supplierId;//供应商id
    private Long unloadEmpId;//卸货员工id
    /**
     * 状态
     */
    private Integer status;

    private Integer page = 1;
    private Integer pageSize = 10;
}
