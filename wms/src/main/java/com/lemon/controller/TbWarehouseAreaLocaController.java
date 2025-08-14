package com.lemon.controller;

import com.lemon.service.impl.TbWarehouseAreaLocaServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* 库位表(tb_warehouse_area_loca)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/tb_warehouse_area_loca")
public class TbWarehouseAreaLocaController {
/**
* 库位表服务
*/
    @Autowired
    private TbWarehouseAreaLocaServiceImpl tbWarehouseAreaLocaServiceImpl;



}
