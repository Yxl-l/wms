package com.lemon.domain.pageParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierPageParam {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String supplierName;
    //城市id
    private Integer cityCode;


}
