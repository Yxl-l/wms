package com.lemon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseAreaLocaDto {
    /**
     *库区id
     */
    private Integer areaId;
    /**
     * 所属仓库id
     */
    private Integer warehouseId;

    private Integer page = 1;
    private Integer pageSize = 10;
}
