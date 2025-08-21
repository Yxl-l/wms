package com.lemon.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "lock_table")
public class LockTable {
    @TableId(value = "row_key", type = IdType.INPUT)
    private String rowKey;

    @TableField(value = "xid")
    private String xid;

    @TableField(value = "transaction_id")
    private Long transactionId;

    @TableField(value = "branch_id")
    private Long branchId;

    @TableField(value = "resource_id")
    private String resourceId;

    @TableField(value = "`table_name`")
    private String tableName;

    @TableField(value = "pk")
    private String pk;

    /**
     * 0:locked ,1:rollbacking
     */
    @TableField(value = "`status`")
    private Byte status;

    @TableField(value = "gmt_create")
    private Date gmtCreate;

    @TableField(value = "gmt_modified")
    private Date gmtModified;
}