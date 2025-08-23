package com.lemon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehousingEntryUpDto {
//    仓库id
   Integer warehouseId;
   Long skuId;
    private Integer status;
    private Integer page = 1;
    private Integer pageSize = 10;
}
