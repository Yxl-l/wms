package com.lemon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TbLogisticsCompanyDto {
    /**
     *物流公司名子
     */
    private String companyName;

    private Integer page = 1;
    private Integer pageSize = 10;
}
