package com.lemon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDto {
    /**
     * 仓库名称
     */
    private String name;
    /**
     * 城市id
     */
    private Integer cityId;

    private Integer page = 1;
    private Integer pageSize = 10;
    public void setTitle(String title) {
        this.name = "undefined".equals(title) ? null : title;
    }


}
