package com.lemon.domain.pageParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPageParam {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String name;
    private String employeeId;
}
