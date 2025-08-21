package com.lemon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehousingEntryDto {
    /**
     * 批次号
     */
    private String batchNumber;
    /**
     * 状态
     */
    private Integer status;
    private Integer page = 1;
    private Integer pageSize = 10;
}
