package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName order_table
 */
@TableName(value ="order_table")
@Data
public class OrderTable implements Serializable {
    private Integer relationId;

    private String orderNumber;

    private Integer userId;

    private Integer productId;

    private Date orderTime;

    private Double paymentAmount;

    private static final long serialVersionUID = 1L;
}