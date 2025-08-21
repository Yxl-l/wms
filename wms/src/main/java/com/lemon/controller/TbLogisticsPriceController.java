package com.lemon.controller;
import com.lemon.domain.PageResult;
import com.lemon.domain.Result;
import com.lemon.domain.TbLogisticsPrice;
import com.lemon.domain.dto.TbLogisticsPriceDto;
import com.lemon.mapper.TbLogisticsPriceMapper;
import com.lemon.service.impl.TbLogisticsPriceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
* 物流价格表(tb_logistics_price)表控制层
*
* @author xxxxx
*/
@Slf4j
@RestController
@RequestMapping("/tb_logistics_price")
public class TbLogisticsPriceController {
/**
* 服务对象
*/
    @Autowired
    private TbLogisticsPriceServiceImpl tbLogisticsPriceService;

    /***
    * 分页查询
    * @param tbLogisticsPriceDto
    * @return
    */
    @GetMapping
    public PageResult<TbLogisticsPrice> getPageAll(TbLogisticsPriceDto tbLogisticsPriceDto) {
        log.info("分页查询物流价格");
        return tbLogisticsPriceService.getPageAll(tbLogisticsPriceDto);
    }
    /***
    * 添加
    * @param tbLogisticsPrice
    * @return
    */
    @PostMapping
    public Result add(@RequestBody TbLogisticsPrice tbLogisticsPrice) {
        log.info("添加物流价格");
        tbLogisticsPrice.setCreateTime(new Date());
        return tbLogisticsPriceService.save(tbLogisticsPrice) ? Result.success("添加成功") : Result.error("添加失败");
    }
    /***
    * 修改
    * @param tbLogisticsPrice
    * @return
    */
    @PutMapping
    public Result update(@RequestBody TbLogisticsPrice tbLogisticsPrice) {
        log.info("修改物流价格");
        return tbLogisticsPriceService.updateById(tbLogisticsPrice) ? Result.success("修改成功") : Result.error("修改失败");
    }
    /***
    * 删除
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        log.info("删除物流价格");
        return tbLogisticsPriceService.removeById(id) ? Result.success("删除成功") : Result.error("删除失败");
    }
    /***
    * 获取所有
    * @return
    */
    @GetMapping("/getAll")
    public Result getAll() {
        log.info("获取所有物流价格");
        return Result.success(tbLogisticsPriceService.list());
    }



}
