package com.lemon.controller;
import com.lemon.domain.PageResult;
import com.lemon.domain.Result;
import com.lemon.domain.TbLogisticsCompany;
import com.lemon.domain.dto.TbLogisticsCompanyDto;
import com.lemon.mapper.TbLogisticsCompanyMapper;
import com.lemon.service.impl.TbLogisticsCompanyServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* 物流公司表(tb_logistics_company)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/tb_logistics_company")
@Slf4j
@RequiredArgsConstructor
public class TbLogisticsCompanyController {
    private final TbLogisticsCompanyMapper tbLogisticsCompanyMapper;
    private final TbLogisticsCompanyServiceImpl tbLogisticsCompanyService;
    /**
     * 查询所有数据
     */
    @GetMapping("/getAll")
    public Result getAll() {
        log.info("查询所有物流公司");
        return Result.success(tbLogisticsCompanyMapper.selectList(null));
    }
    /**
     * 分页查询
     */
    @GetMapping
    public PageResult<TbLogisticsCompany> getPageAll(TbLogisticsCompanyDto tbLogisticsCompanyDto) {
        log.info("分页查询物流公司");
        return tbLogisticsCompanyService.getPageAll(tbLogisticsCompanyDto);
    }
    /**
     * 新增数据
     */
    @PostMapping
    public Result add(@RequestBody TbLogisticsCompany tbLogisticsCompany) {
        log.info("添加物流公司");
        tbLogisticsCompany.setCreateTime(new java.util.Date());
        return tbLogisticsCompanyService.save(tbLogisticsCompany) ? Result.success("添加成功") : Result.error("添加失败");
    }
    /**
     * 修改数据
     */
    @PutMapping
    public Result update(@RequestBody TbLogisticsCompany tbLogisticsCompany) {
        log.info("修改物流公司");
        return tbLogisticsCompanyService.updateById(tbLogisticsCompany) ? Result.success("修改成功") : Result.error("修改失败");
    }
    /**
     * 删除数据
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除物流公司");
        return tbLogisticsCompanyService.removeById(id) ? Result.success("删除成功") : Result.error("删除失败");
    }
    /**
     * 根据id查询数据
     *
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询物流公司");
        return Result.success(tbLogisticsCompanyMapper.selectById(id));
    }



}
