package com.lemon.controller;

import com.lemon.domain.Pcd;
import com.lemon.domain.Result;
import com.lemon.service.PcdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 省市区
 * @author YXL
 */
@RestController
@RequestMapping("/pcd")
@Slf4j
@RequiredArgsConstructor
public class PcdController {
    private final PcdService pcdService;
    /**
     * 获取所有省、市、区(树形结构 )
     * @return
     */
    @GetMapping
    public Result getAll() {
        log.info("获取所有省、市、区");
        return Result.success(pcdService.getAll());
    }
    /**
     * 获取所有省、市、区(普通结构 )
     * @return
     */
    @GetMapping("/all")
    public Result getPcdAll() {
        log.info("获取所有省、市、区(普通结构)");
        return Result.success(pcdService.list());
    }
    /**
     * 添加省、市、区
     */
    @PostMapping
    public Result add(@RequestBody Pcd pcd) {
        log.info("添加省、市、区");
        pcd.setCreateTime(LocalDateTime.now());
        return pcdService.save(pcd) ? Result.success("添加成功") : Result.error("添加失败");
    }



}
