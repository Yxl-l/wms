package com.lemon.controller;
import com.lemon.domain.LockTable;
import com.lemon.service.impl.LockTableServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* (lock_table)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/lock_table")
public class LockTableController {
/**
* 服务对象
*/
    @Autowired
    private LockTableServiceImpl lockTableServiceImpl;



}
