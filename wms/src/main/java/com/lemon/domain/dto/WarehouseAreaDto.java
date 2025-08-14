package com.lemon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseAreaDto {
    /**
    *库区name
     */
    private String name;
    /**
     * 所属仓库id
     */
    private Integer warehouseId;

    private Integer page = 1;
    private Integer pageSize = 10;

}
