package com.lemon.domain.dto;

import com.lemon.domain.OutboundOrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutboundOrderDetailsDto {
    /**
     * 出库单字段
     */
    private Long id;// 出库单ID
    private String customerName;  // 客户name
    private String invoiceCode;// 发货单编号
    private Integer status = 1;   // 状态(1 预发货、2确认发货、3 拣货中、4 拣货完成 、5 待打包、6 待称重、7 待发货、8 已发货、9 已签收)
    private Integer totalCount;    // 总数量
    private Integer totalWeight;   // 总重量
    private Integer totalVolume;   // 总体积
    private String createBy="蔡徐坤";      // 创建人账号
    private Integer empId=1;        // 操作员工ID

    /**
     * 出库单明细字段
     */
    private List<OutboundOrderDetails> outboundOrderDetails;
}
