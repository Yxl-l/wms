package com.lemon.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String employeeId;
    private String name;
    private Integer role;
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer gender;
    private String phone;
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer status;
    private String username;
    private String password;
}
